/**********************************/
/* Table Name: 허위상품신고 */
/**********************************/
DROP TABLE CHEAT CASCADE CONSTRAINTS PURGE;
CREATE TABLE CHEAT(
    ctno                              NUMBER(6)    NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NULL ,
    gubun                             VARCHAR2(50)     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    occurday                          VARCHAR2(20)     DEFAULT ''    NOT NULL,
    buyprice                          NUMBER(15)     DEFAULT 0     NOT NULL,
    cheatid                           VARCHAR2(20)     DEFAULT ''    NULL ,
    cheattel                          VARCHAR2(14)     DEFAULT ''    NULL ,
    cheatemail                        VARCHAR2(100)    DEFAULT ''    NULL ,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NULL ,
    tel                               VARCHAR2(14)     DEFAULT ''    NULL ,
    userid                            VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     DEFAULT ''    NOT NULL,
    passwd                            VARCHAR2(10)     NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    file1             VARCHAR(100) NULL,
    file2             VARCHAR(50) NULL,
    size2            NUMBER(9) DEFAULT 0 NULL,   
    file3             VARCHAR(100) NULL,
		file4             VARCHAR(50) NULL,
		size4            NUMBER(9) DEFAULT 0 NULL,   
		file5             VARCHAR(100) NULL,
		file6             VARCHAR(50) NULL,
		size6            NUMBER(9) DEFAULT 0 NULL,   
		file7             VARCHAR(100) NULL,
		file8             VARCHAR(50) NULL,
		size8            NUMBER(9) DEFAULT 0 NULL,   
		file9             VARCHAR(100) NULL,
		file10           VARCHAR(50) NULL,
		size10          NUMBER(9) DEFAULT 0 NULL,   
	  PRIMARY KEY (ctno),
	  FOREIGN KEY (userid) REFERENCES member (userid)
	);


 /**gubun:신고구분 (물품미발송 / 상태불량 / 이미테이션 / 택배착불 / 더치트,사이버안전국 등록자 등)
  * 
  */
COMMENT ON TABLE CHEAT is '허위상품신고';
COMMENT ON COLUMN CHEAT.ctno is '글번호';
COMMENT ON COLUMN CHEAT.GUBUN is '신고구분';
COMMENT ON COLUMN CHEAT.TITLE is '제목';
COMMENT ON COLUMN CHEAT.REGION is '지역';
COMMENT ON COLUMN CHEAT.OCCURDAY is '발생일자';
COMMENT ON COLUMN CHEAT.BUYPRICE is '사기금액';
COMMENT ON COLUMN CHEAT.CHEATID is '허위상품등록자 ID';
COMMENT ON COLUMN CHEAT.CHEATTEL is '허위상품등록자연락처';
COMMENT ON COLUMN CHEAT.CHEATEMAIL is '허위상품등록자이메일';
COMMENT ON COLUMN CHEAT.cnt is '조회수';
COMMENT ON COLUMN CHEAT.CONTENT is '내용';
COMMENT ON COLUMN CHEAT.EMAIL is '메일주소';
COMMENT ON COLUMN CHEAT.TEL is '전화번호';
COMMENT ON COLUMN CHEAT.userid is '아이디';
COMMENT ON COLUMN CHEAT.nickname is '별명';
COMMENT ON COLUMN CHEAT.passwd is '비밀번호';
COMMENT ON COLUMN CHEAT.wdate is '등록일자';


/** sample 데이터
 * INSERT SQL
 */ 
INSERT INTO cheat (ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd )
VALUES ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM cheat), '입금했는데 연락두절이에요', '물품미발송','충청남도','2016-09-27',  50000, 'badid', '010-1234-5678', 'BADID@daum.net', 0, '어제 입금완료했어요. 연락이 두절되었어요 ㅠㅠ', 'user2@naver.com','010-2222-3333','chanmi','왕눈이','1234'); 

INSERT INTO cheat (ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd )
VALUES ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM cheat), '입금했는데 연락두절이에요2', '물품미발송','경상남도','2016-09-27',  50000, 'badid', '010-1234-5678', 'BADID@daum.net', 0, '어제 입금완료했어요. 연락이 두절되었어요 ㅠㅠ', 'user2@naver.com','010-2222-3333','chanmi','왕눈이', '1234'); 

