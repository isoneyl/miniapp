package com.mini.app.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Result
 * @Description: 接口统一返回状态和信息描述
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Result {



	SUCCESS(0, "成功"),
	ARGUMENT_ERROR(-500, "参数验证不通过"),
	TOKEN_ERROR(-501, "token验证不通过"),
	SAVE_ERROR(-502, "保存失败"),
	UPLOAD_IMG_ERROR(-503, "上传图片失败"),
	IMG_PATH_ERROR(-503, "图片不存在"),
	VX_ERRO(1000, "请求微信接口失败,请稍后重试"),
	;

    private int code;
	private String msg;

	public static Result fromCode(int code) {
		Result[] results = Result.values();
		for (Result result : results) {
			if (result.getCode() == code) {
				return result;
			}
		}
		return null;
	}
	public static Result formMsg(int code, String msg){
		Result[] results = Result.values();
		for (Result result : results) {
			if (result.getCode() == code) {
				result.msg = msg;
				return result;
			}
		}
		return null;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
