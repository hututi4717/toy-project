package com.kh.toy.common.http;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestParams {

	private Map<String,String> params = new HashMap<String,String>();
	
	private RequestParams(RequestParamsBuilder builder) {
		this.params = builder.params;
	}
	
	public static RequestParamsBuilder builder() {
		return new RequestParamsBuilder();
	}
	
	//팩토리 클래스란 ? 인스턴스를 생성해 주는 클래스. 위에 기본생성자를 private로 돌려놨기에, 팩토리 클래스를 통해서 인스턴스화 할 수 있다.
	public static class RequestParamsBuilder {
		
		private Map<String,String> params = new LinkedHashMap<String, String>();
		
		public RequestParamsBuilder param(String name, String value) {
			params.put(name, value);
			return this;
		}
		
		public RequestParams build() {
			return new RequestParams(this);
		}
	}

	public Map<String, String> getParams() {
		return params;
	}
	
	
}
