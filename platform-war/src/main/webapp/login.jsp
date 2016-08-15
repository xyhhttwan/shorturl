
<%@ page language="java" import="java.util.*" pageEncoding="Utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
+ request.getServerName() + ":" + request.getServerPort()
+ path + "/";
%>
<html>
    <head>

        <meta charset="utf-8">
        <title>platform后台管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%--<link rel="bookmark"  type="image/x-icon"  href="<%=basePath%>images/front/logo_title.jpg"/>--%>
        <%--<link rel="shortcut icon" href="<%=basePath%>images/front/logo_title.jpg">--%>
        <meta name="description" content="platform 后台管理系统">
        <meta name="author" content="xyhhttwan@163.com">

        <!-- CSS -->
        <link rel="stylesheet" href="<%=basePath%>js/assets/css/reset.css">
        <link rel="stylesheet" href="<%=basePath%>js/assets/css/supersized.css">
        <link rel="stylesheet" href="<%=basePath%>js/assets/css/style.css">
        <link rel="stylesheet" href="<%=basePath%>js/assets/js/jquery.alert.css">
        <script src="<%=basePath%>js/assets/js/jquery-1.8.2.min.js" ></script>
        <script type="text/javascript" src="<%=basePath %>js/assets/js/jquery.alert.js"></script>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="<%=basePath%>js/assets/js/html5.js"></script>
        <![endif]-->
        <script type="text/javascript">
            var BASE_URL = "<%=basePath%>";
            function login(){
                var userName =$("#userName").val();
                var password = $("#password").val();
                var verifyCode = $("#verifyCode").val();
         /*       if(userName==""){
                    alert("登录名不能为空");
                    $("#userName").focus();
                    return;
                }
                if(password==""){
                    alert("密码不能为空");
                    $("#password").focus();
                    return;
                }
                if(verifyCode==""){
                    alert("验证码不能为空");
                    $("#verifyCode").focus();
                    return;
                }*/
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "<%=basePath%>login.do",
                    data: {userName:userName,password:password,verifyCode:verifyCode},
                    success: function (data) {
                        if(data.result=="true"){
                            window.location="<%=basePath%>backstage/main";
                            return;
                        }else{
                            alert(data.message);
                        }
                    },
                    error: function(data) {
                        alert(data.message);
                    }
                });
            }
            $(function() {

                $("#verify_img").bind('click', function() {
                    var url = '<%=basePath%>verifyCode.do?t=' + Math.random();
                    $('#verify_img').attr('src', url);
                });
                //防止右边出现登录页面
                if (window.parent!=null && (window.parent.document.URL!=document.URL)){
                    window.parent.location= document.URL;
                }
            });
        </script>
    </head>

    <body style="background: url('<%=basePath%>js/assets/img/1.jpg')">
        <div class="page-container">
            <form action="" method="post">
            <h1>platform后台管理系统</h1>

                <input type="text" name="userName" id="userName" class="username" placeholder="请输入您的用户名！">
                <input type="password" id="password" name="password" class="password" placeholder="请输入您的用户密码！">
                <input type="Captcha" class="Captcha" value="" id="verifyCode" name="verifyCode" class="Captcha" placeholder="请输入验证码！">
                <img  class="Captcha" style="margin-top: 30px; padding-left: 5px" id="verify_img"  width="95" src="<%=basePath%>verifyCode.do" href="javascript:void(0);" alt="点击更换验证码">
                <button type="submit" id="submit_button" class="submit_button" >登录</button>
                <div class="error"><span>+</span></div>
            </form>
            </div>

        </div>
		
        <!-- Javascript -->

        <script src="<%=basePath%>js/assets/js/supersized.3.2.7.min.js" ></script>
        <script src="<%=basePath%>js/assets/js/supersized-init.js" ></script>
        <script src="<%=basePath%>js/assets/js/scripts.js" ></script>

    </body>
</html>

