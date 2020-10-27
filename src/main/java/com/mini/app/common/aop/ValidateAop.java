package com.mini.app.common.aop;

;
import com.mini.app.common.annotation.ValidateArgument;
import com.mini.app.common.annotation.ValidateArguments;
import com.mini.app.common.entity.ApiRequest;
import com.mini.app.common.entity.ApiResponse;
import com.mini.app.common.enums.Result;
import com.mini.app.common.utils.ValidateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: ValidateAop
 * @Description: 参数校验aop
 */
@Aspect
@Component
@Order(1)
public class ValidateAop {

	/**
	 * @param
	 * @return
	 * @Description 拦截标有”@ValidateArguments”注解的类
	 */
	@Pointcut("@annotation(com.mini.app.common.annotation.ValidateArguments)")
	public void validate() {
	}

	/**
	 * @param pjp
	 * @return
	 * @Description 环绕通知进行检验参数
	 */
	@Around("validate()")
	public ApiResponse<?> around(ProceedingJoinPoint pjp) throws Throwable {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //IP地址
            String url = request.getRequestURL().toString();

			Object[] objects = pjp.getArgs();
			Class<?> classTarget = pjp.getTarget().getClass();
			Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
			String methodName = pjp.getSignature().getName();
			ApiRequest<?> apiRequest = (ApiRequest<?>) pjp.getArgs()[0];

			Object data = apiRequest.getData();
			if (data == null) {
				return (ApiResponse<?>) pjp.proceed();
			}
			ValidateArguments validateArguments = classTarget.getMethod(methodName, par)
					.getAnnotation(ValidateArguments.class);
			ValidateArgument[] validateFieldsArr = validateArguments.validateArguments();// 获取需要检验的参数
			String[][] validateFields = new String[validateFieldsArr.length][4];
			if (validateFieldsArr.length == 0) { // 没有需要校验的参数，直接通过
				return (ApiResponse<?>) pjp.proceed();
			}
			for (int i = 0; i < validateFieldsArr.length; i++) {
				ValidateArgument argument = validateFieldsArr[i];
				validateFields[i][0] = argument.fieldName();
				validateFields[i][1] = argument.required() ? "y" : "n";
				validateFields[i][2] = String.valueOf(argument.maxLength());
				validateFields[i][3] = argument.isNumberType() ? "number" : "";
			}
			Map<String, String> resultMap;
			// 参数如果为集合， 则对集合里的每个实体（map或javabean进行判断，有一个检验不通过，返回错误）
			if (data instanceof Collection) {
				Collection<?> collection = (Collection<?>) data;
				if (CollectionUtils.isEmpty(collection)) {
					return ApiResponse.createApiResponse("参数体不能为空集合", Result.ARGUMENT_ERROR);
				}
				for (Object item : collection) {
					resultMap = validateData(item, validateFields);
					if (MapUtils.isNotEmpty(resultMap)) {
						return ApiResponse.createApiResponse(resultMap, Result.ARGUMENT_ERROR);
					}
				}
			} else {
				resultMap = validateData(data, validateFields);
				if (MapUtils.isNotEmpty(resultMap)) {
					return ApiResponse.createApiResponse(resultMap, Result.ARGUMENT_ERROR);
				}
			}
			return (ApiResponse<?>) pjp.proceed();


	}

	/**
	 * @param data
	 * @param validateFields
	 * @return
	 * @Description 参数校验
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> validateData(Object data, String[][] validateFields) {
		Map<String, String> resultMap = null;
		if (data instanceof Map) {
			resultMap = ValidateUtil.validate((Map<String, String>) data, validateFields);
		} else {
			// javaBean检验
			Map<String, String> paramsMap = null;
			try {
				paramsMap = BeanUtils.describe(data);
				resultMap = ValidateUtil.validate(paramsMap, validateFields);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return resultMap;

	}

}
