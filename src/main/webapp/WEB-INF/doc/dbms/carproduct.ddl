/**********************************/
/* Table Name: 회원 */
/**********************************/
DROP TABLE carproduct
DROP TABLE p_comments
DELETE * FROM carproduct
select nickname from member
1. member table 생성
CREATE TABLE member(
    userid                             VARCHAR2(20)                           NOT NULL     PRIMARY KEY,
    mno                               NUMBER(6)                               NOT NULL     UNIQUE,
    pwd                                VARCHAR2(30)                          NOT NULL,
    name                              VARCHAR2(20)                           NOT NULL,
    nickname                         VARCHAR2(20)                           NOT NULL     UNIQUE,
    tel                                  VARCHAR2(14)                           NOT NULL,
    zipcode                            VARCHAR2(5)                                   NULL,
    address1                          VARCHAR2(80)                                  NULL,
    address2                          VARCHAR2(50)                                  NULL,
    email                              VARCHAR2(100)                          NOT NULL     UNIQUE,
    mdate                             DATE                                        NOT NULL,
    auth                               VARCHAR2(20)     DEFAULT 'N'      NOT NULL,
    confirm                           CHAR(1)              DEFAULT 'N'      NOT NULL, -- 이메일 링크 클릭 여부, Y:클릭, N:클릭안함
    act                                 CHAR(1)                                     NOT NULL,  -- M: 마스터, Y: 로그인 가능, N: 로그인 불가, H: 인증 대기
    dropout                         VARCHAR2(1)      DEFAULT 'N'       NOT NULL
);

SELECT * FROM member;


COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.mno is '회원 번호';
COMMENT ON COLUMN member.userid is '아이디';
COMMENT ON COLUMN member.pwd is '비밀번호';
COMMENT ON COLUMN member.name is '이름';
COMMENT ON COLUMN member.nickname is '닉네임';
COMMENT ON COLUMN member.tel is '전화번호';
COMMENT ON COLUMN member.zipcode is '우편번호';
COMMENT ON COLUMN member.address1 is '기본 주소';
COMMENT ON COLUMN member.address2 is '상세 주소';
COMMENT ON COLUMN member.email is '이메일';
COMMENT ON COLUMN member.mdate is '가입일';
COMMENT ON COLUMN member.auth is '인증키';
COMMENT ON COLUMN member.confirm is '인증 여부';
COMMENT ON COLUMN member.act is '권한';
COMMENT ON COLUMN member.dropout is '탈퇴 신청';



