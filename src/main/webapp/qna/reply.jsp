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
 
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
 
<script type="text/javascript">
$(function(){
 
});
</script>
 
</head> 
 
<body>
  <div class="container">
     <jsp:include page="/menu/top.jsp" flush='false' />
    <%--  <jsp:include page="/menu/left.jsp" flush='false' /> --%>
 
 
  <div class='content_form' style='width: 100%;'>
    <A href='./list.do'>게시판 목록</A> > 
    <A href='./create.do'>등록</A>｜
    <A href="javascript:location.reload();">새로고침</A>
  </div>
  
  <DIV class='content_form' style='width: 80%;'>
    <FORM name='frm' method='POST' action='./reply.do'
              enctype="multipart/form-data">
      <input type='hidden' name='qnano' id='qnano' value='${qnaVO.qnano }'>
      <input type='hidden' name='categoryno' id='categoryno' value='${qnaVO.categoryno }'>
        
      <div class="row">
        <label for='title' class='col-xs-2 col-lg-2 '>제목</label>
        <input type='text' name='title' id='title' required="required" value='문의합니다' class="col-xs-9 col-lg-9">
      </div>
      <div class="row">
        <label for='content' class='col-xs-2 col-lg-2 ' id="textarea">상세설명</label>
        <textarea rows="10" name="content" id="content" placeholder="내용을 입력하세요"  class="col-xs-9 col-lg-9">내용</textarea>
      </div>
      <hr/>
            <div class='row'>
      <label for="file1" class="col-xs-2 col-lg-2">업로드 파일1 </label>
        <div class="col-xs-10 col-lg-10">
           <input type="file" class="form-control" name='file1MF' id='file1MF' size='40'> <br>
        </div>
      </div>
      <div class='row'>
        <label for="file2" class="col-xs-2 col-lg-2">업로드 파일2 </label> 
        <div class="col-xs-10 col-lg-10">
           <input type="file" class="form-control" name='file2MF' id='file2MF' size='20'> <br>
        </div>
     </div>
      <div class='row'>
        <label for="file3" class="col-xs-2 col-lg-2">업로드 파일3 </label> 
        <div class="col-xs-10 col-lg-10">
           <input type="file" class="form-control" name='file3MF'
              id='file3MF' size='20'> <br>
        </div>
       </div>
       <hr>
      <div class="row">
         <label class='col-xs-2 col-lg-2' for='userid'>작성자</label>
         <span class="col-xs-3 col-lg-3">${memberVO.userid}</span>
         <input type='hidden' name='userid' id='userid' value='${memberVO.userid}' class="col-xs-3 col-lg-3">
         <input type="hidden" name="tel" id="tel" value="${memberVO.tel}"> 
          <input type="hidden" name="passwd" id="passwd" value="${memberVO.pwd}"> 
         <input type="hidden" name="nickname" id="nickname" value="${memberVO.nickname}"> 
         <input type="hidden" name="email" id="email" value="${memberVO.email}"> 
      </div>
      <div class='text_r' >
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.do'" class="btn btn-danger btn-lg">취소</button>
      </div>
    </FORM>
  </DIV>
 
 
     <jsp:include page="/menu/bottom.jsp" flush='false' />     
  </div>
</body>
 
</html> 
 