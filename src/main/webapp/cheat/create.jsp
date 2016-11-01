<%@ page contentType="text/html; charset=UTF-8" %>

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
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/JavaScript">
  window.onload=function(){
    CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
  };
</script>
<script type="text/javascript">
$(function(){
  /* 수정일 경우 자료 조회 */
  if ('${opentype }' == 'U'){
  
  }
});
</script>

</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

<DIV class='title'>허위상품신고 ${opentype == "U" ? "수정" : "등록" }</DIV>

<DIV class='content' style='width: 80%;'>
<FORM name='frm' method='POST' action='./create.do'
 enctype="multipart/form-data">
      <fieldset>
        <DIV class='title'><span>글등록</span></DIV>
         <label>CTNO</label>         
        <ul>
          <li><label class='label_1' for='title'>제목</label> <input
            type='text' name='title' id='title'
            value='${opentype == "U" ? cheatVO.title : "허위상품 신고합니다" }'
            required="required"></li>
          <li><label class='label_1' for='gubun'>구분</label> <select
            id="gubun" name="gubun">

              <option value="물품미발송"
                ${opentype == "U" && cheatVO.gubun =="물품미발송" ? "selected=selected":""}>물품미발송</option>
              <option value="상태불량"
                ${opentype == "U" && cheatVO.gubun =="상태불량" ? "selected=selected":""}>상태불량</option>
              <option value="이미테이션"
                ${opentype == "U" && cheatVO.gubun =="이미테이션" ? "selected=selected":""}>이미테이션</option>
              <option value="택배착불"
                ${opentype == "U" && cheatVO.gubun =="택배착불" ? "selected=selected":""}>택배착불</option>
              <option value="더치트,사이버안전국 등록자"
                ${opentype == "U" && cheatVO.gubun =="더치트,사이버안전국 등록자" ? "selected=selected":""}>더치트,사이버안전국
                등록자</option>
          </select>

            <div class="col-xs-5">
              <label for='region'>*지역</label> <select name='region'
                id='region' class="">
                <option value="서울"
                  ${opentype == "U" && cheatVO.region =="서울" ? "selected=selected":""}>서울</option>
                <option value="인천"
                  ${opentype == "U" && cheatVO.region =="인천" ? "selected=selected":""}>인천</option>
                <option value="대구"
                  ${opentype == "U" && cheatVO.region =="대구" ? "selected=selected":""}>대구</option>
                <option value="대전"
                  ${opentype == "U" && cheatVO.region =="대전" ? "selected=selected":""}>대전</option>
                <option value="광주"
                  ${opentype == "U" && cheatVO.region =="광주" ? "selected=selected":""}>광주</option>
                <option value="울산"
                  ${opentype == "U" && cheatVO.region =="울산" ? "selected=selected":""}>울산</option>
                <option value="부산"
                  ${opentype == "U" && cheatVO.region =="부산" ? "selected=selected":""}>부산</option>
                <option value="경기"
                  ${opentype == "U" && cheatVO.region =="경기" ? "selected=selected":""}>경기</option>
                <option value="강원"
                  ${opentype == "U" && cheatVO.region =="강원" ? "selected=selected":""}>강원</option>
                <option value="경북"
                  ${opentype == "U" && cheatVO.region =="경북" ? "selected=selected":""}>경북</option>
                <option value="경남"
                  ${opentype == "U" && cheatVO.region =="경남" ? "selected=selected":""}>경남</option>
                <option value="전북"
                  ${opentype == "U" && cheatVO.region =="전북" ? "selected=selected":""}>전북</option>
                <option value="전남"
                  ${opentype == "U" && cheatVO.region =="전남" ? "selected=selected":""}>전남</option>
                <option value="충남"
                  ${opentype == "U" && cheatVO.region =="충남" ? "selected=selected":""}>충남</option>
                <option value="충북"
                  ${opentype == "U" && cheatVO.region =="충북" ? "selected=selected":""}>충북</option>
                <option value="제주"
                  ${opentype == "U" && cheatVO.region =="제주" ? "selected=selected":""}>제주</option>
              </select>
            </div></li>
          <li><label class='label_1' for=occurday>발생일자</label> <input
            type='date' name='occurday' id='occurday'
            value='${opentype == "U" ? cheatVO.occurday : "2016-10-20" }'
            required="required"> <label class='label_1'
            for='buyprice'>구매금액</label> <input type='number'
            name='buyprice' id='buyprice'
            value=${opentype == "U" ? cheatVO.buyprice : 30000 }
            required="required"></li>
          <li><label class='label_1' for='cheatid'>허위상품등록자
              ID</label> <input type='text' name='cheatid' id='cheatid'
            value='${opentype == "U" ? cheatVO.cheatid : "badman1" }'>

            <label class='label_1' for='cheattel'>허위상품등록자 전화번호</label> <input
            type='text' name='cheattel' id='cheattel'
            value='${opentype == "U" ? cheatVO.cheattel : "02-1234-4567" }'>
          </li>
          <li><label class='label_1' for='cheatemail'>허위상품등록자
              이메일</label> <input type='text' name='cheatemail' id='cheatemail'
            value='${opentype == "U" ? cheatVO.cheatemail : "badman@gmail.com" }'>

            <label class='label_1' for='hit'>조회수</label> <input
            type='text' name='cnt' id='cnt'
            value='${opentype == "U" ? cheatVO.cnt : 0 }'></li>
          <li><label class='label_1' for='content'>사건 내용</label>
            <div>
              <textarea name='content' id='content' required="required"> ${opentype == "U" ? cheatVO.content : "꼭 보세요. 주의하세요." }</textarea>
            </div></li>
          <li><label class='label_1' for='userid'>등록자 ID</label> <input
            type='text' name='userid' id='userid'
            value='${opentype == "U" ? cheatVO.userid : "chanmi" }'
            required="required"></li>
          <li><label class='label_1' for='email'>등록자 이메일</label> <input
            type='text' name='email' id='email'
            value='${opentype == "U" ? cheatVO.email : "chanmi@gmail.com" }'
            required="required"> <label class='label_1'
            for='tel'>등록자 연락처</label> <input type='text' name='tel'
            id='tel'
            value='${opentype == "U" ? cheatVO.tel : "02-1234-1234" }'
            required="required"></li>
          <li><label class='label_1' for='nickname'>등록자 별명</label>
            <input type='text' name='nickname' id='nickname'
            value='${opentype == "U" ? cheatVO.nickname : "테스터등록자" }'
            required="required"></li>
          <li><label class='label_1' for='passwd'>비밀번호</label> <input
            type='password' name='passwd' id='passwd'
            value='${opentype == "U" ? cheatVO.passwd : "1234" }'
            required="required"></li>
          <li><label for="file1MF"
            class="col-xs-2 col-lg-2 control-label">업로드 파일1 </label>
            ${opentype == "U"? cheatVO.file1:""}
            <div class="col-xs-10 col-lg-10">
              <input type="file" class="form-control" name='file1MF'
                id='file1MF' size='20'> <br> Preview(미리보기)
              이미지 자동 생성됩니다.
            </div></li>
            <li><label for="file2MF"
            class="col-xs-2 col-lg-2 control-label">업로드 파일2 </label>
            ${opentype == "U"? cheatVO.file2:""}
            <div class="col-xs-10 col-lg-10">
              <input type="file" class="form-control" name='file2MF'
                id='file2MF' size='20'> <br> Preview(미리보기)
              이미지 자동 생성됩니다.
            </div></li>
            <li><label for="file3MF"
            class="col-xs-2 col-lg-2 control-label">업로드 파일3 </label>
            ${opentype == "U"? cheatVO.file3:""}
            <div class="col-xs-10 col-lg-10">
              <input type="file" class="form-control" name='file3MF'
                id='file3MF' size='20'> <br> Preview(미리보기)
              이미지 자동 생성됩니다.
            </div></li>
            <li><label for="file4MF"
            class="col-xs-2 col-lg-2 control-label">업로드 파일4 </label>
            ${opentype == "U"? cheatVO.file4:""}
            <div class="col-xs-10 col-lg-10">
              <input type="file" class="form-control" name='file4MF'
                id='file4MF' size='20'> <br> Preview(미리보기)
              이미지 자동 생성됩니다.
            </div></li>
            <li><label for="file5MF"
            class="col-xs-2 col-lg-2 control-label">업로드 파일5 </label>
            ${opentype == "U"? cheatVO.file5:""}
            <div class="col-xs-10 col-lg-10">
              <input type="file" class="form-control" name='file5MF'
                id='file5MF' size='20'> <br> Preview(미리보기)
              이미지 자동 생성됩니다.
            </div></li>
         
        </ul>
      <div class='text_r' >
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.jsp'" class="btn btn-danger btn-lg">취소</button>
      </div>
      </fieldset>
</FORM>
</DIV>

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
