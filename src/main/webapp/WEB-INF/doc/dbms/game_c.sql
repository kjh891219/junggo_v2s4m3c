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
COMMENT ON TABLE GAME is '게임';
COMMENT ON COLUMN GAME.gno is '글 번호';
COMMENT ON COLUMN GAME.category is '카테고리';
COMMENT ON COLUMN GAME.nickname is '닉네임';
COMMENT ON COLUMN GAME.passwd is '패스워드';
COMMENT ON COLUMN GAME.deal_way is '거래방식';
COMMENT ON COLUMN GAME.deal_code is '거래구분';
COMMENT ON COLUMN GAME.product_code is '상품구분';
COMMENT ON COLUMN GAME.hprice is '희망가격';
COMMENT ON COLUMN GAME.region is '지역';
COMMENT ON COLUMN GAME.tel is '전화번호';
COMMENT ON COLUMN GAME.email is '이메일';
COMMENT ON COLUMN GAME.quantity is '수량';
COMMENT ON COLUMN GAME.title is '제목';
COMMENT ON COLUMN GAME.content is '상세설명';
COMMENT ON COLUMN GAME.purc_date is '구입시기';
COMMENT ON COLUMN GAME.wdate is '등록일';
COMMENT ON COLUMN GAME.cnt is '조회수, 기본값 사용';
COMMENT ON COLUMN GAME.lev is '평점';
COMMENT ON COLUMN GAME.genre is '장르';
COMMENT ON COLUMN GAME.userid is '아이디';


drop table game; 

SELECT gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, cnt, lev, genre, userid
FROM game 
ORDER BY gno ASC;

1등록 
INSERT INTO game(gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, lev, genre, userid)  
VALUES((SELECT NVL(MAX(gno), 0) + 1 as gno FROM game), 'category', 'nickname', 'passwd', 'deal_way',
 'deal_code', 'pruduct_code',  240000, 'region', '010-2312-5123', 'acd@naver.com', 1, 'title', 'content',
  '10년4월', 'fall_m.jpg', 'fall.jpg', 0, 'fall2_m.jpg', 'fall2.jpg', 0,'fall3_m.jpg', 'fall3.jpg', 0,'fall4_m.jpg', 'fall4.jpg', 0,'fall5_m.jpg', 'fall5.jpg', 0,'lev', 'genre', (select userid from member_test where userid='master'));

INSERT INTO game(gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date,file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, lev, genre, userid)  
VALUES((SELECT NVL(MAX(gno), 0) + 1 as gno FROM game), 'ps4', '홍길동', '1234', '직거래',
 '팝니다', '중고품',  240000, '서울', '010-2312-5123', 'acd@naver.com', 1, 'ps4팝니다', '싸게팝니다',
  '10년4월', 'fall_m.jpg', 'fall.jpg', 0, 'fall2_m.jpg', 'fall2.jpg', 0,'fall3_m.jpg', 'fall3.jpg', 0,'fall4_m.jpg', 'fall4.jpg', 0,'fall5_m.jpg', 'fall5.jpg', 0,'★★★', 'RPG', (select userid from member_test where userid='master'));
  
INSERT INTO game(gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, file1, file2, size2, file3, file4, size4, file5, file6 ,size6, file7, file8, size8, file9, file10, size10, lev, genre, userid)  
VALUES((SELECT NVL(MAX(gno), 0) + 1 as gno FROM game), 'xbox', '이순신', '1234', '택배',
 '팝니다', '중고품',  140000, '경기도', '010-2312-5123', 'acd@naver.com', 1, 'xbox팝니다', '싸아게팝니다',
  '10년3월', 'fall_m.jpg', 'fall.jpg', 0, 'fall2_m.jpg', 'fall2.jpg', 0,'fall3_m.jpg', 'fall3.jpg', 0,'fall4_m.jpg', 'fall4.jpg', 0,'fall5_m.jpg', 'fall5.jpg', 0, '★★★★', '액션', (select userid from member_test where userid='master'));  
  
2.조회 
SELECT gno,  category , nickname, passwd, deal_way, deal_code, 
 product_code, hprice, region, tel, email, quantity, title, content,
 purc_date, wdate, file1, file2, size2,  file3, size3, file4, size4, file5, size5, file6, size6, cnt, lev, genre, userid
FROM game 
WHERE gno = 1;

3.수정 
UPDATE game 
SET category = 'category2' , nickname = 'nickname2', passwd = 'passwd2', deal_way = 'deal_way2', deal_code = 'deal_code2', 
 product_code = 'product_code2', hprice = 1000, region = '인천', tel = '2014284092', email = '후후후@naver.com', quantity = 3, 
 title = 'title2', content = 'content2', purc_date = '2008년', file1='snow_m.jpg', file2='snow_a.jpg', size2='1500',  file3='snow_a1.jpg', size3='1500',  file4='snow_a2.jpg', size4='1500',  file5='snow_a3.jpg', size5='1500',  file6='snow_a4.jpg', size6='1500', lev = 'lev2', genre = 'genre2'
WHERE gno=1;
4.삭제 

DELETE FROM game 
WHERE gno = 1;

5.목록
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

COMMENT ON TABLE game reply is '게임 댓글';
COMMENT ON COLUMN game reply.rno is '댓글번호';
COMMENT ON COLUMN game reply.nickname is '닉네임';
COMMENT ON COLUMN game reply.passwd is '비밀번호';
COMMENT ON COLUMN game reply.content is '내용';
COMMENT ON COLUMN game reply.gno is '글번호';
COMMENT ON COLUMN game reply.userid is '아이디';

1등록 
INSERT INTO game_reply(grno, nickname, passwd, content, gno,userid)  
VALUES((SELECT NVL(MAX(grno), 0) + 1 as grno FROM game_reply), 'nickname','passwd', 'content', 
(select gno from GAME where gno= 1), (select userid from member_test where userid='master'));

2.조회 
SELECT grno, nickname, passwd, content, userid
FROM  game_reply
WHERE grno = 1;

3.수정 
UPDATE game_reply 
SET content = 'content2'
WHERE grno = 1;
4.삭제 

DELETE FROM game_reply
WHERE grno = 1;

5.목록
SELECT grno, nickname, content,userid
FROM game_reply 
ORDER BY grno ASC;

6.페이징 

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
 







