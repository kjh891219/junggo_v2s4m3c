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
<link href="${pageContext.request.contextPath}/css/style.css?ver=1" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>
<script type="text/javascript">

   $(document).ready(function() {
     
       $(".photo_img img").attr("src",$("#file1").parent("a").attr("href"));
      
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

$(function(){
   $("iframe.myFrame").load(function(){ //iframe 컨텐츠가 로드 된 후에 호출됩니다.
     $(this).height($(this).contents().find('body')[0].scrollHeight + 120);
   
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
 }
  
  
</script>

<style type="text/css">
   .gallery{
      width:80px; 
      height:80px; 
      vertical-align:middle; 
      display:table-cell; 
      text-align: center; 
      background:lightgray;
      /* box-shadow:2px 2px 2px gray;  */
   }
       button{
      background-color: transparent;
      border:1px solid lightgray;
      padding:2px;
    }
</style>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
    <jsp:include page="/menu/top.jsp" flush='false' />
    <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->


   <div class="container"> 

<DIV class='content_form'>
 <%--  <div class='content_menu' style='width: 100%; min-width: 400px;'>
    <A href='../book/list.do'>게시판 목록</A> >
    <A >${bookVO.title }</A>｜
    <A href='./create.do?bno=${bookVO.bno}'>등록</A>｜
    <A href='./update.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>수정</A>｜
    <A href='./delete.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>｜
    <A href="javascript:location.reload();">새로고침</A>
  </div> --%>

    <FORM name='frm' method='POST' action='./update.do'>
      <input type='hidden' name='bno' value='${bookVO.bno}'>         
       
        
        <fieldset>
        <ul style="margin:50px 0; ">
        <li style="display: block;
             height: 35px; border-bottom:3px solid black; /* border-radius: 50px; */  ">
        	<div class="float_l" style="padding-left:20px; margin-top:5px;width:50%;">
	            <label for='title' >제목 : </label>
	            <strong style="font-size: 18px;">${bookVO.title}</strong><br>
        	</div>
        	<div class="float_r" style="margin-top:5px; padding-right:20px; text-align: right;">
	           <label for='bno'>상품번호 : </label>
	           <span>${bookVO.bno}</span> ·
	           <label for='cnt' >조회수 : </label> 
	           <span>${bookVO.cnt}</span>
              <label for='wdate' >등록일 : </label> 
              <span>${bookVO.wdate.substring(0,10)}</span>
        	</div>
        	<div class="both"></div>
        </li>
     
        <li class="float_l" style="margin:40px 5%; margin-right:10%; ">
            <div class="photo_gallery">
            <p class="photo_img" style="width:400px; height:400px; vertical-align:middle; display:table-cell; ">
                <img src="http://i1.daumcdn.net/cfs.tistory/static/images/xBoxReplace_250.png" width="100%" style="margin: auto 0 ;" >
            </p>
            <div class="photo_list">
                <div class="list_wrap" style="width: 400px;">
                      <ul>
                        <li>
                          <c:set var='file1' value="${fn:toLowerCase(bookVO.file1)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file1, '.jpg')}">
                              <a class="gallery" href="./storage/${bookVO.file1}">
                                 <IMG id='file1' src='./storage/${bookVO.file1}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file1, '.gif')}">
                              <a class="gallery" href="./storage/${bookVO.file1}">
                                 <IMG id='file1' src='./storage/${bookVO.file1}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file1, '.png')}">
                              <a class="gallery" href="./storage/${bookVO.file1}">
                                 <IMG id='file1' src='./storage/${bookVO.file1}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div class="gallery"><p style="font-size:9px;">이미지<br>없음</p></div>                             
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file2' value="${fn:toLowerCase(bookVO.file2)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file2, '.jpg')}">
                              <a class="gallery" href="./storage/${bookVO.file2}">
                                 <IMG id='file2' src='./storage/${bookVO.file2}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.gif')}">
                              <a class="gallery" href="./storage/${bookVO.file2}">
                                 <IMG id='file2' src='./storage/${bookVO.file2}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.png')}">
                              <a class="gallery" href="./storage/${bookVO.file2}">
                                 <IMG id='file2' src='./storage/${bookVO.file2}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div class="gallery"><p style="font-size:9px;">이미지<br>없음</p></div>                             
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file3' value="${fn:toLowerCase(bookVO.file3)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file3, '.jpg')}">
                              <a class="gallery" href="./storage/${bookVO.file3}">
                                 <IMG id='file3' src='./storage/${bookVO.file3}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file3, '.gif')}">
                              <a class="gallery" href="./storage/${bookVO.file3}">
                                 <IMG id='file3' src='./storage/${bookVO.file3}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file3, '.png')}">
                              <a class="gallery" href="./storage/${bookVO.file3}">
                                 <IMG id='file3' src='./storage/${bookVO.file3}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div class="gallery"><p style="font-size:9px;">이미지<br>없음</p></div>                             
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file4' value="${fn:toLowerCase(bookVO.file4)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file4, '.jpg')}">
                              <a class="gallery" href="./storage/${bookVO.file4}">
                                 <IMG id='file4' src='./storage/${bookVO.file4}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.gif')}">
                              <a class="gallery" href="./storage/${bookVO.file4}">
                                 <IMG id='file4' src='./storage/${bookVO.file4}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.png')}">
                              <a class="gallery" href="./storage/${bookVO.file4}">
                                 <IMG id='file4' src='./storage/${bookVO.file4}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div class="gallery"><p style="font-size:9px;">이미지<br>없음</p></div>                             
                            </c:otherwise>
                          </c:choose>
                        </li>
                        <li>
                          <c:set var='file5' value="${fn:toLowerCase(bookVO.file5)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file5, '.jpg')}">
                              <a class="gallery" href="./storage/${bookVO.file5}">
                                 <IMG id='file5' src='./storage/${bookVO.file5}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file5, '.gif')}">
                              <a class="gallery" href="./storage/${bookVO.file5}">
                                 <IMG id='file5' src='./storage/${bookVO.file5}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file5, '.png')}">
                              <a class="gallery" href="./storage/${bookVO.file5}">
                                 <IMG id='file5' src='./storage/${bookVO.file5}' width="100%" height="100%">
                              </a>
                            </c:when>
                            <c:otherwise>
                              <div class="gallery"><p style="font-size:9px;">이미지<br>없음</p></div>                            
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
          <div class="text_c" style="margin-bottom:10px;">
            <strong style="font-size: 18px;">상품정보</strong>
         </div>
         <div style="border-left:4px solid #B0E0E6;  padding-left:20px;">
            <table class="table" style='width: 100%;'> 
            <colgroup>
             <col style='width: 30%;'/>
             <col style='width: 70%;'/>
            </colgroup>
              <tr>
               <td style="border:none;">거래구분</td>
               <td style="border:none;">${bookVO.deal_code}</td>
              </tr>
              <tr>
               <td>상품구분</td>
               <td>${bookVO.product_code}</td>
              </tr>
              <tr>
               <td>거래지역</td>
               <td>${bookVO.region}</td>
              </tr>
              <tr>
               <td>거래방식</td>
               <td>${bookVO.deal_way }</td>
              </tr>
              <tr>
               <td>책제목</td>
               <td>${bookVO.btitle}</td>
              </tr>
              <tr>
               <td>출판사</td>
               <td>${bookVO.publisher }</td>
              </tr>
              <tr>
               <td>저자</td>
               <td>${bookVO.bwriter}</td>
              </tr>
              <tr>
               <td>수량</td>
               <td>${bookVO.quantity}</td>
              </tr>
              <tr>
               <td>희망가격</td>
               <td>${bookVO.hprice}</td>
              </tr>
              </table>
         </div>
       
         <div class="text_c" style="margin-bottom:10px; margin-top:20px;">
            <strong style="font-size: 18px;">판매자정보</strong>
         </div>
         <div style="border-left:4px solid #D87093; padding-left:20px;">
         <table class="table" style='width: 100%;'> 
            <colgroup>
             <col style='width: 30%;'/>
             <col style='width: 70%;'/>
            <tr>
               <td style="border:none;">판매자</td>
               <td style="border:none;">
               <A href="javascript: profile(' ${bookVO.userid}' ,' ${bookVO.nickname}') ;" class='list_tag'  title='프로필'>${bookVO.nickname}</A>
               </td>
            </tr>
            <tr>
               <td>이메일</td>
               <td>${bookVO.email}</td>
            </tr>
            <tr>
               <td>연락처</td>
               <td>${bookVO.tel}</td>
            </tr>
         </table>
         </div>
         <div style="text-align: center;">
         <c:if test ="${(bookVO.userid ne userid)}">
        <A href="javascript: send_wish( ' ${bookVO.hprice}' ,' ${bookVO.nickname}' , ' ${bookVO.title}' ,' ${bookVO.thumb }' )  ;" class='top_select'  title='위시리스트'>
          <IMG src='../images/favorite_love.png' alt="WishList"></A>
        <A href="javascript: msg_list(' ${bookVO.userid}');" style="margin-left:50px" title='쪽지보내기'><IMG src='../images/Mail.png' alt="msgsend"></A>
       </c:if>
       </div>
         </div>
         <div class="both"></div>
      </li>
      <li class="both">
      <div style="width:100%; height:30px; line-height:30px; border-bottom:2px solid #3b78ce"><span style="">상세페이지</span></div>
      </li>
      <li>
      </li>
     <li style="display:block; width:80%; margin:0 auto;  min-height:300px;">
      <label for='content'></label>
       <div style="padding-top:30px;">
      <span>${bookVO.content}</span>
      </div>
    </li>
    <li>
     <hr>
    </li>
     <li class='text_r'>
      <button type="button" onclick="location.href='./list.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}'">목록보기</button>
      <c:if test ="${(bookVO.userid eq userid)}">  
      <button type="button" onclick="location.href='./update.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
      <button type="button" onclick="location.href='./delete.do?bno=${bookVO.bno}'">삭제</button>
      </c:if>
     </li>
               
    </ul>
  </fieldset>
</FORM>

<iframe src="${pageContext.request.contextPath}/breply/list.do?bno=${bookVO.bno}" class='myFrame' width="100%" style="border-style: none;"></iframe>  
</DIV>

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</div>
</body>
<!-- -------------------------------------------- -->
</html> 
