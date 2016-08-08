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
		function newItem(){
			oprateUser
			(
					"新增菜单",
					"<%=basePath%>backstage/system/menuManage/addMenuView.do",
					0,
					"add_user",
					"<%=basePath%>backstage/system/menuManage/addMenu.do",
					500,
					"tt",
					"treegrid"

			);
		}
		 function editItem(){
			 oprateUser
			 (
			       "修改用户",
					 "<%=basePath%>backstage/system/menuManage/updateMenuView.do",
					 1,
					 "add_user",
					 "<%=basePath%>backstage/system/menuManage/updateMenu.do",
					 500,
					 "tt",
					 "treegrid"
			 )
		 }


		 /**
		 *删除用户
		  */
		 function destroyItem(){
			 var rows = $('#tt').treegrid('getSelections');
			 var url = "<%=basePath%>backstage/system/menuManage/ifHasChildren.do?id="+rows[0].id;
			 $.get(url, function(data){
				 if (data.result=="true"){
					 $.messager.alert('Error',  "<br/>"+data.message, 'Error');

				 } else {
					 del("tt","<%=basePath%>backstage/system/menuManage/delMenu.do","treegrid");

				 }
			 }, 'json');

	     }

	</script>

</head>

<body>
<form name="myform" id="myform" method="post">
	<div id="toolbar">

		<div style="margin: 5px;backstage: #F4F4F4" >
			<shiro:hasPermission name="menu-add">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增菜单</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="menu-update">
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">修改菜单</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="menu-delete">
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyItem()">删除菜单</a>
			</shiro:hasPermission>
			<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt','treegrid')">刷新</a>
		</div>

	</div>

	<table id="tt" class="easyui-treegrid" height="450px"
		   url="<%=basePath%>backstage/system/menuManage/list.do"
		   title="菜单列表"
		   border="true"
		   toolbar="#toolbar"
		   rownumbers="true"
		   idField="id"
		   treeField="menuName"
		   data-options="
					 onLoadSuccess: function (node, data) {
                   			 $('#tt').treegrid('collapseAll');

						}
		   "
			>
		<thead>
		<tr>
			<th field="menuName" width="20%">菜单名称</th>
			<th field="url" width="40%" >菜单连接</th>
			<th field="icon" width="10%" >icon</th>
			<th field="menuSort" width="10%"  >排序</th>
			<%--<th field="statusStr"width="10%"  >状态</th>--%>
		</tr>
		</thead>
	</table>
</form>

	<div id="add_user"></div>

<!--  <div>-->
<html>