/**********************************/ 
/* Table Name: 자동차 용품 게시판 */
/**********************************/
CREATE TABLE carproduct(
    p_no                               NUMBER(6)                                  NOT NULL,
    nickname                         VARCHAR2(20)                              NOT NULL,
    passwd                             VARCHAR2(20)                              NOT NULL,
    category                           CHAR(20)                                    NOT NULL,
    deal_way                          VARCHAR2(20)                              NOT NULL,
    deal_code                         VARCHAR2(20)                              NOT NULL,
    product_code                    VARCHAR2(20)                              NOT NULL,
    h_price                              NUMBER(15)         DEFAULT 0         NOT NULL,
    region                             VARCHAR2(20)                              NOT NULL,
    tel                                  VARCHAR2(14)                              NOT NULL ,
    email                              VARCHAR2(100)                             NOT NULL ,
    quantity                          NUMBER(6)          DEFAULT 0           NOT NULL ,
    title                                VARCHAR2(200)                             NOT NULL,
    content                           VARCHAR2(4000)                           NOT NULL,
    thumb                            VARCHAR2(100)                                   NULL ,
    file1                               VARCHAR2(50)                                     NULL,
    size1                               NUMBER(9)          DEFAULT 0                NULL,
    file2                              VARCHAR2(50)                                     NULL,
    size2                               NUMBER(9)          DEFAULT 0                NULL,
    file3                               VARCHAR2(50)                                     NULL,
    size3                               NUMBER(9)          DEFAULT 0                NULL,
    file4                               VARCHAR2(50)                                     NULL,
    size4                               NUMBER(9)          DEFAULT 0                NULL,
    file5                               VARCHAR2(50)                                     NULL,
    size5                               NUMBER(9)          DEFAULT 0                NULL,
    purc_date                         VARCHAR2(20)                             NOT NULL ,
    userid                             VARCHAR2(20)                              NOT NULL,
    wdate                             DATE                  DEFAULT sysdate   NOT NULL,
    p_cnt                              NUMBER(6)          DEFAULT 0           NOT NULL,
    PRIMARY KEY(p_no),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

alter table carproduct rename column h_price to hprice;
alter table carproduct rename column p_cnt to cnt;

SELECT * FROM carproduct;
DELETE * FROM carproduct;

COMMENT ON TABLE carproduct is '자동차 용품 게시판';
COMMENT ON COLUMN carproduct.p_no is '용품번호';
COMMENT ON COLUMN carproduct.category is '카테고리';
COMMENT ON COLUMN carproduct.nickname is '닉네임';
COMMENT ON COLUMN carproduct.p_pwd is '패스워드';
COMMENT ON COLUMN carproduct.deal_way is '거래방식';
COMMENT ON COLUMN carproduct.deal_code is '거래구분';
COMMENT ON COLUMN carproduct.product_code is '상품구분';
COMMENT ON COLUMN carproduct.hprice is '희망가격';
COMMENT ON COLUMN carproduct.region is '지역';
COMMENT ON COLUMN carproduct.title is '제목';
COMMENT ON COLUMN carproduct.content is '상세설명';
COMMENT ON COLUMN carproduct.pruc_date is '구입시기';
COMMENT ON COLUMN carproduct.userid is '아이디';
COMMENT ON COLUMN carproduct.wdate is '글등록일';
COMMENT ON COLUMN carproduct.p_cnt is '조회수';


/**********************************/
/* Table Name: 댓글 */
/**********************************/
CREATE TABLE p_comment(
		c_no                          		  NUMBER(6)		         NOT NULL,
		nickname                       VARCHAR2(20)		     NOT NULL,
		passwd                        		VARCHAR2(20)		     NOT NULL,
		content                     		  VARCHAR2(400)		   NOT NULL,
		p_no                          		NUMBER(6)		                NULL,
		PRIMARY KEY (c_no),
   FOREIGN KEY (p_no) REFERENCES carproduct (p_no)
);

COMMENT ON TABLE comment is '댓글';
COMMENT ON COLUMN comment.c_no is '댓글번호';
COMMENT ON COLUMN comment.c_userid is '아이디';
COMMENT ON COLUMN comment.c_pwd is '비밀번호';
COMMENT ON COLUMN comment.c_content is '내용';
COMMENT ON COLUMN comment.p_no is '용품번호';

2)삽입
 --carproduct TABLE 삽입
INSERT INTO carproduct(p_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, quantity ,tel)
VALUES((SELECT NVL(MAX(p_no), 0)+1 as p_no FROM carproduct), 'sol', 
           'sol1', '1234', 'HYUNDAI', '현장거래',
            '팝니다', '중고', 1000000, '2016-10-29','서울', '엔진','깨끗합니다.', 'test@daum.net', 0,'010-0000-0000');
            
INSERT INTO carproduct(p_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, quantity, tel)
VALUES((SELECT NVL(MAX(p_no), 0)+1 as p_no FROM carproduct), 'chan', 
           'PCM', '1234', 'KIA', '택배',
            '팝니다', '신상', 400000, '2016-11-1','경기', '에어컨 필터','좋습니다.', 'test2@daum.net', 5,'010-0000-0001');
            
-- carproduct p_comment TABLE 삽입            
INSERT INTO p_comment(c_no, nickname, passwd, content, p_no)
VALUES((SELECT NVL(MAX(c_no), 0)+1 as c_no FROM p_comment), 'chan', '111', '많이 사주세요', '2');

3)조회
--carproduct TABLE 조회
SELECT * FROM carproduct;

SELECT   * 
FROM   carproduct
WHERE p_no=1;

--comments TABLE 조회
SELECT * FROM p_comment;

SELECT   * 
FROM   p_comment
WHERE c_no=1;


4)삭제
-- carproduct TABLE 삭제
DELETE FROM carproduct;

DELETE FROM carproduct
WHERE p_no = 1;

--p_comments TABLE 삭제
DELETE FROM p_comment;

DELETE FROM p_comment
WHERE c_no = 1;

5)변경
ALTER TABLE carproduct;

UPDATE carproduct
SET content='팔아야 합니다'
WHERE p_no=1;