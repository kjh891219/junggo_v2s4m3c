/**********************************/
/* Table Name: ��ǻ��/��Ʈ�� */
/**********************************/
drop table computer

CREATE TABLE computer(
    ctno                              NUMBER(6)    NOT NULL,
    title                             VARCHAR2(200)    DEFAULT ''    NULL ,
    deal_code                         VARCHAR2(20)     NOT NULL,
    product_code                      VARCHAR2(20)     NOT NULL,
    category                          VARCHAR2(20)     NOT NULL,
    region                            VARCHAR2(20)     DEFAULT ''    NOT NULL,
    deal_way                          VARCHAR2(20)     NOT NULL,
    purc_date                         VARCHAR2(20)     DEFAULT ''    NOT NULL,
    quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
    hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
    content                           VARCHAR2(4000)     NOT NULL,
    cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
    nickname                          VARCHAR2(20)     NOT NULL,
    userid                            VARCHAR2(20)     NOT NULL,
    passwd                            VARCHAR2(100)     NOT NULL,
    tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    deal_status                       VARCHAR2(20)     DEFAULT ''     NOT NULL,
    thumb                   VARCHAR2(100)        NULL ,
    file1                   VARCHAR2(50)         NULL ,
    size1                   NUMBER(9)        DEFAULT 0       NULL ,
    file2                   VARCHAR2(50)         NULL ,
    size2                   NUMBER(9)        DEFAULT 0       NULL ,
    file3                   VARCHAR2(50)         NULL ,
    size3                   NUMBER(9)        DEFAULT 0       NULL ,
    file4                   VARCHAR2(50)         NULL ,
    size4                   NUMBER(9)        DEFAULT 0       NULL ,
    file5                   VARCHAR2(50)         NULL ,
    size5                   NUMBER(9)        DEFAULT 0       NULL ,
  PRIMARY KEY (ctno),
  FOREIGN KEY (userid) REFERENCES member (userid)
);

drop table computer
 
COMMENT ON TABLE computer is '��ǻ��';
COMMENT ON COLUMN computer.ctno is '�۹�ȣ';
COMMENT ON COLUMN computer.title is '����';
COMMENT ON COLUMN computer.deal_code is '�ŷ�����';
COMMENT ON COLUMN computer.product_code is '��ǰ����';
COMMENT ON COLUMN computer.category is '��ǰ����';
COMMENT ON COLUMN computer.region is '����';
COMMENT ON COLUMN computer.deal_way is '�ŷ����';
COMMENT ON COLUMN computer.purc_date is '������';
COMMENT ON COLUMN computer.quantity is '����';
COMMENT ON COLUMN computer.hprice is '�������';
COMMENT ON COLUMN computer.content is '����';
COMMENT ON COLUMN computer.cnt is '��ȸ��';
COMMENT ON COLUMN computer.nickname  is '�г���';
COMMENT ON COLUMN computer.userid is '���̵�';
COMMENT ON COLUMN computer.passwd is '��й�ȣ';
COMMENT ON COLUMN computer.tel is '��ȭ��ȣ';
COMMENT ON COLUMN computer.email is '�����ּ�';
COMMENT ON COLUMN computer.wdate is '�������';
COMMENT ON COLUMN computer.deal_status is '�ŷ�����';

/** Insert sample data*/
truncate table computer 
INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '��Ʈ�� �Ǹ��մϴ�','�˴ϴ�', '����ǰ'
, '��Ʈ��', '��⵵', '�ù�', '2015-01-01', 1
, 1000000, '1�� �̸� ����� ���� �ֻ� ��Ʈ���Դϴ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '����ũž ���ſ��ؿ�','��ϴ�', '�߰�ǰ'
, '����ũž', '��⵵', '���ŷ�', '2015-01-01', 1
, 1000000, '���� �ֻ� ����ũž ���ؿ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, thumb, file1, size1, file2, size2, file3, size3, file4, size4, file5, size5 )
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '����ũž �Ǹ��մϴ�2','�˴ϴ�', '�߰�ǰ'
, '����ũž', '���', '���ŷ�', '2011-01-01', 1
, 1000000, '5�� �̸� ����� �߰� ����ũž�Դϴ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0,'',0,'',0,'',0,'',0);

/** update computer table */
select * from computer
UPDATE computer set title = '����ũž �ŷ���', deal_code = '�ù�', product_code ='�߰�'
        , category = '����ũž', region ='��⵵', deal_way='���ŷ�', purc_date='2016-03-03', quantity=1
        , hprice=800000, content='5�� �̸� �߰� ��ǻ��', cnt = 0, nickname='�մ���', userid ='chanmi'
        , passwd='1234', tel='02-0930-0909', email='test1@google.com', deal_status='Y'
        , thumb = '', file1='test.txt', size1=0, file2 ='test.txt', size2 = 0, file3 ='test.txt', size3 = 0
        , file4 ='test.txt', size4 = 0, file5 ='test.txt', size5 = 0
WHERE ctno = 10 and passwd = '4546'

/** ���� */
DELETE computer WHERE ctno = 9 and passwd = '4546'
delete computer where ctno=2; 
 /**********************************/
/* Table Name: ��ǻ�� �Խñ� ��� */
/**********************************/
 drop table nreply
CREATE TABLE nreply(
    rno                    NUMBER(6)   NOT NULL,  
    rcomment           VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                   NUMBER(6)    NULL ,
    userid                 VARCHAR2(20)     NULL ,
    nickname                VARCHAR2(30)     NOT NULL,
    passwd               VARCHAR2(100)     NOT NULL,
    wdate                 DATE     DEFAULT sysdate     NOT NULL,
    grpno                 NUMBER(7)        NOT NULL,
    indent                NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum              NUMBER(5)        DEFAULT 0       NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member (userid),
    FOREIGN KEY (ctno) REFERENCES computer (ctno)
);

COMMENT ON TABLE nreply is '��ǻ�ʹ��';
COMMENT ON COLUMN nreply.rno is '��۹�ȣ';
COMMENT ON COLUMN nreply.rcomment is '����';
COMMENT ON COLUMN nreply.ctno is '�۹�ȣ';
COMMENT ON COLUMN nreply.userid is '���̵�';
COMMENT ON COLUMN nreply.nickname is '����� �г���';
COMMENT ON COLUMN nreply.passwd is '��й�ȣ';
COMMENT ON COLUMN nreply.wdate is '�������';

INSERT INTO  nreply (rno, rcomment, ctno, userid, nickname, passwd, wdate, grpno, indent, ansnum)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM nreply), '���� �ŷ� �ϼ���', 1, 'master', '���ſ��ϴ»��', '1234', sysdate, 1, 1, 1);

/** ���� */
DELETE nreply WHERE rno = 1 and passwd = '5678'




