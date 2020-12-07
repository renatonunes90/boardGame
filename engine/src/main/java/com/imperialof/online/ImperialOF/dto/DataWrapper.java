package com.imperialof.online.ImperialOF.dto;

import java.io.Serializable;

public class DataWrapper<T> implements Serializable {

	private static final long serialVersionUID = -7337163970689494711L;
	
	private T data;
	
	public DataWrapper() {}
	
	public DataWrapper(T object) {
		this.setData(object);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
