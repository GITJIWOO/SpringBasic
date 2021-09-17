/* oravle은 auto_increment가 없으므로
board_num라는 시퀀스를 만들면 처음에 0이 저장됩니다.
이후 primary key가 들어갈 자리에
board_num.nextval 이라고 기입해주면
실행할 때 마다 1씩 증가된 새로운 값을 그 위치에 넣어줍니다. */
CREATE SEQUENCE board_num;

CREATE TABLE board_tbl (
    bno NUMBER(10, 0),
    title VARCHAR2(200) NOT NULL,
    content VARCHAR2(2000) NOT NULL,
    writer VARCHAR2(50) NOT NULL,
    regdate DATE DEFAULT sysdate,
    updatedate DATE DEFAULT sysdate
);

ALTER TABLE board_tbl ADD CONSTRAINT pk_board PRIMARY KEY(bno);

INSERT INTO board_tbl(bno, title, content, writer) VALUES (board_num.nextval, '테스트', '테스트 글', '테');

SELECT * FROM board_tbl;

SELECT * FROM board_tbl WHERE bno < 4;

SELECT * FROM board_tbl WHERE title LIKE '%a%' ORDER BY bno DESC;

commit;