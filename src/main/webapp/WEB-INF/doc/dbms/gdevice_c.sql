DROP TABLE GAMEDEVICE;
CREATE TABLE GAMEDEVICE(
    gdno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    category                          VARCHAR2(20)     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    region                            VARCHAR2(20)     NOT NULL,
    tel                               VARCHAR2(14)     NOT NULL,
    email                             VARCHAR2(100)    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    title                             VARCHAR2(200)    NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    purc_date                         VARCHAR2(20)     NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    file1                             VARCHAR2(100)        NULL ,
    file2                             VARCHAR2(50)         NULL ,
    size2                             NUMBER(9)        DEFAULT 0       NULL ,
    file3                             VARCHAR2(100)        NULL ,
    file4                             VARCHAR2(50)         NULL ,
    size4                             NUMBER(9)        DEFAULT 0       NULL ,
    file5                             VARCHAR2(100)        NULL ,
    file6                             VARCHAR2(50)         NULL ,
    size6                             NUMBER(9)        DEFAULT 0       NULL ,
    file7                             VARCHAR2(100)        NULL ,
    file8                             VARCHAR2(50)         NULL ,
    size8                             NUMBER(9)        DEFAULT 0       NULL ,
    file9                             VARCHAR2(100)        NULL ,
    file10                            VARCHAR2(50)         NULL ,
    size10                            NUMBER(9)        DEFAULT 0       NULL ,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
    userid                            VARCHAR2(20)     NULL, 
    FOREIGN KEY (userid) REFERENCES member_test (userid)   
);


DROP TABLE GAMEDEVICE;
COMMENT ON TABLE GAMEDEVICE is '���� ���';
COMMENT ON COLUMN GAMEDEVICE.gno is '�� �Ϸ� ��ȣ';
COMMENT ON COLUMN GAMEDEVICE.category is 'ī�װ�';
COMMENT ON COLUMN GAMEDEVICE.nickname is '�г���';
COMMENT ON COLUMN GAMEDEVICE.passwd is '�н�����';
COMMENT ON COLUMN GAMEDEVICE.deal_way is '�ŷ����';
COMMENT ON COLUMN GAMEDEVICE.deal_code is '�ŷ�����';
COMMENT ON COLUMN GAMEDEVICE.product_code is '��ǰ����';
COMMENT ON COLUMN GAMEDEVICE.hprice is '�������';
COMMENT ON COLUMN GAMEDEVICE.region is '����';
COMMENT ON COLUMN GAMEDEVICE.tel is '��ȭ��ȣ';
COMMENT ON COLUMN GAMEDEVICE.email is '�̸���';
COMMENT ON COLUMN GAMEDEVICE.quantity is '����';
COMMENT ON COLUMN GAMEDEVICE.title is '����';
COMMENT ON COLUMN GAMEDEVICE.content is '�󼼼���';
COMMENT ON COLUMN GAMEDEVICE.purc_date is '���Խñ�';
COMMENT ON COLUMN GAMEDEVICE.wdate is '�����';
COMMENT ON COLUMN GAMEDEVICE.cnt is '��ȸ��, �⺻�� ���';
COMMENT ON COLUMN GAMEDEVICE.userid is '���̵�';

SELECT gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, lev, genre, userid
FROM game 
ORDER BY gno ASC;

1��� 
INSERT INTO gamedevice(gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, userid)  
VALUES((SELECT NVL(MAX(gdno), 0) + 1 as gdno FROM gamedevice), 'category', 'nickname', 'passwd', 'deal_way',
 'deal_code', 'pruduct_code',  240000, 'region', '010-2312-5123', 'acd@naver.com', 1, 'title', 'content',
  '10��4��', sysdate, 0, (select userid from member_test where userid='master'));
INSERT INTO gamedevice(gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, userid)  
VALUES((SELECT NVL(MAX(gdno), 0) + 1 as gdno FROM gamedevice), 'ps4', 'ȫ�浿', '1234', '���ŷ�',
 '�˴ϴ�', '�߰�ǰ',  240000, '����', '010-2312-5123', 'acd@naver.com', 1, 'ps4�˴ϴ�', '�ΰ��˴ϴ�',
  '10��4��', (select userid from member_test where userid='master'));
INSERT INTO gamedevice(gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, userid)  
VALUES((SELECT NVL(MAX(gdno), 0) + 1 as gdno FROM gamedevice), 'xbox', '�Ϸ�', '1234', '���ŷ�',
 '�˴ϴ�', '�߰�ǰ',  240000, '����', '010-2312-5123', 'acd@naver.com', 1, 'xbox', '�ΰ��˴ϴ�',
  '10��4��', (select userid from member_test where userid='master'));  
  
2.��ȸ 
SELECT gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, userid
FROM gamedevice 
WHERE gdno = 1;

3.���� 
UPDATE gamedevice 
SET category = 'category2' , nickname = 'nickname2', passwd = 'passwd2', deal_way = 'deal_way2', deal_code = 'deal_code2', 
 product_code = 'product_code2', hprice = 1000, region = '��õ', tel = '2014284092', email = '������@naver.com', quantity = 3, 
 title = 'title2', content = 'content2', purc_date = '2008��'
WHERE gdno=1;
4.���� 

DELETE FROM gamedevice 
WHERE gdno = 1;

5.���
SELECT gdno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, cnt, userid
FROM gamedevice 
ORDER BY gdno ASC;

drop table gdevice_reply;

CREATE TABLE gdevice_reply(
    gdrno                             NUMBER(10)     NOT NULL    PRIMARY KEY,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    content                           VARCHAR2(1000)     NOT NULL,
    gdno                               NUMBER(6)    NULL ,
    userid                            VARCHAR2(20)     NULL ,
  FOREIGN KEY (gdno) REFERENCES GAMEDEVICE (gdno),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

COMMENT ON TABLE game device reply is '���� ��� ���';
COMMENT ON COLUMN game device reply.rno is '��۹�ȣ';
COMMENT ON COLUMN game device reply.nickname is '�г���';
COMMENT ON COLUMN game device reply.passwd is '��й�ȣ';
COMMENT ON COLUMN game device reply.content is '����';
COMMENT ON COLUMN game device reply.gno is '�� ��ȣ';
COMMENT ON COLUMN game device reply.userid is '���̵�';

1��� 
INSERT INTO gdevice_reply(gdrno, nickname, passwd, content, gdno,userid)  
VALUES((SELECT NVL(MAX(gdrno), 0) + 1 as gdrno FROM gdevice_reply), 'nickname','passwd', 'content', 
(select gdno from GAMEDEVICE where gdno= 1), (select userid from member_test where userid='master'));

2.��ȸ 
SELECT gdrno, nickname, passwd, content, wdate, cnt, userid
FROM  gdevice_reply
WHERE gdrno = 1;

3.���� 
UPDATE gdevice_reply 
SET content = 'content2'
WHERE gdrno = 1;
4.���� 

DELETE FROM gdevice_reply
WHERE gdrno = 1;

5.���
SELECT gdrno, nickname, content, wdate, cnt, userid
FROM gdevice_reply 
ORDER BY gdrno ASC;








