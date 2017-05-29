<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>越海物流</title>
    <meta name="Keywords" content="越海物流,越海,物流,快递,绍兴越海物流有限公司,绍兴越海,绍兴物流" />
    <meta name="Description" content="绍兴越海物流有限公司、绍兴越海物流配送、绍兴物流" />

    <link rel="stylesheet" href="<%=basePath%>css/css.css"/>
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/main.js"></script>
    <script src="<%=basePath%>js/unslider.js"></script>
    <script>
        $(function () {
            var slider = $('#banner').unslider({
                speed: 500,
                delay: 3000,
                keys: true,
                dots: true
            });
        });
    </script>

</head>
<body style="background-color: #eee;">
<div class="layout">
    <jsp:include page="head.jsp"/>
    <div class="banner" id="banner">

        <ul>
            <c:forEach var="newTitle" items="${newTitle}">
                <li><a href="javascript:;"><img src="<%=basePath%>${newTitle.pic}" alt=""/></a></li>
            </c:forEach>
        </ul>
    </div>
    <div class="main-box w1200">
        <div class="index-search">
            <textarea id="orderId" name="orderId" class="index-text" placeholder="请输入您要查询的14位订单号"></textarea>
            <button class="index-btn">查询</button>
        </div>
        <div class="m-box w34">
            <div class="m-box-tit">最新资讯 <span>/&nbsp;&nbsp;Latest News</span></div>
            <div class="m-box-container">
                <a href="<%=basePath%>html/news/${newsPic.id}.html">
                    <p class="news-tit">${newsPic.title}</p>
                    <div class="news-text">
                        <c:if test="${newsPic.newsType==0}">
                        <span class="pic"><img src="<%=basePath%>${newsPic.pic}" alt="${newsPic.title}"/></span>
                        </c:if>
                        <c:choose>
                            <c:when test="${fn:length(newsPic.content) > 48}">
                                <c:out value="${fn:substring(newsPic.content, 0, 48)}"/>...
                            </c:when>
                            <c:otherwise>
                                <c:out value="${newsPic.content}"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </a>
                <p class="index-more"><a href="<%=basePath%>html/news_list.html" class="index-ico">查看更多</a></p>
            </div>
        </div>
        <div class="m-box fd w32">
            <div class="m-box-tit bg-yellow">公司简介 <span>/&nbsp;&nbsp;Company Profile</span></div>
            <div class="m-box-container">
                <p class="tx2">
                    <c:choose>
                        <c:when test="${fn:length(common.companyDes) > 90}">
                            <c:out value="${fn:substring(common.companyDes, 0, 90)}"/>...
                        </c:when>
                        <c:otherwise>
                            <c:out value="${common.companyDes}"/>
                        </c:otherwise>
                    </c:choose>
                </p>
                <p class="index-more"><a href="<%=basePath%>html/about_us.html" class="index-ico">查看更多</a></p>
            </div>
        </div>
        <div class="m-box w30">
            <div class="m-box-tit">服务中心 <span>/&nbsp;&nbsp;Service Centre</span></div>
            <div class="m-box-container">
                <img src="<%=basePath%>images/telephone.jpg" alt="服务中心"/>
                <p>7*24小时竭诚为您服务，欢迎您的致电！</p>
            </div>
        </div>
    </div>
    <div class="push"></div>
</div>
<jsp:include page="footer.jsp"/>
<div class="yh-code">微信<br/>关注
    <div class="yh-code-box"><img src="<%=basePath%>images/c-code.png" alt="关注我们"/></div>
</div>
</body>
</html>
