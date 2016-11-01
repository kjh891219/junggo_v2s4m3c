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
<link href="./css/style.css" rel="Stylesheet" type="text/css">

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



</script>


</head> 

<style type="text/css">

/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
  *{ 
    font-family: 'Nanum Gothic', serif;
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
  }
  
  .label_1{
   color : black;
  }
  
  a{
   color:white;
  }
  
/* left를 제외한 스타일 */
  body{
   width:80%;
   margin-left:130px;
  }
  
/* top 스타일 */
 .top_select{
     color: black; 
 }
  header{ 
    height: 35px; 
    background-color: #e6e6e6; 
    font-family: 맑은 고딕;  
    text-align: center;
  }
  .member-list {
    margin:5px 8px 0 0;
  
  }
  
 .member-list li {
    float:left;
    list-style: none;
    padding-left:8px;
  }
 .member-list li a {
    font-size:12px;
  }

/* left */  

   /* 로고 */
   #logo {
      width:70px;
      margin:20px auto;
   }
   #logo img {
      width:70px;
   }
   
  #main_left {
    position:fixed; 
    top:0;
    left:0;
  }
  
  #main_left_left{
    width:130px; 
    height:100%;
    float:left;
    color:white;
    background-color: #737373;
  }
  
   #main_left_detail{
      display:none;
      position:absolute;
      left:130px;
      width:130px;
      height:100%;
      
      background-color:#575757;
   }
  
  .left_list_form {
    padding:10px;
  }
  
  .left_list{
    padding-bottom:8px;
  }

/* index 안에 있는 태그 스타일 */
 .list_tag{
   color : black;
 }
   .container{
      width:100%;
   }
   
   nav ul li {
      list-style:none;
      margin-left: 20px;
   }
   nav {
      margin-top:30px;
   }
   footer{
      text-align: center;
   }
 
 
</style>
</head> 

<!-- ----------------------------------------- -->
<body>
 <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
     

  <div class='content_menu' style='width: 90%;'>
    <A href='../reviews/list.do'  class='top_select'>후기 게시판 목록</A> >
    <A href="javascript:location.reload();"  class='top_select'>새로고침</A>
  </div>
  
  <DIV class='content' style='width: 90%;'>
    <FORM name='frm' method='POST' action='./create.do'
              enctype="multipart/form-data">
  <input type='hidden' name='userid' id='userid' value= '${userid }'/>

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
          <option value="국 LPG/화물차/버스" >국산LPG/화물차/버스</option>
          <option value="수입차" >수입차</option>
          <option value="카오디오/카DVD" >카오디오/카DVD</option>
          <option value="차량용 TV" >차량용 TV</option>
          <option value="휠/타이어/캐리어" >휠/타이어/캐리어</option>
          <option value="기타 차량용품" >기타 차량용품</option>
        </select>
      </li>

      <li>
        <label class='label_1'  for='seller_nick' >판매자</label>
        <input type='text' name='seller_nick' id='seller_nick' ><br><br>
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
      
      <li class='right'>
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
      </li>     
      
      </ul>
       
      </div>   

    </FORM>
  </DIV>

</div>
     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>

</html> 

