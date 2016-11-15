<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.reviews.ReviewsVO" %>

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


<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>


<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
   
    // content: textarea name
    if (CKEDITOR.instances['content'].getData() == '') {
      window.alert('내용을 입력해 주세요.');
      CKEDITOR.instances['content'].focus();
      return false; 
    };

    }


  $(document).ready(function() {
    
    if($(".left").height() < $(".right").height()){
       $(".left").height($(".right").height());
    }
    
  });
  
</script>


</head> 

<style type="text/css">


 
</style>
</head> 

<!-- ----------------------------------------- -->
<body>
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<jsp:include page="/menu/community_left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<div class="float_l right " style="width:80%;">
<div class="container">
     

  <div class='content_menu' style='width: 90%;'>
    <A href='../reviews/list.do'  class='top_select'>후기 게시판 목록</A> 
  </div>
  
   <DIV class='title'><span>글등록</span></DIV>
   <div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
  <DIV class='content' style='width: 90%;'>
    <FORM name='frm' method='POST' action='./create.do'
              enctype="multipart/form-data">
  <input type='hidden' name='userid' id='userid' value= '${userid }'/>

  <DIV class='content_form'>
   <DIV class="">
   <div class="float_l _left">
    <div class="">
     <label class="select need_e" for='t_category'>분류</label>
    <div>
        <select name='t_category' id='t_category'>
          <option value="중고차" selected="selected">중고차</option>
          <option value="자동차 용품" >자동차 용품</option>
          <option value="의류" >의류</option>
          <option value="화장품" >화장품</option>
          <option value="잡화" >잡화</option>
          <option value="핸드폰" >핸드폰</option>
          <option value="게임" >게임</option>
          <option value="게임기기" >게임기기</option>
          <option value="컴퓨터" >컴퓨터</option>
          <option value="음향기기" >음향기기</option>
          <option value="카메라" >카메라</option>
          <option value="문화&예술" >문화&예술</option>
          <option value="도서" >도서</option>
          <option value="생활용품" >생활용품</option>
          <option value="스포츠" >스포츠</option>
        </select>
     </div>
     </div>
     </div>
  <div class='both'></div>
 </DIV><hr/>
 
  <div class="row">
   <label for='title' class='col-xs-2 col-lg-2 need'>제목</label>
   <input type='text' name='title' id='title' required="required" value='' class="col-xs-9 col-lg-9">
</div>  

<div class="row">
 <label for='content' class='col-xs-2 col-lg-2 choice'>상세설명</label>
 <textarea class="form-control" name='content' id='content'  rows='10' cols='70'>내용을 입력해주세요</textarea>
</div>
 <div class="row">
     <label for='product_code' class='col-xs-2 col-lg-2 need'>글 비밀번호</label>
     <input type='text' name='passwd' id='passwd' value= '1234'/>
 </div>
 <div class="row">
    <label for='nickname' class='col-xs-2 col-lg-2 need'>별명</label>
    <input type='text' name='nickname' id='nickname' value= '${memberVO.nickname }' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
  </div>
<hr/>

<div class="row need">
 <label for="file1MF" class="col-xs-2 col-lg-2 control-label">업로드 파일1</label>
 <input type="file" class="form-control" name='file1MF' id='file1MF' size='40' >
</div>  
<div class="row choice" > 
  <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일2</label>  
  <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
</div>
<div class="row choice">  
   <label for="file3MF" class="col-xs-2 col-lg-2 control-label">업로드 파일3</label>
   <input type="file" class="form-control" name='file3MF' id='file3MF' size='40' >
</div> 
<div class="row choice"> 
   <label for="file4MF" class="col-xs-2 col-lg-2 control-label">업로드 파일4</label>
   <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
</div>
<div class="row choice">   
 <label for="file5MF" class="col-xs-2 col-lg-2 control-label">업로드 파일5</label>
  <input type="file" class="form-control" name='file5MF' id='file5MF' size='40' >
</div>
<hr>

<%-- 


   <div class="form-group">   
      <ul>
       <li>
         <label class='label_1'  for='nickname' >닉네임</label>
        <input type='text' name='nickname' id='nickname'  value='${memberVO.nickname}'  readonly><br><br>
         <label class='label_1'   for='passwd'>글 비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required"><br><br>
       </li>
      
       <li>
        <label class='label_1'  for='t_category'>카테고리 코드</label>
        <select name='t_category' id='t_category'>
          <option value="중고차" selected="selected">중고차</option>
          <option value="자동차 용품" >자동차 용품</option>
          <option value="의류" >의류</option>
          <option value="화장품" >화장품</option>
          <option value="잡화" >잡화</option>
          <option value="핸드폰" >핸드폰</option>
          <option value="게임" >게임</option>
          <option value="게임기기" >게임기기</option>
          <option value="컴퓨터" >컴퓨터</option>
          <option value="음향기기" >음향기기</option>
          <option value="카메라" >카메라</option>
          <option value="문화&예술" >문화&예술</option>
          <option value="도서" >도서</option>
          <option value="생활용품" >생활용품</option>
          <option value="스포츠" >스포츠</option>
        </select>
      </li>
         
      <li>
        <label class='label_1'   for='title'>제목</label>
        <input type='text' name='title' id='title' size='50' value='' required="required">
      </li>
      
       <li>
        <label for='content'>내용</label><br>
        <div>
          <textarea class="form-control" name='content' id='content'  rows='10' cols='70'>내용을 입력해주세요</textarea>
        </div>
       </li>

       <li>
      <label for="file1MF" class="col-xs-2 col-lg-2 control-label">업로드 파일1</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file1MF' id='file1MF' size='40' >
      
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
            <li>
      <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일2</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
      
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
      <li>
      <label for="file3MF" class="col-xs-2 col-lg-2 control-label">업로드 파일3</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file3MF' id='file3MF' size='40' >
      
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
     <li>
      <label for="file4MF" class="col-xs-2 col-lg-2 control-label">업로드 파일4</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
      
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>

      <li>
      <label for="file5MF" class="col-xs-2 col-lg-2 control-label">업로드 파일5</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file5MF' id='file5MF' size='40' >
      
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
       --%>
      <div class='text_r' >
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
      </div>

      </div>   

    </FORM>
  </DIV>

</div>
</div>
     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>

</html> 

