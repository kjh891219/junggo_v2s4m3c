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
<script type="text/javascript" src="../js/event.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>
 
<script type="text/javascript">
$(function(){
 
});
$(document).ready(function() {
  
  if($(".left").height() < $(".right").height()){
     $(".left").height($(".right").height());
  }
  
});

</script>
 <style type="text/css">
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
     <jsp:include page="/menu/community_left.jsp" flush='false' />
<div class="float_l right " style="width:80%;">
<div class="container" style="width:90%;">
<!-- ----------------------------------------- -->

<DIV class='content' >
  <FORM name='frm' method='POST' action='./update.do'
              enctype="multipart/form-data">
    <input type='hidden' name='qnano' id='qnano' value='${qnaVO.qnano}'>
    <input type="hidden" name="categoryno" value="${qnaVO.categoryno}">
    <DIV class="text_c">문의게시판</DIV>
  
    <ul>
      <li class="row">
        <label for='title' class="col-xs-1 col-lg-1">제목</label>
        <input type='text' name='title' size='70' id='title' value='${qnaVO.title}' required="required" class="col-xs-10 col-lg-10"  >
      </li>
      <li>
         <label for='content'>내용</label>
        <textarea name='content' id='content' rows='29' style='width: 100%;'>${qnaVO.content}</textarea>
      </li>
      <li>
         
         <span style="margin-left:18%;">      
         Preview(미리보기) 이미지 자동 생성됩니다.
         </span>
         <div class='row'>
          <label for="file1" class="col-xs-2 col-lg-2">업로드 파일1 </label>
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file1MF' id='file1MF' size='40'> <br>
            </div>
          </div>
          <div class='row'>
            <label for="file2" class="col-xs-2 col-lg-2">업로드 파일2 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file2MF' id='file2MF' size='20'> <br>
            </div>
         </div>
          <div class='row'>
            <label for="file3" class="col-xs-2 col-lg-2">업로드 파일3 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file3MF'
                  id='file3MF' size='20'> <br>
            </div>
         
          </div>
          <div class='row'>
            <label for="file4" class="col-xs-2 col-lg-2">업로드 파일4 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file4MF'
                  id='file4MF' size='20'> <br>
            </div>
          </div>
          <div class='row'>
            <label for="file5" class="col-xs-2 col-lg-2">업로드 파일5 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file5MF'
                  id='file5MF' size='20'> <br>
            </div>
          </div>
      </li>
 
      <li class='text_r right'>
        <button type="submit">수정</button>
        <button type="button" onclick="history.back()">취소</button>
      </li>         
    </ul>
  </FORM>
</DIV>
</div>
</div>
<div class="both"></div>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />     
</body>
<!-- -------------------------------------------- -->
</html> 