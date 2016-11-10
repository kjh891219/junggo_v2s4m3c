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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="./css/style.css" rel="Stylesheet" type="text/css">
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
       <%session.setAttribute("url", "computer/list.do");%>
       return false;
       <% } else { %>
       location.href='./create.do';
       return true;
       <% } %> 
     }
</script>

<style type="text/css">

/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
  *{ 
    font-family: 'Nanum Gothic', serif;
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
  }
  
  a{
   color:white;
  }
  
/* left를 제외한 스타일 */
  body{
   width:80%;
   margin-left:130px;
  }
  
/* top 스타일 */
  
  header{ 
    height: 35px; 
    background-color: #e6e6e6; 
    font-family: 맑은 고딕;  
    text-align: center;
  }
  .member-list {
    margin:5px 8px 0 0;
  
  }
  
 .member-list li {
    float:left;
    list-style: none;
    padding-left:8px;
  }
 .member-list li a {
    font-size:12px;
  }

/* left */  

   /* 로고 */
   #logo {
      width:70px;
      margin:20px auto;
   }
   #logo img {
      width:70px;
   }
   
  #main_left {
    position:fixed; 
    top:0;
    left:0;
  }
  
  #main_left_left{
    width:130px; 
    height:100%;
    float:left;
    color:white;
    background-color: #737373;
  }
  
   #main_left_detail{
      display:none;
      position:absolute;
      left:130px;
      width:130px;
      height:100%;
      
      background-color:#575757;
   }
  
  .left_list_form {
    padding:10px;
  }
  
  .left_list{
    padding-bottom:8px;
  }

/* index 안에 있는 태그 스타일 */

   .container{
      width:100%;
   }
   
   nav ul li {
      list-style:none;
      margin-left: 20px;
   }
   nav {
      margin-top:30px;
   }
   footer{
      text-align: center;
   }
 
 
</style>
</head>
 
