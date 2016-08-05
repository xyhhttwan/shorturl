<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="bookmark"  type="image/x-icon"  href="<%=basePath%>images/front/logo_title.jpg"/>
    <link rel="shortcut icon" href="<%=basePath%>images/front/logo_title.jpg">
    <title>农信网后台管理系统</title>
    <link href="<%=basePath%>jqueryeasyui/css/default.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" id="easyuiTheme" type="text/css" href="<%=basePath%>jqueryeasyui/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jqueryeasyui/js/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>jqueryeasyui/js/themes/color.css" />
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>jqueryeasyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jqueryeasyui/js/XiuCai.index.js"> </script>

    <script type="text/javascript">
        var _menus="";
        var baseUrl="<%=basePath%>";
        $(function(){

            $.ajax({
                type: "POST",
                dataType: "json",
                url: "<%=basePath%>background/system/menuManage/menuList.do",
                success: function (data) {
                    _menus=data;
                    InitLeftMenu();
                    tabClose();
                    tabCloseEven();
                },
                error: function(data) {
                    alert(data.message);
                }
            });
        })
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 200,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');
            var $oldPass = $('#oldPassword');
            if ($oldPass.val() == '') {
                msgShow('系统提示', '请输入当前密码！', 'warning');
                $oldPass.focus();
                return false;
            }
            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                $newpass.focus();
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                $rePass.focus();
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                $rePass.focus();
                return false;
            }

            if($newpass.val().length <6 ||$newpass.val().length>16 ){
                msgShow('系统提示', '密码长度(6-16)', 'warning');
                $rePass.focus();
                return false;
            }

            $.post('<%=basePath%>background/system/userManage/updateUserPassword.do?newPassword=' + $newpass.val()+'&oldPassword='+$oldPass.val(),
                    function(msg)
                    {
                        if(msg.result=="true"){
                            msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + $newpass.val(), 'info');
                            $newpass.val('');
                            $rePass.val('');
                            $oldPass.val('');
                            close();
                        }else{
                            msgShow('系统提示',msg.message, 'info');
                            return ;

                        }

                    }
                    , 'json'
            )
            
        }

        $(function() {

            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                    if (r) {
                        location.href = '<%=basePath%>logout.do';
                    }
                });
            })
        });
        /**
         * 样式
         */
        $(function(){
            var themeDiv=$("#layout_north_Menu_0 div div").click(function(){
                debugger;
                var themeName=$.trim($(this).html());
                var $easyuiTheme = $('#easyuiTheme');
                var url = $easyuiTheme.attr('href');
                var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
                $easyuiTheme.attr('href', href);

                var $iframe = $('iframe');
                if ($iframe.length > 0) {
                    for ( var i = 0; i < $iframe.length; i++) {
                        var ifr = $iframe[i];
                        $(ifr).contents().find('#easyuiTheme').attr('href', href);
                    }
                }

                $.cookie('easyuiThemeName', themeName, {
                    expires : 7
                });
            });
        });
    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="<%=basePath%>jqueryeasyui/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>

<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
    <img src="<%=basePath%>jqueryeasyui/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
</div>

    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(<%=basePath%>jqueryeasyui/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 ${currentUser.realName}
           <%-- <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_Menu_0'">更换皮肤</a>
		    <div id="layout_north_Menu_0">
                <div>black</div>
                <div>bootstrap</div>
                <div>default</div>
                <div>gray</div>
                <div>metro</div>
                <div>cupertino</div>
            </div>--%>
            <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="<%=basePath%>jqueryeasyui/images/blocks.gif" width="20" height="20" align="absmiddle" /> 农信网后台管理系统V1.0</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">农信网后台管理系统(2016-2016)</div>
    </div>
    <div region="west" split="true"  title="农信网后台管理系统" style="width:180px;" id="west">
			<div id="nav">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
				<h1 style="font-size:24px;">* 欢迎使用农信网后台系统</h1>
<h1 style="font-size:24px;">* 版本: <a style="font-size:24px;color:green;">V1.0</a></h1>
<h1 style="font-size:24px;">* 技术支持：西安致达信息科技有限公司</h1>

			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                    <td>当前密码：</td>
                    <td><input id="oldPassword" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="closeright">当前页右侧全部关闭</div>
		<div id="closeleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="exit">退出</div>
	</div>


</body>
</html>
