/**********************************/
/* Table Name: ȸ�� */
/**********************************/
DROP TABLE carproduct
DROP TABLE p_comments
DELETE * FROM carproduct
select nickname from member
1. member table ����
CREATE TABLE member(
    userid                             VARCHAR2(20)                           NOT NULL     PRIMARY KEY,
    mno                               NUMBER(6)                               NOT NULL     UNIQUE,
    pwd                                VARCHAR2(30)                          NOT NULL,
    name                              VARCHAR2(20)                           NOT NULL,
    nickname                         VARCHAR2(20)                           NOT NULL     UNIQUE,
    tel                                  VARCHAR2(14)                           NOT NULL,
    zipcode                            VARCHAR2(5)                                   NULL,
    address1                          VARCHAR2(80)                                  NULL,
    address2                          VARCHAR2(50)                                  NULL,
    email                              VARCHAR2(100)                          NOT NULL     UNIQUE,
    mdate                             DATE                                        NOT NULL,
    auth                               VARCHAR2(20)     DEFAULT 'N'      NOT NULL,
    confirm                           CHAR(1)              DEFAULT 'N'      NOT NULL, -- �̸��� ��ũ Ŭ�� ����, Y:Ŭ��, N:Ŭ������
    act                                 CHAR(1)                                     NOT NULL,  -- M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���
    dropout                         VARCHAR2(1)      DEFAULT 'N'       NOT NULL
);

SELECT * FROM member;


COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.mno is 'ȸ�� ��ȣ';
COMMENT ON COLUMN member.userid is '���̵�';
COMMENT ON COLUMN member.pwd is '��й�ȣ';
COMMENT ON COLUMN member.name is '�̸�';
COMMENT ON COLUMN member.nickname is '�г���';
COMMENT ON COLUMN member.tel is '��ȭ��ȣ';
COMMENT ON COLUMN member.zipcode is '�����ȣ';
COMMENT ON COLUMN member.address1 is '�⺻ �ּ�';
COMMENT ON COLUMN member.address2 is '�� �ּ�';
COMMENT ON COLUMN member.email is '�̸���';
COMMENT ON COLUMN member.mdate is '������';
COMMENT ON COLUMN member.auth is '����Ű';
COMMENT ON COLUMN member.confirm is '���� ����';
COMMENT ON COLUMN member.act is '����';
COMMENT ON COLUMN member.dropout is 'Ż�� ��û';



