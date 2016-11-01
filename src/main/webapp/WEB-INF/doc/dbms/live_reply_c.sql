drop table live_reply;
delete from live_reply
select * from live_reply
CREATE TABLE live_reply(
    rno                              NUMBER(6)    NOT NULL    PRIMARY KEY,
    nickname                         VARCHAR2(20) NOT NULL,
    rcomment                         VARCHAR2(1000) NOT NULL,
    rdate                             DATE DEFAULT SYSDATE         NOT NULL,
    grpno                            NUMBER(7)        NOT NULL,
    indent                            NUMBER(2)        DEFAULT 0       NOT NULL,
    ansnum                          NUMBER(5)        DEFAULT 0       NOT NULL
    
);

alter table live_reply add(userid varchar2(20));
alter table live_reply
add constraint FK_live_REPLY_USERID FOREIGN KEY (userid)
REFERENCES member(userid) on delete cascade;

alter table live_reply add(lno number(6));
alter table live_reply
add constraint FK_live_REPLY FOREIGN KEY(lno)
REFERENCES living(lno) on delete cascade;

insert into CAMERA_REPLY(rno, nickname, passwd, rcomment, grpno, indent, ansnum, ctno, userid)
values ((select NVL(MAX(rno),0)+1 as rno from camera_reply),
'°ü¸®ÀÚ', '1234', 'sdfdfdf', (select NVL(MAX(grpno),0)+1 as grpno from camera_reply),
0, 0, 1, 'master');


select * from camera_reply where ctno=3;

select rno, nickname, rcomment, grpno, indent, ansnum, ctno, userid, rownum as r
from (select rno, nickname, rcomment, grpno, indent, ansnum, ctno, userid from CAMERA_REPLY ORDER BY grpno DESC, ansnum ASC)


 

 
 
