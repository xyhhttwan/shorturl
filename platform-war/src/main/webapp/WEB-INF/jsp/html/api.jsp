<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta name="keywords" content="短网址,短网址生成,缩短网址,短链接,短链接生成,网址压缩,短地址,域名伪装,域名转向,短链接生成器">
    <meta name="description" content="短链服务 ">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta name="format-detection" content="telephone=no,email=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Language" content="zh-CN">
    <title>短网址_缩短网址_短网址生成_短链接_在线生成短链接</title>
    <link rel="stylesheet" href="<%=basePath%>css/style.css">
<body class="page2 Api">
<!-- 公共头部开始 -->
<header>
</header>
<!-- 公共头部结束 -->

<div class="container">
    <div class="logo apilogo">
        <img src="<%=basePath%>imgs/logo.png" alt="">
    </div>
    <!--业务区-->
    <div class="content api">
        <h4>欢迎使用API接口，生成稳定、可靠的短网址!</h4>
        <%--<div class="apiItem">--%>
            <%--<h5>TXT格式短网址API接口</h5>--%>
            <%--<p><span>接口：</span><span>http://coolcoder.cc/service/getShortUrl?longUrl=urlencode('要缩短的网址')</span></p>--%>
            <%--<p><span>例如：</span><span>http://coolcoder.cc/service/getShortUrl?url=http%3A%2F%2Fwww.baidu.com</span></p>--%>
            <%--<p><span>返回：</span><span>http://mrw.so/baidu</span></p>--%>
        <%--</div>--%>

        <div class="apiItem">
            <h5>JSON格式短网址API接口</h5>
            <p><span>说明：</span><span>format为json</span></p>
            <p><span>例如：</span><span>https://link.coolcoder.cc/service/getShortUrl=http%3A%2F%2Fwww.baidu.com</span></p>
            <p><span>返回：</span><span>
                {
                "errMsg":"",
                "longuUrl":"http://www.baidu.com",
                "result":true,
                "shortUrl":"https://s.coolcoder.cc?key=pzd9"}
            </span></p>
        </div>


    </div>
    <img class="txt" src="<%=basePath%>imgs/text1.png" alt="">
    <div class="footer" style="display: block;">

        <p class="license">
            ©2018 &nbsp;小兵工作室&nbsp;&nbsp; 网址缩短 - <a href="https://www.coolcoder.cc" target="_blank">陕ICP备17019421号</a>
        </p>
    </div>
</div>
<!-- 公共底部 -->
</body>
</html>