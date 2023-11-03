package com.test.toy.board.model;


import lombok.Data;

@Data
public class BoardDTO {
	
	private String seq;
	private String subject;
	private String content;
	private String regdate;
	private int readcount;
	private String id;
	
	//BoardDTO에 name 변수는 없으므로 생성한다.
	//보편적으로 DTO가 하나의 테이블이지만 테이블에 존재하지않는 값도 넣는 경우도 많다.
	//단, 테이블과 관계없는 데이터는 피한다.
	private String name;
	private int isnew;
	private int ccnt;
}

