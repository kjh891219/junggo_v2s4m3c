drop table cloth;

CREATE TABLE cloth (
clothno              NUMBER(6)  NOT NULL, --글번호 
category        VARCHAR2(20) NOT NULL, --카테고리 
nickname      VARCHAR2(20) NOT NULL, --닉네임 
passwd         VARCHAR2(100) NOT NULL, -- 패스워드 
deal_way       VARCHAR2(20) NOT NULL, -- 거래방식 (직거래, 택배),
deal_code      VARCHAR2(20) NOT NULL, -- 거래구분  (삽니다, 팝니다),
product_code VARCHAR2(20) NOT NULL, -- 상품구분 (중고품, 신상품)  
hprice           NUMBER(15) default 0 NOT NULL,   --희망가격 
region          VARCHAR2(20) NOT NULL, --(서울, 인천...),  지역 
tel               VARCHAR2(18) NOT NULL,  --전화번호 
email           VARCHAR2(100) NOT NULL, --이메일 
quantity       NUMBER(6) default 0 NOT NULL,  --수량 
title             VARCHAR2(200) NOT NULL,  --제목 
content        VARCHAR2(4000) NOT NULL,  --상세설명 
purc_date      VARCHAR(20) NULL, --예)2016년10월 구입시기  
userid           VARCHAR2(20) NOT NULL, --외래키로 설정  아이디 
wdate           DATE default sysdate NOT NULL, --글등록일 
cnt               NUMBER(6) default 0 NOT NULL,  --조회수
file1             VARCHAR(100) NULL,
file2             VARCHAR(50) NULL,
size2            NUMBER(9) DEFAULT 0 NULL,   
file3             VARCHAR(100) NULL,
file4             VARCHAR(50) NULL,
size4            NUMBER(9) DEFAULT 0 NULL,   
file5             VARCHAR(100) NULL,
file6             VARCHAR(50) NULL,
size6            NUMBER(9) DEFAULT 0 NULL,   
file7             VARCHAR(100) NULL,
file8             VARCHAR(50) NULL,
size8            NUMBER(9) DEFAULT 0 NULL,   
file9             VARCHAR(100) NULL,
file10           VARCHAR(50) NULL,
size10          NUMBER(9) DEFAULT 0 NULL, 
FOREIGN KEY (userid) REFERENCES member (userid),
PRIMARY KEY (clothno)
);



DROP TABLE CLOTH;



--입력
INSERT INTO cloth(CLOTHNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(CLOTHNO), 0)+1 as CLOTHNO FROM cloth),
  '남성상의', (select nickname from member where userid = 'master'), 1234, '팝니다', '신상품', '서울', '직거래', 10000,  2015-05-01, 1, '임지환@네이버.컴', 01012341234, '남자옷팔아요', '좋은거싸게사실분', (select userid from member where userid = 'master'));

INSERT INTO cloth(CLOTHNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(CLOTHNO), 0)+1 as CLOTHNO FROM cloth),
  '수입명품', (select nickname from member where userid = 'master'), 1234, '팝니다', '신상품', '울산', '택배', 20000, 2016-05-01, 1, '권재현@네이버.컴', 01056785678, '명품옷팔아요', '좋은거싸게사실분', (select userid from member where userid = 'master'));

INSERT INTO cloth(CLOTHNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(CLOTHNO), 0)+1 as CLOTHNO FROM cloth),
  '학생교복', (select nickname from member where userid = 'master'), 1234, '팝니다', '중고품', '울산', '택배', 20000, 2012-05-01, 1, '권재현@네이버.컴', 01056785552, '교복팔아요', '좋은거싸게사실분',  (select userid from member where userid = 'master'));
  

--조회
SELECT clothno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content
FROM cloth
ORDER BY clothno desc;

--수정
UPDATE cloth
SET       deal_code='삽니다', region='부산', deal_way='직거래'
WHERE clothno = 3;

--삭제
DELETE FROM CLOTH
where clothno = 3;

--조회수 증가
UPDATE cloth
SET cnt = cnt + 1;
WHERE cloth = 1;

-- 검색
-- title
 SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cloth
WHERE clothno=6 AND title LIKE '%ㅋㅋㅋ%'
ORDER BY clothno DESC;
 
-- content
 SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cloth
WHERE clothno=1 AND content LIKE '%휴가%'
ORDER BY cloth DESC;
 
-- 제목과 내용으로 검색
 SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cloth
WHERE clothno=1 AND  title LIKE '%휴가%' OR content LIKE '%휴가%'
ORDER BY clothno DESC;
 
검색 및 전체 레코드 갯수
-- 검색하지 않는 경우
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1;
 
-- title 검색
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1 AND title LIKE '%휴가%';
 
-- content
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1 AND content LIKE '%휴가%';
 
-- 제목과 내용으로 검색
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1 AND  title LIKE '%휴가%' OR content LIKE '%휴가%';

--------------------------------------------------------------------
SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2, wdate, r
FROM(
         SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2, wdate,  rownum as r
         FROM(
                  SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2 , wdate
                  FROM cloth
             
                  ORDER BY clothno DESC
         )
)
WHERE r >=1 AND r <= 3;


   SELECT clothno, category, title, hprice, wdate, region, deal_way, nickname, file1, file2, size2, cnt
            FROM cloth
            