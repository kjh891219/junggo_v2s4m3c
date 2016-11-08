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
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<style>
/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
  *{ 
    font-family: 'Nanum Gothic', serif;
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
  }
</style>
</head> 
<!-- ----------------------------------------- -->
<body style="margin: 0 auto;">
<!-- ----------------------------------------- -->
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./act_update.do'>
  <input type='hidden' name='mno' value='${mno }'>
    <DIV style="font-weight: bold; font-size:30px; text-align: left; padding:10px;">회원 권한 변경</DIV>
    <DIV style='width: 100%; clear: both; height: 1px; border-top: dotted 1px #AAAAAA; margin: 10px auto;'></DIV>
    <ul>
      <li>
        <div class="radio">
        <label>
          <input type='radio' name='act' id='act1' value='M' 
          <c:if test = "${act eq M}">
           checked='checked'
          </c:if>
          >
          M: 마스터
        </label>
        </div>
      </li>
      <li>
        <div class="radio">
        <label>
          <input type='radio' name='act' id='act2' value='Y'  
          <c:if test = "${act eq Y}">
          checked='checked'
          </c:if>
          >
          Y: 로그인 가능
        </label>
        </div>
      </li>      
      <li>
        <div class="radio">
        <label>
          <input type='radio' name='act' id='act3' value='N'  
          <c:if test = "${act eq N}">
          checked='checked'
          </c:if>
          >
          N: 로그인 불가
        </label>
        </div>
      </li>
      <li>
        <div class="radio">
        <label>
          <input type='radio' name='act' id='act4' value='H' 
          <c:if test = "${act eq H}">
          checked='checked'
          </c:if>
          >
          H: 보류(신규 가입)
        </label>
        </div>      
      </li>            
      <li class='center'>
        <button type="submit">권한 변경 적용</button>
        <button type='button' onclick="window.close();">닫기</button> 
      </li>
    </ul>
</FORM>
</DIV>
 
</body>
<!-- -------------------------------------------- -->
</html> 