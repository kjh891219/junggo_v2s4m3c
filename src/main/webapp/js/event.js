$(document).ready(function(){
   $('#left_list_top_1').mouseenter(function(){
     $("#left_list_detail_1").delay(5000).slideDown(500);
     /* Toggle('on'); */
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