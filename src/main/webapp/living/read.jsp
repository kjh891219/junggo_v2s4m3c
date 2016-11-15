<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
 
 
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
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>
 

<script type="text/javascript"> 
 
$(document).ready(function() {
  
  $(".photo_img img").attr("src",$("#file2").parent("a").attr("href"));
 
  //Slide Gallery 이미지 샐랙터
  $(".list_wrap ul li").click(function() {
  $(this).addClass("active").siblings();
  $(".photo_img img").attr("src",$(this).children("a").attr("href"));
  return false;
});

});

$(document).ready(function() {
    
  $(".list_wrap ul li a").parent().addClass("float_l");
  $(".list_wrap ul li div").parent().addClass("float_r");
    
});
/* 
$(document).ready(function() {   
$(".thumb").find('img').each(function(){
 if($(this).width() > 60)
    $(this).removeAttr("width").removeAttr("height").css("width","60");
}) ;

$(".img").find('img').each(function(){
 if($(this).width() > 400)
    $(this).removeAttr("width").removeAttr("height").css("width","400");
}) ;
});
*/

$(function(){
$("iframe.myFrame").load(function(){ //iframe 컨텐츠가 로드 된 후에 호출됩니다.
$(this).height($(this).contents().find('body')[0].scrollHeight + 120);
/*       var frame = $(this).get(0);
var doc = (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
$(this).height(doc.body.scrollHeight);
$(this).width(doc.body.scrollWidth); */
});
});
</script>


<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
  
function send_wish(hprice, nickname, title, thumb){
  <% if( session.getAttribute("userid") == null) { %>
  alert('로그인 한 사용자만 이용이 가능합니다');
  window.openModal();
  return false;
  <% } else { %>
   var url = document.location.href;
   location.href = '../favorite/create.do?nickname='+nickname+'&title='+title+'&hprice='+hprice+'&url='+url+'&thumb='+thumb; 
  return true;
  <% } %> 

}

function profile(userid, nickname){
  var url = '../member/profile.do?nickname='+nickname;
  var encodedInputString=escape(url);
  var win = window.open(url, '프로필', 'width=617.5px, height=600px');
  
  var x = (screen.width - 500) / 2;
  var y = (screen.height - 440) / 2;
  
  win.moveTo(x, y); // 화면 가운데로 이동
}


function msg_list(userid){
  <% if( session.getAttribute("userid") == null) { %>
  alert('로그인 한 사용자만 이용이 가능합니다');
  window.openModal();
  return false;
  <% } else { %>
  
  $("#detail").css("display","block");
  var url = '../message/create.do?userid='+userid;
  var encodedInputString=escape(url);
  var win = window.open(url, '프로필', 'width=750px, height=800px');
  
  var x = (screen.width - 500) / 2;
  var y = (screen.height - 440) / 2;
  
  win.moveTo(x, y); // 화면 가운데로 이동
  return true;
  <% } %> 
 };  
  
</script>
</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
    <jsp:include page="/menu/top.jsp" flush='false' />
    <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
   <div class="container"> 

