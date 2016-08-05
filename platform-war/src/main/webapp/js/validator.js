//扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
    //验证汉子
    CHS: {
        validator: function (value) {
            return /^[\u0391-\uFFE5]+$/.test(value);
        },
        message: '只能输入汉字'
    },
    //移动手机号码验证
    mobile: {//value值为文本框中的值
        validator: function (value) {
            var reg = /^1[3|4|5|8|9]\d{9}$/;
            return reg.test(value);
        },
        message: '输入手机号码格式不准确.'
    },
    //国内邮编验证
    zipcode: {
        validator: function (value) {
            var reg = /^[1-9]\d{5}$/;
            return reg.test(value);
        },
        message: '邮编必须是非0开始的6位数字.'
    },
    //用户账号验证(只能包括 _ 数字 字母)
    account: {//param的值为[]中值
        validator: function (value, param) {
            if (value.length < param[0] || value.length > param[1]) {
                $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                return false;
            } else {
                if (!/^[\w]+$/.test(value)) {
                    $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                    return false;
                } else {
                    return true;
                }
            }
        }, message: ''
    },
    QQ: {
        validator: function (value, param) {
            return /^[1-9]\d{4,10}$/.test(value);
        },
        message: 'QQ号码不正确'
    },
    safepass: {
        validator: function (value, param) {
            return safePassword(value);
        },
        message: '密码由字母和数字组成，至少6位'
    },
    equalTo: {
        validator: function (value, param) {
            return value == $(param[0]).val();
        },
        message: '两次输入的字符不一至'
    },
    number: {
        validator: function (value, param) {
            return /^\d+$/.test(value);
        },
        message: '请输入数字'
    },
    idcard: {
        validator: function (value, param) {
            return idCard(value);
        },
        message:'请输入正确的身份证号码'
    },
    checkWSDL: {
        validator: function(value,param){
            var reg = "^(http://|([0-9]{1,3}[.]{1}[0-9]{1,3}[.]{1}[0-9]{1,3}[.]{1}[0-9]{1,3}:[0-9]{1,4}))[/a-zA-Z0-9._%&:=(),?+]*[?]{1}wsdl$";
            return reg.test(value);
        },
        message: '请输入合法的WSDL地址'
    },
    checkIp : {// 验证IP地址
        validator : function(value) {
            var reg = /^((1?\d?\d|(2([0-4]\d|5[0-5])))\.){3}(1?\d?\d|(2([0-4]\d|5[0-5])))$/ ;
            return reg.test(value);
        },
        message : 'IP地址格式不正确'
    },
    selectValueRequired: {
        validator: function(value,param){
            if (value == "" || value.indexOf('请选择') >= 0) {
                return false;
            }else {
                return true;
            }
        },
        message: '该下拉框为必选项'
    },
    phoneAndMobile : {// 电话号码或手机号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^(13|15|18)\d{9}$/i.test(value);
        },
        message : '电话号码或手机号码格式不正确'
    },
    currency : {// 验证货币
        validator : function(value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message : '货币格式不正确'
    },
    money:{
        validator: function (value, param) {
            return (/^(([1-9]\d*)|\d)(\.\d{1,2})?$/).test(value);
        },
        message:'请输入正确的金额'

    },
    chinese : {// 验证中文
        validator : function(value) {
            return /^[\u0391-\uFFE5]+$/i.test(value);
        },
        message : '请输入中文'
    },
    chineseAndLength : {// 验证中文及长度
        validator : function(value) {
            var len = $.trim(value).length;
            if (len >= param[0] && len <= param[1]) {
                return /^[\u0391-\uFFE5]+$/i.test(value);
            }
        },
        message : '请输入中文'
    },
    english : {// 验证英语
        validator : function(value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message : '请输入英文'
    },
    englishAndLength : {// 验证英语及长度
        validator : function(value, param) {
            var len = $.trim(value).length;
            if (len >= param[0] && len <= param[1]) {
                return /^[A-Za-z]+$/i.test(value);
            }
        },
        message : '请输入英文'
    },
    englishUpperCase : {// 验证英语大写
        validator : function(value) {
            return /^[A-Z]+$/.test(value);
        },
        message : '请输入大写英文'
    },
    unnormal : {// 验证是否包含空格和非法字符
        validator : function(value) {
            return /.+/i.test(value);
        },
        message : '输入值不能为空和包含其他非法字符'
    },
    faxno : {// 验证传真
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : '传真号码不正确'
    },

    custom_remote: {
        validator: function(value, param) {
            var postdata = {};
            postdata[param[1]] = value;
            var m_result =$.ajax({ type: "POST",//http请求方式
                url: param[0],    //服务器段url地址
                data:postdata,      //发送给服务器段的数据
                dataType: "type", //告诉JQuery返回的数据格式
                async: false
            }).responseText;
            if (m_result == "False") {
                $.fn.validatebox.defaults.rules.custom_remote.message = param[2];
                return false;
            }
            else {
                return true;
            }
        },
        message: ''
    }
})
/* 密码由字母和数字组成，至少6位 */
var safePassword = function (value) {
    return !(/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value));
}

