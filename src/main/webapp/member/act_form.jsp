<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/tool.js"></script>
 
</head> 
<!-- ----------------------------------------- -->
<body style="margin: 0 auto;">
<!-- ----------------------------------------- -->
 
<DIV class='content'>
<FORM name='frm' method='POST' action='./act_update.do'>
  <input type='hidden' name='mno' value='${mno }'>
  <fieldset class='fieldset'>
    <legend class='legend'>관리자 등록 - 권한의 변경</legend>
    <ul>
      <li>
        <label>
          <input type='radio' name='act' id='act1' value='M' 
          <c:if test = "${act eq M}">
           checked='checked'
          </c:if>
          >
          M: 마스터
        </label>
      </li>
      <li>
        <label>
          <input type='radio' name='act' id='act2' value='Y'  
          <c:if test = "${act eq Y}">
          checked='checked'
          </c:if>
          >
          Y: 로그인 가능
        </label>
      </li>      
      <li>
        <label>
          <input type='radio' name='act' id='act3' value='N'  
          <c:if test = "${act eq N}">
          checked='checked'
          </c:if>
          >
          N: 로그인 불가
        </label>
      </li>
      <li>
        <label>
          <input type='radio' name='act' id='act4' value='H' 
          <c:if test = "${act eq H}">
          checked='checked'
          </c:if>
          >
          H: 보류(신규 가입)
        </label>      
      </li>            
      <li class='center'>
        <button type="submit">권한 변경 적용</button>
        <button type='button' onclick="window.close();">닫기</button> 
      </li>
    </ul>
  </fieldset>
</FORM>
</DIV>
 
</body>
<!-- -------------------------------------------- -->
</html> 