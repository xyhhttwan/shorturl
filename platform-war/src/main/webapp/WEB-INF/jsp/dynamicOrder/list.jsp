<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>订单列表</title>
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
        /*
         *按条件查询
         * */
        function doSearch(){
            $("#tt").datagrid("load",{
                status:$("#status").combobox('getValue')
            });
        }

        //1.标题2.新增(修改)跳转(请求的页面)3.(0新增1修改)4页面弹出div的id 5.提交url6 弹出框页面宽度7成功后要刷新的列表id
        function newItem() {
            oprateUser
            (
                    "客户订单处理",
                    "<%=basePath%>backstage/order/orderManage/addView",
                    0,
                    "add_user",
                    "<%=basePath%>backstage/order/orderManage/add",
                    500,
                    "tt",
                    ""
            );
        }
        function editItem() {
            oprateUser
            (
                    "处理在线订单",
                    "<%=basePath%>backstage/order/orderManage/updateView",
                    1,
                    "add_user",
                    "<%=basePath%>backstage/order/orderManage/update",
                    500,
                    "tt",
                    ""
            )
        }


        /**
         *删除用户
         */
        function destroyItem() {
            del("tt", "<%=basePath%>backstage/order/orderManage/delete");

        }

        function statuStr(value,row) {
            if(value=="0"){
                return "<span style='color:red'>正在运输</span>";
            }
           else return "<span style='color:green'>已完毕</span>";

        }


    </script>

</head>

<body>
<form name="myform" id="myform" method="post">
    <div id="toolbar">

        <div style="border-bottom: 1px solid #95B8E7; margin: 5px;  backstage: #F4F4F4" >
            <p>查询条件  &nbsp;
                <select class="easyui-combobox" style="width:150px" name="status" id="status">
                    <option value="0">未处理</option>
                    <option value="1">已处理</option>
                </select>
                <span class="btn_1"><input type="button" value="查找" name="search" onclick="doSearch()" id="search"></input></span>
            </p>
        </div>
        <div style="margin: 5px;backstage: #F4F4F4">
            <shiro:hasPermission name="onlineOrder-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
                   onclick="newItem()">新增订单</a>
            </shiro:hasPermission>

            <shiro:hasPermission name="onlineOrder-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                   onclick="editItem()">修改订单</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="onlineOrder-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="editItem()">更新订单</a>
            </shiro:hasPermission>


            <shiro:hasPermission name="onlineOrder-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="destroyItem()">删除</a>
            </shiro:hasPermission>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt')">刷新</a>
        </div>
    </div>


    <table id="tt" class="easyui-datagrid" height="450px"
           url="<%=basePath%>backstage/order/orderManage/list"
           title="客户订单列表"
           border="true"
           toolbar="#toolbar"
           rownumbers="true" pagination="true">
        <thead>
        <tr>
            <th field="id" checkbox="true" width="2%">id</th>
            <th field="orderTime" formatter="formatterDate" width="12%">下单时间</th>
            <th field="orderId"  width="12%">订单号</th>
            <th field="isLink" formatter="statuStr" width="6%">状态</th>
            <th field="mfrom" width="10%">发货地</th>
            <th field="mto" width="10%">目的地</th>
            <th field="carrier" width="8%"> 承运人</th>
            <th field="linkPhone" width="8%">联系电话</th>
            <th field="content" width="25%">发货内容</th>
        </tr>
        </thead>
    </table>
</form>

<div id="add_user"></div>
</body>
<!--  <div>-->
<html>
