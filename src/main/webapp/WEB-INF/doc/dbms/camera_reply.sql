drop table camera_reply;
delete from CAMERA_REPLY
select * from CAMERA_REPLY
CREATE TABLE CAMERA_REPLY(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL,
    rdate                             DATE DEFAULT SYSDATE         NOT NULL,
    grpno                            NUMBER(7)        NOT NULL,
    indent                            NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum                          NUMBER(5)        DEFAULT 0       NOT NULL
    
);



alter table CAMERA_REPLY add(userid varchar2(20));
alter table CAMERA_REPLY
add constraint FK_CAMERA_REPLY_USERID FOREIGN KEY(userid)
REFERENCES member(userid) on delete cascade;

alter table CAMERA_REPLY add(ctno number(6));
alter table CAMERA_REPLY
add constraint FK_CAMERA_REPLY FOREIGN KEY(ctno)
REFERENCES CAMERA(ctno) on delete cascade;

insert into CAMERA_REPLY(rno, nickname, passwd, rcomment, grpno, indent, ansnum, ctno, userid)
values ((select NVL(MAX(rno),0)+1 as rno from camera_reply),
'°ü¸®ÀÚ', '1234', 'sdfdfdf', (select NVL(MAX(grpno),0)+1 as grpno from camera_reply),
0, 0, 1, 'master');


select * from camera_reply where ctno=3;

select rno, nickname, rcomment, grpno, indent, ansnum, ctno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, ctno, userid from CAMERA_REPLY ORDER BY grpno DESC, ansnum ASC)


 

 
 

 



