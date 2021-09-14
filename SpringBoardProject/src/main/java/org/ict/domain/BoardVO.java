package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;
	private String title;
	private String content;
	private String writer;
	// 시간은 Date로 작성합니다. java.sql 내부 자료입니다.
	private Date regdate;
	private Date updatedate;
}
