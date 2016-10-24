
CREATE TABLE member_1_1(
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

select * from member_1
INSERT INTO member_1(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('master', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_1), '1234', '������', '������', 'abc@mail.com', '000-0000-0000', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'M', 'N')
INSERT INTO member_1(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('chanmi', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_1), '1234', '����', '����', 'chanmi910@naver.com', '000-1111-1111', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797084', 'Y', 'Y', 'N')
INSERT INTO member_1(userid, mno, pwd, name, nickname, email, tel, zipcode, address1, address2, mdate, 
                          auth, confirm, act, droupout)
 VALUES('badid', (SELECT NVL(MAX(mno), 0)+1 as mno FROM member_1), '1234', '����1', '�׽�ƮID', 'chanmi9110@naver.com', '000-1111-1111', '12345', '�⺻�ּ�', '���ּ�', sysdate,
              'VIL1476668797083', 'Y', 'Y', 'N')
                            