drop table game;
CREATE TABLE GAME(
    gno                               NUMBER(6)    NOT NULL    PRIMARY KEY,
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
    cnt                               NUMBER(6)        DEFAULT 0     NOT NULL,
    lev                               VARCHAR2(15)     NULL,
    genre                             VARCHAR2(10)     NULL ,
    userid                            VARCHAR2(20)     NULL,
    FOREIGN KEY (userid) REFERENCES member (userid)
 
);

DROP table game;
 gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content, 
 purc_date, wdate, cnt, lev, genre, userid
COMMENT ON TABLE GAME is '����';
COMMENT ON COLUMN GAME.gno is '�� ��ȣ';
COMMENT ON COLUMN GAME.category is 'ī�װ�';
COMMENT ON COLUMN GAME.nickname is '�г���';
COMMENT ON COLUMN GAME.passwd is '�н�����';
COMMENT ON COLUMN GAME.deal_way is '�ŷ����';
COMMENT ON COLUMN GAME.deal_code is '�ŷ�����';
COMMENT ON COLUMN GAME.product_code is '��ǰ����';
COMMENT ON COLUMN GAME.hprice is '�������';
COMMENT ON COLUMN GAME.region is '����';
COMMENT ON COLUMN GAME.tel is '��ȭ��ȣ';
COMMENT ON COLUMN GAME.email is '�̸���';
COMMENT ON COLUMN GAME.quantity is '����';
COMMENT ON COLUMN GAME.title is '����';
COMMENT ON COLUMN GAME.content is '�󼼼���';
COMMENT ON COLUMN GAME.purc_date is '���Խñ�';
COMMENT ON COLUMN GAME.wdate is '�����';
COMMENT ON COLUMN GAME.cnt is '��ȸ��, �⺻�� ���';
COMMENT ON COLUMN GAME.lev is '����';
COMMENT ON COLUMN GAME.genre is '�帣';
COMMENT ON COLUMN GAME.userid is '���̵�';


drop table game; 

SELECT gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, cnt, lev, genre, userid
FROM game 
ORDER BY gno ASC;

1��� 
INSERT INTO game(gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, lev, genre, userid)  
VALUES((SELECT NVL(MAX(gno), 0) + 1 as gno FROM game), 'category', 'nickname', 'passwd', 'deal_way',
 'deal_code', 'pruduct_code',  240000, 'region', '010-2312-5123', 'acd@naver.com', 1, 'title', 'content',
  '10��4��', 'fall_m.jpg', 'fall.jpg', 0, 'fall2_m.jpg', 'fall2.jpg', 0,'fall3_m.jpg', 'fall3.jpg', 0,'fall4_m.jpg', 'fall4.jpg', 0,'fall5_m.jpg', 'fall5.jpg', 0,'lev', 'genre', (select userid from member_test where userid='master'));

INSERT INTO game(gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date,file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, lev, genre, userid)  
VALUES((SELECT NVL(MAX(gno), 0) + 1 as gno FROM game), 'ps4', 'ȫ�浿', '1234', '���ŷ�',
 '�˴ϴ�', '�߰�ǰ',  240000, '����', '010-2312-5123', 'acd@naver.com', 1, 'ps4�˴ϴ�', '�ΰ��˴ϴ�',
  '10��4��', 'fall_m.jpg', 'fall.jpg', 0, 'fall2_m.jpg', 'fall2.jpg', 0,'fall3_m.jpg', 'fall3.jpg', 0,'fall4_m.jpg', 'fall4.jpg', 0,'fall5_m.jpg', 'fall5.jpg', 0,'�ڡڡ�', 'RPG', (select userid from member_test where userid='master'));
  
INSERT INTO game(gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, lev, genre, userid)  
VALUES((SELECT NVL(MAX(gno), 0) + 1 as gno FROM game), 'xbox', '�̼���', '1234', '�ù�',
 '�˴ϴ�', '�߰�ǰ',  140000, '��⵵', '010-2312-5123', 'acd@naver.com', 1, 'xbox�˴ϴ�', '�ξư��˴ϴ�',
  '10��3��', 'fall_m.jpg', 'fall.jpg', 0, 'fall2_m.jpg', 'fall2.jpg', 0,'fall3_m.jpg', 'fall3.jpg', 0,'fall4_m.jpg', 'fall4.jpg', 0,'fall5_m.jpg', 'fall5.jpg', 0, '�ڡڡڡ�', '�׼�', (select userid from member_test where userid='master'));  
  
