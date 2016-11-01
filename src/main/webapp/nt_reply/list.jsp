<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title> 
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> 
<script type="text/JavaScript"
          src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.cookie.js"></script>
<script type="text/javascript" src="../js/tool.js"></script>
<script type="text/javascript">
$(function(){
  $('#panel_frm').hide();
});
function create(rno){
  var e = window.event, btn = e.target || e.srcElement; 
  alert("댓글을 달 글 번호: "+rno);
  var tag = 
   "<DIV id='panel_frm' class='content' style='padding: 10px 0px 10px 0px; width: 100%; text-align: center;'>"+
   "<FORM name='frm' id='frm' method='POST' action='./reply.do'>" +
   "<input type='hidden' name='nickname' id='nickname' value='<c:out value="${nickname}"/>'>" +
   "<input type='hidden' name='noticeno' id='noticeno' value='<c:out value="${noticeno}"/>'>" +
   "<input type='hidden' name='userid' id='userid' value='<c:out value="${userid}"/>'>" +
   "<input type='hidden' name='rno' id='rno' value='" + rno + "'>" +
   "<div class='col-xs-5'>" +
   "<textarea rows='3' cols='100'  name='rcomment' id='rcomment' placeholder='내용을 입력하세요' class='form-group'>댓글입력</textarea>"+
   "<button type='submit' id='submit'>등록</button>" +
   "<button type='button' onclick='create_cancel(this.form)''>닫기</button>" +
   "</div>"+
   "</FORM>"+
   "</DIV>"; 
  $('#comment'+rno).html(tag);  
 }  
 
function create_cancel(frm){
  frm.style.display='none';
}

function delete_form(rno){
  var tag = 
    "<FORM name='delete_from' id='delete_from' method='POST' action='./delete.do'>" +
    "<input type='hidden' name='nickname' id='nickname' value='<c:out value="${nickname}"/>'>" +
    "<input type='hidden' name='noticeno' id='noticeno' value='<c:out value="${noticeno}"/>'>" +
    "<input type='hidden' name='userid' id='userid' value='<c:out value="${userid}"/>'>" +
    "<input type='hidden' name='rno' id='rno' value='" + rno + "'>" +
    "<div class='col-xs-5'>정말로 삭제 하겠습니까?" +
    "<button type='submit' id='submit'>삭제</button>" +
    "<button type='button' onclick='create_cancel(this.form)''>닫기</button>" +
    "</div>"+
    "</FORM>";
    $('#comment'+rno).html(tag);  
}
</script>

</head> 
<!-- ----------------------------------------- -->
<body leftmargin="0" topmargin="0">
<%-- <jsp:include page="/menu/top.jsp" flush='false' /> --%>
<!-- ----------------------------------------- -->
<!-- <DIV id='panel_frm' class='content' style='padding: 10px 0px 10px 0px; width: 100%; text-align: center;'>
              <FORM name='frm' id='frm' method='POST'>
                <input type="hidden" name="nickname" id="nickname" value='dfdf'>
                <input type="hidden" name="ctno" id="ctno" value='1'>
                <input type="hidden" name="userid" id="userid" value='master'>
                <input type="hidden" name="rno" id="rno" value=''>
                <div class="col-xs-5">    
                 <textarea rows="3" cols="100"  name="rcomment" id="rcomment" placeholder="내용을 입력하세요" class="form-group">댓글입력</textarea>
                 <button type="submit" id='submit'>등록</button>
                 <button type="button" onclick="create_cancel()">닫기</button>
                </div>
               
              </FORM>
              </DIV> -->
<jsp:include page="/nt_reply/create.jsp?noticeno=${noticeno}" flush='false'/>

<div class="content" style='width: 90%;'>
    <table class="table" style='width: 100%; border: none;'>
      <tbody>
        <c:forEach var="vo" items="${list }">
          <tr>
            <td class="td_l" style='border: none;'>
              <c:choose>
                <c:when test="${vo.ansnum == 0 }">
                  <img src='./images/url4.png' style='width: 14px;'>
                </c:when>
                <c:when test="${vo.ansnum > 0 }">
                  <c:forEach var="indent"  begin="1" end="${vo.indent }" step="1">
                   <img src='./images/white.jpg' style='width: 25px; opacity: 0.0;'>
                  </c:forEach>
                  <img src='./images/reply3.png'>
                </c:when>
              </c:choose>
                     글번호 : ${vo.rno }
                     글쓴이 : ${vo.nickname }
                     등록일 : ${fn:substring(vo.rdate, 0, 10) }
                      ${vo.rcomment}
              <button type='button' onclick="create('${vo.rno}');">댓글</button>
               
               <c:if test="${(vo.userid eq userid)}">
              <button type='button' onclick="delete_form('${vo.rno}');">삭제</button>
              </c:if>
               
               
               
              <hr>
              <DIV id="comment${vo.rno }">
              <!-- 여기에 Tag를 넣어줍니다 -->
              </DIV> 
            </td>
          </tr>
        </c:forEach>
        
      </tbody>
    </table>
    <br><br>
  </div>




<!-- -------------------------------------------- -->
<%-- <jsp:include page="/menu/bottom.jsp" flush='false' /> --%>
</body>
<!-- -------------------------------------------- -->
</html> 