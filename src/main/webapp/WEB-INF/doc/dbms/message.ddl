/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		userid                        		VARCHAR2(20)		 NOT NULL		 PRIMARY KEY,
		mno                           		NUMBER(6)		 NOT NULL,
		pwd                           		VARCHAR2(30)		 NOT NULL,
		name                          		VARCHAR2(20)		 NOT NULL,
		nickname                      		VARCHAR2(20)		 NULL ,
		tel                           		VARCHAR2(14)		 NOT NULL,
		zipcode                       		VARCHAR2(5)		 NULL ,
		address1                      		VARCHAR2(80)		 NULL ,
		address2                      		VARCHAR2(50)		 NULL ,
		email                         		VARCHAR2(100)		 NOT NULL,
		mdate                         		DATE		 NOT NULL,
		auth                          		VARCHAR2(20)		 DEFAULT N		 NOT NULL,
		confirm                       		CHAR(1)		 DEFAULT N		 NOT NULL,
		act                           		CHAR(1)		 DEFAULT N		 NOT NULL,
		dropout                       		CHAR(1)		 DEFAULT N		 NOT NULL
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


/**********************************/
/* Table Name: 메시지 */
/**********************************/
CREATE TABLE message(
    msg_no                            NUMBER(6)         NOT NULL   PRIMARY KEY, -- 번호
    sendid                            VARCHAR2(20)      NOT NULL,               -- 보낸 사람
    receiveid                         VARCHAR2(20)      NOT NULL,               -- 받는 사람
    title                             VARCHAR2(200)     NOT NULL,               -- 제목
    content                           VARCHAR2(4000)    NOT NULL,               -- 내용
    msg_date                          DATE              NOT NULL,               -- 전송 시간
    read_ck                           CHAR(1)           DEFAULT 'N' NOT NULL,   -- 읽음 여부
    visible                           CHAR(1)           DEFAULT 'Y' NOT NULL,   -- 메시지 표시
  FOREIGN KEY (receiveid) REFERENCES member (userid),
  FOREIGN KEY (sendid)    REFERENCES member (userid)
);
 DROP TABLE message

COMMENT ON TABLE message is '메시지';
COMMENT ON COLUMN message.msg_no is '번호';
COMMENT ON COLUMN message.sendid is '보낸 사람';
COMMENT ON COLUMN message.receiveid is '받는 사람';
COMMENT ON COLUMN message.title is '제목';
COMMENT ON COLUMN message.content is '내용';
COMMENT ON COLUMN message.msg_date is '전송 시간';
COMMENT ON COLUMN message.read_ck is '읽음 여부';


1. 입력
INSERT INTO message (msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible)
  VALUES ((SELECT NVL(MAX(msg_no), 0)+1 as msg_no FROM message), 
            'master', 'chanmi', '제목', '내용', sysdate, 'N', 'Y');
INSERT INTO message (msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible)
  VALUES ((SELECT NVL(MAX(msg_no), 0)+1 as msg_no FROM message), 
            'chanmi', 'master', '답장', '내용2', sysdate, 'N', 'Y');
INSERT INTO message (msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible)
  VALUES ((SELECT NVL(MAX(msg_no), 0)+1 as msg_no FROM message), 
            'master', 'chanmi', '안녕하세요', '내용2', sysdate, 'N', 'Y');


2. 조회
-- 전체 조회 (관리자용)
	SELECT msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible 
	FROM message
-- 받은 메시지 조회 (chanmi 기준)
	SELECT msg_no, sendid, receiveid, title, content, msg_date, read_ck 
	FROM message
	WHERE receiveid = 'chanmi' and visible = 'Y'
-- 보낸 메시지 조회 (chanmi 기준)
	SELECT msg_no, sendid, receiveid, title, content, msg_date, read_ck 
	FROM message
	WHERE sendid = 'chanmi'
	
3. 수정: 메시지 읽었을 때
UPDATE message
SET read_ck = 'Y'
WHERE receiveid = 'chanmi'

4. 삭제
-- 관리자용
DELETE FROM message
WHERE msg_no = '1'

-- 회원용
UPDATE message
SET visible = 'N'
WHERE receiveid = 'chanmi'

