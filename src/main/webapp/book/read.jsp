<%@ page contentType="text/html; charset=UTF-8" %>
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

<script type="text/javascript" src="./jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>

<script type="text/javascript">
window.onload = function(){ // 무명 함수
	  var file2 = document.getElementById("file2");
	  if (file2 != null){
	    if (file2.width > 600){
	      file2.width = 600;
	    }
	  }
	}
	
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
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
   <div class="container"> 
    <jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

  <div class='content_menu' style='width: 100%;'>
    <A href='../book/list.do'>게시판 목록</A> >
    <A href='./list.do?bno=${bookVO.bno}'>${bookVO.title }</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
    <A href='./create.do?bno=${bookVO.bno}'>등록</A>｜
    <A href='./update.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>수정</A>｜
    <A href='./delete.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>｜
    <A href='./delete.do?bno= ${bookVO.bno} '>삭제</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
  </div>

<DIV class='content'>
    <FORM name='frm' method='POST' action='./update.do'>
      <input type='hidden' name='bno' value='${bookVO.bno}'>         
       
        
        <fieldset>
        <ul>
        <li>
            <label for='title' style="width:150px;">제목 : </label>
            <span>${bookVO.title}</span><br>
        </li>
        <li>
            <label for='cnt' style="width:150px;">조회수 : </label>
            <span>${bookVO.cnt}</span><br>
        </li>
        <li>
            <label for='deal_sort' style="width:150px;">거래구분 : </label>
            <span>${bookVO.deal_code}</span><br>
        </li>
       <li>
        <label for='bno'>상품번호 : </label>
         <span>${bookVO.bno}</span><br>
      </li>
      <li>
        <label for='region'>거래지역 : </label>
        <span>${bookVO.region}</span><br> 
      </li>
      <li>
        <label for='deal_way'>거래방식 : </label>
        <span>${bookVO.deal_way }</span><br> 
      </li>
      <li>
        <label  for='purc_date'>구입시기 : </label>
        <span>${bookVO.purc_date}</span><br> 
      </li>
      <li>
        <label for='quantity'>수량 : </label>
        <span>${bookVO.quantity}</span><br>
      </li>
      <li>
        <label for='hprice'>희망가격 : </label>
        <span>${bookVO.hprice}</span><br>
      </li>
     <li>
      <label for='content' style="width:150px;">내용 : </label>
      <span>${bookVO.content}</span>
    </li>
    
    
    <h2>판매자 정보</h2>
     <table border="1">
      <tr>
      <td style="text-align: center;">판매자</td>
      <td style="text-align: center;">${bookVO.nickname}</td>
      </tr>
      <tr>
      <td style="text-align: center;">이메일</td>
      <td style="text-align: center;">${bookVO.email}</td>
      </tr>
      <tr>
      <td style="text-align: center;">전화번호</td>
      <td style="text-align: center;">${bookVO.tel}</td>
      </tr>
      <tr>
      <td style="text-align: center;">등록일</td>
      <td style="text-align: center;">${bookVO.wdate.substring(0,10)}</td>
      </tr>
    </table>

            <li>
                
            <div>
              <c:set var='file1' value="${fn:toLowerCase(bookVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' src='./storage/${bookVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG id='file1'  src='./storage/${bookVO.file1}'>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG id='file1'  src='./storage/${bookVO.file1}'>
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
          
            <div>
              <c:set var='file2' value="${fn:toLowerCase(bookVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${bookVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${bookVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${bookVO.file2}'>
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
            <div>
              <c:set var='file3' value="${fn:toLowerCase(bookVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG id='file3' src='./storage/${bookVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file3'  src='./storage/${bookVO.file3}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file3'  src='./storage/${bookVO.file3}'>
                </c:when>
              </c:choose>
            </div>
          </li>
   
          
          <li>
            <div>
              <c:set var='file4' value="${fn:toLowerCase(bookVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' src='./storage/${bookVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4'  src='./storage/${bookVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4'  src='./storage/${bookVO.file4}'>
                </c:when>
              </c:choose>
            </div>
          </li>
   
          
          <li>
            <div>
              <c:set var='file5' value="${fn:toLowerCase(bookVO.file5)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file5, '.jpg')}">
                  <IMG id='file5' src='./storage/${bookVO.file5}'>
                </c:when>
                <c:when test="${fn:endsWith(file5, '.gif')}">
                  <IMG id='file5'  src='./storage/${bookVO.file5}'>
                </c:when>
                <c:when test="${fn:endsWith(file5, '.png')}">
                  <IMG id='file5'  src='./storage/${bookVO.file5}'>
                </c:when>
              </c:choose>
            </div>
          </li>
<%--    
   	  <li>
        <label class='label' for='userid'>판매자</label>
        <input type="text" name='userid' id='userid' value=''${bookVO.userid}'>
      </li>
 --%>
   	  <li>
        <label class='label' for='nickname'>판매자</label>
        <input type="text" name='nickname' id='nickname' value='${bookVO.nickname}'>
      </li>
      <li>
        <label class='label' for='email'>이메일</label>
        <input type="text" name='email' id='email' value='${bookVO.email}'> 
      </li>
      <li>
        <label class='label' for='tel'>연락처</label>
        <input type="text" name='tel' id='tel' value='${bookVO.tel}'> 
      </li> 
     
   
     <li class='right'>
      <button type="button" onclick="location.href='./list.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}'">목록보기</button>
      <button type="button" onclick="location.href='./update.do?bno=${bookVO.bno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
      <button type="button" onclick="location.href='./delete.do?bno=${bookVO.bno}'">삭제</button>
     </li>
               
    </ul>
  </fieldset>
</FORM>
<iframe src="${pageContext.request.contextPath}/breply/list.do?bno=${bookVO.bno}" class='myFrame' width="100%" style="border-style: none;"></iframe>  

</DIV>

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</div>
</body>
<!-- -------------------------------------------- -->
</html> 
