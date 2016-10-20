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
$(function(){
 
});
</script>
<style>

body {
    width:100%;
    margin: 0px auto;
}
table{
}
</style>
</head> 
<!-- ----------------------------------------- -->

<!-- ----------------------------------------- -->
<body>
<div>
<input type='hidden' value='${flag }' name='flag'>
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

<table width="600">
<tr> 
    <td width="600" height="20" colspan="14"></td>
</tr>
<tr> 
    <td width="30" height="24"></td>
       <td width="99" align="center" valign="middle"><a href="./list_msg.do?flag=recv">받은 쪽지</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="99" align="center" valign="middle"><a href="./list_msg.do?flag=send">보낸 쪽지</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="99" align="center" valign="middle"><a href="./memo_form.php">쪽지 보내기</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="100" valign="middle" bgcolor="#EFEFEF">&nbsp;</td>
    <td width="148" align="left" valign="middle">전체 받은 쪽지 [ <B>${cnt }</B> ]통</td>
    <td width="3" bgcolor="#EFEFEF"></td>
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
                      <c:when test="${flag == 'r'}">
                        <td width="30%" height="24"><b>보낸 사람</b></td>
                      </c:when>
                      <c:when test="${flag == 's'}">
                        <td width="30%" height="24"><b>받은 사람</b></td>
                      </c:when>
                    </c:choose>
                    <td width=25%><b>제목</b></td>
                    <td width=25%><b>내용</b></td>
                    <td width=20%><b>전송 시간</b></td>
                </tr>

                <c:if test="">
                <tr><td height=100 align=center colspan=4>자료가 없습니다.</td></tr> 
                </c:if>
                
                <c:forEach var="vo" items="${list }">
                  <tr>
                  <td>${vo.sendid}</td>
                  <td>
                    <a href="./read_msg.do?msg_no=${vo.msg_no}">${vo.title}</a> 
                  </td> 
                  <td>${vo.content}</td>
                  <td>${vo.msg_date}</td>
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
    <td height="40" align="center" valign="bottom"><a href="javascript:window.close();">닫기</a><br><br></td>
</tr>
</table>
 
</div>
</body>
<!-- -------------------------------------------- -->
<!-- -------------------------------------------- -->
</html> 