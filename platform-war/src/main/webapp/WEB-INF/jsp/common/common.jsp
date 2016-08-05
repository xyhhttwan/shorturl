<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath %>jqueryeasyui/js/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath %>jqueryeasyui/js/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>jqueryeasyui/js/themes/color.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common.css">
<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath %>jqueryeasyui/js/jquery.easyui.min.js"></script>
<%--
<script type="text/javascript" src="<%=basePath %>jqueryeasyui/js/jquery.cookie.js"></script>
--%>

<script type="text/javascript" src="<%=basePath %>jqueryeasyui/js/plugins/jquery.form.js"></script>

<script src="<%=basePath %>jqueryeasyui/js/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/validator.js"></script>
