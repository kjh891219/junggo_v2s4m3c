drop table cloth;

CREATE TABLE cloth (
clothno              NUMBER(6)  NOT NULL, --�۹�ȣ 
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
FOREIGN KEY (userid) REFERENCES member (userid),
PRIMARY KEY (clothno)
);



DROP TABLE CLOTH;



--�Է�
INSERT INTO cloth(CLOTHNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(CLOTHNO), 0)+1 as CLOTHNO FROM cloth),
  '��������', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '����', '���ŷ�', 10000,  2015-05-01, 1, '����ȯ@���̹�.��', 01012341234, '���ڿ��Ⱦƿ�', '�����ŽΰԻ�Ǻ�', (select userid from member where userid = 'master'));

INSERT INTO cloth(CLOTHNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(CLOTHNO), 0)+1 as CLOTHNO FROM cloth),
  '���Ը�ǰ', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�Ż�ǰ', '���', '�ù�', 20000, 2016-05-01, 1, '������@���̹�.��', 01056785678, '��ǰ���Ⱦƿ�', '�����ŽΰԻ�Ǻ�', (select userid from member where userid = 'master'));

INSERT INTO cloth(CLOTHNO, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content, userid)
VALUES((SELECT NVL(MAX(CLOTHNO), 0)+1 as CLOTHNO FROM cloth),
  '�л�����', (select nickname from member where userid = 'master'), 1234, '�˴ϴ�', '�߰�ǰ', '���', '�ù�', 20000, 2012-05-01, 1, '������@���̹�.��', 01056785552, '�����Ⱦƿ�', '�����ŽΰԻ�Ǻ�',  (select userid from member where userid = 'master'));
  

--��ȸ
SELECT clothno, category, nickname, passwd, deal_code, product_code, region, deal_way, hprice, purc_date, quantity, email, tel, title, content
FROM cloth
ORDER BY clothno desc;

--����
UPDATE cloth
SET       deal_code='��ϴ�', region='�λ�', deal_way='���ŷ�'
WHERE clothno = 3;

--����
DELETE FROM CLOTH
where clothno = 3;

--��ȸ�� ����
UPDATE cloth
SET cnt = cnt + 1;
WHERE cloth = 1;

-- �˻�
-- title
 SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cloth
WHERE clothno=6 AND title LIKE '%������%'
ORDER BY clothno DESC;
 
-- content
 SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cloth
WHERE clothno=1 AND content LIKE '%�ް�%'
ORDER BY cloth DESC;
 
-- ����� �������� �˻�
 SELECT clothno, category, title, hprice, region, deal_way, nickname, file1, file2, size2
  FROM cloth
WHERE clothno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%'
ORDER BY clothno DESC;
 
�˻� �� ��ü ���ڵ� ����
-- �˻����� �ʴ� ���
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1;
 
-- title �˻�
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1 AND title LIKE '%�ް�%';
 
-- content
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1 AND content LIKE '%�ް�%';
 
-- ����� �������� �˻�
SELECT COUNT(*) as cnt
FROM cloth
WHERE clothno=1 AND  title LIKE '%�ް�%' OR content LIKE '%�ް�%';

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
            