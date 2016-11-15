<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<DIV id="main_left">
   <div id="main_left_left">
   <div style="height:170px;">
   
   </div>
   <nav class='left_list_form'>
   <!-- <ul>카테고리</ul> -->
   <UL>
            <li>
            </li>
            <li >
               <a class='left_list' id="left_list_f" style="background:#3b78ce url(./images/car.png) no-repeat left; background-position:20px; ">자동차</a>
               <div class='left_detail' >
                     <dl class="left_detail_list"> 
                        <dt><a href='${pageContext.request.contextPath}/usedcar/list.do' class='menu_style'>중고차</a>
                        <iframe id="left_iframe" class="left_iframe" src="${pageContext.request.contextPath}/usedcar/list2.do" style="display:none;"></iframe>
                        </dt>
                        <dt><a href='${pageContext.request.contextPath}/carproduct/list.do' class='menu_style'>자동차용품</a>
                        <iframe src="${pageContext.request.contextPath}/carproduct/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl> 
               </div>
            </li>
            <li class="both" >
               <a class='left_list'  style="background:#3b78ce url(./images/dress.png) no-repeat left; background-position:20px; ">패션·뷰티</a>
               <div class='left_detail'>
                     <dl class="left_detail_list">
                        <dt><a href='${pageContext.request.contextPath}/cloth/list.do' class='menu_style'>의류</a>
                        <iframe class="left_iframe" src="${pageContext.request.contextPath}/cloth/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                        <dt><a href='${pageContext.request.contextPath}/cosmetic/list.do' class='menu_style'>화장품</a>
                        <iframe src="${pageContext.request.contextPath}/cosmetic/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                        <dt><a href='${pageContext.request.contextPath}/product/list.do' class='menu_style'>잡화</a>
                        <iframe src="${pageContext.request.contextPath}/product/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl>
               </div>
            </li>
            <li class="both" >
               <a class='left_list'  style="background:#3b78ce url(./images/computer.png) no-repeat left; background-position:20px; ">모바일·게임</a>
               <div class='left_detail'>
                     <dl class="left_detail_list">
                        <dt>
                        <a href='${pageContext.request.contextPath}/mobile/list.do' class='menu_style'>핸드폰</a>
                        <iframe class="left_iframe" src="${pageContext.request.contextPath}/mobile/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                        <dt>
                        <a href='${pageContext.request.contextPath}/game/list.do' class='menu_style'>게임</a>
                        <iframe src="${pageContext.request.contextPath}/game/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                        <dt>
                        <a href='${pageContext.request.contextPath}/gamedevice/list.do' class='menu_style'>게임기기</a>
                        <iframe src="${pageContext.request.contextPath}/gamedevice/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl>
               </div>
            </li>
            <li class="both" >
               <a class='left_list'  style="background:#3b78ce url(./images/computer.png) no-repeat left; background-position:20px; ">컴퓨터</a>
               <div class='left_detail'>
                     <dl class="left_detail_list">
                        <dt>
                        <a href='${pageContext.request.contextPath}/computer/list.do' class='menu_style'>컴퓨터</a>
                        <iframe class="left_iframe" src="${pageContext.request.contextPath}/computer/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl>
               </div>
            </li>
            <li class="both" >
               <a class='left_list' style="background:#3b78ce url(./images/television.png) no-repeat left; background-position:20px; ">디지털·가전</a>
               <div class='left_detail'>
                     <dl class="left_detail_list">
                        <dt>
                        <a href='${pageContext.request.contextPath}/music/list.do' class='menu_style'>음향기기</a>
                        <iframe class="left_iframe" src="${pageContext.request.contextPath}/music/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl>
               </div>
            </li>
            <li class="both" >
               <a class='left_list'  style="background:#3b78ce url(./images/camera.png) no-repeat left; background-position:20px; ">카메라</a>
               <div class='left_detail'>
                     <dl class="left_detail_list">
                        <dt>
                        <a href='${pageContext.request.contextPath}/camera/list.do' class='menu_style'>카메라</a>
                        <iframe class="left_iframe" src="${pageContext.request.contextPath}/camera/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl>
               </div>
            </li>
            <li class="both" >
               <a class='left_list' style="background:#3b78ce url(./images/book.png) no-repeat left; background-position:20px; ">여가</a>
               <div class='left_detail'>
                     <dl class="left_detail_list">
                        <dt>
                        <a href='${pageContext.request.contextPath}/art/list.do' class='menu_style'>문화&예술</a>
                        <iframe class="left_iframe" src="${pageContext.request.contextPath}/art/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                        <dt>
                        <a href='${pageContext.request.contextPath}/book/list.do' class='menu_style'>도서</a>
                        <iframe src="${pageContext.request.contextPath}/book/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl>
               </div>
            </li>
            <li class="both" >
               <a class='left_list' id="left_list_8" style="background:#3b78ce url(./images/basketball.png) no-repeat left ; background-position:20px; background-size: 32px;">스포츠·리빙</a>
               <div class='left_detail'>
                     <dl class="left_detail_list"> 
                        <dt>
                        <a href='${pageContext.request.contextPath}/living/list.do' class='menu_style'>생활용품</a>
                        <iframe class="left_iframe" src="${pageContext.request.contextPath}/living/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                        <dt>
                        <a href='${pageContext.request.contextPath}/sports/list.do' class='menu_style'>스포츠</a>
                        <iframe src="${pageContext.request.contextPath}/sports/list2.do" class='iFrame' style="display:none;"></iframe>
                        </dt>
                     </dl>
               </div> 
            </li>
            <li style="clear:both;"></li>
   </UL>
   </nav>
   <!-- <div style="float:left;width:20%; height:460px; background-color:#3b78ce; "></div> -->
   <div class='homeiFrame'  style="float:right;width:65%; height:409px; background-color:white; border:1px solid #3b78ce; position:absolute; left:26%; border-left:none; display:none;">
      <!-- <iframe src="" width="100%" height="100%" style="border-style: none;"></iframe> -->
      <div style="width:90%; margin:0 auto; padding-top: 7%;">
         <div class="float_l text_c" style="width:33%;">
            <img alt="" src="./images/OEVLWY0.png" width="70%;">
            <dl style="margin-top:20px;">
            <dt>24시 고객센터</dt>
            </dl>
         </div>
         <div class="float_l text_c" style="width:33%;">
            <img alt="" src="./images/main_img.png" width="70%;">
            <dl style="margin-top:20px;">
            <dt>다양한 상품</dt>
            </dl>
         </div>
         <div class="float_l text_c" style="width:33%;">
            <img alt="" src="./images/main_img_1.png" width="70%;">
            <dl style="margin-top:20px;">
            <dt>안전거래</dt>
            </dl>
         </div>
         <div class="both"></div>
        
      </div>
   </div>
   </div>
</DIV>