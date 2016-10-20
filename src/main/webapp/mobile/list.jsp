<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.mobile.MobileVO" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head>    
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
<!-- ----------------------------------------- -->
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

<DIV class='title'>상품 목록</DIV>
 
<TABLE class='table'>
  <caption>관리자만 접근가능합니다.</caption>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>분류</TH>
    <TH class='th'>통신사</TH>
    <TH class='th'>글쓴이</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>희망가격</TH>
    <TH class='th'>거래방식</TH>
    <TH class='th'>지역</TH>
    <TH class='th'>등록일</TH>
    <TH class='th'>기타</TH>
  </TR>
 
<c:forEach var="mobileVO" items="${list}">

  <TR>
    <TD class='td'>${mobileVO.mno}</TD>
    <TD class='td'>${mobileVO.category}</TD>
    <TD class='td'>${mobileVO.telecome}</TD>
    <TD class='td'><A href="./read.do?mno=${mobileVO.mno}">${mobileVO.nickname}</A></TD>
    <TD class='td'><A href="./read.do?mno=${mobileVO.mno}">${mobileVO.title}</A></TD>
    <TD class='td'>${mobileVO.hprice}</TD>
    <TD class='td'>${mobileVO.deal_way}</TD>
    <TD class='td'>${mobileVO.region}</TD>
    <TD class='td'>${mobileVO.wdate.substring(0,10) }</TD>
    <TD class='td'>
      <A href="./passwd.do?mno=${mobileVO.mno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A>
      <A href="./update.do?mno=${mobileVO.mno}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete.do?mno=${mobileVO.mno}"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
    
  </TR>
</c:forEach>
 
</TABLE>
 
<DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 