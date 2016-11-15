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
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript" src="../js/event.js"></script>
<script type="text/javascript">
 $(function(){
     $('#file2').load(function(){ 
       var file2 = $('#file2');
/*         var width = $('#file2').width(); */
        if(file2.size != null){
         if(file2.width() > screen.width*0.6){
           file2.width('100%');
         }
        }
     }); 
 });
 
 $(document).ready(function() {
      
     var xOffset = 150;
     var yOffset = -50; 

     $(document).on("mouseover",".file",function(e){ //마우스 오버시
          
         $("body").append("<p id='preview'><img src='./storage/"+ $(this).text() +"' width='150px' /></p>"); //보여줄 이미지를 선언                       
         $("#preview")
             .css("top",(e.pageY - xOffset) + "px")
             .css("left",(e.pageX + yOffset) + "px")
             .fadeIn("fast"); //미리보기 화면 설정 셋팅
     });
       
     $(document).on("mousemove",".file",function(e){ //마우스 이동시
         $("#preview")
             .css("top",(e.pageY - xOffset) + "px")
             .css("left",(e.pageX + yOffset) + "px");
     });
      
     $(document).on("mouseout",".file",function(){ //마우스 아웃시
         $("#preview").remove();
     });
       
 });
 
 $(document).ready(function() {
    
    if($(".left").height() < $(".right").height()){
         $(".left").height($(".right").height());
    }
    
  });
  
</script>
<style>
	TH {
		text-align: center;
		font-weight: bold;
		/* background-color: dimgray; */
 		/* background-color: lavender;  */
 		border-right: 1px dashed #dcdcdc;
	}
	
	.th_l{
 		border-left: 1px dashed #dcdcdc;
	
	}
	
     #preview{
        z-index: 9999;
        position:absolute;
        border:0px solid #ccc;
        background:#333;
        padding:1px; 
        color:#fff; 
    }
    button{
    	background-color: transparent;
    	border:1px solid lightgray;
    	padding:2px; 
    }
    
    
</style>
 
