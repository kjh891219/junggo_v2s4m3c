/**********************************/
/* Table Name: 이용 후기 게시판 */
/**********************************/
DROP TABLE reviews

CREATE TABLE reviews(
    r_no                              NUMBER(10)                                     NOT NULL   PRIMARY KEY,
    seller_nick                       VARCHAR2(20)                                   NOT NULL,
    nickname                        VARCHAR2(20)                                   NOT NULL,
    passwd                            VARCHAR2(20)                                   NOT NULL,
    t_category                          CHAR(20)                                         NOT NULL,
    title                                 VARCHAR2(200)                                NOT NULL,
    content                           VARCHAR2(4000)                               NOT NULL,
    thumb                            VARCHAR2(100)                                        NULL,
    file1                               VARCHAR2(50)                                          NULL,
    size1                               NUMBER(9)          DEFAULT 0                      NULL,
    file2                              VARCHAR2(50)                                            NULL,
    size2                               NUMBER(9)          DEFAULT 0                      NULL,
    file3                               VARCHAR2(50)                                           NULL,
    size3                               NUMBER(9)          DEFAULT 0                      NULL,
    file4                               VARCHAR2(50)                                           NULL,
    size4                               NUMBER(9)          DEFAULT 0                      NULL,
    file5                               VARCHAR2(50)                                           NULL,
    size5                               NUMBER(9)          DEFAULT 0                      NULL,
    userid                              VARCHAR2(20)                                   NOT NULL,
    wdate                             DATE                 DEFAULT sysdate         NOT NULL,
    r_cnt                               NUMBER(6)        DEFAULT 0                 NOT NULL,
    FOREIGN KEY (userid)        REFERENCES member(userid)
);

2)조회
SELECT * FROM reviews

  SELECT r_no, seller_nick, nickname, passwd, t_category ,title, content,
                                     userid, file1, file2, size2, file3, file4, size4, file5, file6, size6, file7, file8, size8,
                                     file9, file10, size10, wdate
    FROM reviews
    WHERE r_no=1


4)삭제
-- reviews TABLE 삭제
DELETE FROM reviews;

DELETE FROM reviews
WHERE r_no = 1;

