<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="web.tool.*" %>
<%
// ---------------------------------------------
// 페이징 관련 코드 시작
// ---------------------------------------------
//int totalRecord = youtubeDAO.count(col, word);
/*
listFile: 목록 파일명
nowPage: 현재 페이지, 1페이지부터 시작
recordPerPage: 페이지당 레코드 수, 2개, DAO의 recordPerPage 동일
col: 검색 컬럼
word: 검색어
@ return: 페이징 생성 문자열
*/
// 2: 한 페이지당 출력할 레코드 갯수, DAO의 recordPerPage와 동일
//String paging = new Paging().paging4("list_grid3.jsp", totalRecord, nowPage, youtubeDAO.RECORD_PER_PAGE, col, word);
// ---------------------------------------------
// 페이징 관련 코드 종료
// ---------------------------------------------
 
%> 
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
</script>
<script>
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
<!-- ----------------------------------------- -->
 <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
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
  
  
<DIV class='content'>  
<c:forEach var="vo" items="${list }" varStatus="status"> 
  <c:if test="${status.index != 0 and status.index % 3 == 0}">
    <!-- <DIV style='width: 100%; clear: both; height: 1px; border-top: dotted 1px #AAAAAA; margin: 10px auto;'></DIV> -->
  </c:if>
    <DIV style='background-color: #FFFFFF; float: left; position: relative; left: 0%; margin: 1%; width: 30%; text-align: center;
        border: 1px solid #d6d6d6; padding: 1%;'>
   
        <c:choose>
          <c:when test="${vo.thumb.length() > 0}">
          <div>
          <A href='./read.do?ano=${vo.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' style="color: black;">
          <img src="./storage/${vo.file1}" style='width: 100%; height: 300px;'/>
          </A>
          </div>
          </c:when>
          <c:when test="${vo.size1 > 0}">
            <DIV style='width: 100%; height: 300px; display:table;'>
            <DIV style='width: 100%; height: 300x; display:table-cell; vertical-align: middle;'>
            <A href='./read.do?ano=${vo.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'>${vo.file1 }</A>
            </DIV>
          </DIV>
          </c:when>
          <c:otherwise>
             <DIV style='width: 100%; height: 300px; display:table;'>
             <DIV style='width: 100%; height: 300px; display:table-cell; vertical-align: middle;'>
             <A href='./read.do?ano=${vo.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'>
             <IMG src='./images/no_image2.png' style='width: 100%; height: 250px;'></A>
             </DIV>
             </DIV>
          </c:otherwise>
        </c:choose>
        
        
    <br>
    <A style="font-size: 20px;" href='./read.do?ano=${vo.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'><b>${vo.title}</b></A> <!-- 제목 부분 -->
    <DIV style="text-align: left">
    <span style="font-size: 22px; font-weight:bold; color: red; margin-right: 11px; margin-top: 9px;">가격</span><span style="font-size: 16px; padding: 17px 0 0 0;"><b><fmt:formatNumber value="${vo.hprice }" pattern="#,###원"/></b></span>
    </DIV>
<%--     <IMG src='./images/user.png'> ${vo.cnt}  --%>
  </DIV>
</c:forEach> 
</DIV>
<DIV style='clear: both; margin: 10px auto;'></DIV>
<div style="text-align: center;">
<button onclick="create_login();" type='button' class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>
</div>
<DIV class='bottom'>${paging}</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 