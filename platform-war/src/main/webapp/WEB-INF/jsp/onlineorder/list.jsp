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
                    "客户订单处理",
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
            del("tt", "<%=basePath%>backstage/onlineOrder/onlineOrderManage/delete.do");

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


            <shiro:hasPermission name="navigation-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="doPublish()">发布上线</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="navigation-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="doUnPublish()">发布下线</a>
            </shiro:hasPermission>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt','treegrid')">刷新</a>
        </div>

    </div>

    <table id="tt" class="easyui-datagrid" height="450px"
           url="<%=basePath%>backstage/onlineOrder/onlineOrderManage/list.do"
           title="客户订单列表"
           border="true"
           toolbar="#toolbar"
           rownumbers="true" pagination="true">
        <thead>
        <tr>
            <th field="id" checkbox="true" width="10%">id</th>
            <th field="createDate" width="50%">下单时间</th>
            <th field="linKMan" width="20%">联系人</th>
            <th field="phoneNum" width="50%">联系电话</th>
            <th field="mFrom" width="20%"> 起始地</th>'
            <th field="mTo" width="20%">目的地</th>
            <th field="content" width="20%">发货内容</th>
            <th field="remark" width="20%">备注</th>
            <th field="isLink" width="20%">是否已处理</th>
            <th field="linkResult" width="20%">处理结果</th>
        </tr>
        </thead>
    </table>
</form>

<div id="add_user"></div>

<!--  <div>-->
<html>
