<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>前端导航列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
	<jsp:include page="/WEB-INF/jsp/common/common.jsp"></jsp:include>
	<script type="text/javascript">
		function addComm(){
			$.messager.confirm('确认', '确定要操作该数据?', function (r) {
				if (r) {
					$('#dataform').form('submit', {
						url: "<%=basePath%>backstage/common/commonManage/update",
						dataType: 'json',
						ajax: true,
						onSubmit: function () {
							var validator = $(this).form('validate');
							if (validator) {
								var win = $.messager.progress({
									title: 'Please waiting',
									msg: '正在提交数据...'
								});
								return true;
							}
							return false;
						},
						success: function (data) {
							close_process();
							try{
								var data = eval('(' + data + ')');
								if (data.result == "true") {
									$.messager.show({title: 'Success', msg: "<br/>" + data.message});
								} else {
									$.messager.alert('Error', "<br/>" + data.message, 'Error');
								}
							}catch (exception){

							}

						}

					});
				}
			});
		}
	</script>
</head>


<body>
<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
	<form id="dataform" method="post" >

		<c:if test="${not empty common}">
			<input type="hidden" name="id" value="${common.id}" id="id">

		</c:if>
		<table  style="margin-top: 10px">

			<tr>
				<td><label> 发货热线: </label></td>
				<td>
					<input name="phoneNum" id="phoneNum" value="${common.phoneNum}" validtype="length[11,11]" maxlength="11"    style="height:35px;width: 400px;" REQUIRED class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td><label> 查货热线: </label></td>
				<td>
					<input name="telPhone" id="telPhone" value="${common.telPhone}" validtype="length[1,20]" maxlength="20"    style="height:35px;width: 400px;" class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td><label> 客服热线: </label></td>
				<td>
					<input name="complaintsNum" id="complaintsNum" value="${common.complaintsNum}" validtype="length[1,20]" maxlength="20"    style="height:35px;width: 400px;" class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td><label> 公司地址: </label></td>
				<td>
					<input name="address" id="address" value="${common.address}" REQUIRED validtype="length[1,32]" maxlength="32"    style="height:35px;width: 400px;" class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td><label> 关于我们: </label></td>
				<td>
					<textarea name="aboutUs" id="aboutUs" cols="63" rows="5" class="textareaeasyui-validatebox">${common.aboutUs}</textarea>
			</tr>

			<tr>
				<td><label> 公司介绍: </label></td>
				<td>
					<textarea name="companyDes" id="companyDes" cols="63" rows="5" class="textareaeasyui-validatebox">${common.companyDes}</textarea>
			</tr>

			<tr style="margin: 50px">
				<td colspan="2"  align="center">
			</tr>
			<tr style="margin: 50px">
				<td colspan="2"  align="center">
			</tr>

			<tr style="margin: 50px">

				<td colspan="2"  align="center">
					<span class="btn_1" ><input type="button" value="保存" name="search" onclick="addComm()" id="search"/></span>
			</tr>
		</table>


	</form>
</div>
</body>
</html>
