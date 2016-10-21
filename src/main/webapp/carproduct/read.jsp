<%@ page contentType="text/html; charset=UTF-8"%>
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
    $('#file2').load(function(){ // 태그 메모리 상주후 작동
      // var width = $('#file2').width();
      //alert('file2: ' + width); 
      if ($('#file2').width() > screen.width * 0.7){
        $('#file2').width('70%');      
      }
    });
  });
  
</script>
</head>

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
     
     
  <div class='content_menu' style='width: 100%;'>
   <A href='../carproduct/list.do'>중고차 목록</A>>
    <A href="javascript:location.reload();">새로고침</A>｜
    <A href='./create.do?'>등록</A>｜
    <A href='./update.do?p_no=${carproductVO.p_no}&p_no=${carproductVO.p_no}&col=${searchDTO.col}&word=${searchDTO.word}'>수정</A>｜
    <A href='./delete.do?p_no=${carproductVO.p_no}&p_no=${carproductVO.p_no}&col=${searchDTO.col}&word=${searchDTO.word}'>삭제</A>
  </div>
  
  <DIV class='content'>
    <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="u_no" value="${carproductVO.p_no}">
      <fieldset class="fieldset">
        <ul>
              
          <li>
            <label for='title' style="width:150px;">제목 : </label>
            <span>${carproductVO.title}</span><br>
          </li>
          
        <li>
        <label for="nickname" style="width:150px;">닉네임 : </label>
         <span>${carproductVO.nickname}</span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <label for="wdate" style="width:150px;">등록일 : </label>
         <span>${carproductVO.wdate.substring(0, 16)}</span>
        </li>
        
        <li>
        <label class='label' for='category'>카테고리 코드 : </label>
        <span>${carproductVO.category}</span>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label class='label' for='deal_code'>거래구분 코드 : </label>
       <span>${carproductVO.deal_code}</span><br>
      </li>
      
      <li>
      <label class='label' for='region'>거래 지역 : </label>
      <span>${carproductVO.region}</span> 
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <label class='label' for='deal_way'>거래방식 : </label>
      <span>${carproductVO.deal_way}</span><br>
      </li>
      
        <li>
        <label class='label' for='h_price'>희망가격 : </label>
         <span>${carproductVO.h_price}</span><br>
      </li>
      
      <li>
        <label class='label' for='purc_date'>구입시기 : </label>
      <span>${carproductVO.purc_date}</span> 
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <label class='label' for='product_code'>상품구분 : </label>
        <span>${carproductVO.product_code}</span><br>
      </li>

          <li>
            <label for='content' style="width:150px;">내용 : </label>
            <div>${carproductVO.content}</div>
          </li>
    
          <li>
            <label for="file1" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${carproductVO.size2 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/carproduct/storage&filename=${carproductVO.file2}'>${carproductVO.file2}</A> (${carproductVO.size2Label})
              </c:if>
            </span>    
            <div id='file2Panel'>
              <c:set var='file2' value="${fn:toLowerCase(carproductVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${carproductVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${carproductVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${carproductVO.file2}'' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
         <label for='email'>E-mail</label><br>
            <span>${carproductVO.email}</span><br>
          </li>
          
          <li>
        <label for='tel'>Tel</label><br>
         <span>${carproductVO.tel}</span><br>
      </li>

        </ul>
      </fieldset>
    </FORM>
  </DIV>
 


<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
