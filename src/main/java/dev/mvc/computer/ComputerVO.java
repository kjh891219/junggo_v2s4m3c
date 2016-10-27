package dev.mvc.computer;

import org.springframework.web.multipart.MultipartFile;

public class ComputerVO {
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
  /** 첨부파일정보*/
  /** 썸네일 이미지*/
  private String thumb = "";
  /** 업로드 파일 */
  private String file1 = "";
  /** 업로드된 파일 크기 */
  private long size1 = 0;
  /** 업로드 파일 */
  private String file2 = "";
  /** 업로드된 파일 크기 */
  private long size2 = 0;
  /** 업로드 파일 */
  private String file3 = "";
  /** 업로드된 파일 크기 */
  private long size3 = 0;
  /** 업로드 파일 */
  private String file4 = "";
  /** 업로드된 파일 크기 */
  private long size4 = 0;
  /** 업로드 파일 */
  private String file5 = "";
  /** 업로드된 파일 크기 */
  private long size5 = 0;
  
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체,
  실제 컬럼은 존재하지 않음. */  
  private MultipartFile file1MF;
  private MultipartFile file2MF;
  private MultipartFile file3MF;
  private MultipartFile file4MF;
  private MultipartFile file5MF;
  
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지 않음. */
  private String size1Label; 
  private String size2Label; 
  private String size3Label; 
  private String size4Label; 
  private String size5Label;
  
  public ComputerVO(){}
 

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

  public String getThumb() {
    return thumb;
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  
  public long getSize2() {
    return size2;
  }

  public void setSize2(long size2) {
    this.size2 = size2;
  }
  
  public long getSize3() {
    return size3;
  }
  public void setSize3(long size3) {
    this.size3 = size3;
  }
 
  public long getSize4() {
    return size4;
  }
  public void setSize4(long size4) {
    this.size4 = size4;
  }
  
  public long getSize5() {
    return size5;
  }
  public void setSize5(long size5) {
    this.size5 = size5;
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

  public void setFile2(String file2) {
    this.file2 = file2;
  }

  public String getFile2() {
  if (file2 == null){
   file2 = "";
  }
  return file2;
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
 
 
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public MultipartFile getFile2MF() {
    return file2MF;
  }
  public void setFile2MF(MultipartFile file2mf) {
    file2MF = file2mf;
  }
  public MultipartFile getFile3MF() {
    return file3MF;
  }
  public void setFile3MF(MultipartFile file3mf) {
    file3MF = file3mf;
  }
  public MultipartFile getFile4MF() {
    return file4MF;
  }
  public void setFile4MF(MultipartFile file4mf) {
    file4MF = file4mf;
  }
  public MultipartFile getFile5MF() {
    return file5MF;
  }
  public void setFile5MF(MultipartFile file5mf) {
    file5MF = file5mf;
  }
  public String getSize1Label() {
    return size1Label;
  }
  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
  }
  public String getSize3Label() {
    return size3Label;
  }
  public void setSize3Label(String size3Label) {
    this.size3Label = size3Label;
  }
  public String getSize4Label() {
    return size4Label;
  }
  public void setSize4Label(String size4Label) {
    this.size4Label = size4Label;
  }
  public String getSize5Label() {
    return size5Label;
  }
  public void setSize5Label(String size5Label) {
    this.size5Label = size5Label;
  }
  public String getSize2Label() {
    return size2Label;
  }

  public void setSize2Label(String size2Label) {
    this.size2Label = size2Label;
  }

 
}
