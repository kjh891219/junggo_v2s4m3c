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
<script type="text/javascript" src="../js/event.js?ver=2"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>
<script type="text/javascript">
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
    
  .scrollup_bt{
     width:60px;
  }
   button{
   background-color: transparent;
   border:1px solid lightgray;
   padding:2px;
 }
</style>
 
<script type="text/javascript">
</script>
</head>
 
<body leftmargin="0" topmargin="0" style="">
  <jsp:include page="/menu/top.jsp" flush='false' />
  <jsp:include page="/menu/left.jsp" flush='false' />
<div class="container">
     
  <form name="frm" method="GET" action="./list.do"> 
    <div class='content_menu' style='width: 100%;'>
     <A href='#' onclick="create_login()">글쓰기</A>｜
      <A href="javascript:location.reload();">새로고침</A>
    </div>


  
  <div class="content_from">
    <ul >
      <c:forEach var="vo" items="${list }">
      <li>
      <div class="li" style="margin-top:5px; margin-bottom:5px;">
         <div class='float_l' style="width:25%; min-width: 220px; padding-right:30px;">
            <div class='float_l' style="width:7%;">${vo.bno}</div>
            <div class='float_l text_c' style="width:90%; line-height: 150px; border:1px solid lightgray;" >
              <c:set var='file1' value="${fn:toLowerCase(vo.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <a href="./read.do?bno=${vo.bno}"><IMG id='file1' style="max-width:150px;" src='./storage/${vo.file1}' ></a>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}" >
                  <a href="./read.do?bno=${vo.bno}"><IMG id='file1'  style="max-width:150px;"  src='./storage/${vo.file1}'></a>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <a href="./read.do?bno=${vo.bno}"><IMG id='file1' style="max-width:150px;"  src='./storage/${vo.file1}'></a>
                </c:when>
                <c:otherwise>
                  <a href="./read.do?bno=${vo.bno}"><span style="line-height:inherit; font-size:11px;">미리보기 이미지가 없습니다. </span></a>
                </c:otherwise>
              </c:choose>
            </div>
            <div class='both'></div>
            </div>
         <div class='float_l' style="width:55%; min-width: 350px;">
            <span>[${vo.deal_code }]</span>
            <span>[${vo.product_code }]</span>
            <strong><a href="./read.do?bno=${vo.bno}">${vo.title }</a></strong>
            <span> ${vo.deal_state =='거래중'?"<img src='../images/deal00.png' style='width:50px'>":"<img src='../images/deal01.png' style='width:50px'>" }</span>
            
            <div style="margin-top:65px;">
               <span>카테고리 > ${vo.category }</span>
            </div>
            <span>지역 > ${vo.region }</span>
            <div>
               <span>조회수 : ${vo.cnt } ·</span>
               <span>등록일 : ${vo.wdate.substring(0,10) }</span>
            </div>
         </div>
         <div class='float_l text_r' style="width:20%; margin-top:40px; padding-right:30px; min-width: 150px;">
            <span>
               <strong style="font-family: dotum,'돋움'; font-size: 30px;"><fmt:formatNumber value="${vo.hprice }" pattern="#,###원"/></strong>
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
 
  <DIV class='text_r'>
  <button type='button' onclick="create_login()">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
  <DIV class='bottom'>${paging}</DIV>
  
  	<div class="text_c">
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
    </div>
  </form> 
     <jsp:include page="/menu/bottom.jsp" flush='false' />
          
  </div>
  
</body>
 
</html>