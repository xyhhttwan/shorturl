<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<div class="header">
    <div class="w1200">
        <ul class="nav-list">
            <c:forEach items="${navigation}"  var="navigation" varStatus="tt">
            <li <c:if test="${select eq tt.index}">class="cur" </c:if>>
                <a href="<%=basePath%>${navigation.url}">${navigation.name}</a></li>
            </c:forEach>
        </ul>
        <img src="<%=basePath%>images/logo.png" alt="越海物流" class="logo"/>
    </div>
</div>