<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
	<form id="dataform" method="post">
		<c:if test="${not empty user}">
			<input type="hidden" name="id" value="${user.id}" id="id">
		</c:if>
		<table>
			<tr>
				<td><label> 用户名: </label></td>
				<td><input name="systemName" id="systemName"<c:if test="${not empty user}"> disabled </c:if> validtype="account[3,20]"  maxlength="16"  value="${user.systemName}" class="easyui-validatebox" required="true" /></td>
			</tr>
			<c:if test="${empty user}">
			<tr>
				<td><label> 密码: </label></td>
				<td><input name="password" value="${user.password}"  type="password" validtype="length[6,16]" invalidMessage="有效长度6-16" id="password" required="true" class="easyui-validatebox" /></td>
			</tr>
			</c:if>
			<tr>
				<td><label> 真实姓名:</label></td>
				<td><input id="realName" value="${user.realName}" maxlength="12" name="realName" required="true" class="easyui-validatebox" /></td>
			</tr>
			<tr>
				<td><label> 身份证号: </label></td>
				<td><input id="IDCard" maxlength="18" validtype="idcard" value="${user.IDCard}"  name="IDCard"  class="easyui-validatebox" /></td>
			</tr>
			<tr>
				<td><label> 手机号码: </label></td>
				<td><input id="phone" maxlength="18"  validtype="mobile"  value="${user.phone}" name="phone" class="easyui-validatebox" /></td>
			</tr>
			<tr>
				<td><label> 入职时间: </label></td>
				<td><input id="entryDate" editable="false" value="${user.entryDate}" name="entryDate"  class="easyui-datebox"/></td>
			</tr>
			<tr>
				<td><label> 性别: </label></td>
				<td>
					<select id="sex" name="sex" class="easyui-combobox" style="width:200px;" >
						<option value="0" <c:if test="${user.sex eq 0}"> selected</c:if>>男</option>
						<option value="1" <c:if test="${user.sex eq 1}"> selected</c:if>>女</option>
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
