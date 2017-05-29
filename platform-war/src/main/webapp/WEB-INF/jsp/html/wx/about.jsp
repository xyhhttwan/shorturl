<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<html>
<head>
    <title>越海物流-关于我们</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=basePath%>css/wx/css.css"/>
</head>
<body>
<div class="layout">
    <div class="o-title">关于我们</div>
    <div class="order-list">
        <div class="about-box">
            <p class="about-tit1">公司名称</p>
            <ul class="about-ul">
                <li>绍兴高新区越海货物运输队</li>
            </ul>
            <p class="about-tit1">联系方式</p>
            <ul class="about-ul">
                <li>发货热线：${common.phoneNum}</li>
                <li>查货热线：${common.telPhone}</li>
                <li>客服热线：${common.complaintsNum}</li>
            </ul>
            <p class="about-tit1">公司地址</p>
            <ul class="about-ul">
                <li>${common.address}</li>
            </ul>
            <p class="about-tit1">公司简介</p>
            <div class="about-container">
                ${common.companyDes}
            </div>
        </div>

    </div>
</div>
<div class="footer">Copyright by 版权所有 绍兴越海物流有限公司</div>
</body>
</html>