</head>
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<jsp:include page="/menu/top.jsp" flush='false' />
<jsp:include page="/menu/left.jsp" flush='false' />
<jsp:include page="/menu/community_left.jsp" flush='false' />
<div class="float_l right " style="width:80%;">
<div class="container">
  <!-- ----------------------------------------- -->
    <DIV class="text_c">문의게시판</DIV>
  <DIV class='content'>
    <FORM name='frm' method="get" action='./update.do' 
              enctype="multipart/form-data">
      <input type="hidden" name="qnano" value="${qnaVO.qnano}">
     	
     	<label for="categoryno" >카테고리 > </label>
            <c:choose>
               <c:when test="${qnaVO.categoryno == '1'}">
               <span class='td'>회원가입 및 로그인</span>
               </c:when>
               <c:when test="${qnaVO.categoryno == '2'}">
               <span class='td'>배송문의</span>
               </c:when>
               <c:when test="${qnaVO.categoryno == '3'}">
               <span class='td'>기타문의</span>
               </c:when>
             </c:choose>
		    <div class='content_menu text_r' >
		    <!-- <A href='../qua/list.do'>게시판 목록</A> >  -->
		    <A href='./list.do?'>목록</A>｜ 
		    <A href='./create.do'>등록</A>｜
		    <A href='./reply.do?qnano=${qnaVO.qnano}&categoryno=${qnaVO.categoryno }&col=${searchDTO.col}&word=${searchDTO.word}'>답변</A>｜
		    <A href='./update.do?qnano=${qnaVO.qnano }&col=${searchDTO.col}&word=${searchDTO.word}'>수정</A>｜
		    <A href='./delete.do?qnano=${qnaVO.qnano }&col=${searchDTO.col}&word=${searchDTO.word}'>삭제</A>｜
		    <A href="javascript:location.reload();">새로고침</A>
		  </div>
     <div style="margin-bottom:12px; border-top:1px solid black;border-bottom:1px solid black;">
      <table class="table " style="margin:0;">
      	<colgroup>
	    <col style='width: 20%;'/>
	    <col style='width: 30%;'/>
	    <col style='width: 20%;'/>
	    <col style='width: 30%;'/>
	  </colgroup>
	  <TR>
	    <TH>제목</TH>
	    <TD colspan='3'>${qnaVO.title}</TD>
	  </TR>
	  
	  <TR>
	    <TH>작성자</TH>
	    <TD>${qnaVO.userid}</TD>
	    <TH class="th_l">작성일</TH>
	    <TD>${qnaVO.qdate}</TD>
	  </TR>
	  
	  <TR >
	    <TH style="height: 500px; vertical-align: middle;">내용</TH>
	    <TD colspan='3' style="height: 500px;">${qnaVO.content}</TD>
	  </TR>      
	  <TR >
	    <TH style="vertical-align: middle">파일</TH>
	    <TD colspan='3' >
            <div id="file1Panel">
              <c:set var='file1' value="${fn:toLowerCase(qnaVO.file1)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file1, '.jpg')}">
                  <a id="file1" class="file"href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file1}'>${qnaVO.file1}</a>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.gif')}">
                  <a id="file1" class="file" href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file1}'>${qnaVO.file1}</a>
                </c:when>
                <c:when test="${fn:endsWith(file1, '.png')}">
                  <a id="file1"  class="file"href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file1}'>${qnaVO.file1}</a>
                </c:when>
              </c:choose>
            </div>
            <div id="file2Panel">
              <c:set var='file2' value="${fn:toLowerCase(qnaVO.file2)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file2, '.jpg')}">
                  <a id="file2" class="file" href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file2}'>${qnaVO.file2}</a>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.gif')}">
                  <a id="file2" class="file" href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file2}'>${qnaVO.file2}</a>
                </c:when>
                <c:when test="${fn:endsWith(file2, '.png')}">
                  <a id="file2" class="file" href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file2}'>${qnaVO.file2}</a>
                </c:when>
              </c:choose>
            </div>
            <div id="file3Panel">
              <c:set var='file3' value="${fn:toLowerCase(qnaVO.file3)}" />
              <c:choose>
                <c:when test="${fn:endsWith(file3, '.jpg')}">
                  <a id="file3" class="file" href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file3}'>${qnaVO.file3}</a>
                </c:when>
                <c:when test="${fn:endsWith(file3, '.gif')}">
                  <a id="file3" class="file" href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file3}'>${qnaVO.file3}</a>
                </c:when>
                <c:when test="${fn:endsWith(file3, '.png')}">
                  <a id="file3" class="file" href='${pageContext.request.contextPath}/download?dir=/qna/storage&filename=${qnaVO.file3}'>${qnaVO.file3}</a>
                </c:when>
              </c:choose>
            </div>
	    </TD>
	  </TR>
	  <TR>
	    <TH>이메일</TH>
	    <TD>${qnaVO.email}</TD>
	    <TH class="th_l">전화번호</TH>
	    <TD>${qnaVO.tel}</TD>
	  </TR>      
      
      </table>
      </div>
      <div class='text_r'>
        <button type="button" onclick="location.href='./list.do?col=&word='">목록보기</button>
        <button type="button" onclick="location.href='./update.do?qnano=${qnaVO.qnano}'">수정</button>
        <button type="button" onclick="location.href='./reply.do?qnano=${qnaVO.qnano}&categoryno=${qnaVO.categoryno }'">답변</button>
        <button type="button"
          onclick="location.href='./delete.do?qnano=${qnaVO.qnano}&categoryno=${qnaVO.categoryno}'">삭제</button>
      </div>
    </FORM>
  </DIV>
 </div>
  <!-- -------------------------------------------- -->
  </div>
 <div class="both"></div>
  
  <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html>