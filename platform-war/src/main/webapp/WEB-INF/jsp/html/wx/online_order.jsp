<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>越海物流-微信在线下单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="<%=basePath%>css/wx/css.css"/>
</head>
<body>
<div class="layout">
    <div class="o-title">在线下订单</div>
    <div class="order-list">
        <ul class="yh-order">
            <li><span class="txt">客户公司：</span><input  maxlength="50" id="customer" name="customer" placeholder="请输入客户公司" type="text" class="text-1"/></li>
            <li><span class="txt">姓名：</span><input  maxlength="16" id="linkMan" name="linkMan" placeholder="请输入联系人姓名" type="text" class="text-1"/></li>
            <li><span class="txt">电话：</span><input maxlength="11" id="phoneNum" name="phoneNum" placeholder="请输入联系人电话"  type="text" class="text-1"/></li>
            <li><span class="txt">始发地：</span><input maxlength="32" id="mFrom" name="mFrom"  placeholder="请输入发货地" type="text" class="text-1"/></li>
            <li><span class="txt">目的地：</span><input maxlength="32" id="mTo" name="mTo" type="text"  placeholder="请输入目的地" class="text-1"/></li>
            <li><span class="txt">发货内容：</span><input maxlength="50" id="content" name="content" placeholder="发货内容" type="text" class="text-1"/></li>
            <li><span class="txt">备注信息：</span><textarea  id="remark" name="remark"   class="text-2"></textarea></li>
            <li><span class="txt">验证码：</span><input id="code" maxlength="4" name="code" type="text" class="text-1 w200"/><span class="yh-code-img">
                    <img src="<%=basePath%>/onlineOrderVerifyCode?t=<%=new Date()%>" id="verify_img_code" href="javascript:void(0);" alt="点击更换验证码"/></span></li>
            <li><span class="txt"></span><button type="submit" id="add_online_order" class="order-btn">确认下单</button></li>
        </ul>
    </div>
</div>
<div class="footer">Copyright by 版权所有 绍兴越海物流有限公司</div>
</body>
<script>
    $(function () {
        $("#verify_img_code").bind('click', function () {
            var url = '<%=basePath%>onlineOrderVerifyCode.do?t=' + Math.random();
            $('#verify_img_code').attr('src', url);
        });

        $("#add_online_order").bind('click', function () {

            var linkMan = $("#linkMan").val();
            var phoneNum = $("#phoneNum").val();
            var mFrom = $("#mFrom").val();
            var mTo = $("#mTo").val();
            var content = $("#content").val();
            var remark = $("#remark").val();
            var code = $("#code").val();
            var customer = $("#customer").val();
            if (customer == "") {
                $("#customer").addClass("input");
                $("#customer").focus();
                return;
            } else {
                $("#customer").removeClass("input");
            }

            if (linkMan == "") {
                $("#linkMan").addClass("input");
                $("#linkMan").focus();
                return;
            } else {
                $("#linkMan").removeClass("input");
            }
            if (phoneNum == "") {

                $("#phoneNum").addClass("input");
                $("#phoneNum").focus();
                return;
            } else {
                $("#phoneNum").removeClass("input");
            }

            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
            if (!myreg.test(phoneNum)) {
                $("#phoneNum").addClass("input");
                $("#phoneNum").focus();
                return;
            }
            if (mFrom == "") {

                $("#mFrom").addClass("input");
                $("#mFrom").focus();
                return;
            } else {
                $("#mFrom").removeClass("input");
            }
            if (mTo == "") {

                $("#mTo").addClass("input");
                $("#mTo").focus();
                return;
            } else {
                $("#mTo").removeClass("input");
            }
            if (content == "") {

                $("#content").addClass("input");
                $("#content").focus();
                return;
            } else {
                $("#content").removeClass("input");
            }

            if (remark == "") {

                $("#remark").addClass("input");
                $("#remark").focus();
                return;
            } else {
                $("#remark").removeClass("input");
            }

            if (remark.length > 50) {
                $("#remark").addClass("input");
                $("#remark").focus();
                return;
            }

            if (code == "") {

                $("#code").addClass("input");
                $("#code").focus();
                return;
            } else {
                $("#code").removeClass("input");
            }

                $("#search").html("正在提交...");
                $.ajax({
                    type: 'POST',
                    url: "<%=basePath%>html/wx/online_order/add_online_order",
                    data: {
                        linkMan: linkMan,
                        phoneNum: phoneNum,
                        mfrom: mFrom,
                        mto: mTo,
                        content: content,
                        remark: remark,
                        code: code,
                        customer:customer
                    },
                    cache: false,
                    success: function (data) {
                        $("#search").html("确认下单");
                        if (data.success == false) {
                            alert(data.message);
                        } else {
                            alert("提交成功");
                            $("#linkMan").val("");
                            $("#phoneNum").val("");
                            $("#mFrom").val("");
                            $("#mTo").val("");
                            $("#content").val("");
                            $("#remark").val("");
                            $("#code").val("");
                            $("#customer").val("");

                            var url = '<%=basePath%>onlineOrderVerifyCode.do?t=' + Math.random();
                            $('#verify_img_code').attr('src', url);
                        }
                    },
                    error: function () {
                        $("#search").html("确认下单");
                        alert("服务器异常,请稍后重试");
                        return;
                    },

                    dataType: "json"

                });


        });

    });
</script>
</html>