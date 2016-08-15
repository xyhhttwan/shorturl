<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>
<style type="text/css">
	.hovertree-tracklist ul li {
		list-style: none;
	}

	.hovertree-trackrcol {
		max-width: 900px;
		border: 1px solid #eee;
	}

	.hovertree-tracklist {
		margin: 20px;
		padding-left: 5px;
		position: relative;
	}

	.hovertree-tracklist li {
		position: relative;
		padding: 9px 0 0 25px;
		line-height: 18px;
		border-left: 1px solid #d9d9d9;
		color: #999;
	}

	.hovertree-tracklist li.first {
		color: red;
		padding-top: 0;
		border-left-color: #fff;
	}

	.hovertree-tracklist li .node-icon {
		position: absolute;
		left: -6px;
		top: 50%;
		width: 11px;
		height: 11px;
		background: url(http://hovertree.com/texiao/css/25/img/order-icons.png) -21px -72px no-repeat;
	}

	.hovertree-tracklist li.first .node-icon {
		background-position: 0 -72px;
	}

	.hovertree-tracklist li .time {
		margin-right: 20px;
		position: relative;
		top: 4px;
		display: inline-block;
		vertical-align: middle;
	}

	.hovertree-tracklist li .txt {
		max-width: 600px;
		position: relative;
		top: 4px;
		display: inline-block;
		vertical-align: middle;
	}

	.hovertree-tracklist li.first .time {
		margin-right: 20px;
	}

	.hovertree-tracklist li.first .txt {
		max-width: 600px;
	}
	.hovertreeinfo{margin-top:10px;}
	.hovertreeinfo a{color:blue;}


</style>
<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
	<form id="dataform" method="post">

		<div class="hovertree-trackrcol">
			<div class="hovertree-tracklist">
				<c:if test="${order.status==1}">
					<li class="first">
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate value="${orderCloseDate}"   pattern="yyyy-MM-dd HH:mm:ss" /></span>
						<span class="txt">您的货物已经到达[${order.mto}]感谢您选择越海物流，欢迎您再次光临！</span>
					</li>
				</c:if>
				<c:forEach  items="${list}" var="list" varStatus="index">
					<c:choose>
						<c:when test="${order.status==1}">
							<li>
								<i class="node-icon"></i>
								<span class="time"><fmt:formatDate value="${list.arriveTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></span>
								<span class="txt">到达${list.arrivePosition}</span>
							</li>
						</c:when>
						<c:otherwise>
							<c:if test="${index.first}">
								<li class="first">
									<i class="node-icon"></i>
									<span class="time"><fmt:formatDate value="${list.arriveTime}"   pattern="yyyy-MM-dd HH:mm:ss" /></span>
									<span class="txt">到达${list.arrivePosition}</span>
								</li>
							</c:if>
							<c:if test="${not index.first }">
								<li>
									<i class="node-icon"></i>
									<span class="time"><fmt:formatDate value="${list.arriveTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></span>
									<span class="txt">到达${list.arrivePosition}</span>
								</li>
							</c:if>
						</c:otherwise>
					</c:choose>

				</c:forEach>
					<li>
						<i class="node-icon"></i>
						<span class="time"><fmt:formatDate value="${order.orderTime}"  pattern="yyyy-MM-dd HH:mm:ss" /></span>
						<span class="txt">收件起始地点,${order.mfrom}</span>
					</li>
				</ul>
			</div>
		</div>

	</form>
</div>

