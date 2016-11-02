drop table cosmetic;

CREATE TABLE cosmetic (
cno              NUMBER(6)  NOT NULL, --글번호 
category        VARCHAR2(20) NOT NULL, --카테고리 
nickname      VARCHAR2(20) NOT NULL, --닉네임 
passwd         VARCHAR2(20) NOT NULL, -- 패스워드 
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
PRIMARY KEY (cno),
FOREIGN KEY (userid) REFERENCES member (userid)
);

--입력
INSERT INTO cosmetic(cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(cno), 0)+1 as cno FROM cosmetic),
  '남자화장품', (select nickname from member where userid = 'master'), 1234, '팝니다', '신상품', '서울', '직거래', 10000,  2015-05-01, 1, '임지환@네이버.컴', 01012341234, '화장품팔아요', '좋은거싸게사실분', (select userid from member where userid = 'master'));

INSERT INTO cosmetic(cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(cno), 0)+1 as cno FROM cosmetic),
  '여성화장품', (select nickname from member where userid = 'master'), 1234, '팝니다', '신상품', '울산', '택배', 20000, 2016-05-01, 1, '권재현@네이버.컴', 01056785678, '화장품팔아요', '좋은거싸게사실분', (select userid from member where userid = 'master'));

INSERT INTO cosmetic(cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(cno), 0)+1 as cno FROM cosmetic),
  '여성화장품', (select nickname from member where userid = 'master'), 1234, '팝니다', '신상품', '울산', '택배', 20000, 2016-05-01, 1, '권재현@네이버.컴', 01056785678, '화장품팔아요', '좋은거싸게사실분', (select userid from member where userid = 'master'));
  
  
SELECT * FROM cosmetic;

drop table cosmetic;

--조회
SELECT cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content
FROM COSMETIC
ORDER BY cno desc;

 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic

--수정
UPDATE cosmetic
SET deal_code='팝니다', product_code='중고품', email='임지환@naver.com'
WHERE cno = 1;

--삭제
DELETE FROM cosmetic
WHERE cno = 1;


--컬럼 추가
ALTER TABLE cosmetic
ADD (file1 VARCHAR(100) NULL);

ALTER TABLE cosmetic
ADD (file2 VARCHAR(50) NULL);

ALTER TABLE cosmetic
ADD (size2 NUMBER(9) DEFAULT 0 NULL);

ALTER TABLE cosmetic
ADD (file3 VARCHAR(100) NULL);

ALTER TABLE cosmetic
ADD (file4 VARCHAR(50) NULL);

ALTER TABLE cosmetic
ADD (size4 NUMBER(9) DEFAULT 0 NULL);

ALTER TABLE cosmetic
ADD (file5 VARCHAR(100) NULL);

ALTER TABLE cosmetic
ADD (file6 VARCHAR(50) NULL);

ALTER TABLE cosmetic
ADD (size6 NUMBER(9) DEFAULT 0 NULL);

ALTER TABLE cosmetic
ADD (file7 VARCHAR(100) NULL);

ALTER TABLE cosmetic
ADD (file8 VARCHAR(50) NULL);

ALTER TABLE cosmetic
ADD (size8 NUMBER(9) DEFAULT 0 NULL);

ALTER TABLE cosmetic
ADD (file9 VARCHAR(100) NULL);

ALTER TABLE cosmetic
ADD (file10 VARCHAR(50) NULL);

ALTER TABLE cosmetic
ADD (size10 NUMBER(9) DEFAULT 0 NULL);


--조회수 증가
UPDATE cosmetic
SET cnt = cnt + 1;
WHERE cno = 1;

-- 검색
-- title
 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic
WHERE cno=6 AND title LIKE '%ㅋㅋㅋ%'
ORDER BY cno DESC;
 
-- content
 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic
WHERE cno=1 AND content LIKE '%휴가%'
ORDER BY cno DESC;
 
-- 제목과 내용으로 검색
 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic
WHERE cno=1 AND  title LIKE '%휴가%' OR content LIKE '%휴가%'
ORDER BY cno DESC;
 
검색 및 전체 레코드 갯수
-- 검색하지 않는 경우
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1;
 
-- title 검색
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1 AND title LIKE '%휴가%';
 
-- content
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1 AND content LIKE '%휴가%';
 
-- 제목과 내용으로 검색
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1 AND  title LIKE '%휴가%' OR content LIKE '%휴가%';

--------------------------------------------------------------------
SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2, wdate, r
FROM(
         SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2, wdate,  rownum as r
         FROM(
                  SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2 , wdate
                  FROM cosmetic
             
                  ORDER BY cno DESC
         )
)
WHERE r >=1 AND r <= 3;


   SELECT cno, category, title, hprice, wdate, region, deal_way, nickname, file1, file2, size2, cnt
            FROM cosmetic
            