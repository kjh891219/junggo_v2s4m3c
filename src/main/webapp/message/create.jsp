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
    <td width="99" align="center" valign="middle"><a href="./create.do">쪽지 보내기</a></td>
    <td width="2" align="center" valign="middle">&nbsp;</td>
    <td width="100" valign="middle" bgcolor="#EFEFEF">&nbsp;</td>
    <td width="148" align="left" valign="middle">전체 받은 쪽지 [ <B>${cnt }</B> ]통</td>
    <td width="3" bgcolor="#EFEFEF"></td>
    <td width="30" height="24"></td>
</tr>
</table>


<form name=fmemoform method=post onsubmit="return fmemoform_submit(this);" style="margin:0px;">
<table width="600">
<tr> 
    <td height="300" align="center" valign="top">
        <table width="540">
        <tr> 
            <td height="30" align="right" style="padding-right:0px;">
                        </td>
        </tr>
        <tr> 
            <td height="2" bgcolor="#808080"></td>
        </tr>
        <tr> 
            <td width="540" height="2" align="center" valign="top" bgcolor="#FFFFFF">
                <table width=100% cellpadding=1 cellspacing=1 border=0>
                <tr bgcolor=#E1E1E1 align=center> 
                    <td width="30%" height="24" rowspan="2"><b>받는 회원 아이디</b></td>
                    <td width=70% align="center"><input type=text name="sendid" required style="width:95%;"></td>
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
        <input id=btn_submit type=image src="../skin/member/junggo/img/btn_paper_send.gif" border=0>&nbsp;&nbsp;
        <a href="javascript:window.close();"><img src="../skin/member/junggo/img/btn_close.gif" width="48" height="20" border="0"></a></td>
</tr>
</table>
</form>

 
</div>
</body>
<!-- -------------------------------------------- -->
<!-- -------------------------------------------- -->
</html> 