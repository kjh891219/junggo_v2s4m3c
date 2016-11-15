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
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>

<style type ="text/css">
.form-control {
   width: 40%;
   float: left;
   position : relative;
}


</style>


</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<DIV class=container>
  <jsp:include page="/menu/top.jsp" flush='false' />
  <jsp:include page="/menu/left.jsp" flush="false"/> 
<DIV class='title'><span>글수정</span></DIV>
<div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
<FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data">
   <input type='hidden' name='gno' id='gno' value='${gameVO.gno}'>
<DIV class='content_form'>
     <DIV class="">
     <div class="float_l _left">
      <div class="">
       <label class="select need_e" for='category'>분류</label>
        <div>
      <select name = 'category' id="category" class="full">
         <option value = "${gameVO.category}" selected="selected">${gameVO.category}</option>
         <option value = "ps4" selected="selected">PS4</option>
         <option value = "nintendo">닌텐도</option>
         <option value = "pc">PC</option>
         <option value = "xbox">xbox</option>
        </select>
      </div>
      </div>
        <div class="">  
         <label for='deal_code ' class="control-label need_e">거래구분</label>
          <div>
            <select name='deal_code' id="deal_code" class="control-label full">
            
               <option value="팝니다" selected="selected">${gameVO.deal_code}</option>
               <option value="팝니다">팝니다</option>
               <option value="삽니다">삽니다</option>
            </select>
          </div>
        </div>
        </div>
       <div class='float_l _right' >
        <div class="">  
          <label for='region' class='need_e'>지역</label>
          <div>
            <select name='region' id='region' class="form-control-lg-10-lg-10-lg-10 full" >
               <option value="${gameVO.region}" selected="selected">${gameVO.region}</option>
               <option value="서울">서울</option>
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
           <select name='deal_way' id='deal_way' class="form-control-lg-10-lg-10-lg-10 full">
             <option value="${gameVO.deal_way}" selected="selected">${gameVO.deal_way}</option>
             <option value="직거래">직거래</option>
             <option value="택배">택배</option>
          </select >
        </div>
      </div>
        </div>
        <div class='both'></div>
      </DIV>
      <hr/>
      <div class="row">
        <label for='title' class='col-xs-2 col-lg-2 need'>제목</label>
        <input type='text' name='title' id='title' required="required" value='${gameVO.title}' class="col-xs-9 col-lg-9">
      </div>
      
      <div class=" ">
        <label for='content' class='choice_x'>상세설명</label>
        <div class = "empty">
        </div>
        <textarea rows="10" name="content" id="content" placeholder="내용을 입력하세요"  class="col-xs-9 col-lg-9">${gameVO.content}</textarea>
      </div>
      <hr/>
      
      <!-- 첫번째 파일 수정 -->
      
      <div id='file2Panel' class="row">
        <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일1</label>
          <c:set var='file2' value="${fn:toLowerCase(gameVO.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              <IMG src='./storage/${gameVO.file1}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              <IMG id='file2'  src='./storage/${gameVO.file2}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
              <IMG id='file2'  src='./storage/${gameVO.file2}'>
            </c:when>
            <c:when test="${gameVO.file2.length() > 0}">
              ${gameVO.file2 } 
            </c:when>
          </c:choose>
        </div>
      <div class="row">   
        <label for="file2MF" class='col-xs-2 col-lg-2 need'>수정 파일1</label>
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
      </div>
      
      <!-- 두번째 파일 수정 -->
      
      <div id='file4Panel' class="row">
        <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일2</label>
          <c:set var='file4' value="${fn:toLowerCase(gameVO.file4)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file4, '.jpg')}">
              <IMG src='./storage/${gameVO.file4}'>
            </c:when>
            <c:when test="${fn:endsWith(file4, '.gif')}">
              <IMG id='file4'  src='./storage/${gameVO.file4}'>
            </c:when>
            <c:when test="${fn:endsWith(file4, '.png')}">
              <IMG id='file4'  src='./storage/${gameVO.file4}'>
            </c:when>
            <c:when test="${gameVO.file4.length() > 0}">
              ${gameVO.file4 } 
            </c:when>
          </c:choose>
        </div>
   
      
      <div class="row">   
        <label for="file4MF" class='col-xs-2 col-lg-2 need'>수정 파일2</label>
          <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
      </div>
      
      
      <!-- 세번째 파일 수정 -->
      
      <div id='file6Panel' class="row">
        <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일3</label>
          <c:set var='file6' value="${fn:toLowerCase(gameVO.file6)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file6, '.jpg')}">
              <IMG src='./storage/${gameVO.file6}'>
            </c:when>
            <c:when test="${fn:endsWith(file6, '.gif')}">
              <IMG id='file6'  src='./storage/${gameVO.file6}'>
            </c:when>
            <c:when test="${fn:endsWith(file6, '.png')}">
              <IMG id='file6'  src='./storage/${gameVO.file6}'>
            </c:when>
            <c:when test="${gameVO.file6.length() > 0}">
              ${gameVO.file6 } 
            </c:when>
          </c:choose>
        </div>
   
      
      <div class="row">   
        <label for="file6MF" class='col-xs-2 col-lg-2 need'>수정 파일3</label>
          <input type="file" class="form-control" name='file6MF' id='file6MF' size='40' >
      </div>
      
      
        <!-- 네번째 파일 수정 -->
      
      <div id='file8Panel' class="row">
        <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일4</label>
          <c:set var='file8' value="${fn:toLowerCase(gameVO.file8)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file8, '.jpg')}">
              <IMG src='./storage/${gameVO.file8}'>
            </c:when>
            <c:when test="${fn:endsWith(file8, '.gif')}">
              <IMG id='file8'  src='./storage/${gameVO.file8}'>
            </c:when>
            <c:when test="${fn:endsWith(file8, '.png')}">
              <IMG id='file8'  src='./storage/${gameVO.file8}'>
            </c:when>
            <c:when test="${gameVO.file8.length() > 0}">
              ${gameVO.file8 } 
            </c:when>
          </c:choose>
        </div>
   
      
      <div class="row">   
        <label for="file8MF" class='col-xs-2 col-lg-2 need'>수정 파일4</label>
          <input type="file" class="form-control" name='file8MF' id='file8MF' size='40' >
      </div>
      
        <!-- 다섯번째 파일 수정 -->
      
      <div id='file10Panel' class="row">
        <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일5</label>
          <c:set var='file10' value="${fn:toLowerCase(gameVO.file10)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file10, '.jpg')}">
              <IMG src='./storage/${gameVO.file10}'>
            </c:when>
            <c:when test="${fn:endsWith(file10, '.gif')}">
              <IMG id='file10'  src='./storage/${gameVO.file10}'>
            </c:when>
            <c:when test="${fn:endsWith(file10, '.png')}">
              <IMG id='file10'  src='./storage/${gameVO.file10}'>
            </c:when>
            <c:when test="${gameVO.file10.length() > 0}">
              ${gameVO.file10 } 
            </c:when>
          </c:choose>
        </div>
   
      
      <div class="row">   
        <label for="file10MF" class='col-xs-2 col-lg-2 need'>수정 파일5</label>
          <input type="file" class="form-control" name='file10MF' id='file10MF' size='40' >
      </div>
      
      
      
      
      
            
      <h3>추가항목</h3>
      <div class='inpo'>상품 정보</div>
      <div class='line_box' id='ul_box_1'></div>
          <div class="row">
         <label for='product_code' class='col-xs-2 col-lg-2 need'>상품구분</label>
         <input type="radio" name='product_code' value="중고품" checked="checked"><span class='radio_text'>중고품</span>
         <input type="radio" name='product_code' value="신상품" ><span class='radio_text'>신상품</span>
      </div>
        <div class="row"> 
        <label for='hprice' class='col-xs-2 col-lg-2 need'>희망가격</label>
        <input type='text' name='hprice' id='hprice'  required="required" value='${gameVO.hprice}' class="col-xs-3 col-lg-3">
        </div>
        <div class="row">       
        <label for='purc_date'  class='col-xs-2 col-lg-2 choice'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='${gameVO.purc_date}' class="col-xs-3 col-lg-3"> 
        </div>
      <div class="row"> 
        <label for='quantity' class='col-xs-2 col-lg-2 choice'>수량</label>
        <input type='text' name='quantity' id='quantity' value='${gameVO.quantity}' class="col-xs-3 col-lg-3">
      </div>
      <div class=""> 
         <label for='lev' class='choice_x'>평점</label>
         <div>
         <select name='lev' id='lev' class="form-control-lg-10-lg-10-lg-10 full">
         <option value = "${gameVO.lev}" selected="selected">${gameVO.lev}</option>
         <option value = "★">★</option>
         <option value = "★★">★★</option>
         <option value = "★★★">★★★</option>
         <option value = "★★★★">★★★★</option>
         <option value = "★★★★★">★★★★★</option>
         </select>
         </div>
      </div>
      <div class=""> 
         <label for='genre' class='choice_x'>장르</label>
         <div>
           <select name='genre' id='genre' class="form-control-lg-10-lg-10-lg-10 full">
         <option value = '${gameVO.genre}' selected="selected">${gameVO.genre}</option>
         <option value = "rpg">RPG</option>
         <option value = "action">ACTION</option>
         <option value = "soprts">SPORTS</option>
         <option value = "svg">전략</option>
        </select>
        </div>
     </div> 
      <hr/>
      <div class='inpo'>판매자 정보</div>
        <div class='line_box' id='ul_box_2'></div>
           <div class="row">
              <label for='nickname' class='col-xs-2 col-lg-2 need'>별명</label>
              <input type='text' name='nickname' id='nickname' value= '${gameVO.nickname }' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
              </div>
            <div class="row">
               <label for='tel'  class='col-xs-2 col-lg-2 need'>전화번호</label>
              <input type='text' name='tel' id='tel'  required="required" value='${gameVO.tel}' class="col-xs-3 col-lg-3"> 
            </div>
            <div class="row">  
              <label for='email' class='col-xs-2 col-lg-2 need'>이메일</label>
              <input type='text' name='email' id='email'  required="required" value='${gameVO.email }'  class="col-xs-3 col-lg-3">
            </div>
  
        <hr/>
        

      <div class='text_r' >
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.do?gno=${gameVO.gno}&col=${searchDTO.col}&word=${searchDTO.word}'" class="btn btn-danger btn-lg">취소</button>
      </div>
      </div>
               
</FORM>
</DIV>
<!-- -------------------------------------------- -->
 <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html>  