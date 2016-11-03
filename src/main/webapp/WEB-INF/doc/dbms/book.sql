/**********************************/
/* Table Name: ���� */
/**********************************/
DROP table book;
CREATE TABLE BOOK(
      bno                                 NUMBER(6)                      NOT NULL       PRIMARY KEY,
      title                               VARCHAR2(200)   DEFAULT ''     NOT NULL,
      deal_code                           VARCHAR2(20)                   NOT NULL,
      product_code                        VARCHAR2(20)                   NOT NULL,
      category                            VARCHAR2(20)                   NOT NULL,
      region                              VARCHAR2(20)    DEFAULT ''     NOT NULL,
      deal_way                            VARCHAR2(20)                   NOT NULL,
      deal_state                          VARCHAR2(20)                   NOT NULL,
      purc_date                           VARCHAR2(20)    DEFAULT ''     NOT NULL,
      quantity                            NUMBER(6)       DEFAULT 0      NOT NULL,
      hprice                              NUMBER(15)      DEFAULT 0      NOT NULL,
      btitle                              VARCHAR2(200)                  NOT NULL,
      publisher                           VARCHAR2(20)                   NOT NULL,
      bwriter                             VARCHAR2(20)                   NOT NULL, -- �� ��
      content                             VARCHAR2(4000)                 NOT NULL,
      cnt                                 NUMBER(6)      DEFAULT 0       NOT NULL,
      userid                              VARCHAR2(20)                   NOT NULL,
      nickname                          VARCHAR2(20)     NOT NULL,
      tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
      email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
      wdate                               DATE           DEFAULT sysdate NOT NULL,
      thumb                               VARCHAR2(100)  DEFAULT ''      NULL ,
      file1                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file2                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file3                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file4                               VARCHAR2(50)   DEFAULT ''      NULL ,
      file5                               VARCHAR2(50)   DEFAULT ''      NULL ,
      size1                               NUMBER(9)      DEFAULT 0       NULL ,
      size2                               NUMBER(9)      DEFAULT 0       NULL ,
      size3                               NUMBER(9)      DEFAULT 0       NULL ,
      size4                               NUMBER(9)      DEFAULT 0       NULL ,
      size5                               NUMBER(9)      DEFAULT 0       NULL ,
  FOREIGN KEY (userid) REFERENCES member (userid)
);

COMMENT ON TABLE BOOK is '����';
COMMENT ON COLUMN BOOK.BNO is '������ȣ';
COMMENT ON COLUMN BOOK.TITLE is '����';
COMMENT ON COLUMN BOOK.deal_code is '�ŷ�����';
COMMENT ON COLUMN BOOK.product_code is '��ǰ����';
COMMENT ON COLUMN BOOK.category is '��ǰ����';
COMMENT ON COLUMN BOOK.REGION is '����';
COMMENT ON COLUMN BOOK.deal_way is '�ŷ����';
COMMENT ON COLUMN BOOK.deal_state is '�ŷ�����';
COMMENT ON COLUMN BOOK.purc_date is '������';
COMMENT ON COLUMN BOOK.quantity is '����';
COMMENT ON COLUMN BOOK.hprice is '�������';
COMMENT ON COLUMN BOOK.BTILTE is 'å����';
COMMENT ON COLUMN BOOK.publisher is '���ǻ�';
COMMENT ON COLUMN BOOK.Bwriter is '������';
COMMENT ON COLUMN BOOK.CONTENT is '����';
COMMENT ON COLUMN BOOK.cnt is '��ȸ��';
COMMENT ON COLUMN BOOK.userid is '�ۼ���';
COMMENT ON COLUMN BOOK.wdate is '�������';

SELECT * FROM BOOK;

1) ���

INSERT INTO book(bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date, quantity, 
                 hprice, btitle, publisher, bwriter, content, cnt, userid, nickname, tel, email, wdate, thumb, file1, file2, file3, 
                 file4, file5, size1, size2, size3, size4, size5)  
VALUES((SELECT NVL(MAX(bno), 0) + 1 as bno FROM book), '��� å�˴ϴ�',
        '�˴ϴ�', '�߰�', '��̼���','����', '�ù�', '�Ǹ���', '2016-03-06', 2, 5000, '�Ƿηο� �Բ�', '�����', '�ǹ�',
        '�����ּ��� ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), (select nickname FROM member WHERE mno=2),
        (select tel FROM member WHERE mno=2), (select tel FROM member WHERE mno=2), sysdate, '', '', '', '', '', '', 0, 0, 0, 0, 0);
        
INSERT INTO book(bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date, quantity, 
                 hprice, btitle, publisher, bwriter, content, cnt, userid, nickname, tel, email, wdate, thumb, file1, file2, file3, 
                 file4, file5, size1, size2, size3, size4, size5)  