<DIV class='content_form'>

    <FORM name='frm' method='POST' action='./update.do'>
      <input type='hidden' name='lno' value='${livingVO.lno}'>         
       
       <fieldset>
        <ul style="margin:50px 0;">
        <li class="float_l">
            <label for='title' >제목 : </label>
            <strong style="font-size: 24px;">${livingVO.title}</strong><br>
        </li>
        <li class="float_r text_r" style="">
           <label for='lno'>상품번호 : </label>
           <span>${livingVO.lno}</span> ·
           <label for='cnt' >조회수 : </label> 
           <span>${livingVO.cnt}</span>
        </li>
        <li class="both"><hr> </li>
     
        <li class="float_l" style="margin:40px 0; margin-right:15%;">
            <div class="photo_gallery">
            <p class="photo_img" style="width:300px; height:300px; border:1px solid lightgray; border-bottom:none;">
                <img src="http://i1.daumcdn.net/cfs.tistory/static/images/xBoxReplace_250.png" width="100%" height="100%" >
            </p>
            <div class="photo_list">
                <div class="list_wrap" style="width: 300px;">
                      <ul>
                        <li>
                          <c:set var='file2' value="${fn:toLowerCase(livingVO.file2)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file2, '.jpg')}">
                              <a href="./storage/${livingVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${livingVO.file2}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.gif')}">
                              <a href="./storage/${livingVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${livingVO.file2}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.png')}">
                              <a href="./storage/${livingVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${livingVO.file2}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file4' value="${fn:toLowerCase(livingVO.file4)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file4, '.jpg')}">
                              <a href="./storage/${livingVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${livingVO.file4}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.gif')}">
                              <a href="./storage/${livingVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${livingVO.file4}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.png')}">
                              <a href="./storage/${livingVO.file4}">
                                 <IMG id='file2' class="gallery" src='./storage/${livingVO.file4}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file6' value="${fn:toLowerCase(livingVO.file6)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file6, '.jpg')}">
                              <a href="./storage/${livingVO.file6}">
                                 <IMG id='file6' class="gallery" src='./storage/${livingVO.file6}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file6, '.gif')}">
                              <a href="./storage/${livingVO.file6}">
                                 <IMG id='file6' class="gallery" src='./storage/${livingVO.file6}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file6, '.png')}">
                              <a href="./storage/${livingVO.file3}">
                                 <IMG id='file6' class="gallery" src='./storage/${livingVO.file6}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file8' value="${fn:toLowerCase(livingVO.file8)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file8, '.jpg')}">
                              <a href="./storage/${livingVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${livingVO.file8}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file8, '.gif')}">
                              <a href="./storage/${livingVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${livingVO.file8}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file8, '.png')}">
                              <a href="./storage/${livingVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${livingVO.file8}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;">
                              <p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file10' value="${fn:toLowerCase(livingVO.file10)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file10, '.jpg')}">
                              <a href="./storage/${livingVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${livingVO.file10}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file10, '.gif')}">
                              <a href="./storage/${livingVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${livingVO.file10}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file10, '.png')}">
                              <a href="./storage/${livingVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${livingVO.file10}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                            
                            </c:otherwise>
                          </c:choose>
                        </li>
                    </ul>
                     
                </div>
            </div>
        </div>
        
         <div class="both"></div>
        </li>
         <li class="float_l" style="width:33%; margin:40px 0;">
         <div style="min-width:350px; width:100%;" >
         <strong>상품정보</strong>
         <div style="border-left:4px solid #B0E0E6;  padding-left:20px;">
            <table style='width: 100%;'> 
            <colgroup>
             <col style='width: 30%;'/>
             <col style='width: 70%;'/>
            </colgroup>
              <tr>
               <td>거래구분</td>
               <td>${livingVO.deal_code}</td>
              </tr>
              <tr>
               <td>상품구분</td>
               <td>${livingVO.product_code}</td>
              </tr>
              <tr>
               <td>거래지역</td>
               <td>${livingVO.region}</td>
              </tr>
              <tr>
               <td>거래방식</td>
               <td>${livingVO.deal_way }</td>
              </tr>
              <tr>
               <td>수량</td>
               <td>${livingVO.quantity}</td>
              </tr>
              <tr>
               <td>희망가격</td>
               <td>${livingVO.hprice}</td>
              </tr>
              </table>
         </div>
       
         <strong style="display:block; margin-top:20px;">판매자정보</strong>
         <div style="border-left:4px solid #D87093; padding-left:20px;">
         <table style='width: 100%;'> 
            <colgroup>
             <col style='width: 30%;'/>
             <col style='width: 70%;'/>
            <tr>
               <td>판매자</td>
               <td>
               <A href="javascript: profile(' ${livingVO.userid}' ,' ${livingVO.nickname}') ;" class='list_tag'  title='프로필'>${livingVO.nickname}</A>
               </td>
            </tr>
            <tr>
               <td>이메일</td>
               <td>${livingVO.email}</td>
            </tr>
            <tr>
               <td>연락처</td>
               <td>${livingVO.tel}</td>
            </tr>
         </table>
         </div>
         <div style="text-align: center;">
         <c:if test ="${(livingVO.userid ne userid)}">
        <A href="javascript: send_wish( ' ${livingVO.hprice}' ,' ${livingVO.nickname}' , ' ${livingVO.title}' ,' ${livingVO.file1 }' )  ;" class='top_select'  title='위시리스트'>
          <IMG src='../images/favorite_love.png' alt="WishList"></A>
        <A href="javascript: msg_list(' ${livingVO.userid}');" style="margin-left:50px" title='쪽지보내기'><IMG src='../images/Mail.png' alt="msgsend"></A>
       </c:if>
       </div>
         </div>
         <div class="both"></div>
      </li>
      <li class="both">
      <div style="width:100%; height:30px; line-height:30px; border-bottom:2px solid #3b78ce"><span style="">상세페이지</span></div>
      </li>
      <li>
      <hr>
      </li>
     <li style="display:block; width:60%; margin:0 auto;  min-height:300px;">
      <label for='content'></label>
      <span>${livingVO.content}</span>
    </li>
    <li>
     <hr>
    </li>
     <li class='text_r'>
        <c:if test="${(livingVO.userid eq userid)}">
      <button type="button" onclick="location.href='./update.do?lno=${livingVO.lno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
      <button type="button" onclick="location.href='./delete.do?lno=${livingVO.lno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
         </c:if>
      <button type="button" onclick="location.href='./list.do?lno=${livingVO.lno}&col=${searchDTO.col}&word=${searchDTO.word}'">목록</button>
 
     </li>
               
    </ul>
  </fieldset>
</FORM>

 
</DIV>


</div>
</body>
<iframe src="${pageContext.request.contextPath}/live_reply/list.do?lno=${livingVO.lno}" scrolling=no name=ce width=900 height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;"></iframe> 
<!-- -------------------------------------------- -->
</html> 