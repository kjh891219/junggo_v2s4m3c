<%@ page contentType="text/html; charset=UTF-8"%>
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

<script>

  $(function(){
    $('#file2').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file2').width() > screen.width * 0.7){
        $('#file2').width('70%');      
      }
    });
  });
  
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

</head>

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<DIV class="container">
     
<DIV class='content'>
  <div class='content_menu' style='width: 100%;'>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>목록</A>>
    <A href="javascript:location.reload();" class='top_select'>새로고침</A>｜
    <A href='./create.do' class='top_select'>등록</A>｜
    <A href='./update.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>수정</A>｜
    <A href='./delete.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>
  </div>

  
    <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="ano" value="${artVO.ano}">
      <fieldset>
        <ul style="margin:50px 0;">
          <li class="float_l" style="width:100%;">
          
          <h1 style="padding-left: 0px; margin: 0; padding: 10px; float: left; display: block; font-size: 20px; line-height: 22px; font-weight: bold; color: #444;">
          <a href="/junggo/camera/list.do">예술/문화 게시판</a>
          </h1>

          <div style="clear: both;"></div>

          <div style="padding: 11px; border-top: 1px solid #CCC; border-bottom: 1px solid #CCC; background: #FCFCFC; line-height: 1.5em; display: block; width:100%;">
          <span style="float: right; display: inline; font-weight: 400; letter-spacing: 0; color: #444; font-size: 12px;">${artVO.wdate.substring(0, 16)}</span>
          <h1 style="font-size: 20px; font-weight: bold; color: #444; overflow: hidden; margin: 0; padding: 0 8px; line-height: 18px;">${artVO.title}</h1>
          </div>

          <div style="clear: both;"></div>
          
          </li>
          <li class="both"><hr> </li>
          
          <li class="float_l" style=" margin:40px 0;">
            <div class="photo_gallery">
            <p class="photo_img" style="width:400px; height:400px;">
                <img src="http://i1.daumcdn.net/cfs.tistory/static/images/xBoxReplace_250.png" width="100%" height="100%" >
            </p>
            <div class="photo_list">
                <div class="list_wrap" style="width: 520px;"><ul>
                        <li class="float_l">
                          <c:set var='file1' value="${fn:toLowerCase(artVO.file1)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file1, '.jpg')}">
                              <a href="./storage/${artVO.file1}">
                                 <IMG id='file1' class="gallery" src='./storage/${artVO.file1}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file1, '.gif')}">
                              <a href="./storage/${artVO.file1}">
                                 <IMG id='file1' class="gallery" src='./storage/${artVO.file1}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file1, '.png')}">
                              <a href="./storage/${artVO.file1}">
                                 <IMG id='file1' class="gallery" src='./storage/${artVO.file1}' width="80" >
                              </a>
                            </c:when>
                          </c:choose>
                        </li>
                        <li class="float_l">
                          <c:set var='file2' value="${fn:toLowerCase(artVO.file2)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file2, '.jpg')}">
                              <a href="./storage/${artVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${artVO.file2}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.gif')}">
                              <a href="./storage/${artVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${artVO.file2}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file2, '.png')}">
                              <a href="./storage/${artVO.file2}">
                                 <IMG id='file2' class="gallery" src='./storage/${artVO.file2}' width="80" >
                              </a>
                            </c:when>
                          </c:choose>
                        </li>
                        <li class="float_l">
                          <c:set var='file3' value="${fn:toLowerCase(artVO.file3)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file3, '.jpg')}">
                              <a href="./storage/${artVO.file3}">
                                 <IMG id='file3' class="gallery" src='./storage/${artVO.file3}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file3, '.gif')}">
                              <a href="./storage/${artVO.file3}">
                                 <IMG id='file3' class="gallery" src='./storage/${artVO.file3}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file3, '.png')}">
                              <a href="./storage/${artVO.file3}">
                                 <IMG id='file3' class="gallery" src='./storage/${artVO.file3}' width="80" >
                              </a>
                            </c:when>
                          </c:choose>
                        </li>
                        <li class="float_l">
                          <c:set var='file4' value="${fn:toLowerCase(artVO.file4)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file4, '.jpg')}">
                              <a href="./storage/${artVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${artVO.file4}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.gif')}">
                              <a href="./storage/${artVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${artVO.file4}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file4, '.png')}">
                              <a href="./storage/${artVO.file4}">
                                 <IMG id='file4' class="gallery" src='./storage/${artVO.file4}' width="80" >
                              </a>
                            </c:when>
                          </c:choose>
                        </li>
                        <li class="float_l">
                          <c:set var='file5' value="${fn:toLowerCase(artVO.file5)}" />
                          <c:choose>
                            <c:when test="${fn:endsWith(file5, '.jpg')}">
                              <a href="./storage/${artVO.file5}">
                                 <IMG id='file5' class="gallery" src='./storage/${artVO.file5}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file5, '.gif')}">
                              <a href="./storage/${artVO.file5}">
                                 <IMG id='file5' class="gallery" src='./storage/${artVO.file5}' width="80" >
                              </a>
                            </c:when>
                            <c:when test="${fn:endsWith(file5, '.png')}">
                              <a href="./storage/${artVO.file5}">
                                 <IMG id='file5' class="gallery" src='./storage/${artVO.file5}' width="80" >
                              </a>
                            </c:when>
                          </c:choose>
                        </li>
                    </ul>
                     
                </div>
            </div>
        </div>
          
        <div class="both"></div>
        </li>
         <li class="float_r" style="width:43%; margin:40px 0;">
         <div style="width:100%;" class="float_r">
         <!-- <strong>상품정보</strong> -->
         <span style="font-size: 16px; font-weight:bold;">PRODUCT INFO</span>&nbsp;<span style="color: #8C8C8C">제품정보</span>
         <div style="padding: 11px; border: 1px solid #c6cae2; background: #FCFCFC; line-height: 1.5em; display: block; width:100%;">
          <span style="float: right; display: inline; font-weight: 400; letter-spacing: 0; color: #444; font-size: 12px;">${artVO.hprice}</span>
          <h1 style="font-size: 16px; font-weight: bold; color: #444; overflow: hidden; margin: 0; padding: 0 8px; line-height: 18px;">판매가</h1>
          </div>
         
         <div style="border-left:4px solid #B0E0E6;  padding-left:20px;">
            <table style='width: 100%;'> 
            <colgroup>
             <col style='width: 30%;'/>
             <col style='width: 70%;'/>
            </colgroup>
              <tr>
               <td>카테고리</td>
               <td>${artVO.category}</td>
              </tr>
              <tr>
               <td>거래구분</td>
               <td>${artVO.deal_code}</td>
              </tr>
              <tr>
               <td>상품구분</td>
               <td>${artVO.product_code}</td>
              </tr>
              <tr>
               <td>거래지역</td>
               <td>${artVO.region}</td>
              </tr>
              <tr>
               <td>거래방식</td>
               <td>${artVO.deal_way }</td>
              </tr>
              <tr>
               <td>구입시기</td>
               <td>${artVO.purc_date}</td>
              </tr>
              <tr>
               <td>희망가격</td>
               <td>${artVO.hprice}</td>
              </tr>
              <tr>
               <td>수량</td>
               <td>${artVO.quantity}</td>
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
               <A href="javascript: profile(' ${artVO.userid}' ,' ${artVO.nickname}') ;" class='list_tag'  title='프로필'>${artVO.nickname}</A>
               </td>
            </tr>
            <tr>
               <td>이메일</td>
               <td>${artVO.email}</td>
            </tr>
            <tr>
               <td>연락처</td>
               <td>${artVO.tel}</td>
            </tr>
            <tr>
               <td>등록일</td>
               <td>${artVO.wdate.substring(0, 16)}</td>
            </tr>
         </table>
         </div>
         <div style="text-align: center;">
         <c:if test ="${(artVO.userid ne userid)}">
        <A href="javascript: send_wish( ' ${artVO.hprice}' ,' ${artVO.nickname}' , ' ${artVO.title}' ,' ${artVO.thumb }' )  ;" class='top_select'  title='위시리스트'>
          <IMG src='../images/favorite_love.png' alt="WishList"></A>
        <A href="javascript: msg_list(' ${artVO.userid}');" style="margin-left:50px" title='쪽지보내기'><IMG src='../images/Mail.png' alt="msgsend"></A>
       </c:if>
       </div>
         </div>
         <div class="both"></div>
      </li>
      <li class="both">
      <div style="width:100%; height:30px; line-height:30px; border-bottom:2px solid #8C8C8C">
      <span style="font-size: 16px; font-weight:bold;">MORE INFO</span>&nbsp;<span style="color: #8C8C8C">추가정보</span></div>
      </li>
      <li>
      </li>
     <li style="display:block; width:80%; margin:0 auto;  min-height:300px;">
      <label for='content'></label>
      <br>
      <span>${artVO.content}</span>
    </li>
    <li>
     <hr>
    </li>
     <li class='text_r'>
      <button type="button" onclick="location.href='./list.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}'">목록보기</button>
      <button type="button" onclick="location.href='./update.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
      <button type="button" onclick="location.href='./delete.do?ano=${artVO.ano}'">삭제</button>
     </li>
    </ul>
  </fieldset>
</FORM>  
</DIV>
 
<iframe src="${pageContext.request.contextPath}/art_reply/list.do?ano=${artVO.ano}" scrolling=no name=ce width=900 height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;"></iframe>  

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</DIV>
</body>
<!-- -------------------------------------------- -->
</html> 
