<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<DIV id="main_left">
   <div id="main_left_left">
   <div id='logo'><img src="${pageContext.request.contextPath}/images/logo_1.png"></div>
   
   <nav class='left_form'>
   <!-- <ul>카테고리</ul> -->
   <UL class='float_r' style="width:100%;">
      <li class='left_list_main' id="left_list_top_1">
         <a href='#' class='menu_top'>카테고리</a>
         <ul class='left_list_detail'id='left_list_detail_1'>
            <li>
               <a href='${pageContext.request.contextPath}/usedcar/list.do' class='menu_style'>중고차</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/carproduct/list.do' class='menu_style'>자동차용품</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/cloth/list.do' class='menu_style'>의류</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/cosmetic/list.do' class='menu_style'>화장품</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/product/list.do' class='menu_style'>잡화</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/computer/list.do' class='menu_style'>컴퓨터</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/music/list.do' class='menu_style'>음향기기</a>
            </li>
            <li>
               <a href='#' class='menu_style'>예술·문화</a>
            </li>
            <li>
               <a href='#' class='menu_style'>도서</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/game/list.do' class='menu_style'>게임</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/gamedevice/list.do' class='menu_style'>게임기기</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/mobile/list.do' class='menu_style'>핸드폰</a>
            </li>
            <li>
               <a href='#' class='menu_style'>생활용품</a>
            </li>
            <li>
               <a href='#' class='menu_style'>스포츠</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/camera/list.do' class='menu_style'>카메라</a>
            </li>
         </ul>
      </li>
      <li class='left_list_main' id="left_list_top_2">
         <a href='#' class='menu_top'>커뮤니티</a>
         <ul class='left_list_detail' id='left_list_detail_2'>
            <li>
               <a href='#' class='menu_style'>공지사항</a>
            </li>
            <li>
               <a href='#' class='menu_style'>이용후기</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/cheat/list.do' class='menu_style'>허위상품신고</a>
            </li>
            <li>
               <a href='${pageContext.request.contextPath}/qna/list.do' class='menu_style'>Q&A</a>
            </li>
         </ul>
      </li>
      <li class='left_list_main' >
      </li>
   </UL>
   </nav>
   </DIV>
   <div style="clear:both;"></div>
</DIV>