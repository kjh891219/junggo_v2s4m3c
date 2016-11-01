<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.music.MusicVO" %>

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
    <A href='../music/list.do'  class='top_select'>게시판 목록</A> >
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
        <label class='label_1'  for='category'>카테고리 코드</label>
        <select name='category' id='category'>
              <option value="아이팟/MP3" selected="selected">아이팟/MP3</option>
              <option value="음향기기" >음향기기</option>
              <option value="CD/DVD/음반" >CD/DVD/음반</option>
              <option value="악기" >악기</option>
              <option value="주변기기">주변기기</option>
        </select>
        <label class='label_1'  for='deal_code'>거래구분 코드</label>
        <select name='deal_code' id='deal_code'>
          <option value="팝니다" selected="selected">팝니다</option>
          <option value="삽니다">삽니다</option>
        </select>
      </li>
      
      <li>
        <label class='label_1'  for='region'>거래 지역</label>
        <select name='region' id='region' >
           <option value="서울" selected="selected">서울</option>
           <option value="인천">인천</option>
           <option value="대구">대구</option>
           <option value="대전">대전</option>
           <option value="광주">광주</option>
           <option value="울산">울산</option>
           <option value="부산">부산</option>
           <option value="경기">경기</option>
           <option value="강원">강원</option>
           <option value="경북">경북</option>
           <option value="경남">경남</option>
           <option value="전북">전북</option>
           <option value="전남">전남</option>
           <option value="충남">충남</option>
           <option value="충북">충북</option>
           <option value="제주">제주</option>
        </select>
          <label class='label_1'   for='deal_way'>거래방식</label>
        <select name='deal_way' id='deal_way'>
          <option value="현장거래" selected="selected">현장거래</option>
          <option value="택배">택배</option>
        </select>
      </li>
      <li>
        <label class='label_1'  for='h_price'>희망가격</label>
        <input type='text' name='h_price' id='h_price' value='0' >
      </li>
      
      <li>
        <label class='label_1'  for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='' >
       
       <label class='label_1'  for='product_code'>거래방식</label>
        <select name='product_code' id='product_code'>
          <option value="중고" selected="selected">중고</option>
          <option value="신상">신상</option>
        </select>
      </li>
      
      <li><div class="col-xs-5">
              <label for='quantity'>수량</label> 
              <input type='number' name='quantity' id='quantity' value=${opentype == "U" ? musicVO.quantity : 2 } class=" ">
            </div></li>
         
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
      
      <li>
        <label for='email'>E-mail</label><br>
        <input type='text' name='email' id='email'  required="required" value='${memberVO.email }' class="form-control-lg-10-lg-10-lg-10">
      </li>
      
    <li>
        <label for='tel'>Tel</label><br>
        <input type='text' name='tel' id='tel' size='15' value='' required="required">
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

