<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="./jquery.cookie.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>

<script type="text/JavaScript">
  window.onload=function(){
/*     if ('${opentype }' == 'U'){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
    } */
    
    /* 화면 처음 로딩할 땐 보이지 않기 */
    $('#divReply').hide(); 
    
    /* btnreply 버튼을 클릭하면 답글 입력 란이 보인다. */
    $('#btnreply').click(function(){ 
      $('#divReply').show(); //보이기  
    });
  };
  
  /* 답글 등록 실행 */
  function create(){
    if ((val("comment") == null) || val("comment") =="")
      {
        alert("글 내용을 입력하세요");
        $("#comment").focus();
        return false;        
      }
    if ((val("passwd") == null) || val("passwd") =="")
    {
      alert("비밀번호를 입력하세요");
      $("#passwd").focus();
      return false;        
    }
  }
</script>
 
<script type="text/javascript">
 
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
<DIV class='title'>허위상품신고 </DIV>
 
<DIV class='content' style='width: 80%;'>
<div class='content_menu' style='width: 100%;'>
      <A href='./create.do?ctno=0&opentype=R'>등록</A>｜
      <A href='./read.do?ctno=${cheatVO.ctno }'>상세보기</A>｜
      <A href='./create.do?ctno=${cheatVO.ctno }&opentype=U'>수정</A>｜
      <A href='./list.do'>목록</A>｜
      <A href='./delete.do?ctno=${cheatVO.ctno }'>삭제</A>｜
      <A href="javascript:location.reload();">새로고침</A>
    </div>
