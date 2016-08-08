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
        <input name="roleId" type="hidden" id="roleId" value="${role.id}">
        <table>
            <tr>
                <td><label> 角色: </label></td>
                <td>${role.roleName}</td>

            </tr>
            <tr>
                <td><label> 菜单: </label></td>
                <td>
                    <select id="menuIds" name="menuIds" class="easyui-combotree"
                            data-options="
						url:'<%=basePath2%>backstage/system/menuManage/getAllMenus.do',
						method:'get',
						checkbox :true,
						multiple:true,
						 cascadeCheck: false,
						 onLoadSuccess: function (node, data) {
                   			 $('#menuIds').combotree('tree').tree('collapseAll');
                   			 $('#menuIds').combotree(
                   			  'setValues',
                   			   [
                        		<c:forEach items="${userMenu}" var="userMenu">
									${userMenu.id},
			 					 </c:forEach>
                               ]
                   			 )
						},

						onCheck: function (node, checked) {
                              treeOnCheck(node,checked);
                            }
					     "

                            style="width:400px;">

                    </select>

                </td>

            </tr>
    </form>
</div>

