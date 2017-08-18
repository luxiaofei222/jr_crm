!
function(a) {
	function b(a) {
		$.ajax({
			type: a.type || "post",
			scriptCharset: a.scriptCharset || "gb2312",
			url: a.url || "",
			data: a.data || null,
			dataType: a.dataType || "json",
			success: a.success || null,
			error: a.error || null,
			async: a.async || !0,
			cache: a.cache || !1
		})
	}
	a.lmj = a.lmj || {}, a.lmj.ajax = b
}(window), function(a) {
	function b() {
		var a = this;
		return a.config = {
			id: null,
			stratDate: null,
			stopDate: "2016/12/31",
			replaceStr: null,
			numberLen: null
		}, $.extend(a.config, arguments[0]), c(a.config), a
	}
	function c(a) {
		var b = new Date(a.stopDate).getTime() - (a.stratDate ? new Date(a.stratDate).getTime() : (new Date).getTime());
		0 > b && (b = 0), b = Math.ceil(b / 864e5), a.numberLen && (b = d(b, a.numberLen)), a.replaceStr && (b = b.toString().replace(/(\d)/g, a.replaceStr)), a.id ? $(a.id).html(b) : document.write(b)
	}
	function d(a, b) {
		var c, d = a.toString(),
			e = d.length,
			f = "";
		if (b > e) {
			for (c = 0; b - e > c; c++) f += "0";
			d = f + d
		}
		return d
	}
	a.lmj = a.lmj || {}, a.lmj.down_day = b
}(window);
var lmj_base = {
	getStr: function(a, b) {
		var c = "";
		switch (b) {
		case "val":
			c = $.trim(a.val());
			break;
		case "text":
			c = $.trim(a.text());
			break;
		case "html":
			c = $.trim(a.html())
		}
		return c
	},
	formateDate: function(a, b) {
		var c = "object" == typeof a ? a : "string" == typeof a ? new Date(a) : new Date(parseInt(a.toString().replace(/\D+/g, ""))),
			d = c.getFullYear(),
			e = d.toString().substring(2),
			f = c.getMonth() + 1,
			g = c.getDate(),
			h = c.getHours(),
			i = c.getMinutes(),
			j = c.getSeconds();
		return f = 10 > f ? "0" + f : f, g = 10 > g ? "0" + g : g, h = 10 > h ? "0" + h : h, i = 10 > i ? "0" + i : i, j = 10 > j ? "0" + j : j, b = b || "yyyy-mm-dd", b.replace(/yyyy/gi, d).replace(/yy/gi, e).replace(/mm/gi, f).replace(/dd/gi, g).replace(/hh/gi, h).replace(/min/gi, i).replace(/sec/gi, j)
	},
	strLen: function(a) {
		var b, c;
		for (c = 0, b = 0; b < a.length; b++) c += a.charCodeAt(b) >= 0 && a.charCodeAt(b) <= 255 ? 1 : 2;
		return c
	},
	AddFavorite: function() {
		var a = arguments[0] || window.location.href,
			b = arguments[1] || document.title;
		try {
			window.external.addFavorite(a, b)
		} catch (c) {
			try {
				window.sidebar.addPanel(b, a, "")
			} catch (c) {
				alert("加入收藏失败，请使用Ctrl+D进行添加")
			}
		}
	},
	test: function(a, b) {
		switch (b) {
		case "phone":
			return /^1[34578]\d{9}$/.test(a);
		case "tel":
			return /^^(\d{3,4}-)\d{7,8}$/.test(a);
		case "id":
			return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(a);
		case "num":
			return /^\d+$/.test(a);
		case "ab":
			return /^[A-Za-z]+$/.test(a);
		case "a1":
			return /^[A-Za-z0-9]+$/.test(a);
		case "a1_":
			return /^\w$/.test(a);
		case "china":
			return /^[\u4e00-\u9fa5]{0,}$/.test(a);
		case "email":
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(a);
		case "url":
			return /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/.test(a);
		case "day":
			return /^\d{4}[-\/]([1 - 9] | [1][0 - 2])[ - \/]([1-9]|[1-2][0-9]|[3][01])$/.test(a);
		default:
			return b.test(a)
		}
	},
	Parameters: function() {
		var a, b, c, d = arguments[0] || window.location.search,
			e = {};
		if (0 == d.indexOf("?")) for (d = d.substr(1), a = -1 == d.indexOf("&") ? [d] : d.split("&"), b = 0; b < a.length; b++) c = a[b].split("="), e[c[0]] = c[1];
		return e
	},
	iphide: function(a) {
		var b = a;
		return b = b.substring(0, b.lastIndexOf(".")), b = b.substring(0, b.lastIndexOf(".")), b += ".**.**"
	},
	getChecked: function(a) {
		return a.prop("checked") ? arguments[1] || 1 : arguments[2] || 0
	},
	scriptReady: function(a, b) {
		var c = "object" == typeof a ? a : window.document.getElementById(a);
		c.readyState ? c.onreadystatechange = function() {
			(c.attachEvent ? "complete" === c.readyState : "loading" !== c.readyState) && (c.onreadystatechange = null, b && b())
		} : c.onload = function() {
			b && b()
		}
	},
	getScript: function(a, b, c) {
		var d, e = window.document.createElement("script");
		if (e.type = "text/javascript", e.charset = "utf-8", e.defer = "defer", e.async = "async", e.src = a, b) for (d in b) e[d] = b[d];
		window.document.getElementsByTagName("head")[0].appendChild(e), c && this.scriptReady(e, c)
	}
};