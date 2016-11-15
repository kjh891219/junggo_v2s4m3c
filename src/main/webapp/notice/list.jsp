<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
          
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
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript" src="../js/event.js"></script>
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
       <%session.setAttribute("url", "notice/list.do");%>
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

</head>
<body> 
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush="false"/> 
     <jsp:include page="/menu/community_left.jsp" flush='false' /> 
<!-- <div class = "container" style="margin: 0px; border: 0px solid rgb(255, 255, 255); border-image-source: none; width: 100%; float: left; background-image: none; background-color: transparent; background-position: 0px 0px; background-repeat: repeat;"> -->


<div class="float_l right " style="width:80%;">
<div class="container" style="width:90%; margin-top:10%; min-height: 380px;">
 <table class="table table-hover" style='width: 100%;'>
    <thead>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>글쓴이</TH>
    <TH class='th'>조회수</TH>
    <TH class='th'>등록일</TH>
    <TH class='th'>기타</TH>
  </TR>
 
  </thead>

 
<c:forEach var="vo" items="${list}">

  <TR>
    <TD class='td'>${vo.noticeno}</TD>
                <td style='vertical-align: middle;'>
              <c:choose>
                <c:when test="${vo.ansnum == 0 }">
                </c:when>
                <c:when test="${vo.ansnum > 0 }">
                  <c:forEach var="indent"  begin="1" end="${vo.indent }" step="1">
                    &nbsp;&nbsp;&nbsp;   
                  </c:forEach>
                  <img src='./images/reply3.jpg'>
                </c:when>
              </c:choose>
              <a href="./read.do?noticeno=${vo.noticeno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}">${vo.title}</a> 
            </td> 
    <TD >${vo.nickname}</TD>
    <TD >${vo.cnt}</TD>
    <TD >${vo.wdate.substring(0,10) }</TD>
    <TD >
      <A href="./update.do?noticeno=${vo.noticeno}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete.do?noticeno=${vo.noticeno}"><IMG src='./images/delete.png' title='삭제'></A>
      <A href='./reply.do?noticeno=${vo.noticeno}'><IMG src='./images/url5.png' title='답변'>답변</A>｜
    </TD>
    
  </TR>
</c:forEach>
 
</TABLE>
<div style="text-align: center; margin-top: 50px;">
<button type='button' onclick="create_login();" class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>

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

<DIV class='bottom'>${paging}</DIV>
</div>
</div>
<!-- -------------------------------------------- -->
<div class='both'></div>

<!-- </div> -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 