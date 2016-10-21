/**********************************/
/* Table Name: ������ǰ�Ű� */
/**********************************/
DROP TABLE CHEAT
CREATE TABLE CHEAT(
    ctno                              NUMBER(6)    NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NULL ,
    gubun                             VARCHAR2(50)     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    occurday                          CHAR(10)     DEFAULT ''    NOT NULL,
    buyprice                          NUMBER(15)     DEFAULT 0     NOT NULL,
    cheatid                           VARCHAR2(20)     DEFAULT ''    NULL ,
    cheattel                          VARCHAR2(14)     DEFAULT ''    NULL ,
    cheatemail                        VARCHAR2(100)    DEFAULT ''    NULL ,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NULL ,
    tel                               VARCHAR2(14)     DEFAULT ''    NULL ,
    userid                            VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     DEFAULT ''    NOT NULL,
    passwd                            VARCHAR2(10)     NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    file1                   VARCHAR2(100)        NULL ,
    file2                   VARCHAR2(50)         NULL ,
    size2                  NUMBER(9)        DEFAULT 0       NULL ,
  PRIMARY KEY (ctno),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

 /**gubun:�Ű����� (��ǰ�̹߼� / ���ºҷ� / �̹����̼� / �ù����� / ��ġƮ,���̹������� ����� ��)
  * 
  */
COMMENT ON TABLE CHEAT is '������ǰ�Ű�';
COMMENT ON COLUMN CHEAT.ctno is '�۹�ȣ';
COMMENT ON COLUMN CHEAT.GUBUN is '�Ű�����';
COMMENT ON COLUMN CHEAT.TITLE is '����';
COMMENT ON COLUMN CHEAT.REGION is '����';
COMMENT ON COLUMN CHEAT.OCCURDAY is '�߻�����';
COMMENT ON COLUMN CHEAT.BUYPRICE is '���ݾ�';
COMMENT ON COLUMN CHEAT.CHEATID is '������ǰ����� ID';
COMMENT ON COLUMN CHEAT.CHEATTEL is '������ǰ����ڿ���ó';
COMMENT ON COLUMN CHEAT.CHEATEMAIL is '������ǰ������̸���';
COMMENT ON COLUMN CHEAT.cnt is '��ȸ��';
COMMENT ON COLUMN CHEAT.CONTENT is '����';
COMMENT ON COLUMN CHEAT.EMAIL is '�����ּ�';
COMMENT ON COLUMN CHEAT.TEL is '��ȭ��ȣ';
COMMENT ON COLUMN CHEAT.userid is '���̵�';
COMMENT ON COLUMN CHEAT.nickname is '����';
COMMENT ON COLUMN CHEAT.passwd is '��й�ȣ';
COMMENT ON COLUMN CHEAT.wdate is '�������';

    SELECT ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd
    FROM cheat

/** sample ������
 * INSERT SQL
 */ 
INSERT INTO cheat (ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd)
VALUES ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM cheat), '�Ա��ߴµ� ���������̿���', '��ǰ�̹߼�','��û����','2016-09-27',  50000, 'badid', '010-1234-5678', 'BADID@daum.net', 0, '���� �ԱݿϷ��߾��. ������ �����Ǿ���� �Ф�', 'user2@naver.com','010-2222-3333','chanmi','�մ���','1234'); 

INSERT INTO cheat (ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd)
VALUES ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM cheat), '�Ա��ߴµ� ���������̿���2', '��ǰ�̹߼�','��󳲵�','2016-09-27',  50000, 'badid', '010-1234-5678', 'BADID@daum.net', 0, '���� �ԱݿϷ��߾��. ������ �����Ǿ���� �Ф�', 'user2@naver.com','010-2222-3333','chanmi','�մ���','1234'); 

INSERT INTO cheat (ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd)
VALUES ((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM cheat), '��ǰ ���°� �����̿���', '���ºҷ�','��⵵','2016-09-27',  50000, 'badid', '010-1234-5678', 'BADID@daum.net', 0, '���� �ԱݿϷ��߾��. ������ �����Ǿ���� �Ф�', 'user2@naver.com','010-2222-3333','chanmi','�մ���','1234'); 

/** �˻� SQL */
SELECT ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd, wdate
   FROM cheat
   WHERE title LIKE '%����%' AND CONTENT LIKE '%�Ա�%'  AND nickname LIKE '%' 
   ORDER BY ctno ASC

/**
 * ���� SQL
 */   
UPDATE cheat
      SET title = '���̵� user1441�� �ŷ����� ������', gubun = 'E', region = '��⵵', occurday = '2016-10-01'
          , buyprice = 40000, cheatid = 'user1441', cheattel = '010-2345-5678'
          , cheatemail = 'BADID1@daum.net', cnt = 0, content = '��Ʈ���� �̹����̼��̿���'
          , email = 'user2@naver.com', tel = '010-2222-3333', userid = 'user2', nickname = '�մ���', passwd = '1234'
          , file1 = '1', file2 ='2', size2 = 3
WHERE ctno = 10
AND passwd = '1234'

/**
 ���� SQL
*/
DELETE FROM cheat 
WHERE ctno = 10 

/** ��ȸ�� ���� */
UPDATE cheat
     SET cnt = cnt + 1
WHERE ctno = 10   

/**********************************/
/* Table Name: ������ǰ�Ű� �Խñ� ��� */
/**********************************/
drop table creply
CREATE TABLE creply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    rname                VARCHAR2(30)     NOT NULL,
    passwd               VARCHAR2(10)     NOT NULL,
    wdate                 DATE     DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member_1 (userid),
    FOREIGN KEY (ctno) REFERENCES computer (ctno)
);

COMMENT ON TABLE reply is '������ǰ���';
COMMENT ON COLUMN reply.rno is '��۹�ȣ';
COMMENT ON COLUMN reply.rcomment is '����';
COMMENT ON COLUMN reply.ctno is '�۹�ȣ';
COMMENT ON COLUMN reply.userid is '���̵�';
COMMENT ON COLUMN reply.rname is '����� �г���';
COMMENT ON COLUMN reply.passwd is '��й�ȣ';
COMMENT ON COLUMN reply.wdate is '�������';

INSERT INTO  creply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM creply), '���� �ŷ� �ϼ���', 1, 'master', '���ſ��ϴ»��', '1234', sysdate);

INSERT INTO  creply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM creply), '���� �ŷ� �ϼ���2', 1, 'master', '���ſ��ϴ»��', '1234', sysdate);

INSERT INTO  creply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM creply), '���� �ŷ� �ϼ���3', 2, 'master', '���ſ��ϴ»��', '1234', sysdate);

/** ���� */
DELETE creply WHERE rno = 1 and passwd = '5678'
select * from cheat
 SELECT ctno, title, gubun, region, occurday, buyprice, cheatid, cheattel, cheatemail, cnt, content, email, tel, userid, nickname, passwd, wdate
    FROM cheat
    
    
    SELECT ctno, title, gubun, region, occurday, buyprice, cheatid,
  cheattel, cheatemail, cnt, content, email, tel, userid, nickname,
  passwd, wdate, file1, file2, size2, r
  FROM(
  SELECT ctno, title, gubun, region, occurday, buyprice, cheatid,
  cheattel, cheatemail, cnt, content, email, tel, userid, nickname,
  passwd, wdate, file1, file2, size2, rownum as r
  FROM(
SELECT ctno, title, gubun, region, occurday, buyprice, cheatid,
  cheattel, cheatemail, cnt, content, email, tel, userid, nickname,
  passwd, wdate, file1, file2, size2
  FROM cheat

  ORDER BY ctno ASC
  
  )
  )
  