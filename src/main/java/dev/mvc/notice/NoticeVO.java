package dev.mvc.notice;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {
    
  private int noticeno; 
  private String nickname;
  private String title;
  private String content;
  private String wdate;
  private int cnt; 
  private String file1;
  private String file2;
  private long size2;
  private String userid;
  private MultipartFile file2MF;
  private String size2Label; 
  
  public int getNoticeno() {
    return noticeno;
  }
  public void setNoticeno(int noticeno) {
    this.noticeno = noticeno;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
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
    if(file1 == null){
      file1 = "";
   
  }
    return file1;
  }
  
  public void setFile1(String file1) {
    this.file1 = file1;
  }
  public String getFile2() {
    if(file2 == null){
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
  public String getUserid() {
    return userid;
  }
  public void setUserid(String userid) {
    this.userid = userid;
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

}
