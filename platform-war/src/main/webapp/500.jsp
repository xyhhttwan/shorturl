<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>500 server error</title>
    <style type="text/css">
        .error {
            font-size: 20px;
            padding: 30px;
            border: 1px solid #FF0000;
            background: url(<%=basePath%>images/error.gif) 22px 50% no-repeat #FFEEFF ;
            padding-left: 40px;

        }
        .co{
            color:red;
        }
    </style>
</head>
<body bgcolor="#F9FDFF">
<div class="error">
    系统发生异常
</div>

</body>
</html>