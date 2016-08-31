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
    <title>越海物流-新闻详情-${news.title}</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/splitPage/kkpager_green.css"/>

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
        <div class="position">新闻资讯 <span>/ Latest News</span></div>
        <p class="news-tit new-con">${news.title}</p>
        <p class="news-time">管理员发布于：<fmt:formatDate value="${news.postTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
        <div class="news-container">
            ${news.content}

        </div>
    </div>
    <div class="push"></div>
</div>

<jsp:include page="footer.jsp"/>
</body>
<script type="text/javascript" src="<%=basePath%>js/page/kkpager.js"></script>
<script>
    $(function () {
        kkpager.generPageHtml({
            mode: 'click',//默认值是link，可选link或者click
            click: function (n) {
                getData(n);
                kkpager.selectPage(n);
                return false;
            }
        }, true);
        getData(1);

    });
    function getData(n) {
        $.ajax(
                {
                    url: "<%=basePath%>html/getNewsDataList",
                    data: {
                        pno: n,
                        rows:4
                    },
                    async: false,
                    success: function (data) {
                        var html = data;
                        $("#pageList").empty();
                        $("#pageList").html(data);
                        kkpager.total = $("#totalPage").val();
                        kkpager.totalRecords = $("#totalRecords").val();
                        return false;
                    }
                })
    }
</script>
</html>
