package dev.mvc.usedcar;


import org.springframework.web.multipart.MultipartFile;


public class UsedcarVO {
  

  /** 중고차번호 */
  private int     u_no;
  /** 닉네임 */
  private String nickname;
  /** 비밀번호 */
  private String passwd;
  /** 아이디 */
  private String userid;
  /** 자동차카테고리코드 */
  private String category;
  /** 거래방식 */
  private String deal_way;
  /** 거래구분코드 */
  private String deal_code;
  /** 상품구분 */
  private String product_code;
  /** 희망가격 */
  private int     h_price;
  /** 거래지역 */
  private String region;
  /** 전화번호 */
  private String tel;
  /** 이메일 */
  private String email;
  /** 수량 */
  private int quantity;
  /** 제목 */
  private String title;
  /** 내용 */
  private String content;
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
  private String file1;
  /** 업로드 파일 */
  private String file2;
  /** 업로드된 파일 크기 */
  private long size2;
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
  private String file3;
  /** 업로드 파일 */
  private String file4;
  /** 업로드된 파일 크기 */
  private long size4;
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
  private String file5;
  /** 업로드 파일 */
  private String file6;
  /** 업로드된 파일 크기 */
  private long size6;
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
  private String file7;
  /** 업로드 파일 */
  private String file8;
  /** 업로드된 파일 크기 */
  private long size8;
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
  private String file9;
  /** 업로드 파일 */
  private String file10;
  /** 업로드된 파일 크기 */
  private long size10;
  /** 구입시기 */
  private String purc_date;
  /** 글등록일 */
  private String wdate;
  /** 조회수 */
  private int     u_cnt;
  
  /** Framework에서 자동 주입되는 업로드 파일 객체 */  
  private MultipartFile file2MF;
  /** size2의 컴마 저장 출력용 변수 */
  private String size2Label; 
  
  
  public String getFile1() {
    return file1;
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getFile2() {
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
  public String getSize2Label() {
    return size2Label;
  }
  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }
  
  
  
  public int getU_no() {
    return u_no;
  }
  public void setU_no(int u_no) {
    this.u_no = u_no;
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
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
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
  public int getH_price() {
    return h_price;
  }
  public void setH_price(int h_price) {
    this.h_price = h_price;
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
  public int getU_cnt() {
    return u_cnt;
  }
  public void setU_cnt(int u_cnt) {
    this.u_cnt = u_cnt;
  }
  public String getFile3() {
    return file3;
  }
  public void setFile3(String file3) {
    this.file3 = file3;
  }
  public String getFile4() {
    return file4;
  }
  public void setFile4(String file4) {
    this.file4 = file4;
  }
  public long getSize4() {
    return size4;
  }
  public void setSize4(long size4) {
    this.size4 = size4;
  }
  public String getFile5() {
    return file5;
  }
  public void setFile5(String file5) {
    this.file5 = file5;
  }
  public String getFile6() {
    return file6;
  }
  public void setFile6(String file6) {
    this.file6 = file6;
  }
  public long getSize6() {
    return size6;
  }
  public void setSize6(long size6) {
    this.size6 = size6;
  }
  public String getFile7() {
    return file7;
  }
  public void setFile7(String file7) {
    this.file7 = file7;
  }
  public String getFile8() {
    return file8;
  }
  public void setFile8(String file8) {
    this.file8 = file8;
  }
  public long getSize8() {
    return size8;
  }
  public void setSize8(long size8) {
    this.size8 = size8;
  }
  public String getFile9() {
    return file9;
  }
  public void setFile9(String file9) {
    this.file9 = file9;
  }
  public String getFile10() {
    return file10;
  }
  public void setFile10(String file10) {
    this.file10 = file10;
  }
  public long getSize10() {
    return size10;
  }
  public void setSize10(long size10) {
    this.size10 = size10;
  }
  


  
}
