DROP TABLE GAMEDEVICE;
CREATE TABLE GAMEDEVICE(
    gdno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    category                          VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    region                            VARCHAR2(20)     NOT NULL,
    tel                               VARCHAR2(14)     NOT NULL,
    email                             VARCHAR2(100)    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    title                             VARCHAR2(200)    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    purc_date                         VARCHAR2(20)     NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    file1                             VARCHAR2(100)        NULL ,
    file2                             VARCHAR2(50)         NULL ,
    size2                             NUMBER(9)        DEFAULT 0       NULL ,
    file3                             VARCHAR2(100)        NULL ,
    file4                             VARCHAR2(50)         NULL ,
    size4                             NUMBER(9)        DEFAULT 0       NULL ,
    file5                             VARCHAR2(100)        NULL ,
    file6                             VARCHAR2(50)         NULL ,
    size6                             NUMBER(9)        DEFAULT 0       NULL ,
    file7                             VARCHAR2(100)        NULL ,
    file8                             VARCHAR2(50)         NULL ,
    size8                             NUMBER(9)        DEFAULT 0       NULL ,
    file9                             VARCHAR2(100)        NULL ,
    file10                            VARCHAR2(50)         NULL ,
    size10                            NUMBER(9)        DEFAULT 0       NULL ,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
    userid                            VARCHAR2(20)     NULL, 
    FOREIGN KEY (userid) REFERENCES member_test (userid)   
);


DROP TABLE GAMEDEVICE;
COMMENT ON TABLE GAMEDEVICE is '게임 기기';
COMMENT ON COLUMN GAMEDEVICE.gno is '글 일련 번호';
COMMENT ON COLUMN GAMEDEVICE.category is '카테고리';
COMMENT ON COLUMN GAMEDEVICE.nickname is '닉네임';
COMMENT ON COLUMN GAMEDEVICE.passwd is '패스워드';
COMMENT ON COLUMN GAMEDEVICE.deal_way is '거래방식';
COMMENT ON COLUMN GAMEDEVICE.deal_code is '거래구분';
COMMENT ON COLUMN GAMEDEVICE.product_code is '상품구분';
COMMENT ON COLUMN GAMEDEVICE.hprice is '희망가격';
COMMENT ON COLUMN GAMEDEVICE.region is '지역';
COMMENT ON COLUMN GAMEDEVICE.tel is '전화번호';
COMMENT ON COLUMN GAMEDEVICE.email is '이메일';
COMMENT ON COLUMN GAMEDEVICE.quantity is '수량';
COMMENT ON COLUMN GAMEDEVICE.title is '제목';
COMMENT ON COLUMN GAMEDEVICE.content is '상세설명';
COMMENT ON COLUMN GAMEDEVICE.purc_date is '구입시기';
COMMENT ON COLUMN GAMEDEVICE.wdate is '등록일';
COMMENT ON COLUMN GAMEDEVICE.cnt is '조회수, 기본값 사용';
COMMENT ON COLUMN GAMEDEVICE.userid is '아이디';

SELECT gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, lev, genre, userid
FROM game 
ORDER BY gno ASC;

1등록 
INSERT INTO gamedevice(gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, userid)  
VALUES((SELECT NVL(MAX(gdno), 0) + 1 as gdno FROM gamedevice), 'category', 'nickname', 'passwd', 'deal_way',
 'deal_code', 'pruduct_code',  240000, 'region', '010-2312-5123', 'acd@naver.com', 1, 'title', 'content',
  '10년4월', sysdate, 0, (select userid from member_test where userid='master'));
INSERT INTO gamedevice(gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, userid)  
VALUES((SELECT NVL(MAX(gdno), 0) + 1 as gdno FROM gamedevice), 'ps4', '홍길동', '1234', '직거래',
 '팝니다', '중고품',  240000, '서울', '010-2312-5123', 'acd@naver.com', 1, 'ps4팝니다', '싸게팝니다',
  '10년4월', (select userid from member_test where userid='master'));
INSERT INTO gamedevice(gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, userid)  
VALUES((SELECT NVL(MAX(gdno), 0) + 1 as gdno FROM gamedevice), 'xbox', '하루', '1234', '직거래',
 '팝니다', '중고품',  240000, '서울', '010-2312-5123', 'acd@naver.com', 1, 'xbox', '싸게팝니다',
  '10년4월', (select userid from member_test where userid='master'));  
  
2.조회 
SELECT gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, userid
FROM gamedevice 
WHERE gdno = 1;

3.수정 
UPDATE gamedevice 
SET category = 'category2' , nickname = 'nickname2', passwd = 'passwd2', deal_way = 'deal_way2', deal_code = 'deal_code2', 
 product_code = 'product_code2', hprice = 1000, region = '인천', tel = '2014284092', email = '후후후@naver.com', quantity = 3, 
 title = 'title2', content = 'content2', purc_date = '2008년'
WHERE gdno=1;
4.삭제 

DELETE FROM gamedevice 
WHERE gdno = 1;

5.목록
SELECT gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, userid
FROM gamedevice 
ORDER BY gdno ASC;

drop table gdevice_reply;

CREATE TABLE gdevice_reply(
    gdrno                             NUMBER(10)     NOT NULL    PRIMARY KEY,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    content                           VARCHAR2(1000)     NOT NULL,
    gdno                               NUMBER(6)    NULL ,
    userid                            VARCHAR2(20)     NULL ,
  FOREIGN KEY (gdno) REFERENCES GAMEDEVICE (gdno),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

COMMENT ON TABLE game device reply is '게임 기기 댓글';
COMMENT ON COLUMN game device reply.rno is '댓글번호';
COMMENT ON COLUMN game device reply.nickname is '닉네임';
COMMENT ON COLUMN game device reply.passwd is '비밀번호';
COMMENT ON COLUMN game device reply.content is '내용';
COMMENT ON COLUMN game device reply.gno is '글 번호';
COMMENT ON COLUMN game device reply.userid is '아이디';

1등록 
INSERT INTO gdevice_reply(gdrno, nickname, passwd, content, gdno,userid)  
VALUES((SELECT NVL(MAX(gdrno), 0) + 1 as gdrno FROM gdevice_reply), 'nickname','passwd', 'content', 
(select gdno from GAMEDEVICE where gdno= 1), (select userid from member_test where userid='master'));

2.조회 
SELECT gdrno, nickname, passwd, content, wdate, cnt, userid
FROM  gdevice_reply
WHERE gdrno = 1;

3.수정 
UPDATE gdevice_reply 
SET content = 'content2'
WHERE gdrno = 1;
4.삭제 

DELETE FROM gdevice_reply
WHERE gdrno = 1;

5.목록
SELECT gdrno, nickname, content, wdate, cnt, userid
FROM gdevice_reply 
ORDER BY gdrno ASC;








