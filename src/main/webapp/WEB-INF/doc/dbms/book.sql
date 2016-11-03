/**********************************/
/* Table Name: 도서 */
/**********************************/
DROP table book;
CREATE TABLE BOOK(
      bno                                 NUMBER(6)                      NOT NULL       PRIMARY KEY,
      title                               VARCHAR2(200)   DEFAULT ''     NOT NULL,
      deal_code                           VARCHAR2(20)                   NOT NULL,
      product_code                        VARCHAR2(20)                   NOT NULL,
      category                            VARCHAR2(20)                   NOT NULL,
      region                              VARCHAR2(20)    DEFAULT ''     NOT NULL,
      deal_way                            VARCHAR2(20)                   NOT NULL,
      deal_state                          VARCHAR2(20)                   NOT NULL,
      purc_date                           VARCHAR2(20)    DEFAULT ''     NOT NULL,
      quantity                            NUMBER(6)       DEFAULT 0      NOT NULL,
      hprice                              NUMBER(15)      DEFAULT 0      NOT NULL,
      btitle                              VARCHAR2(200)                  NOT NULL,
      publisher                           VARCHAR2(20)                   NOT NULL,
      bwriter                             VARCHAR2(20)                   NOT NULL, -- 값 ↑
      content                             VARCHAR2(4000)                 NOT NULL,
      cnt                                 NUMBER(6)      DEFAULT 0       NOT NULL,
      userid                              VARCHAR2(20)                   NOT NULL,
      nickname                          VARCHAR2(20)     NOT NULL,
      tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
      email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
      wdate                               DATE           DEFAULT sysdate NOT NULL,
      thumb                               VARCHAR2(100)  DEFAULT ''      NULL ,
      file1                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file2                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file3                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file4                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file5                               VARCHAR2(50)   DEFAULT ''      NULL ,
      size1                               NUMBER(9)      DEFAULT 0       NULL ,
      size2                               NUMBER(9)      DEFAULT 0       NULL ,
      size3                               NUMBER(9)      DEFAULT 0       NULL ,
      size4                               NUMBER(9)      DEFAULT 0       NULL ,
      size5                               NUMBER(9)      DEFAULT 0       NULL ,
  FOREIGN KEY (userid) REFERENCES member (userid)
);

COMMENT ON TABLE BOOK is '도서';
COMMENT ON COLUMN BOOK.BNO is '도서번호';
COMMENT ON COLUMN BOOK.TITLE is '제목';
COMMENT ON COLUMN BOOK.deal_code is '거래구분';
COMMENT ON COLUMN BOOK.product_code is '신품구분';
COMMENT ON COLUMN BOOK.category is '상품구분';
COMMENT ON COLUMN BOOK.REGION is '지역';
COMMENT ON COLUMN BOOK.deal_way is '거래방법';
COMMENT ON COLUMN BOOK.deal_state is '거래상태';
COMMENT ON COLUMN BOOK.purc_date is '구입일';
COMMENT ON COLUMN BOOK.quantity is '수량';
COMMENT ON COLUMN BOOK.hprice is '희망가격';
COMMENT ON COLUMN BOOK.BTILTE is '책제목';
COMMENT ON COLUMN BOOK.publisher is '출판사';
COMMENT ON COLUMN BOOK.Bwriter is '지은이';
COMMENT ON COLUMN BOOK.CONTENT is '내용';
COMMENT ON COLUMN BOOK.cnt is '조회수';
COMMENT ON COLUMN BOOK.userid is '작성자';
COMMENT ON COLUMN BOOK.wdate is '등록일자';

SELECT * FROM BOOK;

1) 등록

INSERT INTO book(bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date, quantity, 
                 hprice, btitle, publisher, bwriter, content, cnt, userid, nickname, tel, email, wdate, thumb, file1, file2, file3, 
                 file4, file5, size1, size2, size3, size4, size5)  
VALUES((SELECT NVL(MAX(bno), 0) + 1 as bno FROM book), '어린이 책팝니다',
        '팝니다', '중고', '어린이서적','서울', '택배', '판매중', '2016-03-06', 2, 5000, '뽀로로와 함께', '뽀통령', '뽀미',
        '연락주세요 ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), (select nickname FROM member WHERE mno=2),
        (select tel FROM member WHERE mno=2), (select tel FROM member WHERE mno=2), sysdate, '', '', '', '', '', '', 0, 0, 0, 0, 0);
        
INSERT INTO book(bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date, quantity, 
                 hprice, btitle, publisher, bwriter, content, cnt, userid, nickname, tel, email, wdate, thumb, file1, file2, file3, 
                 file4, file5, size1, size2, size3, size4, size5)  
