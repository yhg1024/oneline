package com.com.com.board;

public class PageVO {
	private int totalCount; // 게시글 총 갯수
	private int nowPage; // 현재페이지
	private int startPage; // 시작페이지
	private int lastPage; // 마지막 페이지
	private int endPage; // 끝페이지
	private int listSize = 10; // 페이지당 글 갯수
	private int start; // 쿼리 start
	private int end; // 쿼리 end
	private int cntPage = 10; // 몇개의 페이지를 보일지
	
	public PageVO() {
	}
	public PageVO(int totalCount, int nowPage, int listSize) {
		setNowPage(nowPage);
		setEndPage(listSize);
		setTotalCount(totalCount);
		calcLastPage(getTotalCount(), getListSize());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getListSize());
	}
	
	// 제일 마지막 페이지 계산
	public void calcLastPage(int total, int listSize) {
		setLastPage((int) Math.ceil((double)total / (double)listSize));
	}
	// 시작, 끝 페이지 계산
	public void calcStartEndPage(int nowPage, int cntPage) {
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		if (getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		setStartPage(getEndPage() - cntPage + 1);
		if (getStartPage() < 1) {
			setStartPage(1);
		}
	}
	// DB 쿼리에서 사용할 start, end값 계산
	public void calcStartEnd(int nowPage, int listSize) {
		setEnd(nowPage * listSize);
		setStart(getEnd() - listSize + 1);
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getCntPage() {
		return cntPage;
	}
	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}
}
