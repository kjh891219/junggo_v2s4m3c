<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="web.tool.*" %>
 
<%

String url_address = Tool.checkNull(request.getParameter("url_address")); // 로그인 후 이동할 주소

System.out.println("--> 3) login_ck_form.jsp: url_address: " + url_address); 
 
Cookie[] cookies = request.getCookies();
Cookie cookie = null;
 
String ck_userid = ""; // userid 저장 변수
String ck_userid_save = ""; // userid 저장 여부를 체크하는 변수
String ck_pwd = ""; // pwd 저장 변수
String ck_pwd_save = ""; // pwd 저장 여부를 체크하는 변수

if(cookies != null) {  
  for (int i=0; i < cookies.length; i++){
    cookie = cookies[i];
    
    if (cookie.getName().equals("ck_userid")){
      ck_userid = cookie.getValue();         // test1@mail.com
    }else if(cookie.getName().equals("ck_userid_save")){
      ck_userid_save = cookie.getValue();  // Y, N
    }else if (cookie.getName().equals("ck_pwd")){
      ck_pwd = cookie.getValue();         // test1@mail.com
    }else if(cookie.getName().equals("ck_pwd_save")){
      ck_pwd_save = cookie.getValue();  // Y, N
    }
  }
}


%>

  <FORM name='frm' method='POST'
    action='${pageContext.request.contextPath}/member/login.do'>
    <input type='hidden' name='url_address' value='<%=url_address%>'>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              <span aria-hidden="true">×</span><span class="sr-only">Close</span>
            </button>
            <h4 class="modal-title" id="myModalLabel">로그인</h4>
          </div>
          <div class="modal-body">
            <ul>
              <li class='center'><label class='' for='userid'>아이디</label>
                <input type='text' name='userid' id='userid'
                value='<%=ck_userid%>' style='width: 50%;'
                autocomplete="off"> <label> <input
                  type='checkbox' name='userid_save' value='Y'
                  <%=(ck_userid_save.equals("Y")) ? "checked='checked'" : ""%>>
                  저장
              </label></li>
              <li class='center'><label class='' for='pwd'>패스워드</label>
                <input type='password' name='pwd' id='pwd'
                value='<%=ck_pwd%>' style='width: 50%;'
                autocomplete="off"> <label> <input
                  type='checkbox' name='pwd_save' value='Y'
                  <%=(ck_pwd_save.equals("Y")) ? "checked='checked'" : ""%>>
                  저장
              </label></li>
              <li class='center'><A href="./create.do">회원가입</A></li>
            </ul>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default"
              data-dismiss="modal">취소</button>
            <button type="submit" class="btn btn-primary">로그인</button>
          </div>
        </div>
        <!-- 모달 콘텐츠 -->
      </div>
      <!-- 모달 다이얼로그 -->
    </div>
    <!-- 모달 전체 윈도우 -->
  </FORM>