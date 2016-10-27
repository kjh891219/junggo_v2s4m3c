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
function msg_no(){
  var flag = $("#flag").val();
  var msg_no_arr = [];
  $("input[name='msg_no']:checked").each(function(i) {
    msg_no_arr.push($(this).val());
  });
  if(msg_no_arr.length == 0) {
    msg_no_arr.push(0);
  }
  location.href="./visible.do?msg_no_arr="+ msg_no_arr +"&flag="+flag;
}

</script>
<style>

body {
    width:100%;
    margin: 0px auto;
}
table{
}

button {
background-color:transparent; 
}
</style>
</head> 
<!-- ----------------------------------------- -->

<!-- ----------------------------------------- -->
<body>
<div>


<!-- <form method="GET" action="./visible_recv.do" onsubmit="return msg_no()"> -->

<table width="600" height="50">
<tr>
    <td align="center" valign="middle" bgcolor="#EBEBEB">
        <table width="590" height="40" border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td width="25" align="center" bgcolor="#FFFFFF" ><img src="../skin/member/junggo/img/icon_01.gif" width="5" height="5"></td>
            <td width="165" align="left" bgcolor="#FFFFFF" ><font color="#666666"><b>내 쪽지함</b></font></td>
            <td width="400" bgcolor="#FFFFFF" ></td>
        </tr>
        </table></td>
</tr>
</table>
<br />

<form name="frmSearch" method="get" action="./list.do" style="margin-left: 30px;"> 
<input type='hidden' value='${flag }' name='flag' id='flag'>
    <div class='content_menu' style='width: 100%;'>
      <select name="col"> 
        <option value="">선택</option> 
        <option value="id" ${searchDTO.col == "id" ? "selected=selected" : "" }>아이디</option> 
        <option value="title" ${searchDTO.col == "title" ? "selected=selected" : "" }>제목</option> 
        <option value="content" ${searchDTO.col == "content" ? "selected=selected" : "" }>내용</option> 
        <option value="title_content" ${searchDTO.col == "title_content" ? "selected=selected" : "" }>제목+내용</option> 
        <option value="total" ${searchDTO.col == "" ? "selected=selected" : "" }>전체 목록</option>
      </select>
      <c:choose>
        <c:when test="${searchDTO.col != 'total' }"> <!-- 검색 상태 -->
          <input type="text" name="word" size="15" value="${searchDTO.word }">
        </c:when>
        <c:when test="${searchDTO.col == 'total' }"> <!-- 전체 레코드 -->
          <input type="text" name="word" size="15" value="">
        </c:when>
      </c:choose>
     
      <input type="submit" value="검색">
    </div>
</form>
      




<table width="600">
<tr> 
    <td width="600" height="15" colspan="14"></td>
</tr>
<tr> 
    <td width="30" height="24"></td>
    <td width="99" align="center" valign="middle"><a href="./list.do?flag=recv">받은 쪽지</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="99" align="center" valign="middle"><a href="./list.do?flag=send">보낸 쪽지</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="99" align="center" valign="middle"><a href="./create.do">쪽지 보내기</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="100" valign="middle" bgcolor="#EFEFEF">&nbsp;</td>
    <td width="148" align="left" valign="middle">전체 메시지 [ <B>${totalRecord }</B> ]통</td>
    <td width="30" height="24"></td>
</tr>
</table>

<table width="600">
<tr> 
    <td height="200" align="center" valign="top">
        <table width="540">
        <tr> 
            <td height="2" bgcolor="#808080"></td>
        </tr>
        <tr> 
            <td width="540" bgcolor="#FFFFFF">
                <table width=100%;>
                <tr bgcolor=#E1E1E1> 
                    <c:choose>
                      <c:when test="${flag == 'recv'}">
                        <td width="20%" height="24"><b>보낸 사람</b></td>
                      </c:when>
                      <c:when test="${flag == 'send'}">
                        <td width="20%" height="24"><b>받은 사람</b></td>
                      </c:when>
                    </c:choose>
                    <td width=20%><b>제목</b></td>
                    <td width=35%><b>내용</b></td>
                    <td width=20%><b>전송 시간</b></td>
                    <td width=5%><button onclick="msg_no()"><img src='./images/trashcan.png' /></button></td>
                </tr>

                <c:if test="">
                <tr><td height=100 align=center colspan=4>자료가 없습니다.</td></tr> 
                </c:if>
                
                <c:forEach var="vo" items="${list }">
                  <tr>
                   <c:choose>
                      <c:when test="${flag == 'recv'}">
                        <td>${vo.sendid}</td>
                      </c:when>
                      <c:when test="${flag == 'send'}">
                        <td>${vo.receiveid}</td>
                      </c:when>
                    </c:choose>
                  <td>
                    <a href="./read_msg.do?msg_no=${vo.msg_no}&flag=${flag}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}">${vo.title}</a> 
                  </td> 
                  <td>${vo.content}</td>
                  <td>${vo.msg_date}</td>
                  <td><input type="checkbox" id="msg_no" name="msg_no" value="${vo.msg_no }"></td>
                </tr>
               </c:forEach>
                </table></td>
        </tr>
        </table>
     </td>
</tr>
<tr> 
    <td height="2" align="center" valign="top" bgcolor="#D5D5D5"></td>
</tr>
<tr>
    <td height="2" align="center" valign="top" bgcolor="#E6E6E6"></td>
</tr>
<tr>
    <td height="40" align="center" valign="bottom">
    <a href="javascript:window.close();">닫기</a><br><br></td>
</tr>
</table>
<DIV class='bottom'>${paging}</DIV>
<!-- </form> -->
 
</div>
</body>
<!-- -------------------------------------------- -->
<!-- -------------------------------------------- -->
</html> 