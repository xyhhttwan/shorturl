<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="format-detection" content="telephone=no,email=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>短网址_缩短网址_短网址生成_短链接_在线生成短链接</title>
    <link rel="stylesheet" href="<%=basePath%>css/style.css">
</head>
<body class="page1">
<div class="container">
    <div class="menugroup clearfix">
        <div class="links menu">
					<span>
						<a href="<%=basePath%>html/api.html" target="_blank">API接口</a>
					</span>
            <%--<span>--%>
            <%--<a href="feedback.html" target="_blank">反馈有礼</a>--%>
            <%--</span>--%>
        </div>
    </div>

    <div class="logo">
        <img src="<%=basePath%>imgs/logow.png" alt="">
        <!--v2.0-->
        <div class="navbox">
            <div class="navbar clearfix">
                <a href="<%=basePath%>html/index.html" class="active">缩短网址</a>
                <%--<a href="http://stat.mrw.so/stat/login?fromType=1" target="_blank">数据分析 <img src="../imgs/new.png" alt="new" class="bedge"></a>--%>
            </div>
        </div>
        <!---2.0-->
    </div>
    <!--业务区-->
    <div class="content">
        <%--<div class="batch clearfix"><a href="batchsuo.html" target="_blank">批量缩短</a></div>--%>

        <div class="input">
            <input type="text" placeholder="请输入需要缩短的网址">
        </div>
        <div class="btnBox clearfix">
            <div class="btn">缩短网址</div>
        </div>
        <div class="result">
            <span>短网址：</span><span class="shortedNet"></span>
            <div class="copy" id="copy">复制</div>
            <!--<span class="copyTip">复制成功！</span>-->
        </div>

        <div class="warning">
            <p><span>!</span>您的源网址可能存在风险，生成的短网址可能无法在微信、QQ中打开</p>
        </div>
        <div class="data-analysis-area" style="display: none;">
            <!--<div><span>数据分析用户名：</span><span class="username">suo.im/12345</span></div>-->
            <div><span>数据分析密码：</span><span class="passwd"></span>
                <div class="copy2" id="copy2">复制</div>
            </div>
            <div class="pass-tip warning">
                <p><span>!</span>用户名及密码用于在<a class="linkto" data-href="http://stat.mrw.so/stat/login?fromType=2"
                                             target="_blank" style="color: #F5A623;">[数据分析]</a>板块查看短网址数据，该密码将不再展示，<i>请务必牢记</i>！
                </p>
            </div>
        </div>
    </div>

    <div class="footer" style="display: block;">
        <p class="license">
            ©2018 &nbsp;小兵工作室&nbsp;&nbsp; 网址缩短 - <a href="https://www.coolcoder.cc" target="_blank">陕ICP备17019421号</a>
        </p>
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/app.js"></script>
<script type="text/javascript" src="<%=basePath%>js/layer.js"></script>
<script type="text/javascript">
    var postUrl = '<%=basePath%>service/getShortUrl';
    $(".btn").on("click", function () {
        setTimeout(function () {
            layer.closeAll('loading');
        }, 3000);
    });
    //
    ;(function (callback) {

        $(".data-analysis-area").hide();
        $(".input input").focus(function () {
            $(this).removeClass("error");
        })
        $(".define input").focus(function () {
            $(this).parent().removeClass("error");
        })
        var obj = {
            "lenError": "网址过长，请重新输入",
            "noneError": "请输入需要缩短的网址",
            "InvalidError": "源网址格式错误，请重新输入",
            "occuError": "已占用,请重输"
        }

        function Errors(str, str1, callback) {
            var t = IsURL(str,2000);
            if (t === true) {
                //前端验证通过
                callback(str, str1, occupation, addNet);
            } else {
                $(".input input").addClass("error");
                layer.msg(obj[t]);
                return;
            }
        }

        //改变按钮点击样式，参数如下，第二个是类名
        clickChange('.btn', "clickbtn");

        //校验+安全等级
        $(document).on("click", ".btn", function (e) {
            $(".data-analysis-area").hide();
            $(".result").hide();
            $(".warning").hide();
            e.preventDefault();
            e.stopPropagation();
            $(".input input").blur();
            $(".define").removeClass("error");
            var str = $(".input input").val(),
                str1 = $(".define input").val();

            Errors(str, str1, callback);

        })
        //生成的网址不安全时  弹出警告
        //param1 要显示的警告类名  param2 真假值   真值显示，假值跳过
//			showWarning('.warning',1);//v2.0
        $(".copy").on("click", function () {
            jsCopy(".shortedNet")
        });
        $(".copy2").on("click", function () {
            jsCopy(".data-analysis-area .passwd")
        });

        function jsCopy(ele) {
            var tmpTxt = $(ele).text();

            var aux = document.createElement("input");
            aux.setAttribute("value", tmpTxt);
            document.body.appendChild(aux);
            aux.select();

            if (!document.execCommand("copy")) {
                document.body.removeChild(aux);
                layer.msg("复制失败,请手动选中复制！")
            } else {
                document.body.removeChild(aux);
                layer.msg("复制成功!")
            }
        }

        function occupation(msg) {
            //$(".define input").addClass("error");
            layer.msg(msg);
        }

        function addNet(result) {
            $(".result").show().find(".shortedNet").text(result);
        }

        function warn(result) {
            $(".result").show().find(".shortedNet").text(result);
        }
    })(oper);

    function showWarning(ele1, ele2, str) {
        $(ele1).show();
        if (str) {
            $(ele2).html("<span>!</span>" + str);
        }
    }

    function oper(lnk_val, def_val, occupation, addNet) {
        //lnk_val 原网址   def_val是自定义短网址     addNet显示缩短后的网址
        //占用错误   调用这个方法
        if (def_val != '') {
            //alert("自定义"+def_val);
            if (!/^[A-Za-z0-9_]{0,10}$/.test(def_val)) {
                layer.msg("请输入数字，字母，下划线");
                $(".define").addClass("error");
                return;
            }
        }
        var tinurl = "";
        var pwd = "";
        var msg = "";
        //这里进行后端操作
        $.post(postUrl, {longUrl: lnk_val, defurl: def_val}, function (data) {
            if (data.result == false) {
                occupation(data.err_msg);//提示错误信息
            } else {
                tinurl = data.shortUrl;
               // pwd = data.pwd;
               //  msg = data.retMsg;
                //$(".input input").val(tinurl);//显示生成的短网址
                //操作成功，调用这个方法  参数是生成后的短网址回显短网址信息
                if (tinurl != "") {
                    addNet(tinurl);
                }
                //操作成功，如果有密码则回显密码
                if (pwd != "" & pwd != " " & pwd != null) {
                    $(".data-analysis-area").show().find(".passwd").text(pwd);
                    //给数据分析链接加短网址和密码
                    $(".linkto").attr("href", $(".linkto").attr("data-href") + "&shortUrl=" + tinurl + "&shortUrlPwd=" + pwd);
                }
                if (msg != "" && msg != null) {
                    layer.msg(msg);
                }
                //不安全警告
                if (data.tip != "" && data.tip != null && data.tip == 1) {
                    showWarning('.warning', '.warning p', "");
                }
            }
        }, 'json');
    };

    //v3.2
    //移动端点击显示
    $(".rotatebox").on("click", function () {
        $(".codepng").show().addClass("scale1").on("click", function () {
            $(this).hide();
        });
    })

    //2018-1-12 @ljy-s
    $(window).ready(function () {
        var s = $(".icon");
        setInterval(function () {
            s.removeClass("animate");
        }, 2000)

        setInterval(function () {
            s.addClass("animate");
        }, 3000)

    })
    //2018-1-12 @ljy-e
</script>

</body>
</html>