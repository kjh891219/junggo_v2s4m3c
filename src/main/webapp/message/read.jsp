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
</style>
</head> 
<!-- ----------------------------------------- -->

<!-- ----------------------------------------- -->
<body>
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


<table width="600" border="0" cellspacing="0" cellpadding="0">
<tr> 
    <td width="600" height="20" colspan="4"></td>
</tr>
<tr> 
    <td width="30" height="24"></td>
    <td width="530" align="right" bgcolor="#EFEFEF">
        <b>${messageVO.sendid }</b> 님께서 보내온 쪽지의 내용입니다.    </td>
    <td width="10" align="center" valign="middle" bgcolor="#EFEFEF">&nbsp;</td>
    <td width="30" height="24"></td>
</tr>
</table>

<table width="600" border="0" cellspacing="0" cellpadding="0">
<tr> 
    <td height="200" align="center" valign="top">
        <table width="540" border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td height="2" bgcolor="#808080"> </td>
        </tr>
        <tr> 
            <td height="2" bgcolor="#F6F6F6">제목: ${messageVO.title}</td>
        </tr>
        <tr> 
            <td width="540" height="150" align="center" valign="middle" bgcolor="#F6F6F6"><table width="500" height="110" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td valign="top" style='padding-top:10px; padding-bottom:10px;' class=lh>${messageVO.content }</td>
                    </tr>
                </table></td>
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
        <a href='./list.do?flag=${flag}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'>목록</a>&nbsp;&nbsp;        
        <a href="javascript:window.close();">닫기</a><br><br></td>
</tr>
</table>
<br>


</body>
</html>
 
</body>
<!-- -------------------------------------------- -->
<!-- -------------------------------------------- -->
</html> 