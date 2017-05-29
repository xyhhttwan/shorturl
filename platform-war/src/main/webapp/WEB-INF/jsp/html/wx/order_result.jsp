<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh">
<html>
<head>
    <title>越海物流-订单查询结果</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=basePath%>css/wx/css.css"/>
</head>
<body>
<div class="layout">
    <div class="o-title">物流跟踪</div>
    <div class="order-list">
        <ul class="kd-list">

            <c:if test="${order.status==1}">
                <li class="cur">
                    <span class="kd-ico"></span>
                    您的货物已经到达[${order.mto}]感谢您选择越海物流，欢迎您再次光临！
                    <p><fmt:formatDate value="${orderCloseDate}" pattern="yyyy-MM-dd HH:mm:ss"/></p>

                </li>
            </c:if>

            <c:forEach items="${list}" var="list" varStatus="index">
                <c:choose>
                    <c:when test="${order.status==1}">
                        <li>
                            <span class="kd-ico"></span>
                                ${list.arrivePosition}
                            <p><fmt:formatDate value="${list.arriveTime}"
                                               pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${index.first}">
                            <li class="cur">
                                <span class="kd-ico"></span>
                                    ${list.arrivePosition}
                                <p><fmt:formatDate value="${list.arriveTime}"
                                                   pattern="yyyy-MM-dd HH:mm:ss"/></p>
                            </li>
                        </c:if>
                        <c:if test="${not index.first }">
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${not empty order}">
                <li <c:if test="${empty list }"> class="cur" </c:if>>
                    <span class="kd-ico"></span>
                    收件起始地点,${order.mfrom}
                    <p><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                    <span class="kd-ico2"></span>
                </li>
            </c:if>
            <c:if test="${ empty order &&  not empty orderId}">
                    没有查询到该订单信息,如有疑问请联系客服
            </c:if>
        </ul>
    </div>
</div>
<div class="footer">Copyright by 版权所有 绍兴越海物流有限公司</div>
</body>
</html>