-----------------------------------------------------------
 SELECT wdate
    FROM usedcar
    ORDER BY u_no ASC

-----------------------------------------------------------

/**********************************/
/* Table Name: 회원+관리자    */
/**********************************/
DROP TABLE member
DROP TABLE usedcar
DROP TABLE c_comment
DELETE * FROM usedcar
commit

CREATE TABLE member(
    userid                            VARCHAR2(20)     NOT NULL PRIMARY KEY,   -- 아이디
    mno                               NUMBER(6)        NOT NULL UNIQUE,        -- 회원 번호
    pwd                               VARCHAR2(30)     NOT NULL,               -- 비밀번호
    name                              VARCHAR2(20)     NOT NULL,               -- 이름
    nickname                          VARCHAR2(20)     NOT NULL UNIQUE,        -- 닉네임
    tel                               VARCHAR2(14)     NOT NULL,               -- 전화번호
    zipcode                           VARCHAR2(5)          NULL,               -- 우편번호
    address1                          VARCHAR2(80)         NULL,               -- 기본 주소
    address2                          VARCHAR2(50)         NULL,               -- 상세 주소
    email                             VARCHAR2(100)    NOT NULL UNIQUE,        -- 이메일
    mdate                             DATE             NOT NULL,               -- 가입일
    auth                              VARCHAR2(20)     DEFAULT 'N'   NOT NULL, -- 인증키
    confirm                           CHAR(1)          DEFAULT 'N'   NOT NULL, -- 인증 여부, 이메일 링크 클릭 여부, Y:클릭, N:클릭안함
    act                               CHAR(1)          NOT NULL,               -- 권한, M: 마스터, Y: 로그인 가능, N: 로그인 불가, H: 인증 대기
    droupout                          VARCHAR2(1)      DEFAULT 'N'   NOT NULL  -- 탈퇴 신청
);


