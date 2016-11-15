package dev.mvc.breply;

public class BreplyVO {
   
   /** 댓글 번호 */
   private int rno;
   /** 댓글 내용 */
   private String rcomment;
   /** 게시물 번호 */
   private int bno;
   /** 회원 ID */
   private String userid;
   /** 회원 별명 */
   private String nickname;
   /** 등록일 */
   private String wdate;
   /** 그룹 */
   private int grpno;
   /** 들여쓰기 */
   private int indent;
   /** 그룹안에 댓글 순서 */
   private int ansnum;
   
   public BreplyVO() {
      // TODO Auto-generated constructor stub
   }

   public BreplyVO(int rno, String rcomment, int bno, String userid, String nickname, String passwd, String wdate,
         int grpno, int indent, int ansnum) {
      this.rno = rno;
      this.rcomment = rcomment;
      this.bno = bno;
      this.userid = userid;
      this.nickname = nickname;
      this.wdate = wdate;
      this.grpno = grpno;
      this.indent = indent;
      this.ansnum = ansnum;
   }

   public int getRno() {
      return rno;
   }

   public void setRno(int rno) {
      this.rno = rno;
   }

   public String getRcomment() {
      return rcomment;
   }

   public void setRcomment(String rcomment) {
      this.rcomment = rcomment;
   }

   public int getBno() {
      return bno;
   }

   public void setBno(int bno) {
      this.bno = bno;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }

   public String getNickname() {
      return nickname;
   }

   public void setNickname(String nickname) {
      this.nickname = nickname;
   }

   public String getWdate() {
      return wdate;
   }

   public void setWdate(String wdate) {
      this.wdate = wdate;
   }

   public int getGrpno() {
      return grpno;
   }

   public void setGrpno(int grpno) {
      this.grpno = grpno;
   }

   public int getIndent() {
      return indent;
   }

   public void setIndent(int indent) {
      this.indent = indent;
   }

   public int getAnsnum() {
      return ansnum;
   }

   public void setAnsnum(int ansnum) {
      this.ansnum = ansnum;
   }
   
   
   

}
