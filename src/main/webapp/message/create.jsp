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
/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
  *{ 
    font-family: 'Nanum Gothic', serif;
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
  }

body {
    width:100%;
    margin: 0px auto;
}
table{
    width:100%;
}
</style>
</head> 
<!-- ----------------------------------------- -->

<!-- ----------------------------------------- -->
<body>
<div>
<input type='hidden' value='${flag }' name='flag'>
<table style="height:50px">
<tr>
    <td align="center" valign="middle" bgcolor="#EBEBEB">
        <table style="width: 95%; height:40px">
        <tr> 
            <td width="25" align="center" bgcolor="#FFFFFF" ><img src="../images/message.png"></td>
            <td width="165" align="left" bgcolor="#FFFFFF" ><font color="#666666"><b>내 쪽지함</b></font></td>
            <td width="400" bgcolor="#FFFFFF" ></td>
        </tr>
        </table></td>
</tr>
</table>

<table>
<tr> 
    <td width="600" height="20" colspan="14"></td>
</tr>
<tr> 
    <td width="30" height="24"></td>
    <td width="99" align="center" valign="middle"><a href="./list_msg.do?flag=recv">받은 쪽지</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="99" align="center" valign="middle"><a href="./list_msg.do?flag=send">보낸 쪽지</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="99" align="center" valign="middle"><a href="./create.do">쪽지 보내기</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="148" align="left" valign="middle"></td>
    <td width="30" height="24"></td>
</tr>
</table>


<form name=fmemoform method=post action="./create.do" style="margin:0px;">
<table>
<tr> 
    <td height="300" align="center" valign="top">
        <table style="width: 90%;">
        <tr> 
            <td height="30" align="right" style="padding-right:0px;">
                        </td>
        </tr>
        <tr> 
            <td height="2" bgcolor="#808080"></td>
        </tr>
        <tr> 
            <td width="540" height="2" align="center" valign="top" bgcolor="#FFFFFF">
                <table>
                <tr bgcolor=#E1E1E1 align=center> 
                    <td width="30%" height="24" rowspan="2"><b>받는 회원 아이디</b></td>
                    <td width=70% align="center"><input type=text name="receiveid" required style="width:95%;"></td>
                </tr>
                <tr bgcolor=#E1E1E1 align=center> 
                    <td>※ ID를 잘 확인하세요.</td>
                </tr>
                <tr bgcolor=#F6F6F6 align=center> 
                    <td width="30%" height="24" rowspan="2"><b>제목</b></td>
                    <td width=70% align="center"><input type=text name="title" required style="width:95%;"></td>
                </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="200" align="center" valign="middle" bgcolor="#F6F6F6">
                <textarea name=content rows=10 style='width:95%;' required></textarea></td>
        </tr>
        </table></td>
</tr>
<tr> 
    <td height="2" align="center" valign="top" bgcolor="#D5D5D5"></td>
</tr>
<tr>
    <td height="2" align="center" valign="top" bgcolor="#E6E6E6"></td>
</tr>
<tr>
    <td height="40" align="center" valign="bottom">
        <input type=submit value="전송">&nbsp;&nbsp;
<!--         <a href="javascript:window.close();">닫기</a></td> -->
</tr>
</table>
</form>

 
</div>
</body>
<!-- -------------------------------------------- -->
<!-- -------------------------------------------- -->
</html> 