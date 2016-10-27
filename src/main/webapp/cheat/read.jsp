<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>

<script type="text/JavaScript">
function formCreate(divnm, rno, grpno, indent, ansnum, ctno) {
  /* 답글 등록 화면 form 구성 */ 
  var div = document.getElementById(divnm);
  var str = '<FORM name="frm' + rno + '" id="frm' + rno + '" method="POST" action="./reply.do">';
  str += '<input type="hidden" name="rno" id="rno" value='+ rno + '>';
  str += '<input type="hidden" id="grpno" name="grpno" value='+ grpno + '>';
  str += '<input type="hidden" id="indent" name="indent" value='+ indent + '>';
  str += '<input type="hidden" id="ansnum" name="ansnum" value='+ ansnum + '>';
  str += '<input type="hidden" id="ctno" name="ctno" value='+ ctno + '>';
  str += '<label>아이디</label>';
  str += '<input type="text" id="userid" name="userid" value='+ 'chanmi' +'>';
  str += '<label>닉네임</label>';
  str += '<input type="text" id="nickname" name="nickname" value='+ '등록자' + '>';
  str += '<label>비밀번호</label>';
  str += '<input type="password" id="passwd" name="passwd" value="1234">';
  str += '<textarea rows="3" cols="100"  name="rcomment" id="rcomment" placeholder="내용을 입력하세요">댓글입력</textarea>';
  str += '<input type="submit" value="전송"/>&nbsp;';
  str += '<input type="button" value="닫기" onclick="create_cancel(this.form)"/>';
  str += '</FORM>';
  div.innerHTML = str;
 
 } 
 
