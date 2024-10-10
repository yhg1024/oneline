package com.com.com.board;

public class PageVO {
	private int totalCount; // �Խñ� �� ����
	private int nowPage; // ����������
	private int startPage; // ����������
	private int lastPage; // ������ ������
	private int endPage; // ��������
	private int listSize = 10; // �������� �� ����
	private int start; // ���� start
	private int end; // ���� end
	private int cntPage = 10; // ��� �������� ������
	
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
	
	// ���� ������ ������ ���
	public void calcLastPage(int total, int listSize) {
		setLastPage((int) Math.ceil((double)total / (double)listSize));
	}
	// ����, �� ������ ���
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
	// DB �������� ����� start, end�� ���
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
