
/**********************************/
/* Table Name: 문의게시판 */
/**********************************/

-- 테이블 이름 변경
alter table qna Rename to qna_1;
alter table member_1 Rename to member_2;
-- 컬럼추가
alter table qna add(qfile2 VARCHAR2(100) NULL) ;
alter table qna add(
        cnt                    NUMBER(7)        DEFAULT 0       NOT NULL,
        replycnt              NUMBER(7)        DEFAULT 0       NOT NULL,
        grpno                 NUMBER(7)        DEFAULT 0       NOT NULL,
        indent                NUMBER(2)        DEFAULT 0       NOT NULL,
        ansnum              NUMBER(5)        DEFAULT 0       NOT NULL);
alter table qna add(
    nickname                VARCHAR2(30)  DEFAULT ''   NOT NULL,
          tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
      email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    passwd               VARCHAR2(10)   DEFAULT ''  NOT NULL);
-- 컬럼명 변경
alter table test rename column osy to osy79;
-- 컬럼 타입 변경
alter table qna modify(passwd varchar(20));
-- 컬럼 삭제
alter table qna drop(cnt, replycnt,grpno, indent, ansnum);


DROP TABLE QnA;

CREATE TABLE QnA(
      qnano                               NUMBER(6)       NOT NULL       PRIMARY KEY,
      title                               VARCHAR2(200)      NOT NULL,
      content                             VARCHAR2(4000)     NOT NULL,
      qdate                               VARCHAR2(20)       NOT NULL,
      file1                               VARCHAR2(100)    DEFAULT ''       NULL ,
      file2                               VARCHAR2(50)     DEFAULT ''       NULL ,
      file3                               VARCHAR2(100)    DEFAULT ''       NULL ,
      size1                               NUMBER(9)        DEFAULT 0       NULL ,
      size2                               NUMBER(9)        DEFAULT 0       NULL ,
      size3                               NUMBER(9)        DEFAULT 0       NULL,
      categoryno                          NUMBER(6)          NOT NULL ,
      userid                              VARCHAR2(20)       NOT NULL ,
      nickname                            VARCHAR2(30)      DEFAULT ''   NOT NULL,
      tel                                 VARCHAR2(14)     DEFAULT ''    NOT NULL,
      email                               VARCHAR2(100)    DEFAULT ''    NOT NULL,
      passwd                              VARCHAR2(100)      DEFAULT ''  NOT NULL,
      cnt                                 NUMBER(7)        DEFAULT 0       NOT NULL,
      replycnt                            NUMBER(7)        DEFAULT 0       NOT NULL,
      grpno                               NUMBER(7)        DEFAULT 0       NOT NULL,
      indent                              NUMBER(2)        DEFAULT 0       NOT NULL,
      ansnum                              NUMBER(5)        DEFAULT 0       NOT NULL,
  FOREIGN KEY (userid) REFERENCES member (userid)
);

 -- 테이블 조회 
--SELECT * FROM QnA;
--   SELECT categoryno, sort
--   FROM category
--   ORDER BY categoryno DESC;
--
--select qnano, title, content, qdate, qfile, cate.sort, userid 
--FROM qna, CATEGORY cate 
--where qna.categoryno = cate.categoryno 
--ORDER BY qnano DESC;

-- 조인 조회
--select cate.sort FROM QnA, category cate where qna.categoryno = cate.categoryno and qnano = 1;
--select cate.sort FROM QnA, category cate where qna.categoryno = cate.categoryno;

1) 등록

INSERT INTO qna(qnano, title, content, qdate, file1, file2, file3, size1, size2, size3, categoryno, userid, nickname,
					tel, email, passwd, cnt, replycnt, grpno, indent, ansnum)  
VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qna), '로그인이 되지 않습니다',
        '해결해주세요', sysdate, '', '', '', 0, 0, 0, 1, (SELECT userid FROM member WHERE userid = 'chanmi'),
        (SELECT nickname FROM member WHERE userid = 'chanmi'), (SELECT tel FROM member WHERE userid = 'chanmi'), 
        (SELECT email FROM member WHERE userid = 'chanmi'), (SELECT pwd FROM member WHERE userid = 'chanmi'),
        0, 0, 0, 0, 0);
        
INSERT INTO qna(qnano, title, content, qdate, file1, file2, file3, size1, size2, size3, categoryno, userid, nickname,
					tel, email, passwd, cnt, replycnt, grpno, indent, ansnum)  
VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qna), '배송문의합니다',
        '상품을 받아보지 못했습니다', sysdate, '', '', '', 0, 0, 0, 2, (SELECT userid FROM member WHERE userid = 'chanmi'),
        (SELECT nickname FROM member WHERE userid = 'chanmi'), (SELECT tel FROM member WHERE userid = 'chanmi'), 
        (SELECT email FROM member WHERE userid = 'chanmi'), (SELECT pwd FROM member WHERE userid = 'chanmi'),
        0, 0, 0, 0, 0);
        
INSERT INTO qna(qnano, title, content, qdate, file1, file2, file3, size1, size2, size3, categoryno, userid, nickname,
					tel, email, passwd, cnt, replycnt, grpno, indent, ansnum)  
VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qna), '기타문의',
        '화면이 보이지 않습니다', sysdate, '', '', '', 0, 0, 0, 1, (SELECT userid FROM member WHERE userid = 'chanmi'),
        (SELECT nickname FROM member WHERE userid = 'chanmi'), (SELECT tel FROM member WHERE userid = 'chanmi'), 
        (SELECT email FROM member WHERE userid = 'chanmi'), (SELECT pwd FROM member WHERE userid = 'chanmi'),
        0, 0, 0, 0, 0);
        
SELECT * FROM qna;
        
 QNANO TITLE        CONTENT        QDATE    FILE1 FILE2 SIZE2 CATEGORYNO USERID CNT REPLYCNT GRPNO INDENT ANSNUM FILE3 SIZE1 SIZE3 NICKNAME TEL           EMAIL               PASSWD
 ----- ------------ -------------- -------- ----- ----- ----- ---------- ------ --- -------- ----- ------ ------ ----- ----- ----- -------- ------------- ------------------- ------
     3 기타문의         화면이 보이지 않습니다   16/10/31 NULL  NULL      0          1 chanmi   0        0     0      0      0 NULL      0     0 찬미       000-1111-1111 chanmi910@naver.com 1234
     1 로그인이 되지 않습니다 해결해주세요         16/10/31 NULL  NULL      0          1 chanmi   0        0     0      0      0 NULL      0     0 찬미       000-1111-1111 chanmi910@naver.com 1234
     2 배송문의합니다      상품을 받아보지 못했습니다 16/10/31 NULL  NULL      0          2 chanmi   0        0     0      0      0 NULL      0     0 찬미       000-1111-1111 chanmi910@naver.com 1234

2) 조회 

-- 조인 조회
select qnano, title, content, qdate, file1, categoryno, m.userid 
FROM qna, member m
where qna.userid = m.userid
ORDER BY qnano DESC;

 QNANO TITLE        CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------------ -------------- -------- ----- ---------- ------
     8 상품 미수취4      상품을 받아보지 못했습니다 16/10/26 NULL           2 user2
     7 상품 미수취3      상품을 받아보지 못했습니다 16/10/26 NULL           2 user1
     6 상품 미수취2      상품을 받아보지 못했습니다 16/10/26 NULL           2 user2
     5 상품 미수취1      상품을 받아보지 못했습니다 16/10/26 NULL           2 user1
     4 상품 미수취       상품을 받아보지 못했습니다 16/10/26 NULL           2 chanmi
     3 기타문의         화면이 보이지 않습니다   16/10/26 NULL           3 chanmi
     2 배송문의합니다      상품을 받아보지 못했습니다 16/10/26 NULL           2 chanmi
     1 로그인이 되지 않습니다 해결해주세요         16/10/26 NULL           1 chanmi


     
