<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
 
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<link href="../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
 
</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
   <jsp:include page="/menu/top.jsp" flush='false' />
<!-- ----------------------------------------- -->
<DIV class=container>
<DIV class='title'>상품등록</DIV>
 
<FORM name='frm' method='POST' action='./create.do' class=''  enctype="multipart/form-data">
<DIV class='content_form'>

     <DIV class="">

       <div class="row">
        <label for='title' class='col-xs-2 col-lg-2 need'>제목</label>
        <input type='text' name='title' id='title' required="required" value='가을이 없어요' class="col-xs-9 col-lg-9">
      </div>
      <div class="row">
        <label for='content' class='col-xs-2 col-lg-2 choice'>내용</label>
        <textarea rows="10" name="content" id="content" placeholder="내용을 입력하세요"  class="col-xs-9 col-lg-9"></textarea>
      </div>
      <hr/>
        <div class="row need">   
        <label for='file2MF' class='col-xs-2 col-lg-2'>업로드 파일</label>
        <input type="file" name='file2MF' id='file2MF' size='40'>
      </div>
   
          <div class="row">
            <label for='nickname' class='col-xs-2 col-lg-2 need'>별명</label>
            <input type='text' name='nickname' id='nickname' value= '투투' required="required" readonly="readonly" class="col-xs-3 col-lg-3"/>
            </div>
          <div class="row">
              <label class='col-xs-2 col-lg-2 need' for='userid'>아이디</label>
              <input type='text' name='userid' id='userid' value='master' required="required" class="col-xs-3 col-lg-3">
          </div>
      
      <hr/>
       <div class='text_r' >
        <button type="submit" class="btn btn-success btn-lg">등록</button>
        <button type="button" onclick="location.href='./list.jsp'" class="btn btn-danger btn-lg">취소</button>
      </div>
      </div>
      </DIV>
               
</FORM>
</DIV>
<!-- -------------------------------------------- -->
 <jsp:include page="/menu/bottom.jsp" flush='false' />
</body>
<!-- -------------------------------------------- -->
</html> 
