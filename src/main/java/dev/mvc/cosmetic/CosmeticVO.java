package dev.mvc.cosmetic;

import org.springframework.web.multipart.MultipartFile;

public class CosmeticVO {
 
  /** 글번호 **/
  private int cno;
  /** 소분류 **/
  private String category;
  /** 닉네임 **/
  private String nickname;
  /** 패스워드 **/
  private String passwd;
  /** 거래 구분**/
  private String deal_code;
  /** 상품 구분 **/
  private String product_code;
  /** 지역 **/
  private String region;
  /** 거래 방식 **/
  private String deal_way;
  /** 희망 가격 **/
  private int hprice;
  /** 아이디 **/
  private String userid;
  /** 구입시기 **/
  private String purc_date;
  /** 수량 **/
  private int quantity;
  /** 이메일**/
  private String email;
  /** 연락처 **/
  private String tel;
  /** 글제목 **/
  private String title;
  /** 상세설명 **/
  private String content;
  /** 글등록일 **/
  private String wdate;
  /** 조회수 **/
  private int cnt;
  
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
  private String file1 = "";
  /** 업로드 파일 */
  private String file2 = "";
  /** 업로드된 파일 크기 */
  private long size2 = 0;
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체 
   * 실제 컬럼은 존재하지 않음.
   * */  
  private MultipartFile file2MF;
  
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지않음. */
  private String size2Label;

  private String file3 = "";
  /** 업로드 파일 */
  private String file4 = "";
  /** 업로드된 파일 크기 */
  private long size4 = 0;
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체 
   * 실제 컬럼은 존재하지 않음.
   * */  
  private MultipartFile file4MF;
  
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지않음. */
  private String size4Label;

  private String file5 = "";
  /** 업로드 파일 */
  private String file6 = "";
  /** 업로드된 파일 크기 */
  private long size6 = 0;
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체 
   * 실제 컬럼은 존재하지 않음.
   * */  
  private MultipartFile file6MF;
  
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지않음. */
  private String size6Label;

  private String file7 = "";
  /** 업로드 파일 */
  private String file8 = "";
  /** 업로드된 파일 크기 */
  private long size8 = 0;
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체 
   * 실제 컬럼은 존재하지 않음.
   * */  
  private MultipartFile file8MF;
  
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지않음. */
  private String size8Label;

  private String file9 = "";
  /** 업로드 파일 */
  private String file10 = "";
  /** 업로드된 파일 크기 */
  private long size10 = 0;
  /** Spring Framework에서 자동 주입되는 업로드 파일 객체 
   * 실제 컬럼은 존재하지 않음.
   * */  
  private MultipartFile file10MF;
  
  /** size2의 컴마 저장 출력용 변수, 실제 컬럼은 존재하지않음. */
  private String size10Label;

  
  
  
  public int getCno() {
    return cno;
  }

  public void setCno(int cno) {
    this.cno = cno;
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

  public int getHprice() {
    return hprice;
  }

  public void setHprice(int hprice) {
    this.hprice = hprice;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
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

  public MultipartFile getFile4MF() {
    return file4MF;
  }

  public void setFile4MF(MultipartFile file4mf) {
    file4MF = file4mf;
  }

  public String getSize4Label() {
    return size4Label;
  }

  public void setSize4Label(String size4Label) {
    this.size4Label = size4Label;
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

  public MultipartFile getFile6MF() {
    return file6MF;
  }

  public void setFile6MF(MultipartFile file6mf) {
    file6MF = file6mf;
  }

  public String getSize6Label() {
    return size6Label;
  }

  public void setSize6Label(String size6Label) {
    this.size6Label = size6Label;
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

  public MultipartFile getFile8MF() {
    return file8MF;
  }

  public void setFile8MF(MultipartFile file8mf) {
    file8MF = file8mf;
  }

  public String getSize8Label() {
    return size8Label;
  }

  public void setSize8Label(String size8Label) {
    this.size8Label = size8Label;
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

  public MultipartFile getFile10MF() {
    return file10MF;
  }

  public void setFile10MF(MultipartFile file10mf) {
    file10MF = file10mf;
  }

  public String getSize10Label() {
    return size10Label;
  }

  public void setSize10Label(String size10Label) {
    this.size10Label = size10Label;
  } 
  
  
 
  
}