<body leftmargin="0" topmargin="0">
<div class="container">
  <jsp:include page="/menu/top.jsp" flush='false' />
  <jsp:include page="/menu/left.jsp" flush='false' />
     
  <form name="frmSearch" method="get" action="./list.do"> 
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
  
  <div style="width: 1200px; margin: 0 auto; padding-top: 40px; display: block;">
  <div style="position: relative; background: #fff; border-top: 1px solid #ccc; display: block;">
    <ul style="display: inline-block; width: 100%; border-bottom: 1px solid #dadada; list-style: none; padding: 0; margin: 0;">
      <c:forEach var="vo" items="${list }">
      <li style="border-top: 1px  solid #dadada; background: #fefdf1; list-style: none;">
        <div style="border-top: none; display: inline-block; position: relative; width: 100%; padding: 15px 0;">
          <!--  그림 -->
          <div style="float: left; position: relative; width: 160px; height: 160px; margin-left: 15px; display: block;">
            <a href='./read.do?ctno=${vo.ctno }' style="display: block; background-color: #fff; text-align: center; color: #333; text-decoration: none;">
              <c:choose>
              <c:when test="${vo.thumb == null}"></c:when>
              <c:when test="${vo.thumb != null}">
                <c:set var='thumb' value="${fn:toLowerCase(vo.thumb)}" />
                <c:choose>
                  <c:when test="${fn:endsWith(thumb, '.jpg')}">
                    <IMG id='thumb' src='./storage/${vo.thumb}' style="max-width: 160px; height: 160px; vertical-align: top; border: 0 none;">
                  </c:when>
                  <c:when test="${fn:endsWith(thumb, '.gif')}">
                    <IMG id='thumb'  src='./storage/${vo.thumb}' style="max-width: 160px; height: 160px; vertical-align: top; border: 0 none;">
                  </c:when>
                  <c:when test="${fn:endsWith(thumb, '.png')}">
                    <IMG id='thumb'  src='./storage/${vo.thumb}' style="max-width: 160px; height: 160px; vertical-align: top; border: 0 none;">
                  </c:when>
                  <c:otherwise>
                    ${vo.thumb}
                  </c:otherwise>
                </c:choose>
                </c:when>
            </c:choose>
            </a>
          </div>
          <!-- 제목 -->
          <div style="float: left; position: relative; z-index: 1; width: 560px; height: 155px; padding: 5px 0 0 15px; display: block;">
            <p style="overflow: hidden; max-height: 22px; padding: 0; margin: 0; display: block;">
              <a href='./read.do?ctno=${vo.ctno }' style="font-size: 16px; line-height: 22px; color: #333; letter-spacing: -1px; text-decoration: none;">
                ${vo.title }
              </a>
            </p>
             <!--상품구분 조회수 지역 등록일  -->
          <div style="position: absolute; bottom: 0; width: 100%; display: block;">
            <div style="float: left; padding-top: 20px; vertical-align: top; line-height: 110%; display: block;">
              <div style="float: left; width: 120px; height: 20px; margin-right: 20px; vertical-align: top; display: block;">
                <span style="overflow:hidden; width: 90px; height: 0; padding-top: 20px; font-weight: bold; color: #333;">
                  ${vo.category}
                </span>
              </div>
              <a style="float: left; padding-right: 10px; text-align: left; font-weight: bold; color: #333; text-decoration: none;">
              ${vo.region}
              </a>
              <span style="margin: 3px 10px 0px -1px; float: left; display: inline-block; width: 1px; height: 12px; background: #dadada;">
              </span>
              <div style="padding: 0 20px 0 0; position: relative; z-index: 1px; float: left; display: block;">
                <strong style="display: inline-block; height: 13px; font-size: 14px; color: #333; letter-spacing: 0; vertical-align: .4em; font-weight: bold;">
                  등록일 : ${vo.wdate}
                </strong>
              </div>
            </div>
            
            <div style="width: auto; margin-top: 15px; display: inline-block; display: block;">
              <div style="padding-top: 3px; float: left; overflow: hidden; display: block;">
                <strong style="display: inline-block; height: 13px; font-size: 14px; color: #333; letter-spacing: 0; vertical-align: .4em; font-weight: bold;">
                  조회수 : ${vo.cnt}
                </strong>
              </div>
            </div>
            
          </div>
          </div>
          <!-- 등록자 희망가격 거래방식 -->
         <div style="float: left; position: relative; width: 190px; height: 150px; margin-left: 15px; padding: 7px 0 0 20px; border-left: 1px solid #f0f0f0; display: block;">
            <div style="height: 114px; display: block;">
              <span style="color: #111; font-size: 16px; font-weight: bold;">
                <strong style="font-size: 20px; font-family: 'Tahoma', 'sans-serif'; color: #111; font-weight: bold;">
                  <fmt:formatNumber value="${vo.hprice }" pattern="#,###원"/>
                </strong>
              </span>
            </div>
            <div style="position: absolute; bottom: 5px; display: block;">
              <p style="padding-top: 5px; padding: 0; margin: 0; display: block;">
                <span style="background: none; padding-left: 0; display: inline-block; padding: 0 8px; font-size: 12px; color: #333; line-height: 120%;">
                  ${vo.deal_way} | ${vo.deal_code}
                </span>
              </p>
            </div> 
         </div>
         <div style="width: 185px; float: left; position: relative; height: 150px; padding: 6px 0 0 20px; border-left: 1px solid #f0f0f0; display: block;">
            <p style="padding: 0; margin: 0; display: block;">
              <a style="display: block; overflow: hidden; width: 100%; height: 19px; font-weight: bold; line-height: 1.4; color: #333; vertical-align: top; text-decoration: none;">
                ${vo.nickname}
              </a>
            </p>
         </div>
        </div>
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
     <jsp:include page="/menu/bottom.jsp" flush='false' />     
  </div>
</body>
 
</html>