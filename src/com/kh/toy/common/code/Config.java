package com.kh.toy.common.code;

public enum Config {
	//DOMAIN("https://pclass.ga")
	DOMAIN("http://localhost:9090"),
	COMPANY_EMAIL("mailtest4717@gmail.com"),
	SMTP_AUTHENTICATION_ID("mailtest4717@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("Z#85f%&e]3w]/7<"),
	UPLOAD_PATH("C:\\CODE\\before\\upload\\");
	
	public final String DESC;
	
	Config(String desc){
		this.DESC=desc;
	}
}
