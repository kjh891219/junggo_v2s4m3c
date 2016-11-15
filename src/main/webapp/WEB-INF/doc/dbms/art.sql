/**********************************/
/* Table Name: ����/��ȭ */
/**********************************/
DROP TABLE ART
CREATE TABLE ART(
ano               NUMBER(6)                           NOT NULL  PRIMARY KEY, -- ��ȣ
category          VARCHAR2(20)                        NOT NULL, -- ī�װ� 
userid            VARCHAR2(20)                        NOT NULL, -- ���̵�
nickname          VARCHAR2(20)                        NOT NULL, -- �г���
passwd            VARCHAR2(100)                        NOT NULL, -- ��й�ȣ
deal_way          VARCHAR2(20)                        NOT NULL, -- �ŷ����
deal_code         VARCHAR2(20)                        NOT NULL, -- �ŷ�����
product_code      VARCHAR2(20)                        NOT NULL, -- ��ǰ����
hprice            NUMBER(15)        DEFAULT 0         NOT NULL, -- �������
region            VARCHAR2(20)      DEFAULT ''        NOT NULL, -- ����
tel               VARCHAR2(14)      DEFAULT ''        NOT NULL, -- ��ȭ ��ȣ
email             VARCHAR2(100)     DEFAULT ''        NOT NULL, -- �̸���
quantity          NUMBER(6)         DEFAULT 0         NOT NULL, -- ����
title             VARCHAR2(200)     DEFAULT ''        NOT NULL, -- ����
content           VARCHAR2(4000)                      NOT NULL, -- ����
cnt               NUMBER(6)         DEFAULT 0         NOT NULL, -- ��ȸ��
purc_date         VARCHAR2(20)      DEFAULT ''        NOT NULL, -- ������
wdate             DATE              DEFAULT sysdate   NOT NULL, -- ��� ����
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
  
1) ���

INSERT INTO art(artno, title, deal_code, product_code, REGION, deal_way, deal_state, purc_date, quantity, 
                 hprice, content, cnt, userid, wdate)  
VALUES((SELECT NVL(MAX(artno), 0) + 1 as artno FROM art), '�̼� ����ȸ Ƽ�� �Ⱦƿ�',
        '�˴ϴ�', '�߰�', '����', '�ù�', '�ŷ�������', '2016-03-06', 2, 5000,
        '�����ּ��� ~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), sysdate);
        
INSERT INTO art(artno, title, deal_code, product_code, REGION, deal_way, deal_state, purc_date, quantity, 
                 hprice, content, cnt, userid, wdate)  
VALUES((SELECT NVL(MAX(artno), 0) + 1 as artno FROM art), '�ܼ�Ʈ Ƽ�� ���մϴ�',
        '���մϴ�', '����ǰ', '�λ�', '�ù�', '�ŷ��Ϸ�', '2016-12-02', 2, 30000,
        '���ϸ� �ܼ�Ʈ Ƽ�� ���մϴ� ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), sysdate);
        
INSERT INTO art(artno, title, deal_code, product_code, REGION, deal_way, deal_state, purc_date, quantity, 
                 hprice, content, cnt, userid, wdate)  
VALUES((SELECT NVL(MAX(artno), 0) + 1 as artno FROM art), '�̼���ǰ �˴ϴ�',
        '�˴ϴ�', '����ǰ', '���', '�ù�', '�Ǹ���', '2016-03-06', 2, 20000,
        '�̰��� ��äȭ�� ��(3��) �˴ϴ� �����ּ���~ ', 0, (SELECT userid FROM member WHERE userid = 'chanmi'), sysdate);
        

 ARTNO TITLE         DEAL_CODE PRODUCT_CODE REGION DEAL_WAY DEAL_STATE PURC_DATE  QUANTITY HPRICE CONTENT                    CNT USERID WDATE
 ----- ------------- --------- ------------ ------ -------- ---------- ---------- -------- ------ -------------------------- --- ------ ---------------------
     1 �̼� ����ȸ Ƽ�� �Ⱦƿ� �˴ϴ�       �߰�           ����     �ù�       �ŷ�������      2016-03-06        2   5000 �����ּ��� ~                      0 chanmi 2016-10-17 12:54:31.0
     2 �ܼ�Ʈ Ƽ�� ���մϴ�   ���մϴ�      ����ǰ          �λ�     �ù�       �ŷ��Ϸ�       2016-12-02        2  30000 ���ϸ� �ܼ�Ʈ Ƽ�� ���մϴ�              0 chanmi 2016-10-17 12:54:32.0
     3 �̼���ǰ �˴ϴ�      �˴ϴ�       ����ǰ          ���     �ù�       �Ǹ���        2016-03-06        2  20000 �̰��� ��äȭ�� ��(3��) �˴ϴ� �����ּ���~    0 chanmi 2016-10-17 12:55:03.0