1. 입력
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '관리자', '관리자', 'abc@mail.com', '000-0000-0000', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N')
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('chanmi', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '성명', '찬미', 'chanmi910@naver.com', '000-1111-1111', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N')
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('user1', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '성명', '유저1', 'test@naver.com', '000-1111-1111', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N')
              
2. 조회
SELECT userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout
FROM member
ORDER BY mno ASC

select * from member;
select * from usedcar;
3. 수정
UPDATE member 
SET pwd='0000'
WHERE mno='1'

4. 삭제
DELETE FROM MEMBER WHERE mno='2'



/**********************************/
/* Table Name: 중고차 거래 게시판 */
/**********************************/
DROP TABLE usedcar;
CREATE TABLE usedcar(
		u_no                          		NUMBER(10)		                                 NOT NULL   PRIMARY KEY,
		nickname                      	VARCHAR2(20)		                               NOT NULL,
		passwd                        		VARCHAR2(20)		                               NOT NULL,
		category                      		CHAR(20)		                                     NOT NULL,
		deal_way                      		VARCHAR2(20)		                               NOT NULL,
		deal_code                     		VARCHAR2(20)		                               NOT NULL,
		product_code                  	VARCHAR2(20)	            	                   NOT NULL,
		h_price                       		NUMBER(15)		   DEFAULT 0		             NOT NULL,
		region                        		VARCHAR2(20)		                               NOT NULL,
		tel                           		  VARCHAR2(14)		                               NOT NULL,
		email                         		  VARCHAR2(100)		                             NOT NULL,
		quantity                      		NUMBER(6)		     DEFAULT 0		             NOT NULL,
		title                         		    VARCHAR2(200)		                             NOT NULL,
		content                       		VARCHAR2(4000)		                           NOT NULL,
    thumb                            VARCHAR2(100)                                      NULL ,
    file1                               VARCHAR2(50)                                        NULL,
    size1                               NUMBER(9)          DEFAULT 0                   NULL,
    file2                              VARCHAR2(50)                                         NULL,
    size2                               NUMBER(9)          DEFAULT 0                   NULL,
    file3                               VARCHAR2(50)                                        NULL,
    size3                               NUMBER(9)          DEFAULT 0                   NULL,
    file4                               VARCHAR2(50)                                        NULL,
    size4                               NUMBER(9)          DEFAULT 0                   NULL,
    file5                               VARCHAR2(50)                                        NULL,
    size5                               NUMBER(9)          DEFAULT 0                   NULL,
		purc_date                     		VARCHAR2(20)		                               NOT NULL,
		userid                        		  VARCHAR2(20)		                               NOT NULL,
		wdate                         		DATE		             DEFAULT sysdate		     NOT NULL,
		u_cnt                         		  NUMBER(6)		     DEFAULT 0		             NOT NULL,
    FOREIGN KEY (userid)        REFERENCES member(userid)
);

SELECT * FROM member
COMMIT
COMMENT ON TABLE usedcar is '중고차 거래 게시판';
COMMENT ON COLUMN usedcar.u_no is '중고차번호';
COMMENT ON COLUMN usedcar.nickname is '닉네임';
COMMENT ON COLUMN usedcar.passwd is '패스워드';
COMMENT ON COLUMN usedcar.category is '카테고리';
COMMENT ON COLUMN usedcar.deal_way is '거래방식';
COMMENT ON COLUMN usedcar.deal_code is '거래구분';
COMMENT ON COLUMN usedcar.product_code is '상품구분';
COMMENT ON COLUMN usedcar.h_price is '희망가격';
COMMENT ON COLUMN usedcar.region is '지역';
COMMENT ON COLUMN usedcar.tel is '전화번호';
COMMENT ON COLUMN usedcar.email is '이메일';
COMMENT ON COLUMN usedcar.quantity is '수량';
COMMENT ON COLUMN usedcar.title is '제목';
COMMENT ON COLUMN usedcar.content is '상세설명';
COMMENT ON COLUMN usedcar.purc_date is '구입시기';
COMMENT ON COLUMN usedcar.userid is '아이디';
COMMENT ON COLUMN usedcar.wdate is '글등록일';
COMMENT ON COLUMN usedcar.u_cnt is '조회수';


/**********************************/
/* Table Name: 댓글 */
/**********************************/
CREATE TABLE c_comment(
		c_no                          		 NUMBER(10)		        NOT NULL,
		nickname                      	VARCHAR2(20)		    NOT NULL,
		c_pwd                         		VARCHAR2(20)		    NOT NULL,
		c_content                     		VARCHAR2(400)		  NOT NULL,
		u_no                          		NUMBER(10)		             NULL,
		PRIMARY KEY(c_no),
  FOREIGN KEY (u_no) REFERENCES usedcar (u_no)
);

COMMENT ON TABLE comments is '댓글';
COMMENT ON COLUMN comments.c_no is '댓글번호';
COMMENT ON COLUMN comments.c_userid is '아이디';
COMMENT ON COLUMN comments.c_pwd is '비밀번호';
COMMENT ON COLUMN comments.c_content is '내용';
COMMENT ON COLUMN comments.u_no is '중고차번호';

2)삽입
 --member TABLE 삽입
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('sol1', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', 'hansol', 'sol', 'test@daum.net', '010-0000-0000', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');

INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('PCM', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', 'chanmi', 'pic', 'test2@daum.net', '010-0000-0001', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');

INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('jae', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', 'jaeheon', 'heon', 'test3@daum.net', '010-0000-0002', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');
              
  --usedcar TABLE 삽입            
INSERT INTO usedcar(u_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, tel)
VALUES((SELECT NVL(MAX(u_no), 0)+1 as u_no FROM usedcar), 'sol', 
           'sol1', '1234', 'HYUNDAI', '현장거래',
            '팝니다', '중고', 40000000, '2016-10-30','서울', '현대차','중고차 팝니다.', 'test@daum.net', '010-0000-0000');

INSERT INTO usedcar(u_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, tel)
VALUES((SELECT NVL(MAX(u_no), 0)+1 as u_no FROM usedcar), 'chan', 
           'PCM', '1234', 'KIA', '택배',
            '팝니다', '중고', 40000000, '2016-11-1','경기', '현대차','중고차 팝니다.', 'test2@daum.net', '010-0000-0001');
            
INSERT INTO usedcar(u_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, tel)
VALUES((SELECT NVL(MAX(u_no), 0)+1 as u_no FROM usedcar), (SELECT nickname FROM member WHERE userid='jae'), 
           'jae', '1234', 'KIA', '택배',
            '팝니다', '중고', 40000000, '2016-11-1','경기', '현대차','중고차 팝니다.', 'test2@daum.net', '010-0000-0001');                             
            
-- usedcar c_comment TABLE 삽입            
INSERT INTO c_comment(c_no, nickname, passwd, content, p_no)
VALUES((SELECT NVL(MAX(c_no), 0)+1 as c_no FROM c_comment), 'chan', '111', '많이 사주세요', '2');


3)조회

SELECT * FROM member;
--usedcar TABLE 조회
SELECT * FROM usedcar;

SELECT * 
FROM usedcar
WHERE u_no = 1;

--c_comment TABLE 조회
SELECT * FROM c_comment;

SELECT * 
FROM c_comment
WHERE c_no = 1;


4)삭제
--usedcar TABLE 삭제
DELETE FROM usedcar;

DELETE FROM usedcar
WHERE u_no = 1;

--p_comments TABLE 삭제
DELETE FROM c_comment;

DELETE FROM c_comment
WHERE c_no = 1;


5)변경
ALTER TABLE usedcar;

UPDATE usedcar
SET content='팔아야 합니다'
WHERE u_no=1;

6)조회수 증가
UPDATE usedcar
SET u_cnt = u_cnt + 1
WHERE u_no=1
