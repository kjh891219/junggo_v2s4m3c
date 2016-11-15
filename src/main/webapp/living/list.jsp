<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.living.LivingVO" %> 
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
       <%session.setAttribute("url", "living/list.do");%>
       return false;
       <% } else { %>
       location.href='./create.do';
       return true;
       <% } %> 
     }
</script>
<style type="text/css">

/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/malgungothic.css);
  *{ 
    font-family: 'Malgun Gothic', sans-serif;
    font-size: 12px;
    margin: 0px;
    padding: 0px;  
    line-height: 1.5;
  }
  
 

/* left를 제외한 스타일 */
  body{
   width:80%;
   margin-left:130px;
  }
 


   .thum_img{
      width:80%;
   }
   
   .thum_img li{
      border-bottom-width: 1px;
      border-bottom-style: solid;
      border-bottom-color: black;
      padding-top: 30px;    
      padding-right: 0px;    
      padding-bottom: 28px;    
      padding-left: 0px;    
      position: relative;
   }
   
   .container li:after{
    clear: both;
    display: block;
    content: "";
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
 .thum_area{
   width: 25%;
   float: left;
   position : relative;
 
 }
 
.thum_area img{ 
  width: 148px;
  height: 148px;
  border: 1px; 
  solid : #e2e2e2;
  
}
 
 div{
  display: block;
 }

.info_area {
  width: 60%;
}
 
.info_area .tagarea { 
 padding-bottom : 5px; 
 word-break: break-all;
 text-align: center;
} 
.info_area .strong{
 
}

.info_area a{
  text-align: center;
  color:black;
  display: block; 
  font-size: 18px; 
  color: #181818;
  margin-top: -3px;
  margin-right: 0px;
  margin-bottom: 14px;
  margin-left: 0px;
  width: 560px;
  }
  
.info_area a:hover{
  color: #0064FF;
  }

.bottom_area { 
 padding-bottom : -5px; 
 width : 560px; 
 word-break: break-all;
     text-align: center;
} 

.etc_txt{
 line-height : 17px; 
 vertical-align : top;
 display : block; 
 color : #181818;
 padding-top : 7px;
}

.price_area { 
 position : absolute; 
 padding-right:10px; 
 min-width: 150px;
 width: 25%;
 right : 0;
 top : 18px;
 color : #0073be;
 min-width: 150px;
 text-align: right;
 margin-top: 30px;
 margin-right: 10px;
 margin-bottom: 0px;
 margin-left: 0px;
}

.price_area .priea strong{
 pattern : "#,###원";
 text-align: center;
  display: block; 
  font-size: 30px; 
  color: #0064FF;

} 

.price_area .priea .lev{
text-align: center;
  display: block; 
  font-size: 25px; 
  color: #0064FF;

} 


</style>
</head> 
<div class = "container" style="margin: 0px; border: 0px solid rgb(255, 255, 255); border-image-source: none; width: 100%; float: left; background-image: none; background-color: transparent; background-position: 0px 0px; background-repeat: repeat;">
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


<ul id= "listBodyDiv" class="thum_img" style="margin: auto; display: block;">

 <c:forEach var="vo" items="${list}">
  
   <li class = "product" id = modelno_1230"> 
             <div class ="thum_area">
             <A href="./read.do?lno=${vo.lno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}">
                  <c:set var='file2' value="${fn:toLowerCase(vo.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${vo.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${vo.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${vo.file1}'>
                </c:when>
                 <c:otherwise>
                  <A href='${root}/download?dir=/game/storage&filename=${vo.file2}'>${vo.file2}</A>
                </c:otherwise>
              </c:choose>
                 </A>
           </div>
    <div class= "info_area">
   <strong><A href="./read.do?lno=${vo.lno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}">${vo.title}</A></strong>
    <div class ="tagarea">
      <span class = "tx">분류 : ${vo.category} | </span>
      <span class = "tx">거래방법 : ${vo.deal_way} | </span>
      <span class = "tx">거래구분 : ${vo.deal_code} | </span>
      <span class = "tx">상품구분 : ${vo.product_code} | </span>
      <span class = "tx">지역 : ${vo.region}</span>
    </div>
    <div class = "bottom_area">
    <span class = "etc_txt"></span>
    조회수:${vo.cnt} | 등록일:${vo.wdate.substring(0,10) }
    </div>
    </div>
    <div class="price_area" >
    <div class = "priea">
     <span class= "hprice"><strong >${vo.hprice}</strong></span>
    </div>
   </div>
 
</li>

</c:forEach>
</ul>

<div style="text-align: center; margin-top: 50px;">
<button type='button' onclick="create_login();" class="btn btn-success btn-lg">등록</button>
<button type='button' onclick="location.reload();" class="btn btn-danger btn-lg">새로 고침</button>
</div>

<DIV class='bottom'>${paging}</DIV>
</div>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 