package cn.com.sparknet.common.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sparknet.common.util.WebUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
* @author 作者 xuef
* @version 创建时间：2015年9月9日 下午4:11:17
* 类说明
*/
public class JsonController {
	
	private static Log log = LogFactory.getLog(JsonController.class);

	public void serialize(HttpServletRequest request, HttpServletResponse response, JSONObject result)	throws Exception {
		PrintWriter writer = response.getWriter();
		if(WebUtil.isAjaxRequest(request)){
    		response.setContentType("application/x-json; charset=UTF-8");
    	}else{
    		response.setContentType("text/html; charset=UTF-8");
    	}
		if (log.isDebugEnabled()) {
			log.debug(result.toString());
		}
		log.info("result is : " + result.toString());
		writer.write(result.toString());
		writer.flush();
		writer.close();
	}
	
	public void serialize(HttpServletRequest request, HttpServletResponse response, Object obj)	throws Exception {
		PrintWriter writer = response.getWriter();
		JSONObject result = new JSONObject();
		
		if(WebUtil.isAjaxRequest(request)){
    		response.setContentType("application/x-json; charset=UTF-8");
    	}else{
    		response.setContentType("text/html; charset=UTF-8");
    	}
		result.put("success", true);
		result.put("data", obj.toString());
		result = transToLowerObject(result);
		if (log.isDebugEnabled()) {
			log.debug(result.toString());
		}
		writer.write(result.toString());
		writer.flush();
//		writer.close();
	}
	
	public void serialize(HttpServletRequest request, HttpServletResponse response, Object obj,boolean aa)	throws Exception {
		PrintWriter writer = response.getWriter();
		JSONObject result = new JSONObject();

		
		if(WebUtil.isAjaxRequest(request)){
    		response.setContentType("application/x-json; charset=UTF-8");
    	}else{
    		response.setContentType("text/html; charset=UTF-8");
    	}
		/*result.put("success", true);*/
		
		if (log.isDebugEnabled()) {
			log.debug(obj.toString());
		}
		writer.write(obj.toString());
		writer.flush();
		writer.close();
	}
	
	public void serialize(HttpServletRequest request, HttpServletResponse response, Object obj,int total)	throws Exception {
		PrintWriter writer = response.getWriter();
		JSONObject result = new JSONObject();
		if(WebUtil.isAjaxRequest(request)){
    		response.setContentType("application/x-json; charset=UTF-8");
    	}else{
    		response.setContentType("text/html; charset=UTF-8");
    	}
		result.put("success", true);
		result.put("data", obj.toString());
		result.put("totalProperty", total);
		if (log.isDebugEnabled()) {
			log.debug(result.toString());
		}
		writer.write(result.toString());
		writer.flush();
		writer.close();
	}
	
	public void serialize(HttpServletRequest request, HttpServletResponse response, List<?> list)	throws Exception {
		PrintWriter writer = response.getWriter();
		JSONObject result = new JSONObject();
		if(WebUtil.isAjaxRequest(request)){
    		response.setContentType("application/x-json; charset=UTF-8");
    	}else{
    		response.setContentType("text/html; charset=UTF-8");
    	}
		result.put("success", true);
		result.put("data", list);
		if (log.isDebugEnabled()) {
			log.debug(result.toString());
		}
		writer.write(result.toString());
		writer.flush();
		writer.close();
	}
	
	public void serialize(HttpServletRequest request, HttpServletResponse response, List<?> list, Boolean isOnlyList)	throws Exception {
		PrintWriter writer = response.getWriter();
		String result = JSONArray.fromObject(list).toString();
		if(WebUtil.isAjaxRequest(request)){
    		response.setContentType("application/x-json; charset=UTF-8");
    	}else{
    		response.setContentType("text/html; charset=UTF-8");
    	}
		if (log.isDebugEnabled()) {
			log.debug(result.toString());
		}
		writer.write(result.toString());
		writer.flush();
		writer.close();
	}
	
	/**
	 * 下载附件时通用responseHeader
	 * @param response
	 * @param fileName
	 * @throws Exception
	 */
	public static final void setDownLoadResponseHeader(HttpServletRequest request, 
			HttpServletResponse response, String filename) throws Exception {
		if (!response.isCommitted()){
			response.reset();
		}
		response.setContentType("application/x-msdownload");
		String	name = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + name);
	}
	
	
	/**
     * json大写转小写
     * 
     * @param jSONArray1 jSONArray1
     * @return JSONObject
     */
    public static JSONObject transToLowerObject(JSONObject jSONArray1) {
        JSONObject jSONArray2 = new JSONObject();
        Iterator it = jSONArray1.keys();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object object = jSONArray1.get(key);
            if (object.getClass().toString().endsWith("String")) {
                jSONArray2.accumulate(key.toLowerCase(), object);
            } else if (object.getClass().toString().endsWith("JSONObject")) {
                jSONArray2.accumulate(key.toLowerCase(), transToLowerObject((JSONObject) object));
            } else if (object.getClass().toString().endsWith("JSONArray")) {
                jSONArray2.accumulate(key.toLowerCase(), transToArray(jSONArray1.getJSONArray(key)));
            }
        }
        return jSONArray2;
    }
    
    /**
     * jsonArray转jsonArray
     * 
     * @param jSONArray1 jSONArray1
     * @return JSONArray
     */
    public static JSONArray transToArray(JSONArray jSONArray1) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray1.size(); i++) {
            Object jArray = jSONArray1.getJSONObject(i);
            if (jArray.getClass().toString().endsWith("JSONObject")) {
                jSONArray2.add(transToLowerObject((JSONObject) jArray));
            } else if (jArray.getClass().toString().endsWith("JSONArray")) {
                jSONArray2.add(transToArray((JSONArray) jArray));
            }
        }
        return jSONArray2;
    }
}
