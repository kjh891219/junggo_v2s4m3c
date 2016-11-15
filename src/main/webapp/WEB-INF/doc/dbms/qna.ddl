
/**********************************/
/* Table Name: ���ǰԽ��� */
/**********************************/

-- ���̺� �̸� ����
alter table qna Rename to qna_1;
alter table member_1 Rename to member_2;
-- �÷��߰�
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
-- �÷��� ����
alter table test rename column osy to osy79;
-- �÷� Ÿ�� ����
alter table qna modify(passwd varchar(20));
-- �÷� ����
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

 -- ���̺� ��ȸ 
--SELECT * FROM QnA;
--   SELECT categoryno, sort
--   FROM category
--   ORDER BY categoryno DESC;
--
--select qnano, title, content, qdate, qfile, cate.sort, userid 
--FROM qna, CATEGORY cate 
--where qna.categoryno = cate.categoryno 
--ORDER BY qnano DESC;

-- ���� ��ȸ
--select cate.sort FROM QnA, category cate where qna.categoryno = cate.categoryno and qnano = 1;
--select cate.sort FROM QnA, category cate where qna.categoryno = cate.categoryno;

1) ���

INSERT INTO qna(qnano, title, content, qdate, file1, file2, file3, size1, size2, size3, categoryno, userid, nickname,
					tel, email, passwd, cnt, replycnt, grpno, indent, ansnum)  
VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qna), '�α����� ���� �ʽ��ϴ�',
        '�ذ����ּ���', sysdate, '', '', '', 0, 0, 0, 1, (SELECT userid FROM member WHERE userid = 'chanmi'),
        (SELECT nickname FROM member WHERE userid = 'chanmi'), (SELECT tel FROM member WHERE userid = 'chanmi'), 
        (SELECT email FROM member WHERE userid = 'chanmi'), (SELECT pwd FROM member WHERE userid = 'chanmi'),
        0, 0, 0, 0, 0);
        
INSERT INTO qna(qnano, title, content, qdate, file1, file2, file3, size1, size2, size3, categoryno, userid, nickname,
					tel, email, passwd, cnt, replycnt, grpno, indent, ansnum)  
VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qna), '��۹����մϴ�',
        '��ǰ�� �޾ƺ��� ���߽��ϴ�', sysdate, '', '', '', 0, 0, 0, 2, (SELECT userid FROM member WHERE userid = 'chanmi'),
        (SELECT nickname FROM member WHERE userid = 'chanmi'), (SELECT tel FROM member WHERE userid = 'chanmi'), 
        (SELECT email FROM member WHERE userid = 'chanmi'), (SELECT pwd FROM member WHERE userid = 'chanmi'),
        0, 0, 0, 0, 0);
        
INSERT INTO qna(qnano, title, content, qdate, file1, file2, file3, size1, size2, size3, categoryno, userid, nickname,
					tel, email, passwd, cnt, replycnt, grpno, indent, ansnum)  
VALUES((SELECT NVL(MAX(qnano), 0) + 1 as qnano FROM qna), '��Ÿ����',
        'ȭ���� ������ �ʽ��ϴ�', sysdate, '', '', '', 0, 0, 0, 1, (SELECT userid FROM member WHERE userid = 'chanmi'),
        (SELECT nickname FROM member WHERE userid = 'chanmi'), (SELECT tel FROM member WHERE userid = 'chanmi'), 
        (SELECT email FROM member WHERE userid = 'chanmi'), (SELECT pwd FROM member WHERE userid = 'chanmi'),
        0, 0, 0, 0, 0);
        
SELECT * FROM qna;
        
 QNANO TITLE        CONTENT        QDATE    FILE1 FILE2 SIZE2 CATEGORYNO USERID CNT REPLYCNT GRPNO INDENT ANSNUM FILE3 SIZE1 SIZE3 NICKNAME TEL           EMAIL               PASSWD
 ----- ------------ -------------- -------- ----- ----- ----- ---------- ------ --- -------- ----- ------ ------ ----- ----- ----- -------- ------------- ------------------- ------
     3 ��Ÿ����         ȭ���� ������ �ʽ��ϴ�   16/10/31 NULL  NULL      0          1 chanmi   0        0     0      0      0 NULL      0     0 ����       000-1111-1111 chanmi910@naver.com 1234
     1 �α����� ���� �ʽ��ϴ� �ذ����ּ���         16/10/31 NULL  NULL      0          1 chanmi   0        0     0      0      0 NULL      0     0 ����       000-1111-1111 chanmi910@naver.com 1234
     2 ��۹����մϴ�      ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/31 NULL  NULL      0          2 chanmi   0        0     0      0      0 NULL      0     0 ����       000-1111-1111 chanmi910@naver.com 1234

