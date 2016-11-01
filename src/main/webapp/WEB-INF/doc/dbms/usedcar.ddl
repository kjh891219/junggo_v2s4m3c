-----------------------------------------------------------
 SELECT wdate
    FROM usedcar
    ORDER BY u_no ASC

-----------------------------------------------------------

/**********************************/
/* Table Name: ȸ��+������    */
/**********************************/
DROP TABLE member
DROP TABLE usedcar
DROP TABLE c_comment
DELETE * FROM usedcar
commit

CREATE TABLE member(
    userid                            VARCHAR2(20)     NOT NULL PRIMARY KEY,   -- ���̵�
    mno                               NUMBER(6)        NOT NULL UNIQUE,        -- ȸ�� ��ȣ
    pwd                               VARCHAR2(30)     NOT NULL,               -- ��й�ȣ
    name                              VARCHAR2(20)     NOT NULL,               -- �̸�
    nickname                          VARCHAR2(20)     NOT NULL UNIQUE,        -- �г���
    tel                               VARCHAR2(14)     NOT NULL,               -- ��ȭ��ȣ
    zipcode                           VARCHAR2(5)          NULL,               -- �����ȣ
    address1                          VARCHAR2(80)         NULL,               -- �⺻ �ּ�
    address2                          VARCHAR2(50)         NULL,               -- �� �ּ�
    email                             VARCHAR2(100)    NOT NULL UNIQUE,        -- �̸���
    mdate                             DATE             NOT NULL,               -- ������
    auth                              VARCHAR2(20)     DEFAULT 'N'   NOT NULL, -- ����Ű
    confirm                           CHAR(1)          DEFAULT 'N'   NOT NULL, -- ���� ����, �̸��� ��ũ Ŭ�� ����, Y:Ŭ��, N:Ŭ������
    act                               CHAR(1)          NOT NULL,               -- ����, M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���
    droupout                          VARCHAR2(1)      DEFAULT 'N'   NOT NULL  -- Ż�� ��û
);


