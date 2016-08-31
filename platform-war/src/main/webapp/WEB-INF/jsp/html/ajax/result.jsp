<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
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
<c:if test="${ empty order}">
    <tr>
        <td width="40%" colspan="2">没有查询到该订单信息,如有疑问请联系客服</td>

    </tr>
</c:if>