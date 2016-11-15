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
</script>



</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
   <jsp:include page="/menu/top.jsp" flush='false' />
   <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->


<DIV class=container>

<DIV class='title'><span>글등록</span></DIV>
   <div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
<FORM name='frm' method='POST' action='./create.do' class=''  enctype="multipart/form-data"> 
 <input type='hidden' name='userid' id='userid' value= '${userid }'/>
 <input type='hidden' name='passwd' id='passwd' value= '${pwd }'/>
 <input type="hidden" name='deal_status' value="거래중" checked="checked">
<DIV class='content_form' style="margin-top:30px;">
     <DIV class="">
     <div class="float_l _left">
      <div class="">
        <label class="select need_e" for='category'>분류</label>
        <div>
        <select name='category' id="category" class="full">
           <option value="노트북/넷북" selected="selected">노트북/넷북</option>
           <option value="모니터" selected="selected">모니터</option>
           <option value="데스크탑/본체">데스크탑/본체</option>
           <option value="주변기기">주변기기</option>
        </select>
        </div>
      </div>
        <div class="">  
           <label for='deal_code ' class="control-label need_e">거래구분</label>
           <div>
              <select name='deal_code' id="deal_code" class="control-label full">
                 <option value="팝니다" selected="selected">팝니다</option>
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
       <div class=""> 
         <label for='deal_way' class='need_e'>거래방식</label>
         <div>
            <select name='deal_way' id='deal_way' class="form-control-lg-10-lg-10-lg-10 full">
              <option value="직거래" selected="selected">직거래</option>
              <option value="</">택배</option>
           </select >
        </div>
      </div>
        </div>
      <div class='both'></div>
      </DIV>
      <hr/>
      <div class="row">
        <label for='title' class='col-xs-2 col-lg-2 need'>제목</label>
        <input type='text' name='title' id='title' required="required" value='디카팔아요' class="col-xs-9 col-lg-9">
      </div>
      <div>
        <label for='content' class='choice_e'>상세설명</label>
        <textarea rows="10" name="content" id="content" placeholder="내용을 입력하세요"  class="col-xs-9 col-lg-9">sqld책 싸게 팔아요</textarea>
      </div>
      <hr/>
        <div class="row need">   
        <label for='file1MF' class='col-xs-2 col-lg-2'>업로드 파일1</label>
        <input type="file" name='file1MF' id='file1MF' size='40'>
      </div>
      <div class="row choice" >   
        <label for='file2MF' class='col-xs-2 col-lg-2'>업로드 파일2</label>
        <input type="file" name='file2MF' id='file2MF' size='40'>
      </div>
      <div class="row choice">   
        <label for='file3MF' class='col-xs-2 col-lg-2'>업로드 파일3</label>
        <input type="file" name='file3MF' id='file3MF' size='40'>
      </div>
      <div class="row choice">   
        <label for='file4MF' class='col-xs-2 col-lg-2'>업로드 파일4</label>
        <input type="file" name='file4MF' id='file4MF' size='40'>
      </div>
      <div class="row choice">   
        <label for='file5MF' class='col-xs-2 col-lg-2'>업로드 파일5</label>
        <input type="file" name='file5MF' id='file5MF' size='40'>
      </div>
      <hr>
      
      <h3>추가항목</h3>
      <div class='inpo'>상품 정보</div>
      <div class='line_box' id='ul_box_1'></div>
         <div class="row">
         <label for='product_code' class='col-xs-2 col-lg-2 need'>상품구분</label>
         <input type="radio" name='product_code' value="중고품"  checked="checked"><span class='radio_text'>중고품</span>
         <input type="radio" name='product_code' value="신상품" ><span class='radio_text'>신상품</span>
        </div>
        <div class="row"> 
        <label for='hprice' class='col-xs-2 col-lg-2 need'>희망가격</label>
        <input type='text' name='hprice' id='hprice'  required="required" value='100000' class="col-xs-3 col-lg-3">원
        </div>
        <div class="row">       
        <label for='purc_date'  class='col-xs-2 col-lg-2 choice'>구입시기</label>
        <input type='text' name='purc_date' id='purc_date' value='2016년10월' class="col-xs-3 col-lg-3"> 
        </div>
      <div class="row"> 
        <label for='quantity' class='col-xs-2 col-lg-2 choice'>수량</label>
        <input type='text' name='quantity' id='quantity' value='1' class="col-xs-3 col-lg-3">
      </div>

      <hr/>
      <div class='inpo'>판매자 정보</div>
         <div class='line_box' id='ul_box_2'></div>
            <div class="row">
              <label for='nickname' class='col-xs-2 col-lg-2 need'>별명</label>
              <input type='text' name='nickname' id='nickname' value= '${nickname }' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
              </div>
            <div class="row">
               <label for='tel'  class='col-xs-2 col-lg-2 need'>전화번호</label>
              <input type='text' name='tel' id='tel'  required="required" value='010-1111-2222' class="col-xs-3 col-lg-3"> 
            </div>
            <div class="row">  
              <label for='email' class='col-xs-2 col-lg-2 need'>이메일</label>
              <input type='text' name='email' id='email'  required="required" value='${email}'  class="col-xs-3 col-lg-3">
            </div>
  
      <hr/>
      
      <div class='text_r' >
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.jsp'" class="btn btn-danger btn-lg">취소</button>
      </div>
      </div>
</FORM>
</DIV>
<!-- -------------------------------------------- -->
 <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 