VALUES((SELECT NVL(MAX(bno), 0) + 1 as bno FROM book), 'Ʈ���� ���� ��ϴ�',
        '��ϴ�', '�߰�', '���Ǽ���','�λ�', '�ù�', '�ŷ���', '2016-05-05', 1, 15000, 'Ʈ���� ����', '�ٶ�', '���ǿ���ȸ',
        '�����ּ��� ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), (select nickname FROM member WHERE mno=2),
        (select tel FROM member WHERE mno=2), (select tel FROM member WHERE mno=2), sysdate, '', '', '', '', '', '', 0, 0, 0, 0, 0);
        
INSERT INTO book(bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date, quantity,
                 hprice, btitle, publisher, bwriter, content, cnt, userid, nickname, tel, email, wdate, thumb, file1, file2, file3, 
                 file4, file5, size1, size2, size3, size4, size5)  
VALUES((SELECT NVL(MAX(bno), 0) + 1 as bno FROM book), 'IT å�˴ϴ�',
        '�˴ϴ�', '�߰�', 'IT����','����', '���ŷ�', '�ǸſϷ�', '2016-09-01', 1, 3000, 'JAVA ù����', '�ڹٱ�', 'java',
        '�����ּ��� ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), (select nickname FROM member WHERE mno=2),
        (select tel FROM member WHERE mno=2), (select tel FROM member WHERE mno=2), sysdate, '', '', '', '', '', '', 0, 0, 0, 0, 0);

        
 BNO TITLE      DEAL_CODE PRODUCT_CODE CATEGORY REGION DEAL_WAY DEAL_STATE PURC_DATE  QUANTITY HPRICE BTITLE   PUBLISHER BWRITER CONTENT  CNT USERID NICKNAME TEL           EMAIL         WDATE                 THUMB FILE1 FILE2 FILE3 FILE4 FILE5 SIZE1 SIZE2 SIZE3 SIZE4 SIZE5
 --- ---------- --------- ------------ -------- ------ -------- ---------- ---------- -------- ------ -------- --------- ------- -------- --- ------ -------- ------------- ------------- --------------------- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
   3 IT å�˴ϴ�    �˴ϴ�       �߰�           IT����     ����     ���ŷ�      �ǸſϷ�       2016-09-01        1   3000 JAVA ù���� �ڹٱ�       java    �����ּ��� ~    0 chanmi ����       000-1111-1111 000-1111-1111 2016-10-31 00:24:07.0 NULL  NULL  NULL  NULL  NULL  NULL      0     0     0     0     0
   1 ��� å�˴ϴ�   �˴ϴ�       �߰�           ��̼���    ����     �ù�       �Ǹ���        2016-03-06        2   5000 �Ƿηο� �Բ�  �����       �ǹ�      �����ּ��� ~    0 chanmi ����       000-1111-1111 000-1111-1111 2016-10-31 00:24:05.0 NULL  NULL  NULL  NULL  NULL  NULL      0     0     0     0     0
   2 Ʈ���� ���� ��ϴ� ��ϴ�       �߰�           ���Ǽ���     �λ�     �ù�       �ŷ���        2016-05-05        1  15000 Ʈ���� ����   �ٶ�        ���ǿ���ȸ   �����ּ��� ~    0 chanmi ����       000-1111-1111 000-1111-1111 2016-10-31 00:24:06.0 NULL  NULL  NULL  NULL  NULL  NULL      0     0     0     0     0

2) ��ü ��� ���

SELECT bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date,
                 hprice, cnt, userid, nickname, wdate, thumb
FROM book
ORDER BY bno DESC;

3)ī�װ��� ��� ���

SELECT bno, title, deal_code, product_code, category, region, deal_way, deal_state, purc_date,
                 hprice, cnt, userid, wdate, thumb
FROM book
WHERE category='��̼���'
ORDER BY bno DESC;

4)����

UPDATE book
SET title='IT å�˴ϴ�', deal_code='�˴ϴ�', product_code='�߰�', category='IT����',
   region='����', deal_way='���ŷ�', deal_state='�ǸſϷ�', purc_date='2016-09-01', quantity=1, 
   hprice=3000, btitle='JAVA ù����', publisher='�ڹٱ�', bwriter='java',
        content='�����ּ��� ~ ', thumb='', file1='', file2='', file3='', file4='', file5='', size1=0, size2=0, 
        size3=0, size4=0, size5=0
WHERE bno=3;

5)����

DELETE book;
WHERE bno = 4;


DROP table breply;
CREATE TABLE breply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    bno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
    wdate                 DATE         DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member (userid) on delete cascade,
    FOREIGN KEY (bno) REFERENCES book (bno) on delete cascade
);


INSERT INTO  breply(rno, rcomment, bno, userid, nickname, passwd, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM breply), '���� �ŷ� �ϼ���', 2,
(select userid from member where userid='chanmi') , '������', '1234', sysdate, 1, 1, 1);


SELECT * FROM breply where bno=2;

/** ���� */
DELETE nreply WHERE rno = 1 and passwd = '5678'