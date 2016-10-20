<%@ page contentType="text/html; charset=UTF-8" %>
 
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

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
<DIV class='title'>상품등록</DIV>
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./create.do'>
  <fieldset>
    <ul>
       <li>
        <label class='label' for='category'>분류</label>
        <select name = 'category' id = 'category'>
         <option value = "I-PHONE" selected="selected">아이폰</option>
         <option value = "GALAXY">GALAXY</option>
         <option value = "LG">LG</option>
         <option value = "SKY">SKY</option>
         <option value = "기타">기타</option>
        </select>
      </li>
      <hr/>
      <li>
       <label class='label' for='nickname'>아이디</label>
        <input type='text' name='nickname' id='nickname' value='투투' required="required">
       <label class='label' for='userid'>아이디</label>
        <input type='text' name='userid' id='userid' value='user1' required="required">
        <label class='label' for='passwd'>패스워드</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='email'>이메일</label>
        <input type='text' name='email' id='email' value='abc@naver.com' required="required">
        <label class='label' for='tel'>전화번호</label>
        <input type="text" name='tel' id='tel' value='010-9999-9999'> 예) 010-0000-0000
      </li>
      <hr/>
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value='xbox' required="required">
      </li>
      <hr/>
      <li>
        <label class='label' for='content'>상세 설명</label>
        <textarea rows ="10" cols = "40"  name = "content" id="content">싸게 팔아요</textarea>
      </li>
      <hr/>
      <li>
        <label class='label' for='region'>지역</label>
      <select name = 'region' id = 'region'>
           <option value="서울" selected="selected">서울</option>
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
         <option value = "sell" selected="selected">팝니다</option>
         <option value = "done">판매완료</option>
         <option value = "buy">삽니다</option>
         <option value = "bdone">구매완료</option>
        </select>
      </li>
       <hr/>
      <li>
        <label class='label' for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='2016년7월' required="required">
      </li>
       <hr/>
      <li>
        <label class='label' for='product_code'>상품구분</label>
        <select name = 'product_code' id = 'product_code'>
         <option value = "old" selected="selected">중고품</option>
         <option value = "new">신고품</option>
         <option value = "lastest">신상품</option>
        </select>
        <label class='label' for='deal_way'>거래방식</label>
        <select name = 'deal_way' id = 'deal_way'>
         <option value = "직거래" selected="selected">직거래</option>
         <option value = "택배">택배</option>
         <option value = "등기우편">등기우편</option>
         <option value = "안전거래">안전거래</option>
        </select>
          <label class='label' for='telecome'>통신사</label>
        <select name = 'telecome' id = 'telecome'>
         <option value = "SKT" selected="selected">SKT</option>
         <option value = "KT">KT</option>
         <option value = "LG">LG</option>
        </select>
       </li>
       <hr/>
      <li>
        <label class='label' for='quantity'>수량</label>
        <input type='text'  name ='quantity' id = 'quantity' value='1' required="required">
        <label class='label' for='hprice'>희망 가격</label>
        <input type='text'  name ='hprice' id = 'hprice' value='270000' required="required">
      </li>
       <hr/>
      <li class='right'>
        <button type="submit">가입</button>
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