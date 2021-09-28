package org.ict.domain;

import lombok.Getter;
import lombok.ToString;

// DTO는 Data Transfer Object로 데이터로 전달 객체라고 합니다.
// DTO나 VO나 엄격하게 구분되는 것은 아니도 둘 다 특정 데이터를
// 한 변수에 묶어서 보내기 위해 사용합니다.
// 차이점이 있다면 VO는 DB에서 바로 꺼낸 데이터를 매칭시키고
// DTO는 DB에 있는 정보를 토대로 가공한 데이터를 전달할 때
// 사용한다는 차이가 있습니다.
@Getter
@ToString
public class PageDTO {
	
	// 페이지 갯수 (pageNum / 10)
	private int btnNum;
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria cri;
	
	// 위의 변수 정보들을 자동으로 계산하게 하기 위한 생성자
	public PageDTO(Criteria cri, int total, int btnNum) {
		this.cri = cri;
		this.total = total;
		this.btnNum = btnNum;
		
		// 위에서 저장된 멤버변수를 이용해 나머지 정보 구하기
		
		// 끝페이지 공식
		// 현재 보고있는 페이지를 (실수)btnNum으로 나눴다가
		// 다시 곱한 결과물에 올림을 하고 btnNum을 다시 곱해
		// 자릿수를 원상복구 시킵니다.
		this.endPage = (int)(Math.ceil(cri.getPageNum() / (double)this.btnNum)) * cri.getAmount();
		
		this.startPage = this.endPage - this.btnNum + 1;
		
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		// 전으로 가는 버튼 여부
		this.prev = this.startPage == 1 ? false : true;
		
		// 다음으로 가는 버튼 여부
		this.next = this.endPage < realEnd;
	}
}