function create_cancel(frm){
  frm.style.display='none';
}

  window.onload=function(){    
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
  
  function delete_reply(rno_del, ctno_del){
   
    document.getElementById("rno_del").value = rno_del;
    document.getElementById("ctno_del").value = ctno_del;
    var frm = document.getElementById("frm_reply_del");
    frm.submit();
  }
  
  function fnMove(){
    alert('');
    var offset = $("#div_r").offset();
    $('html','body').animate({scrollTop:position.top},100);
  }
</script>


<script type="text/javascript">
 
</script>

</head>
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
  <jsp:include page="/menu/top.jsp" flush='false' />
  <!-- ----------------------------------------- -->

  <DIV class='title'>허위상품신고</DIV>

  <DIV class='content' style='width: 80%;'>
    <div class='content_menu' style='width: 100%;'>
      <A href='./create.do?ctno=0&opentype=R'>등록</A>｜ <A href='./read.do?ctno=${cheatVO.ctno }'>상세보기</A>｜ <A
        href='./create.do?ctno=${cheatVO.ctno }&opentype=U'>수정</A>｜ <A href='./list.do'>목록</A>｜ <A
        href='./delete.do?ctno=${cheatVO.ctno }'>삭제</A>｜ <A href="javascript:location.reload();">새로고침</A>
    </div>
    <FORM name='frm' method='POST' enctype="multipart/form-data">
      <input type='hidden' id='ctno' name='ctno' value='${cheatVO.ctno }'>

      <ul>

        <li><label class='label' for='title'>제목</label> <input type='text' name='title' id='title'
          value='${cheatVO.title}' required="required"></li>
        <li><label class='label' for='gubun'>구분</label> <select id="gubun" name="gubun">
            <option value="물품미발송" ${cheatVO.gubun == "물품미발송" ? "selected=selected" : "" }>물품미발송</option>
            <option value="상태불량" ${cheatVO.gubun == "상태불량" ? "selected=selected" : "" }>상태불량</option>
            <option value="이미테이션" ${cheatVO.gubun == "이미테이션" ? "selected=selected" : "" }>이미테이션</option>
            <option value="택배착불" ${cheatVO.gubun == "택배착불" ? "selected=selected" : "" }>택배착불</option>
            <option value="더치트,사이버안전국 등록자" ${cheatVO.gubun == "더치트,사이버안전국 등록자" ? "selected=selected" : "" }>더치트,사이버안전국
              등록자</option>
        </select> <label for='region'>*지역</label> <select name='region' id='region' class="">
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
        </select></li>
        <li><label class='label' for=occurday>발생일자</label> <input type='date' name='occurday' id='occurday'
          value='${cheatVO.occurday}' required="required"> <label class='label' for='buyprice'>구매금액</label> <input
          type='number' name='buyprice' id='buyprice' value='${cheatVO.buyprice}' required="required"></li>
        <li><label class='label' for='cheatid'>허위상품등록자 ID</label> <input type='text' name='cheatid' id='cheatid'
          value='${cheatVO.cheatid}'> <label class='label' for='cheattel'>허위상품등록자 전화번호</label> <input
          type='text' name='cheattel' id='cheattel' value='${cheatVO.cheattel}'></li>
        <li><label class='label' for='cheatemail'>허위상품등록자 이메일</label> <input type='text' name='cheatemail'
          id='cheatemail' value='${cheatVO.cheatemail}'> <label class='label' for='hit'>조회수</label> <input
          type='text' name='hit' id='hit' value='${cheatVO.cnt}'></li>
        <li>
          <div>
            <textarea name='content' id='content' required="required">${cheatVO.content}</textarea>
          </div>
        </li>
        <li>
        <li><label for="file1MF" class="col-xs-2 col-lg-2 control-label">업로드 파일1:${cheatVO.file1} </label>
          ${opentype == "U"? cheatVO.file1:""}
          <div class="col-xs-10 col-lg-10">
            <input type="file" class="form-control" name='file1MF' id='file1MF' size='20'> <br>
            Preview(미리보기) 이미지 자동 생성됩니다.
          </div></li>
        <li><label for="file2MF" class="col-xs-2 col-lg-2 control-label">업로드 파일2:${cheatVO.file2} </label>
          ${opentype == "U"? cheatVO.file2:""}
          <div class="col-xs-10 col-lg-10">
            <input type="file" class="form-control" name='file2MF' id='file2MF' size='20'> <br>
            Preview(미리보기) 이미지 자동 생성됩니다.
          </div></li>
        <li><label for="file3MF" class="col-xs-2 col-lg-2 control-label">업로드 파일3:${cheatVO.file3} </label>
          ${opentype == "U"? cheatVO.file3:""}
          <div class="col-xs-10 col-lg-10">
            <input type="file" class="form-control" name='file3MF' id='file3MF' size='20'> <br>
            Preview(미리보기) 이미지 자동 생성됩니다.
          </div></li>
        <li><label for="file4MF" class="col-xs-2 col-lg-2 control-label">업로드 파일4:${cheatVO.file4} </label>
          ${opentype == "U"? cheatVO.file4:""}
          <div class="col-xs-10 col-lg-10">
            <input type="file" class="form-control" name='file4MF' id='file4MF' size='20'> <br>
            Preview(미리보기) 이미지 자동 생성됩니다.
          </div></li>
        <li><label for="file5MF" class="col-xs-2 col-lg-2 control-label">업로드 파일5:${cheatVO.file5} </label>
          ${opentype == "U"? cheatVO.file5:""}
          <div class="col-xs-10 col-lg-10">
            <input type="file" class="form-control" name='file5MF' id='file5MF' size='20'> <br>
            Preview(미리보기) 이미지 자동 생성됩니다.
          </div></li>

        <li><label class='label' for='userid'>등록자 ID</label> <input type='text' name='userid' id='userid'
          value='${cheatVO.userid}' required="required"></li>
        <li><label class='label' for='email'>등록자 이메일</label> <input type='text' name='email' id='email'
          value='${cheatVO.email}' required="required"> <label class='label' for='tel'>등록자 연락처</label> <input
          type='text' name='tel' id='tel' value='${cheatVO.tel}' required="required"></li>
        <li><label class='label' for='nickname'>등록자 별명</label> <input type='text' name='nickname' id='nickname'
          value='${cheatVO.nickname}' required="required"></li>

        <li><label class='label' for='passwd'>비밀번호</label> <input type='password' name='passwd' id='passwd'
          value='${cheatVO.passwd}' required="required"></li>

      </ul>
    </FORM>
    <br>
    <hr>
    <div id='div0'></div>

    <div class="divReplyList" id="div_r">
      <c:forEach var="cReplyVO" items="${cReplylist }">
        <div class="divReplyOne">
          <input type='hidden' id='grpno' name='grpno' value=${cReplyVO.grpno } style='width: 100px;'> <input
            type='hidden' id='indent' name='indent' value=${cReplyVO.indent } style='width: 100px;'> <input
            type='hidden' id='ansnum' name='ansnum' value=${cReplyVO.ansnum } style='width: 100px;'> <input
            type='hidden' id='rno' name='rno' value=${cReplyVO.rno } style='width: 100px;'>
          <c:choose>
            <c:when test="${cReplyVO.ansnum == 0 }">
              <img src='./images/reply3.png' style='width: 14px;'>
            </c:when>
            <c:when test="${cReplyVO.ansnum > 0 }">
              <c:forEach var="indent" begin="1" end="${cReplyVO.indent }" step="1">
               　
              </c:forEach>
              <img src='../images/reply3.jpg'>
            </c:when>
          </c:choose> 
          ${cReplyVO.rcomment } <span style='font-size: 10px;'>${cReplyVO.nickname }</span>
          <A href="javascript:formCreate('div' + '${cReplyVO.rno }' ,'${cReplyVO.rno }' ,'${cReplyVO.grpno }'
        ,'${cReplyVO.indent }' ,'${cReplyVO.ansnum }','${cReplyVO.ctno }')" >답글쓰기</a>&nbsp;
          <a href='./delete_reply.do?rno=${cReplyVO.rno }&ctno=${cReplyVO.ctno }'>삭제</A>
          <a href="javascript:delete_reply(${cReplyVO.rno }, ${cReplyVO.ctno })">삭제</A>          
          <div id='div${cReplyVO.rno }'></div>
        </div>
      </c:forEach>
    </div>
    <A href="javascript:formCreate('div0' ,'0' ,'1'
        ,'1' ,'1','${cheatVO.ctno }')">댓글등록</a> <br>
  </DIV>
  <form id='frm_reply_del' method="POST" action="./delete_reply.do">
  <input type="hidden" name="rno_del" id="rno_del"/>
  <input type="hidden" name="ctno_del" id="ctno_del"/>
  <button type="submit" onclick="fnMove()"></button>
  </form>
  <!-- -------------------------------------------- -->
  <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html>
