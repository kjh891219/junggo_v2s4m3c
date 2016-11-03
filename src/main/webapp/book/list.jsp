<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="web.tool.*" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
 
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>

<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>
<script>
     function create_login() {
       <% if( session.getAttribute("userid") == null) { %>
       alert('로그인 한 사용자만 이용이 가능합니다');
       window.openModal();
       <%session.setAttribute("url", "book/list.do");%>
       return false;
       <% } else { %>
       location.href='./create.do';
       return true;
       <% } %> 
     }
</script>


<style type="text/css">
   .li:hover{
      background-color:gray;
   }
   li{
      border-top:1px solid gray;
   }
</style>
 
<script type="text/javascript">
</script>
</head>
 
<body leftmargin="0" topmargin="0">
  <jsp:include page="/menu/top.jsp" flush='false' />
<div class="container">
     
  <form name="frm" method="GET" action="./list.do"> 
    <div class='content_menu' style='width: 100%;'>
     <A href='#' onclick="create_login();">글쓰기</A>｜
      <A href="javascript:location.reload();">새로고침</A>
    </div>

      <select name="col"> 
        <option value="">선택</option> 
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
    
  </form> 
  
  <div class="content_from">
    <ul >
      <c:forEach var="vo" items="${list }">
      <li>
      <div class="li" style="margin-top:5px; margin-bottom:5px;">
         <div class='float_l' style="width:20%;">
            <div class='float_l'>${vo.bno}</div>
            <div class='float_l text_c' style="width:90%;" >
              <c:set var='file1' value="${fn:toLowerCase(vo.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' style="max-height:100px;" src='./storage/${vo.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}" >
                  <IMG id='file1'  style="max-height:100px;"  src='./storage/${vo.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file1' style="max-height:100px;"  src='./storage/${vo.file1}'>
                </c:when>
              </c:choose>
            </div>
            <div class='both'></div>
            </div>
         <div class='float_l' style="width:60%;">
            <span>[${vo.deal_code }]</span>
            <span>[${vo.product_code }]</span>
            <strong><a href="./read.do?bno=${vo.bno}">${vo.title }</a></strong>
            <span><img >${vo.deal_state }</span>
            <div>
            <span>카테고리 > ${vo.category }</span>
            </div>
            <span>지역 > ${vo.region }</span>
            <div>
            <span>조회수 : ${vo.cnt } ·</span>
            <span>등록일 : ${vo.wdate.substring(0,10) }</span>
            </div>
         </div>
         <div class='float_l' style="width:20%;">
            <span>
               <strong>${vo.hprice}</strong>
            </span>
               <div>
               <span>${vo.userid }</span>
               </div>
         </div>
         <div class='both'></div>
         </div>
      </li>
      </c:forEach>
    </ul>
   
  </div>
 
  <DIV class='bottom'>${paging}</DIV>
  
     <jsp:include page="/menu/bottom.jsp" flush='false' />     
  </div>
</body>
 
</html>