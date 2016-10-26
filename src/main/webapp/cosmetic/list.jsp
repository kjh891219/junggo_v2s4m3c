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
/* $(function(){
	  $('#panel_frm').hide();
	  $('#panel_frm_delete').hide();
	});
	
	
function create(){
	  $('#panel_frm').show();
	  $('#frm').attr('action', './create.do');
	  $('#submit').html('등록');
	}
	 
	function create_cancel(){
	  $('#panel_frm').hide();
	}
 */
</script>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
 <form name="frmSearch" method="get" action="./list.do"> 
    <input type='hidden' name='cno' id='cno' value='${cosmeticVO.cno }'>
   
     <div class='content_menu' style='width: 100%;'>
     <A href='../cosmetic/list.do'>게시판 목록</A> ｜
    <A href='./list.do'>${cosmeticVO.title } (${totalRecord })</A>｜
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
    </div> 
  </form>
   
   <div class="content" style='width: 90%;'>
<TABLE class='table' style='width: 70%;'>
  <%-- <colgroup>
    <col style='width: 5%;'/>
    <col style='width: 13%;'/>
    <col style='width: 16%;'/>
    <col style='width: 17%;'/>
    <col style='width: 10%;'/>
    <col style='width: 14%;'/>
    <col style='width: 14%;'/>
    <col style='width: 10%;'/>
    <col style='width: 10%;'/>
  </colgroup> --%>
  
  <TR>
    <TH class='th'>글번호</TH>
    <TH class='th'>사진</TH>
    
    <TH class='th'>제품명</TH>
    <TH class='th'>글제목</TH>
    <TH class='th'>가격</TH>
    <TH class='th'>거래지역</TH>
    <TH class='th'>거래방식</TH>
    <TH class='th'>닉네임</TH>
    <TH class='th'>조회수</TH>
    <TH class='th'>기타</TH>
  </TR>
 
  <c:forEach var="vo" items="${list }">
  <TR>
    <TD class='td'>${vo.cno}</TD>
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
                  <A href='${root}/download?dir=/cosmetic/storage&filename=${vo.file2}'>${vo.file2}</A> (${vo.size2Label})
                </c:otherwise>
              </c:choose>
            </td>
            <%-- <td class="td">${vo.file2}</td> --%>
    <TD class='td'>${vo.category}</TD>
    <TD class='td'>
         <a href="./read.do?cno=${vo.cno}">${vo.title}</a> 
    </TD>
    <TD class='td'>${vo.hprice}</TD>
    <TD class='td'>${vo.region}</TD>
    <TD class='td'>${vo.deal_way}</TD>
    <TD class='td'>${vo.nickname}</TD>
    <TD class='td'>${vo.cnt}</TD>
    
    <TD class='td'>
      <A href="./update.do?cno=${vo.cno}"><IMG src='./images/update.png' title='수정'></A>
      <A href="./delete.do?cno=${vo.cno}"><IMG src='./images/delete.png' title='삭제'></A>
    </TD>
  </TR>
  </c:forEach>
 
</TABLE>
</div>
   <DIV class='bottom'>${paging}</DIV>
   
<DIV class='bottom'>
  <button type='button' onclick="location.href='./create.do';" >등록</button>
  <button type='button' onclick="location.reload();">새로 고침</button>
</DIV>
<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
 