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
    
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>



</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->



<DIV class='center-block'>
<DIV class='title'>글수정</DIV>
<FORM name='frm' method='POST' action='./update.do' class='form-inline' enctype="multipart/form-data">
  <input type='hidden' name='cno' id='cno' value='${cosmeticVO.cno}'>
  <fieldset>
    <ul>
    <div class="row">
      <div class="col-xs-5">
        <label class='select' for='category'>*분류</label>
        <select name='category' id="category" class="form-control-lg-10-lg-10-lg-10-lg-10">
           <option value="${cosmeticVO.category }" selected="selected">${cosmeticVO.category }</option>
            <option value="여성화장품">여성화장품</option>
           <option value="남성화장품">남성화장품</option>
           <option value="메이크업/클렌징">메이크업/클렌징</option>
           <option value="향수/아로마">향수/아로마</option>
           <option value="헤어/바디케어">헤어/바디케어</option>
           <option value="요가/운동제품">요가/운동제품</option>
           <option value="기타미용제품">기타미용제품</option>
        </select>
      </div>
      <div class="col-xs-5">
      <label for='tel' >*전화번호</label>
        <input type='text' name='tel' id='tel'  required="required" value='${cosmeticVO.tel }' class="form-control-lg-10-lg-10-lg-10"> 
      </div>
      </div>
      <hr/>
      <div class="row">
      <div class="col-xs-5"> 
        <label for='nickname'>*별명</label>
        <input type='text' name='nickname' id='nickname' value= '${cosmeticVO.nickname }' required="required" readonly="readonly" class='form-control-lg-10-lg-10-lg-10-lg-10'/>
      </div>
     <div class="col-xs-5">    
        <label for='passwd'>*패스워드</label>
        <input type='password' name='passwd' id='passwd' value='${cosmeticVO.passwd }' required="required" class='form-control-lg-10-lg-10-lg-10'/>
      </div>
      </div>
      <hr/>
     <div class="row">
        <div class="col-xs-5">  
        <label for='deal_code'>*거래구분</label>
        <select name='deal_code' id="deal_code" class="form-control-lg-10-lg-10-lg-10">
           <option value="${cosmeticVO.deal_code }" selected="selected">${cosmeticVO.deal_code }</option>
           <option value="팝니다">팝니다</option>
           <option value="삽니다">삽니다</option>
        </select>
        </div>
        <div class="col-xs-5">
         <label for='product_code'>*상품구분</label>
        <select name='product_code' id='product_code' class="form-control-lg-10-lg-10-lg-10">
           <option value="${cosmeticVO.product_code }" selected="selected">${cosmeticVO.product_code }</option>
           <option value="중고품">중고품</option>
           <option value="신상품">신상품</option>
        </select>
        </div>
      </div>
      <hr/>
      <div class="row">
       <div class="col-xs-5"> 
         <label for='deal_way'>*거래방식</label>
         <select name='deal_way' id='deal_way' class="form-control-lg-10-lg-10-lg-10">
           <option value="${cosmeticVO.deal_way}" selected="selected">${cosmeticVO.deal_way}</option>
           <option value="직거래">직거래</option>
           <option value="택배">택배</option>
        </select >
        </div>
        <div class="col-xs-5">  
        <label for='region'>*지역</label>
         <select name='region' id='region' class="form-control-lg-10-lg-10-lg-10">
           <option value="${cosmeticVO.region }" selected="selected">${cosmeticVO.region }</option>
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
      <hr/>
      <div class="row">
        <div class="col-xs-5"> 
        <label for='hprice'>*희망가격</label>
        <input type='text' name='hprice' id='hprice'  required="required" value='${cosmeticVO.hprice }' class="form-control-lg-10-lg-10-lg-10">원
        </div>
        <div class="col-xs-5">       
        <label for='purc_date' >구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='${cosmeticVO.purc_date }' class="form-control-lg-10-lg-10-lg-10"> 
        </div>
     </div>
      <hr/>
      <div class="row">
      <div class="col-xs-5"> 
        <label for='quantity'>수량</label>
        <input type='text' name='quantity' id='quantity' value='${cosmeticVO.quantity}' class="form-control-lg-10-lg-10-lg-10">
      </div>
       <div class="col-xs-5">  
        <label for='email'>*이메일</label>
        <input type='text' name='email' id='email'  required="required" value='${cosmeticVO.email}' class="form-control-lg-10-lg-10-lg-10">
        </div>
       </div>
      <hr/>
      <div class="form-group">
        <label for='title'>*제목</label>
        <input type='text' name='title' id='title' required="required" value='${cosmeticVO.title} ' class="form-control-lg-10-lg-10-lg-10">
      </div>
      <hr/>
      <div class="form-group">
        <label for='content'>상세설명</label>
        <textarea rows="10" cols="100"  name="content" id="content" placeholder="내용을 입력하세요" class="form-group">${cosmeticVO.content}</textarea>
      </div>
      <div id='file2Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file2' value="${fn:toLowerCase(cosmeticVO.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              <IMG src='./storage/${cosmeticVO.file1}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              <IMG id='file2'  src='./storage/${cosmeticVO.file2}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
              <IMG id='file2'  src='./storage/${cosmeticVO.file2}'>
            </c:when>
            <c:when test="${cosmeticVO.file2.length() > 0}">
              ${cosmeticVO.file2 } 
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
      
      
      <div id='file4Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일2</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file4' value="${fn:toLowerCase(cosmeticVO.file4)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file4, '.jpg')}">
              <IMG src='./storage/${cosmeticVO.file3}'>
            </c:when>
            <c:when test="${fn:endsWith(file4, '.gif')}">
              <IMG id='file4'  src='./storage/${cosmeticVO.file4}'>
            </c:when>
            <c:when test="${fn:endsWith(file4, '.png')}">
              <IMG id='file4'  src='./storage/${cosmeticVO.file4}'>
            </c:when>
            <c:when test="${cosmeticVO.file4.length() > 0}">
              ${cosmeticVO.file4 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      
      <div class="form-group">   
        <label for="file4MF" class="col-xs-2 col-lg-2 control-label">업로드 파일2</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
      
       
        <div id='file6Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일3</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file6' value="${fn:toLowerCase(cosmeticVO.file6)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file6, '.jpg')}">
              <IMG src='./storage/${cosmeticVO.file5}'>
            </c:when>
            <c:when test="${fn:endsWith(file6, '.gif')}">
              <IMG id='file6'  src='./storage/${cosmeticVO.file6}'>
            </c:when>
            <c:when test="${fn:endsWith(file6, '.png')}">
              <IMG id='file6'  src='./storage/${cosmeticVO.file6}'>
            </c:when>
            <c:when test="${cosmeticVO.file6.length() > 0}">
              ${cosmeticVO.file6 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      
      <div class="form-group">   
        <label for="file6MF" class="col-xs-2 col-lg-2 control-label">업로드 파일3</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file6MF' id='file6MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
       
       <div id='file8Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일4</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file8' value="${fn:toLowerCase(cosmeticVO.file8)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file8, '.jpg')}">
              <IMG src='./storage/${cosmeticVO.file7}'>
            </c:when>
            <c:when test="${fn:endsWith(file8, '.gif')}">
              <IMG id='file8'  src='./storage/${cosmeticVO.file8}'>
            </c:when>
            <c:when test="${fn:endsWith(file8, '.png')}">
              <IMG id='file8'  src='./storage/${cosmeticVO.file8}'>
            </c:when>
            <c:when test="${cosmeticVO.file8.length() > 0}">
              ${cosmeticVO.file8 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      
      <div class="form-group">   
        <label for="file8MF" class="col-xs-2 col-lg-2 control-label">업로드 파일4</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file8MF' id='file8MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
       
       
       
        <div id='file10Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일5</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file10' value="${fn:toLowerCase(cosmeticVO.file10)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file10, '.jpg')}">
              <IMG src='./storage/${cosmeticVO.file9}'>
            </c:when>
            <c:when test="${fn:endsWith(file10, '.gif')}">
              <IMG id='file10'  src='./storage/${cosmeticVO.file10}'>
            </c:when>
            <c:when test="${fn:endsWith(file10, '.png')}">
              <IMG id='file10'  src='./storage/${cosmeticVO.file10}'>
            </c:when>
            <c:when test="${cosmeticVO.file10.length() > 0}">
              ${cosmeticVO.file10 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      
      <div class="form-group">   
        <label for="file10MF" class="col-xs-2 col-lg-2 control-label">업로드 파일5</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file10MF' id='file10MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
       
       
       
      <hr/>
      <div>
      <li class='right'>
        <button type="submit" class="btn btn-success btn-lg">수정</button>
        <button type="button" onclick="location.href='./list.jsp'" class="btn btn-danger btn-lg">취소</button>
      </li>         
    </ul>
  </fieldset>
</FORM>
</DIV>

<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 

