<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
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
<script src="${pageContext.request.contextPath}/js/event.js?ver=2"></script>


<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }

function create_login() {
  <% if( session.getAttribute("userid") == null) { %>
  alert('로그인 한 사용자만 이용이 가능합니다');
  window.openModal();
  return false;
  <% } else { %>
  location.href='./create.do';
  return true;
  <% } %> 
}
</script>

</head> 

 <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />

<DIV class='title'>회원 목록</DIV>
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
    <TH>글번호</TH>
    <TH>거래구분</TH>
    <TH>제목</TH>
    <TH>희망가격</TH>
    <TH>거래방식</TH>
    <TH>지역</TH>
    <TH>닉네임</TH>
    <TH>이미지</TH>
    <TH>날짜</TH>
    <TH>조회수</TH>  
  </TR>
  </thead>
 
<tbody>
<c:forEach var="vo" items="${list }">
  <TR>
    <TD>${vo.ano}</TD>
    <TD>${vo.deal_code }</TD>
    <TD style="color: black;"><A href="./read.do?ano=${vo.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}" style="color: black;">${vo.title}</A></TD>
    <TD>${vo.hprice}</TD>
    <TD>${vo.deal_way}</TD>
    <TD>${vo.region}</TD>
    <TD>${vo.nickname}</TD>
    <td>
    <c:if test="${vo.thumb.length() > 0}">
     <img src="./storage/${vo.thumb}"/>
    </c:if></td>
     <TD>${fn:substring(vo.wdate, 0, 10) }</TD>
     <TD>${vo.cnt}</TD>
  </TR>
</c:forEach>
</tbody>  
</TABLE>

</div>
<div style="text-align: center;">
<button onclick="create_login();" type='button' class="btn btn-primary">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-success">새로 고침</button>
</div>
</div>
<DIV class='bottom'>${paging}</DIV>

<jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>
<!-- -------------------------------------------- -->
</html> 