/**********************************/ 
/* Table Name: �ڵ��� ��ǰ �Խ��� */
/**********************************/
CREATE TABLE carproduct(
    p_no                               NUMBER(6)                                  NOT NULL,
    nickname                         VARCHAR2(20)                              NOT NULL,
    passwd                             VARCHAR2(20)                              NOT NULL,
    category                           CHAR(20)                                    NOT NULL,
    deal_way                          VARCHAR2(20)                              NOT NULL,
    deal_code                         VARCHAR2(20)                              NOT NULL,
    product_code                    VARCHAR2(20)                              NOT NULL,
    h_price                              NUMBER(15)         DEFAULT 0         NOT NULL,
    region                             VARCHAR2(20)                              NOT NULL,
    tel                                  VARCHAR2(14)                              NOT NULL ,
    email                              VARCHAR2(100)                             NOT NULL ,
    quantity                          NUMBER(6)          DEFAULT 0           NOT NULL ,
    title                                VARCHAR2(200)                             NOT NULL,
    content                           VARCHAR2(4000)                           NOT NULL,
    thumb                            VARCHAR2(100)                                   NULL ,
    file1                               VARCHAR2(50)                                     NULL,
    size1                               NUMBER(9)          DEFAULT 0                NULL,
    file2                              VARCHAR2(50)                                     NULL,
    size2                               NUMBER(9)          DEFAULT 0                NULL,
    file3                               VARCHAR2(50)                                     NULL,
    size3                               NUMBER(9)          DEFAULT 0                NULL,
    file4                               VARCHAR2(50)                                     NULL,
    size4                               NUMBER(9)          DEFAULT 0                NULL,
    file5                               VARCHAR2(50)                                     NULL,
    size5                               NUMBER(9)          DEFAULT 0                NULL,
    purc_date                         VARCHAR2(20)                             NOT NULL ,
    userid                             VARCHAR2(20)                              NOT NULL,
    wdate                             DATE                  DEFAULT sysdate   NOT NULL,
    p_cnt                              NUMBER(6)          DEFAULT 0           NOT NULL,
    PRIMARY KEY(p_no),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

alter table carproduct rename column h_price to hprice;
alter table carproduct rename column p_cnt to cnt;

SELECT * FROM carproduct;
DELETE * FROM carproduct;

COMMENT ON TABLE carproduct is '�ڵ��� ��ǰ �Խ���';
COMMENT ON COLUMN carproduct.p_no is '��ǰ��ȣ';
COMMENT ON COLUMN carproduct.category is 'ī�װ�';
COMMENT ON COLUMN carproduct.nickname is '�г���';
COMMENT ON COLUMN carproduct.p_pwd is '�н�����';
COMMENT ON COLUMN carproduct.deal_way is '�ŷ����';
COMMENT ON COLUMN carproduct.deal_code is '�ŷ�����';
COMMENT ON COLUMN carproduct.product_code is '��ǰ����';
COMMENT ON COLUMN carproduct.hprice is '�������';
COMMENT ON COLUMN carproduct.region is '����';
COMMENT ON COLUMN carproduct.title is '����';
COMMENT ON COLUMN carproduct.content is '�󼼼���';
COMMENT ON COLUMN carproduct.pruc_date is '���Խñ�';
COMMENT ON COLUMN carproduct.userid is '���̵�';
COMMENT ON COLUMN carproduct.wdate is '�۵����';
COMMENT ON COLUMN carproduct.p_cnt is '��ȸ��';


/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE p_comment(
		c_no                          		  NUMBER(6)		         NOT NULL,
		nickname                       VARCHAR2(20)		     NOT NULL,
		passwd                        		VARCHAR2(20)		     NOT NULL,
		content                     		  VARCHAR2(400)		   NOT NULL,
		p_no                          		NUMBER(6)		                NULL,
		PRIMARY KEY (c_no),
   FOREIGN KEY (p_no) REFERENCES carproduct (p_no)
);

COMMENT ON TABLE comment is '���';
COMMENT ON COLUMN comment.c_no is '��۹�ȣ';
COMMENT ON COLUMN comment.c_userid is '���̵�';
COMMENT ON COLUMN comment.c_pwd is '��й�ȣ';
COMMENT ON COLUMN comment.c_content is '����';
COMMENT ON COLUMN comment.p_no is '��ǰ��ȣ';

2)����
 --carproduct TABLE ����
INSERT INTO carproduct(p_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, quantity ,tel)
VALUES((SELECT NVL(MAX(p_no), 0)+1 as p_no FROM carproduct), 'sol', 
           'sol1', '1234', 'HYUNDAI', '����ŷ�',
            '�˴ϴ�', '�߰�', 1000000, '2016-10-29','����', '����','�����մϴ�.', 'test@daum.net', 0,'010-0000-0000');
            
INSERT INTO carproduct(p_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, quantity, tel)
VALUES((SELECT NVL(MAX(p_no), 0)+1 as p_no FROM carproduct), 'chan', 
           'PCM', '1234', 'KIA', '�ù�',
            '�˴ϴ�', '�Ż�', 400000, '2016-11-1','���', '������ ����','�����ϴ�.', 'test2@daum.net', 5,'010-0000-0001');
            
-- carproduct p_comment TABLE ����            
INSERT INTO p_comment(c_no, nickname, passwd, content, p_no)
VALUES((SELECT NVL(MAX(c_no), 0)+1 as c_no FROM p_comment), 'chan', '111', '���� ���ּ���', '2');

3)��ȸ
--carproduct TABLE ��ȸ
SELECT * FROM carproduct;

SELECT   * 
FROM   carproduct
WHERE p_no=1;

--comments TABLE ��ȸ
SELECT * FROM p_comment;

SELECT   * 
FROM   p_comment
WHERE c_no=1;


4)����
-- carproduct TABLE ����
DELETE FROM carproduct;

DELETE FROM carproduct
WHERE p_no = 1;

--p_comments TABLE ����
DELETE FROM p_comment;

DELETE FROM p_comment
WHERE c_no = 1;

5)����
ALTER TABLE carproduct;

UPDATE carproduct
SET content='�Ⱦƾ� �մϴ�'
WHERE p_no=1;