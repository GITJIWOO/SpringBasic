package org.ict.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private long r_no;
	private long bno;
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
	
}