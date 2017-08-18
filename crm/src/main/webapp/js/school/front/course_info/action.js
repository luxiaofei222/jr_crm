;(function(){
	$("[node-type]").each(function(i,n) {
		switch($(this).attr("node-type")) {
			case "menu-tab":
				menu_tab(this);
				break;
			case "menu-mouse":
				menu_mouse(this);
				break;
			case "menu-hover":
				menu_hover(this);
				break;
			case "menu-slide":
				menu_slide(this);
				break;
			case "menu-sliding":
				menu_sliding(this);
				break;
			case "menu-rolling":
				menu_rolling(this);
				break;
			default:
				
		}
	})
	function menu_tab(obj) {
		var t = eval("("+ $(obj).data("type") +")"),
			ev = t.ev || "mouseover",
			cs = t.cs || "block",
			cs1 = t.cs1 || "",
			cs2 = t.cs2 || "",
			tab = t.tab || obj,
			tabList = t.tlist,
			con = t.con || tab,
			conList = t.clist,
			fun = t.fun;
		var config = {
				ev : ev,
				cs : cs,
				cs1 : cs1,
				cs2 : cs2,
				tab : tab,
				tabList : tabList,
				con : con,
				conList : conList,
				fun : fun
			}
		$(tab).on(ev,tabList,function(){
			var $this = $(this);
			$(tab).find(tabList).removeClass(cs1).addClass(cs2);
			$this.addClass(cs1).removeClass(cs2);
			$(con).find(conList).css("display","none");
			$(con).find(conList).eq($(tab).find(tabList).index($this)).css("display",cs);
			fun && eval(fun)(config,$this);
		})
	}
	function menu_mouse(obj) {
		var t = eval("("+ $(obj).data("type") +")"),
			ev = t.ev || "mouseover",
			cs1 = t.cs1 || "",
			cs2 = t.cs2 || "",
			tab = t.tab || obj,
			tabList = t.tlist;
		$(tab).on(ev,tabList,function(){
			var $this = $(this);
			$(tab).find(tabList).removeClass(cs1).addClass(cs2);
			$this.addClass(cs1).removeClass(cs2);
		})
	}
	function menu_hover(obj) {
		var t = eval("("+ $(obj).data("type") +")"),
			cs1 = t.cs1 || "",
			cs2 = t.cs2 || "",
			that = t.tlist || obj;
		$(that).hover(function(){
			$(this).removeClass(cs2).addClass(cs1);
		},function(){
			$(this).removeClass(cs1).addClass(cs2);
		})
	}
	function menu_slide(obj) {
		var ev = eval("("+ $(obj).data("type") +")");
		ev.main = ev.main || obj;
		new lmj.slide(ev).init();
	}
	function menu_sliding(obj) {
		var ev = eval("("+ $(obj).data("type") +")");
		ev.main = ev.main || obj;
		new lmj.sliding(ev).init();
	}
	function menu_rolling(obj) {
		var ev = eval("("+ $(obj).data("type") +")");
		ev.main = ev.main || obj;
		new lmj.rolling(ev).init();
	}
})();

// 课程列表收起展开
$(function(){
	$(".expanded").click(function(){
		 $(this).parent("li").toggleClass("hover");
	})
})
