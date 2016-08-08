
jQuery(document).ready(function() {

    $('.page-container form').submit(function(){
        var username =  $(this).find('.username').val()
        var password = $(this).find('.password').val();
        var verifyCode = $(this).find('.Captcha').val();

        if(username == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '27px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.username').focus();
            });
            return false;
        }

        if(password == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '96px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.password').focus();
            });
            return false;
        }

        if(verifyCode == '') {
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', '191px');
            });
            $(this).find('.error').fadeIn('fast', function(){
                $(this).parent().find('.Captcha').focus();
            });
            return false;
        }
        $("#submit_button").html("正在登录...");
        $("#submit_button").attr("disabled",true);
        $.ajax({
            type: "POST",
            dataType: "json",
            url:BASE_URL+ "login.do",
            data: {userName:username,password:password,verifyCode:verifyCode},
            success: function (data) {
                if(data.result=="true"){
                    $("#submit_button").html("登录成功");
                    $("#submit_button").attr("disabled",false);
                    window.location=BASE_URL+"backstage/main";
                    return;
                }else{
                    $("#submit_button").attr("disabled",false);
                    $("#submit_button").html("登录");
                    jAlert(data.message, '错误提示');
                    return;
                }
            },
            error: function(data) {
                jAlert(data.message, '错误提示');
                return;
            }
        });
        return false;

    });


    $('.page-container form .username, .page-container form .password,.page-container form .Captcha').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });

});

