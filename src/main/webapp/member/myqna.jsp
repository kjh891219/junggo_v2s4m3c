<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<!-- <link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          jQuery (necessary for Bootstrap's JavaScript plugins)
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
합쳐지고 최소화된 최신 CSS
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
부가적인 테마
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
합쳐지고 최소화된 최신 자바스크립트
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script> -->

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
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>
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

<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
<!-- ----------------------------------------- -->
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<jsp:include page="/member/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<div class="float_l right " style="width:80%;">
<div class="container">
   <form name="frm" method="GET" action="./list.do"> 
   <input type='hidden' value='${flag }' name='flag' id='flag'>
    <div class='content_menu' style='width: 100%;'>
     <A href='./create.do?'>등록</A>｜
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
    <c:if test="${(userid eq 'master')}"><TH class='th'>관리</TH></c:if>
  </TR>
 
   <c:forEach var="vo" items="${list }">
  <TR>
    <TD class='td'>${vo.rownum}</TD>
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
      
    <TD class='td'>
      <c:choose>
      <c:when test="${vo.ansnum == 0 }"></c:when>
      <c:when test="${vo.ansnum > 0 }">
         <c:forEach var="indent"  begin="1" end="${vo.indent }" step="1">　</c:forEach></c:when>
      </c:choose>
    <A href="./read.do?qnano=${vo.qnano}&col=${searchDTO.col}&word=${searchDTO.word}&flag=${flag}">${vo.title }</A></TD>
    <TD class='td'>${vo.userid }</TD>
    <TD class='td'>${vo.qdate}</TD>
    <TD class='td'>
      <c:if test="${(userid eq 'master')}"><a href="./reply.do?qnano=${vo.qnano}&categoryno=${vo.categoryno}&col=${searchDTO.col}&word=${searchDTO.word}&flag=${flag}"><span>답변</span><!-- <img src="./images/reply.png" title="답변" border='0'/> --></a></c:if>
      <c:if test="${(userid eq 'master')}"><A href="./read.do?qnano=${vo.qnano}&flag=${flag}"><IMG src='../menu/images/update.png' width="16px" title='수정'></A></c:if>
      <c:if test="${(userid eq 'master')}"><A href="./delete.do?qnano=${vo.qnano}&flag=${flag}"><IMG src='../menu/images/delete.png' width="16px" title='삭제'></A></c:if>
    </TD>
    
  </TR>
 </c:forEach>
</TABLE>
 
   <DIV class='bottom'>${paging}</DIV>
 
<DIV class='bottom text_r'>
  <button type='button' onclick="create_login();">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
</DIV>
<div class="both"></div>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</div>
</body>

<!-- -------------------------------------------- -->
</html> 