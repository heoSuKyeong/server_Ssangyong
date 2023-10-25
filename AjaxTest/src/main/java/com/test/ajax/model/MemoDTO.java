package com.test.ajax.model;

public class MemoDTO {
	
	//계층간 데이터를 넘겨주는
	//MemoDTO는 tblMemo의 레코드 한 줄과 구성이 똑같다.
	
	private String seq;
	private String name;
	private String pw;
	private String memo;
	private String regdate;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
