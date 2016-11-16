<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css?ver=2" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/event.js?ver=2"></script>
<script type="text/javascript">

function delete_form(mno){
  var sw = window.confirm("정말로 회원을 삭제하시겠습니까?");
  if (sw == false){
    window.alert('회원 삭제를 취소합니다.');
  }else{
    location.href = './delete.do?mno=' + mno;
  }
}

function act_form(mno){
  var url = './act_update.do?mno='+mno;
  var win = window.open(url, '권한 변경', 'width=480px, height=280px, scrollbars=no');
  
  var x = (screen.width - 530) / 2;
  var y = (screen.height - 400) / 2;
  
  win.moveTo(x, y); // 화면 이동 
}
$(document).ready(function() {
  
  if($(".left").height() < $(".right").height()){
     $(".left").height($(".right").height());
  }
  
});
</script>
</head> 
<!-- ----------------------------------------- -->
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<jsp:include page="/menu/admin_left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<div class="float_l right " style="width:80%; ">
  <DIV class='container' style="width:90%; min-height:380px;"> 
  
<div class='content_menu' style='width: 100%;'>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>목록</A>>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>회원 목록</A>｜
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}&dropout=Y' class='top_select'>탈퇴 신청</A>｜
</div>

<div style="font-size: 30px; text-align: center;">
    <c:choose>
      <c:when test="${dropout == 'Y'}">
      <h1>탈퇴 신청</h1>
      </c:when>
      <c:otherwise>
      <h1>회원 목록</h1>
      </c:otherwise>
    </c:choose>
</div>


<div class='content_menu' style='width: 100%;'>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>회원 목록</A>>
    <A href='./create.do' class='top_select'>등록</A>｜
    <A href="javascript:location.reload();" class='top_select'>새로고침</A>｜
</div>

<div>
<TABLE class='table table-hover' style='width: 100%;'>
  <caption>관리자만 접근가능합니다.</caption>
  <thead>
  <TR>
    <TH>번호</TH>
    <TH>ID</TH>
    <TH>비밀번호</TH>
    <TH>성명</TH>
    <TH>닉네임</TH>
    <TH>이메일</TH>
    <TH>전화번호</TH>
    <TH>주소</TH>
    <TH>등록일</TH>
    <TH>인증</TH>
    <TH>권한</TH>
    <TH>탈퇴신청</TH>
    <TH>기타</TH>
  </TR>
  </thead>
 
<c:forEach var="vo" items="${list}">
 
  <TR>
    <TD>${vo.mno} </TD>
    <TD><A href="./read.do?mno=${vo.mno}">${vo.userid} </A></TD>
    <TD>${vo.pwd}</TD>
    <TD><A href="./read.do?mno=${vo.mno}">${vo.name}</A></TD>
    <TD>${vo.nickname}</TD>
    <TD>${vo.email}</TD>
    <TD>${vo.tel}</TD>
    <TD>
    <c:choose>
      <c:when test="${vo.address1.length() > 15 }">
        ${vo.address1.substring(0, 15)}...
      </c:when>
      <c:otherwise>
        ${vo.address1 }
      </c:otherwise>
    </c:choose>
    </TD>
    <TD>${vo.mdate.substring(0, 10)}</TD>
    <TD>${vo.confirm}</TD>
    <TD>${vo.act}</TD>
    <TD>${vo.dropout}</TD>
    <TD>
    <%--   <A href="./passwd.do?mno=${vo.mno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A> --%>
      <A href="javascript:act_form(${vo.mno})"><IMG src='./images/update.png' title='권한 변경'></A>
      <A href="javascript:delete_form(${vo.mno})"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
    
  </TR>
  
</c:forEach>
 
</TABLE>
</div>
 
<!-- <DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV> -->

<DIV class='bottom'>${paging}</DIV>
</DIV>
</DIV>
<div class="both"></div>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 