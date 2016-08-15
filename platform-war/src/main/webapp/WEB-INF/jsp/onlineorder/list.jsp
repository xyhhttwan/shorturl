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


        /*
         *按条件查询
         * */
        function doSearch(){
            $("#tt").datagrid("load",{
                isLink:$("#isLink").combobox('getValue')
            });
        }

        //1.标题2.新增(修改)跳转(请求的页面)3.(0新增1修改)4页面弹出div的id 5.提交url6 弹出框页面宽度7成功后要刷新的列表id
        function newItem() {
            oprateUser
            (
                    "客户订单处理",
                    "<%=basePath%>backstage/onlineOrder/navigationManage/addView",
                    0,
                    "add_user",
                    "<%=basePath%>backstage/onlineOrder/navigationManage/add",
                    500,
                    "tt",
                    ""
            );
        }
        function editItem() {
            oprateUser
            (
                    "处理在线订单",
                    "<%=basePath%>backstage/onlineOrder/onlineOrderManage/updateView",
                    1,
                    "add_user",
                    "<%=basePath%>backstage/onlineOrder/onlineOrderManage/handleOrder",
                    500,
                    "tt",
                    ""
            )
        }


        /**
         *删除用户
         */
        function destroyItem() {
            del("tt", "<%=basePath%>backstage/onlineOrder/onlineOrderManage/delete");

        }

        function statuStr(value,row) {
            if(value=="0"){
                return "<span style='color:red'>未处理</span>";
            }
           else return "<span style='color:green'>已处理</span>";

        }
        //上线
        function doPublish() {
            doPost("tt","<%=basePath%>backstage/onlineOrder/onlineOrderManage/setPublishOrUnPublish","","status=1");
        }
        //下线
        function doUnPublish() {
            doPost("tt","<%=basePath%>backstage/onlineOrder/onlineOrderManage/setPublishOrUnPublish","","status=0");
        }
    </script>

</head>

<body>
<form name="myform" id="myform" method="post">
    <div id="toolbar">

        <div style="border-bottom: 1px solid #95B8E7; margin: 5px;  backstage: #F4F4F4" >
            <p>查询条件  &nbsp;
                <select class="easyui-combobox" style="width:150px" name="isLink" id="isLink">
                    <option value="0">未处理</option>
                    <option value="1">已处理</option>
                </select>
                <span class="btn_1"><input type="button" value="查找" name="search" onclick="doSearch()" id="search"></input></span>
            </p>
        </div>
        <div style="margin: 5px;backstage: #F4F4F4">
            <shiro:hasPermission name="onlineOrder-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="editItem()">处理</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="onlineOrder-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="destroyItem()">删除</a>
            </shiro:hasPermission>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt')">刷新</a>
        </div>
    </div>


    <table id="tt" class="easyui-datagrid" height="450px"
           url="<%=basePath%>backstage/onlineOrder/onlineOrderManage/list"
           title="客户在线订单列表"
           border="true"
           toolbar="#toolbar"
           rownumbers="true" pagination="true">
        <thead>
        <tr>
            <th field="id" checkbox="true" width="2%">id</th>
            <th field="createDate" formatter="formatterDate" width="12%">下单时间</th>
            <th field="isLink" formatter="statuStr" width="6%">是否已处理</th>
            <th field="linkResult" width="10%">处理结果</th>
            <th field="linkMan" width="6%">联系人</th>
            <th field="phoneNum" width="8%">联系电话</th>
            <th field="mfrom" width="12%"> 起始地</th>
            <th field="mto" width="12%">目的地</th>
            <th field="content" width="20%">发货内容</th>
            <th field="remark" width="11%">备注</th>

        </tr>
        </thead>
    </table>
</form>

<div id="add_user"></div>
</body>
<!--  <div>-->
<html>
