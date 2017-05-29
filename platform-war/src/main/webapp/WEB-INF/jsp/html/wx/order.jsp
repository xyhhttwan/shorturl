<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <title>越海物流-微信订单查询</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=basePath%>css/wx/css.css"/>
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>

<%--<script type="text/javascript">--%>
        <%--// 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器--%>
        <%--var useragent = navigator.userAgent;--%>
        <%--if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {--%>
            <%--// 这里警告框会阻塞当前页面继续加载--%>
            <%--// 以下代码是用javascript强行关闭当前页面--%>
            <%--var opened = window.open('http://www.baidu.com', '_self');--%>
            <%--opened.opener = "";--%>
            <%--opened.close();--%>
        <%--}--%>
    <%--</script>--%>


</head>
<body class="bg01">
<div class="layout">
    <div class="o-logo"><span>越海物流订单查询</span></div>
    <div class="o-search">
        <input type="text" name="orderId" id="orderId" placeholder="输入14位订单号查询订单" class="o-input"/>
        <button type="button" class="o-btn">查询</button>
        <div id="error_msg" style="color: #fff3f3;font-size: 15px;padding-top: 5px; float: right;display: none">请输入订单号查询!</div>
    </div>
    <div class="push"></div>
</div>
<div class="footer">Copyright by 版权所有 绍兴越海物流有限公司</div>
<script>
$(function () {
        $(".o-btn").click(function () {
            var orderId = $("#orderId").val();
            if(orderId==""){
                $("#error_msg").show();
                $("#orderId").focus();
                return;
            }else{
                $("#error_msg").hide();
            }
            window.location.href="<%=basePath%>html/wx/order/"+orderId;
        });
})
</script>

</body>
</html>
