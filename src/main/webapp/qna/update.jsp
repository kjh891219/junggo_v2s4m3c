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
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>
 
<script type="text/javascript">
$(function(){
 
});
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<div class="container">
<!-- ----------------------------------------- -->
<div class='content_menu'>
  <A href='./list2.do?category=${qnaVO.categoryno}'></A>
</div> 
<DIV class='content' style='width: 90%;'>
  <FORM name='frm' method='POST' action='./update.do'
              enctype="multipart/form-data">
    <input type='hidden' name='qnano' id='qnano' value='${qnaVO.qnano}'>
    <input type="hidden" name="categoryno" value="${qnaVO.categoryno}">
  
    <ul>
      <li>
        <label for='title' >제목</label>
        <input type='text' name='title' size='70' id='title' value='${qnaVO.title}' required="required">
      </li>
      <li>
         <label for='content'>내용</label>
        <textarea name='content' id='content' rows='10' style='width: 100%;'>${qnaVO.content}</textarea>
      </li>
      <li>
        <div id="file2Panel">
           <c:set var='file2' value="${fn:toLowerCase(qnaVO.file2)}" />
           <c:choose>
             <c:when test="${fn:endsWith(file2, '.jpg')}">
                등록된 이미지: <IMG id='file2' src='./storage/${qnaVO.file2}'>
             </c:when>
             <c:when test="${fn:endsWith(file2, '.gif')}" >
                등록된 이미지: <IMG id='file2'  src='./storage/${qnaVO.file2}'>
             </c:when>
             <c:when test="${fn:endsWith(file2, '.png')}">
                등록된 이미지: <IMG id='file2'  src='./storage/${qnaVO.file2}'>
             <c:when test="${qnaVO.file2.length() > 0}">
                등록된 파일: ${qnaVO.file2 } 
             </c:when>
             </c:when>
           </c:choose>
         </div>
      </li>
      
      <li>
        <label for='file2MF' class='label'>업로드 파일</label><!-- 추후 구현 -->
         <input type="file" name='file2MF' id='file2MF' size='40' >
      </li>
 
      <li class='right'>
        <button type="submit">수정</button>
        <button type="button" onclick="location.href='./list.do?'">목록[취소]</button>
      </li>         
    </ul>
  </FORM>
</DIV>
 
<!-- -------------------------------------------- -->
</div>
</body>
<!-- -------------------------------------------- -->
</html> 