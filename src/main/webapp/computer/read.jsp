<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<link href="../css/style.css" rel="Stylesheet" type="text/css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/JavaScript">
  window.onload = function() {
    /* CKEDITOR.replace('content'); // <TEXTAREA>태그 id 값 */
  };
</script>
<script type="text/javascript">
$(function(){
  $("iframe.myFrame").load(function(){ //iframe 컨텐츠가 로드 된 후에 호출됩니다.
    $(this).height($(this).contents().find('body')[0].scrollHeight + 120);
    /*       var frame = $(this).get(0);
    var doc = (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
    $(this).height(doc.body.scrollHeight);
    $(this).width(doc.body.scrollWidth); */
  });
});

</script>

<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>



</head>

<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
  <jsp:include page="/menu/top.jsp" flush='false' />
  <!-- ----------------------------------------- -->

  <DIV class='title'>컴퓨터 조회</DIV>

  <DIV class='content' style='width: 80%;'>
    <FORM name='frm' method='POST' action='./create.do' enctype="multipart/form-data">
      <fieldset>
        <ul>
          <li><label for='deal_status'>*판매상태</label> 
          <select name='deal_status' id='deal_status' class=" ">
              <option value="거래중" ${computerVO.deal_status =="거래중" ? "selected=selected":""}>거래중</option>
              <option value="거래완료" ${computerVO.deal_status =="거래완료" ? "selected=selected":""}>거래완료</option>
          </select></li>
          <li><label class='label_1' for='title'>제목</label> 
          ${computerVO.title} </li>
          <li><label class='label_1' for='category'>분류</label>
               ${computerVO.category} 
            <div class="col-xs-5">
              <label for='deal_code'>*거래구분</label>
                ${computerVO.deal_code}
            </div>
            <div class="col-xs-5">
              <label for='product_code'>*상품구분</label>
                ${computerVO.product_code}
            </div></li>
          <li>
            <div class="col-xs-5">
              <label for='deal_way'>*거래방식</label> 
                ${computerVO.deal_way}
            </div>
            <div class="col-xs-5">
              <label for='region'>*지역</label> 
              ${computerVO.region }
            </div>
          </li>
          <li>
            <div class="col-xs-5">
              <label for='hprice'>*희망가격</label> 
                ${computerVO.hprice }
            </div>
            <div class="col-xs-5">
              <label for='purc_date'>구입시기</label>
                ${computerVO.purc_date } 
            </div>
          </li>
          <li>
            <div>
            <label for='content'>내용</label>
              ${computerVO.content }
            </div>
          </li>
          <li><div class="col-xs-5">
              <label for='quantity'>수량</label> 
                ${computerVO.quantity }
            </div></li>
          <li><label class='label_1' for='userid'>등록자 ID</label>
            ${computerVO.userid }</li>
          <li><label class='label_1' for='email'>등록자 이메일</label> 
            ${computerVO.email } <label class='label_1' for='tel'>등록자 연락처</label> ${computerVO.tel }</li>
          <li><label class='label_1' for='nickname'>등록자 별명</label> 
            ${computerVO.nickname }</li>
          <label for="file1" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${computerVO.size1 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/computer/storage&filename=${computerVO.file1}'>${computerVO.file1}</A> (${computerVO.size1Label})
              </c:if>
            </span>    
            <div id='file1Panel'>
              <c:set var='file1' value="${fn:toLowerCase(computerVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' src='./storage/${computerVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG id='file1'  src='./storage/${computerVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG id='file1'  src='./storage/${computerVO.file1}'' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
            <label for="file2" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${computerVO.size2 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/computer/storage&filename=${computerVO.file2}'>${computerVO.file2}</A> (${computerVO.size2Label})
              </c:if>
            </span>    
            <div id='file2Panel'>
              <c:set var='file2' value="${fn:toLowerCase(computerVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${computerVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${computerVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${computerVO.file2}'' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
          <li>
            <label for="file3" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${computerVO.size3 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/computer/storage&filename=${computerVO.file3}'>${computerVO.file3}</A> (${computerVO.size3Label})
              </c:if>
            </span>    
            <div id='file3Panel'>
              <c:set var='file3' value="${fn:toLowerCase(computerVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG id='file3' src='./storage/${computerVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.gif')}">
                  <IMG id='file3'  src='./storage/${computerVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.png')}">
                  <IMG id='file3'  src='./storage/${computerVO.file3}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
           <li>
            <label for="file4" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${computerVO.size4 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/computer/storage&filename=${computerVO.file4}'>${computerVO.file4}</A> (${computerVO.size4Label})
              </c:if>
            </span>    
            <div id='file4Panel'>
              <c:set var='file4' value="${fn:toLowerCase(computerVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' src='./storage/${computerVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4'  src='./storage/${computerVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4'  src='./storage/${computerVO.file4}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
           <li>
            <label for="file5" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${computerVO.size5 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/computer/storage&filename=${computerVO.file5}'>${computerVO.file5}</A> (${computerVO.size5Label})
              </c:if>
            </span>    
            <div id='file5Panel'>
              <c:set var='file5' value="${fn:toLowerCase(computerVO.file5)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file5, '.jpg')}">
                  <IMG id='file5' src='./storage/${computerVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.gif')}">
                  <IMG id='file5'  src='./storage/${computerVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.png')}">
                  <IMG id='file5'  src='./storage/${computerVO.file5}' >
                </c:when>
              </c:choose>
            </div>
          </li>
        </ul>

      </fieldset>
    </FORM>
  </DIV>
  
  <DIV style="text-align: right;">
   <c:if test="${(computerVO.userid eq userid)}">
  <button type='button' onclick="location.href='./update.do?ctno=${computerVO.ctno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
  <button type='button' onclick="location.href='./delete.do?ctno=${computerVO.ctno}&col=${searchDTO.col}&word=${searchDTO.word}'">삭제</button>
  </c:if>
  <button type='button' onclick="location.href='./list.do'">목록</button>
  
</DIV>
  
  
  <!-- -------------------------------------------- -->
  <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>

<iframe src="${pageContext.request.contextPath}/computer_reply/list.do?ctno=${computerVO.ctno}" class='myFrame' width="100%" style="border-style: none;"></iframe>
<!-- -------------------------------------------- -->
</html>
