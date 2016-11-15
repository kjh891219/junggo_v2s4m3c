-- 테이블 다 지우기
DROP TABLE usedcar;
DROP TABLE rv_comment;
DROP TABLE review;
DROP TABLE message;
DROP TABLE MEMBER;
DROP TABLE MEMBER CASCADE CONSTRAINTS PURGE;
-----------------------------------------------
UPDATE member 
    SET dropout='Y', act='N'
    WHERE userid='chanmi'
UPDATE member 
    SET dropout='N', act='Y'
    WHERE userid='chanmi'

    UPDATE member 
    SET confirm='Y', act='Y'
    WHERE userid='handam'
    UPDATE member 
    SET confirm='Y', act='Y'
    WHERE userid='sol'
    
SELECT nickname
    FROM member
    WHERE userid NOT IN ('chanmi');
-----------------------------------------------

CREATE TABLE member(
    userid                            VARCHAR2(20)     NOT NULL PRIMARY KEY,   -- 아이디
    mno                               NUMBER(6)        NOT NULL UNIQUE,        -- 회원 번호
    pwd                               VARCHAR2(100)     NOT NULL,               -- 비밀번호
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
    dropout                          VARCHAR2(1)      DEFAULT 'N'   NOT NULL   -- 탈퇴 신청
);

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.userid is '아이디';
COMMENT ON COLUMN member.mno is '회원 번호';
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


DROP TABLE member;
delete from MEMBER
select * from member
SELECT COUNT(userid) as cnt
    FROM member
    WHERE email='test@mail.com'
1. 입력
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '관리자', '관리자', 'abc@mail.com', '000-0000-0000', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('chanmi', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '박찬미', '찬미', 'chanmi910@naver.com', '000-1111-1111', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N');
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('user1', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '성명', '유저1', 'test@naver.com', '000-1111-1111', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N');
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('user2', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '성명', '유저2', 'tes2t@naver.com', '000-1111-1111', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797083', 'Y', 'Y', 'N');
              
2. 조회
SELECT userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout
FROM member
ORDER BY mno ASC

3. 수정
UPDATE member 
SET pwd='0000'
WHERE mno='1'

4. 삭제
DELETE FROM MEMBER WHERE mno='2'