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
		<input name="roleId" type="hidden" id="roleId" value="${role.id}">
		<table>
			<tr>
				<td><label> 角色: </label></td>
				<td>${role.roleName}</td>

			</tr>
			<tr>
				<td><label> 权限: </label></td>
				<td>
				<select id="permissionIds" name="permissionIds" class="easyui-combotree"
						data-options="
						url:'<%=basePath2%>backstage/system/permissionManage/getPermissionAll.do',
						method:'get',
						checkbox :true,
						multiple:true,
						 onLoadSuccess: function (node, data) {
                   			 $('#permissionIds').combotree('tree').tree('collapseAll');
                   			  $('#permissionIds').combotree(
                   			  'setValues',
                   			   [
                        		<c:forEach items="${permission}" var="permission">
									${permission.id},
			 					 </c:forEach>
                        ]
                   			  )
						}

					     "

						style="width:400px;">

				</select>

				</td>

			</tr>
	</form>
</div>