1. �Է�
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '������', '������', 'abc@mail.com', '000-0000-0000', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N')
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('chanmi', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '����', '����', 'chanmi910@naver.com', '000-1111-1111', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N')
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('user1', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '����', '����1', 'test@naver.com', '000-1111-1111', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N')
              
2. ��ȸ
SELECT userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout
FROM member
ORDER BY mno ASC

select * from member;
select * from usedcar;
3. ����
UPDATE member 
SET pwd='0000'
WHERE mno='1'

4. ����
DELETE FROM MEMBER WHERE mno='2'



/**********************************/
/* Table Name: �߰��� �ŷ� �Խ��� */
/**********************************/
DROP TABLE usedcar;
CREATE TABLE usedcar(
		u_no                          		NUMBER(10)		                                 NOT NULL   PRIMARY KEY,
		nickname                      	VARCHAR2(20)		                               NOT NULL,
		passwd                        		VARCHAR2(20)		                               NOT NULL,
		category                      		CHAR(20)		                                     NOT NULL,
		deal_way                      		VARCHAR2(20)		                               NOT NULL,
		deal_code                     		VARCHAR2(20)		                               NOT NULL,
		product_code                  	VARCHAR2(20)	            	                   NOT NULL,
		h_price                       		NUMBER(15)		   DEFAULT 0		             NOT NULL,
		region                        		VARCHAR2(20)		                               NOT NULL,
		tel                           		  VARCHAR2(14)		                               NOT NULL,
		email                         		  VARCHAR2(100)		                             NOT NULL,
		quantity                      		NUMBER(6)		     DEFAULT 0		             NOT NULL,
		title                         		    VARCHAR2(200)		                             NOT NULL,
		content                       		VARCHAR2(4000)		                           NOT NULL,
    thumb                            VARCHAR2(100)                                      NULL ,
    file1                               VARCHAR2(50)                                        NULL,
    size1                               NUMBER(9)          DEFAULT 0                   NULL,
    file2                              VARCHAR2(50)                                         NULL,
    size2                               NUMBER(9)          DEFAULT 0                   NULL,
    file3                               VARCHAR2(50)                                        NULL,
    size3                               NUMBER(9)          DEFAULT 0                   NULL,
    file4                               VARCHAR2(50)                                        NULL,
    size4                               NUMBER(9)          DEFAULT 0                   NULL,
    file5                               VARCHAR2(50)                                        NULL,
    size5                               NUMBER(9)          DEFAULT 0                   NULL,
		purc_date                     		VARCHAR2(20)		                               NOT NULL,
		userid                        		  VARCHAR2(20)		                               NOT NULL,
		wdate                         		DATE		             DEFAULT sysdate		     NOT NULL,
		u_cnt                         		  NUMBER(6)		     DEFAULT 0		             NOT NULL,
    FOREIGN KEY (userid)        REFERENCES member(userid)
);

SELECT * FROM member
COMMIT
COMMENT ON TABLE usedcar is '�߰��� �ŷ� �Խ���';
COMMENT ON COLUMN usedcar.u_no is '�߰�����ȣ';
COMMENT ON COLUMN usedcar.nickname is '�г���';
COMMENT ON COLUMN usedcar.passwd is '�н�����';
COMMENT ON COLUMN usedcar.category is 'ī�װ�';
COMMENT ON COLUMN usedcar.deal_way is '�ŷ����';
COMMENT ON COLUMN usedcar.deal_code is '�ŷ�����';
COMMENT ON COLUMN usedcar.product_code is '��ǰ����';
COMMENT ON COLUMN usedcar.h_price is '�������';
COMMENT ON COLUMN usedcar.region is '����';
COMMENT ON COLUMN usedcar.tel is '��ȭ��ȣ';
COMMENT ON COLUMN usedcar.email is '�̸���';
COMMENT ON COLUMN usedcar.quantity is '����';
COMMENT ON COLUMN usedcar.title is '����';
COMMENT ON COLUMN usedcar.content is '�󼼼���';
COMMENT ON COLUMN usedcar.purc_date is '���Խñ�';
COMMENT ON COLUMN usedcar.userid is '���̵�';
COMMENT ON COLUMN usedcar.wdate is '�۵����';
COMMENT ON COLUMN usedcar.u_cnt is '��ȸ��';


/**********************************/
/* Table Name: ��� */
/**********************************/
CREATE TABLE c_comment(
		c_no                          		 NUMBER(10)		        NOT NULL,
		nickname                      	VARCHAR2(20)		    NOT NULL,
		c_pwd                         		VARCHAR2(20)		    NOT NULL,
		c_content                     		VARCHAR2(400)		  NOT NULL,
		u_no                          		NUMBER(10)		             NULL,
		PRIMARY KEY(c_no),
  FOREIGN KEY (u_no) REFERENCES usedcar (u_no)
);

COMMENT ON TABLE comments is '���';
COMMENT ON COLUMN comments.c_no is '��۹�ȣ';
COMMENT ON COLUMN comments.c_userid is '���̵�';
COMMENT ON COLUMN comments.c_pwd is '��й�ȣ';
COMMENT ON COLUMN comments.c_content is '����';
COMMENT ON COLUMN comments.u_no is '�߰�����ȣ';

2)����
 --member TABLE ����
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('sol1', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', 'hansol', 'sol', 'test@daum.net', '010-0000-0000', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');

INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('PCM', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', 'chanmi', 'pic', 'test2@daum.net', '010-0000-0001', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');

INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('jae', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', 'jaeheon', 'heon', 'test3@daum.net', '010-0000-0002', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');
              
  --usedcar TABLE ����            
INSERT INTO usedcar(u_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, tel)
VALUES((SELECT NVL(MAX(u_no), 0)+1 as u_no FROM usedcar), 'sol', 
           'sol1', '1234', 'HYUNDAI', '����ŷ�',
            '�˴ϴ�', '�߰�', 40000000, '2016-10-30','����', '������','�߰��� �˴ϴ�.', 'test@daum.net', '010-0000-0000');

INSERT INTO usedcar(u_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, tel)
VALUES((SELECT NVL(MAX(u_no), 0)+1 as u_no FROM usedcar), 'chan', 
           'PCM', '1234', 'KIA', '�ù�',
            '�˴ϴ�', '�߰�', 40000000, '2016-11-1','���', '������','�߰��� �˴ϴ�.', 'test2@daum.net', '010-0000-0001');
            
INSERT INTO usedcar(u_no, nickname, userid, passwd, category, deal_way, 
                             deal_code, product_code, h_price, purc_date, region, title, content, email, tel)
VALUES((SELECT NVL(MAX(u_no), 0)+1 as u_no FROM usedcar), (SELECT nickname FROM member WHERE userid='jae'), 
           'jae', '1234', 'KIA', '�ù�',
            '�˴ϴ�', '�߰�', 40000000, '2016-11-1','���', '������','�߰��� �˴ϴ�.', 'test2@daum.net', '010-0000-0001');                             
            
-- usedcar c_comment TABLE ����            
INSERT INTO c_comment(c_no, nickname, passwd, content, p_no)
VALUES((SELECT NVL(MAX(c_no), 0)+1 as c_no FROM c_comment), 'chan', '111', '���� ���ּ���', '2');


3)��ȸ

SELECT * FROM member;
--usedcar TABLE ��ȸ
SELECT * FROM usedcar;

SELECT * 
FROM usedcar
WHERE u_no = 1;

--c_comment TABLE ��ȸ
SELECT * FROM c_comment;

SELECT * 
FROM c_comment
WHERE c_no = 1;


4)����
--usedcar TABLE ����
DELETE FROM usedcar;

DELETE FROM usedcar
WHERE u_no = 1;

--p_comments TABLE ����
DELETE FROM c_comment;

DELETE FROM c_comment
WHERE c_no = 1;


5)����
ALTER TABLE usedcar;

UPDATE usedcar
SET content='�Ⱦƾ� �մϴ�'
WHERE u_no=1;

6)��ȸ�� ����
UPDATE usedcar
SET u_cnt = u_cnt + 1
WHERE u_no=1
