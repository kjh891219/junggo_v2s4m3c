/**********************************/
/* Table Name: 예술/문화 */
/**********************************/
DROP TABLE ART
CREATE TABLE ART(
ano               NUMBER(6)                           NOT NULL  PRIMARY KEY, -- 번호
category          VARCHAR2(20)                        NOT NULL, -- 카테고리 
userid            VARCHAR2(20)                        NOT NULL, -- 아이디
nickname          VARCHAR2(20)                        NOT NULL, -- 닉네임
passwd            VARCHAR2(100)                        NOT NULL, -- 비밀번호
deal_way          VARCHAR2(20)                        NOT NULL, -- 거래방식
deal_code         VARCHAR2(20)                        NOT NULL, -- 거래구분
product_code      VARCHAR2(20)                        NOT NULL, -- 상품구분
hprice            NUMBER(15)        DEFAULT 0         NOT NULL, -- 희망가격
region            VARCHAR2(20)      DEFAULT ''        NOT NULL, -- 지역
tel               VARCHAR2(14)      DEFAULT ''        NOT NULL, -- 전화 번호
email             VARCHAR2(100)     DEFAULT ''        NOT NULL, -- 이메일
quantity          NUMBER(6)         DEFAULT 0         NOT NULL, -- 수량
title             VARCHAR2(200)     DEFAULT ''        NOT NULL, -- 제목
content           VARCHAR2(4000)                      NOT NULL, -- 내용
cnt               NUMBER(6)         DEFAULT 0         NOT NULL, -- 조회수
purc_date         VARCHAR2(20)      DEFAULT ''        NOT NULL, -- 구입일
wdate             DATE              DEFAULT sysdate   NOT NULL, -- 등록 일자
thumb             VARCHAR2(100)                      NULL ,
file1             VARCHAR2(50)                       NULL ,
file2             VARCHAR2(50)                       NULL,
file3             VARCHAR2(50)                       NULL,
file4             VARCHAR2(50)                       NULL,
file5             VARCHAR2(50)                       NULL,
size1             NUMBER(9)         DEFAULT 0        NULL,
size2             NUMBER(9)         DEFAULT 0        NULL,
size3             NUMBER(9)         DEFAULT 0        NULL,
size4             NUMBER(9)         DEFAULT 0        NULL,
size5             NUMBER(9)         DEFAULT 0        NULL,
  FOREIGN KEY (userid) REFERENCES member(userid)
);

SELECT thumb, file1, file2, file3, file4, file5 FROM art where ano = 8; 
  
1) 등록

INSERT INTO art(artno, title, deal_code, product_code, REGION, deal_way, deal_state, purc_date, quantity, 
                 hprice, content, cnt, userid, wdate)  
VALUES((SELECT NVL(MAX(artno), 0) + 1 as artno FROM art), '미술 전시회 티켓 팔아요',
        '팝니다', '중고', '서울', '택배', '거래진행중', '2016-03-06', 2, 5000,
        '연락주세요 ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), sysdate);
        
INSERT INTO art(artno, title, deal_code, product_code, REGION, deal_way, deal_state, purc_date, quantity, 
                 hprice, content, cnt, userid, wdate)  
VALUES((SELECT NVL(MAX(artno), 0) + 1 as artno FROM art), '콘서트 티켓 구합니다',
        '구합니다', '세제품', '부산', '택배', '거래완료', '2016-12-02', 2, 30000,
        '에일리 콘서트 티켓 구합니다 ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), sysdate);
        
INSERT INTO art(artno, title, deal_code, product_code, REGION, deal_way, deal_state, purc_date, quantity, 
                 hprice, content, cnt, userid, wdate)  
VALUES((SELECT NVL(MAX(artno), 0) + 1 as artno FROM art), '미술용품 팝니다',
        '팝니다', '새제품', '울산', '택배', '판매중', '2016-03-06', 2, 20000,
        '미개봉 수채화용 붓(3종) 팝니다 연락주세요~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), sysdate);
        

 ARTNO TITLE         DEAL_CODE PRODUCT_CODE REGION DEAL_WAY DEAL_STATE PURC_DATE  QUANTITY HPRICE CONTENT                    CNT USERID WDATE
 ----- ------------- --------- ------------ ------ -------- ---------- ---------- -------- ------ -------------------------- --- ------ ---------------------
     1 미술 전시회 티켓 팔아요 팝니다       중고           서울     택배       거래진행중      2016-03-06        2   5000 연락주세요 ~                      0 chanmi 2016-10-17 12:54:31.0
     2 콘서트 티켓 구합니다   구합니다      세제품          부산     택배       거래완료       2016-12-02        2  30000 에일리 콘서트 티켓 구합니다              0 chanmi 2016-10-17 12:54:32.0
     3 미술용품 팝니다      팝니다       새제품          울산     택배       판매중        2016-03-06        2  20000 미개봉 수채화용 붓(3종) 팝니다 연락주세요~    0 chanmi 2016-10-17 12:55:03.0