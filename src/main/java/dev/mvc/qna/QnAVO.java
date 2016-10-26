package dev.mvc.qna;

import org.springframework.web.multipart.MultipartFile;

public class QnAVO {
   /** ���ǰԽ��� ��ȣ */
   private  int      qnano;
   /** ���ǰԽ��� ���� */
   private  String   title="";
   /** ���ǰԽ��� ���� */
   private  String   content="";
   /** ����� */
   private  String   qdate="";
   /** Preview ���� �̹��� 200 X 150, �ڵ� ������*/
   private  String   file1="";
   /** ���ε� ���� */
   private  String   file2="";
   /** ���ε�� ���� ũ�� */
   private  long     size2= 0;
   /** ī�װ� */
   private  int      categoryno;
   /** ȸ�� ���̵� */
   private  String   userid="";
   
   private MultipartFile file2MF;
   private String size2Label;

   public QnAVO() {
   }

   public QnAVO(int qnano, String title, String content, String qdate, String file1, String file2, long size2,
         int categoryno, String userid, MultipartFile file2mf, String size2Label) {
      this.qnano = qnano;
      this.title = title;
      this.content = content;
      this.qdate = qdate;
      this.file1 = file1;
      this.file2 = file2;
      this.size2 = size2;
      this.categoryno = categoryno;
      this.userid = userid;
      file2MF = file2mf;
      this.size2Label = size2Label;
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

   public String getQdate() {
      return qdate;
   }

   public void setQdate(String qdate) {
      this.qdate = qdate;
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