<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="web.tool.*" %>
 
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>
 
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/tool.js"></script>
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
 
<script type="text/javascript">
$(function(){
 
});
</script>
 
<script type="text/javascript">
</script>
</head>
 
<body leftmargin="0" topmargin="0">
<div class="container">
  <jsp:include page="/menu/top.jsp" flush='false' />
     
  <form name="frmSearch" method="get" action="./list.do"> 
    <div class='content_menu' style='width: 100%;'>
     <A href='./create.do?ctno=0&opentype=R'>등록</A>｜
      <A href="javascript:location.reload();">새로고침</A>
    </div>
      <select name="col"> 
        <option value="">선택</option> 
        <option value="title" ${searchDTO.col == "title" ? "selected=selected" : "" }>제목</option> 
        <option value="content" ${searchDTO.col == "content" ? "selected=selected" : "" }>내용</option> 
        <option value="title_content" ${searchDTO.col == "title_content" ? "selected=selected" : "" }>제목+내용</option> 
        <option value="total" ${searchDTO.col == "" ? "selected=selected" : "" }>전체 목록</option>
      </select>
      <c:choose>
        <c:when test="${searchDTO.col != 'total' }"> <!-- 검색 상태 -->
          <input type="text" name="word" size="15" value="${searchDTO.word }">
        </c:when>
        <c:when test="${searchDTO.col == 'total' }"> <!-- 전체 레코드 -->
          <input type="text" name="word" size="15" value="">
        </c:when>
      </c:choose>
     
      <input type="submit" value="검색">
    
  </form> 
  
  <div class="content" style='width: 100%;'>
    <table class="table table-striped" style='width: 100%;'>
      <colgroup>
        <col style="width: 5%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 20%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 10%;"></col>       
      </colgroup>
          
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>번호</th>
          <th>거래구분</th>
          <th>상품구분</th>
          <th>이미지</th>
          <th>제목</th>
          <th>조회수</th>
          <th>등록자</th>
          <th>등록일</th>
          <th>거래상태</th>      
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
        <c:forEach var="vo" items="${list }">
            <tr>
              <td>${vo.ctno}</td>
              <td>${vo.deal_code}</td>
              <td>${vo.category}</td>
              <TD><c:choose>
                  <c:when test="${vo.file1 == null}"></c:when>
                  <c:when test="${vo.file1 != null}">
                    <IMG src='./storage/${vo.file1}'>
                  </c:when>
                  <c:when test="${vo.size2} > 0)">
                    <A href='./read.do?ctno=${vo.ctno }'>${vo.file1}</A>
                  </c:when>
                  <c:otherwise></c:otherwise>
                </c:choose>
              </TD>
              <td><A href='./read.do?ctno=${vo.ctno }'>${vo.title}</A></td>
              <td>${vo.cnt}</td>
              <td>${vo.nickname}</td>
              <td>${vo.wdate}</td>
              <td>${vo.deal_status}</td>
            </tr>
          </c:forEach>
      </tbody>
    </table>
  </div>
 
  <DIV class='bottom'>${paging}</DIV>
  
     <jsp:include page="/menu/bottom.jsp" flush='false' />     
  </div>
</body>
 
</html>