<FORM name='frm' method='POST'  
        enctype="multipart/form-data">
   <input type='hidden' id='ctno' name='ctno' value='${cheatVO.ctno }'>
   
      <ul> 
  
      <li>
        <label class='label' for='title'>제목</label>
        <input type='text' name='title' id='title' value='${cheatVO.title}' required="required">
      </li>
      <li>
        <label class='label' for='gubun'>구분</label>
        <select id = "gubun" name="gubun">
        <option value="물품미발송" ${cheatVO.gubun == "물품미발송" ? "selected=selected" : "" }>물품미발송</option> 
        <option value="상태불량" ${cheatVO.gubun == "상태불량" ? "selected=selected" : "" }>상태불량</option> 
        <option value="이미테이션" ${cheatVO.gubun == "이미테이션" ? "selected=selected" : "" }>이미테이션</option> 
        <option value="택배착불" ${cheatVO.gubun == "택배착불" ? "selected=selected" : "" }>택배착불</option> 
        <option value="더치트,사이버안전국 등록자" ${cheatVO.gubun == "더치트,사이버안전국 등록자" ? "selected=selected" : "" }>더치트,사이버안전국 등록자</option> 
        </select>
       
        <label for='region'>*지역</label>
         <select name='region' id='region' class="">
           <option value="서울" ${cheatVO.region == "서울" ? "selected=selected" : "" }>서울</option>
           <option value="인천" ${cheatVO.region == "인천" ? "selected=selected" : "" }>인천</option>
           <option value="대구" ${cheatVO.region == "대구" ? "selected=selected" : "" }>대구</option>
           <option value="대전" ${cheatVO.region == "대전" ? "selected=selected" : "" }>대전</option>
           <option value="광주" ${cheatVO.region == "광주" ? "selected=selected" : "" }>광주</option>
           <option value="울산" ${cheatVO.region == "울산" ? "selected=selected" : "" }>울산</option>
           <option value="부산" ${cheatVO.region == "부산" ? "selected=selected" : "" }>부산</option>
           <option value="경기" ${cheatVO.region == "경기" ? "selected=selected" : "" }>경기</option>
           <option value="강원" ${cheatVO.region == "강원" ? "selected=selected" : "" }>강원</option>
           <option value="경북" ${cheatVO.region == "경북" ? "selected=selected" : "" }>경북</option>
           <option value="경남" ${cheatVO.region == "경남" ? "selected=selected" : "" }>경남</option>
           <option value="전북" ${cheatVO.region == "전북" ? "selected=selected" : "" }>전북</option>
           <option value="전남" ${cheatVO.region == "전남" ? "selected=selected" : "" }>전남</option>
           <option value="충남" ${cheatVO.region == "충남" ? "selected=selected" : "" }>충남</option>
           <option value="충북" ${cheatVO.region == "충북" ? "selected=selected" : "" }>충북</option>
           <option value="제주" ${cheatVO.region == "제주" ? "selected=selected" : "" }>제주</option>
        </select>  
     
      </li>
      <li>
        <label class='label' for=occurday>발생일자</label>
        <input type='date' name='occurday' id='occurday' value='${cheatVO.occurday}' required="required">
        <label class='label' for='buyprice'>구매금액</label>
        <input type='number' name='buyprice' id='buyprice' value='${cheatVO.buyprice}' required="required">
      </li>
      <li>
        <label class='label' for='cheatid'>허위상품등록자 ID</label>
        <input type='text' name='cheatid' id='cheatid' value='${cheatVO.cheatid}' >
      
        <label class='label' for='cheattel'>허위상품등록자 전화번호</label>
        <input type='text' name='cheattel' id='cheattel' value='${cheatVO.cheattel}' >
      </li>
      <li>
        <label class='label' for='cheatemail'>허위상품등록자 이메일</label>
        <input type='text' name='cheatemail' id='cheatemail' value='${cheatVO.cheatemail}' >
      
        <label class='label' for='hit'>조회수</label>
        <input type='text' name='hit' id='hit' value='${cheatVO.cnt}'>
      </li>
      <li>
        <div><textarea name='content' id='content' required="required">${cheatVO.content}</textarea></div>
      </li>
      <li>
      <label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일 : ${cheatVO.file2 }</label>
        <div class="col-xs-10 col-lg-10">
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='20' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>    
      <li>
        <label class='label' for='userid'>등록자 ID</label>
        <input type='text' name='userid' id='userid' value='${cheatVO.userid}' required="required">
      </li>
      <li>
        <label class='label' for='email'>등록자 이메일</label>
        <input type='text' name='email' id='email' value='${cheatVO.email}' required="required">
        <label class='label' for='tel'>등록자 연락처</label>
        <input type='text' name='tel' id='tel' value='${cheatVO.tel}' required="required">
      </li>
      <li>
        <label class='label' for='nickname'>등록자 별명</label>
        <input type='text' name='nickname' id='nickname' value='${cheatVO.nickname}' required="required">
      </li>
      
      <li>
        <label class='label' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value='${cheatVO.passwd}' required="required">
      </li>
      
    </ul>
 </FORM>
  
 <button id="btnreply" >답글등록</button> 
 <div id="divReply" style='text-align: center; background-color: #efefef; padding: 3px;' >
      <form name='frmreply' id='frmreply' class="form-inline" action='./reply.do' method='POST' >
      <input type ='hidden' id='ctno' name='ctno' value=${cheatVO.ctno }>
      <input type='hidden'  id='userid' name='userid' value='chanmi'  >
        <ul>
          <li>
          <textarea rows="2" cols="" id="rcomment" name="rcomment" style="width: 100%;"
              placeholder="글을 작성해 주세요" class="form-control" ></textarea></li>
          <li style="text-align: right;">
            작성자<input type='text' id='nickname' name='nickname'  value='chanmi' style='width: 100px; margin-left: 2px; '
            value='' class="form-control" > 
            비밀번호<input type='password' id='passwd' name='passwd' style='width: 100px; margin-left: 2px;' class="form-control"  >
<!--             <button type='button' onclick='create();'>등록</button></li> -->
            <button type='submit'>등록</button></li>
        </ul>
      </form>
    </div>
 
</DIV>
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 