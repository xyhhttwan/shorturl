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
		<input name="userId" type="hidden" id="userId" value="${user.id}">
		<table>
			<tr>
				<td><label> 用户名: </label></td>
				<td>${user.systemName}</td>

			</tr>
			<tr>
				<td><label> 角色: </label></td>
				<td> <select class="easyui-combogrid" name ="roleIds" id="roleIds" style="width:400px" data-options="
				panelWidth: 400,
				multiple: true,
				idField: 'id',
				textField: 'roleName',
				loadMsg:'正在加载角色信息...',
				mode:'remote',
				url: '<%=basePath2%>backstage/system/roleManage/getAllRoles.do',
				method: 'get',
				columns: [[
					{field:'ck',checkbox:true},
					{field:'id',title:'角色ID',width:80},
					{field:'roleName',title:'角色名称',width:120}

				]],
				onLoadSuccess:function(q,w){

                        $('#roleIds').combogrid('setValues',
                        [
                        		<c:forEach items="${userRole}" var="userRole">
									${userRole.roleId},
			 					 </c:forEach>
                        ])
                    },
            fitColumns: true
        ">
				</select></td>

			</tr>
	</form>
</div>

