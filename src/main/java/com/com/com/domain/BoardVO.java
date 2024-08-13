package com.com.com.domain;

import java.util.Date;

public class BoardVO {
	private int seq;
	private String memName;
	private String memId;
	private String title;
	private String boardContent;
	private Date regDate;
	private Date upDate;
	private String viewCnt;
	private String useYN;
	
	
	
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", memName=" + memName + ", memId=" + memId + ", title=" + title
				+ ", boardContent=" + boardContent + ", regDate=" + regDate + ", upDate=" + upDate + ", viewCnt="
				+ viewCnt + ", useYN=" + useYN + "]";
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpDate() {
		return upDate;
	}
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	public String getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(String viewCnt) {
		this.viewCnt = viewCnt;
	}
	public String getUseYN() {
		return useYN;
	}
	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}
	
	
}
