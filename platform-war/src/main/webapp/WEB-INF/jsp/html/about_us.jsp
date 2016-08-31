<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>越海物流-关于我们</title>
    <link rel="stylesheet" href="<%=basePath%>css/css.css"/>
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/main.js"></script>
</head>
<div class="layout">
    <jsp:include page="head.jsp"/>
    <div class="s-banner">
        <img src="<%=basePath%>images/banner3.jpg" alt=""/>
    </div>
    <div class="w1200">
        <div class="position">关于我们 <span>/ AboutUs</span></div>
        <div class="about-us">
            <div class="about-left">
                <p class="about-tit">越海物流有限责任公司</p>
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
            </div>
            <div class="about-right">
                <p class="ar-tit"><span>公司简介</span></p>
                <div class="about-container">
                    ${common.companyDes}
            </div>
        </div>
    </div>
    <div class="push"></div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
