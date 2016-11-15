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
<link href="${pageContext.request.contextPath}/css/style.css?ver=2" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/event.js?ver=3"></script>
<script>
     function create_login() {
       <% if( session.getAttribute("userid") == null) { %>
       alert('로그인 한 사용자만 이용이 가능합니다');
       window.openModal();
       <%session.setAttribute("url", "qna/list.do");%>
       return false;
       <% } else { %>
       location.href='./create.do';
       return true;
       <% } %> 
     }
     $(document).ready(function() {
       
       if($(".left").height() < $(".right").height()){
          $(".left").height($(".right").height());
       }
       
     });
     
     
</script>
<style>
    
</style>

</head> 
<body>
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />

     <jsp:include page="/member/menu/left.jsp" flush='false' />
<div class="float_l right " style="width:80%;">


<%--   <form name="frmSearch" method="get" action="./list.do"> 
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
  </form>  --%>

<div class="container" style="margin: 30px auto; width:90%;">
<c:if test="${!empty reviews_list}"> 
        <div><b>후기 게시판 (${reviews_list.get(my_cnt).getMy_cnt()})</b></div>
<table class="table table-hover" style='width: 100%;'>
<%--   <colgroup>
    <col style='width: 8%;'/>
    <col style='width: 10%;'/>
    <col style='width: 49%;'/>
    <col style='width: 15%;'/>
    <col style='width: 8%;'/>
  </colgroup> --%>
    <thead>
    <TR>
    <TH>글번호</TH>
    <TH>카테고리</TH>
    <TH>제목</TH>
    <TH>등록일</TH>
    <TH>조회수</TH>  
  </TR>
  </thead>
<tbody>
<c:forEach var="vo" items="${reviews_list}">
  <TR>
    <TD>${vo.r_no}</TD>
    <TD>${vo.t_category }</TD>
    <TD style="color: black;">${vo.title}</TD>
    <TD>${fn:substring(vo.wdate, 0, 10) }</TD>
    <TD>${vo.cnt}</TD>
  </TR>
</c:forEach> 
</tbody>  
</TABLE>
</c:if>
 
<c:if test="${!empty cheat_list}"> 
        <div><b>신고 게시판 (${cheat_list.get(my_cnt).getMy_cnt()})</b></div>
<table class="table table-hover" style='width: 100%;'>
  <colgroup>
    <col style='width: 8%;'/>
    <col style='width: 10%;'/>
    <col style='width: 49%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 8%;'/>
  </colgroup>
    <thead>
    <TR>
    <TH>글번호</TH>
    <TH>신고구분</TH>
    <TH>제목</TH>
    <TH>신고대상</TH>
    <TH>등록일</TH>
    <TH>조회수</TH>  
  </TR>
  </thead>
<tbody>
<c:forEach var="vo" items="${cheat_list}">
  <TR>
    <TD>${vo.ctno}</TD>
    <TD>${vo.gubun }</TD>
    <TD style="color: black;">${vo.title}</TD>
    <TD>${vo.cheatid}</TD>
    <TD>${fn:substring(vo.wdate, 0, 10) }</TD>
    <TD>${vo.cnt}</TD>
  </TR>
</c:forEach> 
</tbody>  
</TABLE> 
</c:if>


<DIV style='width: 100%; clear: both; height: 1px; border-top: dotted 1px #AAAAAA; margin: 10px auto;'></DIV>
<br>

 <c:forEach var="list" items="${hashMap}"> 
<c:if test="${!empty list.value}"> 
   <c:choose>
      <c:when test="${list.key eq 'art_list'}">
        <div><b>예술/문화 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>
      <c:when test="${list.key eq 'camera_list'}">
        <div><b>카메라 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>
      <c:when test="${list.key eq 'book_list'}">  
        <div><b>도서 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'computer_list'}">  
        <div><b>컴퓨터 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'cloth_list'}">  
        <div><b>의류 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'cosmetic_list'}">  
        <div><b>화장품 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'product_list'}">  
        <div><b>잡화 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'game_list'}">  
        <div><b>게임 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'gamedevice_list'}">  
        <div><b>게임기기 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'mobile_list'}">  
        <div><b>핸드폰 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'living_list'}">  
        <div><b>생활 용품 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'sports_list'}">  
        <div><b>스포츠 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'usedcar_list'}">  
        <div><b>중고차 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'carproduct_list'}">  
        <div><b>자동차 용품 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
      <c:when test="${list.key eq 'music_list'}">  
        <div><b>음향기기 게시판 (${list.value.get(my_cnt).getMy_cnt()})</b></div>
      </c:when>  
   </c:choose>  

<table class="table table-hover" style='width: 100%;'>
  <colgroup>
    <col style='width: 8%;'/>
    <col style='width: 10%;'/>
    <col style='width: 32%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 7%;'/>
    <col style='width: 15%;'/>
    <col style='width: 8%;'/>
  </colgroup>
    <thead>
    <TR>
    <TH>글번호</TH>
    <TH>거래구분</TH>
    <TH>제목</TH>
    <TH>희망가격</TH>
    <TH>거래방식</TH>
    <TH>지역</TH>
    <TH>등록일</TH>
    <TH>조회수</TH>  
  </TR>
  </thead>
<tbody>

<c:forEach var="vo" items="${list.value}">
  <TR>
    <TD>${vo.my_no}</TD>
    <TD>${vo.deal_code }</TD>
    <TD style="color: black;">${vo.title}</TD>
    <TD>${vo.hprice}</TD>
    <TD>${vo.deal_way}</TD>
    <TD>${vo.region}</TD>
    <TD>${fn:substring(vo.wdate, 0, 10) }</TD>
    <TD>${vo.cnt}</TD>
  </TR>
</c:forEach>
</tbody>  
</TABLE> 
</c:if> 
</c:forEach> 
</div>




</div>

<div class="both"></div>
<jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>
<!-- -------------------------------------------- -->
</html> 