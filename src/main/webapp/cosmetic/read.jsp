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
</script>
</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
   <div class="container"> 
    <jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->

  <div class='content_menu' style='width: 100%;'>
    <A href='../cosmetic/list.do'>게시판 목록</A> >
    <A href='./list.do?cno=${CosmeticVO.cno}'>${CosmeticVO.title }</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
    <A href='./create.do?cno=${CosmeticVO.cno}'>등록</A>｜
    <A href='./reply.do?cno=${CosmeticVO.cno}'>답변</A>｜
    <A href='./update.do?cno= ${CosmeticVO.cno}'>수정</A>｜
    <A href='./delete.do?cno= ${CosmeticVO.cno} '>삭제</A>｜
    <A href="javascript:location.reload();">새로고침</A>｜
  </div>

<DIV class='content'>
    <FORM name='frm' method='POST' action='./update.do'>
      <input type='hidden' name='cno' value='${CosmeticVO.cno}'>         
       
        
        <fieldset>
        <ul>
        <li>
            <label for='title' style="width:150px;">제목 : </label>
            <span>${CosmeticVO.title}</span><br>
        </li>
        <li>
            <label for='cnt' style="width:150px;">조회수 : </label>
            <span>${CosmeticVO.cnt}</span><br>
        </li>
        <li>
            <label for='deal_sort' style="width:150px;">거래구분 : </label>
            <span>${CosmeticVO.deal_code}</span><br>
        </li>
       <li>
        <label for='cno'>상품번호 : </label>
         <span>${CosmeticVO.cno}</span><br>
      </li>
      <li>
        <label for='region'>거래지역 : </label>
        <span>${CosmeticVO.region}</span><br> 
      </li>
      <li>
        <label for='deal_way'>거래방식 : </label>
        <span>${CosmeticVO.deal_way }</span><br> 
      </li>
      <li>
        <label  for='purc_date'>구입시기 : </label>
        <span>${CosmeticVO.purc_date}</span><br> 
      </li>
      <li>
        <label for='quantity'>수량 : </label>
        <span>${CosmeticVO.quantity}</span><br>
      </li>
      <li>
        <label for='hprice'>희망가격 : </label>
        <span>${CosmeticVO.hprice}</span><br>
      </li>
     <li>
      <label for='content' style="width:150px;">내용 : </label>
      <span>${CosmeticVO.content}</span>
    </li>
    
    
          <h2>판매자 정보</h2>
     <table border="1">
      <tr>
      <td style="text-align: center;">판매자</td>
      <td style="text-align: center;">${CosmeticVO.nickname}</td>
      </tr>
      <tr>
      <td style="text-align: center;">이메일</td>
      <td style="text-align: center;">${CosmeticVO.email}</td>
      </tr>
      <tr>
      <td style="text-align: center;">전화번호</td>
      <td style="text-align: center;">${CosmeticVO.tel}</td>
      </tr>
      <tr>
      <td style="text-align: center;">등록일</td>
      <td style="text-align: center;">${CosmeticVO.wdate}</td>
      </tr>
    </table>

            <li>
            <label for="file1" class="label" style="width:150px;">
                    업로드 파일: 
            <c:if test="${CosmeticVO.size2 > 0}">
              <A href='${root}/download?dir=/cosmetic/storage&filename=${CosmeticVO.file2}'>${CosmeticVO.file2}</A> (${CosmeticVO.size2Label})
            </c:if>
                
            </label>
            <div>
              <c:set var='file2' value="${fn:toLowerCase(CosmeticVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${CosmeticVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${CosmeticVO.file2}'>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${CosmeticVO.file2}'>
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
            <label for="file3" class="label" style="width:150px;">
                    업로드 파일: 
            <c:if test="${CosmeticVO.size4 > 0}">
              <A href='${root}/download?dir=/cosmetic/storage&filename=${CosmeticVO.file4}'>${CosmeticVO.file4}</A> (${CosmeticVO.size4Label})
            </c:if>
                
            </label>
            <div>
              <c:set var='file4' value="${fn:toLowerCase(CosmeticVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' src='./storage/${CosmeticVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4'  src='./storage/${CosmeticVO.file4}'>
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4'  src='./storage/${CosmeticVO.file4}'>
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
            <label for="file5" class="label" style="width:150px;">
                    업로드 파일: 
            <c:if test="${CosmeticVO.size6 > 0}">
              <A href='${root}/download?dir=/cosmetic/storage&filename=${CosmeticVO.file6}'>${CosmeticVO.file6}</A> (${CosmeticVO.size6Label})
            </c:if>
                
            </label>
            <div>
              <c:set var='file6' value="${fn:toLowerCase(CosmeticVO.file6)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file6, '.jpg')}">
                  <IMG id='file6' src='./storage/${CosmeticVO.file6}'>
                </c:when>
                <c:when test="${fn:endsWith(file6, '.gif')}">
                  <IMG id='file6'  src='./storage/${CosmeticVO.file6}'>
                </c:when>
                <c:when test="${fn:endsWith(file6, '.png')}">
                  <IMG id='file6'  src='./storage/${CosmeticVO.file6}'>
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
          <li>
            <label for="file7" class="label" style="width:150px;">
                    업로드 파일: 
            <c:if test="${CosmeticVO.size8 > 0}">
              <A href='${root}/download?dir=/cosmetic/storage&filename=${CosmeticVO.file8}'>${CosmeticVO.file8}</A> (${CosmeticVO.size8Label})
            </c:if>
                
            </label>
            <div>
              <c:set var='file8' value="${fn:toLowerCase(CosmeticVO.file8)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file8, '.jpg')}">
                  <IMG id='file8' src='./storage/${CosmeticVO.file8}'>
                </c:when>
                <c:when test="${fn:endsWith(file8, '.gif')}">
                  <IMG id='file8'  src='./storage/${CosmeticVO.file8}'>
                </c:when>
                <c:when test="${fn:endsWith(file8, '.png')}">
                  <IMG id='file8'  src='./storage/${CosmeticVO.file8}'>
                </c:when>
              </c:choose>
            </div>
          </li>
          
          
          <li>
            <label for="file9" class="label" style="width:150px;">
                    업로드 파일: 
            <c:if test="${CosmeticVO.size10 > 0}">
              <A href='${root}/download?dir=/cosmetic/storage&filename=${CosmeticVO.file10}'>${CosmeticVO.file10}</A> (${CosmeticVO.size10Label})
            </c:if>
                
            </label>
            <div>
              <c:set var='file10' value="${fn:toLowerCase(CosmeticVO.file10)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file10, '.jpg')}">
                  <IMG id='file10' src='./storage/${CosmeticVO.file10}'>
                </c:when>
                <c:when test="${fn:endsWith(file10, '.gif')}">
                  <IMG id='file10'  src='./storage/${CosmeticVO.file10}'>
                </c:when>
                <c:when test="${fn:endsWith(file10, '.png')}">
                  <IMG id='file10'  src='./storage/${CosmeticVO.file10}'>
                </c:when>
              </c:choose>
            </div>
          </li>
     <%--  <li>
        <label class='label' for='tel'>판매자</label>
        <input type="text" name='tel' id='tel' value=''${CosmeticVO.tel}'>
      </li>
      <li>
        <label class='label' for='tel'>이메일</label>
        <input type="text" name='tel' id='tel' value=''${CosmeticVO.tel}'> 
      </li>
      <li>
        <label class='label' for='tel'>연락처</label>
        <input type="text" name='tel' id='tel' value=''${CosmeticVO.tel}'> 
      </li> --%>
     
   
     <li class='right'>
      <button type="button" onclick="location.href='./list.do?cno=${cosmeticVO.cno}&col=${searchDTO.col}&word=${searchDTO.word}'">목록보기</button>
      <button type="button" onclick="location.href='./update.do?cno=${cosmeticVO.cno}&col=${searchDTO.col}&word=${searchDTO.word}'">수정</button>
      <button type="button" onclick="location.href='./delete.do?cno=${cosmeticVO.cno}'">삭제</button>
     </li>
               
    </ul>
  </fieldset>
</FORM>
</DIV>

<!-- -------------------------------------------- -->
<jsp:include page="/menu/bottom.jsp" flush='false' />
</div>
</body>
<!-- -------------------------------------------- -->
</html> 
