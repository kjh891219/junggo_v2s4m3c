<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title> 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="./css/style.css" rel="Stylesheet" type="text/css">
<script>
$(document).ready(function(){
   $('.left_list').mouseenter(function(){
     $("#main_left_detail").show();
     /* Toggle('on'); */
   })
   $('#main_left_detail').mouseleave(function(){
     $("#main_left_detail").hide();
   });
   
});
</script>

<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>
<script>
function create_login() {
  <% if( session.getAttribute("userid")== null) { %>
 alert('관리자만 등록이 가능합니다');
  <%session.setAttribute("url", "notice/list.do");%>
      return false;      
  <% } else if(session.getAttribute("userid").equals("master")){ %>
      location.href='./create.do';
      return true;   
  <% } %> 
}
</script>

<style type="text/css">

/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
  *{ 
    font-family: 'Nanum Gothic', serif;
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
  }
  
  a{
   color:white;
  }
  
/* left를 제외한 스타일 */
  body{
   width:80%;
   margin-left:130px;
  }
  
/* top 스타일 */
  
  header{ 
    height: 35px; 
    background-color: #e6e6e6; 
    font-family: 맑은 고딕;  
    text-align: center;
  }
  .member-list {
    margin:5px 8px 0 0;
  
  }
  
 .member-list li {
    float:left;
    list-style: none;
    padding-left:8px;
  }
 .member-list li a {
    font-size:12px;
  }

/* left */  

   /* 로고 */
   #logo {
      width:70px;
      margin:20px auto;
   }
   #logo img {
      width:70px;
   }
   
  #main_left {
    position:fixed; 
    top:0;
    left:0;
  }
  
  #main_left_left{
    width:130px; 
    height:100%;
    float:left;
    color:white;
    background-color: #737373;
  }
  
   #main_left_detail{
      display:none;
      position:absolute;
      left:130px;
      width:130px;
      height:100%;
      
      background-color:#575757;
   }
  
  .left_list_form {
    padding:10px;
  }
  
  .left_list{
    padding-bottom:8px;
  }

/* index 안에 있는 태그 스타일 */

   .container{
      width:100%;
   }
   
   nav ul li {
      list-style:none;
      margin-left: 20px;
   }
   nav {
      margin-top:30px;
   }
   footer{
      text-align: center;
   }
   
</style>

</head> 
 <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
 
 
  <form name="frmSearch" method="get" action="./list.do"> 
    <div class='content_menu' style='width: 100%;'>
      <select name="col"> 
        <option value="">선택</option> 
        <option value="category" ${searchDTO.col == "category" ? "selected=selected" : "" }>카테고리</option>
        <option value="title" ${searchDTO.col == "title" ? "selected=selected" : "" }>제목</option> 
        <option value="content" ${searchDTO.col == "content" ? "selected=selected" : "" }>내용</option> 
        <option value="title_content" ${searchDTO.col == "title_content" ? "selected=selected" : "" }>제목+내용</option> 
        <option value="total" ${searchDTO.col == "" ? "selected=selected" : "" }>전체 목록</option>
      </select>
      <c:choose>
        <c:when test="${searchDTO.col != 'total' }"> <!-- 검색 상태 -->
          <input type="text" name="word" size="15" value="${searchDTO.word }">
        </c:when>
        <c:when test="${searchDTO.col == 'total' }"> <!-- 전체 레코드 -->
          <input type="text" name="word" size="15" value="">
        </c:when>
      </c:choose>
     
      <input type="submit" value="검색">
    </div>
  </form> 


<div class="container" style="margin: auto;">
 <table class="table table-hover" style='width: 100%;'>
    <thead>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>글쓴이</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>조회수</TH>
    <TH class='th'>등록일</TH>
    <c:if test="${(userid eq 'master')}"><TH class='th'>기타</TH></c:if>
  </TR>
 
  </thead>

 
<c:forEach var="vo" items="${list}">

  <TR>
    <TD class='td'>${vo.noticeno}</TD>
    <TD class='td'>${vo.nickname}</TD>
    <TD class='td'><A href="./read.do?noticeno=${vo.noticeno}" style="color: black;">${vo.title}</A></TD>
    <TD class='td'>${vo.cnt}</TD>
    <TD class='td'>${vo.wdate.substring(0,10) }</TD>
    <TD class='td'>
      <c:if test="${(userid eq 'master')}"><A href="./update.do?noticeno=${vo.noticeno}"><IMG src='./images/update.png' title='수정'></A></c:if>
      <c:if test="${(userid eq 'master')}"><A href="./delete.do?noticeno=${vo.noticeno}"><IMG src='./images/delete.png' title='삭제'></A></c:if>
    </TD>
    
  </TR>
</c:forEach>
 
</TABLE>
</div>

<DIV class='bottom'>
  <button type='button' onclick="create_login();">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
</div>



<DIV class='bottom'>${paging}</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 