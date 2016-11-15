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
       <%String ctno = request.getParameter("ctno");%>
       <%session.setAttribute("url", "music/read.do?ctno="+ctno);%>
       return false;
       <% } else { %>
       return true;
       <% } %>
       
     }
      </script>

</head> 

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->

    
<FORM name='frm' method='POST' action='${pageContext.request.contextPath}/music_reply/create.do' class='form-inline'
          onsubmit = 'return create_login()'>
  <input type="hidden" name="nickname" id="nickname" value='${nickname}'>
  <input type="hidden" name="ctno" id="ctno" value='<%=request.getParameter("ctno")%>'>
  <input type="hidden" name="userid" id="userid" value='${userid }'>
  <div style="margin-top: 15px; margin-bottom: 15px; padding: 12px 16px 20px; background: #fcfcfc; border: 1px solid #ddd; border-bottom-color: #ccc; border-radius:8px;">    
     <label style="cursor: pointer; position: relative; margin-bottom: 10px; float: left;">
        <strong style="padding-left: 5px; font-weight: bold;">댓글 쓰기</strong>
     </label>   
     <div style="display: block; position: relative; clear: both;">
        <div style="margin-left: 0; position: relative; margin: 0 10px; display: block;">
          <div style="display: block;">
            <textarea style="width:90%; float:left;" rows="3" name="rcomment" id="rcomment" placeholder="내용을 입력하세요" style="display: block; height: 46px; padding: 4px 8px; border: 1px solid #ddd; border-radius:2px; background: #fff;"><%=request.getParameter("ctno")%></textarea>
          </div>
          <button type="submit" style="width:10%;float:left; height: 56px; line-height: 56px; white-space: no">등록</button>
          <div class="both"></div>
        </div>      
     </div>
     
        
         
  </div>
 
</FORM>


<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 

