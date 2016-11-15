<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script type="text/javascript" src="../js/event.js?ver=1"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
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
       <% if( session.getAttribute("userid") == null) { %>
       alert('로그인 한 사용자만 이용이 가능합니다');
       window.openModal();
       <%session.setAttribute("url", "cloth/list.do");%>
       return false;
       <% } else { %>
       location.href='./create.do';
       return true;
       <% } %> 
     }
</script>
<style type="text/css">
   .li:hover{
      background-color:#dcdcdc;
   }
   .content_from li{
      border-top:1px solid gray;
   }
     *{ 
    font-family: dotum,"돋움";
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
    list-style: none;
  }
</style>
 
<script type="text/javascript">
</script>
</head>
 
<body leftmargin="0" topmargin="0" >
  <jsp:include page="/menu/top.jsp" flush='false' />
  <jsp:include page="/menu/left.jsp" flush='false' />
<div class="container">

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



<div class="container" >
 <table class="table table-hover" style='width: 100%;'>
    <thead>
    <TR>
    <TH >글번호</TH>
    <TH >거래구분</TH>
    <TH >제목</TH>
    <TH >희망가격</TH>
    <TH >지역</TH>
    <TH >이미지</TH>
    <TH >날짜</TH>
    <TH >조회수</TH>  
  </TR>
  </thead>
 
<tbody>
<c:forEach var="vo" items="${list }">
  <TR>
    <TD>${vo.clothno}</TD>
    <TD>${vo.deal_code }</TD>
    <TD style="color: black;"><A href="./read.do?clothno=${vo.clothno}" style="color: black;">${vo.title}</A></TD>
    <TD>${vo.hprice}</TD>
    <TD>${vo.region}</TD>
     <td>
            <c:choose>
              <c:when test="${vo.file1 == null}"></c:when>
              <c:when test="${vo.file1 != null}">
                <c:set var='file2' value="${fn:toLowerCase(vo.file1)}" />
                <c:choose>
                  <c:when test="${fn:endsWith(file2, '.jpg')}">
                    <IMG id='file2' src='./storage/${vo.file1}' >
                  </c:when>
                  <c:when test="${fn:endsWith(file2, '.gif')}">
                    <IMG id='file2'  src='./storage/${vo.file1}' >
                  </c:when>
                  <c:when test="${fn:endsWith(file2, '.png')}">
                    <IMG id='file2'  src='./storage/${vo.file1}' >
                  </c:when>
                  <c:otherwise>
                    ${vo.file1}
                  </c:otherwise>
                </c:choose>
                </c:when>
            </c:choose>
            </td>
     <TD>${fn:substring(vo.wdate, 0, 10) }</TD>
     <TD>${vo.cnt}</TD>
  </TR>
</c:forEach>
</tbody>  
</TABLE>

</div>
<div style="text-align: center;">
<button type='button' onclick="create_login()" class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>
</div>

<DIV class='bottom'>${paging}</DIV>

<jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>
<!-- -------------------------------------------- -->
</html> 