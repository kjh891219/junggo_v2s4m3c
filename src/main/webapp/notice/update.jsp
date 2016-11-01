<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>
  
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
</head> 

<DIV class='title'>상품 수정</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data">
   <input type='hidden' name='noticeno' id='noticeno' value='${vo.noticeno}'>
   <input type='hidden' name='userid' id='userid' value='${vo.userid}'>
  <fieldset>
    <ul>
   
      <li>
       <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value ='${vo.nickname}' required="required">
       </li>
      <hr/>
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value ='${vo.title}' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='content'>내용</label>
        <textarea rows ="10" cols = "40"  name = "content" id="content">${vo.content}</textarea>
      </li>
        <hr/>
      
     <div id='file2Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일1</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file2' value="${fn:toLowerCase(vo.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              <IMG src='./storage/${vo.file2}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              <IMG id='file2'  src='./storage/${vo.file2}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
              <IMG id='file2'  src='./storage/${vo.file2}'>
            </c:when>
            <c:when test="${vo.file2.length() > 0}">
              ${vo.file2 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      <div class="form-group">   
        <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일1</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
       </div>
       </div>
     <br>
      <li class='right'>
        <button type="submit">수정</button>
        <button type="button" onclick="location.href='./list.jsp'">취소</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
</DIV>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 