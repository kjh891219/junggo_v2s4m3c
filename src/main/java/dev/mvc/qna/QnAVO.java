package dev.mvc.qna;

import org.springframework.web.multipart.MultipartFile;

public class QnAVO {
   private  int      qnano;
   private  String   title="";
   private  String   content="";
   private  String   qdate="";
   private  String   qfile="";
   private  int      categoryno;
   private  String   userid="";
   
   private MultipartFile file2MF;
   
   private String size2Label; 
   
   
   
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


   public QnAVO(){
      
   }
   

   public QnAVO(int qnano, String title, String content, String qdate, String qfile, int categoryno, String userid) {
      this.qnano = qnano;
      this.title = title;
      this.content = content;
      this.qdate = qdate;
      this.qfile = qfile;
      this.categoryno = categoryno;
      this.userid = userid;
   }


   public String getQdate() {
      return qdate;
   }

   public void setQdate(String qdate) {
      this.qdate = qdate;
   }

   public int getQnano() {
      return qnano;
   }
   public void setQnano(int qnano) {
      this.qnano = qnano;
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
   public String getQfile() {
      return qfile;
   }
   public void setQfile(String qfile) {
      this.qfile = qfile;
   }
   public int getCategoryno() {
      return categoryno;
   }
   public void setCategoryno(int categoryno) {
      this.categoryno = categoryno;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }


   public String getFile2() {
      // TODO Auto-generated method stub
      return null;
   }
   
   
   
}