<%@ page contentType="text/html; charset=UTF-8"%>

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

  <DIV class='title'>음악/음향/악기 ${opentype == "U" ? "수정" : "등록" }</DIV>

  <DIV class='content' style='width: 80%;'>
    <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
      <div class='content_menu' style='width: 100%;'>
        <A href='./create.do?ctno=0&opentype=R'>등록</A>｜ 
        <A href='./read.do?ctno=${musicVO.ctno }'>상세보기</A>｜ 
        <A href='./create.do?ctno=${musicVO.ctno }&opentype=U'>수정</A>｜ 
        <A href='./list.do'>목록</A>｜ 
        <A href='./delete.do?ctno=${musicVO.ctno }'>삭제</A>｜
        <A href="javascript:location.reload();">새로고침</A>
      </div>
      <fieldset>
        <input type='text' id='opentype' name='opentype' value='${opentype }'> 
        <label>CTNO</label> 
        <input type='text' name='ctno' id='ctno' value='${opentype == "U" ? musicVO.ctno : "0" }'>
        <ul>
        <li>
            <div class="col-xs-5">
              <label for='deal_status'>*판매상태</label> <select
                name='deal_status' id='deal_status' class=" ">
                <option value="거래중"
                  ${opentype == "U" && computerVO.deal_status =="거래중" ? "selected=selected":""}>거래중</option>
                <option value="거래완료"
                  ${opentype == "U" && computerVO.deal_status =="거래완료" ? "selected=selected":""}>거래완료</option>
              </select>
            </div>
          </li>
          <li><label class='label_1' for='title'>제목</label> 
          <input type='text' name='title' id='title'
            value='${opentype == "U" ? musicVO.title : "피아노 팔아요" }' required="required"></li>
          <li><label class='label_1' for='category'>분류</label> 
          <select id="category" name="category">
              <option value="아이팟/MP3" ${opentype == "U" && musicVO.category =="아이팟/MP3" ? "selected=selected":""}>아이팟/MP3</option>
              <option value="음향기기" ${opentype == "U" && musicVO.category =="음향기기" ? "selected=selected":""}>음향기기</option>
              <option value="CD/DVD/음반" ${opentype == "U" && musicVO.category =="CD/DVD/음반" ? "selected=selected":""}>CD/DVD/음반</option>
              <option value="악기" ${opentype == "U" && musicVO.category =="악기" ? "selected=selected":""}>악기</option>
              <option value="주변기기" ${opentype == "U" && musicVO.category =="주변기기" ? "selected=selected":""}>주변기기</option>
          </select>
            <div class="col-xs-5">
              <label for='deal_code'>*거래구분</label> 
              <select name='deal_code' id="deal_code" class=" ">
                <option value="팝니다"  ${opentype == "U" && musicVO.deal_code =="팝니다" ? "selected=selected":""}>팝니다</option>
                <option value="삽니다"  ${opentype == "U" && musicVO.deal_code =="삽니다" ? "selected=selected":""}>삽니다</option>
              </select>
            </div>
            <div class="col-xs-5">
              <label for='product_code'>*상품구분</label> 
              <select name='product_code' id='product_code' class=" ">
                <option value="중고품"  ${opentype == "U" && musicVO.product_code =="중고품" ? "selected=selected":""}>중고품</option>
                <option value="신상품"  ${opentype == "U" && musicVO.product_code =="신상품" ? "selected=selected":""}>신상품</option>
              </select>
            </div></li>
          <li>
            <div class="col-xs-5">
              <label for='deal_way'>*거래방식</label> 
              <select name='deal_way' id='deal_way' class=" ">
                <option value="직거래"  ${opentype == "U" && musicVO.deal_way =="직거래" ? "selected=selected":""}>직거래</option>
                <option value="택배"  ${opentype == "U" && musicVO.deal_way =="택배" ? "selected=selected":""}>택배</option>
                 
              </select>
            </div>
            <div class="col-xs-5">
              <label for='region'>*지역</label> <select name='region' id='region' class="">
                <option value="서울" ${opentype == "U" && musicVO.region =="서울" ? "selected=selected":""}>서울</option>
                <option value="인천" ${opentype == "U" && musicVO.region =="인천" ? "selected=selected":""}>인천</option>
                <option value="대구" ${opentype == "U" && musicVO.region =="대구" ? "selected=selected":""}>대구</option>
                <option value="대전" ${opentype == "U" && musicVO.region =="대전" ? "selected=selected":""}>대전</option>
                <option value="광주" ${opentype == "U" && musicVO.region =="광주" ? "selected=selected":""}>광주</option>
                <option value="울산" ${opentype == "U" && musicVO.region =="울산" ? "selected=selected":""}>울산</option>
                <option value="부산" ${opentype == "U" && musicVO.region =="부산" ? "selected=selected":""}>부산</option>
                <option value="경기" ${opentype == "U" && musicVO.region =="경기" ? "selected=selected":""}>경기</option>
                <option value="강원" ${opentype == "U" && musicVO.region =="강원" ? "selected=selected":""}>강원</option>
                <option value="경북" ${opentype == "U" && musicVO.region =="경북" ? "selected=selected":""}>경북</option>
                <option value="경남" ${opentype == "U" && musicVO.region =="경남" ? "selected=selected":""}>경남</option>
                <option value="전북" ${opentype == "U" && musicVO.region =="전북" ? "selected=selected":""}>전북</option>
                <option value="전남" ${opentype == "U" && musicVO.region =="전남" ? "selected=selected":""}>전남</option>
                <option value="충남" ${opentype == "U" && musicVO.region =="충남" ? "selected=selected":""}>충남</option>
                <option value="충북" ${opentype == "U" && musicVO.region =="충북" ? "selected=selected":""}>충북</option>
                <option value="제주" ${opentype == "U" && musicVO.region =="제주" ? "selected=selected":""}>제주</option>
              </select>
            </div>
          </li>
          <li>
            <div class="col-xs-5">
              <label for='hprice'>*희망가격</label> 
              <input type='number' name='hprice' id='hprice' required="required"
                value=${opentype == "U" ? musicVO.hprice : 10000 } class=" ">원
            </div>
            <div class="col-xs-5">
              <label for='purc_date'>구입시기</label> 
              <input type='text' name='purc_date' id='purc_date'
                value='${opentype == "U" ? musicVO.purc_date : "2014년 10월" }' class="">
            </div>
          </li>
          <li>
            <div>
              <textarea name='content' id='content' required="required"> ${opentype == "U" ? musicVO.content : "꼭 보세요. 주의하세요." }</textarea>
            </div>
          </li>
          <li><div class="col-xs-5">
              <label for='quantity'>수량</label> 
              <input type='number' name='quantity' id='quantity' value=${opentype == "U" ? musicVO.quantity : 2 } class=" ">
            </div></li>
          <li><label class='label_1' for='userid'>등록자 ID</label> 
          <input type='text' name='userid' id='userid' value='${opentype == "U" ? musicVO.userid : "chanmi" }' required="required"></li>
          <li><label class='label_1' for='email'>등록자 이메일</label> 
          <input type='text' name='email' id='email' value='${opentype == "U" ? musicVO.email : "chanmi@gmail.com" }' required="required"> 
            <label class='label_1' for='tel'>등록자 연락처</label> 
            <input type='text' name='tel' id='tel' value='${opentype == "U" ? musicVO.tel : "02-1234-1234" }' required="required"></li>
          <li><label class='label_1' for='nickname'>등록자 별명</label> 
          <input type='text' name='nickname' id='nickname'
            value='${opentype == "U" ? musicVO.nickname : "테스터등록자" }' required="required"></li>
          <li><label class='label_1' for='passwd'>비밀번호</label> 
          <input type='password' name='passwd' id='passwd'
            value='${opentype == "U" ? musicVO.passwd : "1234" }' required="required"></li>
          <li><label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일 </label> 
          ${opentype == "U"? musicVO.file2:""}
            <div class="col-xs-10 col-lg-10">
              <input type="file" class="form-control" name='file2MF' id='file2MF' size='20'> 
              <br> Preview(미리보기) 이미지 자동 생성됩니다.
            </div></li>
          <li class='right'>
            <button type="submit">${opentype == "U"?"저장":"등록"}</button>
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
