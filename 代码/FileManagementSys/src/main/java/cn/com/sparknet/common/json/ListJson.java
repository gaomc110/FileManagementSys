package cn.com.sparknet.common.json;

import java.io.Serializable;
import java.util.List;
import cn.com.sparknet.common.bean.JsonBean;

/**
 * 列表查询时的Json
 * @author chenxy
 *
 */
public class ListJson extends JsonBean implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private List rows;
    
	private int total;
	private List data;
	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private int count;
	private String msg;
	private int code;
	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
