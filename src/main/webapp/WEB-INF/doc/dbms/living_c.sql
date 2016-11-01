DROP TABLE living CASCADE CONSTRAINTS PURGE;

CREATE TABLE living(
    lno                               NUMBER(6)    NOT NULL    PRIMARY KEY,
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

1등록 
INSERT INTO mobile(mno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, telecome,userid)  
VALUES((SELECT NVL(MAX(mno), 0) + 1 as mno FROM mobile), 'category', 'nickname', 'passwd', 'deal_way',
 'deal_code', 'pruduct_code',  240000, 'region', '010-2312-5123', 'acd@naver.com', 1, 'title', 'content',
  '10년4월', sysdate, 0,'SKT', (select userid from member_test where userid='master'));

INSERT INTO mobile(mno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, telecome,userid)  
VALUES((SELECT NVL(MAX(mno), 0) + 1 as mno FROM mobile), '갤럭시', '런런런', '1234', '교환',
 '팝시다', '중고품',  540000, '부산', '010-5125-5123', 'acd@naver.com', 1, '갤럭시팝니다', '안터집니다',
  '10년4월', 'KT', (select userid from member_test where userid='master'));
  
INSERT INTO mobile(mno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, telecome,userid)  
VALUES((SELECT NVL(MAX(mno), 0) + 1 as mno FROM mobile), '아이폰', '런런런', '1234', '교환',
 '팝시다', '중고품',  540000, '부산', '010-5125-5123', 'acd@naver.com', 1, '아이폰팝니다', '잭있습니다',
  '10년4월', 'LG', (select userid from member_test where userid='master'));  
2.조회 
SELECT mno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, telecome, userid
FROM mobile 
WHERE mno = 1;

3.수정 
UPDATE mobile 
SET category = 'category2' , nickname = 'nickname2', passwd = 'passwd2', deal_way = 'deal_way2', deal_code = 'deal_code2', 
 product_code = 'product_code2', hprice = 1000, region = '인천', tel = '2014284092', email = '후후후@naver.com', quantity = 3, 
 title = 'title2', content = 'content2', purc_date = '2008년' , telecome = 'KT'
WHERE mno=1;
4.삭제 

DELETE FROM mobile 
WHERE mno = 1;

5.목록
SELECT mno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, telecome ,userid
FROM mobile 
ORDER BY mno ASC;

DROP TABLE mobile_reply;

CREATE TABLE mobile_reply(
    rmno                               NUMBER(10)     NOT NULL    PRIMARY KEY,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    content                           VARCHAR2(1000)     NOT NULL,
    mno                               NUMBER(10)     NOT NULL,
    userid                            VARCHAR2(20)     NULL ,
  FOREIGN KEY (mno) REFERENCES Mobile (mno),
  FOREIGN KEY (userid) REFERENCES member_test (userid)
);

COMMENT ON TABLE mobile reply is '모바일 댓글';
COMMENT ON COLUMN mobile reply.rno is '댓글번호';
COMMENT ON COLUMN mobile reply.nickname is '닉네임';
COMMENT ON COLUMN mobile reply.passwd is '비밀번호';
COMMENT ON COLUMN mobile reply.content is '내용';
COMMENT ON COLUMN mobile reply.mno is '글번호';
COMMENT ON COLUMN mobile reply.userid is '아이디';

1등록 
INSERT INTO mobile_reply(rmno, nickname, passwd, content, mno,userid)  
VALUES((SELECT NVL(MAX(rmno), 0) + 1 as rmno FROM mobile_reply), 'nickname','passwd', 'content', 
(select mno from mobile where mno= 1), (select userid from member_test where userid='master'));

2.조회 
SELECT rmno, nickname, passwd, content, wdate, cnt, userid
FROM  mobile_reply
WHERE rmno = 1;

3.수정 
UPDATE mobile_reply 
SET content = 'content2'
WHERE rmno = 1;
4.삭제 

DELETE FROM mobile_reply
WHERE rmno = 1;

5.목록
SELECT rmno, nickname, content, wdate, cnt, userid
FROM mobile_reply 
ORDER BY rmno ASC;


