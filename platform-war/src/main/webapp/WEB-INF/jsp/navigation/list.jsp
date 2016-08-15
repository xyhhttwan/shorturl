<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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

        //1.标题2.新增(修改)跳转(请求的页面)3.(0新增1修改)4页面弹出div的id 5.提交url6 弹出框页面宽度7成功后要刷新的列表id
        function newItem() {
            oprateUser
            (
                    "新增导航",
                    "<%=basePath%>backstage/navigation/navigationManage/addNavigationView.do",
                    0,
                    "add_user",
                    "<%=basePath%>backstage/navigation/navigationManage/addNavigation.do",
                    500,
                    "tt",
                    ""
            );
        }
        function editItem() {
            oprateUser
            (
                    "修改导航",
                    "<%=basePath%>backstage/navigation/navigationManage/updateNavigationView.do",
                    1,
                    "add_user",
                    "<%=basePath%>backstage/navigation/navigationManage/updateNavigation.do",
                    500,
                    "tt",
                    ""
            )
        }


        /**
         *删除用户
         */
        function destroyItem() {
            del("tt", "<%=basePath%>backstage/navigation/navigationManage/deleteNavigation.do");

        }

        function statuStr(value,row) {
            if(value=="0"){
                return "<span style='color:red'>未发布</span>";
            }
           else return "<span style='color:green'>已发布</span>";

        }
        //上线
        function doPublish() {
            doPost("tt","<%=basePath%>backstage/navigation/navigationManage/setPublishOrUnPublish","","status=1");
        }
        //下线
        function doUnPublish() {
            doPost("tt","<%=basePath%>backstage/navigation/navigationManage/setPublishOrUnPublish","","status=0");
        }
    </script>

</head>

<body>
<form name="myform" id="myform" method="post">
    <div id="toolbar">

        <div style="margin: 5px;backstage: #F4F4F4">
            <shiro:hasPermission name="navigation-add">
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增导航</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="navigation-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">修改导航</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="navigation-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="destroyItem()">删除导航</a>
            </shiro:hasPermission>

            <shiro:hasPermission name="navigation-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-mini_publish" plain="true"
                   onclick="doPublish()">发布上线</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="navigation-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="doUnPublish()">发布下线</a>
            </shiro:hasPermission>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt')">刷新</a>
        </div>

    </div>

    <table id="tt" class="easyui-datagrid" height="450px"
           url="<%=basePath%>backstage/navigation/navigationManage/list.do"
           title="导航列表"
           border="true"
           toolbar="#toolbar"
           rownumbers="true" pagination="true">
        <thead>
        <tr>
            <th field="id" checkbox="true" width="10%">id</th>
            <th field="name" width="20%">导航名称</th>
            <th field="url" width="50%">连接地址</th>
            <th field="status" width="20%" formatter="statuStr">  状态  </th>
        </tr>
        </thead>
    </table>
</form>

<div id="add_user"></div>

<!--  <div>-->
<html>
