<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>


</head>

<body>
<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
    <form id="dataform" method="post">

        <c:if test="${not empty newsTitle}">
            <input type="hidden" name="id" value="${newsTitle.id}" id="id">
            <input type="hidden" name="status" value="${newsTitle.status}" id="status">

        </c:if>
        <table>
            <tr>
                <td><label> 标题: </label></td>
                <td><input name="title" id="title" style="height:35px;width: 400px;" validtype="length[0,16]"
                           maxlength="16" value="${newsTitle.title}" class="easyui-validatebox" required="true"/></td>

            </tr>

            <tr>
                <td><label> 图片: </label></td>
                <td>
                    <input name="pic" id="pic" type="hidden" required value="${newsTitle.pic}"/>
                    <br>
                    <br>
                    <div id="uploader-item">
                        <!--用来存放item-->
                        <div id="fileList"></div>
                        <div id="filePicker">上传图片</div>
                        <span id="process" style="margin-left: 200px;display: none">正在上传...</span>
                    </div>

                    <br>
                    <br>
                    <div id="pic_div" <c:if test="${ empty newsTitle.pic}"> style="display: none" </c:if>><a href=""
                                                                                                             id="hh"
                                                                                                             target="_blank">
                        <img height="100px;" src="<%=basePath2%>${newsTitle.pic}" width="100px;" id="tem_pic"></a>
                    </div>

                </td>

            </tr>

            <tr>
                <td><label> 内容: </label></td>

                <td>

                    <textarea name="content" id="content" required="true" cols="180" rows="6" style="height: 300px;" required="true"
                              class="textareaeasyui-validatebox">${newsTitle.content}</textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
    UM.getEditor('content').destroy();
    var um = UM.getEditor('content', {
        <%--imageUrl: "<%=basePath2%>common/upload/uploadImage?pt=news",--%>
        <%--imagePath: "<%=basePath2%>upload/news",--%>
        <%--focus: true--%>
    });

    var BASE_URL = "<%=basePath2%>";


</script>

<script type="text/javascript" src="<%=basePath2%>js/upload/webuploader.js"/>
<script type="text/javascript" src="<%=basePath2%>js/upload/news/uploader.js"/>
</body>
</html>

