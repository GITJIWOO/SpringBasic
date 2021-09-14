/* oravle�� auto_increment�� �����Ƿ�
board_num��� �������� ����� ó���� 0�� ����˴ϴ�.
���� primary key�� �� �ڸ���
board_num.nextval �̶�� �������ָ�
������ �� ���� 1�� ������ ���ο� ���� �� ��ġ�� �־��ݴϴ�. */
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

INSERT INTO board_tbl(bno, title, content, writer) VALUES (board_num.nextval, '�׽�Ʈ', '�׽�Ʈ ��', '��');

SELECT * FROM board_tbl;

SELECT * FROM board_tbl WHERE bno < 4;

commit;