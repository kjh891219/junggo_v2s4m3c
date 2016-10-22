<%@ page contentType="text/html; charset=UTF-8" %> 
<% request.setCharacterEncoding("UTF-8"); %>
<% session.setAttribute("url", "index.jsp"); //test %> 
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
<script>
$(document).ready(function(){
   $('#left_list_1').click(function(){
     $(".left_list_detail").slideUp();
     $("#left_detail_1").slideDown(500);
     /* Toggle('on'); */
   })
   $('#left_detail_1').mouseleave(function(){
     $(".left_list_detail").slideUp();
   });
   
   $('#left_list_2').click(function(){
     $(".left_list_detail").slideUp();
     $("#left_detail_2").slideDown(500);
     /* Toggle('on'); */
   })
   $('#left_detail_2').mouseleave(function(){
     
      $(".left_list_detail").slideUp();
   });
   
   $('#left_list_3').click(function(){
      $(".left_list_detail").slideUp();
      $("#left_detail_3").slideDown(500);
     /* Toggle('on'); */
   })
   $('#left_detail_3').mouseleave(function(){
      $(".left_list_detail").slideUp();
   });
   
   $('#left_list_4').click(function(){
      $(".left_list_detail").slideUp();
      $("#left_detail_4").slideDown(500);
      /* Toggle('on'); */
    })
    $('#left_detail_4').mouseleave(function(){
       $(".left_list_detail").slideUp();
    });
   
   $('#left_list_5').click(function(){
      $(".left_list_detail").slideUp();
      $("#left_detail_5").slideDown(500);
      /* Toggle('on'); */
    })
    $('#left_detail_5').mouseleave(function(){
       $(".left_list_detail").slideUp();
    });
   
   $('#left_list_6').click(function(){
      $(".left_list_detail").slideUp();
      $("#left_detail_6").slideDown(500);
      /* Toggle('on'); */
    })
    $('#left_detail_6').mouseleave(function(){
       $(".left_list_detail").slideUp();
    });
   
   $('#left_list_7').click(function(){
      $(".left_list_detail").slideUp();
      $("#left_detail_7").slideDown(500);
      /* Toggle('on'); */
    })
    $('#left_detail_7').mouseleave(function(){
       $(".left_list_detail").slideUp();
    });

   $('#left_list_8').click(function(){
      $(".left_list_detail").slideUp();
      $("#left_detail_8").slideDown(500);
      /* Toggle('on'); */
    })
    $('#left_detail_8').mouseleave(function(){
       $(".left_list_detail").slideUp();
    });
   
});
</script>
<style type="text/css">
 
</style>
</head> 

<body leftmargin="0" topmargin="0">

   <jsp:include page="/menu/top.jsp" flush='false' />
   <jsp:include page="/menu/left.jsp" flush='false' />
   
<div class="container">
   <!-- 본문 내용 -->
   <table class="table table-hover" style='width: 100%;'>
    <thead>
      <colgroup>
        <col style="width: 7%;"></col>
        <col style="width: 30%;"></col>
        <col style="width: 8%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 10%;"></col>
        <col style="width: 15%;"></col>
        <col style="width: 7%;"></col>
        <col style="width: 8%;"></col>
      </colgroup>
          
      <%-- table 컬럼 --%>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>추천</th>
          <th>등록일</th>
          <th>파일</th>
          <th>업로드 파일</th>
          <th>댓글</th>
          <th>기타</th>
        </tr>
      
      </thead>
      
      <%-- table 내용 --%>
      <tbody>
      
        <c:forEach var="vo" items="${list }">
          <tr>
            <td>${vo.blogno}</td>
            <td>
              <a href="./read.do?blogno=${vo.blogno}">${vo.title}</a> 
            </td> 
            <td>${vo.good}</td>
            <td>${vo.rdate}</td>
            <td>${vo.file1}</td>
            <td>${vo.file2}</td>
            <td>${vo.replycnt}</td>
            <td>
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    
    <br><br>
   
   <div style='height:5000px;'></div>
   
</div>
   <jsp:include page="/menu/bottom.jsp" flush='false' />
   

 
</body>
</html> 
 
