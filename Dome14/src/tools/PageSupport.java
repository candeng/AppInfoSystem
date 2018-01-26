package tools;

public class PageSupport {
	//当前页码-来自于用户输入
	private int currentPageNo = 1;
	//页面容量
	private int pageSize = 0;
	//总条数
	private int all=0;
	//总页数
	private int pageCountNo = 1;
	//从第几条开始显示
	private int pageShi=0;

	public int getAll() {
		return all;
	}
	
	public void setAll(int all) {
		this.all = all;
	}
	public int getPageShi() {
		return pageShi;
	}

	public void setPageShi(int pageShi) {
		this.pageShi = pageShi;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo > 0){
			this.currentPageNo = currentPageNo;
		}
	}

	public int getPageCountNo() {
		return pageCountNo;
	}
	public void setPageCountNo(int pageCountNo) {
		if(pageCountNo > 0){
			this.pageCountNo = pageCountNo%this.pageSize==0?(pageCountNo/this.pageSize):(pageCountNo/this.pageSize+1);
		}else{
			pageCountNo=0;
		}
	}
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
	}
}