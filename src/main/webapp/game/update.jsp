<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
 
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
<DIV class='title'>상품 수정</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data">
   <input type='hidden' name='gno' id='gno'
             value='${vo.gno}'>
  <fieldset>
    <ul>
       <li>
        <label class='label' for='category'>분류</label>
        <select name = 'category' id = 'category'>
         <option value = "${vo.category}" selected="selected">${vo.category}</option>
         <option value = "ps4">PS4</option>
         <option value = "nintendo">닌텐도</option>
         <option value = "pc">PC</option>
         <option value = "xbox">xbox</option>
        </select>
      </li>
      <hr/>
      <li>
       <label class='label' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value ='${vo.nickname}' required="required">
        <label class='label' for='passwd'>패스워드</label>
        <input type='password' name='passwd' id='passwd' value = '${vo.passwd}' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='email'>이메일</label>
        <input type='text' name='email' id='email' value= '${vo.email}' required="required">
        <label class='label' for='tel'>전화번호</label>
        <input type="text" name='tel' value ='${vo.tel}' id='tel' > 예) 010-0000-0000
      </li>
      <hr/>
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value ='${vo.title}' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='content'>상세 설명</label>
        <textarea rows ="10" cols = "40"  name = "content" id="content">${vo.content}</textarea>
      </li>
      <hr/>
      <li>
        <label class='label' for='region'>지역</label>
      <select name = 'region' id = 'region'>
           <option value="${vo.region}" selected="selected">${vo.region}</option>
           <option value="서울" >서울</option>
           <option value="인천">인천</option>
           <option value="대구">대구</option>
           <option value="대전">대전</option>
           <option value="광주">광주</option>
           <option value="울산">울산</option>
           <option value="busan">부산</option>
           <option value="gyunggi">경기</option>
           <option value="gang">강원</option>
           <option value="gbook">경북</option>
           <option value="gnam">경남</option>
           <option value="jbook">전북</option>
           <option value="jnam">전남</option>
           <option value="cnam">충남</option>
           <option value="cbook">충북</option>
           <option value="jeju">제주</option>
        </select>
        <label class='label' for='deal_code'>거래구분</label>
        <select name = 'deal_code' id = 'deal_code'>
         <option value = "${vo.deal_code}" selected="selected">${vo.deal_code}</option>
         <option value = "판매">팝니다</option>
         <option value = "판매완료">판매완료</option>
         <option value = "삽니다">삽니다</option>
         <option value = "구매완료">구매완료</option>
        </select>
      </li>
       <hr/>
      <li>
        <label class='label' for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value= '${vo.purc_date}' required="required">
      </li>
       <hr/>
      <li>
        <label class='label' for='product_code'>상품구분</label>
        <select name = 'product_code' id = 'product_code' >
         <option value = "${vo.product_code}" selected="selected">${vo.product_code}</option>
         <option value = "중고">중고품</option>
         <option value = "신고품">신고품</option>
         <option value = "신상품">신상품</option>
        </select>
        <label class='label' for='deal_way'>거래방식</label>
        <select name = 'deal_way' id = 'deal_way'>
         <option value = "${vo.deal_way}" selected="selected">${vo.deal_way}</option>
         <option value = "직거래">직거래</option>
         <option value = "택배">택배</option>
         <option value = "등기우편">등기우편</option>
         <option value = "안전거래">안전거래</option>
        </select>
       </li>
       <hr/>
      <li>
        <label class='label' for='quantity'>수량</label>
        <input type='text'  name ='quantity' id = 'quantity' value= '${vo.quantity}' required="required">
        <label class='label' for='hprice'>희망가격</label>
        <input type='text'  name ='hprice' id = 'hprice'  value = '${vo.hprice}' required="required">
      </li>
       <hr/>
      <li>
        <label class='label' for='lev'>평점</label>
         <select name = 'lev' id = 'lev'>
         <option value = "${vo.lev}" selected="selected">${vo.lev}</option>
         <option value = "★">★</option>
         <option value = "★★">★★</option>
         <option value = "★★★">★★★</option>
         <option value = "★★★★">★★★★</option>
         <option value = "★★★★★">★★★★★</option>
         </select>
      
        <label class='label' for='genre'>장르</label>
         <select name = 'genre' id = 'genre' >
         <option value = '${vo.genre}' selected="selected">${vo.genre}</option>
         <option value = "rpg">RPG</option>
         <option value = "action">ACTION</option>
         <option value = "soprts">SPORTS</option>
         <option value = "svg">전략</option>
        </select>
      </li>
      <hr/>
      
     <div id='file2Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일</label>
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
      <div class="form-group">   
        <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>  
      <br>
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