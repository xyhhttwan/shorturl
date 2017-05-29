$(function () {
    $('.nav-list li').click(function () {
        $(this).addClass('cur').siblings().removeClass('cur');
    });
    $(".index-text").focus(function () {
        $(this).parent().css({top: "-220px"}).animate({
            height: '170px'
        }, 'fast');
        $(this).animate({
            height: "120px"
        }, 'fast');
    });
    $('.index-btn').click(function () {
        var orderId = $("#orderId").val();
        if (orderId != "" && !isNaN(orderId) && orderId.length == 14) {
            location.href = "../html/cargo_trace.html?orderId=" + orderId;
        }
    })

    $('.log-btn').click(function () {
        getOrder();
    });

    $("#verify_img_code").bind('click', function () {
        var url = '../onlineOrderVerifyCode.do?t=' + Math.random();
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
            url: "../html/add_online_order",
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

                    var url = '../onlineOrderVerifyCode.do?t=' + Math.random();
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


function getOrder() {

    var orderId = $("#orderId").val();
    if (orderId != "" && !isNaN(orderId) && orderId.length == 14) {
        $("#search").html("正在查询...");
        $.ajax({
            type: 'POST',
            url: "../html/orderSearch",
            data: {orderId: orderId},
            cache: false,
            success: function (data) {
                $("#search").html("查询");
                $("#result").html(data);
            },
            error: function () {
                $("#search").html("查询");
                alert("服务器异常,请稍后查询");
                return;
            },

            dataType: "html"

        });
    }

}