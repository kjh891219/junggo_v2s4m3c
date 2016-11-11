<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
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
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>

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
       <%session.setAttribute("url", "sports/list.do");%>
       return false;
       <% } else { %>
       location.href='./create.do';
       return true;
       <% } %> 
     }
</script>





</head> 
<div style="margin: 0px; border: 0px solid rgb(255, 255, 255); border-image-source: none; width: 100%; float: left; background-image: none; background-color: transparent; background-position: 0px 0px; background-repeat: repeat;">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush="false"/> 


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



<div style="margin: 0px; border: 0px solid rgb(255, 255, 255); border-image-source: none; width: 100%; float: left; background-image: none; background-color: transparent; background-position: 0px 0px; background-repeat: repeat;">
  <h2 style="background: #f6f6f6; color:#696969; height: 29px; border-bottom: 1px solid #ddd; margin: 0; padding: 0 0 0 15px; font: bold 14px Arial; display: block; -webkit-margin-before: 0.83em;
    -webkit-margin-after: 0.83em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;">
   <span style="padding: 7px 0; display: block;">
     <a style="display: block; text-decoration: none; cursor: auto; color: #444;">스포츠용품 게시판</a>
   </span>
  </h2>
 </div>
 <div style="clear: both;"></div>
 
<!-- 본문 -->

 <div style="overflow: hidden; position: relative; padding-top: 20px; margin: 0 auto; width: 80%;">
    <ul style="overflow: hidden; clear: both; width: 990px; border-top: 1px solid #c2c2c2; position: relative; list-style: none; margin: 0; padding: 0; display: block;">
     <c:forEach var="vo" items="${list }">
      <li style="position: relative; width: 330px; height: 270px; border-bottom: 1px solid #e2e6ea; float: left; list-style: none; display: list-item;">
        <a href="./read.do?sno=${vo.sno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"style="color: #555; text-decoration: none; display: block; margin: 0; padding: 0;">
          <c:choose>
              <c:when test="${vo.file1 == null}"></c:when>
              <c:when test="${vo.file1 != null}">
                <c:set var='file1' value="${fn:toLowerCase(vo.file1)}" />
                <c:choose>
                  <c:when test="${fn:endsWith(file1, '.jpg')}">
                    <IMG id='file1' src='./storage/${vo.file1}' style="display: inline; width: 320px; height: 166px; vertical-align: bottom; border: 0;">
                  </c:when>
                  <c:when test="${fn:endsWith(file1, '.gif')}">
                    <IMG id='file1'  src='./storage/${vo.file1}' style="display: inline; width: 320px; height: 166px; vertical-align: bottom; border: 0;">
                  </c:when>
                  <c:when test="${fn:endsWith(file1, '.png')}">
                    <IMG id='file1'  src='./storage/${vo.file1}' style="display: inline; width: 320px; height: 166px; vertical-align: bottom; border: 0;">
                  </c:when>
                  <c:otherwise>
                    ${vo.file1}
                  </c:otherwise>
                </c:choose>
                </c:when>
            </c:choose>
          <span style="padding: 15px 0 0 15px; width: 290px; height: 16px; font-size: 12px; text-overflow: ellipsis; white-space: nowrap; overflow: hidden; font-family: 'Malgun Gothic'; line-height: .9em;">
            ${vo.title}
          </span>
        </a>
        <span style="display: block; padding-left: 15px; margin: 0; padding: 0; color: #676767; font-family: '돋움';">
         <em style="display: inline-block; margin-top: 3px; color: #0082f0; font-family: 'tahoma';">
            <strong style="font-size: 18px; color: #4e88cf;">${vo.region}</strong>
         </em>
       
        <span style="display: inline-block; margin-top: 3px; vertical-align: top; font-family: 'tahoma';">
          <strong style="font-size: 18px; color: #000;"><fmt:formatNumber value="${vo.hprice }" pattern="#,###원"/></strong>
        </span>
        <span style="position: absolute; left: 15px; bottom: 10px; height: 22px; margin: 0; padding: 0;">
          <span style="display: inline-block; border: 1px solid #eee; vertical-align: top; height: 20px; overflow: hidden; border-radius:2px; padding: 0 6px; line-height: 1;">
            <span style="height: 18px; line-height: 18px; border-top: 2px solid #fff; vertical-align: top; white-space: nowrap; color: #999; font-family: '돋움'; font-size: 11px; letter-spacing: -1px;">${vo.deal_way}</span>
          </span>
        </span>
        <span style="display: block; position: absolute; left: 254px; bottom: 10px; text-align: center; width: 50px; height: 50px; color: #999; font-size: 11px;">
          <em style="padding: 8px 0 3px; color: #519bde; display: block; font-family: '돋움'; text-align: center; font-size: 14px;">
             ${vo.cnt}
          </em>
          <span style="margin: 0; padding: 0; text-align: center; color: #999; font-size: 11px;">조회수</span>
        </span>
        </span>
      </li>
      </c:forEach>
    </ul> 
 </div>
 
 
 
 
 
<div style="text-align: center; margin-top: 50px;">
<button type='button' onclick="create_login();" class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>
</div>
<DIV class='bottom'>${paging}</DIV>

  </div>
<jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>
<!-- -------------------------------------------- -->
</html> 