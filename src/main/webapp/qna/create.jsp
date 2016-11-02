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
<link href="/junggo/css/style.css" rel="Stylesheet" type="text/css">
 
<script type="text/javascript">
$(function(){
 
});
</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<!-- ----------------------------------------- -->
 
 
<DIV class=container> 

<DIV class='title'>문의사항 등록</DIV>
<FORM name='frm' method='POST' action='./create.do'
              enctype="multipart/form-data">
              
<DIV class='content_form'>
   <DIV>
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
         <span class="col-xs-3 col-lg-3">${memberVO.userid}</span>
         <input type='hidden' name='userid' id='userid' value='chanmi' class="col-xs-3 col-lg-3">
      </div>
      <div class='row'>
       <label class='col-xs-2 col-lg-2 ' for='email'>이메일</label> 
       <span class="col-xs-3 col-lg-3">${memberVO.email}</span> 
       <input type="hidden" name="email" value="${memberVO.email}"> 
      </div>
      <div class='row'>
       <label class='col-xs-2 col-lg-2 ' for='tel'>등록자 연락처</label> 
       <span class="col-xs-3 col-lg-3">${memberVO.tel}</span> 
       <input type="hidden" name="tel" value="${memberVO.tel}"> 
      </div>
      <div class='row'> 
       <label class='col-xs-2 col-lg-2 ' for='nickname'>등록자 별명</label>
       <span class="col-xs-3 col-lg-3">${memberVO.nickname}</span> 
       <input type="hidden" name="nickname" value="${memberVO.nickname}"> 
      </div>   
      <!-- 
      <div class="row">  
        <label for='email' class='col-xs-2 col-lg-2'>이메일</label>
        <input type='text' name='email' id='email'  required="required" value=''  class="col-xs-3 col-lg-3">
      </div>
       -->
      <div class='text_r' >
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.do'" class="btn btn-danger btn-lg">취소</button>
      </div>
    </DIV>
  </DIV>    
</FORM>
</DIV>
 
<!-- -------------------------------------------- -->
</body>
<!-- -------------------------------------------- -->
</html> 
 