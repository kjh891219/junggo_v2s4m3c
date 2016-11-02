<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title></title>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link href="./css/style.css" rel="Stylesheet" type="text/css">

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
<script>
window.openModal = function() {
  $( '#myModal' ).modal( 'show' );
  }
</script>




<style type="text/css">

/* 전체 스타일 */
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
  *{ 
    font-family: 'Nanum Gothic', serif;
    font-size: 15px;
    margin: 0px;
    padding: 0px;  
  }
  
  a{
   color:white;
  }
  
/* left를 제외한 스타일 */
  body{
   width:80%;
   margin-left:130px;
  }
  
/* top 스타일 */
 .top_select{
     color: black; 
 }
  header{ 
    height: 35px; 
    background-color: #e6e6e6; 
    font-family: 맑은 고딕;  
    text-align: center;
  }
  .member-list {
    margin:5px 8px 0 0;
  
  }
  
 .member-list li {
    float:left;
    list-style: none;
    padding-left:8px;
  }
 .member-list li a {
    font-size:12px;
  }

/* left */  

   /* 로고 */
   #logo {
      width:70px;
      margin:20px auto;
   }
   #logo img {
      width:70px;
   }
   
  #main_left {
    position:fixed; 
    top:0;
    left:0;
  }
  
  #main_left_left{
    width:130px; 
    height:100%;
    float:left;
    color:white;
    background-color: #737373;
  }
  
   #main_left_detail{
      display:none;
      position:absolute;
      left:130px;
      width:130px;
      height:100%;
      
      background-color:#575757;
   }
  
  .left_list_form {
    padding:10px;
  }
  
  .left_list{
    padding-bottom:8px;
  }

/* index 안에 있는 태그 스타일 */
 .list_tag{
   color : black;
 }
   .container{
      width:100%;
   }
   
   nav ul li {
      list-style:none;
      margin-left: 20px;
   }
   nav {
      margin-top:30px;
   }
   footer{
      text-align: center;
   }
 
 
</style>

</head>

<!-- ----------------------------------------- -->
<body>
<div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
     
     
  <div class='content_menu' style='width: 100%;'>
    <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>목록</A>>
    <A href="javascript:location.reload();" class='top_select'>새로고침</A>｜
    <A href='./create.do' class='top_select'>등록</A>｜
    <A href='./update.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>수정</A>｜
    <A href='./delete.do?ano=${artVO.ano}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>
  </div>
  
  <DIV class='content'>
    <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="ano" value="${artVO.ano}">
      <fieldset class="fieldset">
        <ul>
              
          <li>
            <label for='title' style="width:150px;">제목 : </label>
            <span>${artVO.title}</span><br>
          </li>
          
        <li>
        <label for="nickname" style="width:150px;">닉네임 : </label>
         <span>${artVO.nickname}</span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <label for="wdate" style="width:150px;">등록일 : </label>
         <span>${artVO.wdate.substring(0, 16)}</span>
        </li>
        
        <li>
        <label class='label' for='category'>카테고리 코드 : </label>
        <span>${artVO.category}</span>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label class='label' for='deal_code'>거래구분 코드 : </label>
       <span>${artVO.deal_code}</span><br>
      </li>
      
      <li>
      <label class='label' for='region'>거래 지역 : </label>
      <span>${artVO.region}</span> 
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <label class='label' for='deal_way'>거래방식 : </label>
      <span>${artVO.deal_way}</span><br>
      </li>
      
        <li>
        <label class='label' for='hprice'>희망가격 : </label>
         <span>${artVO.hprice}</span><br>
      </li>
      
      <li>
        <label class='label' for='purc_date'>구입시기 : </label>
      <span>${artVO.purc_date}</span> 
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <label class='label' for='product_code'>상품구분 : </label>
        <span>${artVO.product_code}</span><br>
      </li>

          <li>
            <label for='content' style="width:150px;">내용 : </label>
            <div>${artVO.content}</div>
          </li>
    
          <li>
            <label for="file1" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${artVO.size1 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/usedcar/storage&filename=${artVO.file1}'>${artVO.file1}</A> (${artVO.size1Label})
              </c:if>
            </span>    
            <div id='file1Panel'>
              <c:set var='file1' value="${fn:toLowerCase(artVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' src='./storage/${artVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG id='file1'  src='./storage/${artVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG id='file1'  src='./storage/${artVO.file1}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file2" style="width:150px;">업로드 파일2: </label>
            <span>
              <c:if test="${artVO.size2 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/usedcar/storage&filename=${artVO.file2}'>${artVO.file2}</A> (${artVO.size2Label})
              </c:if>
            </span>    
            <div id='file2Panel'>
              <c:set var='file2' value="${fn:toLowerCase(artVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${artVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${artVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${artVO.file2}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file3" style="width:150px;">업로드 파일3: </label>
            <span>
              <c:if test="${artVO.size3 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/usedcar/storage&filename=${artVO.file3}'>${artVO.file3}</A> (${artVO.size3Label})
              </c:if>
            </span>    
            <div id='file3Panel'>
              <c:set var='file3' value="${fn:toLowerCase(artVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG id='file3' src='./storage/${artVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.gif')}">
                  <IMG id='file3'  src='./storage/${artVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.png')}">
                  <IMG id='file3'  src='./storage/${artVO.file3}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file4" style="width:150px;">업로드 파일4: </label>
            <span>
              <c:if test="${artVO.size4 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/usedcar/storage&filename=${artVO.file4}'>${artVO.file4}</A> (${artVO.size4Label})
              </c:if>
            </span>    
            <div id='file4Panel'>
              <c:set var='file4' value="${fn:toLowerCase(artVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' src='./storage/${artVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4'  src='./storage/${artVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4'  src='./storage/${artVO.file4}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file5" style="width:150px;">업로드 파일5: </label>
            <span>
              <c:if test="${artVO.size5 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/usedcar/storage&filename=${artVO.file5}'>${artVO.file5}</A> (${artVO.size5Label})
              </c:if>
            </span>    
            <div id='file5Panel'>
              <c:set var='file5' value="${fn:toLowerCase(artVO.file5)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file5, '.jpg')}">
                  <IMG id='file5' src='./storage/${artVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.gif')}">
                  <IMG id='file5'  src='./storage/${artVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.png')}">
                  <IMG id='file5'  src='./storage/${artVO.file5}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
          <li>
         <label for='email'>E-mail</label><br>
            <span>${artVO.email}</span><br>
          </li>
          
          <li>
        <label for='tel'>Tel</label><br>
         <span>${artVO.tel}</span><br>
      </li>

        </ul>
      </fieldset>
    </FORM>
  </DIV>
 
<iframe src="${pageContext.request.contextPath}/art_reply/list.do?ano=${artVO.ano}" scrolling=no name=ce width=900 height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;"></iframe>  

<!-- -------------------------------------------- -->
</div>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
