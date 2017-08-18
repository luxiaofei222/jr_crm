;(function(win){
	function slide () {
		var self = this;
		self.config = {
			main : "#flash",
			img : ".flash-img a",
			nav : ".flash-nav i",
			left : ".flash-left",
			right : ".flash-right",
			cs : "flash-on",
			speed : 3000,
			delay : 1000,
			effect : 1,	//0：无特效，1：渐隐渐现
			page : 0,
			auto : true,
			timerId : null,
			callBack : null
		}
		$.extend(self.config,arguments[0]);
		return self;
	}
	slide.prototype = {
		init : function () {
			var config = this.config;
			this.config.len = $(config.main).find(config.img).length - 1;
			config.auto && this._hover(config);
			this._click(config);
			this._nav(config);
		},
		_hover : function (config) {
			var self = this;
			$(config.main).hover(function(){
				config.timerId && clearInterval(config.timerId);
			},function(){
				config.timerId = setInterval(function(){
					self._right(config);
				},config.speed);
			}).trigger("mouseout");
		},
		_right : function (config) {
			config.page == config.len ? config.page = 0 : config.page++;
			this._slide(config);
		},
		_left : function (config) {
			config.page == 0 ? config.page = config.len : config.page--;
			this._slide(config);
		},
		_click : function (config) {
			var self = this;
			$(config.main).on("click",config.left,function(){
				self._left(config);
			});
			$(config.main).on("click",config.right,function(){
				self._right(config);
			});
		},
		_nav : function (config) {
			var self = this;
			$(config.main).on("mouseover",config.nav,function(){
				config.page = $(config.main).find(config.nav).index(this);
				self._slide(config);
			});
		},
		_slide : function (config) {
			config.effect == 1 ? $(config.main).find(config.img).eq(config.page).stop(true,true).fadeIn(config.delay).siblings(config.img).fadeOut(config.delay) : $(config.main).find(config.img).eq(config.page).css("display","block").siblings(config.img).css("display","none");
			$(config.main).find(config.nav+"."+config.cs).removeClass(config.cs);
			$(config.main).find(config.nav).eq(config.page).addClass(config.cs);
			config.callBack && config.callBack(config);
		}
	}
	win.lmj = win.lmj || {};
	win.lmj.slide = slide;
})(window);