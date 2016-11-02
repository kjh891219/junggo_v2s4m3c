<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">

</head>
 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
   <jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

  <div class='container'>
  <DIV class='title'>책 수정</DIV>
  <div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
  <FORM name='frm' method='POST' action='./update.do' enctype="multipart/form-data">
  <input type="hidden" name="bno" id="bno" value="${bookVO.bno}">
  <DIV class='content_form'>
      <div class='content_menu'>
        <A href='./create.do'>등록</A>｜ 
        <A href='./read.do}'>상세보기</A>｜ 
        <A href='./list.do'>목록</A>｜ 
        <A href='./delete.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>｜
        <A href="javascript:location.reload();">새로고침</A>
      </div>
         <div class='float_l _left'>
          <label class='select need_e' for='category'>분류</label> 
            <div class=''>
            <select id="category" name="category" class="full">
              <option value="인문" ${bookVO.category=='인문'? "selected='selected'":""}>인문</option>
              <option value="예술" ${bookVO.category=='예술'? "selected='selected'":""}>예술</option>
              <option value="IT" 	${bookVO.category=='IT'? "selected='selected'":""}>IT</option>
              <option value="참고서" ${bookVO.category=='참고서'? "selected='selected'":""}>참고서</option>
              <option value="여행" ${bookVO.category=='여행'? "selected='selected'":""}>여행</option>
              <option value="요리" ${bookVO.category=='요리'? "selected='selected'":""}>요리</option>
              <option value="어린이" ${bookVO.category=='어린이'? "selected='selected'":""}>어린이</option>
              <option value="만화" ${bookVO.category=='만화'? "selected='selected'":""}>만화</option>
          </select>
          </div>
            <div class="">
              <label for='deal_code' class='control-label need_e'>거래구분</label> 
               <select name='deal_code' id="deal_code" class="control-label full">
                <option value="팝니다" ${bookVO.deal_code=='팝니다'? "selected='selected'":""}>팝니다</option>
                <option value="삽니다" ${bookVO.deal_code=='삽니다'? "selected='selected'":""}>삽니다</option>
               </select>
            </div>
         </div>   
         <div  class='float_l _right' >
            <div>
              <label for='region' class='need_e'>지역</label> 
               <div>
              <select name='region' id='region' class="form-control-lg-10-lg-10-lg-10 full" >
                <option value="서울" ${bookVO.region=='서울'? "selected='selected'":""}>서울</option>
                <option value="인천" ${bookVO.region=='인천'? "selected='selected'":""}>인천</option>
                <option value="대구" ${bookVO.region=='대구'? "selected='selected'":""}>대구</option>
                <option value="대전" ${bookVO.region=='대전'? "selected='selected'":""}>대전</option>
                <option value="광주" ${bookVO.region=='광주'? "selected='selected'":""}>광주</option>
                <option value="울산" ${bookVO.region=='울산'? "selected='selected'":""}>울산</option>
                <option value="부산" ${bookVO.region=='부산'? "selected='selected'":""}>부산</option>
                <option value="경기" ${bookVO.region=='경기'? "selected='selected'":""}>경기</option>
                <option value="강원" ${bookVO.region=='강원'? "selected='selected'":""}>강원</option>
                <option value="경북" ${bookVO.region=='경북'? "selected='selected'":""}>경북</option>
                <option value="경남" ${bookVO.region=='경남'? "selected='selected'":""}>경남</option>
                <option value="전북" ${bookVO.region=='전북'? "selected='selected'":""}>전북</option>
                <option value="전남" ${bookVO.region=='전남'? "selected='selected'":""}>전남</option>
                <option value="충남" ${bookVO.region=='충남'? "selected='selected'":""}>충남</option>
                <option value="충북" ${bookVO.region=='충북'? "selected='selected'":""}>충북</option>
                <option value="제주" ${bookVO.region=='제주'? "selected='selected'":""}>제주</option>
              </select>
            </div>
               <div>
             
                  <label for='deal_way' class='need_e'>거래방식</label> 
                  <div>
                    <select name='deal_way' id='deal_way' class="form-control-lg-10-lg-10-lg-10 full">
                      <option value="직거래" 	${bookVO.deal_way=='직거래'? "selected='selected'":""}>직거래</option>
                      <option value="택배"		${bookVO.deal_way=='택배'? "selected='selected'":""}>택배</option>
                    </select>
                  </div>
               </div>
            </div>
            
         </div>
         <div class='both'></div>
         <hr>
         
         <div class='row'> 
          <label class='col-xs-2 col-lg-2 need' for='title' class="col-xs-9 col-lg-9 need">제목</label> 
            <input type='text' name='title' id='title' 
               value='${bookVO.title }' class="col-xs-9 col-lg-9" required="required">
         </div>    
         <div class='row'>
           <label for='content' class='col-xs-2 col-lg-2 choice'>상세설명</label>
           <textarea rows='10' name='content' id='content' required="required" class="col-xs-9 col-lg-9">${bookVO.content}</textarea>
         </div>
         <hr/>
               
         <span style="margin-left:18%;">      
         Preview(미리보기) 이미지 자동 생성됩니다.
         </span>
         <div class='row'>
          <label for="file1" class="col-xs-2 col-lg-2">업로드 파일1 </label>
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file1MF' id='file1MF' size='40'> <br>
            </div>
          </div>
          <div class='row'>
            <label for="file2" class="col-xs-2 col-lg-2">업로드 파일2 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file2MF' id='file2MF' size='20'> <br>
            </div>
         </div>
          <div class='row'>
            <label for="file3" class="col-xs-2 col-lg-2">업로드 파일3 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file3MF'
                  id='file3MF' size='20'> <br>
            </div>
         
          </div>
          <div class='row'>
            <label for="file4" class="col-xs-2 col-lg-2">업로드 파일4 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file4MF'
                  id='file4MF' size='20'> <br>
            </div>
          </div>
          <div class='row'>
            <label for="file5" class="col-xs-2 col-lg-2">업로드 파일5 </label> 
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file5MF'
                  id='file5MF' size='20'> <br>
            </div>
          </div>
         
      <h3>추가항목</h3>   
      <div class='inpo'>상품 정보</div>
      <div class='line_box' id='ul_box_1'></div>
      <div class='row'>
           <label for='deal_state' class='col-xs-2 col-lg-2 need'>거래상태</label> 
             <input type="radio" name='deal_state' value="거래중" 
             ${bookVO.deal_state == "거래중"? "checked='checked'":""}><span class='radio_text'>거래중</span>
             <input type="radio" name='deal_state' value="거래완료" 
             ${bookVO.deal_state == "거래완료"? "checked='checked'":""}><span class='radio_text'>거래완료</span>
      </div>
         <div class='row'>
           <label for='product_code' class='col-xs-2 col-lg-2 need'>상품구분</label> 
           <input type="radio" name='product_code' value="중고품" 
           ${bookVO.product_code == "중고품"? "checked='checked'":""}><span class='radio_text'>중고품</span>
           <input type="radio" name='product_code' value="신상품" 
           ${bookVO.product_code == "신상품"? "checked='checked'":""}><span class='radio_text'>신상품</span> 
            
         </div>
         <div class='row'>
           <label for='btitle'  class='col-xs-2 col-lg-2 need'>책제목</label> 
           <input type='text' name='btitle' id='btitle' value='${bookVO.btitle}'  class='col-xs-3 col-lg-3'>
         </div>
         <div class='row'>
           <label for='bwriter'  class='col-xs-2 col-lg-2 choice'>저자</label> 
           <input type='text' name='bwriter' id='bwriter' value='${bookVO.bwriter }' class='col-xs-3 col-lg-3'>
         </div>
         <div class='row'>
           <label for='publisher'  class='col-xs-2 col-lg-2 choice'>출판사</label> 
           <input type='text' name='publisher' id='publisher' value='${bookVO.publisher}' class='col-xs-3 col-lg-3'>
         
         </div>
         <div class='row'>
           <label for='hprice'  class='col-xs-2 col-lg-2 need'>희망가격</label> 
           <input type='number' name='hprice' id='hprice' required="required"
             value='${bookVO.hprice }' class="col-xs-3 col-lg-3">원
         </div>
         <div class='row'>
           <label for='purc_date' class='col-xs-2 col-lg-2 choice'>구입시기</label> 
           <input type='text' name='purc_date' id='purc_date'
             value='${bookVO.purc_date }' class="col-xs-3 col-lg-3">
         </div>
         <div class='row'>
           <label for='quantity'  class='col-xs-2 col-lg-2 choice'>수량</label> 
           <input type='number' name='quantity' id='quantity'
                value='${bookVO.quantity }' class="col-xs-3 col-lg-3">
         </div>
            
      <hr/>
      <div class='inpo'>판매자 정보</div>
         <div class='line_box' id='ul_box_2'></div>
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='userid'>등록자 ID</label>
          <span class="col-xs-3 col-lg-3">${bookVO.userid}</span> 
         </div>
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='email'>등록자 이메일</label> 
          <span class="col-xs-3 col-lg-3">${bookVO.email}</span> 
         </div>
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='tel'>등록자 연락처</label> 
          <span class="col-xs-3 col-lg-3">${bookVO.tel}</span> 
         </div>
         <div class='row'> 
          <label class='col-xs-2 col-lg-2 need' for='nickname'>등록자 별명</label>
          <span class="col-xs-3 col-lg-3">${bookVO.nickname}</span> 
         </div>
            <div class='text_r'>
            <button type="submit">등록</button>
            <button type="button" onclick="location.href='./list.do'">목록</button>
          </div>

      </DIV>
    </FORM>

  <!-- -------------------------------------------- -->
  <jsp:include page="/menu/bottom.jsp" flush='false' />
  </div>
</body>
<!-- -------------------------------------------- -->
</html>
