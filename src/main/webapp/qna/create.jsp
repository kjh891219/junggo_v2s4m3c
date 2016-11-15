<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
 
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
<!-- ----------------------------------------- -->
 
<div class="float_l right " style="width:80%;">
 
<DIV class='container' style="width:90%;"> 

<DIV class='title'>문의사항 등록</DIV>
<FORM name='frm' method='POST' action='./create.do'
              enctype="multipart/form-data">
              <input type="hidden" id="passwd" name="passwd" value='${pwd }'/>
              
<DIV class='content_form'>
   <DIV style="margin-top:20px;">
      <div class='row'>
        <label class='col-xs-2 col-lg-2' for='categoryno'>카테고리</label>
        <select name='categoryno' id='categoryno' class="col-xs-3 col-lg-3">
         <option value="1">회원가입 및 로그인</option>
         <option value="2">배송문의</option>
         <option value="3">기타문의</option>
        </select>
      </div>
      <hr/>
      
      <div class="row">
        <label for='title' class='col-xs-2 col-lg-2 '>제목</label>
        <input type='text' name='title' id='title' required="required" value='문의합니다' class="col-xs-9 col-lg-9">
      </div>
      <div class="row">
        <label for='content' class='col-xs-2 col-lg-2 ' id="textarea">상세설명</label>
        <textarea rows="10" name="content" id="content" placeholder="내용을 입력하세요"  class="col-xs-9 col-lg-9">내용</textarea>
      </div>
      <hr/>
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
      <hr>
      <div class="row">
         <label class='col-xs-2 col-lg-2' for='userid'>작성자</label>
         <span class="col-xs-3 col-lg-3">${userid}</span>
         <input type='hidden' name='userid' id='userid' value='${userid }' class="col-xs-3 col-lg-3">
      </div>
      <div class='row'>
       <label class='col-xs-2 col-lg-2 ' for='email'>이메일</label> 
       <span class="col-xs-3 col-lg-3">${email}</span> 
       <input type="hidden" name="email" value="${email}"> 
      </div>
      <div class='row'>
       <label class='col-xs-2 col-lg-2 ' for='tel'>등록자 연락처</label> 
       <span class="col-xs-3 col-lg-3">${tel}</span> 
       <input type="hidden" name="tel" value="${tel}"> 
      </div>
      <div class='row'> 
       <label class='col-xs-2 col-lg-2 ' for='nickname'>등록자 별명</label>
       <span class="col-xs-3 col-lg-3">${nickname}</span> 
       <input type="hidden" name="nickname" value="${nickname}"> 
      </div>   
      <!-- 
      <div class="row">  
        <label for='email' class='col-xs-2 col-lg-2'>이메일</label>
        <input type='text' name='email' id='email'  required="required" value=''  class="col-xs-3 col-lg-3">
      </div>
       -->
      <div class='text_r' >
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">취소</button>
      </div>
    </DIV>
  </DIV>    
</FORM>
</DIV>
  </div>
<div class="both"></div>
<!-- -------------------------------------------- -->
     <jsp:include page="/menu/bottom.jsp" flush='false' />  
</body>
<!-- -------------------------------------------- -->
</html> 
 