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
<script type="text/javascript" src="../js/event.js"></script>



</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->



<DIV class='center-block'>
<DIV class='title'>글수정</DIV>
<FORM name='frm' method='POST' action='./update.do' class='form-inline' enctype="multipart/form-data">
  <input type='hidden' name='ctno' id='ctno' value='${cheatVO.ctno}'>
  <fieldset>
    <ul>
    <div class="row">
      <div class="col-xs-5">
        <label class='select' for='gubun'>*신고구분</label>
        <select name='gubun' id="gubun" class="form-control-lg-10-lg-10-lg-10-lg-10">
           <option value="${cheatVO.gubun }" selected="selected">${cheatVO.gubun }</option>
           <option value="물품미발송" >물품미발송</option>
           <option value="상태불량">상태불량</option>
           <option value="이미테이션">이미테이션</option>
           <option value="택배착불">택배착불</option>
        </select>
      </div>
      <div class="col-xs-5">
      <label for='tel' >*전화번호</label>
        <input type='text' name='tel' id='tel'  required="required" value='${cheatVO.tel }' class="form-control-lg-10-lg-10-lg-10"> 
      </div>
      </div>
      <hr/>
      <div class="row">
      <div class="col-xs-5"> 
        <label for='nickname'>*별명</label>
        <input type='text' name='nickname' id='nickname' value= '${cheatVO.nickname }' required="required" readonly="readonly" class='form-control-lg-10-lg-10-lg-10-lg-10'/>
      </div>
     <div class="col-xs-5">    
        <label for='passwd'>*패스워드</label>
        <input type='password' name='passwd' id='passwd' value='${cheatVO.passwd }' required="required" class='form-control-lg-10-lg-10-lg-10'/>
      </div>
      </div>
      <hr/>
            
      <div class="row">
             <div class="col-xs-5">  
        <label for='region'>*지역</label>
         <select name='region' id='region' class="form-control-lg-10-lg-10-lg-10">
           <option value="${cheatVO.region }" selected="selected">${cheatVO.region }</option>
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
        <div class="form-group">
        <label for='content'>상세설명</label>
        <textarea rows="10" cols="100"  name="content" id="content" placeholder="내용을 입력하세요" class="form-group">${cheatVO.content}</textarea>
      </div>
      <hr/>

      <div class='inpo'>사기 정보</div>
         <div class='line_box' id='ul_box_2'></div>
            <div class="row">
              <label for='cheatid' class='col-xs-2 col-lg-2 need'>'허위상품등록자 ID</label>
              <input type='text' name='cheatid' id='cheatid' value= '${cheatVO.cheatid}' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
              </div>
            <div class="row">
               <label for='cheattel'  class='col-xs-2 col-lg-2 need'>허위상품등록자연락처</label>
              <input type='text' name='cheattel' id='cheattel'  required="required" value='${cheatVO.cheattel }' class="col-xs-3 col-lg-3"> 
            </div>
            <div class="row">  
              <label for='cheatemail' class='col-xs-2 col-lg-2 need'>허위상품등록자이메일</label>
              <input type='text' name='cheatemail' id='cheatemail'  required="required" value='${cheatVO.cheatemail }'  class="col-xs-3 col-lg-3">
            </div>
            
            <hr/>
      <h3>추가항목</h3>
      <div class='inpo'>신고자 정보</div>
      <div class='line_box' id='ul_box_1'></div>
        <div class="row"> 
        <label for='buyprice class='col-xs-2 col-lg-2 need'>사기금액</label>
        <input type='text' name='buyprice' id='buyprice'  required="required" value='${cheatVO.buyprice }' class="col-xs-3 col-lg-3">원
        </div>
        <div class="row">       
        <label for='occurday'  class='col-xs-2 col-lg-2 choice'>발생시기</label>
        <input type='text' name='occurday' id='occurday' value='${cheatVO.occurday }' class="col-xs-3 col-lg-3"> 
        </div>
        <div class='line_box' id='ul_box_2'></div>
            <div class="row">
              <label for='nickname' class='col-xs-2 col-lg-2 need'>별명</label>
              <input type='text' name='nickname' id='nickname' value= '${cheatVO.nickname }' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
              </div>
          
            <div class="row">  
              <label for='email' class='col-xs-2 col-lg-2 need'>이메일</label>
              <input type='text' name='email' id='email'  required="required" value='${cheatVO.email }'  class="col-xs-3 col-lg-3">
            </div>
  
      <hr/>  
                
      <div id='file2Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file2' value="${fn:toLowerCase(cheatVO.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              <IMG src='./storage/${cheatVO.file1}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              <IMG id='file2'  src='./storage/${cheatVO.file2}'>
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
              <IMG id='file2'  src='./storage/${cheatVO.file2}'>
            </c:when>
            <c:when test="${cheatVO.file2.length() > 0}">
              ${cheatVO.file2 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      <hr/>
      <div class="form-group">   
        <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>      
      <hr/>
      <div id='file4Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일2</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file4' value="${fn:toLowerCase(cheatVO.file4)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file4, '.jpg')}">
              <IMG src='./storage/${cheatVO.file3}'>
            </c:when>
            <c:when test="${fn:endsWith(file4, '.gif')}">
              <IMG id='file4'  src='./storage/${cheatVO.file4}'>
            </c:when>
            <c:when test="${fn:endsWith(file4, '.png')}">
              <IMG id='file4'  src='./storage/${cheatVO.file4}'>
            </c:when>
            <c:when test="${cheatVO.file4.length() > 0}">
              ${cheatVO.file4 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      <hr/>
      <div class="form-group">   
        <label for="file4MF" class="col-xs-2 col-lg-2 control-label">업로드 파일2</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
      
       <hr/>
        <div id='file6Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일3</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file6' value="${fn:toLowerCase(cheatVO.file6)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file6, '.jpg')}">
              <IMG src='./storage/${cheatVO.file5}'>
            </c:when>
            <c:when test="${fn:endsWith(file6, '.gif')}">
              <IMG id='file6'  src='./storage/${cheatVO.file6}'>
            </c:when>
            <c:when test="${fn:endsWith(file6, '.png')}">
              <IMG id='file6'  src='./storage/${cheatVO.file6}'>
            </c:when>
            <c:when test="${cheatVO.file6.length() > 0}">
              ${cheatVO.file6 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      <hr/>
      <div class="form-group">   
        <label for="file6MF" class="col-xs-2 col-lg-2 control-label">업로드 파일3</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file6MF' id='file6MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
       <hr/>
       <div id='file8Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일4</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file8' value="${fn:toLowerCase(cheatVO.file8)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file8, '.jpg')}">
              <IMG src='./storage/${cheatVO.file7}'>
            </c:when>
            <c:when test="${fn:endsWith(file8, '.gif')}">
              <IMG id='file8'  src='./storage/${cheatVO.file8}'>
            </c:when>
            <c:when test="${fn:endsWith(file8, '.png')}">
              <IMG id='file8'  src='./storage/${cheatVO.file8}'>
            </c:when>
            <c:when test="${cheatVO.file8.length() > 0}">
              ${cheatVO.file8 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      <hr/>
      <div class="form-group">   
        <label for="file8MF" class="col-xs-2 col-lg-2 control-label">업로드 파일4</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file8MF' id='file8MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
              
       <hr/>
        <div id='file10Panel' class="form-group">
        <label for="content" class="col-xs-2 col-lg-2 control-label">등록된 파일5</label>
        <div class="col-xs-10 col-lg-10">
          <c:set var='file10' value="${fn:toLowerCase(cheatVO.file10)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file10, '.jpg')}">
              <IMG src='./storage/${cheatVO.file9}'>
            </c:when>
            <c:when test="${fn:endsWith(file10, '.gif')}">
              <IMG id='file10'  src='./storage/${cheatVO.file10}'>
            </c:when>
            <c:when test="${fn:endsWith(file10, '.png')}">
              <IMG id='file10'  src='./storage/${cheatVO.file10}'>
            </c:when>
            <c:when test="${cheatVO.file10.length() > 0}">
              ${cheatVO.file10 } 
            </c:when>
          </c:choose>
        </div>
      </div>
      <hr/>
      <div class="form-group">   
        <label for="file10MF" class="col-xs-2 col-lg-2 control-label">업로드 파일5</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file10MF' id='file10MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </div>
       <hr/>
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

