drop table cosmetic;

CREATE TABLE cosmetic (
cno              NUMBER(6)  NOT NULL, --�۹�ȣ 
category        VARCHAR2(20) NOT NULL, --ī�װ� 
nickname      VARCHAR2(20) NOT NULL, --�г��� 
passwd         VARCHAR2(20) NOT NULL, -- �н����� 
deal_way       VARCHAR2(20) NOT NULL, -- �ŷ���� (���ŷ�, �ù�),
deal_code      VARCHAR2(20) NOT NULL, -- �ŷ�����  (��ϴ�, �˴ϴ�),
product_code VARCHAR2(20) NOT NULL, -- ��ǰ���� (�߰�ǰ, �Ż�ǰ)  
hprice           NUMBER(15) default 0 NOT NULL,   --������� 
region          VARCHAR2(20) NOT NULL, --(����, ��õ...),  ���� 
tel               VARCHAR2(18) NOT NULL,  --��ȭ��ȣ 
email           VARCHAR2(100) NOT NULL, --�̸��� 
quantity       NUMBER(6) default 0 NOT NULL,  --���� 
title             VARCHAR2(200) NOT NULL,  --���� 
content        VARCHAR2(4000) NOT NULL,  --�󼼼��� 
purc_date      VARCHAR(20) NULL, --��)2016��10�� ���Խñ�  
userid           VARCHAR2(20) NOT NULL, --�ܷ�Ű�� ����  ���̵� 
wdate           DATE default sysdate NOT NULL, --�۵���� 
cnt               NUMBER(6) default 0 NOT NULL,  --��ȸ�� 
PRIMARY KEY (cno),
FOREIGN KEY (userid) REFERENCES member (userid)
);

--�Է�
INSERT INTO cosmetic(cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(cno), 0)+1 as cno FROM cosmetic),
  '����ȭ��ǰ', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '����', '���ŷ�', 10000,  2015-05-01, 1, '����ȯ@���̹�.��', 01012341234, 'ȭ��ǰ�Ⱦƿ�', '�����ŽΰԻ�Ǻ�', (select userid from member where userid = 'master'));

INSERT INTO cosmetic(cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(cno), 0)+1 as cno FROM cosmetic),
  '����ȭ��ǰ', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '���', '�ù�', 20000, 2016-05-01, 1, '������@���̹�.��', 01056785678, 'ȭ��ǰ�Ⱦƿ�', '�����ŽΰԻ�Ǻ�', (select userid from member where userid = 'master'));

INSERT INTO cosmetic(cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(cno), 0)+1 as cno FROM cosmetic),
  '����ȭ��ǰ', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '���', '�ù�', 20000, 2016-05-01, 1, '������@���̹�.��', 01056785678, 'ȭ��ǰ�Ⱦƿ�', '�����ŽΰԻ�Ǻ�', (select userid from member where userid = 'master'));
  
  
SELECT * FROM cosmetic;

drop table cosmetic;

--��ȸ
SELECT cno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content
FROM COSMETIC
ORDER BY cno desc;

 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic

--����
UPDATE cosmetic
SET deal_code='�˴ϴ�', product_code='�߰�ǰ', email='����ȯ@naver.com'
WHERE cno = 1;

--����
DELETE FROM cosmetic
WHERE cno = 1;


--�÷� �߰�
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


--��ȸ�� ����
UPDATE cosmetic
SET cnt = cnt + 1;
WHERE cno = 1;

-- �˻�
-- title
 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic
WHERE cno=6 AND title LIKE '%������%'
ORDER BY cno DESC;
 
-- content
 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic
WHERE cno=1 AND content LIKE '%�ް�%'
ORDER BY cno DESC;
 
-- ����� �������� �˻�
 SELECT cno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cosmetic
WHERE cno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%'
ORDER BY cno DESC;
 
�˻� �� ��ü ���ڵ� ����
-- �˻����� �ʴ� ���
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1;
 
-- title �˻�
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1 AND title LIKE '%�ް�%';
 
-- content
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1 AND content LIKE '%�ް�%';
 
-- ����� �������� �˻�
SELECT COUNT(*) as cnt
FROM cosmetic
WHERE cno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%';

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
            