2) ��ȸ 

-- ���� ��ȸ
select qnano, title, content, qdate, file1, categoryno, m.userid 
FROM qna, member m
where qna.userid = m.userid
ORDER BY qnano DESC;

 QNANO TITLE        CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------------ -------------- -------- ----- ---------- ------
     8 ��ǰ �̼���4      ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user2
     7 ��ǰ �̼���3      ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user1
     6 ��ǰ �̼���2      ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user2
     5 ��ǰ �̼���1      ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user1
     4 ��ǰ �̼���       ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 chanmi
     3 ��Ÿ����         ȭ���� ������ �ʽ��ϴ�   16/10/26 NULL           3 chanmi
     2 ��۹����մϴ�      ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 chanmi
     1 �α����� ���� �ʽ��ϴ� �ذ����ּ���         16/10/26 NULL           1 chanmi


     
-- ī�װ� �� ��ȸ 
select qnano, title, content, qdate, file1, categoryno, userid 
FROM qna 
where categoryno = 2
ORDER BY qnano DESC;

 QNANO TITLE   CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------- -------------- -------- ----- ---------- ------
     8 ��ǰ �̼���4 ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user2
     7 ��ǰ �̼���3 ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user1
     6 ��ǰ �̼���2 ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user2
     5 ��ǰ �̼���1 ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user1
     4 ��ǰ �̼���  ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 chanmi
     2 ��۹����մϴ� ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 chanmi


-- �������̵� �� ��ȸ 

select qnano, title, content, qdate, file1, categoryno, m.userid 
FROM qna, member m
where qna.userid = m.userid
and qna.userid = 'user1';

 QNANO TITLE   CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------- -------------- -------- ----- ---------- ------
     5 ��ǰ �̼���1 ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user1
     7 ��ǰ �̼���3 ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user1



3) ����

UPDATE QnA 
SET  title='��ǰ�߼۹���'
WHERE qnano =  5;

 QNANO TITLE   CONTENT        QDATE    FILE1 CATEGORYNO USERID
 ----- ------- -------------- -------- ----- ---------- ------
     5 ��ǰ�߼۹���  ��ǰ�� �޾ƺ��� ���߽��ϴ� 16/10/26 NULL           2 user1

4) ����

DELETE FROM qna 
WHERE qnano = 7; 

SELECT qnano, title, rownum
FROM qna
ORDER BY title ASC;

5) �˻�
-- title
SELECT blogno,
           blogcategoryno, mno, title, content, good, file1, file2, size2, cnt, replycnt, rdate, 
           grpno, indent, ansnum
FROM blog
WHERE blogcategoryno=1 AND title LIKE '%�ް�%'
ORDER BY blogno DESC;
 
-- content
SELECT blogno,
           blogcategoryno, mno, title, content, good, file1, file2, size2, cnt, replycnt, rdate, 
           grpno, indent, ansnum
FROM blog
WHERE blogcategoryno=1 AND content LIKE '%�ް�%'
ORDER BY blogno DESC;
 
-- ����� �������� �˻�
SELECT blogno,
           blogcategoryno, mno, title, content, good, file1, file2, size2, cnt, replycnt, rdate, 
           grpno, indent, ansnum
FROM blog
WHERE blogcategoryno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%'
ORDER BY blogno DESC;
 
6) �˻� �� ��ü ���ڵ� ����
-- �˻����� �ʴ� ���
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1;
 
-- title �˻�
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1 AND title LIKE '%�ް�%';
 
-- content
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1 AND content LIKE '%�ް�%';
 
-- ����� �������� �˻�
SELECT COUNT(*) as cnt
FROM blog
WHERE blogcategoryno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%';



COMMENT ON TABLE QnA is '���ǰԽ���';
COMMENT ON COLUMN QnA.qnano is '���ǰԽ��ǹ�ȣ';
COMMENT ON COLUMN QnA.title is '����';
COMMENT ON COLUMN QnA.content is '����';
COMMENT ON COLUMN QnA.date is '��¥';
COMMENT ON COLUMN QnA.file is '����';
COMMENT ON COLUMN QnA.writer is '�۾���';
COMMENT ON COLUMN QnA.password is '��й�ȣ';
COMMENT ON COLUMN QnA.categoryno is '��ȣ';
COMMENT ON COLUMN QnA.userid is '���̵�';

