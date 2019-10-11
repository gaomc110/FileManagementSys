package cn.com.sparknet.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页对象
 * @author chenxy
 *
 */
public class Page {

	private int count=0;
	
	private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}

	public Page(int count, List<Map<String, Object>> list) {
		super();
		this.count = count;
		this.list = list;
	}

	public Page() {
		super();
	}
	 
}
