package dev.mvc.music;

import org.springframework.web.multipart.MultipartFile;

public class MusicVO {
 /** 게시글번호 */
  private int ctno;
  /** 제목 */
  private String title;
  /** 거래구분 */
  private String deal_code;
  /** 신품구분 */
  private String product_code;
  /** 상세 카테고리 */
  private String category;
  /** 지역 */
  private String region;
  /** 거래방법 */
  private String deal_way;
  /** 구입일자 */
  private String purc_date;
  /** 판매수량 */
  private int quantity;
  /** 희망가격 */
  private int hprice;
  /** 내용 */
  private String content;
  /** 조회수 */
  private int cnt;
  /** 닉네임 */
  private String nickname;
  /** 아이디 */
  private String userid;
  /** 비밀번호 */
  private String passwd;
  /** 전화번호 */
  private String tel;
  /** 이메일 */
  private String email;
  /** 등록일자 */
  private String wdate;
  /** 거래상태 */
  private String deal_status;
  /** 썸네일 파일명 */
  private String file1;
  /** 첨부파일명 */
  private String file2;
  /** 첨부파일 사이즈 */
  private long size2 =0;
  /**
   * Spring Framework에서 자동 주입되는 업로드 파일 객체, 실제 컬럼은 존재하지 않음.
   */
  private MultipartFile file2MF;

  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size2Label;
  
  public MusicVO(){}
  public MusicVO(int ctno, String title, String deal_code,
      String product_code, String category, String region, String deal_way,
      String purc_date, int quantity, int hprice, String content, int cnt,
      String nickname, String userid, String passwd, String tel, String email,
      String wdate, String deal_status, String file1, String file2,
      long size2, MultipartFile file2mf, String size2Label) {
    this.ctno = ctno;
    this.title = title;
    this.deal_code = deal_code;
    this.product_code = product_code;
    this.category = category;
    this.region = region;
    this.deal_way = deal_way;
    this.purc_date = purc_date;
    this.quantity = quantity;
    this.hprice = hprice;
    this.content = content;
    this.cnt = cnt;
    this.nickname = nickname;
    this.userid = userid;
    this.passwd = passwd;
    this.tel = tel;
    this.email = email;
    this.wdate = wdate;
    this.deal_status = deal_status;
    this.file1 = file1;
    this.file2 = file2;
    this.size2 = size2;
    this.file2MF = file2mf;
    this.size2Label = size2Label;
  }

  public int getCtno() {
    return ctno;
  }

  public void setCtno(int ctno) {
    this.ctno = ctno;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getDeal_way() {
    return deal_way;
  }

  public void setDeal_way(String deal_way) {
    this.deal_way = deal_way;
  }

  public String getPurc_date() {
    return purc_date;
  }

  public void setPurc_date(String purc_date) {
    this.purc_date = purc_date;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getHprice() {
    return hprice;
  }

  public void setHprice(int hprice) {
    this.hprice = hprice;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
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

  public String getWdate() {
    return wdate;
  }

  public void setWdate(String wdate) {
    this.wdate = wdate;
  }

  public String getDeal_status() {
    return deal_status;
  }

  public void setDeal_status(String deal_status) {
    this.deal_status = deal_status;
  }

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
    this.file2MF = file2mf;
  }

  public String getSize2Label() {
    return size2Label;
  }

  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }

  

}
