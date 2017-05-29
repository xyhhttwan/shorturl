<%@ page import="java.util.Date" %>
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
    <title>越海物流-在线下单</title>
    <meta name="Keywords" content="越海物流,越海,物流,快递,绍兴越海物流有限公司,绍兴越海,绍兴物流" />
    <meta name="Description" content="绍兴越海物流有限公司、绍兴越海物流配送、绍兴物流" />
    <meta name="baidu-site-verification" content="np2igrCvss" />

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
        <div class="position">我要寄件 <span>/ Send Something</span></div>
        <div class="log-search">
            <p class="log-search-tit">填写寄件信息</p>
            <ul class="yh-order">
                <li><span class="txt">客户公司：</span><input  maxlength="50" id="customer" name="customer" placeholder="请输入客户公司" type="text" class="text-1"/></li>
                <li><span class="txt">姓名：</span><input maxlength="16" id="linkMan" name="linkMan" placeholder="请输入联系人姓名" type="text" class="text-1"/></li>
                <li><span class="txt">电话：</span><input maxlength="11" id="phoneNum" name="phoneNum" placeholder="请输入联系人电话"  type="text" class="text-1"/></li>
                <li><span class="txt">始发地：</span><input maxlength="32" id="mFrom" name="mFrom"  placeholder="请输入发货地" type="text" class="text-1"/></li>
                <li><span class="txt">目的地：</span><input maxlength="32" id="mTo" name="mTo" type="text"  placeholder="请输入目的地" class="text-1"/></li>
                <li><span class="txt">发货内容：</span><input maxlength="50" id="content" name="content" placeholder="发货内容" type="text" class="text-1"/></li>
                <li><span class="txt">备注信息：</span><textarea  id="remark" name="remark"   class="text-2"></textarea></li>
                <li><span class="txt">验证码：</span><input id="code" maxlength="4" name="code" type="text" class="text-1 w200"/><span class="yh-code-img">
                    <img src="<%=basePath%>/onlineOrderVerifyCode?t=<%=new Date()%>"id="verify_img_code" href="javascript:void(0);" alt="点击更换验证码"/></span></li>
                <li><span class="txt"></span><button type="submit" id="add_online_order" class="order-btn">确认下单</button></li>
            </ul>
        </div>
    </div>
    <div class="push"></div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
