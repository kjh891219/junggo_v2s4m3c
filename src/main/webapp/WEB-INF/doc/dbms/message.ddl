/**********************************/
/* Table Name: ȸ�� */
/**********************************/
CREATE TABLE member(
		userid                        		VARCHAR2(20)		 NOT NULL		 PRIMARY KEY,
		mno                           		NUMBER(6)		 NOT NULL,
		pwd                           		VARCHAR2(30)		 NOT NULL,
		name                          		VARCHAR2(20)		 NOT NULL,
		nickname                      		VARCHAR2(20)		 NULL ,
		tel                           		VARCHAR2(14)		 NOT NULL,
		zipcode                       		VARCHAR2(5)		 NULL ,
		address1                      		VARCHAR2(80)		 NULL ,
		address2                      		VARCHAR2(50)		 NULL ,
		email                         		VARCHAR2(100)		 NOT NULL,
		mdate                         		DATE		 NOT NULL,
		auth                          		VARCHAR2(20)		 DEFAULT N		 NOT NULL,
		confirm                       		CHAR(1)		 DEFAULT N		 NOT NULL,
		act                           		CHAR(1)		 DEFAULT N		 NOT NULL,
		dropout                       		CHAR(1)		 DEFAULT N		 NOT NULL
);

COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.userid is '���̵�';
COMMENT ON COLUMN member.mno is 'ȸ�� ��ȣ';
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
/* Table Name: �޽��� */
/**********************************/
CREATE TABLE message(
    msg_no                            NUMBER(6)         NOT NULL   PRIMARY KEY, -- ��ȣ
    sendid                            VARCHAR2(20)      NOT NULL,               -- ���� ���
    receiveid                         VARCHAR2(20)      NOT NULL,               -- �޴� ���
    title                             VARCHAR2(200)     NOT NULL,               -- ����
    content                           VARCHAR2(4000)    NOT NULL,               -- ����
    msg_date                          DATE              NOT NULL,               -- ���� �ð�
    read_ck                           CHAR(1)           DEFAULT 'N' NOT NULL,   -- ���� ����
    visible                           CHAR(1)           DEFAULT 'Y' NOT NULL,   -- �޽��� ǥ��
  FOREIGN KEY (receiveid) REFERENCES member (userid),
  FOREIGN KEY (sendid)    REFERENCES member (userid)
);
 DROP TABLE message

COMMENT ON TABLE message is '�޽���';
COMMENT ON COLUMN message.msg_no is '��ȣ';
COMMENT ON COLUMN message.sendid is '���� ���';
COMMENT ON COLUMN message.receiveid is '�޴� ���';
COMMENT ON COLUMN message.title is '����';
COMMENT ON COLUMN message.content is '����';
COMMENT ON COLUMN message.msg_date is '���� �ð�';
COMMENT ON COLUMN message.read_ck is '���� ����';


1. �Է�
INSERT INTO message (msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible)
  VALUES ((SELECT NVL(MAX(msg_no), 0)+1 as msg_no FROM message), 
            'master', 'chanmi', '����', '����', sysdate, 'N', 'Y');
INSERT INTO message (msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible)
  VALUES ((SELECT NVL(MAX(msg_no), 0)+1 as msg_no FROM message), 
            'chanmi', 'master', '����', '����2', sysdate, 'N', 'Y');
INSERT INTO message (msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible)
  VALUES ((SELECT NVL(MAX(msg_no), 0)+1 as msg_no FROM message), 
            'master', 'chanmi', '�ȳ��ϼ���', '����2', sysdate, 'N', 'Y');


2. ��ȸ
-- ��ü ��ȸ (�����ڿ�)
	SELECT msg_no, sendid, receiveid, title, content, msg_date, read_ck, visible 
	FROM message
-- ���� �޽��� ��ȸ (chanmi ����)
	SELECT msg_no, sendid, receiveid, title, content, msg_date, read_ck 
	FROM message
	WHERE receiveid = 'chanmi' and visible = 'Y'
-- ���� �޽��� ��ȸ (chanmi ����)
	SELECT msg_no, sendid, receiveid, title, content, msg_date, read_ck 
	FROM message
	WHERE sendid = 'chanmi'
	
3. ����: �޽��� �о��� ��
UPDATE message
SET read_ck = 'Y'
WHERE receiveid = 'chanmi'

4. ����
-- �����ڿ�
DELETE FROM message
WHERE msg_no = '1'

-- ȸ����
UPDATE message
SET visible = 'N'
WHERE receiveid = 'chanmi'

