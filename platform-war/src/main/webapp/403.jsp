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
    <title>403</title>
    <style type="text/css">
        .error {
            font-size: 12px;
            padding: 5px;
            border: 1px solid #FF0000;
            background: url(<%=basePath%>images/error.gif) 8px 5px no-repeat #FFEEFF;
            padding-left: 30px;

        }
        .co{
            color:red;
        }
    </style>
</head>
<body bgcolor="#F9FDFF">
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<br />
<center>
    <table border="0" width="325" height="223" cellpadding="0" cellspacing="0" style="margin-top:250">
        <tr>
            <td align="center" background="<%=basePath%>images/mess.gif">

                <span class="co">&nbsp;&nbsp;您的访问被拒绝</span>
                <br><br>
                <a href="javascript:window.history.go(-1)">返回</a>
            </td>
        </tr>
    </table>
</center>

</body>
</html>