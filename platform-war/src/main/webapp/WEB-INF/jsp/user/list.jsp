<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>用户列表</title>
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

	     //1.标题2.新增(修改)跳转(请求的页面)3.(0新增1修改)4页面弹出div的id 5.提交url6 弹出框页面宽度7成功后要刷新的列表id
		function newUser(){
			oprateUser
			(
					"新增用户",
					"<%=basePath%>background/system/userManage/addUserView.do",
					0,
					"add_user",
					"<%=basePath%>background/system/userManage/addUser.do",
					500,
					"tt"
			);
		}
		 function editUser(){
			 oprateUser
			 (
			       "修改用户",
					 "<%=basePath%>background/system/userManage/updateUserView.do",
					 1,
					 "add_user",
					 "<%=basePath%>background/system/userManage/updateUser.do",
					 500,
					 "tt"
			 )
		 }
		/*
		*按条件查询
		* */
		function doSearch(){
			$("#tt").datagrid("load",{
				userName:$("#userName").val(),
				phone:$("#phone").val()
			});
		}
		 /**
		 *删除用户
		  */
		 function destroyUser(){
			 del("tt","<%=basePath%>background/system/userManage/delUser.do");
	     }

		 var role_dialogParent="";
		 var role_dialogOwn="";
		function setRole()
		{
			var url="<%=basePath%>background/system/userManage/setRoleView.do";
			var	rows = $('#tt').datagrid('getSelections')
			if (rows.length != 1) {
					$.messager.alert('消息', '<br/>请选择一行数据!', 'info');
					return;
				}else{
					url = url+"?id="+rows[0].id;
				}

			//解决关闭不了的问题
			role_dialogParent = $("#add_role").parent();
			role_dialogOwn = $("#add_role").clone();
			$('#add_role').dialog({
				href : url,
				modal : true,
				title : "维护角色",
				top : '15%',
				left : '30%',
				width : 500,
				resizable:true,
				cache: false,
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : function() {
						//dialogOwn.appendTo(dialogParent);
						addRole("<%=basePath%>background/system/userManage/setRole.do");
					}
				}, {
					text : '取消',
					iconCls : 'icon-cancel',
					handler : function() {
						role_dialogOwn.appendTo(role_dialogParent);
						$('#add_role').dialog("destroy").remove();
					}
				} ],
				onClose : function() {
					role_dialogOwn.appendTo(role_dialogParent);
					$('#add_role').dialog("destroy").remove();
					//$(this).dialog('destroy');
				},
				onLoad : function() {

				}
			});
		}

		 function addRole(url) {
			 $('#dataform').form('submit',{
				 url: url,
				 onSubmit: function(){
					// return $(this).form('validate');
				 },
				 success: function(data){
					 var data = eval('('+data+')');
					 if (data.result=="true"){
						 role_dialogOwn.appendTo(role_dialogParent);
						 $('#add_role').dialog("destroy").remove();      // close the dialog
						 $.messager.show({ title: 'Success',msg:"<br/>"+ data.message});

					 } else {
						 $.messager.alert('Error',  "<br/>"+data.message, 'Error');
					 }
				 }
			 });
		 }

		function checkName(val,row) {
			if(val=="admin"){
				var rows = row;
			}
			return val;
		}

		 function formatterdate(val, row) {
			 if (val != null) {
				 var date = new Date(val);
				 return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
						 + date.getDate();
			 }
		 }
	</script>

</head>

<body>
<form name="myform" id="myform" method="post">
	<div id="toolbar">
		<div style="border-bottom: 1px solid #95B8E7; margin: 5px;  background: #F4F4F4" >
			<p>查询条件  &nbsp;姓名:<input  class="easyui-textbox" type="text" name="userName" id="userName">
				联系电话:<input  class="easyui-textbox" type="text" name="phone" id="phone">
				<span class="btn_1"> <input type="button" value="查找" name="search" onclick="doSearch()" id="search"></input></span>
			</p>
		</div>
		<div style="margin: 5px;background: #F4F4F4" >
			<shiro:hasPermission name="user-add">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增用户</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="user-update">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改用户</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="user-delete">
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除用户</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="user-setRole">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="setRole()">设置角色</a>
			</shiro:hasPermission>

			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt')">刷新</a>
		</div>

	</div>

	<table id="tt" class="easyui-datagrid" height="650px"
		   url="<%=basePath%>background/system/userManage/list.do"
		   title="用户列表"
		   border="true"
		   toolbar="#toolbar"
		   rownumbers="true" pagination="true" >
		<thead>
		<tr>
			<th field="id" checkbox="true" width="10%"   >用户ID</th>
			<th field="systemName"width="10%" formatter="checkName"  >姓名</th>
			<th field="realName"width="10%"  >真实姓名</th>
			<th field="idcard"width="25%"  >身份证号</th>
			<th field="phone" width="20%" >联系电话</th>
			<th field="sexStr" width="10%" >性别</th>
			<th field="entryDate"  width="20%" formatter="formatterdate">入职时间</th>
		</tr>
		</thead>
	</table>
</form>

	<div id="add_user"></div>
<div id="add_role"></div>
<!--  <div>-->
<html>
