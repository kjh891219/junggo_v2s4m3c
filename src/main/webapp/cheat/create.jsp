<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script>
<script type="text/javascript">
$(function(){

});
</script>

</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

<DIV class='title'>허위상품신고</DIV>

<DIV class='content' style='width: 80%;'>
<FORM name='frm' method='POST' action='./create.do'
 enctype="multipart/form-data">
<div class='content_menu' style='width: 100%;'>
      <A href='./create.do'>등록</A>｜
      <A href='./read.do?ctno=${vo.ctno }'>상세보기</A>｜
      <A href='./read.do?ctno=${vo.ctno }'>수정</A>｜
      <A href='./list.do'>목록</A>｜
      <A href='./delete.do?ctno=${vo.ctno }'>삭제</A>｜
      <A href="javascript:location.reload();">새로고침</A>
    </div>
  <fieldset>
    <ul> 
    <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value='허위상품 신고할께요' required="required">
      </li>
      <li>
        <label class='label' for='gubun'>구분</label>
        <select id = "gubun" name="gubun">
        <option value="물품미발송" selected='selected'>물품미발송</option>
        <option value="상태불량">상태불량</option>
        <option value="이미테이션">이미테이션</option>
        <option value="택배착불">택배착불</option>
        <option value="더치트,사이버안전국 등록자">더치트,사이버안전국 등록자</option>
        </select>
      
       <div class="col-xs-5">  
        <label for='region'>*지역</label>
         <select name='region' id='region' class="">
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
      </li>
      <li>
        <label class='label' for=occurday>발생일자</label>
        <input type='date' name='occurday' id='occurday' value='2016-10-01' required="required">
        <label class='label' for='buyprice'>구매금액</label>
        <input type='number' name='buyprice' id='buyprice' value=5000 required="required">
      </li>
      <li>
        <label class='label' for='cheatid'>허위상품등록자 ID</label>
        <input type='text' name='cheatid' id='cheatid' value='SAGIGGUN' >
      
        <label class='label' for='cheattel'>허위상품등록자 전화번호</label>
        <input type='text' name='cheattel' id='cheattel' value='010-1234-1234' >
      </li>
      <li>
        <label class='label' for='cheatemail'>허위상품등록자 이메일</label>
        <input type='text' name='cheatemail' id='cheatemail' value='BADNOM@mail.com' >
       
        <label class='label' for='hit'>조회수</label>
        <input type='text' name='hit' id='hit' value=3>
      </li>
      <li>
        <label class='label' for='content'>사건 내용</label>
        <div><textarea name='content' id='content' required="required"> 물품을 받지 못했어요</textarea></div>
      </li>
      <li>
        <label class='label' for='userid'>등록자 ID</label>
        <input type='text' name='userid' id='userid' value='chanmi' required="required">
      </li>
      <li>
        <label class='label' for='email'>등록자 이메일</label>
        <input type='text' name='email' id='email' value='persion@daum.net' required="required">
        
        <label class='label' for='tel'>등록자 연락처</label>
        <input type='text' name='tel' id='tel' value='123456' required="required">
      </li>
      <li>
        <label class='label' for='nickname'>등록자 별명</label>
        <input type='text' name='nickname' id='nickname' value='속상해요' required="required">
      </li>
      <li>
        <label class='label' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='1234' required="required">
      </li>
      <li>
      <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>    
      <li class='right'>
        <button type="submit">등록</button>
        <button type="button" onclick="location.href='./list.do'">목록</button>
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
