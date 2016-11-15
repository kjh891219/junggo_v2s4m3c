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

function msg_list(userid){
  $("#detail").css("display","block");
  var url = '../message/create.do?userid='+userid;
  var encodedInputString=escape(url);
  var win = window.open(url, '프로필', 'width=750px, height=800px');
  
  var x = (screen.width - 500) / 2;
  var y = (screen.height - 440) / 2;
  
  win.moveTo(x, y); // 화면 가운데로 이동
 };

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
<body topmargin="0" leftmargin="0" >
<div>
<input type='hidden' name='nickname' id='nickname' value= '${param.nickname} '/>

<table width="600" height="50" border="0" cellpadding="0" cellspacing="0">
<tr>
    <td align="center" valign="middle" bgcolor="#EBEBEB">
        <table width="590" height="40" border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td colspan="2" width="195" align="left" bgcolor="#FFFFFF" ><font color="#666666"><b><span class='member'>${param.nickname}</span>님의 프로필</b></font></td>
        </tr>
        <tr>
              
            <td id="detail" style="width:100px; height:50px; clear:both; display: none;" >쪽지보내기</td>
            <td width="370" bgcolor="#FFFFFF" ></td>
        </tr>
        </table></td>
</tr>
</table>

<table width="600" border="0" cellspacing="0" cellpadding="0">
<tr> 
    <td align="center" valign="top">
        <table width="540" border="0" cellspacing="0" cellpadding="0">
        <tr> 
            <td height="20" colspan="3"></td>
        </tr>
        <tr> 
            <td width="174" height="149" align="center" valign="middle" background="../skin/member/junggo/img/self_intro_bg.gif">
                <table width="170" height="130" border="0" cellpadding="0" cellspacing="0">
                <tr> 
                    <td align="center" valign="middle">
                      <img src='../member/images/member.jpg' border='0' width='140' height='140'><br>                    </td>
                </tr>
                </table></td>
            <td width="15" height="149"></td>
            <td width="351" height="149" align="center" valign="middle" background="">
                <table width="300" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                    <td width="30" height="25" align="center"><img src="../skin/member/junggo/img/arrow_01.gif" width="7" height="5"></td>
                    <td width="270">이름 : ${vo.name}</td>
                </tr>
                <tr> 
                    <td height="1" colspan="2" bgcolor="#FFFFFF"></td>
                </tr>
                <tr> 
                    <td width="30" height="25" align="center"><img src="../skin/member/junggo/img/arrow_01.gif" width="7" height="5"></td>
                    <td width="270">전화 : ${vo.tel}</td>
                </tr>
                <tr> 
                    <td height="1" colspan="2" bgcolor="#FFFFFF"></td>
                </tr>
                                <tr> 
                    <td width="30" height="25" align="center"><img src="../skin/member/junggo/img/arrow_01.gif" width="7" height="5"></td>
                    <td width="270">메일 : ${vo.email}</td>
                </tr>
                <tr> 
                    <td height="1" colspan="2" bgcolor="#FFFFFF"></td>
                </tr>
                <tr> 
                    <td width="30" height="25" align="center"><img src="../skin/member/junggo/img/arrow_01.gif" width="7" height="5"></td>
                    <td width="270">회원가입일 : ${vo.mdate}</td>
                </tr>
                </table></td>
                </tr>
                </table>
                </td></tr>
                </table><br><br>
      <table width="600" border="0" cellspacing="0" cellpadding="0">
      <tr>
      
     <td height="40" align="center" valign="bottom">
     <A href="javascript: msg_list(' ${vo.userid}');">쪽지보내기</A>
     <a href="javascript:window.close();">닫기</a><br><br></td>    
     </tr> 
     </table>
                
 
</div>
</body>
<!-- -------------------------------------------- -->
<!-- -------------------------------------------- -->
</html> 