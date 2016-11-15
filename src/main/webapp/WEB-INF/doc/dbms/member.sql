-- ���̺� �� �����
DROP TABLE usedcar;
DROP TABLE rv_comment;
DROP TABLE review;
DROP TABLE message;
DROP TABLE MEMBER;
DROP TABLE MEMBER CASCADE CONSTRAINTS PURGE;
-----------------------------------------------
UPDATE member 
    SET dropout='Y', act='N'
    WHERE userid='chanmi'
UPDATE member 
    SET dropout='N', act='Y'
    WHERE userid='chanmi'

    UPDATE member 
    SET confirm='Y', act='Y'
    WHERE userid='handam'
    UPDATE member 
    SET confirm='Y', act='Y'
    WHERE userid='sol'
    
SELECT nickname
    FROM member
    WHERE userid NOT IN ('chanmi');
-----------------------------------------------

CREATE TABLE member(
    userid                            VARCHAR2(20)     NOT NULL PRIMARY KEY,   -- ���̵�
    mno                               NUMBER(6)        NOT NULL UNIQUE,        -- ȸ�� ��ȣ
    pwd                               VARCHAR2(100)     NOT NULL,               -- ��й�ȣ
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
    dropout                          VARCHAR2(1)      DEFAULT 'N'   NOT NULL   -- Ż�� ��û
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


DROP TABLE member;
delete from MEMBER
select * from member
SELECT COUNT(userid) as cnt
    FROM member
    WHERE email='test@mail.com'
1. �Է�
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '������', '������', 'abc@mail.com', '000-0000-0000', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N');
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('chanmi', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '������', '����', 'chanmi910@naver.com', '000-1111-1111', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N');
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('user1', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '����', '����1', 'test@naver.com', '000-1111-1111', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N');
INSERT INTO member(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, dropout)
 VALUES('user2', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member), '1234', '����', '����2', 'tes2t@naver.com', '000-1111-1111', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797083', 'Y', 'Y', 'N');
              
2. ��ȸ
SELECT userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout
FROM member
ORDER BY mno ASC

3. ����
UPDATE member 
SET pwd='0000'
WHERE mno='1'

4. ����
DELETE FROM MEMBER WHERE mno='2'