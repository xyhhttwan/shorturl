var clickChange = function(ele, className) {
    $(document).on("mousedown touchstart", ele, function(e) {
        $(this).addClass(className);
    });
    $(document).on("mouseup touchend", ele, function(e) {

        $(this).removeClass(className);
    })
}

//地址校验规则
function IsURL(str_url,len) {
    if(str_url.length==0){
        return "noneError";
    }else if(str_url.length >= len){
        return "lenError";
    }else{
        var strRegex =/^((https|http|ftp|rtsp):\/\/)([a-zA-Z0-9]|\.|\_|\/)./g;
        var re = new RegExp(strRegex);
        if(re.test(str_url)) {
            return(true);
        } else {
            return "InvalidError";
        }
    }
    /**
     else{
		var strRegex =/^((https|http|ftp|rtsp):\/\/)([a-zA-Z0-9]|\.|\_|\/)./g;
		var re = new RegExp(strRegex);
		if(re.test(str_url)) {
			return(true);
		} else {
			return "InvalidError";
		}
	}
     */
}
//跳回首页
$(".logo img").on("click",function(){
    location.href = "./index.html";
})
$('.define input').focus(function(){
    $(this).removeClass("error");
})

var windheight = $(window).height();  /*未唤起键盘时当前窗口高度*/
$(window).resize(function(){
    var docheight = $(window).height();  /*唤起键盘时当前窗口高度*/
    if(docheight < windheight){            /*当唤起键盘高度小于未唤起键盘高度时执行*/
        $(".footer").css("display","none");
    }else{
        $(".footer").css("display","block");
    }
})