INSERT INTO cheat (ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd)
VALUES ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM cheat), '물품 상태가 엉망이에요', '상태불량','경기도','2016-09-27',  50000, 'badid', '010-1234-5678', 'BADID@daum.net', 0, '어제 입금완료했어요. 연락이 두절되었어요 ㅠㅠ', 'user2@naver.com','010-2222-3333','chanmi','왕눈이','1234'); 

/** 검색 SQL */
SELECT ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd, wdate
   FROM cheat
   WHERE title LIKE '%연락%' AND CONTENT LIKE '%입금%'  AND nickname LIKE '%' 
   ORDER BY ctno ASC

/**
 * 수정 SQL
 */   
UPDATE cheat
      SET title = '아이디 user1441와 거래하지 마세요', gubun = 'E', region = '경기도', occurday = '2016-10-01'
          , buyprice = 40000, cheatid = 'user1441', cheattel = '010-2345-5678'
          , cheatemail = 'BADID1@daum.net', cnt = 0, content = '노트북이 이미테이션이에요'
          , email = 'user2@naver.com', tel = '010-2222-3333', userid = 'user2', nickname = '왕눈이', passwd = '1234'
          , thumb = '', file1='test.txt', size1=0, file2 ='test.txt', size2 = 0, file3 ='test.txt', size3 = 0
          , file4 ='test.txt', size4 = 0, file5 ='test.txt', size5 = 0
WHERE ctno = 10
AND passwd = '1234'

/**
 삭제 SQL
*/
DELETE FROM cheat 
WHERE ctno = 10 

/** 조회수 증가 */
UPDATE cheat
     SET cnt = cnt + 1
WHERE ctno = 10   

/**********************************/
/* Table Name: 허위상품신고 게시글 댓글 */
/**********************************/
drop table cheat_reply

CREATE TABLE cheat_reply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
   -- passwd               VARCHAR2(10)     NOT NULL,
    wdate                 DATE     DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member (userid) on delete cascade,
    FOREIGN KEY (ctno) REFERENCES cheat (ctno) on delete cascade
);

COMMENT ON TABLE reply is '허위상품댓글';
COMMENT ON COLUMN reply.rno is '댓글번호';
COMMENT ON COLUMN reply.rcomment is '내용';
COMMENT ON COLUMN reply.ctno is '글번호';
COMMENT ON COLUMN reply.userid is '아이디';
COMMENT ON COLUMN reply.nickname is '등록자 닉네임';
--COMMENT ON COLUMN reply.passwd is '비밀번호';
COMMENT ON COLUMN reply.wdate is '등록일자';

INSERT INTO  cheat_reply (rno, rcomment, ctno, userid, nickname, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM cheat_reply), '좋은 거래 하세요', 1, 'master', '구매원하는사람', sysdate, 1, 1, 1);

INSERT INTO  cheat_reply (rno, rcomment, ctno, userid, nickname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM cheat_reply), '좋은 거래 하세요2', 1, 'master', '구매원하는사람', '1234', sysdate);

INSERT INTO  cheat_reply (rno, rcomment, ctno, userid, nickname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM cheat_reply), '좋은 거래 하세요3', 2, 'master', '구매원하는사람', '1234', sysdate);

/** 삭제 */
DELETE cheat_reply WHERE rno = 1 and passwd = '5678'
select * from cheat
 SELECT ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd, wdate
    FROM cheat
    

/** 페이징 */    
    SELECT ctno, title, gubun, region, occurday, buyprice, cheatid,
  cheattel, cheatemail, cnt, content, email, tel, userid, nickname,
  passwd, wdate, file1, file2, size2, r
  FROM(
  SELECT ctno, title, gubun, region, occurday, buyprice, cheatid,
  cheattel, cheatemail, cnt, content, email, tel, userid, nickname,
  passwd, wdate, file1, file2, size2, rownum as r
  FROM(
SELECT ctno, title, gubun, region, occurday, buyprice, cheatid,
  cheattel, cheatemail, cnt, content, email, tel, userid, nickname,
  passwd, wdate, file1, file2, size2
  FROM cheat

  ORDER BY ctno ASC
  
  )
  )
  
 
  select * from cheat_reply
  delete from  cheat_reply
  
   SELECT ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd, wdate, file1, file2, size2
    FROM cheat
    
     SELECT nvl(max(grpno),0)
    FROM cheat_reply
    WHERE ctno=16