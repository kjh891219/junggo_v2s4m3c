/**********************************/
/* Table Name: 음향/기기/악기 */
/**********************************/
drop table music
delete from music
select * from music
CREATE TABLE music(
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
    passwd                            VARCHAR2(20)     NOT NULL,
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
  FOREIGN KEY (userid) REFERENCES member_1 (userid)
);

alter table computer
MODIFY   file1  VARCHAR2(100)  DEFAULT ''   
alter table computer
MODIFY   file2  VARCHAR2(100)  DEFAULT ''  

COMMENT ON TABLE music is '음향/기기';
COMMENT ON COLUMN music.ctno is '글번호';
COMMENT ON COLUMN music.title is '제목';
COMMENT ON COLUMN music.deal_code is '거래구분';
COMMENT ON COLUMN music.product_code is '신품구분';
COMMENT ON COLUMN music.category is '상품구분';
COMMENT ON COLUMN music.region is '지역';
COMMENT ON COLUMN music.deal_way is '거래방법';
COMMENT ON COLUMN music.purc_date is '구입일';
COMMENT ON COLUMN music.quantity is '수량';
COMMENT ON COLUMN music.hprice is '희망가격';
COMMENT ON COLUMN music.content is '내용';
COMMENT ON COLUMN music.cnt is '조회수';
COMMENT ON COLUMN music.nickname  is '닉네임';
COMMENT ON COLUMN music.userid is '아이디';
COMMENT ON COLUMN music.passwd is '비밀번호';
COMMENT ON COLUMN music.tel is '전화번호';
COMMENT ON COLUMN music.email is '메일주소';
COMMENT ON COLUMN music.wdate is '등록일자';
COMMENT ON COLUMN music.deal_status is '거래상태';

/** Insert sample data*/
truncate table music 
INSERT INTO music (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status)
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM music), '피아노 판매합니다','팝니다', '새상품'
, '피아노', '경기도', '택배', '2015-01-01', 1
, 1000000, '1년 미만 사용한 상태 최상 피아노입니다', 0, '정직한거래자', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N');

INSERT INTO music (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status)
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM music), 'MIKA 음반 구매원해요','삽니다', '중고품'
, 'MIKA 음반', '경기도', '직거래', '2015-01-01', 1
, 1000000, '상태 최상 MIKA 음반 원해요', 0, '정직한거래자', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N');

INSERT INTO music (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status)
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM music), '피아노 판매합니다2','팝니다', '중고품'
, 'MIKA 음반', '경상도', '직거래', '2011-01-01', 1
, 1000000, '5년 미만 사용한 중고 피아노', 0, '정직한거래자', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N');

/** update music table */
select * from music
UPDATE music set title = 'MIKA 음반 거래중', deal_code = '택배', product_code ='새상품'
        , category = 'MIKA 음반', region ='경기도', deal_way='직거래', purc_date='2016-03-03', quantity=1
        , hprice=800000, content='미사용 CD', cnt = 0, nickname='왕눈이', userid ='chanmi'
        , passwd='1234', tel='02-0930-0909', email='test1@google.com', deal_status='Y'
WHERE ctno = 3 and passwd = '4546'

/** 삭제 */
DELETE music WHERE ctno = 9 and passwd = '4546'
 

/**********************************/
/* Table Name: 뮤직/음향기기 게시글 댓글 */
/**********************************/
drop table mreply
CREATE TABLE mreply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
    passwd               VARCHAR2(10)     NOT NULL,
    wdate                 DATE     DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member_1 (userid),
    FOREIGN KEY (ctno) REFERENCES music (ctno)
);

COMMENT ON TABLE reply is '뮤직/음향 댓글';
COMMENT ON COLUMN reply.rno is '댓글번호';
COMMENT ON COLUMN reply.rcomment is '내용';
COMMENT ON COLUMN reply.ctno is '글번호';
COMMENT ON COLUMN reply.userid is '아이디';
COMMENT ON COLUMN reply.rname is '등록자 닉네임';
COMMENT ON COLUMN reply.passwd is '비밀번호';
COMMENT ON COLUMN reply.wdate is '등록일자';

INSERT INTO  mreply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM mreply), '좋은 거래 하세요', 1, 'master', '구매원하는사람', '1234', sysdate);

INSERT INTO  mreply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM mreply), '좋은 거래 하세요2', 1, 'master', '구매원하는사람', '1234', sysdate);

INSERT INTO  mreply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM mreply), '좋은 거래 하세요3', 2, 'master', '구매원하는사람', '1234', sysdate);

/** 삭제 */
DELETE mreply WHERE rno = 1 and passwd = '5678'

select * from music
