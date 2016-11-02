<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
<!-- ----------------------------------------- -->
<body>
<!-- ----------------------------------------- -->
<div class="container">
<DIV class='title'>문의게시판</DIV>

<TABLE class='table'>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 15%;'/>
    <col style='width: 30%;'/>
    <col style='width: 20%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>분류</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>작성자</TH>
    <TH class='th'>작성일</TH>
    <TH class='th'>관리</TH>
  </TR>
 
   <c:forEach var="vo" items="${idlist }">
  <TR>
    <TD class='td'>${vo.qnano}</TD>
    <%-- <TD class='td'>${vo.sort}</TD> --%>
         <c:choose>
      <c:when test="${vo.categoryno == '1'}">
      <TD class='td'>회원가입 및 로그인</TD>
      </c:when>
      <c:when test="${vo.categoryno == '2'}">
      <TD class='td'>배송문의</TD>
      </c:when>
      <c:when test="${vo.categoryno == '3'}">
      <TD class='td'>기타문의</TD>
      </c:when>
    </c:choose>
    <TD class='td'><A href="./read.do?qnano=${vo.qnano}">${vo.title }</A></TD>
    <TD class='td'>${vo.userid }</TD>
    <TD class='td'>${vo.qdate}</TD>
    <TD class='td'>
      <A href="./passwd.do?qnano=${vo.qnano}"><IMG src='../menu/images/passwd.ico' title='패스워드 변경'></A>
      <A href="./read.do?qnano=${vo.qnano}"><IMG src='../menu/images/update.png' width="16px" title='수정'></A>
      <A href="./delete.do?qnano=${vo.qnano}"><IMG src='../menu/images/delete.png' width="16px" title='삭제'></A>
    </TD>
    
  </TR>
 </c:forEach>
</TABLE>
 
<DIV class='bottom text_r'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<!-- -------------------------------------------- -->
</div>
</body>

<!-- -------------------------------------------- -->
</html> 