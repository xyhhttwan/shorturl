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
    <title>越海物流-货物跟踪</title>
    <meta name="Keywords" content="越海物流,越海,物流,快递,绍兴越海物流有限公司,绍兴越海,绍兴物流" />
    <meta name="Description" content="绍兴越海物流有限公司、绍兴越海物流配送、绍兴物流" />
</head>
<link rel="stylesheet" href="<%=basePath%>css/css.css"/>
<script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
<script src="<%=basePath%>js/main.js"></script>
</head>
<body>
<div class="layout">
    <jsp:include page="head.jsp"/>

    <div class="s-banner">
        <img src="<%=basePath%>images/banner3.jpg" alt=""/>
    </div>
    <div class="w1200">
        <div class="position">物流跟踪 <span>/ Logistics Tracking</span></div>
        <div class="log-search">
            <p class="log-search-tit">物流信息管理</p>
            <textarea class="log-text" id="orderId" name="orderId" placeholder="请输入您要查询的14位订单号">${orderId}</textarea>
            <p>
                <button class="log-btn" id="search">查询</button>
            </p>
        </div>

        <table class="log-table">
            <thead>
            <tr>
                <td>时间</td>
                <td>到达城市/操作记录</td>
            </tr>
            </thead>
            <tbody id="result">
            <tr>

                <c:if test="${order.status==1}">

                    <td width="6%"><fmt:formatDate value="${orderCloseDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td width="30%">您的货物已经到达[${order.mto}]感谢您选择越海物流，欢迎您再次光临！</td>
                </c:if>

            </tr>

            <c:forEach items="${list}" var="list" varStatus="index">
                <tr>
                    <c:choose>
                        <c:when test="${order.status==1}">
                            <td width="6%"><fmt:formatDate value="${list.arriveTime}"
                                                           pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td width="30%">${list.arrivePosition}</td>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${index.first}">

                                <td width="6%"><fmt:formatDate value="${list.arriveTime}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td width="30%">${list.arrivePosition}</td>

                            </c:if>
                            <c:if test="${not index.first }">
                                <td width="6%"><fmt:formatDate value="${list.arriveTime}"
                                                               pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td width="30%">${list.arrivePosition}</td>
                            </c:if>
                        </c:otherwise>
                    </c:choose>

                </tr>
            </c:forEach>
            <c:if test="${not empty order}">
                <tr>
                    <td width="6%"><fmt:formatDate value="${order.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td width="30%">收件起始地点,${order.mfrom}</td>
                </tr>
            </c:if>
            <c:if test="${ empty order &&  not empty orderId}">
                <tr>
                    <td width="40%" colspan="2">没有查询到该订单信息,如有疑问请联系客服</td>

                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="push"></div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
