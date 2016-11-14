<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.mvc.usedcar.UsedcarVO" %>

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

<script type="text/javascript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
   
    // content: textarea name
    if (CKEDITOR.instances['content'].getData() == '') {
      window.alert('내용을 입력해 주세요.');
      CKEDITOR.instances['content'].focus();
      return false;
    }

  };

</script>

<style type="text/css">


</style>
</head> 

<!-- ----------------------------------------- -->
<body>
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
     
 <div class="container">

  <div class='content_menu' style='width: 90%;'>
    <A href='../usedcar/list.do' class='top_select'>게시판 목록</A> > 
  </div>
  
 <DIV class='title'><span>글등록</span></DIV>
   <div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
    <FORM name='frm' method='POST' action='./create.do'
              enctype="multipart/form-data">
   <input type='hidden' name='userid' id='userid' value= '${userid }'/>


<DIV class='content_form'>
 <DIV class="">
  <div class="float_l _left">
   <div class="">
    <label class="select need_e" for='category'>분류</label>
   <div>
   <select name='category' id='category'>
      <option value="HYUNDAI" selected="selected">HYUNDAI</option>
      <option value="KIA" >KIA</option>
      <option value="쉐보레" >쉐보레</option>
      <option value="BENZ" >BENZ</option>
      <option value="외제차" >외제차</option>
   </select>
   </div>
  </div>
 <div class=""> 
   <label for='deal_code ' class="control-label need_e">거래구분</label>
   <div>
     <select name='deal_code' id='deal_code'>
       <option value="팝니다" selected="selected">팝니다</option>
       <option value="삽니다">삽니다</option>
     </select>
    </div>
  </div>
  </div> 
  <div class='float_l _right' >
  <div class="">  
   <label for='region' class='need_e'>지역</label>
     <div>
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
      </div>
   </div>
   
 <div class=""> 
  <label for='deal_way' class='need_e'>거래방식</label>
 <div>
   <select name='deal_way' id='deal_way'>
      <option value="현장거래" selected="selected">현장거래</option>
      <option value="택배">택배</option>
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
</div><hr/>

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

<h3>추가항목</h3>
  <div class='inpo'>상품 정보</div>
  <div class='line_box' id='ul_box_1'></div>
      <div class="row">
       <label for='product_code' class='col-xs-2 col-lg-2 need'>글 비밀번호</label>
       <input type='text' name='passwd' id='passwd' value= '1234'/>
     </div>
     <div class="row">
        <label for='product_code' class='col-xs-2 col-lg-2 need'>상품구분</label>
          <input type="radio" name='product_code' value="중고품"  checked="checked"><span class='radio_text'>중고품</span>
         <input type="radio" name='product_code' value="신상품" ><span class='radio_text'>신상품</span>
     </div>
     <div class="row"> 
        <label for='hprice' class='col-xs-2 col-lg-2 need'>희망가격</label>
        <input type='text' name='hprice' id='hprice'  required="required" value='' class="col-xs-3 col-lg-3">원
     </div>
     <div class="row">       
        <label for='purc_date'  class='col-xs-2 col-lg-2 choice'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='' class="col-xs-3 col-lg-3"> 
     </div>
<!--       <div class="row"> 
        <label for='quantity' class='col-xs-2 col-lg-2 choice'>수량</label>
        <input type='text' name='quantity' id='quantity' value='1' class="col-xs-3 col-lg-3">
      </div> -->
      
      <hr/>
      <div class='inpo'>판매자 정보</div>
      <div class='line_box' id='ul_box_2'></div>
      <div class="row">
         <label for='nickname' class='col-xs-2 col-lg-2 need'>별명</label>
         <input type='text' name='nickname' id='nickname' value= '${memberVO.nickname }' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
      </div>
      <div class="row">
         <label for='tel'  class='col-xs-2 col-lg-2 need'>전화번호</label>
          <input type='text' name='tel' id='tel'  required="required" value='${memberVO.tel}' class="col-xs-3 col-lg-3"> 
       </div>
       <div class="row">  
          <label for='email' class='col-xs-2 col-lg-2 need'>이메일</label>
          <input type='text' name='email' id='email'  required="required" value='${memberVO.email }'  class="col-xs-3 col-lg-3">
       </div>
       
       <hr/>
   

<%-- 
   <div class="form-group">   
      <ul>
       <li>
         <label class='label_1' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value=' ${memberVO.nickname}'  readonly><br><br>
         <label class='label_1' for='passwd'>글 비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required"><br><br>
       </li>
      
       <li>
        <label class='label_1' for='category'>카테고리 코드</label>
        <select name='category' id='category'>
          <option value="HYUNDAI" selected="selected">HYUNDAI</option>
          <option value="KIA" >KIA</option>
          <option value="쉐보레" >쉐보레</option>
          <option value="BENZ" >BENZ</option>
        </select>
        <label class='label_1' for='deal_code'>거래구분 코드</label>
        <select name='deal_code' id='deal_code'>
          <option value="팝니다" selected="selected">팝니다</option>
          <option value="삽니다">삽니다</option>
        </select>
      </li>
      
      <li>
        <label class='label_1' for='region'>거래 지역</label>
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
          <label class='label_1' for='deal_way'>거래방식</label>
        <select name='deal_way' id='deal_way'>
          <option value="현장거래" selected="selected">현장거래</option>
          <option value="택배">택배</option>
        </select>
      </li>
      <li>
        <label class='label_1' for='hprice'>희망가격</label>
        <input type='text' name='hprice' id='hprice' value='0' >
      </li>
      
      <li>
        <label class='label_1' for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='' >
       
       <label class='label_1' for='product_code'>거래방식</label>
        <select name='product_code' id='product_code'>
          <option value="중고" selected="selected">중고</option>
          <option value="신상">신상</option>
        </select>
      </li>
         
      <li>
        <label class='label_1' for='title'>제목</label>
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
      </li> --%>
      
    <div class='text_r' >
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
      </div>
       
      </div>   

    </FORM>
  </DIV>

     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>

</html> 

