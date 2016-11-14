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
<link href="${pageContext.request.contextPath}/css/style.css?ver=1" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>



<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');


  };

  
  
  </script>
<style type="text/css">

/* 전체 스타일 */

 
</style>

</head> 

<!-- ----------------------------------------- -->
<body>
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
 <div class="container">
     
  <div class='content_menu' style='width: 90%;'>
    <A href='../music/list.do' class='top_select'>게시판 목록</A> > 
  </div>
  
  <DIV class='title'><span>글수정</span></DIV>
  <div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
  <DIV class='content' style='width: 90%;'>
    <FORM name='frm' method='POST' action='./update.do'
                enctype="multipart/form-data">
      <input type='hidden' name='ctno' id='ctno' value='${musicVO.ctno}'>

<DIV class='content_form'>
   <DIV class="">
   <div class="float_l _left">
    <div class="">
     <label class="select need_e" for='category'>분류</label>
    <div>
     <select name='category' id='category' class="full">
         <option value="${musicVO.category }" selected="selected">${musicVO.category }</option> 
         <option value="아이팟/MP3" selected="selected">아이팟/MP3</option>
         <option value="음향기기" >음향기기</option>
         <option value="CD/DVD/음반" >CD/DVD/음반</option>
         <option value="악기" >악기</option>
         <option value="주변기기">주변기기</option>
      </select>
     </div>
  </div>  
   <div class="">  
     <label for='deal_code ' class="control-label need_e">거래구분</label>
     <div>
       <select name='deal_code' id='deal_code' class="control-label full">
       <option value="${musicVO.deal_code }" selected="selected">${musicVO.deal_code }</option>
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
    <select name='region' id='region'  class="form-control-lg-10-lg-10-lg-10 full" >
            <option value="${musicVO.region }" selected="selected">${musicVO.region }</option>
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
      </div>
    </div>  
    <div class=""> 
     <label for='deal_way' class='need_e'>거래방식</label>
   <div>
   <select name='deal_way' id='deal_way'>
      <option value="${musicVO.deal_way}" selected="selected">${musicVO.deal_way}</option>
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
     <input type='text' name='title' id='title' required="required" value='${musicVO.title} ' class="col-xs-9 col-lg-9">
   </div>
   <div class="row">
     <label for='content' class='col-xs-2 col-lg-2 choice'>상세설명</label>
     <textarea rows="10" name="content" id="content" placeholder="내용을 입력하세요"  class="col-xs-9 col-lg-9">${musicVO.content}</textarea>
   </div>
   
   <!-- 첫번째 파일 수정 -->
   <div id='file1Panel' class="row">
    <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일1</label>
        <c:set var='file1' value="${fn:toLowerCase(musicVO.file1)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file1, '.jpg')}">
              <IMG src='./storage/${musicVO.thumb}'>
            </c:when>
            <c:when test="${fn:endsWith(file1, '.gif')}">
              ${musicVO.file1 } 
            </c:when>
            <c:when test="${fn:endsWith(file1, '.png')}">
              ${musicVO.file1 } 
            </c:when>
            <c:when test="${carproductVO.file1.length() > 0}">
              ${musicVO.file1 } 
            </c:when>
          </c:choose>
     </div>
     <div class="row">   
       <label for="file1MF" class='col-xs-2 col-lg-2 need'>수정 파일1</label>
       <input type="file" class="form-control" name='file1MF' id='file1MF' size='40' >
     </div>
    
  <!-- 두번째 파일 수정 -->
   <div id='file2Panel' class="row">
    <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일2</label>
       <c:set var='file2' value="${fn:toLowerCase(musicVO.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              ${musicVO.file2}
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              ${musicVO.file2}
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
             ${musicVO.file2}
            </c:when>
            <c:when test="${musicVO.file2.length() > 0}">
              ${musicVO.file2 } 
            </c:when>
          </c:choose>
      </div>
      <div class="row">   
       <label for="file2MF" class='col-xs-2 col-lg-2 need'>수정 파일1</label>
       <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
      </div>
      
 <!-- 세번째 파일 수정 -->
   <div id='file3Panel' class="row">
     <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일3</label>
     <c:set var='file3' value="${fn:toLowerCase(musicVO.file3)}" />
        <c:choose>
          <c:when test="${fn:endsWith(file3, '.jpg')}">
              ${musicVO.file3 } 
          </c:when>
          <c:when test="${fn:endsWith(file3, '.gif')}">
              ${musicVO.file3 } 
          </c:when>
          <c:when test="${fn:endsWith(file3, '.png')}">
              ${musicVO.file3 } 
          </c:when>
          <c:when test="${musicVO.file3.length() > 0}">
              ${musicVO.file3 } 
          </c:when>
       </c:choose>
   </div>
   <div class="row">   
      <label for="file3MF" class='col-xs-2 col-lg-2 need'>수정 파일3</label>
      <input type="file" class="form-control" name='file3MF' id='file3MF' size='40' >
   </div>
   
  <!-- 네번째 파일 수정 --> 
   <div id='file4Panel' class="row">
     <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일4</label>
        <c:set var='file4' value="${fn:toLowerCase(musicVO.file4)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file4, '.jpg')}">
              ${musicVO.file4 } 
            </c:when>
            <c:when test="${fn:endsWith(file4, '.gif')}">
              ${musicVO.file4 } 
            </c:when>
            <c:when test="${fn:endsWith(file4, '.png')}">
              ${musicVO.file4 } 
            </c:when>
            <c:when test="${musicVO.file4.length() > 0}">
              ${musicVO.file4 } 
            </c:when>
          </c:choose>
       </div>
       <div class="row">   
        <label for="file4MF" class='col-xs-2 col-lg-2 need'>수정 파일4</label>
        <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
      </div>
      
  <!-- 다섯번째 파일 수정 -->    
    <div id='file5Panel' class="row">
      <label for="content" class='col-xs-2 col-lg-2 need'>업로드 파일5</label>
         <c:set var='file5' value="${fn:toLowerCase(musicVO.file5)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file5, '.jpg')}">
              ${musicVO.file5 } 
            </c:when>
            <c:when test="${fn:endsWith(file5, '.gif')}">
              ${musicVO.file5 } 
            </c:when>
            <c:when test="${fn:endsWith(file5, '.png')}">
              ${musicVO.file5 } 
            </c:when>
            <c:when test="${musicVO.file5.length() > 0}">
              ${musicVO.file5 } 
            </c:when>
          </c:choose>
      </div>
       <div class="row">   
        <label for="file5MF" class='col-xs-2 col-lg-2 need'>수정 파일5</label>
        <input type="file" class="form-control" name='file5MF' id='file5MF' size='40' >
      </div>
   
   <hr>
      <h3>추가항목</h3>
      <div class='inpo'>상품 정보</div>
      <div class='line_box' id='ul_box_1'></div>
         <div class="row">
       <label for='passwd' class='col-xs-2 col-lg-2 need'>글 비밀번호</label>
       <input type='text' name='passwd' id='passwd' value= '${musicVO.passwd}'/>
     </div>
      <div class="row">
        <label for='product_code' class='col-xs-2 col-lg-2 need'>상품구분</label>
         <input type="radio" name='product_code' value="중고품"  checked="checked"><span class='radio_text'>중고품</span>
         <input type="radio" name='product_code' value="신상품" ><span class='radio_text'>신상품</span>
      </div>
      <div class="row"> 
        <label for='hprice' class='col-xs-2 col-lg-2 need'>희망가격</label>
        <input type='text' name='hprice' id='hprice'  required="required" value='${musicVO.hprice}' class="col-xs-3 col-lg-3">원
      </div>
      <div class="row">       
        <label for='purc_date'  class='col-xs-2 col-lg-2 choice'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='${musicVO.purc_date}' class="col-xs-3 col-lg-3"> 
      </div>
      <div class="row"> 
        <label for='quantity' class='col-xs-2 col-lg-2 choice'>수량</label>
        <input type='text' name='quantity' id='quantity' value='${musicVO.quantity}' class="col-xs-3 col-lg-3">
      </div>
      
      <hr/>
      <div class='inpo'>판매자 정보</div>
      <div class='line_box' id='ul_box_2'></div>
        <div class="row">
         <label for='nickname' class='col-xs-2 col-lg-2 need'>별명</label>
          <input type='text' name='nickname' id='nickname' value= '${musicVO.nickname}' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
        </div>
        <div class="row">
         <label for='tel'  class='col-xs-2 col-lg-2 need'>전화번호</label>
         <input type='text' name='tel' id='tel'  required="required" value='${musicVO.tel }' class="col-xs-3 col-lg-3"> 
        </div>
        <div class="row">  
          <label for='email' class='col-xs-2 col-lg-2 need'>이메일</label>
          <input type='text' name='email' id='email'  required="required" value='${musicVO.email }'  class="col-xs-3 col-lg-3">
        </div>
  
      <hr/>
   
   
