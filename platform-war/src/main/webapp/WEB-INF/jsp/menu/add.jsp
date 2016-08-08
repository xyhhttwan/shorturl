<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path2 = request.getContextPath();
    String basePath2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path2 + "/";
%>
<!-- 弹出框 tbsUserDlg -->
<div id="tbsUserDlg">
    <form id="dataform" method="post">

        <c:if test="${not empty menu}">
            <input type="hidden" name="id" value="${menu.id}" id="id">
            <input type="hidden" name="status" value="${menu.status}" id="status">
        </c:if>
        <table>
            <tr>
                <td><label> 菜单名称: </label></td>
                <td><input name="menuName" id="menuName" validtype="length[0,16]" maxlength="16"
                           value="${menu.menuName}" class="easyui-validatebox" required="true"/></td>

            </tr>

            <tr>
                <td><label> 菜单URL: </label></td>
                <td><input name="url" value="${menu.url}" type="text" validtype="length[1,255]"
                           invalidMessage="有效长度1-255" id="url" class="easyui-validatebox"/></td>
            </tr>
            <tr>
                <td><label> icon: </label></td>
                <td><input name="icon" value="${menu.icon}" type="text" validtype="length[2,32]" id="icon"
                           class="easyui-validatebox"/></td>
            </tr>
            <tr>
                <td><label> 排序: </label></td>
                <td><input name="menuSort" value="${menu.menuSort}" required="true" type="text" validtype="number"
                           id="menuSort" class="easyui-validatebox"/></td>
            </tr>
            <tr>
                <td><label> 父菜单:</label></td>
                <td>
                    <input class="easyui-combotree" id="parentId" name="parentId" editable="false"
                           data-options="
						   valueField:'id',
						   textField:'text',
						   value:'<c:if test="${not empty menu}">${menu.parentId}</c:if><c:if test="${ empty menu}">${id}</c:if>',
						   url:'<%=basePath2%>backstage/system/menuManage/getMenuAll.do',
					       method:'get',
					       panelHeight:'auto'">
                </td>
            </tr>

        </table>
    </form>
</div>

