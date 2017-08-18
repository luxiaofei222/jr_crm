function coursescroll(){
    var H = 0;
    var w = $(".course-wrap .course-list .ovh").width();
    for(i=0;i<$(".course-list li").length;i++){
        if($(".course-list li").eq(i).height() > H){
            H = $(".course-list li").eq(i).height();
        }
        if ($(".course-list li").eq(i).index() * (207 + 93) > w) {
            $(".course-list li").eq(i).css('opacity', '0');
        }else{
            $(".course-list li").eq(i).css('opacity', '1');
        }
    }
    var a = Math.ceil($(".course-list .ovh").width()/300);
    $(".course-list .ovh").height(2 * H - 15);
    $(".course-list ul").width($(".course-list li").length * (207 + 93));
    $(".course-list li").not($(".course-list li.odd")).each(function(){
        $(this).css("margin-top", H - 15 );
    })
    $(".course-list li.odd").each(function(){
        $(this).css("margin-top", H - $(this).height() );
    })
    $(".course-list li").eq(a).addClass("on").next().css("opacity", 0);
    $(".course-list .next").click(function(){
        $(".course-list .prev").show();
        var $LiOn = $(".course-list li.on");
        // $(".course-list li.on").next().css("opacity", 1)
        $(".course-list li").eq($LiOn.index() - a).css("opacity", 0)
        if($(".course-list li").length - 1 > $LiOn.index() ){
            if($LiOn.index() == $(".course-list li").length - 2){
                $(this).hide();
            }
            $(".course-list li.on").next().addClass("on").siblings().removeClass("on")
            var n = $(".course-list li.on").index() - a;
            $(".course-list ul").stop().animate({marginLeft : - n * 213 }, 500);
            $(".course-list li.on").prev().css('opacity', '1');
            $(".course-list li.on").css('opacity', '0');
            $(".course-list li").last().css('opacity', '1');
        }
    })
    $(".course-list .prev").click(function(){
        $(".course-list .next").show();
        var $LiOn = $(".course-list li.on");
        if($LiOn.index() > a ){
            if($LiOn.index() == a+1){
                $(this).hide();
            }
            $(".course-list li.on").prev().addClass("on").siblings().removeClass("on");
            $(".course-list li.on").next().css("opacity", 0);
            $(".course-list li").eq($(".course-list li.on").index() - a).css("opacity", 1)
            var n = $(".course-list li.on").index() - a;
            $(".course-list ul").stop().animate({marginLeft : - n * 213 }, 500);
        }
    })
}
