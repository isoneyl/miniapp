package com.mini.app.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mini.app.common.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: ApiResponse
 * @Description: 接口统一返回实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;// 描述
	private int code;// 状态码
	private T data;

	/**
	 * @Description 创建返回对象
	 * @param data
	 * @param result
	 */
	public static <T> ApiResponse<T> createApiResponse(T data, Result result) {
		return new ApiResponse<>(result.getMsg(), result.getCode(), data);
	}
}
