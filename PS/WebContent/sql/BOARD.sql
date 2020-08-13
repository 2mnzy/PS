drop table BOARD;
CREATE TABLE BOARD(
	BOARD_NUM NUMBER, 
	BOARD_NAME VARCHAR2(30), 
	BOARD_PASS VARCHAR2(30), 
	BOARD_SUBJECT VARCHAR2(300), 
	BOARD_CONTENT VARCHAR2(4000), 
	BOARD_FILE VARCHAR2(50), 
	BOARD_RE_REF NUMBER,
	BOARD_RE_LEV NUMBER, 
	BOARD_RE_SEQ NUMBER,
	BOARD_READCOUNT NUMBER, 
	BOARD_DATE DATE, 
	PRIMARY KEY(BOARD_NUM)
);