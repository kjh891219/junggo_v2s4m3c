/**********************************/
/* Table Name: ��ǻ��/��Ʈ�� */
/**********************************/
drop table computer
delete from computer
select * from computer
select * from nreply
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
    passwd                            VARCHAR2(20)     NOT NULL,
    tel                               VARCHAR2(14)     DEFAULT ''    NOT NULL,
    email                             VARCHAR2(100)    DEFAULT ''    NOT NULL,
    wdate                             DATE     DEFAULT sysdate     NOT NULL,
    deal_status                       VARCHAR2(20)     DEFAULT ''     NOT NULL,
    file1                   VARCHAR2(100)  DEFAULT ''      NULL ,
    file2                   VARCHAR2(50)   DEFAULT ''      NULL ,
    size2                  NUMBER(9)        DEFAULT 0       NULL ,
  PRIMARY KEY (ctno),
  FOREIGN KEY (userid) REFERENCES member_1 (userid)
);
alter table computer
MODIFY   file1  VARCHAR2(100)  DEFAULT ''   
alter table computer
MODIFY   file2  VARCHAR2(100)  DEFAULT ''  

 
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
        , passwd, tel, email, wdate, deal_status, file1, file2, size2)
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '��Ʈ�� �Ǹ��մϴ�','�˴ϴ�', '����ǰ'
, '��Ʈ��', '��⵵', '�ù�', '2015-01-01', 1
, 1000000, '1�� �̸� ����� ���� �ֻ� ��Ʈ���Դϴ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0);

INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, file1, file2, size2)
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '����ũž ���ſ��ؿ�','��ϴ�', '�߰�ǰ'
, '����ũž', '��⵵', '���ŷ�', '2015-01-01', 1
, 1000000, '���� �ֻ� ����ũž ���ؿ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0);

INSERT INTO computer (ctno, title, deal_code, product_code
        , category, region, deal_way, purc_date, quantity
        , hprice, content, cnt, nickname, userid
        , passwd, tel, email, wdate, deal_status, file1, file2, size2)
values((SELECT NVL(MAX(ctno), 0)+1 as ctno FROM computer), '����ũž �Ǹ��մϴ�2','�˴ϴ�', '�߰�ǰ'
, '����ũž', '���', '���ŷ�', '2011-01-01', 1
, 1000000, '5�� �̸� ����� �߰� ����ũž�Դϴ�', 0, '�����Ѱŷ���', 'chanmi'
, '1234', '010-0292-0909','test1@naver.com', sysdate, 'N', '', '', 0);

/** update computer table */
select * from computer
UPDATE computer set title = '����ũž �ŷ���', deal_code = '�ù�', product_code ='�߰�'
        , category = '����ũž', region ='��⵵', deal_way='���ŷ�', purc_date='2016-03-03', quantity=1
        , hprice=800000, content='5�� �̸� �߰� ��ǻ��', cnt = 0, nickname='�մ���', userid ='chanmi'
        , passwd='1234', tel='02-0930-0909', email='test1@google.com', deal_status='Y'
        , file1='', file2='', size2=0
WHERE ctno = 3 and passwd = '4546'

/** ���� */
DELETE computer WHERE ctno = 9 and passwd = '4546'
 
 /**********************************/
/* Table Name: ��ǻ�� �Խñ� ��� */
/**********************************/
 drop table nreply
CREATE TABLE nreply(
    rno                              NUMBER(6)   NOT NULL,  
    rcomment                     VARCHAR2(1000)     DEFAULT ' '    NOT NULL,
    ctno                             NUMBER(6)    NULL ,
    userid                           VARCHAR2(20)     NULL ,
    rname                           VARCHAR2(30)     NOT NULL,
    passwd                          VARCHAR2(10)     NOT NULL,
    wdate                            DATE     DEFAULT sysdate     NOT NULL,
    PRIMARY KEY(rno),
    FOREIGN KEY (userid) REFERENCES member_1 (userid),
    FOREIGN KEY (ctno) REFERENCES computer (ctno)
);

COMMENT ON TABLE reply is '��ǻ�ʹ��';
COMMENT ON COLUMN reply.rno is '��۹�ȣ';
COMMENT ON COLUMN reply.rcomment is '����';
COMMENT ON COLUMN reply.ctno is '�۹�ȣ';
COMMENT ON COLUMN reply.userid is '���̵�';
COMMENT ON COLUMN reply.rname is '����� �г���';
COMMENT ON COLUMN reply.passwd is '��й�ȣ';
COMMENT ON COLUMN reply.wdate is '�������';

INSERT INTO  nreply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM nreply), '���� �ŷ� �ϼ���', 1, 'master', '���ſ��ϴ»��', '1234', sysdate);

INSERT INTO  nreply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM nreply), '���� �ŷ� �ϼ���2', 1, 'master', '���ſ��ϴ»��', '1234', sysdate);

INSERT INTO  nreply (rno, rcomment, ctno, userid, rname, passwd, wdate)
values ((SELECT NVL(MAX(rno), 0)+1 as rno FROM nreply), '���� �ŷ� �ϼ���3', 2, 'master', '���ſ��ϴ»��', '1234', sysdate);

/** ���� */
DELETE nreply WHERE rno = 1 and passwd = '5678'

select * from computer


