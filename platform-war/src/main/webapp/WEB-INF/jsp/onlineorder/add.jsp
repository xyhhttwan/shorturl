<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path2 = request.getContextPath();
	String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2+"/";
%>
<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
	<form id="dataform" method="post">

		<c:if test="${not empty onlineOrder}">
			<input type="hidden" name="id" value="${onlineOrder.id}" id="id">

		</c:if>
		<table  style="margin-top: 10px">
			<tr>
				<td><label> 处理结果: </label></td>
				<td>
					<input name="linkResult" id="linkResult"  validtype="length[0,32]" maxlength="32"    style="height:35px;width: 400px;" class="easyui-validatebox"/></td>
			</tr>

		</table>
	</form>
</div>

