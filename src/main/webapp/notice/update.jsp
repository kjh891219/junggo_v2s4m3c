<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
 <script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
          <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
  $(document).ready(function() {
     
     if($(".left").height() < $(".right").height()){
          $(".left").height($(".right").height());
     }
     
   });
</script>
  
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
<body>



<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<jsp:include page="/menu/community_left.jsp" flush='false' />

<div class="float_l right " style="width:80%; ">
   <DIV class='container' style="width:90%; min-height:380px;"> 

<DIV class='title'>상품 수정</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data">
   <input type='hidden' name='noticeno' id='noticeno' value='${vo.noticeno}'>
   <input type='hidden' name='userid' id='userid' value='${vo.userid}'>
  <fieldset>
    <ul>
   
      <li>
      <div class="row">
       <label for='nickname' class="col-xs-2 col-lg-2">닉네임</label>
        <input type='text' name='nickname' id='nickname' value ='${vo.nickname}' required="required">
      </div>
      <hr/>
       </li>
      <li>
      <div class="row">
        <label for='title' class="col-xs-2 col-lg-2">제목</label>
        <input type='text' name='title' id='title' value ='${vo.title}' required="required" class="col-xs-9 col-lg-9">
      </div>
      <hr/>
      </li>
      <li>
        <label for='content'>내용</label>
        <textarea rows ="29" cols = "40"  name = "content" id="content">${vo.content}</textarea>
        <hr/>
      </li>
      <li>
     <div id='file2Panel' class="row">
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
      </li>
      <li>
      <div class="row">   
        <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일1</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
       </div>
       </div>
     <br>
     </li>
      <li class='text_r'>
        <button type="submit">수정</button>
        <button type="button" onclick="location.href='./list.jsp'">취소</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
</DIV>
</DIV>
</DIV>
<div class="both"></div>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 