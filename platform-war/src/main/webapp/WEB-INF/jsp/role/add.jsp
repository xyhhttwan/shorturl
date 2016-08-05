<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
	<form id="dataform" method="post" datatype="json">
		<c:if test="${not empty role}">
			<input type="hidden" name="id" value="${role.id}" id="id">
		</c:if>
		<table>
			<tr>
				<td><label> 角色名称: </label></td>
				<td><input name="roleName" id="roleName" maxlength="16"
						   value="${role.roleName}"
						   class="easyui-validatebox" required="true" /></td>

			</tr>

		</table>
	</form>
</div>