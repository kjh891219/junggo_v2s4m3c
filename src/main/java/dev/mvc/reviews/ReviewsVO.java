package dev.mvc.reviews;

import org.springframework.web.multipart.MultipartFile;

public class ReviewsVO {
  /** 이용후기 글 번호 */
  private int     r_no;
  /** 판매자 닉네임 */
  private String seller_nick;
  /** 닉네임 */
  private String nickname;
  /** 비밀번호 */
  private String passwd;
  /** 전체 카테고리 */
  private String t_category;
  /** 제목 */
  private String title;
  /** 내용 */
  private String content;
  /** 아이디 */
  private String userid;
  
  /** Preview 소형 이미지 200 X 150, 자동 생성됨*/
  private String thumb;
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
  
  /** 글등록일 */
  private String wdate;
  /** 조회수 */
  private int     cnt;
  
  /** Framework에서 자동 주입되는 업로드 파일 객체 */  
  private MultipartFile file1MF;
  /** size1의 컴마 저장 출력용 변수 */
  private String size1Label;
  
  /** Framework에서 자동 주입되는 업로드 파일 객체 */  
  private MultipartFile file2MF;
  /** size2의 컴마 저장 출력용 변수 */
  private String size2Label;
  
  /** Framework에서 자동 주입되는 업로드 파일 객체 */  
  private MultipartFile file3MF;
  /** size3의 컴마 저장 출력용 변수 */
  private String size3Label;
  
  /** Framework에서 자동 주입되는 업로드 파일 객체 */  
  private MultipartFile file4MF;
  /** size4의 컴마 저장 출력용 변수 */
  private String size4Label;
  
  /** Framework에서 자동 주입되는 업로드 파일 객체 */  
  private MultipartFile file5MF;
  /** size5의 컴마 저장 출력용 변수 */
  private String size5Label;
  
  public int getR_no() {
    return r_no;
  }
  public void setR_no(int r_no) {
    this.r_no = r_no;
  }
  public String getSeller_nick() {
    return seller_nick;
  }
  public void setSeller_nick(String seller_nick) {
    this.seller_nick = seller_nick;
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
  public String getT_category() {
    return t_category;
  }
  public void setT_category(String t_category) {
    this.t_category = t_category;
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
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
  }
  public String getThumb() {
    return (thumb != null)?thumb:"";
  }
  public void setThumb(String thumb) {
    this.thumb = thumb;
  }
  public String getFile1() {
    return (file1 != null)?file1:"";
  }
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public long getSize1() {
    return size1;
  }
  public void setSize1(long size1) {
    this.size1 = size1;
  }
  public String getFile2() {
    return (file2 != null)?file2:"";
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
    return (file3 != null)?file3:"";
  }
  public void setFile3(String file3) {
    this.file3 = file3;
  }
  public long getSize3() {
    return size3;
  }
  public void setSize3(long size3) {
    this.size3 = size3;
  }
  public String getFile4() {
    return (file4 != null)?file4:"";
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
    return (file5 != null)?file5:"";
  }
  public void setFile5(String file5) {
    this.file5 = file5;
  }
  public long getSize5() {
    return size5;
  }
  public void setSize5(long size5) {
    this.size5 = size5;
  }
  public String getWdate() {
    return wdate;
  }
  public void setWdate(String wdate) {
    this.wdate = wdate;
  }
  public int getcnt() {
    return cnt;
  }
  public void setcnt(int cnt) {
    this.cnt = cnt;
  }
  public MultipartFile getFile1MF() {
    return file1MF;
  }
  public void setFile1MF(MultipartFile file1mf) {
    file1MF = file1mf;
  }
  public String getSize1Label() {
    return size1Label;
  }
  public void setSize1Label(String size1Label) {
    this.size1Label = size1Label;
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
  public MultipartFile getFile3MF() {
    return file3MF;
  }
  public void setFile3MF(MultipartFile file3mf) {
    file3MF = file3mf;
  }
  public String getSize3Label() {
    return size3Label;
  }
  public void setSize3Label(String size3Label) {
    this.size3Label = size3Label;
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
  public MultipartFile getFile5MF() {
    return file5MF;
  }
  public void setFile5MF(MultipartFile file5mf) {
    file5MF = file5mf;
  }
  public String getSize5Label() {
    return size5Label;
  }
  public void setSize5Label(String size5Label) {
    this.size5Label = size5Label;
  }

  
  
}
