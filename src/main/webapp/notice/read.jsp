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
  $(function(){
    $('#file2').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file2').width() > screen.width * 0.7){
        $('#file2').width('70%');      
      }
    });
    $('#file4').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file4').width() > screen.width * 0.7){
        $('#file4').width('70%');      
      }
    });
  });
  
</script>

<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>
</head> 
<body leftmargin="0" topmargin="0">
    <jsp:include page="/menu/top.jsp" flush='false' />
    <jsp:include page="/menu/left.jsp" flush='false' />
    
<DIV class='content_form'>
  <div class='content_menu' style='width: 100%;'>
    <A href='../notice/list.do'>게시판 목록</A> >
    <A href="javascript:location.reload();">새로고침</A>｜
    <A href='./create.do?noticeno=${noticeVO.noticeno}'>등록</A>｜
    <A href='./reply.do?noticeno=${noticeVO.noticeno}'>답변</A>｜
  </div>
     <FORM name='frm' method='POST' action='./update.do'>
      <input type='hidden' name='noticeno' value='${noticeVO.noticeno}'>         
  <fieldset>
    <ul style="margin:50px 0;">
     <li class="float_l">
            <label for='title' >제목 : </label>
            <strong style="font-size: 24px;">${noticeVO.title}</strong><br>
       </li>     
      <li class="both">
      <div style="width:100%; height:30px; line-height:30px; border-bottom:2px solid #3b78ce"><span style="">상세페이지</span></div>
      </li>
      <li>
      <hr>
      </li>
     <li style="display:block; width:60%; margin:0 auto;  min-height:300px;">
      <label for='content'></label>
      <span> ${noticeVO.content}</span>
    </li>
    
  <li><label for="file1">업로드 파일1: </label> <span> 
  <c:if test="${noticeVO.size2 > 0}">
        <A href='${pageContext.request.contextPath}/download?dir=/notice/storage&filename=${noticeVO.file2}'>${noticeVO.file2}</A> (${noticeVO.size2Label})
  </c:if>
  </span>
    <div id='file2Panel'>
      <c:set var='file2' value="${fn:toLowerCase(noticeVO.file2)}" />
      <c:choose>
        <c:when test="${fn:endsWith(file2, '.jpg')}">
          <IMG id='file2' src='./storage/${noticeVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.gif')}">
          <IMG id='file2' src='./storage/${noticeVO.file2}'>
        </c:when>
        <c:when test="${fn:endsWith(file2, '.png')}">
          <IMG id='file2' src='./storage/${noticeVO.file2}'>
        </c:when>
      </c:choose>
    </div></li>
   <li class='text_r'>
    <c:if test="${(noticeVO.userid eq userid)}">
  <button type='button' onclick="location.href='./update.do?noticeno=${noticeVO.noticeno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
  <button type='button' onclick="location.href='./delete.do?noticeno=${noticeVO.noticeno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
    </c:if>
  <button type='button' onclick="location.href='./list.do'">목록</button>
 </li>
</ul>
</fieldset>
</FORM>
</DIV>
</body>
<iframe src="${pageContext.request.contextPath}/nt_reply/list.do?noticeno=${noticeVO.noticeno}" class='myFrame' width="100%" style="border-style: none;"></iframe>  

<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>

<!-- -------------------------------------------- -->
</html> 