var idCard = function (value) {
    if (value.length == 18 && 18 != value.length) return false;
    var number = value.toLowerCase();
    var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
    var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
    if (re == null || a.indexOf(re[1]) < 0) return false;
    if (re[2].length == 9) {
        number = number.substr(0, 6) + '19' + number.substr(6);
        d = ['19' + re[4], re[5], re[6]].join('-');
    } else d = [re[9], re[10], re[11]].join('-');
    if (!isDateTime.call(d, 'yyyy-MM-dd')) return false;
    for (var i = 0; i < 17; i++) sum += number.charAt(i) * w[i];
    return (re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11));
}
var isDateTime = function (format, reObj) {
    format = format || 'yyyy-MM-dd';
    var input = this, o = {}, d = new Date();
    var f1 = format.split(/[^a-z]+/gi), f2 = input.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = input.split(/\d+/g);
    var len = f1.length, len1 = f3.length;
    if (len != f2.length || len1 != f4.length) return false;
    for (var i = 0; i < len1; i++) if (f3[i] != f4[i]) return false;
    for (var i = 0; i < len; i++) o[f1[i]] = f2[i];
    o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
    o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
    o.dd = s(o.dd, o.d, d.getDate(), 31);
    o.hh = s(o.hh, o.h, d.getHours(), 24);
    o.mm = s(o.mm, o.m, d.getMinutes());
    o.ss = s(o.ss, o.s, d.getSeconds());
    o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
    if (o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0) return false;
    if (o.yyyy < 100) o.yyyy += (o.yyyy > 30 ? 1900 : 2000);
    d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
    var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
    return reVal && reObj ? d : reVal;
    function s(s1, s2, s3, s4, s5) {
        s4 = s4 || 60, s5 = s5 || 2;
        var reVal = s3;
        if (s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
        if (s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
        return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
    }
};
/**
 * 扩展easyui validatebox的两个方法.移除验证和还原验证
 */
$.extend($.fn.validatebox.methods, {
    remove : function(jq, newposition) {
        return jq.each(function() {
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');
            // remove tip
            // $(this).validatebox('hideTip', this);
        });
    },
    reduce : function(jq, newposition) {
        return jq.each(function() {
            var opt = $(this).data().validatebox.options;
            $(this).addClass("validatebox-text").validatebox(opt);
            // $(this).validatebox('validateTip', this);
        });
    },
    validateTip : function(jq) {
        jq = jq[0];
        var opts = $.data(jq, "validatebox").options;
        var tip = $.data(jq, "validatebox").tip;
        var box = $(jq);
        var value = box.val();
        function setTipMessage(msg) {
            $.data(jq, "validatebox").message = msg;
        };
        var disabled = box.attr("disabled");
        if (disabled == true || disabled == "true") {
            return true;
        }
        if (opts.required) {
            if (value == "") {
                box.addClass("validatebox-invalid");
                setTipMessage(opts.missingMessage);
                $(jq).validatebox('showTip', jq);
                return false;
            }
        }
        if (opts.validType) {
            var result = /([a-zA-Z_]+)(.*)/.exec(opts.validType);
            var rule = opts.rules[result[1]];
            if (value && rule) {
                var param = eval(result[2]);
                if (!rule["validator"](value, param)) {
                    box.addClass("validatebox-invalid");
                    var message = rule["message"];
                    if (param) {
                        for (var i = 0; i < param.length; i++) {
                            message = message.replace(new RegExp("\\{" + i + "\\}", "g"), param[i]);
                        }
                    }
                    setTipMessage(opts.invalidMessage || message);
                    $(jq).validatebox('showTip', jq);
                    return false;
                }
            }
        }
        box.removeClass("validatebox-invalid");
        $(jq).validatebox('hideTip', jq);
        return true;
    },
    showTip : function(jq) {
        jq = jq[0];
        var box = $(jq);
        var msg = $.data(jq, "validatebox").message
        var tip = $.data(jq, "validatebox").tip;
        if (!tip) {
            tip = $("<div class=\"validatebox-tip\">" + "<span class=\"validatebox-tip-content\">" + "</span>" + "<span class=\"validatebox-tip-pointer\">" + "</span>" + "</div>").appendTo("body");
            $.data(jq, "validatebox").tip = tip;
        }
        tip.find(".validatebox-tip-content").html(msg);
        tip.css({
            display : "block",
            left : box.offset().left + box.outerWidth(),
            top : box.offset().top
        });
    },
    hideTip : function(jq) {
        jq = jq[0];
        var tip = $.data(jq, "validatebox").tip;
        if (tip) {
            tip.remove;
            $.data(jq, "validatebox").tip = null;
        }
    }
});

//$(".validatebox-tip").remove();
//$(".validatebox-invalid").removeClass("validatebox-invalid");
/*$.extend($.fn.validatebox.methods, {
    remove: function(jq, newposition){
        return jq.each(function(){
            $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus').unbind('blur');
        });
    },
    reduce: function(jq, newposition){
        return jq.each(function(){
            var opt = $(this).data().validatebox.options;
            $(this).addClass("validatebox-text").validatebox(opt);
        });
    }
});

//使用
$('#id').validatebox('remove'); //删除
$('#id').validatebox('reduce'); //恢复*/

$.extend($.fn.validatebox.defaults.rules,{
    TimeCheck:{
        validator:function(value,param){
            var s = $("input[name="+param[0]+"]").val();
            //因为日期是统一格式的所以可以直接比较字符串 否则需要Date.parse(_date)转换
            return value>=s;
        },
        message:'开始日期必须小于结束日期'
    }
});