<%-- 
      <div class="form-group">  
      
      <ul>
       <li>

         <label class='label_1'  for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value=' ${musicVO.passwd}' required="required"><br><br>
         <label class='label_1'  for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value=' ${musicVO.nickname} '  required="required">
       </li>
       
       <li>
        <label class='label_1'   for='category'>카테고리 코드</label>
        <select name='category' id='category'>
              <option value="아이팟/MP3" selected="selected">아이팟/MP3</option>
              <option value="음향기기" >음향기기</option>
              <option value="CD/DVD/음반" >CD/DVD/음반</option>
              <option value="악기" >악기</option>
              <option value="주변기기">주변기기</option>
        </select>
        <label class='label_1'   for='deal_code'>거래구분 코드</label>
        <select name='deal_code' id='deal_code'>
          <option value="sale" selected="selected">팝니다</option>
          <option value="buy">삽니다</option>
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
          <label class='label_1'  for='deal_way'>거래방식</label>
        <select name='deal_way' id='deal_way'>
          <option value="field" selected="selected">현장거래</option>
          <option value="delivery">택배</option>
        </select>
      </li>
       
        <li>
        <label class='label_1'  for='hprice'>희망가격</label>
        <input type='text' name='hprice' id='hprice' value='${musicVO.hprice }' >
      </li>
      
      <li>
        <label class='label_1'  for='purc_date'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value=' ${musicVO.purc_date }' >
       
       <label class='label_1'  for='product_code'>거래방식</label>
        <select name='product_code' id='product_code'>
          <option value="old" selected="selected">중고</option>
          <option value="new">신상</option>
        </select>
      </li>
       
       <li>
        <label class='label_1'  for='title'>제목</label>
        <input type='text' name='title' id='title' size='50' value=' ${musicVO.title}'  required="required">
      </li>
      
      <li>
      <label for="content" class="col-xs-2 col-lg-2 control-label">내용</label>
        <div class="col-xs-10 col-lg-10">
          <textarea class="form-control" name='content' id='content'  rows='10' style='width: 100%;'>${musicVO.content}</textarea>
        </div>
      </li>
      
      <li>
      <label for="content" >등록된 파일1</label>
        <div>
          <c:set var='file1' value="${fn:toLowerCase(musicVO.file1)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file1, '.jpg')}">
              <IMG src='./storage/${musicVO.thumb}'>
            </c:when>
            <c:when test="${fn:endsWith(file1, '.gif')}">
              ${musicVO.file1 } 
            </c:when>
            <c:when test="${fn:endsWith(file1, '.png')}">
              ${musicVO.file1 } 
            </c:when>
            <c:when test="${musicVO.file1.length() > 0}">
              ${musicVO.file1 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file1MF" >업로드 파일</label>
        <div>
          <input type="file" class="form-control" name='file1MF' id='file1MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
     <li>
      <label for="content" >등록된 파일2</label>
        <div>
          <c:set var='file2' value="${fn:toLowerCase(musicVO.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              ${musicVO.file2}
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              ${musicVO.file2}
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
             ${musicVO.file2}
            </c:when>
            <c:when test="${musicVO.file2.length() > 0}">
              ${musicVO.file2 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file2MF" >업로드 파일2</label>
        <div>
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
     
      
     <li>
      <label for="content" >등록된 파일3</label>
        <div>
          <c:set var='file3' value="${fn:toLowerCase(musicVO.file3)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file3, '.jpg')}">
              ${musicVO.file3 } 
            </c:when>
            <c:when test="${fn:endsWith(file3, '.gif')}">
              ${musicVO.file3 } 
            </c:when>
            <c:when test="${fn:endsWith(file3, '.png')}">
              ${musicVO.file3 } 
            </c:when>
            <c:when test="${musicVO.file3.length() > 0}">
              ${musicVO.file3 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file3MF" >업로드 파일3</label>
        <div>
          <input type="file" class="form-control" name='file3MF' id='file3MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
      <li>
      <label for="content" >등록된 파일4</label>
        <div>
          <c:set var='file4' value="${fn:toLowerCase(musicVO.file4)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file4, '.jpg')}">
              ${musicVO.file4 } 
            </c:when>
            <c:when test="${fn:endsWith(file4, '.gif')}">
              ${musicVO.file4 } 
            </c:when>
            <c:when test="${fn:endsWith(file4, '.png')}">
              ${musicVO.file4 } 
            </c:when>
            <c:when test="${musicVO.file4.length() > 0}">
              ${musicVO.file4 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file4MF" >업로드 파일4</label>
        <div>
          <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
            <li>
      <label for="content" >등록된 파일5</label>
        <div>
          <c:set var='file5' value="${fn:toLowerCase(musicVO.file5)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file5, '.jpg')}">
              ${musicVO.file5 } 
            </c:when>
            <c:when test="${fn:endsWith(file5, '.gif')}">
              ${musicVO.file5 } 
            </c:when>
            <c:when test="${fn:endsWith(file5, '.png')}">
              ${musicVO.file5 } 
            </c:when>
            <c:when test="${musicVO.file5.length() > 0}">
              ${musicVO.file5 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file5MF" >업로드 파일5</label>
        <div>
          <input type="file" class="form-control" name='file5MF' id='file5MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      

         <li>
        <label for='email'>E-mail</label><br>
        <input type='text' name='email' id='email'  required="required" value='${musicVO.email }' class="form-control-lg-10-lg-10-lg-10">
      </li>

      
      <li>
        <label for='tel'>Tel</label><br>
        <input type='text' name='tel' id='tel' size='14' value=' ${usedcarVO.tel } ' required="required">
      </li>
      
        </ul> 
        </div> --%>
        

      <div class='text_r' >
        <button type="submit">수정</button>
        <button type="button" onclick="location.href='./list.do?ctno= ${musicVO.ctno}'">목록[취소]</button>
      </DIV>         
    </FORM>
  </DIV>

</div>
     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>

</html> 