2.��ȸ 
SELECT gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, file1, file2, size2,  file3, size3, file4, size4, file5, size5, file6, size6, cnt, lev, genre, userid
FROM game 
WHERE gno = 1;

3.���� 
UPDATE game 
SET category = 'category2' , nickname = 'nickname2', passwd = 'passwd2', deal_way = 'deal_way2', deal_code = 'deal_code2', 
 product_code = 'product_code2', hprice = 1000, region = '��õ', tel = '2014284092', email = '������@naver.com', quantity = 3, 
 title = 'title2', content = 'content2', purc_date = '2008��', file1='snow_m.jpg', file2='snow_a.jpg', size2='1500',  file3='snow_a1.jpg', size3='1500',  file4='snow_a2.jpg', size4='1500',  file5='snow_a3.jpg', size5='1500',  file6='snow_a4.jpg', size6='1500', lev = 'lev2', genre = 'genre2'
WHERE gno=1;
4.���� 

DELETE FROM game 
WHERE gno = 1;

5.���
SELECT gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, file1, file2, size2, cnt, lev, genre, userid
FROM game 
ORDER BY gno ASC;


DROP TABLE game_reply;
CREATE TABLE game_reply(
    grno                               NUMBER(10)     NOT NULL    PRIMARY KEY,
    nickname                          VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    content                           VARCHAR2(1000)     NOT NULL,
    gno                               NUMBER(10)     NOT NULL,
    userid                            VARCHAR2(20)     NULL ,
  FOREIGN KEY (gno) REFERENCES GAME (gno),
  FOREIGN KEY (userid) REFERENCES member_test (userid)
);
 rno, nickname, passwd, content, gno, userid

COMMENT ON TABLE game reply is '���� ���';
COMMENT ON COLUMN game reply.rno is '��۹�ȣ';
COMMENT ON COLUMN game reply.nickname is '�г���';
COMMENT ON COLUMN game reply.passwd is '��й�ȣ';
COMMENT ON COLUMN game reply.content is '����';
COMMENT ON COLUMN game reply.gno is '�۹�ȣ';
COMMENT ON COLUMN game reply.userid is '���̵�';

1��� 
INSERT INTO game_reply(grno, nickname, passwd, content, gno,userid)  
VALUES((SELECT NVL(MAX(grno), 0) + 1 as grno FROM game_reply), 'nickname','passwd', 'content', 
(select gno from GAME where gno= 1), (select userid from member_test where userid='master'));

2.��ȸ 
SELECT grno, nickname, passwd, content, userid
FROM  game_reply
WHERE grno = 1;

3.���� 
UPDATE game_reply 
SET content = 'content2'
WHERE grno = 1;
4.���� 

DELETE FROM game_reply
WHERE grno = 1;

5.���
SELECT grno, nickname, content,userid
FROM game_reply 
ORDER BY grno ASC;

6.����¡ 

    SELECT gno,  category , nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, file1, file2, size2, cnt, lev, genre, userid, r
    FROM(
       SELECT gno,  category , nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, file1, file2, size2, cnt, lev, genre, userid, rownum as r     
           FROM(
             SELECT gno,  category , nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, file1, file2, size2, cnt, lev, genre, userid
                 FROM game
                        ORDER BY gno DESC
         )
)
WHERE r >=1 AND r <= 3;
 

    SELECT gno,  category , nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, file1, file2, size2, cnt, lev, genre, userid, r
    FROM(
       SELECT gno,  category , nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, file1, file2, size2, cnt, lev, genre, userid, rownum as r     
           FROM(
             SELECT gno,  category , nickname, passwd, deal_way, deal_code, product_code, hprice, region, tel, email, quantity, title, content, purc_date, wdate, file1, file2, size2, cnt, lev, genre, userid
                 FROM game
                                      ORDER BY gno DESC
         )
)    
    WHERE r >= 1 AND r <= 10 
 







