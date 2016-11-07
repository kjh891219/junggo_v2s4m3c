$(document).ready(function(){
      $("#menu_top").click(function(){
         if($("#category_list").css("display") == "none"){
            $('#category_list').toggle('fast', "swing", function() {
              $(this).show();
            });
         }else{
            $('#category_list').toggle('fast', "linear",  function() {
              $(this).hide();
            });
         }
       });
      });


$(document).ready(function(){
   $("#downs").click(function(){
      if($("#down_b").css("display") == "none"){
         $('#down_b').slideDown('slow', function() {
           $(this).show();
           $("#down_img").attr("src","${pageContext.request.contextPath}/images/drop_up.png");
         });
      }else{
         $('#down_b').slideUp('slow', function() {
           $(this).hide();
         $("#down_img").attr("src","${pageContext.request.contextPath}/images/drop_down.png");
         });
      }
    });
   });


//현재 스크롤바의 위치를 저장하는 변수 (px)
var currentScrollTop = 0;
   
//비동기식 jQuery이므로 window load 후 jQuery를 실행해야 함
window.onload = function() {
  // 새로고침 했을 경우를 대비한 메소드 실행
  scrollController();
   
  // 스크롤을 하는 경우에만 실행됨
  $(window).on('scroll', function() {
      scrollController();
  });
}
   
//메인 메뉴의 위치를 제어하는 함수
function scrollController() {
  currentScrollTop = $(window).scrollTop();
  if (currentScrollTop < 80) {
      $('#logo').css('display', 'block');
      $('#category_list').css('top',170-(currentScrollTop));
      
  } else {
      $('#logo').css('display', 'none');
      $('#category_list').css('top', 80);
      
  }
}

function message(){
   var url = '${pageContext.request.contextPath}/message/list.do?flag=recv';
   var win = window.open(url, '쪽지함', 'width=600px, height=700px');
   
   var x = (screen.width - 500) / 2;
   var y = (screen.height - 440) / 2;
   
   win.moveTo(x, y); // 화면 가운데로 이동
 }