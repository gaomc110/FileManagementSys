package cn.com.sparknet.common.bean;

import java.util.*;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * JsonBean抽象类
 * @author chenxy
 *
 */
public class JsonBean{

	private String dateFormat;

	public JsonBean() {
		dateFormat = "yyyy-MM-dd";
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String toJsonString() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		return toJsonString(jsonConfig);
	}

	public String toJsonString(JsonConfig jsonConfig) {
		return JSONObject.fromObject(this, jsonConfig).toString();
	}

}
