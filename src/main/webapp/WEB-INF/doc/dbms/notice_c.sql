DROP table notice

CREATE TABLE notice(
    noticeno                          NUMBER(10)     NOT NULL    PRIMARY KEY,
    nickname                          VARCHAR2(20)     NOT NULL,
    title                             VARCHAR2(100)    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NULL ,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
    file1                             VARCHAR2(100)        NULL ,
    file2                             VARCHAR2(50)         NULL ,
    size2                             NUMBER(9)        DEFAULT 0       NULL ,
    userid                            VARCHAR2(20)     NULL ,
  FOREIGN KEY (userid) REFERENCES member_test (userid)
);

 noticeno, nickname, title, content, wdate, cnt, userid
COMMENT ON TABLE notice is '공지사항';
COMMENT ON COLUMN notice.noticeno is '공지사항';
COMMENT ON COLUMN notice.nickname is '닉네임';
COMMENT ON COLUMN notice.title is '제목';
COMMENT ON COLUMN notice.content is '내용';
COMMENT ON COLUMN notice.wdate is '날짜';
COMMENT ON COLUMN notice.cnt is '조회수';
COMMENT ON COLUMN notice.userid is '아이디';

INSERT INTO notice(noticeno, nickname, title, content, userid)  
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice), 'nickname','title', 'content',
   (select userid from member_test where userid='master'));

INSERT INTO notice(noticeno, nickname, title, content, userid)  
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice), '자바','공지사항', '공지사항입니다',
   (select userid from member_test where userid='master'));
   
INSERT INTO notice(noticeno, nickname, title, content, userid)  
VALUES((SELECT NVL(MAX(noticeno), 0) + 1 as noticeno FROM notice), 'HTML','가을입니다', '가을이군요',
   (select userid from member_test where userid='master'));   
   
2.조회 
SELECT noticeno, nickname, title, content, wdate, cnt, userid
FROM notice
WHERE noticeno = 1;

3.수정 
UPDATE notice 
SET title = 'title2', content = 'content2'
WHERE noticeno = 1;
4.삭제 

DELETE FROM notice
WHERE noticeno = 1;

5.목록
SELECT noticeno, nickname, title, content, wdate, cnt, userid
FROM notice 
ORDER BY noticeno ASC;

drop table notice_reply

CREATE TABLE notice_reply(
    rnno                              NUMBER(7)        NOT NULL    PRIMARY KEY,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    rcontent                          VARCHAR2(1000)     NOT NULL,
    noticeno                          NUMBER(10)     NOT NULL,
    userid                            VARCHAR2(20)     NULL ,
  FOREIGN KEY (noticeno) REFERENCES notice (noticeno),
  FOREIGN KEY (userid) REFERENCES member_test (userid)
);
alter table notice_reply add(rnno number(6));
alter table notice_reply
add constraint FK_notice_reply FOREIGN KEY(noticeno)
REFERENCES notice (noticeno);

alter table notice_reply add(userid varchar2(20));
alter table notice_reply
add constraint notice_reply_userid FOREIGN KEY(userid)
REFERENCES member_test(userid);

1등록 
INSERT INTO notice_reply(rnno, nickname, passwd, rcontent, noticeno, userid)  
VALUES((SELECT NVL(MAX(rnno), 0) + 1 as rnno FROM notice_reply), 'nickname','passwd', 'rcontent', (select noticeno from notice where noticeno= 1)
   ,(select userid from member_test where userid='master'));

2.조회 
SELECT rnno, nickname, passwd, content, wdate, cnt, userid
FROM  notice_reply
WHERE rnno = 1;

3.수정 
UPDATE notice_reply 
SET content = 'content2'
WHERE rmno = 1;
4.삭제 

DELETE FROM mobile_reply
WHERE rmno = 1;

5.목록
SELECT rmno, nickname, content, wdate, cnt, userid
FROM mobile_reply 
ORDER BY rmno ASC;

COMMENT ON TABLE notice reply is '공지사항 댓글';
COMMENT ON COLUMN notice reply.rno is '댓글번호';
COMMENT ON COLUMN notice reply.nickname is '닉네임';
COMMENT ON COLUMN notice reply.passwd is '비밀번호';
COMMENT ON COLUMN notice reply.rcontent is '내용';
COMMENT ON COLUMN notice reply.noticeno is '글번호';
COMMENT ON COLUMN notice reply.userid is '아이디';



