package com.wj.android.todo.http;

import java.io.Serializable;

/**
* 响应类型
* @author wangwenjie
*泛型T是实际的响应类型
*响应类型T为Object
*/
public class ResponseItem<T> implements Serializable {
	/**
	 * 错误的内部编号,success表示成功
	 */
	private int errorCode;
	/**
	 * 错误描述
	 */
	private String errorMsg;
	/**
	 * 返回值
	 */
	private T data;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return errorCode == 0;
	}

}