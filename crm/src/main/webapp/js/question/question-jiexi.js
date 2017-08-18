$(function(){
	//右侧题号的定位第一种
	$(window).scroll(function(){
	var top = $(document).scrollTop();          //定义变量，获取滚动条的高度
	var menu = $(".numbers");                      //定义变量，抓取#menu
	var items = $(".exam-main").find(".collect_nei");    //定义变量，查找.item
	// var menuli=menu.find("li");
	var curId = "";                             //定义变量，当前所在的楼层item #id 

	items.each(function(){
	    var m = $(this);                        //定义变量，获取当前类
	    var itemsTop = m.offset().top;        //定义变量，获取当前类的top偏移量
	    if(top > itemsTop-100){
	        curId = "#" + m.attr("id");
	    }else{
	        return false;
	    }
	});
	    
	//给相应的楼层设置cur,取消其他楼层的cur
	var curLink = menu.find(".cur");
	if( curId && curLink.attr("href") != curId ){
	    curLink.removeClass("cur");
	    menu.find( "[href=" + curId + "]" ).addClass("cur");

	}
	});
})
function SideBar(opt){
  var self=this;
    this.opt={
        sidebar:'',
        items:''
    };
    this.opt=opt;
    this.proboxTop=[];//每个详细模块的偏移量
    this.opt.sidebar.find('span').eq(0).addClass('cur');
    $.each(self.opt.items,function(i){//循环将每个模块对应的偏移量存入数组中
        self.proboxTop[i]=self.opt.items.eq(i).offset().top;
    });

    this.init();//执行init()函数；核心函数
}
$(function(){

	SideBar.prototype={
	    init:function(){
	        this.clickTo();
	        this.scrollTo();

	    },
	    //点击跳转到某个模块
	    clickTo:function(){
	        var self=this;
	        this.opt.sidebar.find('span').finish().click(function(){
	            var i=$(this).index();//获取点击的li对应的索引
	           $('html,body').finish().animate({
	               scrollTop:self.proboxTop[i]//移动到当前与当前索引相同的item上
	           },200);
	            //当前添加类cur其他的兄弟元素去掉cur这个类
	            $(this).addClass('cur').siblings().removeClass('cur');
	        });
	    },
	    //滚动到对应位置显示高亮
	    scrollTo:function() {
	        var self = this;
	        //刷新页面时获取当前对应的高亮处的位置
	        this.change($(window).scrollTop());
	        //滑动滚动条的变化对应左侧导航栏高亮的变化
	        $(window).on('scroll',function(){
	          self.change($(this).scrollTop());
	        });
	    },
	    //左侧高亮变化函数
	    change:function(scrollTop){
	        //第一张到倒数第二张的判断
	            var self=this;
	        var el=self.opt.sidebar;
	        for(var i=0;i<self.proboxTop.length-1;i++){
	            if(self.proboxTop[i]<=scrollTop&&self.proboxTop[i+1]>scrollTop){
	               el.find('span').eq(i).addClass('cur').siblings().removeClass('cur');
	            }
	        }
	        //最后一张的判断
	        if(self.proboxTop[self.proboxTop.length-1]<=scrollTop){
	           el.find('span').eq(self.proboxTop.length-1).addClass('cur').siblings().removeClass('cur');
	        }
	    }
	}
	//禁用右键、文本选择功能、复制按键  
	$(document).bind("contextmenu",function(){return false;});  
	$(document).bind("selectstart",function(){return false;});

	// 点击只看错题
	var scroll=new SideBar({
	    sidebar:$('.numbers'),
	    items:$(".exam-main").find(".collect_nei")
	});
	$(".check_wrong").click(function(){
		if( $(this).prop("checked")){
			 $(".collect_correct").hide();
		}else{
			 $(".collect_correct").show();
		}
	})
	// 历年真题的单项选择与多项选择的切换
	//单选
	$(".list-nav li").click(function(){
	  $(this).addClass("active").siblings().removeClass("active");
	})
	$(".list-item").click(function(){
	  var off1=$(".well1").offset().top+53;
	   $('html,body').finish().animate({
	      scrollTop:off1
	    },200);
	})
	$(".list-item1").click(function(){
	  var off2=$(".well2").offset().top+53;
	   $('html,body').finish().animate({
	      scrollTop:off2
	    },200);
	})
})
//添加收藏
function collection_question(obj,chapter_recourd_id){
	if($.trim($(obj).children("em").text())=="收藏本题"){
		$.post("/front_chapter/collection_question.html",{
			'chapter_recourd_id':chapter_recourd_id,
			'type':"章节练习"
		},function(data){
			if(data==1){
				$(obj).children("i").toggleClass("collect_active");
				$(obj).children("em").html("取消收藏");
			}
		})
	}else{
		quxiao_question_collection(obj,chapter_recourd_id);
	}
}
//取消收藏
function quxiao_question_collection(obj,chapter_recourd_id){
	if($.trim($(obj).children("em").text())=="取消收藏"){
	$.post("/front_chapter/delte_collection_question.html",{
		'chapter_recourd_id':chapter_recourd_id
	},function(data){
		if(data==1){
			$(obj).children("i").toggleClass("collect_active");
			$(obj).children("em").html("收藏本题");
		}
	})
	}else{
		collection_question(obj,chapter_recourd_id)
	}
}