VALUES((SELECT NVL(MAX(bno), 0) + 1 as bno FROM book), '트럼펫 교본 삽니다',
        '삽니다', '중고', '음악서적','부산', '택배', '거래중', '2016-05-05', 1, 15000, '트럼펫 교본', '다라', '음악연구회',
        '연락주세요 ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), (select nickname FROM member WHERE mno=2),
        (select tel FROM member WHERE mno=2), (select tel FROM member WHERE mno=2), sysdate, '', '', '', '', '', '', 0, 0, 0, 0, 0);
        
INSERT INTO book(bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date, quantity,
                 hprice, btitle, publisher, bwriter, content, cnt, userid, nickname, tel, email, wdate, thumb, file1, file2, file3, 
                 file4, file5, size1, size2, size3, size4, size5)  
VALUES((SELECT NVL(MAX(bno), 0) + 1 as bno FROM book), 'IT 책팝니다',
        '팝니다', '중고', 'IT서적','서울', '직거래', '판매완료', '2016-09-01', 1, 3000, 'JAVA 첫걸음', '자바군', 'java',
        '연락주세요 ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), (select nickname FROM member WHERE mno=2),
        (select tel FROM member WHERE mno=2), (select tel FROM member WHERE mno=2), sysdate, '', '', '', '', '', '', 0, 0, 0, 0, 0);

        
 BNO TITLE      DEAL_CODE PRODUCT_CODE CATEGORY REGION DEAL_WAY DEAL_STATE PURC_DATE  QUANTITY HPRICE BTITLE   PUBLISHER BWRITER CONTENT  CNT USERID NICKNAME TEL           EMAIL         WDATE                 THUMB FILE1 FILE2 FILE3 FILE4 FILE5 SIZE1 SIZE2 SIZE3 SIZE4 SIZE5
 --- ---------- --------- ------------ -------- ------ -------- ---------- ---------- -------- ------ -------- --------- ------- -------- --- ------ -------- ------------- ------------- --------------------- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
   3 IT 책팝니다    팝니다       중고           IT서적     서울     직거래      판매완료       2016-09-01        1   3000 JAVA 첫걸음 자바군       java    연락주세요 ~    0 chanmi 찬미       000-1111-1111 000-1111-1111 2016-10-31 00:24:07.0 NULL  NULL  NULL  NULL  NULL  NULL      0     0     0     0     0
   1 어린이 책팝니다   팝니다       중고           어린이서적    서울     택배       판매중        2016-03-06        2   5000 뽀로로와 함께  뽀통령       뽀미      연락주세요 ~    0 chanmi 찬미       000-1111-1111 000-1111-1111 2016-10-31 00:24:05.0 NULL  NULL  NULL  NULL  NULL  NULL      0     0     0     0     0
   2 트럼펫 교본 삽니다 삽니다       중고           음악서적     부산     택배       거래중        2016-05-05        1  15000 트럼펫 교본   다라        음악연구회   연락주세요 ~    0 chanmi 찬미       000-1111-1111 000-1111-1111 2016-10-31 00:24:06.0 NULL  NULL  NULL  NULL  NULL  NULL      0     0     0     0     0

2) 전체 목록 출력

SELECT bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date,
                 hprice, cnt, userid, nickname, wdate, thumb
FROM book
ORDER BY bno DESC;

3)카테고리별 목록 출력

SELECT bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date,
                 hprice, cnt, userid, wdate, thumb
FROM book
WHERE category='어린이서적'
ORDER BY bno DESC;

4)수정

UPDATE book
SET title='IT 책팝니다', deal_code='팝니다', product_code='중고', category='IT서적',
   region='서울', deal_way='직거래', deal_state='판매완료', purc_date='2016-09-01', quantity=1, 
   hprice=3000, btitle='JAVA 첫걸음', publisher='자바군', bwriter='java',
        content='연락주세요 ~ ', thumb='', file1='', file2='', file3='', file4='', file5='', size1=0, size2=0, 
        size3=0, size4=0, size5=0
WHERE bno=3;

5)삭제

DELETE book;
WHERE bno = 4;


DROP table breply;
CREATE TABLE breply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    bno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
    wdate                 DATE         DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member (userid) on delete cascade,
    FOREIGN KEY (bno) REFERENCES book (bno) on delete cascade
);


INSERT INTO  breply(rno, rcomment, bno, userid, nickname, passwd, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM breply), '좋은 거래 하세요', 2,
(select userid from member where userid='chanmi') , '구매인', '1234', sysdate, 1, 1, 1);


SELECT * FROM breply where bno=2;

/** 삭제 */
DELETE nreply WHERE rno = 1 and passwd = '5678'