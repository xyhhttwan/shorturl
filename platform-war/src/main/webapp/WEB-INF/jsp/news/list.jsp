<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>新闻列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <jsp:include page="/WEB-INF/jsp/common/common.jsp"></jsp:include>
    <link href="<%=basePath %>css/upload/news/upload.css" type="text/css" rel="stylesheet"/>
    <link href="<%=basePath %>js/upload/webuploader.css" type="text/css" rel="stylesheet"/>
    <!-- 样式文件 -->
    <link rel="stylesheet" href="<%=basePath %>js/ue/themes/default/css/umeditor.css">
    <!-- 引用jquery -->
    <!-- 配置文件 -->
    <script type="text/javascript" src="<%=basePath%>js/ue/umeditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=basePath%>js/ue/umeditor.js"></script>
    <!-- 语言包文件 -->
    <script type="text/javascript" src="<%=basePath%>js/ue/lang/zh-cn/zh-cn.js"></script>

    <script type="text/javascript">


        /**
         *按条件查询
         */
        function doSearch() {
            var validator = $("#myform").form('validate');
            if (validator) {
                $("#tt").datagrid("load", {
                    title: $("#title").val(),
                    status: $("#status").combobox('getValue'),

                });
            }

        }

        //1.标题2.新增(修改)跳转(请求的页面)3.(0新增1修改)4页面弹出div的id 5.提交url6 弹出框页面宽度7成功后要刷新的列表id
        function newItem() {
            operate_news
            (
                    "新增新闻",
                    "<%=basePath%>backstage/news/newsManage/addView.do",
                    0,
                    "<%=basePath%>backstage/news/newsManage/add.do"

            );
        }
        function editItem() {
            operate_news
            (
                    "修改新闻",
                    "<%=basePath%>backstage/news/newsManage/updateView.do",
                    1,
                    "<%=basePath%>backstage/news/newsManage/update.do"

            )
        }


        function operate_news(title, url, type, submitUrl) {
            if (type == 1) { //edit
                var rows = $("#tt").datagrid('getSelections')
                if (rows.length != 1) {
                    $.messager.alert("消息", "请选择一行数据!", "info");
                    return;
                } else {
                    url = url + "?id=" + rows[0].id;
                }
            }
            //解决关闭不了的问题
            dialogParent = $("#add_news").parent();
            dialogOwn = $("#add_news").clone();
            $("#add_news").dialog({
                href: url,
                modal: true,
                title: title,
                width: "80%",
                resizable: true,
                cache: false,
                fit: true,
                buttons: [
                    {
                        text: '确定',
                        iconCls: 'icon-ok',
                        handler: function () {
                            if($("#pic").val()==""){
                                $.messager.alert("消息", "请上传图片!", "info");
                                return;
                            }
                            var text = um.getContentTxt();
                            if(text==""){
                                $.messager.alert("消息", "内容不能为空!", "info");
                                UM.getEditor('content').focus();
                                return;
                            }

                            dataSubmit(submitUrl, "add_news", "tt", "");
                        }
                    },
                    {
                        text: '取消',
                        iconCls: 'icon-cancel',
                        handler: function () {
                            dialogOwn.appendTo(dialogParent);
                            $('#add_news').dialog("destroy").remove();
                        }
                    }
                ],
                onClose: function () {
                    dialogOwn.appendTo(dialogParent);
                    $(this).dialog('destroy');
                },
                onLoad: function () {

                }
            });
        }

        /**
         *删除用户
         */
        function destroyItem() {
            del("tt", "<%=basePath%>backstage/news/newsManage/delete.do");

        }

        function statuStr(value,row) {
            if(value=="0"){
                return "<span style='color:red'>未发布</span>";
            }
           else return "<span style='color:green'>已发布</span>";

        }
        //上线
        function doPublish() {
            doPost("tt","<%=basePath%>backstage/news/newsManage/setPublishOrUnPublish","","status=1");
        }
        //下线
        function doUnPublish() {
            doPost("tt","<%=basePath%>backstage/news/newsManage/setPublishOrUnPublish","","status=0");
        }

        function formatterType(val, rows) {


          if(val=="0"){
              return "图片新闻";
          }else{
              return "文字新闻";
          }
        }



        function formatterIMG(val, rows) {

            if(rows.newsType=="1") return "";
            if (val != null && "" != val) {
                var picture = '<a target="_blank" class="easyui-tooltip" id="picture-' + rows.id + '"href=\"<%=basePath%>' + val + '">' + '<img src=\"<%=basePath%>images/img.gif\"/>' + '  </a>';
                return picture;
            } else {
                return "";
            }
        }

    </script>

</head>

<body>
<form name="myform" id="myform" method="post">



    <div id="toolbar">

        <div style="border-bottom: 1px solid #95B8E7; margin: 5px;  backstage: #F4F4F4" >
            <p>查询条件  &nbsp;
               <%--标题: <input type="text" name="title" id="title" class="easyui-textbox" >--%>
                状态:
                <select class="easyui-combobox" style="width:150px" name="status" id="status">
                    <option value="">请选择</option>
                    <option value="0">未发布</option>
                    <option value="1">已发布</option>
                </select>
                <span class="btn_1"><input type="button" value="查找" name="search" onclick="doSearch()" id="search"/></span>
            </p>
        </div>
        <div style="margin: 5px;backstage: #F4F4F4">
            <shiro:hasPermission name="news-add">
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增新闻</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="news-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">修改新闻</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="news-delete">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="destroyItem()">删除新闻</a>
            </shiro:hasPermission>

            <shiro:hasPermission name="news-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-mini_publish" plain="true"
                   onclick="doPublish()">发布上线</a>
            </shiro:hasPermission>
            <shiro:hasPermission name="news-update">
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
                   onclick="doUnPublish()">发布下线</a>
            </shiro:hasPermission>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reload('tt')">刷新</a>
        </div>

    </div>

    <table id="tt" class="easyui-datagrid" height="450px"
           url="<%=basePath%>backstage/news/newsManage/list"
           title="新闻列表"
           border="true"
           toolbar="#toolbar"
           rownumbers="true" pagination="true">
        <thead>
        <tr>
            <th field="id" checkbox="true" width="10%">id</th>
            <th field="title" width="25%">标题</th>
            <th field="postTime" width="20%"  formatter="formatterDate">发布时间</th>
            <th field="updateTime" width="20%"  formatter="formatterDate">更新时间</th>
            <th field="pic" width="10%" formatter="formatterIMG">图片</th>
            <th field="newsType" width="10%" formatter="formatterType">  类型  </th>
            <th field="status" width="10%" formatter="statuStr">  状态  </th>
        </tr>
        </thead>
    </table>
</form>

<div id="add_news"></div>

<!--  <div>-->
<html>
