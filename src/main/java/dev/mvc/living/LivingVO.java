package dev.mvc.living;

import org.springframework.web.multipart.MultipartFile;

public class LivingVO {

  private int lno; 
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
  private String quantity;
  private String title;
  private String content;
  private String purc_date;
  private String wdate;
  private int cnt;
  private String userid;
  private String file1;
  private String file2;
  private long size2 = 0;
  private String file3;
  private String file4;
  private String file5;
  private String file6;
  private String file7;
  private String file8;
  private String file9;
  private String file10;
  private long size4 = 0;
  private long size6 = 0;
  private long size8 = 0;
  private long size10 = 0;
  private MultipartFile file2MF;
  private MultipartFile file4MF;
  private MultipartFile file6MF;
  private MultipartFile file8MF;
  private MultipartFile file10MF;
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size2Label; 
  private String size4Label; 
  private String size6Label; 
  private String size8Label; 
  private String size10Label; 
  public int getLno() {
    return lno;
  }
  public void setLno(int lno) {
    this.lno = lno;
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
  public String getQuantity() {
    return quantity;
  }
  public void setQuantity(String quantity) {
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

  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getFile1() {
    if (file1 == null){
      file1 = "";
    }  
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
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
  public String getFile3() {
    if (file3 == null){
      file3 = "";
    }  
    return file3;
  }
  public void setFile3(String file3) {
    this.file3 = file3;
  }
  public String getFile4() {
    if (file4 == null){
      file4 = "";
    }  
    return file4;
  }
  public void setFile4(String file4) {
    this.file4 = file4;
  }
  public String getFile5() {
    if (file5 == null){
      file5 = "";
    }  
    return file5;
  }
  public void setFile5(String file5) {
    this.file5 = file5;
  }
  public String getFile6() {
    if (file6 == null){
      file6 = "";
    }  
    return file6;
  }
  public void setFile6(String file6) {
    this.file6 = file6;
  }
  public String getFile7() {
    if (file7 == null){
      file7 = "";
    }  
    return file7;
  }
  public void setFile7(String file7) {
    this.file7 = file7;
  }
  public String getFile8() {
    if (file8 == null){
      file8 = "";
    }  
    return file8;
  }
  public void setFile8(String file8) {
    this.file8 = file8;
  }
  public String getFile9() {
    if (file9 == null){
      file9 = "";
    }  
    return file9;
  }
  public void setFile9(String file9) {
    this.file9 = file9;
  }
  public String getFile10() {
    if (file10 == null){
      file10 = "";
    }  
    return file10;
  }
  public void setFile10(String file10) {
    this.file10 = file10;
  }
  public long getSize4() {
    return size4;
  }
  public void setSize4(long size4) {
    this.size4 = size4;
  }
  public long getSize6() {
    return size6;
  }
  public void setSize6(long size6) {
    this.size6 = size6;
  }
  public long getSize8() {
    return size8;
  }
  public void setSize8(long size8) {
    this.size8 = size8;
  }
  public long getSize10() {
    return size10;
  }
  public void setSize10(long size10) {
    this.size10 = size10;
  }
  public MultipartFile getFile2MF() {
    return file2MF;
  }
  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }
  public MultipartFile getFile4MF() {
    return file4MF;
  }
  public void setFile4MF(MultipartFile file4mf) {
    file4MF = file4mf;
  }
  public MultipartFile getFile6MF() {
    return file6MF;
  }
  public void setFile6MF(MultipartFile file6mf) {
    file6MF = file6mf;
  }
  public MultipartFile getFile8MF() {
    return file8MF;
  }
  public void setFile8MF(MultipartFile file8mf) {
    file8MF = file8mf;
  }
  public MultipartFile getFile10MF() {
    return file10MF;
  }
  public void setFile10MF(MultipartFile file10mf) {
    file10MF = file10mf;
  }
  public String getSize2Label() {
    return size2Label;
  }
  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }
  public String getSize4Label() {
    return size4Label;
  }
  public void setSize4Label(String size4Label) {
    this.size4Label = size4Label;
  }
  public String getSize6Label() {
    return size6Label;
  }
  public void setSize6Label(String size6Label) {
    this.size6Label = size6Label;
  }
  public String getSize8Label() {
    return size8Label;
  }
  public void setSize8Label(String size8Label) {
    this.size8Label = size8Label;
  }
  public String getSize10Label() {
    return size10Label;
  }
  public void setSize10Label(String size10Label) {
    this.size10Label = size10Label;
  }

  
}
