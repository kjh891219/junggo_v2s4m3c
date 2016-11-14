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
</script>
</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
    <jsp:include page="/menu/top.jsp" flush='false' />
    <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
   <div class="container"> 

<DIV class='content_form'>
  <div class='content_menu' style='width: 100%;'>
    <A href='../game/list.do'>게시판 목록</A> >
    <A href='./list.do?gno=${gameVO.gno}'>${gameVO.title }</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
    <A href='./create.do?gno=${gameVO.gno}'>등록</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
  </div>

    <FORM name='frm' method='POST' action='./update.do'>
      <input type='hidden' name='gno' value='${gameVO.gno}'>         
       
       <fieldset>
        <ul style="margin:50px 0;">
        <li class="float_l">
            <label for='title' >제목 : </label>
            <strong style="font-size: 24px;">${gameVO.title}</strong><br>
        </li>
        <li class="float_r text_r" style="">
           <label for='gno'>상품번호 : </label>
           <span>${gameVO.gno}</span> ·
           <label for='cnt' >조회수 : </label> 
           <span>${gameVO.cnt}</span>
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
                          <c:set var='file2' value="${fn:toLowerCase(gameVO.file2)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file2, '.jpg')}">
                              <a href="./storage/${gameVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${gameVO.file2}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.gif')}">
                              <a href="./storage/${gameVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${gameVO.file2}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.png')}">
                              <a href="./storage/${gameVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${gameVO.file2}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file4' value="${fn:toLowerCase(gameVO.file4)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file4, '.jpg')}">
                              <a href="./storage/${gameVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${gameVO.file4}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.gif')}">
                              <a href="./storage/${gameVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${gameVO.file4}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.png')}">
                              <a href="./storage/${gameVO.file4}">
                                 <IMG id='file2' class="gallery" src='./storage/${gameVO.file4}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file6' value="${fn:toLowerCase(gameVO.file6)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file6, '.jpg')}">
                              <a href="./storage/${gameVO.file6}">
                                 <IMG id='file6' class="gallery" src='./storage/${gameVO.file6}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file6, '.gif')}">
                              <a href="./storage/${gameVO.file6}">
                                 <IMG id='file6' class="gallery" src='./storage/${gameVO.file6}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file6, '.png')}">
                              <a href="./storage/${gameVO.file3}">
                                 <IMG id='file6' class="gallery" src='./storage/${gameVO.file6}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;"><p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file8' value="${fn:toLowerCase(gameVO.file8)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file8, '.jpg')}">
                              <a href="./storage/${gameVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${gameVO.file8}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file8, '.gif')}">
                              <a href="./storage/${gameVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${gameVO.file8}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file8, '.png')}">
                              <a href="./storage/${gameVO.file8}">
                                 <IMG id='file8' class="gallery" src='./storage/${gameVO.file8}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div style="width:60px; height:60px; text-align: center; background:lightgray;">
                              <p style="font-size:9px; line-height: 60px;">이미지 없음</p></div>                           
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file10' value="${fn:toLowerCase(gameVO.file10)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file10, '.jpg')}">
                              <a href="./storage/${gameVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${gameVO.file10}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file10, '.gif')}">
                              <a href="./storage/${gameVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${gameVO.file10}' width="60" height="60" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file10, '.png')}">
                              <a href="./storage/${gameVO.file10}">
                                 <IMG id='file10' class="gallery" src='./storage/${gameVO.file10}' width="60" height="60" >
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
               <td>${gameVO.deal_code}</td>
              </tr>
              <tr>
               <td>상품구분</td>
               <td>${gameVO.product_code}</td>
              </tr>
              <tr>
               <td>거래지역</td>
               <td>${gameVO.region}</td>
              </tr>
              <tr>
               <td>거래방식</td>
               <td>${gameVO.deal_way }</td>
              </tr>
              <tr>
               <td>수량</td>
               <td>${gameVO.quantity}</td>
              </tr>
              <tr>
               <td>희망가격</td>
               <td>${gameVO.hprice}</td>
              </tr>
              <tr>
               <td>평점</td>
               <td>${gameVO.lev}</td>
              </tr>
              <tr>
               <td>장르</td>
               <td>${gameVO.genre}</td>
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
               <td>${gameVO.nickname}</td>
            </tr>
            <tr>
               <td>이메일</td>
               <td>${gameVO.email}</td>
            </tr>
            <tr>
               <td>연락처</td>
               <td>${gameVO.tel}</td>
            </tr>
         </table>
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
      <span>${gameVO.content}</span>
    </li>
    <li>
     <hr>
    </li>
     <li class='text_r'>
        <c:if test="${(gameVO.userid eq userid)}">
      <button type="button" onclick="location.href='./update.do?gno=${gameVO.gno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
      <button type="button" onclick="location.href='./delete.do?gno=${gameVO.gno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
         </c:if>
      <button type="button" onclick="location.href='./list.do?gno=${gameVO.gno}&col=${searchDTO.col}&word=${searchDTO.word}'">목록</button>
 
     </li>
               
    </ul>
  </fieldset>
</FORM>

 
</DIV>


</div>
</body>
<iframe src="${pageContext.request.contextPath}/game_reply/list.do?gno=${gameVO.gno}" class='myFrame' width="100%" style="border-style: none;"></iframe> 
<!-- -------------------------------------------- -->
</html> 