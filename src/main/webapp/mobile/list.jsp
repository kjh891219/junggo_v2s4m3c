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
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="../js/event.js?ver=1"></script>
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
       <%session.setAttribute("url", "mobile/list.do");%>
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
<body style="width:80%; margin:0 auto;">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush="false"/> 

<div class = "container" style="">

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
  <h2 style=" color:#696969; height: 29px; border: 1px solid #3289C7; margin: 0; padding: 0 0 0 15px; font: bold 14px Arial; display: block; -webkit-margin-before: 0.83em;
    -webkit-margin-after: 0.83em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;">
   <span style="padding: 7px 0; display: block;">
     <a style="display: block; text-decoration: none; cursor: auto; color: #444;">모바일 게시판</a>
   </span>
  </h2>
 </div>
 
 
 
 <div style="overflow: hidden; position: relative; padding-top: 0px; padding-right: 15px; padding-bottom: 0px; padding-left: 15px; margin-top: 20px; width: 100%;">
    <ul style="overflow-x: hidden; overflow-y: hidden; margin-left : 15px; text-align: center;">
     <c:forEach var="mobileVO" items="${list }">
      <li style="float : left; position: relative; width: 240px; height: 280px; margin-left: 30px; margin-bottom:26px; text-align: center;">
        <a href="./read.do?mno=${mobileVO.mno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"style="color: #555; text-decoration: none; display: block; margin: 0; padding: 0;">
          <c:choose>
              <c:when test="${mobileVO.file1 == null}"></c:when>
              <c:when test="${mobileVO.file1 != null}">
                <c:set var='file1' value="${fn:toLowerCase(mobileVO.file1)}" />
                <c:choose>
                  <c:when test="${fn:endsWith(file1, '.jpg')}">
                    <IMG id='file1' src='./storage/${mobileVO.file1}' style="display: inline; width: 240px; height: 160px; vertical-align: bottom; border: 0;">
                  </c:when>
                  <c:when test="${fn:endsWith(file1, '.gif')}">
                    <IMG id='file1'  src='./storage/${mobileVO.file1}' style="display: inline; width: 240px; height: 160px; vertical-align: bottom; border: 0;">
                  </c:when>
                  <c:when test="${fn:endsWith(file1, '.png')}">
                    <IMG id='file1'  src='./storage/${mobileVO.file1}' style="display: inline; width: 240px; height: 160px; vertical-align: bottom; border: 0;">
                  </c:when>
                  <c:otherwise>
                    ${mobileVO.file1}
                  </c:otherwise>
                </c:choose>
                </c:when>
            </c:choose>
            </a>
        <p class = "name">
          <a href="./read.do?mno=${mobileVO.mno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}"style="color: #555; text-decoration: none; display: block; margin-top: 3px; padding: 0;"> ${mobileVO.title} </a> 
        </p>
      <span style="display: block; padding-left: 15px; margin: 0; padding: 0; color: #676767; font-family: '돋움';">
         <em style="display: inline-block; margin-top: 3px; color: #0082f0; font-family: 'tahoma';">
            <strong style="font-size: 18px; color: #4e88cf;">${mobileVO.region}</strong>
         </em>
        <span style="display: inline-block; margin-top: 3px; vertical-align: top; font-family: 'tahoma';">
          <strong style="font-size: 18px; color: #000;"><fmt:formatNumber value="${mobileVO.hprice }" pattern="#,###원"/></strong>
        </span>
        <span style="position: absolute; left: 15px; bottom: 10px; height: 22px; margin-bottom: 5px; padding: 0;">
          <span style="display: inline-block; border: 1px solid #eee; vertical-align: top; height: 20px; overflow: hidden; border-radius:2px; padding: 0 6px; line-height: 1;">
            <span style="height: 18px; line-height: 18px; border-top: 2px solid #fff; vertical-align: top; white-space: nowrap; color: #999; font-family: '돋움'; font-size: 11px; letter-spacing: -1px;">${mobileVO.deal_way}</span>
          </span>
        </span>
        <span style="display: block; position: absolute; left: 160px; bottom: 10px; text-align: center; width: 50px; height: 50px; color: #999; font-size: 11px;">
          <em style="padding: 8px 0 3px; color: #519bde; display: block; font-family: '돋움'; text-align: center; font-size: 14px;">
             ${mobileVO.cnt}
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

<DIV class='bottom'>${paging}</DIV>
</div>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 