<%@ page contentType="text/html; charset=UTF-8" %> 
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

<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>



<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');

  };


  
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
  
  .label_1{
    color:black;
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
     
  <div class='content_menu' style='width: 90%;'>
    <A href='../reviews/list.do' class='top_select'>게시판 목록</A> > 
    <A href="javascript:location.reload();" class='top_select'>새로고침</A>
  </div>
  
  <DIV class='content' style='width: 90%;'>
    <FORM name='frm' method='POST' action='./update.do'
                enctype="multipart/form-data">
      <input type='hidden' name='r_no' id='r_no' value='${reviewsVO.r_no}'>

      <div class="form-group">  
      
      <ul>
     
      <li>
         <label class='label_1' for='nickname'>닉네임</label>
        <input type='text' name='nickname' id='nickname' value=' ${reviewsVO.nickname} '  required="required">
         <label class='label_1' for='passwd'>비밀번호</label>
        <input type='password' name='passwd' id='passwd' value=' ${reviewsVO.passwd}' required="required"><br><br>
       </li>
       
       <li>
       <label class='label_1'  for='t_category'>카테고리 코드</label>
        <select name='t_category' id='t_category'>
          <option value="중고차" selected="selected">중고차</option>
          <option value="자동차 용품" >자동차 용품</option>
          <option value="국 LPG/화물차/버스" >국산LPG/화물차/버스</option>
          <option value="수입차" >수입차</option>
          <option value="카오디오/카DVD" >카오디오/카DVD</option>
          <option value="차량용 TV" >차량용 TV</option>
          <option value="휠/타이어/캐리어" >휠/타이어/캐리어</option>
          <option value="기타 차량용품" >기타 차량용품</option>
        </select>
      </li>
       
     <li>
        <label class='label_1'  for='seller_nick' >판매자</label>
        <input type='text' name='seller_nick' id='seller_nick' value='${reviewsVO.seller_nick}' ><br><br>
      </li>
       
       <li>
        <label class='label_1' for='title'>제목</label>
        <input type='text' name='title' id='title' size='50' value=' ${reviewsVO.title}'  required="required">
      </li>
      
     <li>
        <label for='content'>내용</label><br>
        <div>
          <textarea class="form-control" name='content' id='content'  rows='10' cols='70'>내용을 입력해주세요</textarea>
        </div>
       </li>

     <li>
      <label for="content" >등록된 파일1</label>
        <div>
          <c:set var='file1' value="${fn:toLowerCase(reviewsVO.file1)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file1, '.jpg')}">
              <IMG src='./storage/${reviewsVO.thumb}'>
            </c:when>
            <c:when test="${fn:endsWith(file1, '.gif')}">
              ${reviewsVO.file1 } 
            </c:when>
            <c:when test="${fn:endsWith(file1, '.png')}">
              ${reviewsVO.file1 } 
            </c:when>
            <c:when test="${reviewsVO.file1.length() > 0}">
              ${reviewsVO.file1 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file1MF" >업로드 파일</label>
        <div>
          <input type="file" class="form-control" name='file1MF' id='file1MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
     <li>
      <label for="content" >등록된 파일2</label>
        <div>
          <c:set var='file2' value="${fn:toLowerCase(reviewsVO.file2)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file2, '.jpg')}">
              ${reviewsVO.file2}
            </c:when>
            <c:when test="${fn:endsWith(file2, '.gif')}">
              ${reviewsVO.file2}
            </c:when>
            <c:when test="${fn:endsWith(file2, '.png')}">
             ${reviewsVO.file2}
            </c:when>
            <c:when test="${reviewsVO.file2.length() > 0}">
              ${reviewsVO.file2 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file2MF" >업로드 파일2</label>
        <div>
          <input type="file" class="form-control" name='file2MF' id='file2MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
     
      
     <li>
      <label for="content" >등록된 파일3</label>
        <div>
          <c:set var='file3' value="${fn:toLowerCase(reviewsVO.file3)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file3, '.jpg')}">
              ${reviewsVO.file3 } 
            </c:when>
            <c:when test="${fn:endsWith(file3, '.gif')}">
              ${reviewsVO.file3 } 
            </c:when>
            <c:when test="${fn:endsWith(file3, '.png')}">
              ${reviewsVO.file3 } 
            </c:when>
            <c:when test="${reviewsVO.file3.length() > 0}">
              ${reviewsVO.file3 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file3MF" >업로드 파일3</label>
        <div>
          <input type="file" class="form-control" name='file3MF' id='file3MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
      <li>
      <label for="content" >등록된 파일4</label>
        <div>
          <c:set var='file4' value="${fn:toLowerCase(reviewsVO.file4)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file4, '.jpg')}">
              ${reviewsVO.file4 } 
            </c:when>
            <c:when test="${fn:endsWith(file4, '.gif')}">
              ${reviewsVO.file4 } 
            </c:when>
            <c:when test="${fn:endsWith(file4, '.png')}">
              ${reviewsVO.file4 } 
            </c:when>
            <c:when test="${reviewsVO.file4.length() > 0}">
              ${reviewsVO.file4 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file4MF" >업로드 파일4</label>
        <div>
          <input type="file" class="form-control" name='file4MF' id='file4MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>
      
            <li>
      <label for="content" >등록된 파일5</label>
        <div>
          <c:set var='file5' value="${fn:toLowerCase(reviewsVO.file5)}" />
          <c:choose>
            <c:when test="${fn:endsWith(file5, '.jpg')}">
              ${reviewsVO.file5 } 
            </c:when>
            <c:when test="${fn:endsWith(file5, '.gif')}">
              ${reviewsVO.file5 } 
            </c:when>
            <c:when test="${fn:endsWith(file5, '.png')}">
              ${reviewsVO.file5 } 
            </c:when>
            <c:when test="${reviewsVO.file5.length() > 0}">
              ${reviewsVO.file5 } 
            </c:when>
          </c:choose>
        </div>
      </li>
      
      <li>
      <label for="file5MF" >업로드 파일5</label>
        <div>
          <input type="file" class="form-control" name='file5MF' id='file5MF' size='40' >
          <br>
          Preview(미리보기) 이미지 자동 생성됩니다.
        </div>
      </li>

        </ul> 
        </div>
        

      <DIV style='text-align: right;'>
        <button type="submit">수정</button>
        <button type="button" onclick="location.href='./list.do?r_no= ${reviewsVO.r_no}'">목록[취소]</button>
      </DIV>         
    </FORM>
  </DIV>

</div>
     <jsp:include page="/menu/bottom.jsp" flush='false' />     

</body>

</html> 


