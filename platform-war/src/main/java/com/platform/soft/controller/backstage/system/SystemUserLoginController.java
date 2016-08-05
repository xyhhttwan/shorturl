package com.platform.soft.controller.backstage.system;

import com.platform.soft.api.backstage.ex.ISystemUserService;

import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.domain.RspMsg;
import com.platform.soft.base.domain.VerifyCode;
import com.platform.soft.base.enums.MessageCode;
import com.platform.soft.base.springmvc.BackStageController;
import com.platform.soft.common.utils.encryptUtils.EncryptUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户登录controller
 */
@Controller
@Scope("prototype")
public class SystemUserLoginController extends BackStageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemUserLoginController.class);
    @Autowired
    private ISystemUserService systemUserService;

    private ResponseMessage rsgMsg = new ResponseMessage();

    /**
     * 用户登录
     *
     * @param userName(用户名)
     * @param password(密码)
     * @param verifyCode(验证码)
     * @return String
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public RspMsg index(String userName, String password, String verifyCode) {
        LOGGER.debug("userName:" + userName + ",password:" + ",verifyCode:" + verifyCode);

        //判断参数是否为空
        if (StringUtils.isAnyEmpty(userName, password, verifyCode)) {
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.PARAM_EMPTY);
            return rsgMsg;
        }
        //判断验证码是否正确
        String sessionCode = (String) request.getSession().getAttribute(VerifyCode.VERIFY_TYPE_COMMENT);
        if (null == sessionCode || !sessionCode.equalsIgnoreCase(verifyCode)) {
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.VERIFYCODE_EMPTY);
            return rsgMsg;
        }

        //shiro 权限登录验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(
                userName, EncryptUtils.encodeMD5String(password));
        //token.setRememberMe(true);
        try {
            subject.login(token);
            rsgMsg.setIsSuccess(true);
            rsgMsg.setMsgCode(MessageCode.DEFAULT_SUCCESS);
            return rsgMsg;
        } catch (UnknownAccountException e) {//账号不存在
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.USER_EMPTY);
        } catch (IncorrectCredentialsException e) {//用户名/密码错误
            LOGGER.debug(e.getMessage(),e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.USER_EMPTY);
        } catch (ExcessiveAttemptsException e) {
            LOGGER.debug(e.getMessage(),e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.LOGIN_ERROR_TOO_MUCH);
        } catch (Exception e) {
            LOGGER.debug(e.getMessage(),e);
            rsgMsg.setIsSuccess(false);
            rsgMsg.setMsgCode(MessageCode.UNKNOWN_ERROR);
        }

        return rsgMsg;
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:"+getRootPath()+"login.jsp";
    }


}
