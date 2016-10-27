<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  window.onload = function() {
    CKEDITOR.replace('content'); // <TEXTAREA>태그 id 값
  };
</script>
<script type="text/javascript">
  $(function() {
    /* 수정일 경우 자료 조회 */
    if ('${opentype }' == 'U') {

    }
  });
</script>

</head>

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
  <jsp:include page="/menu/top.jsp" flush='false' />
  <!-- ----------------------------------------- -->
  <div class='container'>
  <DIV class='title'>컴퓨터 ${opentype == "U" ? "수정" : "등록" }</DIV>
  <div><span class='need_e'>필수항목</span><span class='choice_e'>선택항목</span></div>
  <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
  <DIV class='content_form'>
      <div class='content_menu'>
        <A href='./create.do?ctno=0&opentype=R'>등록</A>｜ 
        <A href='./read.do?ctno=${computerVO.ctno }'>상세보기</A>｜ 
        <A href='./create.do?ctno=${computerVO.ctno }&opentype=U'>수정</A>｜ 
        <A href='./list.do'>목록</A>｜ 
        <A href='./delete.do?ctno=${computerVO.ctno }'>삭제</A>｜
        <A href="javascript:location.reload();">새로고침</A>
      </div>
        <input type='hidden' id='opentype' name='opentype' value='${opentype }'> 
        <input type='hidden' name='ctno' id='ctno' value='${opentype == "U" ? computerVO.ctno : "0" }'>
         <div class='float_l _left'>
          <label class='select need_e' for='category'>분류</label> 
            <div class=''>
            <select id="category" name="category" class="full">
              <option value="노트북/넷북"
                ${opentype == "U" && computerVO.category =="노트북/넷북" ? "selected=selected":""}>노트북/넷북</option>
              <option value="데스크탑/본체"
                ${opentype == "U" && computerVO.category =="데스크탑/본체" ? "selected=selected":""}>데스크탑/본체</option>
              <option value="모니터"
                ${opentype == "U" && computerVO.category =="모니터" ? "selected=selected":""}>모니터</option>
              <option value="CPU/메인보드/HDD/메모리"
                ${opentype == "U" && computerVO.category =="CPU/메인보드/HDD/메모리" ? "selected=selected":""}>CPU/메인보드/HDD/메모리</option>
              <option value="주변기기"
                ${opentype == "U" && computerVO.category =="주변기기" ? "selected=selected":""}>주변기기</option>
          </select>
          </div>
            <div class="">
              <label for='deal_code' class='control-label need_e'>거래구분</label> 
               <select name='deal_code' id="deal_code" class="control-label full">
                <option value="팝니다"
                  ${opentype == "U" && computerVO.deal_code =="팝니다" ? "selected=selected":""}>팝니다</option>
                <option value="삽니다"
                  ${opentype == "U" && computerVO.deal_code =="삽니다" ? "selected=selected":""}>삽니다</option>
               </select>
            </div>
         </div>   
         <div  class='float_l _right' >
            <div>
              <label for='region' class='need_e'>지역</label> 
               <div>
              <select name='region' id='region' class="form-control-lg-10-lg-10-lg-10 full" >
                <option value="서울"
                  ${opentype == "U" && computerVO.region =="서울" ? "selected=selected":""}>서울</option>
                <option value="인천"
                  ${opentype == "U" && computerVO.region =="인천" ? "selected=selected":""}>인천</option>
                <option value="대구"
                  ${opentype == "U" && computerVO.region =="대구" ? "selected=selected":""}>대구</option>
                <option value="대전"
                  ${opentype == "U" && computerVO.region =="대전" ? "selected=selected":""}>대전</option>
                <option value="광주"
                  ${opentype == "U" && computerVO.region =="광주" ? "selected=selected":""}>광주</option>
                <option value="울산"
                  ${opentype == "U" && computerVO.region =="울산" ? "selected=selected":""}>울산</option>
                <option value="부산"
                  ${opentype == "U" && computerVO.region =="부산" ? "selected=selected":""}>부산</option>
                <option value="경기"
                  ${opentype == "U" && computerVO.region =="경기" ? "selected=selected":""}>경기</option>
                <option value="강원"
                  ${opentype == "U" && computerVO.region =="강원" ? "selected=selected":""}>강원</option>
                <option value="경북"
                  ${opentype == "U" && computerVO.region =="경북" ? "selected=selected":""}>경북</option>
                <option value="경남"
                  ${opentype == "U" && computerVO.region =="경남" ? "selected=selected":""}>경남</option>
                <option value="전북"
                  ${opentype == "U" && computerVO.region =="전북" ? "selected=selected":""}>전북</option>
                <option value="전남"
                  ${opentype == "U" && computerVO.region =="전남" ? "selected=selected":""}>전남</option>
                <option value="충남"
                  ${opentype == "U" && computerVO.region =="충남" ? "selected=selected":""}>충남</option>
                <option value="충북"
                  ${opentype == "U" && computerVO.region =="충북" ? "selected=selected":""}>충북</option>
                <option value="제주"
                  ${opentype == "U" && computerVO.region =="제주" ? "selected=selected":""}>제주</option>
              </select>
            </div>
               <div>
             
                  <label for='deal_way' class='need_e'>거래방식</label> 
                  <div>
                    <select name='deal_way' id='deal_way' class="form-control-lg-10-lg-10-lg-10 full">
                      <option value="직거래"
                        ${opentype == "U" && computerVO.deal_way =="직거래" ? "selected=selected":""}>직거래</option>
                      <option value="택배"
                        ${opentype == "U" && computerVO.deal_way =="택배" ? "selected=selected":""}>택배</option>
      
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
               value='${opentype == "U" ? computerVO.title : "컴퓨터 팔아요" }' class="col-xs-9 col-lg-9" required="required">
         </div>    
         <div class='row'>
           <label for='content' class='col-xs-2 col-lg-2 choice'>상세설명</label>
           <textarea rows='10' name='content' id='content' required="required" class="col-xs-9 col-lg-9"> ${opentype == "U" ? computerVO.content : "꼭 보세요. 주의하세요." }</textarea>
         </div>
         <hr/>
               
         <span style="margin-left:18%;">      
         Preview(미리보기) 이미지 자동 생성됩니다.
         </span>
         <div class='row'>
          <label for="file1MF" class="col-xs-2 col-lg-2">업로드 파일1 </label>
            ${opentype == "U"? cheatVO.file1:""}
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file1MF' id='file1MF' size='40'> <br>
            </div>
          </div>
          <div class='row'>
            <label for="file2MF" class="col-xs-2 col-lg-2">업로드 파일2 </label> 
            ${opentype == "U"? cheatVO.file2:""}
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file2MF' id='file2MF' size='20'> <br>
            </div>
         </div>
          <div class='row'>
            <label for="file3MF" class="col-xs-2 col-lg-2">업로드 파일3 </label> 
            ${opentype == "U"? cheatVO.file3:""}
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file3MF'
                  id='file3MF' size='20'> <br>
            </div>
         
          </div>
          <div class='row'>
            <label for="file4MF" class="col-xs-2 col-lg-2">업로드 파일4 </label> 
            ${opentype == "U"? cheatVO.file4:""}
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file4MF'
                  id='file4MF' size='20'> <br>
            </div>
          </div>
          <div class='row'>
            <label for="file5MF" class="col-xs-2 col-lg-2">업로드 파일5 </label> 
            ${opentype == "U"? cheatVO.file5:""}
            <div class="col-xs-10 col-lg-10">
               <input type="file" class="form-control" name='file5MF'
                  id='file5MF' size='20'> <br>
            </div>
          </div>
         
      <h3>추가항목</h3>   
      <div class='inpo'>상품 정보</div>
      <div class='line_box' id='ul_box_1'></div>
         <div class='row'>
           <c:if test="${opentype == 'U'}">
                <label for='deal_status' class='col-xs-2 col-lg-2 need'>판매상태</label>
                <input type="radio" name='product_code' ${opentype == "U" && computerVO.deal_status =="거래중" ? "checked=checked":""}><span class='radio_text'>거래중</span>
                <input type="radio" name='product_code' ${opentype == "U" && computerVO.deal_status =="거래완료" ? "checked=checked":""}><span class='radio_text'>거래완료</span> 
          </c:if>
         </div>
         <div class='row'>
           <label for='product_code' class='col-xs-2 col-lg-2 need'>상품구분</label> 
           <input type="radio" name='product_code' ${opentype == "U" && computerVO.product_code =="중고품" ? "checked=checked":""}><span class='radio_text'>중고품</span>
           <input type="radio" name='product_code' ${opentype == "U" && computerVO.product_code =="신상품" ? "checked=checked":""}><span class='radio_text'>신상품</span> 
         </div>
         <div class='row'>
           <label for='hprice'  class='col-xs-2 col-lg-2 need'>희망가격</label> 
           <input type='number' name='hprice' id='hprice' required="required"
             value=${opentype == "U" ? computerVO.hprice : 10000 } class="col-xs-3 col-lg-3">원
         </div>
         <div class='row'>
           <label for='purc_date' class='col-xs-2 col-lg-2 choice'>구입시기</label> 
           <input type='text' name='purc_date' id='purc_date'
             value='${opentype == "U" ? computerVO.purc_date : "2014년 10월" }' class="col-xs-3 col-lg-3">
         </div>
         <div class='row'>
           <label for='quantity'  class='col-xs-2 col-lg-2 choice'>수량</label> 
           <input type='number' name='quantity' id='quantity'
                value=${opentype == "U" ? computerVO.quantity : 2 } class="col-xs-3 col-lg-3">
         </div>
            
      <hr/>
      <div class='inpo'>판매자 정보</div>
         <div class='line_box' id='ul_box_2'></div>
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='userid'>등록자 ID</label> 
          <input type='text' name='userid' id='userid' value='${opentype == "U" ? computerVO.userid : "chanmi" }'
            class="col-xs-3 col-lg-3" required="required">
         </div>
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='email'>등록자 이메일</label> 
          <input type='text' name='email' id='email' value='${opentype == "U" ? computerVO.email : "chanmi@gmail.com" }'
            class="col-xs-3 col-lg-3" required="required"> 
         </div>
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='tel'>등록자 연락처</label> 
          <input type='text' name='tel' id='tel' value='${opentype == "U" ? computerVO.tel : "02-1234-1234" }'
            class="col-xs-3 col-lg-3" required="required">
         </div>
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='nickname'>등록자 별명</label>
          <input type='text' name='nickname' id='nickname' value='${opentype == "U" ? computerVO.nickname : "테스터등록자" }'
            class="col-xs-3 col-lg-3" required="required">
         </div>
         
         <div class='row'>
          <label class='col-xs-2 col-lg-2 need' for='passwd'>비밀번호</label> 
          <input type='password' name='passwd' id='passwd' value='${opentype == "U" ? computerVO.passwd : "1234" }'
            class="col-xs-3 col-lg-3" required="required">
         </div>
            <div class='text_r'>
            <button type="submit">${opentype == "U"?"저장":"등록"}</button>
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
