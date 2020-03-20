package com.example.base.resultpojo;

public class EgoResult {
	private int status;
	private Object data;
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public EgoResult() {
	}

	public EgoResult(int status, Object data, String msg) {
		this.status = status;
		this.data = data;
		this.msg = msg;
	}
}
