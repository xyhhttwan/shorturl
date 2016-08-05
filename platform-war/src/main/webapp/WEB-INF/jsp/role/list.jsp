<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<title>角色列表</title>
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
		function newRole(){
			oprateUser
			(
					"新增角色",
					"<%=basePath%>background/system/roleManage/addRoleView.do",
					0,
					"add_role",
					"<%=basePath%>background/system/roleManage/addRole.do",
					500,
					"tt"
			)
		}
		 function editRole(){
			 oprateUser
			 (
			       "修改角色",
					 "<%=basePath%>background/system/roleManage/updateRoleView.do",
					 1,
					 "add_role",
					 "<%=basePath%>background/system/roleManage/updateRole.do",
					 500,
					 "tt"
			 )
		 }
		/*
		*按条件查询
		* */
		function doSearch(){
			$("#tt").datagrid("load",{
				roleName:$("#roleName").val()
			});
		}
		 /**
		 *删除用户
		 */
		 function destroyRole(){
			 del("tt","<%=basePath%>background/system/roleManage/deleteRole.do");
		 }

		function setMenu(){
			var url="<%=basePath%>background/system/roleManage/setMenuView.do";
			var addurl ="<%=basePath%>background/system/roleManage/setMenu.do";
			set(url,addurl,"维护角色菜单关系",1);
		}

		 var role_dialogParent="";
		 var role_dialogOwn="";
		 function set(url,addurl,title,type)
		 {

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
				 title : title,
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
						 add(addurl);
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

		 function add(url) {
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

		function setPermission(){
			var url ="<%=basePath%>background/system/roleManage/setPermissionView.do";
			var addUrl="<%=basePath%>background/system/roleManage/setPermission.do";
			set(url,addUrl,"维护角色权限关系",0);
		}

		 var  isClickCh =true;
		function treeOnCheck(node,checked){

			var trees = $("#menuIds").combotree("tree");
			var parentNode = trees.tree('getParent', node.target);
			var ch = node.children;


			if (checked) {
				if(ch.length>0){

						for(var i=0;i<ch.length;i++){
							if(!ch[i].checked){
								trees.tree('check', ch[i].target);
							}
						}

					/*var childNode = trees.tree('getChildren', node.target);*/
				}else{
					if (parentNode != null) {
						if(!parentNode.checked){
							trees.tree('check', parentNode.target);
						}
					}
				}


			} else {

				var childNode =trees.tree('getChildren', node.target);
				if (childNode.length > 0) {
					for (var i = 0; i < childNode.length; i++) {
						trees.tree('uncheck', childNode[i].target);
					}
				}
			}
		}
	</script>

</head>

<body>
<form name="myform" id="myform" method="post">
	<div id="toolbar">
		<div style="border-bottom: 1px solid #95B8E7; margin: 5px;  background: #F4F4F4" >
			<p>查询条件  &nbsp;角色名称:<input  class="easyui-textbox" type="text" name="roleName" id="roleName">

				<span class="btn_1"> <input type="button" value="查找" name="search" onclick="doSearch()" id="search"/></span>
			</p>
		</div>
		<div style="margin: 5px;background: #F4F4F4" >
			<shiro:hasPermission name="role-add">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"  onclick="newRole()">新增角色</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="role-update">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">修改角色</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="role-delete">
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyRole()">删除角色</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="role-setMenu">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="setMenu()">设置菜单</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="role-setPermission">
			<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="setPermission()">设置权限</a>
			</shiro:hasPermission>
			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt')">刷新</a>
		</div>

	</div>


	<table id="tt" class="easyui-datagrid" height="450px"
		   url="<%=basePath%>background/system/roleManage/list.do"
		   title="角色列表"
		   border="true"
		   toolbar="#toolbar"
		   rownumbers="true" pagination="true" >
		<thead>
		<tr>
			<th field="id" checkbox="true" width="30%" >角色ID</th>
			<th field="roleName" width="40%"  >角色名称</th>
<%--
			<th field="statusStr" width="40%"  >状态</th>
--%>
		</tr>
		</thead>
	</table>
</form>

	<div id="add_role"></div>

<!--  <div>-->
<html>
