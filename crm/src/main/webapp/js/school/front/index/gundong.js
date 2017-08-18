// 左侧菜单的滚动
$(function(){
     $(window).scroll(function(){
        var top = $(document).scrollTop();          //定义变量，获取滚动条的高度
        var menu = $("#menu");                      //定义变量，抓取#menu
        var items = $("#content").find(".course_item");    //定义变量，查找.item
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
        function SideBar(opt){
          var self=this;
            this.opt={
                sidebar:'',
                items:''
            };
            this.opt=opt;
            this.proboxTop=[];//每个详细模块的偏移量
            this.opt.sidebar.find('li').eq(0).addClass('cur');
            $.each(self.opt.items,function(i){//循环将每个模块对应的偏移量存入数组中
                self.proboxTop[i]=self.opt.items.eq(i).offset().top;
            });

            this.init();//执行init()函数；核心函数
        }
        SideBar.prototype={
            init:function(){
                this.clickTo();
                this.scrollTo();

            },
            //点击跳转到某个模块
            clickTo:function(){
                var self=this;
                this.opt.sidebar.find('li').finish().click(function(){
                    var i=$(this).index();//获取点击的li对应的索引
                   $('html,body').finish().animate({
                       scrollTop:self.proboxTop[i]//移动到当前与当前索引相同的item上
                   },1000);
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
                       el.find('li').eq(i).addClass('cur').siblings().removeClass('cur');
                    }
                }
                //最后一张的判断
                if(self.proboxTop[self.proboxTop.length-1]<=scrollTop){
                   el.find('li').eq(self.proboxTop.length-1).addClass('cur').siblings().removeClass('cur');
                }
            }
        }
        var scroll=new SideBar({
            sidebar:$('#menu'),
            items:$("#content").find(".course_item")
        });
    });