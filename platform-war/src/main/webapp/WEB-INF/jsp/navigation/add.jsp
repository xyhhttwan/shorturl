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

		<c:if test="${not empty navigation}">
			<input type="hidden" name="id" value="${navigation.id}" id="id">
			<input type="hidden" name="status" value="${navigation.status}" id="status">

		</c:if>
		<table>
			<tr>
				<td><label> 导航名称: </label></td>
				<td><input name="name" id="name"   style="height:35px;width: 400px;" validtype="length[0,16]" maxlength="16"  value="${navigation.name}" class="easyui-validatebox" required="true" /></td>

			</tr>

			<tr>
				<td><label> 连接地址: </label></td>
				<td><input name="url"  style="height:35px;width: 400px;" value="${navigation.url}" style="width: 400px;" type="text" validtype="length[1,255]" invalidMessage="有效长度1-255" id="url" required="true" class="easyui-validatebox" /></td>
			</tr>

		</table>
	</form>
</div>

