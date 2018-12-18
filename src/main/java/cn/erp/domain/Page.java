package cn.erp.domain;

import java.util.ArrayList;
import java.util.List;
//分页代码
public class Page<T> {
	
	private Integer pageNow;//当前页
	private Integer firstPage = 1;
	private Integer lastPage;
	private Integer total = 10 ;//总页数,默认为10
	private Integer size;//每页显示的数据量
	
	private List<T> list = new ArrayList<T>();//数据 

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}

	public Integer getLastPage() {
		return lastPage;
	}

	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