-- 카테고리 별 조회 
select qnano, title, content, qdate, file1, categoryno, userid 
FROM qna 
where categoryno = 2
ORDER BY qnano DESC;

 QNANO TITLE   CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------- -------------- -------- ----- ---------- ------
     8 상품 미수취4 상품을 받아보지 못했습니다 16/10/26 NULL           2 user2
     7 상품 미수취3 상품을 받아보지 못했습니다 16/10/26 NULL           2 user1
     6 상품 미수취2 상품을 받아보지 못했습니다 16/10/26 NULL           2 user2
     5 상품 미수취1 상품을 받아보지 못했습니다 16/10/26 NULL           2 user1
     4 상품 미수취  상품을 받아보지 못했습니다 16/10/26 NULL           2 chanmi
     2 배송문의합니다 상품을 받아보지 못했습니다 16/10/26 NULL           2 chanmi


-- 유저아이디 별 조회 

select qnano, title, content, qdate, file1, categoryno, m.userid 
FROM qna, member m
where qna.userid = m.userid
and qna.userid = 'user1';

 QNANO TITLE   CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------- -------------- -------- ----- ---------- ------
     5 상품 미수취1 상품을 받아보지 못했습니다 16/10/26 NULL           2 user1
     7 상품 미수취3 상품을 받아보지 못했습니다 16/10/26 NULL           2 user1



3) 수정

UPDATE QnA 
SET  title='상품발송문의'
WHERE qnano =  5;

 QNANO TITLE   CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------- -------------- -------- ----- ---------- ------
     5 상품발송문의  상품을 받아보지 못했습니다 16/10/26 NULL           2 user1

4) 삭제

DELETE FROM qna 
WHERE qnano = 7; 

SELECT qnano, title, rownum
FROM qna
ORDER BY title ASC;

5) 검색
-- title
SELECT blogno,
           blogcategoryno, mno, title, content, good, file1, file2, size2, cnt, replycnt, rdate, 
           grpno, indent, ansnum
FROM blog
WHERE blogcategoryno=1 AND title LIKE '%휴가%'
ORDER BY blogno DESC;
 
-- content
SELECT blogno,
           blogcategoryno, mno, title, content, good, file1, file2, size2, cnt, replycnt, rdate, 
           grpno, indent, ansnum
FROM blog
WHERE blogcategoryno=1 AND content LIKE '%휴가%'
ORDER BY blogno DESC;
 
-- 제목과 내용으로 검색
SELECT blogno,
           blogcategoryno, mno, title, content, good, file1, file2, size2, cnt, replycnt, rdate, 
           grpno, indent, ansnum
FROM blog
WHERE blogcategoryno=1 AND  title LIKE '%휴가%' OR content LIKE '%휴가%'
ORDER BY blogno DESC;
 
6) 검색 및 전체 레코드 갯수
-- 검색하지 않는 경우
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1;
 
-- title 검색
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1 AND title LIKE '%휴가%';
 
-- content
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1 AND content LIKE '%휴가%';
 
-- 제목과 내용으로 검색
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1 AND  title LIKE '%휴가%' OR content LIKE '%휴가%';



COMMENT ON TABLE QnA is '문의게시판';
COMMENT ON COLUMN QnA.qnano is '문의게시판번호';
COMMENT ON COLUMN QnA.title is '제목';
COMMENT ON COLUMN QnA.content is '내용';
COMMENT ON COLUMN QnA.date is '날짜';
COMMENT ON COLUMN QnA.file is '파일';
COMMENT ON COLUMN QnA.writer is '글쓴이';
COMMENT ON COLUMN QnA.password is '비밀번호';
COMMENT ON COLUMN QnA.categoryno is '번호';
COMMENT ON COLUMN QnA.userid is '아이디';

