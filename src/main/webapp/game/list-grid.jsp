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
<link href="./css/style.css" rel="Stylesheet" type="text/css">

<script type="text/javascript">
$(function(){
  $('#panel_frm').hide();
});

  function profile(nickname, userid){
    var url = '../member/profile.do?nickname='+nickname;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=617.5px, height=600px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
  }
 
function create(){
  $('#panel_frm').show();
  $('#frm').attr('action', './create.do');
  $('#sort').val('');
  $('#submit').html('등록');
  $('#sort').focus();
}
 
function create_cancel(){
  $('#panel_frm').hide();
}

function update(gno, sort, seqno){
  $('#panel_frm').show();
  $('#frm').attr('action', './update.do');
  // $('#codeno').val(codeno); // Chrome Elements에 변경이 안됨
  $('#codeno').attr('value', codeno);
  // $('#sort').val(sort);          // Chrome Elements에 변경이 안됨
  $('#sort').attr('value', sort);
  // $('#seqno').val(seqno); // Chrome Elements에 변경이 안됨
  $('#seqno').attr('value', seqno);
  $('#submit').html('저장');
  $('#sort').focus();
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
  
  a{
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
  a:hover{
  color: #0064FF;
  }
  li{
    text-decoration: none;
    list-style: none;
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
  

/* index 안에 있는 태그 스타일 */

   .container{
      width:100%;
   }
   
   .container li{
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
   width: 165px;
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
li { 
  text-align: center;
}
.info_area strong{

} 
 
.info_area .tagarea { 
 padding-bottom : 5px; 
 width : 560px; 
 word-break: break-all;

} 

.bottom_area { 
 padding-bottom : -5px; 
 width : 560px; 
 word-break: break-all;

} 

.etc_txt{
 line-height : 17px; 
 vertical-align : top;
 display : block; 
 color : #181818;
 padding-top : 7px;
}

.container  .price_area { 
 position : absolute; 
 right : 0;
 top : 18px;
 color : #0073be;
 min-width: 150px;
 text-align: right;
 margin-top: 30px;
 margin-right: 50px;
 margin-bottom: 0px;
 margin-left: 0px;
}

.price_area strong{
  display: block; 
  font-size: 25px; 
  color: #0064FF;
  width: 560px;
} 

.price_area .lev{
  display: block; 
  font-size: 18px; 
  color: #0064FF;
  width: 560px;
} 


</style>
</head> 
 <div class="container">



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


<ul id= "listBodyDiv" class="container" style="margin: auto; display: block;">

 <c:forEach var="vo" items="${list}">
  
   <li class = "product" id = modelno_1230"> 
             <div class ="thum_area">
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
           </div>
    <div class= "info_area">
   <strong><A href="./read.do?gno=${vo.gno}">${vo.title}</A></strong>
    <div class ="tagarea">
      <span class = "tx">분류 : ${vo.category} | </span>
      <span class = "tx">거래방식 : ${vo.deal_way} | </span>
      <span class = "tx">거래구분 : ${vo.deal_code} | </span>
      <span class = "tx">상품구분 : ${vo.product_code} | </span>
      <span class = "tx">지역 : ${vo.region}</span>
    </div>
    <div class = "bottom_area">
    <span class = "etc_txt"></span>
    조회수:${vo.cnt} | 등록일:${vo.wdate.substring(0,10) }
    </div>
    </div>
    <div class="price_area">
     <span class= "hprice"><strong>${vo.hprice}</strong></span>
     <span class= "lev">${vo.lev}</span>
   </div>
 
</li>

</c:forEach>
</ul>

<DIV style="text-align: center;">
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>


<DIV class='bottom'>${paging}</DIV>
</div>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 