<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:forEach var="list" items="${page.list}">
    <div class="news-list" >
        <a href="<%=basePath%>html/news/${list.id}" target="_blank">
            <p class="news-tit">${list.title}</p>
            <div class="news-text">
                <c:if test="${not empty list.pic}">
                    <span class="pic"><img src="<%=basePath%>${list.pic}" alt="${list.title}"/></span>
                </c:if>
                <c:choose>
                    <c:when test="${fn:length(list.content) > 78}">
                        &nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${fn:substring(list.content, 0, 78)}"/>...
                    </c:when>
                    <c:otherwise>
                        &nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${list.content}"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </a>
    </div>
</c:forEach>

<input type="hidden" name="totalPage" id="totalPage" value="${page.pages}">
<input type="hidden" name="totalRecords" id="totalRecords" value="${page.total}">