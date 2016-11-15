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
    
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<link href="/junggo/css/style.css" rel="Stylesheet" type="text/css">
<script src="/junggo/js/event.js"></script>


<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
  $(document).ready(function() {
    
    if($(".left").height() < $(".right").height()){
       $(".left").height($(".right").height());
    }
    
  });
</script>

</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
   <jsp:include page="/menu/top.jsp" flush='false' />
   <jsp:include page="/menu/left.jsp" flush='false' />
    <jsp:include page="/menu/community_left.jsp" flush='false' />

<!-- ----------------------------------------- -->
<div class="float_l right " style="width:80%;">
 <div class="container" style="min-height:380px;"> 

<DIV class='title'><span>글등록</span></DIV>
   <div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
<FORM name='frm' method='POST' action='./create.do' class=''  enctype="multipart/form-data"> 
 <input type='hidden' name='userid' id='userid' value= '${userid }'/>
 <input type='hidden' name='passwd' id='passwd' value= '${pwd }'/>

<DIV class='content_form' style="margin-top:30px;">
     <DIV class="">
     <div class="float_l _left">
      <div class="">
        <label class="select need_e" for='gubun'>신고구분</label>
        <div>
        <select name='gubun' id="gubun" class="full">
                   <option value="물품미발송" selected="selected">물품미발송</option>
           <option value="상태불량">상태불량</option>
           <option value="이미테이션">이미테이션</option>
           <option value="택배착불">택배착불</option>
        </select>
        </div>
      </div>
       </div>
        <div class='float_l _right' >
        <div class="">  
           <label for='region' class='need_e'>지역</label>
           <div>
              <select name='region' id='region' class="form-control-lg-10-lg-10-lg-10 full" >
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
       </div>
      <div class='both'></div>
      </DIV>
      <hr/>
      <div class="row">
        <label for='title' class='col-xs-2 col-lg-2 need'>제목</label>
        <input type='text' name='title' id='title' required="required" value='' class="col-xs-9 col-lg-9">
      </div>
      <div>
        <label for='content' class='choice_e'>상세설명</label>
        <textarea rows="29" name="content" id="content" placeholder="내용을 입력하세요"  class="col-xs-9 col-lg-9"></textarea>
      </div>
      <hr/>
        <div class="row need">   
        <label for='file2MF' class='col-xs-2 col-lg-2'>업로드 파일</label>
        <input type="file" name='file2MF' id='file2MF' size='40'>
      </div>
      <div class="row choice" >   
        <label for='file4MF' class='col-xs-2 col-lg-2'>업로드 파일</label>
        <input type="file" name='file4MF' id='file4MF' size='40'>
      </div>
      <div class="row choice">   
        <label for='file6MF' class='col-xs-2 col-lg-2'>업로드 파일</label>
        <input type="file" name='file6MF' id='file6MF' size='40'>
      </div>
      <div class="row choice">   
        <label for='file8MF' class='col-xs-2 col-lg-2'>업로드 파일</label>
        <input type="file" name='file8MF' id='file8MF' size='40'>
      </div>
      <div class="row choice">   
        <label for='file10MF' class='col-xs-2 col-lg-2'>업로드 파일</label>
        <input type="file" name='file10MF' id='file10MF' size='40'>
      </div>
      <hr>
      <h3>추가항목</h3>
            <div class='inpo'>사기 정보</div>
         <div class='line_box' id='ul_box_2'></div>
            <div class="row">
              <label for='cheatid' class='col-xs-3 col-lg-3 need'>'허위상품등록자 ID</label>
              <input type='text' name='cheatid' id='cheatid' value= '흰둥이' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
              </div>
            <div class="row">
               <label for='cheattel'  class='col-xs-3 col-lg-3 need'>허위상품등록자연락처</label>
              <input type='text' name='cheattel' id='cheattel'  required="required" value='010-1111-2222' class="col-xs-3 col-lg-3"> 
            </div>
            <div class="row">  
              <label for='cheatemail' class='col-xs-3 col-lg-3 need'>허위상품등록자이메일</label>
              <input type='text' name='cheatemail' id='cheatemail'  required="required" value='qwkjd@nqwk.com'  class="col-xs-3 col-lg-3">
            </div>
      <hr/>
  
      <div class='inpo'>신고자 정보</div>
      <div class='line_box' id='ul_box_1'></div>
        <div class="row"> 
        <label for='buyprice' class='col-xs-3 col-lg-3 need'>사기금액</label>
        <input type='text' name='buyprice' id='buyprice'  required="required" value='100000' class="col-xs-3 col-lg-3">원
        </div>
        <div class="row">       
        <label for='occurday'  class='col-xs-3 col-lg-3 choice'>발생시기</label>
        <input type='text' name='occurday' id='occurday' value='2016년10월' class="col-xs-3 col-lg-3"> 
        </div>
            <div class="row">
              <label for='nickname' class='col-xs-3 col-lg-3 need'>별명</label>
              <input type='text' name='nickname' id='nickname' value= '${memberVO.nickname }' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
              </div>
            <div class="row">
               <label for='tel'  class='col-xs-3 col-lg-3 need'>전화번호</label>
              <input type='text' name='tel' id='tel'  required="required" value='010-1111-2222' class="col-xs-3 col-lg-3"> 
            </div>
            <div class="row">  
              <label for='email' class='col-xs-3 col-lg-3 need'>이메일</label>
              <input type='text' name='email' id='email'  required="required" value='${memberVO.email }'  class="col-xs-3 col-lg-3">
            </div>
  
      
      <div class='text_r' >
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.jsp'" class="btn btn-danger btn-lg">취소</button>
      </div>
      </div>
</FORM>
</DIV>
</DIV>
<div class="both"></div>
<!-- -------------------------------------------- -->
 <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 

