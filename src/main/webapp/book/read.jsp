<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript" src="./jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<style type="text/css">
        *{ 
    font-family: dotum,"돋움";
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
    list-style: none;
  }
</style>
<script type="text/javascript">
$(document).ready(function() {	
   $(".thumb").find('img').each(function(){
      if($(this).width() > 80)
         $(this).removeAttr("width").removeAttr("height").css("width","80");
   })	;
   
   $(".img").find('img').each(function(){
      if($(this).width() > 400)
         $(this).removeAttr("width").removeAttr("height").css("width","400");
   })	;
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
   <div class="container"> 
    <jsp:include page="/menu/old_top.jsp" flush='false' />
<!-- ----------------------------------------- -->

<DIV class='content_form'>
  <div class='content_menu' style='width: 100%;'>
    <A href='../book/list.do'>게시판 목록</A> >
    <A href='./list.do?bno=${bookVO.bno}'>${bookVO.title }</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
    <A href='./create.do?bno=${bookVO.bno}'>등록</A>｜
    <A href='./update.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>수정</A>｜
    <A href='./delete.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>｜
    <A href='./delete.do?bno= ${bookVO.bno} '>삭제</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
  </div>

    <FORM name='frm' method='POST' action='./update.do'>
      <input type='hidden' name='bno' value='${bookVO.bno}'>         
       
        
        <fieldset>
        <ul style="margin:50px 0;">
        <li class="float_l">
            <label for='title' >제목 : </label>
            <strong style="font-size: 24px;">${bookVO.title}</strong><br>
        </li>
        <li class="float_r text_r" style="">
           <label for='bno'>상품번호 : </label>
           <span>${bookVO.bno}</span> ·
           <label for='cnt' >조회수 : </label> 
           <span>${bookVO.cnt}</span>
        </li>
        <li class="both"><hr> </li>
     
        <li class="float_l" style=" margin:40px 0;">
        <div>
            <div class="img">
              <c:set var='file1' value="${fn:toLowerCase(bookVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' class="gallery" src='./storage/${bookVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG id='file1' class="gallery" src='./storage/${bookVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG id='file1' class="gallery" src='./storage/${bookVO.file1}'>
                </c:when>
              </c:choose>
            </div>
            <div class="img">
              <c:set var='file2' value="${fn:toLowerCase(bookVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' class="gallery" src='./storage/${bookVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  class="gallery" src='./storage/${bookVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2' class="gallery" src='./storage/${bookVO.file2}'>
                </c:when>
              </c:choose>
            </div>
            <div class="img">
              <c:set var='file3' value="${fn:toLowerCase(bookVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG id='file3' class="gallery" src='./storage/${bookVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file3' class="gallery" src='./storage/${bookVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file3' class="gallery" src='./storage/${bookVO.file3}'>
                </c:when>
              </c:choose>
            </div>
            <div class="img">
              <c:set var='file4' value="${fn:toLowerCase(bookVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' class="gallery" src='./storage/${bookVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4' class="gallery" src='./storage/${bookVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4' class="gallery" src='./storage/${bookVO.file4}'>
                </c:when>
              </c:choose>
            </div>
            <div class="img">
              <c:set var='file5' value="${fn:toLowerCase(bookVO.file5)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file5, '.jpg')}">
                  <IMG id='file5' class="gallery" src='./storage/${bookVO.file5}'>
                </c:when>
                <c:when test="${fn:endsWith(file5, '.gif')}">
                  <IMG id='file5' class="gallery" src='./storage/${bookVO.file5}'>
                </c:when>
                <c:when test="${fn:endsWith(file5, '.png')}">
                  <IMG id='file5' class="gallery" src='./storage/${bookVO.file5}'>
                </c:when>
              </c:choose>
            </div>
        </div>
         <div>
            <div class="float_l"><a class="thumb" href="#file2">
              <c:set var='file1' value="${fn:toLowerCase(bookVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file1}'>
                </c:when>
              </c:choose>
            </a></div>
            <div class="float_l"><a class="thumb" >
              <c:set var='file2' value="${fn:toLowerCase(bookVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file2}'>
                </c:when>
              </c:choose>
            </a></div>
            <div class="float_l"><a class="thumb" >
              <c:set var='file3' value="${fn:toLowerCase(bookVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file3, '.gif')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file3, '.png')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file3}'>
                </c:when>
              </c:choose>
            </a></div>
            <div class="float_l"><a class="thumb" >
              <c:set var='file4' value="${fn:toLowerCase(bookVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file4}'>
                </c:when>
              </c:choose>
            </a></div>
            <div class="float_l"><a class="thumb" >
              <c:set var='file5' value="${fn:toLowerCase(bookVO.file5)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file5, '.jpg')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file5}'>
                </c:when>
                <c:when test="${fn:endsWith(file5, '.gif')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file5}'>
                </c:when>
                <c:when test="${fn:endsWith(file5, '.png')}">
                  <IMG class="gallery_d" src='./storage/${bookVO.file5}'>
                </c:when>
              </c:choose>
            </a></div>
         </div>
         <div class="both"></div>
        </li>
         <li class="float_r" style="width:43%; margin:40px 0;">
         <div style="width:100%;" class="float_r">
         <strong>상품정보</strong>
         <div style="border-left:4px solid #B0E0E6;  padding-left:20px;">
            <table style='width: 100%;'> 
            <colgroup>
             <col style='width: 30%;'/>
             <col style='width: 70%;'/>
            </colgroup>
              <tr>
               <td>거래구분</td>
               <td>${bookVO.deal_code}</td>
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
       
         <strong style="display:block; margin-top:20px;">판매자정보</strong>
         <div style="border-left:4px solid #D87093; padding-left:20px;">
         <table style='width: 100%;'> 
            <colgroup>
             <col style='width: 30%;'/>
             <col style='width: 70%;'/>
            <tr>
               <td>판매자</td>
               <td>${bookVO.nickname}</td>
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
      <span>${bookVO.content}</span>
    </li>
    <li>
     <hr>
    </li>
     <li class='text_r'>
      <button type="button" onclick="location.href='./list.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}'">목록보기</button>
      <button type="button" onclick="location.href='./update.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
      <button type="button" onclick="location.href='./delete.do?bno=${bookVO.bno}'">삭제</button>
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
