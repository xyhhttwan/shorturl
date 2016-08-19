<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
    <form id="dataform" method="post">

        <c:if test="${not empty newsTitle}">
            <input type="hidden" name="id" value="${news.id}" id="id">
            <input type="hidden" name="status" value="${news.status}" id="status">

        </c:if>
        <table>
            <tr>
                <td><label> 标题: </label></td>
                <td><input name="title" id="title" style="height:35px;width: 400px;" validtype="length[0,16]"
                           maxlength="16" value="${news.title}" class="easyui-validatebox" required="true"/></td>

            </tr>

            <tr>
                <td><label> 类型: </label></td>
                <td>

                    <input class="easyui-combobox" editable="false" name="newsType" id="newsType" required data-options="
                    valueField: 'label',
                    textField: 'value',
                    data: [{
                        label: '0',
                        value: '图片新闻' <c:if test="${ empty news or news.newsType eq 0}">,selected:true</c:if>
                    },{
                        label: '1',
                        value: '文字新闻'<c:if test="${ news.newsType eq 1}">,selected:true</c:if>
                    }]" />
                    <%--<select class="easyui-combobox" style="width:150px" name="newsType" id="newsType">--%>
                        <%--<option value="0">图片新闻</option>--%>
                        <%--<option value="1">文字新闻</option>--%>
                    <%--</select>--%>
                </td>
            </tr>

            <tr id="tb_img">
                <td><label> 图片: </label></td>
                <td>
                    <input name="pic" id="pic" type="hidden" required value="${news.pic}"/>
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
                    <div id="pic_div" <c:if test="${ empty news.pic}"> style="display: none" </c:if>><a href=""
                                                                                                        id="hh"
                                                                                                        target="_blank">
                        <img height="100px;" src="<%=basePath2%>${news.pic}" width="100px;" id="tem_pic"></a>
                    </div>

                </td>

            </tr>
            <br>
            <tr>
                <td><label> 内容: </label></td>

                <td>

                    <textarea name="content" id="content" cols="180" rows="6" style="height: 300px;"
                    >${news.content}</textarea>
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

    $('#newsType').combobox({
        onChange: function(record){
            if (record=="1"){
                $("#tb_img").hide();
            }else{
                $("#tb_img").show();
            }
        }


    });

</script>

<script type="text/javascript" src="<%=basePath2%>js/upload/webuploader.js"/>
<script type="text/javascript" src="<%=basePath2%>js/upload/news/uploader.js"/>


