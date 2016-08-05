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

		<c:if test="${not empty permission}">
			<input type="hidden" name="id" value="${permission.id}" id="id">
			<input type="hidden" name="status" value="${permission.status}" id="status">

		</c:if>
		<table>
			<tr>
				<td><label> 权限名称: </label></td>
				<td><input name="permissionName" id="permissionName"  validtype="length[0,16]" maxlength="16"  value="${permission.permissionName}" class="easyui-validatebox" required="true" /></td>

			</tr>

			<tr>
				<td><label> 权限编码: </label></td>
				<td><input name="permissionCode" value="${permission.permissionCode}" <c:if test="${not empty permission}">readonly="readonly"</c:if> type="text" validtype="length[1,32]" invalidMessage="有效长度1-32" id="permissionCode" required="true" class="easyui-validatebox" /></td>
			</tr>

			<tr>
				<td><label> 父权限:</label></td>
				<td>
					<input class="easyui-combotree" id="parentId" name="parentId"  editable="false"
						   data-options="
						   valueField:'id',
						   textField:'text',
						   value:'<c:if test="${not empty permission}">${permission.parentId}</c:if><c:if test="${ empty permission}">${id}</c:if>',
						   url:'<%=basePath2%>background/system/permissionManage/getPermissionAll.do',
					       method:'get',
					        onLoadSuccess: function (node, data) {
                   			 $('#parentId').combotree('tree').tree('collapseAll');
						},
					       panelHeight:'auto'">
				</td>
			</tr>

		</table>
	</form>
</div>

