<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  
  <div class="content" style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>       
      </colgroup>
          
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>번호</th>
          <th>거래구분</th>
          <th>상품구분</th>
          <th>이미지</th>
          <th>제목</th>
          <th>조회수</th>
          <th>등록자</th>
          <th>등록일</th>
          <th>거래상태</th>      
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="vo" items="${list }">
            <tr>
              <td>${vo.ctno}</td>
              <td>${vo.deal_code}</td>
              <td>${vo.category}</td>
              <td> <c:choose>
              <c:when test="${vo.thumb == null}"></c:when>
              <c:when test="${vo.thumb != null}">
                <c:set var='thumb' value="${fn:toLowerCase(vo.thumb)}" />
                <c:choose>
                  <c:when test="${fn:endsWith(thumb, '.jpg')}">
                    <IMG id='thumb' src='./storage/${vo.thumb}' >
                  </c:when>
                  <c:when test="${fn:endsWith(thumb, '.gif')}">
                    <IMG id='thumb'  src='./storage/${vo.thumb}' >
                  </c:when>
                  <c:when test="${fn:endsWith(thumb, '.png')}">
                    <IMG id='thumb'  src='./storage/${vo.thumb}' >
                  </c:when>
                  <c:otherwise>
                    ${vo.thumb}
                  </c:otherwise>
                </c:choose>
                </c:when>
            </c:choose>
            </td>
              <td style="color: black;"><A href='./read.do?ctno=${vo.ctno }' style="color: black;">${vo.title}</A></td>
              <td>${vo.cnt}</td>
              <td>${vo.nickname}</td>
              <td>${vo.wdate}</td>
              <td>${vo.deal_status}</td>
            </tr>
          </c:forEach>
      </tbody>
    </table>
  <div style="text-align: center;">
<button type='button' onclick="location.href='./create.do?ctno=0'" class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>
  </div>
  <DIV class='bottom'>${paging}</DIV>
  
     <jsp:include page="/menu/bottom.jsp" flush='false' />     
  </div>
</body>
 
</html>