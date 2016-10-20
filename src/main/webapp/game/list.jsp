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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
 
});
</script>
</head> 
<!-- ----------------------------------------- -->
<body>
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 <div class='content_menu' style='width: 100%;'>
      <A href='../game/list.do'>게시판 목록</A> >
      <A href='./list.do?gno=${vo.gno}'>${vo.title } (${totalRecord }) </A>｜
      <A href='./create.do?'>등록</A>｜
      <A href="javascript:location.reload();">새로고침</A>
  <form name="frmSearch" method="get" action="./list.do"> 
      <select name="col"> 
        <option value="">선택</option> 
        <option value="title" ${searchDTO.col == "title" ? "selected=selected" : "" }>제목</option> 
        <option value="content" ${searchDTO.col == "content" ? "selected=selected" : "" }>내용</option> 
        <option value="category" ${searchDTO.col == "category" ? "selected=selected" : "" }>분류</option> 
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
    </div>
     </form> 
<DIV class='title'>상품 목록</DIV>
 
<TABLE class='table'>

  <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
    <col style='width: 20%;'/>
    <col style='width: 20%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 5%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup>
  <TR>
    <TH class='th'>번호</TH>
    <TH class='th'>파일</TH>
    <TH class='th'>업로드 파일</TH>
    <TH class='th'>분류</TH>
    <TH class='th'>글쓴이</TH>
    <TH class='th'>제목</TH>
    <TH class='th'>희망가격</TH>
    <TH class='th'>거래방식</TH>
    <TH class='th'>지역</TH>
    <TH class='th'>조회수</TH>
    <TH class='th'>평점</TH>
    <TH class='th'>등록일</TH>
    <TH class='th'>기타</TH>
  </TR>
 
<c:forEach var="vo" items="${list}">

  <TR>
    <TD class='td'>${vo.gno}</TD>
     <td class="td">
              <c:set var='file2' value="${fn:toLowerCase(vo.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${vo.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${vo.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${vo.file1}'>
                </c:when>
                <c:otherwise>
                  <A href='${root}/download?dir=/game/storage&filename=${vo.file2}'>${vo.file2}</A> (${vo.size2Label})
                </c:otherwise>
              </c:choose>
            </td>
            <td class="td">${vo.file2}</td>
    <TD class='td'>${vo.category}</TD>
    <TD class='td'><A href="./read.do?gno=${vo.gno}">${vo.nickname}</A></TD>
    <TD class='td'><A href="./read.do?gno=${vo.gno}">${vo.title}</A></TD>
    <TD class='td'>${vo.hprice}</TD>
    <TD class='td'>${vo.deal_way}</TD>
    <TD class='td'>${vo.region}</TD>
    <TD class='td'>${vo.cnt}</TD>
    <TD class='td'>${vo.lev}</TD>
    <TD class='td'>${vo.wdate.substring(0,10) }</TD>
    <TD class='td'>
      <A href="./passwd.do?gno=${vo.gno}"><IMG src='./images/passwd.png' title='패스워드 변경'></A>
      <A href="./update.do?gno=${vo.gno}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete.do?gno=${vo.gno}"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
    
  </TR>
</c:forEach>
 
</TABLE>

<DIV class='bottom'>${paging}</DIV>
<DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do'">등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 