<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
<DIV class='title'>상품 수정</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data">
   <input type='hidden' name='cno' id='cno'
             value='${cosmeticVO.cno}'>
  <fieldset>
    <ul>
       <li>
        <label class='label' for='category'>분류</label>
        <select name = 'category' id = 'category'>
         <option value = "${cosmeticVO.category}" selected="selected">${cosmeticVO.category}</option>
         <option value = "여자화장품">여자화장품</option>
         <option value = "남성화장품">남성화장품</option>
         <option value = "메이크업/클렌징">메이크업/클렌징</option>
         <option value = "향수/아로마">향수/아로마</option>
         <option value = "헤어바디케어">헤어바디케어</option>
         <option value = "기타미용제품">기타미용제품</option>
        </select>
      </li>
      <hr/>
      <li>
       <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value ='${cosmeticVO.nickname}' required="required">
        <label class='label' for='passwd'>패스워드</label>
        <input type='password' name='passwd' id='passwd' value = '${cosmeticVO.passwd}' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='email'>이메일</label>
        <input type='text' name='email' id='email' value= '${cosmeticVO.email}' required="required">
        <label class='label' for='tel'>전화번호</label>
        <input type="text" name='tel' value ='${cosmeticVO.tel}' id='tel' > 예) 010-0000-0000
      </li>
      <hr/>
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value ='${cosmeticVO.title}' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='content'>상세 설명</label>
        <textarea rows ="10" cols = "40"  name = "content" id="content">${cosmeticVO.content}</textarea>
      </li>
      <hr/>
      <li>
        <label class='label' for='region'>지역</label>
      <select name = 'region' id = 'region'>
           <option value="${cosmeticVO.region}" selected="selected">${cosmeticVO.region}</option>
           <option value="서울" >서울</option>
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
        <label class='label' for='deal_code'>거래구분</label>
        <select name = 'deal_code' id = 'deal_code'>
         <option value = "${cosmeticVO.deal_code}" selected="selected">${cosmeticVO.deal_code}</option>
         <option value = "판매">팝니다</option>
         <option value = "판매완료">판매완료</option>
         <option value = "삽니다">삽니다</option>
         <option value = "구매완료">구매완료</option>
        </select>
      </li>
       <hr/>
      <li>
        <label class='label' for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value= '${cosmeticVO.purc_date}' required="required">
      </li>
       <hr/>
      <li>
        <label class='label' for='product_code'>상품구분</label>
        <select name = 'product_code' id = 'product_code' >
         <option value = "${cosmeticVO.product_code}" selected="selected">${cosmeticVO.product_code}</option>
         <option value = "중고">중고품</option>
         <option value = "신고품">신고품</option>
         <option value = "신상품">신상품</option>
        </select>
        
        <label class='label' for='deal_way'>거래방식</label>
        <select name = 'deal_way' id = 'deal_way'>
         <option value = "${cosmeticVO.deal_way}" selected="selected">${cosmeticVO.deal_way}</option>
         <option value = "직거래">직거래</option>
         <option value = "택배">택배</option>
         <option value = "등기우편">등기우편</option>
         <option value = "안전거래">안전거래</option>
        </select>
       </li>
       
       
       <hr/>
      <li>
        <label class='label' for='quantity'>수량</label>
        <input type='text'  name ='quantity' id = 'quantity' value= '${cosmeticVO.quantity}' required="required">
        <label class='label' for='hprice'>희망가격</label>
        <input type='text'  name ='hprice' id = 'hprice'  value = '${cosmeticVO.hprice}' required="required">
      </li>
       <hr/>
       <br>
       
      <div class="form-group">
        <label  for='file1'>Thumb 파일</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
     </div>
     
     <div id='file2Panel'>
     <label for='content'  class ="col-xs-2 col-lg-2 control-label">등록된 파일</label>
      <div class="col-xs-10 col-lg-10"> 
        <c:set var='file2' value="${fn:toLowerCase(cosmeticVO.file2)}" />
        <c:choose>
          <c:when test="${fn:endsWith(file2, '.jpg')}">
             <IMG src='./storage/${cosmeticVO.file1}'>
          </c:when>
          <c:when test="${fn:endsWith(file2, '.gif')}">
             <IMG id='file2'  src='./storage/${cosmeticVO.file1}'>
          </c:when>
          <c:when test="${fn:endsWith(file2, '.png')}">
             <IMG id='file2'  src='./storage/${cosmeticVO.file1}'>
          </c:when>
          <c:when test="${cosmeticVO.file2.length() > 0}">
              ${cosmeticVO.file2 } 
          </c:when>
        </c:choose>
      </div>
      </div>
      
   <div class="form-group" class="form-group">
        <label  for='file2'>업로드 파일</label>
        <input type="file" name='file2MF' id='file2MF' size='40' >
      </div>
      
      <br><br>
      <div class="form-group">
        <label  for='file3'>Thumb 파일</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
     </div>
     
     <div id='file4Panel'>
     <label for='content'  class ="col-xs-2 col-lg-2 control-label">등록된 파일</label>
      <div class="col-xs-10 col-lg-10"> 
        <c:set var='file4' value="${fn:toLowerCase(cosmeticVO.file4)}" />
        <c:choose>
          <c:when test="${fn:endsWith(file4, '.jpg')}">
             <IMG src='./storage/${cosmeticVO.file3}'>
          </c:when>
          <c:when test="${fn:endsWith(file4, '.gif')}">
             <IMG id='file4'  src='./storage/${cosmeticVO.file3}'>
          </c:when>
          <c:when test="${fn:endsWith(file4, '.png')}">
             <IMG id='file4'  src='./storage/${cosmeticVO.file3}'>
          </c:when>
          <c:when test="${cosmeticVO.file4.length() > 0}">
              ${cosmeticVO.file4 } 
          </c:when>
        </c:choose>
      </div>
      </div>
      
   <div class="form-group" class="form-group">
        <label  for='file4'>업로드 파일</label>
        <input type="file" name='file4MF' id='file4MF' size='40' >
      </div>
      
      <br><br>
      <div class="form-group">
        <label  for='file5'>Thumb 파일</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
     </div>
     
     <div id='file6Panel'>
     <label for='content'  class ="col-xs-2 col-lg-2 control-label">등록된 파일</label>
      <div class="col-xs-10 col-lg-10"> 
        <c:set var='file6' value="${fn:toLowerCase(cosmeticVO.file6)}" />
        <c:choose>
          <c:when test="${fn:endsWith(file6, '.jpg')}">
             <IMG src='./storage/${cosmeticVO.file5}'>
          </c:when>
          <c:when test="${fn:endsWith(file6, '.gif')}">
             <IMG id='file6'  src='./storage/${cosmeticVO.file5}'>
          </c:when>
          <c:when test="${fn:endsWith(file6, '.png')}">
             <IMG id='file6'  src='./storage/${cosmeticVO.file5}'>
          </c:when>
          <c:when test="${cosmeticVO.file6.length() > 0}">
              ${cosmeticVO.file6 } 
          </c:when>
        </c:choose>
      </div>
      </div>
      
      <div class="form-group" class="form-group">
        <label  for='file6'>업로드 파일</label>
        <input type="file" name='file6MF' id='file6MF' size='40' >
      </div>
      
      
      <br><br>
      <div class="form-group">
        <label  for='file7'>Thumb 파일</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
     </div>
     
     <div id='file8Panel'>
     <label for='content'  class ="col-xs-2 col-lg-2 control-label">등록된 파일</label>
      <div class="col-xs-10 col-lg-10"> 
        <c:set var='file8' value="${fn:toLowerCase(cosmeticVO.file8)}" />
        <c:choose>
          <c:when test="${fn:endsWith(file8, '.jpg')}">
             <IMG src='./storage/${cosmeticVO.file7}'>
          </c:when>
          <c:when test="${fn:endsWith(file8, '.gif')}">
             <IMG id='file8'  src='./storage/${cosmeticVO.file7}'>
          </c:when>
          <c:when test="${fn:endsWith(file8, '.png')}">
             <IMG id='file8'  src='./storage/${cosmeticVO.file7}'>
          </c:when>
          <c:when test="${cosmeticVO.file8.length() > 0}">
              ${cosmeticVO.file8 } 
          </c:when>
        </c:choose>
      </div>
      </div>
      
   <div class="form-group" class="form-group">
        <label  for='file8'>업로드 파일</label>
        <input type="file" name='file8MF' id='file8MF' size='40' >
      </div>
      
      
      <br><br>
      <div class="form-group">
        <label  for='file9'>Thumb 파일</label>
        Preview(미리보기) 이미지 자동 생성됩니다.
     </div>
     
     <div id='file10Panel'>
     <label for='content'  class ="col-xs-2 col-lg-2 control-label">등록된 파일</label>
      <div class="col-xs-10 col-lg-10"> 
        <c:set var='file10' value="${fn:toLowerCase(cosmeticVO.file10)}" />
        <c:choose>
          <c:when test="${fn:endsWith(file10, '.jpg')}">
             <IMG src='./storage/${cosmeticVO.file9}'>
          </c:when>
          <c:when test="${fn:endsWith(file10, '.gif')}">
             <IMG id='file10'  src='./storage/${cosmeticVO.file9}'>
          </c:when>
          <c:when test="${fn:endsWith(file10, '.png')}">
             <IMG id='file10'  src='./storage/${cosmeticVO.file9}'>
          </c:when>
          <c:when test="${cosmeticVO.file10.length() > 0}">
              ${cosmeticVO.file10 } 
          </c:when>
        </c:choose>
      </div>
      </div>
      
   <div class="form-group" class="form-group">
        <label  for='file10'>업로드 파일</label>
        <input type="file" name='file10MF' id='file10MF' size='40' >
      </div> 
      
      
      
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