
CREATE TABLE member_1_1(
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

select * from member_1
INSERT INTO member_1(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_1), '1234', '관리자', '관리자', 'abc@mail.com', '000-0000-0000', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N')
INSERT INTO member_1(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('chanmi', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_1), '1234', '성명', '찬미', 'chanmi910@naver.com', '000-1111-1111', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N')
INSERT INTO member_1(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('badid', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_1), '1234', '성명1', '테스트ID', 'chanmi9110@naver.com', '000-1111-1111', '12345', '기본주소', '상세주소', sysdate,
              'VIL1476668797083', 'Y', 'Y', 'N')
                            