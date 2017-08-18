$(document).ready(function () {
var stars = 800;
var $stars = $('.stars');
var r = 800;
for (var i = 0; i < stars; i++) {
    if (window.CP.shouldStopExecution(1)) {
        break;
    }
    var $star = $('<div/>').addClass('star');
    $stars.append($star);
}
window.CP.exitedLoop(1);
$('.star').each(function () {
    var cur = $(this);
    var s = 0.2 + Math.random() * 1;
    var curR = r + Math.random() * 300;
    cur.css({
        transformOrigin: '0 0 ' + curR + 'px',
        transform: ' translate3d(0,0,-' + curR + 'px) rotateY(' + Math.random() * 360 + 'deg) rotateX(' + Math.random() * -50 + 'deg) scale(' + s + ',' + s + ')'
    });
});
if(isIE()){
	location.href="/upgrade.jsp";
}
});
//判断是否是IE浏览器
function isIE() { //ie?
	 if (!!window.ActiveXObject || "ActiveXObject" in window){
		 
		 return true;
	 }else{
		 return false;
		 }
	 }

