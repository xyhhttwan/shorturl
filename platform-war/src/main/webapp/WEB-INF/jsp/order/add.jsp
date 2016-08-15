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

		<c:if test="${not empty order}">
			<input type="hidden" name="id" value="${order.id}" id="id">

		</c:if>
		<table  style="margin-top: 10px">

			<tr>
				<td><label> 订单号: </label></td>
				<td>
					<input name="orderId" id="orderId" <c:if test="${not empty order}">readonly="readonly"</c:if> value="${order.orderId}" required="true" validtype="length[14,14]" maxlength="14"    style="height:35px;width: 400px;" class="easyui-validatebox"/></td>
			</tr>

			<tr>
				<td><label> 下单时间: </label></td>
				<td>
					<input name="orderTime" id="orderTime"
						   value="<fmt:formatDate value="${order.orderTime }"  type="both" />" class="easyui-datetimebox"  editable="false"   style="height:35px;width: 400px;" /></td>
			</tr>

			<tr>
				<td><label> 发货地: </label></td>
				<td>
					<input name="mfrom" id="mfrom"  value="${order.mfrom}"  maxlength="32"  validtype="length[2,32]" class="easyui-validatebox" required="true"   style="height:35px;width: 400px;" /></td>
			</tr>

			<tr>
				<td><label> 目的地: </label></td>
				<td>
					<input name="mto" id="mto" maxlength="32"  value="${order.mto}"  validtype="length[2,32]" class="easyui-validatebox" required="true"   style="height:35px;width: 400px;" /></td>
			</tr>

			<tr>
				<td><label> 费用(元): </label></td>
				<td>
					<input name="price" id="price" value="${order.price}" class="easyui-numberbox" data-options="min:0,precision:2" maxlength="10"  validtype="length[0,10]"  required="true"   style="height:35px;width: 400px;" /></td>
			</tr>

			<tr>
				<td><label> 承运人: </label></td>
				<td>
					<input name="carrier" id="carrier" value="${order.carrier}" class="easyui-validatebox" maxlength="10"  validtype="length[0,10]"    style="height:35px;width: 400px;" /></td>
			</tr>
			<tr>
				<td><label> 联系电话: </label></td>
				<td>
					<input name="linkPhone" id="linkPhone" value="${order.linkPhone}" class="easyui-validatebox" maxlength="11"  validtype="length[11,11]"    style="height:35px;width: 400px;" /></td>
			</tr>
			<tr>
				<td><label> 货物内容: </label></td>
				<td>
					<textarea  name="content" id="content"   required="false" cols="63" rows="5" class="textareaeasyui-validatebox">${order.content}</textarea>
				</td>
			</tr>

		</table>
	</form>
</div>

