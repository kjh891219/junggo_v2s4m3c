/**********************************/
/* Table Name: 컴퓨터/노트북 */
/**********************************/
drop table computer

CREATE TABLE computer(
    ctno                              NUMBER(6)    NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NULL ,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    category                          VARCHAR2(20)     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    purc_date                         VARCHAR2(20)     DEFAULT ''    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    userid                            VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(100)     NOT NULL,
    tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    deal_status                       VARCHAR2(20)     DEFAULT ''     NOT NULL,
    thumb                   VARCHAR2(100)        NULL ,
    file1                   VARCHAR2(50)         NULL ,
    size1                   NUMBER(9)        DEFAULT 0       NULL ,
    file2                   VARCHAR2(50)         NULL ,
    size2                   NUMBER(9)        DEFAULT 0       NULL ,
    file3                   VARCHAR2(50)         NULL ,
    size3                   NUMBER(9)        DEFAULT 0       NULL ,
    file4                   VARCHAR2(50)         NULL ,
    size4                   NUMBER(9)        DEFAULT 0       NULL ,
    file5                   VARCHAR2(50)         NULL ,
    size5                   NUMBER(9)        DEFAULT 0       NULL ,
  PRIMARY KEY (ctno),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

drop table computer
 
COMMENT ON TABLE computer is '컴퓨터';
COMMENT ON COLUMN computer.ctno is '글번호';
COMMENT ON COLUMN computer.title is '제목';
COMMENT ON COLUMN computer.deal_code is '거래구분';
COMMENT ON COLUMN computer.product_code is '신품구분';
COMMENT ON COLUMN computer.category is '상품구분';
COMMENT ON COLUMN computer.region is '지역';
COMMENT ON COLUMN computer.deal_way is '거래방법';
COMMENT ON COLUMN computer.purc_date is '구입일';
COMMENT ON COLUMN computer.quantity is '수량';
COMMENT ON COLUMN computer.hprice is '희망가격';
COMMENT ON COLUMN computer.content is '내용';
COMMENT ON COLUMN computer.cnt is '조회수';
COMMENT ON COLUMN computer.nickname  is '닉네임';
COMMENT ON COLUMN computer.userid is '아이디';
COMMENT ON COLUMN computer.passwd is '비밀번호';
COMMENT ON COLUMN computer.tel is '전화번호';
COMMENT ON COLUMN computer.email is '메일주소';
COMMENT ON COLUMN computer.wdate is '등록일자';
COMMENT ON COLUMN computer.deal_status is '거래상태';

/** Insert sample data*/
truncate table computer 
INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '노트북 판매합니다','팝니다', '새상품'
, '노트북', '경기도', '택배', '2015-01-01', 1
, 1000000, '1년 미만 사용한 상태 최상 노트북입니다', 0, '정직한거래자', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '데스크탑 구매원해요','삽니다', '중고품'
, '데스크탑', '경기도', '직거래', '2015-01-01', 1
, 1000000, '상태 최상 데스크탑 원해요', 0, '정직한거래자', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '데스크탑 판매합니다2','팝니다', '중고품'
, '데스크탑', '경상도', '직거래', '2011-01-01', 1
, 1000000, '5년 미만 사용한 중고 데스크탑입니다', 0, '정직한거래자', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

/** update computer table */
select * from computer
UPDATE computer set title = '데스크탑 거래중', deal_code = '택배', product_code ='중고'
        , category = '데스크탑', region ='경기도', deal_way='직거래', purc_date='2016-03-03', quantity=1
        , hprice=800000, content='5년 미만 중고 컴퓨터', cnt = 0, nickname='왕눈이', userid ='chanmi'
        , passwd='1234', tel='02-0930-0909', email='test1@google.com', deal_status='Y'
        , thumb = '', file1='test.txt', size1=0, file2 ='test.txt', size2 = 0, file3 ='test.txt', size3 = 0
        , file4 ='test.txt', size4 = 0, file5 ='test.txt', size5 = 0
WHERE ctno = 10 and passwd = '4546'

/** 삭제 */
DELETE computer WHERE ctno = 9 and passwd = '4546'
delete computer where ctno=2; 
 /**********************************/
/* Table Name: 컴퓨터 게시글 댓글 */
/**********************************/
 drop table nreply
CREATE TABLE nreply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
    passwd               VARCHAR2(100)     NOT NULL,
    wdate                 DATE     DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member (userid),
    FOREIGN KEY (ctno) REFERENCES computer (ctno)
);

COMMENT ON TABLE nreply is '컴퓨터댓글';
COMMENT ON COLUMN nreply.rno is '댓글번호';
COMMENT ON COLUMN nreply.rcomment is '내용';
COMMENT ON COLUMN nreply.ctno is '글번호';
COMMENT ON COLUMN nreply.userid is '아이디';
COMMENT ON COLUMN nreply.nickname is '등록자 닉네임';
COMMENT ON COLUMN nreply.passwd is '비밀번호';
COMMENT ON COLUMN nreply.wdate is '등록일자';

INSERT INTO  nreply (rno, rcomment, ctno, userid, nickname, passwd, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM nreply), '좋은 거래 하세요', 1, 'master', '구매원하는사람', '1234', sysdate, 1, 1, 1);

/** 삭제 */
DELETE nreply WHERE rno = 1 and passwd = '5678'




