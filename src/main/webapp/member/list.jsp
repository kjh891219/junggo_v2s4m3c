<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
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
<jsp:include page="/member/menu/admin_left.jsp" flush='false' />
<!-- ----------------------------------------- -->
<DIV class='title'>회원 목록</DIV>
<div class='content_menu' style='width: 100%;'>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>목록</A>>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}&dropout=Y' class='top_select'>탈퇴 신청</A>｜
    <A href="javascript:location.reload();" class='top_select'>새로고침</A>｜
    <A href='./create.do' class='top_select'>등록</A>｜
    <A href='./update.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>수정</A>｜
    <A href='./delete.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>
</div>
 
<TABLE class='table'>
  <caption>관리자만 접근가능합니다.</caption>
  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 15%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>ID</TH>
    <TH class='th'>비밀번호</TH>
    <TH class='th'>성명</TH>
    <TH class='th'>닉네임</TH>
    <TH class='th'>이메일</TH>
    <TH class='th'>전화번호</TH>
    <TH class='th'>주소</TH>
    <TH class='th'>등록일</TH>
    <TH class='th'>인증여부</TH>
    <TH class='th'>권한</TH>
    <TH class='th'>탈퇴신청</TH>
    <TH class='th'>기타</TH>
  </TR>
 
<c:forEach var="vo" items="${list}">
 
  <TR>
    <TD class='td'>${vo.mno} </TD>
    <TD class='td'><A href="./read.do?mno=${vo.mno}">${vo.userid} </A></TD>
    <TD class='td'>${vo.pwd}</TD>
    <TD class='td'><A href="./read.do?mno=${vo.mno}">${vo.name}</A></TD>
    <TD class='td'>${vo.nickname}</TD>
    <TD class='td'>${vo.email}</TD>
    <TD class='td'>${vo.tel}</TD>
    <TD class='td'>
    <c:choose>
      <c:when test="${vo.address1.length() > 15 }">
        ${vo.address1.substring(0, 15)}...
      </c:when>
      <c:otherwise>
        ${vo.address1 }
      </c:otherwise>
    </c:choose>
    </TD>
    <TD class='td'>${vo.mdate.substring(0, 10)}</TD>
    <TD class='td'>${vo.confirm}</TD>
    <TD class='td'>${vo.act}</TD>
    <TD class='td'>${vo.dropout}</TD>
    <TD class='td'>
      <A href="./passwd.do?mno=${vo.mno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A>
      <A href="javascript:act_form(${vo.mno})"><IMG src='./images/update.png' title='수정'></A>
      <A href="javascript:delete_form(${vo.mno})"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
    
  </TR>
  
</c:forEach>
 
</TABLE>
 
<DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>

<DIV class='bottom'>${paging}</DIV>

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 