CREATE TABLE product(
pno              NUMBER(6)  NOT NULL, --�۹�ȣ 
category        VARCHAR2(20) NOT NULL, --ī�װ� 
nickname      VARCHAR2(20) NOT NULL, --�г��� 
passwd         VARCHAR2(100) NOT NULL, -- �н����� 
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
PRIMARY KEY (pno), 
FOREIGN KEY (userid) REFERENCES member (userid)
);

DROP TABLE product;


--�Է�
INSERT INTO product(PNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(PNO), 0)+1 as PNO FROM product),
  '�����Ź�', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '����', '���ŷ�', 10000,  2015-05-01, 1, '����ȯ@���̹�.��', 010-1234-1234, '�Ź��Ⱦƿ�', '�����ŽΰԻ�Ǻ�',  (select userid from member where userid = 'master'));

INSERT INTO product(pno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(PNO), 0)+1 as PNO FROM product),
  '�Ǽ��縮', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '���', '�ù�', 20000, 2016-05-01, 1, '������@���̹�.��', 01056785678, '�Ǽ��縮�Ⱦƿ�', '�����ŽΰԻ�Ǻ�', (select userid from member where userid = 'master'));

INSERT INTO product(PNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(PNO), 0)+1 as PNO FROM product),
  '����', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '���', '�ù�', 20000, 2016-05-01, 1, '������@���̹�.��', 01056785678, '�����Ⱦƿ�', '�����ŽΰԻ�Ǻ�', (select userid from member where userid = 'master'));


--��ȸ
SELECT pno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content
FROM product
ORDER BY pno desc;

--����
UPDATE product
SET       deal_code='��ϴ�', region='�λ�', deal_way='���ŷ�'
WHERE pno = 3;

--����
DELETE FROM product
where pno = 3;

--��ȸ�� ����
UPDATE product
SET cnt = cnt + 1;
WHERE pno = 1;

-- �˻�
-- title
 SELECT pno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM product
WHERE pno=6 AND title LIKE '%������%'
ORDER BY pno DESC;
 
-- content
 SELECT pno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM product
WHERE pno=1 AND content LIKE '%�ް�%'
ORDER BY pno DESC;
 
-- ����� �������� �˻�
 SELECT pno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM product
WHERE pno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%'
ORDER BY pno DESC;
 
�˻� �� ��ü ���ڵ� ����
-- �˻����� �ʴ� ���
SELECT COUNT(*) as cnt
FROM product
WHERE pno=1;
 
-- title �˻�
SELECT COUNT(*) as cnt
FROM product
WHERE pno=1 AND title LIKE '%�ް�%';
 
-- content
SELECT COUNT(*) as cnt
FROM product
WHERE pno=1 AND content LIKE '%�ް�%';
 
-- ����� �������� �˻�
SELECT COUNT(*) as cnt
FROM product
WHERE pno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%';

--------------------------------------------------------------------
SELECT pno, category, title, hprice, region, deal_way, nickname, file1, file2, size2, wdate, r
FROM(
         SELECT pno, category, title, hprice, region, deal_way, nickname, file1, file2, size2, wdate,  rownum as r
         FROM(
                  SELECT pno, category, title, hprice, region, deal_way, nickname, file1, file2, size2 , wdate
                  FROM product
             
                  ORDER BY pno DESC
         )
)

WHERE r >=1 AND r <= 3;


   SELECT pno, category, title, hprice, wdate, region, deal_way, nickname, file1, file2, size2, cnt
            FROM product
            