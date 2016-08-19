<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<link href="<%=basePath %>js/upload/webuploader.css" type="text/css" rel="stylesheet"/>
<script>
    var BASE_URL = "<%=basePath%>";
</script>
<style>
    .gr-s,.gr-xx .c-red,.gr-xx input{ float: left;}
    .gr-s{width:130px; text-align: right; line-height: 30px; margin-right:8px;}
</style>
<body>

<img id="phone_img" name="phone_img" src="" alt=""
     class="m-infor"/>

<input type="hidden" id="photo" name="photo" value="${webAccounts.photo}"/>
<li><span class="gr-s">&nbsp;</span><div class="divUpload">
    <div id="filePicker">选择图片</div></div></li>
<li id="hli"><span class="gr-s" id="progressSpan1">&nbsp;</span>
    <span class="gr-s" id="progressSpan"></span>
</li>
<li id="hli"><span class="gr-s" id="progressSpan1">&nbsp;</span><span class="gr-s"
                                                                      id="progressSpan"></span>
</li>

<script type="text/javascript" src="<%=basePath%>js/upload/webuploader.js"></script>
<script type="text/javascript" src="<%=basePath%>js/upload/enterprise/index/uploader.js"></script>

</body>