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
<link href="${pageContext.request.contextPath}/css/style.css?ver=1" rel="Stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/js/event.js?ver=1"></script>

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
  
  function profile(userid, nickname){
    var url = '../member/profile.do?nickname='+nickname;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=617.5px, height=600px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
  }
  
  function msg_list(userid){
    $("#detail").css("display","block");
    var url = '../message/create.do?userid='+userid;
    var encodedInputString=escape(url);
    var win = window.open(url, '프로필', 'width=750px, height=800px');
    
    var x = (screen.width - 500) / 2;
    var y = (screen.height - 440) / 2;
    
    win.moveTo(x, y); // 화면 가운데로 이동
   };
</script>
<style type="text/css">
 .Line{
   border-style : solid;
   color : #d6d6c2;
   width:80%;
   margin : auto;
 }

</style>

</head>

<!-- ----------------------------------------- -->
<body>
     <jsp:include page="/menu/top.jsp" flush='false' />
     <jsp:include page="/menu/left.jsp" flush='false' />
<!-- ----------------------------------------- -->
  
<div class="container">
  
  <DIV class='content'>
    <FORM name='frm' method="get" action='./update.do'>
      <input type="hidden" name="r_no" value="${reviewsVO.r_no}">
       <div class='content_menu' style='width: 100%;'>
        <A href='./list.do?col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}'  class='top_select'>후기 목록</A>>
        <c:if test="${(reviewsVO.userid eq userid)}">
        <A href='./update.do?r_no=${reviewsVO.r_no}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>수정</A>｜
        <A href='./delete.do?r_no=${reviewsVO.r_no}&col=${searchDTO.col}&word=${searchDTO.word}&nowPage=${searchDTO.nowPage}' class='top_select'>삭제</A>
        </c:if>
    </div>
       
       <br><br>
       <div class="Line"  >
       <br>
            <h1 class="tit-prd" style="text-align: center; font-weight: bold ; color:#666633;">
             ${reviewsVO.title}</h1>
            <br>
       </div>
       
       <br><br><br>
      <div style="text-align: center; width:100%;" >
        <div>
          닉네임 : ${reviewsVO.nickname}
         </div>
         <div>
         카테고리 코드 : ${reviewsVO.t_category}
        </div>

        <div>
        등록일 : ${reviewsVO.wdate.substring(0, 16)}
      </div>
    </div>

      <div style="clear:both;"></div>
      <div id="detail_detail" style=" text-align: center; margin-top:70px;">
  
         <div>   
          ${reviewsVO.content}  <br>
         <c:choose>
          <c:when test="${reviewsVO.file1.length() > 0}">
             <IMG src='./storage/${reviewsVO.file1}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
     <BR><BR><BR><BR>
         <c:choose>
           <c:when test="${reviewsVO.file2.length() > 0}">
           <IMG src='./storage/${reviewsVO.file2}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
     <BR><BR><BR><BR>
         <c:choose>
           <c:when test="${reviewsVO.file3.length() > 0}">
           <IMG src='./storage/${reviewsVO.file3}'  style='height:300px; width:430px'>
          </c:when>       
          <c:otherwise>
          </c:otherwise>
          </c:choose>
    </div>
    </div>
    
     <%--       <li>
            <label for="file1" style="width:150px;">업로드 파일: </label>
            <span>
              <c:if test="${reviewsVO.size1 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/reviews/storage&filename=${reviewsVO.file1}'>${reviewsVO.file1}</A> (${reviewsVO.size1Label})
              </c:if>
            </span>    
            <div id='file1Panel'>
              <c:set var='file1' value="${fn:toLowerCase(reviewsVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <IMG id='file1' src='./storage/${reviewsVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <IMG id='file1'  src='./storage/${reviewsVO.file1}' >
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <IMG id='file1'  src='./storage/${reviewsVO.file1}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file2" style="width:150px;">업로드 파일2: </label>
            <span>
              <c:if test="${reviewsVO.size2 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/reviews/storage&filename=${reviewsVO.file2}'>${reviewsVO.file2}</A> (${reviewsVO.size2Label})
              </c:if>
            </span>    
            <div id='file2Panel'>
              <c:set var='file2' value="${fn:toLowerCase(reviewsVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <IMG id='file2' src='./storage/${reviewsVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <IMG id='file2'  src='./storage/${reviewsVO.file2}' >
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <IMG id='file2'  src='./storage/${reviewsVO.file2}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file3" style="width:150px;">업로드 파일3: </label>
            <span>
              <c:if test="${reviewsVO.size3 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/reviews/storage&filename=${reviewsVO.file3}'>${reviewsVO.file3}</A> (${reviewsVO.size3Label})
              </c:if>
            </span>    
            <div id='file3Panel'>
              <c:set var='file3' value="${fn:toLowerCase(reviewsVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <IMG id='file3' src='./storage/${reviewsVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.gif')}">
                  <IMG id='file3'  src='./storage/${reviewsVO.file3}' >
                </c:when>
                <c:when test="${fn:endsWith(file3, '.png')}">
                  <IMG id='file3'  src='./storage/${reviewsVO.file3}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file4" style="width:150px;">업로드 파일4: </label>
            <span>
              <c:if test="${reviewsVO.size4 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/reviews/storage&filename=${reviewsVO.file4}'>${reviewsVO.file4}</A> (${reviewsVO.size4Label})
              </c:if>
            </span>    
            <div id='file4Panel'>
              <c:set var='file4' value="${fn:toLowerCase(reviewsVO.file4)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file4, '.jpg')}">
                  <IMG id='file4' src='./storage/${reviewsVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.gif')}">
                  <IMG id='file4'  src='./storage/${reviewsVO.file4}' >
                </c:when>
                <c:when test="${fn:endsWith(file4, '.png')}">
                  <IMG id='file4'  src='./storage/${reviewsVO.file4}' >
                </c:when>
              </c:choose>
            </div>
          </li>
          
              <li>
            <label for="file5" style="width:150px;">업로드 파일5: </label>
            <span>
              <c:if test="${reviewsVO.size5 > 0}">
                <A href='${pageContext.request.contextPath}/download?dir=/reviews/storage&filename=${reviewsVO.file5}'>${reviewsVO.file5}</A> (${reviewsVO.size5Label})
              </c:if>
            </span>    
            <div id='file5Panel'>
              <c:set var='file5' value="${fn:toLowerCase(reviewsVO.file5)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file5, '.jpg')}">
                  <IMG id='file5' src='./storage/${reviewsVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.gif')}">
                  <IMG id='file5'  src='./storage/${reviewsVO.file5}' >
                </c:when>
                <c:when test="${fn:endsWith(file5, '.png')}">
                  <IMG id='file5'  src='./storage/${reviewsVO.file5}' >
                </c:when>
              </c:choose>
            </div>
          </li>
  --%>
 <div style="width:100%; text-align: center;">
  <iframe style="width:70%; clear:both; text-align: center;" src="${pageContext.request.contextPath}/reviews_reply/list.do?r_no=${reviewsVO.r_no}" 
     scrolling=no name=ce height=900 frameborder=0 style="border-width:0px; border-color:white; border-style:solid;">
  </iframe>
</div>
 
    </FORM>
  </DIV> 



<!-- -------------------------------------------- -->
</div>
<jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
