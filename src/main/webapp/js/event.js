$(document).ready(function(){
   $('#left_list_top_1').mouseenter(function(){
     $("#left_list_detail_1").slideDown(500);
     /* Toggle('on'); .delay(5000) */
   })
   $('#left_list_detail_1').parent().mouseleave(function(){
     $("#left_list_detail_1").slideUp();
   });
   
   $('#left_list_top_2').mouseenter(function(){
     $("#left_list_detail_2").slideDown(500);
     /* Toggle('on'); */
   })
   $('#left_list_detail_2').parent().mouseleave(function(){
     
      $("#left_list_detail_2").slideUp();
   });
});


function message(){
   var url = './message/list.do?flag=recv';
   var win = window.open(url, '쪽지함', 'width=600px, height=700px');
   
   var x = (screen.width - 500) / 2;
   var y = (screen.height - 440) / 2;
   
   win.moveTo(x, y); // 화면 가운데로 이동
 }