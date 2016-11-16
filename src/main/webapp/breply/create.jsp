<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="web.tool.*" %>
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script>
function create_login() {
  <% if( session.getAttribute("userid") == null) { %>
  alert('로그인 한 사용자만 사용이 가능합니다.');
  window.parent.openModal();
  <%String bno = request.getParameter("bno");%>
  <%session.setAttribute("url", "book/read.do?bno="+bno);%>
  return false;
  <% } else { %>
  return true;
  <% } %>
  
}

      </script>


</head> 
<!-- ----------------------------------------- -->
<body style="width:100%; ">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->


    
<DIV class='center-block'>
<DIV class='title'>댓글 쓰기</DIV>
<FORM name='frm' method='POST' action='${pageContext.request.contextPath}/breply/create.do' class='form-inline' onsubmit = 'return create_login()'>
  <input type="hidden" name="nickname" id="nickname" value='${nickname}'>
  <input type="hidden" name="bno" id="bno" value='<%=request.getParameter("bno")%>'>
  <input type="hidden" name="userid" id="userid" value='${userid }'>
  <div style="margin:0 auto;">    
        <textarea rows="3" cols="100"  name="rcomment" id="rcomment" placeholder="내용을 입력하세요" style="width:100%;"><%=request.getParameter("bno")%></textarea>
         <div class="text_r">
         <h1 class="reply_num">(<span>250</span>/250)
         <button type="submit">등록</button></h1>
         </div> 
  </div>
 
</FORM>
</DIV>



<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 

