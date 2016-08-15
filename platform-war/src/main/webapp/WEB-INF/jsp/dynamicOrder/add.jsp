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

		<table  style="margin-top: 10px">

			<tr>
				<td><label> 订单号: </label></td>
				<td>
					<input name="orderId" id="orderId" readonly="readonly"value="${order.orderId}" required="true" validtype="length[14,14]" maxlength="14"    style="height:35px;width: 400px;" class="easyui-validatebox"/></td>
			</tr>

			<tr>
				<td><label> 到达地方: </label></td>
				<td>
					<input name="arrivePosition" id="arrivePosition"   maxlength="50"  validtype="length[2,32]" class="easyui-validatebox" required="true"   style="height:35px;width: 400px;" /></td>
			</tr>
			<tr>
				<td><label> 到达时间: </label></td>
				<td>
					<input name="arriveTime" id="arriveTime"
						   value="<fmt:formatDate value="${now }"  type="both" />" class="easyui-datetimebox"  editable="false"   style="height:35px;width: 400px;" /></td>
			</tr>

		</table>
	</form>
</div>

