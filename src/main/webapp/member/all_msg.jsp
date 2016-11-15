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
  var sw = window.confirm("정말로 관리자 회원을 삭제할까요?");
  if (sw == false){
    window.alert('회원 삭제를 취소합니다.');
  }else{
    location.href = './delete.do?mno=' + mno;
  }
}

function act_form(mno){
  var url = './act_update.do?mno='+mno;
  var win = window.open(url, '권한 변경', 'width=530px, height=400px, scrollbars=no');
  
  var x = (screen.width - 530) / 2;
  var y = (screen.height - 400) / 2;
  
  win.moveTo(x, y); // 화면 이동 
}
</script>
</head> 
<!-- ----------------------------------------- -->
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<jsp:include page="/member/menu/admin_left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<DIV class='container'>
<div class='content_menu' style='width: 100%;'>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>목록</A>>
    <A href="javascript:location.reload();" class='top_select'>새로고침</A>｜
</div>

<DIV style="font-size: 30px; text-align: center;"><h1>전체 메시지 목록</h1></DIV>

<!-- 메시지 삭제 처리 -->
<FORM name='frm' method='POST' action='./delete.do'>
<TABLE style="width:100%">
<TR>
<TD style="width:10%; font-size: 15px; text-align: center;">삭제 기간</TD> 
<TD>
<button type="submit" class="btn btn-primary" name="date" value="-1">1개월</button> 
<button type="submit" class="btn btn-primary" name="date" value="-6">6개월</button> 
<button type="submit" class="btn btn-primary" name="date" value="-12">1년</button>
<button type="submit" class="btn btn-primary" name="date" value="-999">전체</button>
</TD>
</TR>
</TABLE>
<!--  날짜별
<input type="date" class="form-control">  ~  <input type="date" class="form-control">   -->
</FORM>
<br>
<TABLE class='table'>
<%--   <caption>관리자만 접근가능합니다.</caption>--%>  
  <TR>
    <TH>번호</TH>
    <TH>보낸 사람</TH>
    <TH>받은 사람</TH>
    <TH>제목</TH>
    <TH>내용</TH>
    <TH>전송 시간</TH>
  </TR>
 
<c:forEach var="vo" items="${list}">
 
  <TR>
    <TD>${vo.msg_no} </TD>
    <TD>${vo.sendid}</TD>
    <TD>${vo.receiveid}</TD>
    <TD>${vo.title}</TD>
    <TD>${vo.content}</TD>
    <TD>${vo.msg_date}</TD>
  </TR>
  
</c:forEach>
 
</TABLE>
 

<DIV class='bottom'>${paging}</DIV>
</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 