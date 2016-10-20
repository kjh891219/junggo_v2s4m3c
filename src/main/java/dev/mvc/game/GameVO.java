package dev.mvc.game;

import org.springframework.web.multipart.MultipartFile;

public class GameVO {
//  CREATE TABLE GAME(
//      gno                               NUMBER(6)    NOT NULL    PRIMARY KEY,
//      category                          VARCHAR2(20)     NOT NULL,
//      nickname                          VARCHAR2(20)     NOT NULL,
//      passwd                            VARCHAR2(20)     NOT NULL,
//      deal_way                          VARCHAR2(20)     NOT NULL,
//      deal_code                         VARCHAR2(20)     NOT NULL,
//      product_code                      VARCHAR2(20)     NOT NULL,
//      hprice                            NUMBER(15)     DEFAULT 0     NOT NULL,
//      region                            VARCHAR2(20)     NOT NULL,
//      tel                               VARCHAR2(14)     NOT NULL,
//      email                             VARCHAR2(100)    NOT NULL,
//      quantity                          NUMBER(6)    DEFAULT 0     NOT NULL,
//      title                             VARCHAR2(200)    NOT NULL,
//      content                           VARCHAR2(4000)     NOT NULL,
//      purc_date                         VARCHAR2(20)     NOT NULL,
//      wdate                             DATE     DEFAULT sysdate     NOT NULL,
//      cnt                               NUMBER(6)    DEFAULT 0     NOT NULL,
//      lev                               VARCHAR2(15)     NULL,
//      genre                             VARCHAR2(10)     NULL ,
//      userid                            VARCHAR2(20)     NULL 
//   
   private int gno; 
   private String category; 
   private String nickname;
   private String passwd;
   private String deal_way;
   private String deal_code;
   private String product_code;
   private int hprice; 
   private String region;
   private String tel;
   private String email;
   private int quantity;
   private String title;
   private String content;
   private String purc_date;
   private String wdate;
   private int cnt;
   private String lev;
   private String genre;
   private String userid;
   private String file1;
   private String file2;
   private long size2 = 0;
   
   public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }

  public String getSize2Label() {
    return size2Label;
  }
  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }
 private MultipartFile file2MF;
  
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size2Label; 
    
  public String getFile2() {
    if (file2 == null){
      file2 = "";
    }
    return file2;
  }
  public void setFile2(String file2) {
    this.file2 = file2;
  }
  public long getSize2() {
    return size2;
  }
  public void setSize2(long size2) {
    this.size2 = size2;
  }
  public MultipartFile getFile2MF() {
    return file2MF;
  }
  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }
   
  public int getGno() {
    return gno;
  }
  public void setGno(int gno) {
    this.gno = gno;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getDeal_way() {
    return deal_way;
  }
  public void setDeal_way(String deal_way) {
    this.deal_way = deal_way;
  }
  public String getDeal_code() {
    return deal_code;
  }
  public void setDeal_code(String deal_code) {
    this.deal_code = deal_code;
  }
  public String getProduct_code() {
    return product_code;
  }
  public void setProduct_code(String product_code) {
    this.product_code = product_code;
  }
  public int getHprice() {
    return hprice;
  }
  public void setHprice(int hprice) {
    this.hprice = hprice;
  }
  public String getRegion() {
    return region;
  }
  public void setRegion(String region) {
    this.region = region;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getPurc_date() {
    return purc_date;
  }
  public void setPurc_date(String purc_date) {
    this.purc_date = purc_date;
  }
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getLev() {
    return lev;
  }
  public void setLev(String lev) {
    this.lev = lev;
  }
  public String getGenre() {
    return genre;
  }
  public void setGenre(String genre) {
    this.genre = genre;
  }
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }

}
