CREATE TABLE favorite(
    f_no                                NUMBER(6)                                  NOT NULL,
    nickname                         VARCHAR2(20)                              NOT NULL,
    url                                  VARCHAR2(400)                            NOT NULL,
    title                                VARCHAR2(200)                             NOT NULL,
    userid                             VARCHAR2(20)                               NOT NULL,
    h_price                            NUMBER(15)         DEFAULT 0          NOT NULL,
    thumb                          VARCHAR2(100)                                    NULL ,
    PRIMARY KEY(f_no)
);

alter table favorite
add constraint FK_favorite_USERID FOREIGN KEY(userid)
REFERENCES member(userid);

alter table favorite rename column h_price to hprice;
alter table favorite rename column p_cnt to cnt;

DELETE FROM favorite;
DROP TABLE favorite;
SELECT * FROM favorite

 INSERT INTO favorite(f_no, nickname, url, userid, title)
   VALUES ((SELECT NVL(MAX(f_no), 0)+1 as f_no FROM favorite), 'heon', 'http://localhost:9090/usedcar/carproduct/read.do?p_no=14&col=&word=&nowPage=1' 
                  ,'sol1','dfd')
            commit      
SELECT * FROM FAVORITE f1
WHERE F1.ROWID>(SELECT MIN(ROWID)
FROM FAVORITE
WHERE f1.url=url);

2)리스트 보기
 SELECT  f_no, nickname, url, userid, title,  r
  FROM(
           SELECT f_no, nickname, url, userid, title, rownum as r
           FROM(
                    SELECT f_no, nickname, url, userid, title
                    FROM favorite f1
                   WHERE userid = 'jae' AND F1.ROWID>(SELECT MIN(ROWID)
                   FROM FAVORITE
                   WHERE F1.url=url)
                      ORDER BY f_no DESC
               )
         )


         
select * from favorite;        
select * from carproduct;

         