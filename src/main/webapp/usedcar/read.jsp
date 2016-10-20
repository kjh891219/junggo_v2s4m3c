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
 
</script>
 
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 
<DIV class='content'>
    <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="u_no" value="${usedcarVO.u_no}">
      <fieldset class="fieldset">
        <ul>
          <li>
            <label for='title' style="width:150px;">제목 : </label>
            <span>${usedcarVO.title}</span><br>
          </li>
          <li>
            <label for='content' style="width:150px;">내용 : </label>
            <div>${usedcarVO.content}</div>
          </li>
          <li>
            <label for="wdate" style="width:150px;">등록일 : </label>
            <span>${usedcarVO.wdate.substring(0, 16)}</span>
          </li>
         <%--  <li>
            <label for="file1" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${usedcarVO.size2 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/blog/storage&filename=${usedcarVO.file2}'>${usedcarVO.file2}</A> (${usedcarVO.size2Label})
              </c:if>
            </span>    
            <div id='file2Panel'>
              <c:set var='file2' value="${fn:toLowerCase(usedcarVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${usedcarVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${usedcarVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${usedcarVO.file2}' >
                </c:when>
              </c:choose>
            </div>
          </li> --%>
           <TD class='td'>
      <A href="./update.do?u_no=${vo.u_no}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete.do?u_no=${vo.u_no}"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
        </ul>
      </fieldset>
    </FORM>
  </DIV>
 
 
 
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 