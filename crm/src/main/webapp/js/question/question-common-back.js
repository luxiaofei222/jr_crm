$(".chapterMenu").hover(function(){
   $(this).toggleClass("is-hover");
   })
$(".zhangjie_content li .list-items .nth-1").click(function(){
   $(this).parent().next().slideToggle();
   var val= $(this).parent().children(".col-6").find("i").attr("class").replace(/\s+/g, "");
   if(val=="iconiconfonticon-yuanquanjiahao"){
	  $(this).parent().children(".col-6").find("i").removeClass("icon-yuanquanjiahao");
	  $(this).parent().children(".col-6").find("i").addClass("icon-yuanquanjianhao"); 
	  $(this).parent().children(".col-6").find("i").css("color","#999");
		}
   else{
    $(this).parent().children(".col-6").find("i").removeClass("icon-yuanquanjianhao");
    $(this).parent().children(".col-6").find("i").addClass("icon-yuanquanjiahao");
	 $(this).parent().children(".col-6").find("i").css("color","#69a5eb");
  	}
})
$(function(){
	//划过2016教材版本出现
	$(".width_diff").hover(function(){
		$(".width_diff_info").show();
	},function(){
		$(".width_diff_info").hide();
	})
	$(".jiucuo .fold-toggle").click(function(){
		$(".answer_inline").toggle(200);
		$(".fold-bd").toggle(200);
		$(this).find("i").toggleClass("tubiaoz");
	})
})
