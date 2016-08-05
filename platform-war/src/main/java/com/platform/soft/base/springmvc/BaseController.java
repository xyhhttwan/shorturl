package com.platform.soft.base.springmvc;

import com.platform.soft.base.domain.Constants;
import com.platform.soft.base.domain.ResponseMessage;
import com.platform.soft.base.enums.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公共的controller类
 *
 * @author baixb
 * @version [v1.0，2015-06-16]
 */
@Controller
@Scope("prototype")
public class BaseController {

    /**
     * request 对象
     */
    @Autowired
    protected HttpServletRequest request;

    /**
     * response 对象
     */
    @Autowired
    protected HttpServletResponse response;


    protected ResponseMessage msg = new ResponseMessage();



    /**
     * 默认日期的转换(如果子类有不同的需求可以重写这个方法)
     *
     * @param binder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(Constants.DATE_FORMAT_TEMPLATE), true));
    }


    /**
     * 获取项目路径
     *
     * @return String
     */
    protected String getRootPath() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(request.getScheme());
        buffer.append(Constants.PATH_STR_3);
        buffer.append(Constants.PATH_STR);
        buffer.append(request.getServerName());
        buffer.append(Constants.PATH_STR_3);
        buffer.append(request.getServerPort());
        buffer.append(request.getContextPath());
        buffer.append(Constants.PATH_STR_2);
        return buffer.toString();
    }


    /**
     * 提示操作失败
     *
     * @return ResponseMessage
     */
    protected ResponseMessage getErrorResponseMessage() {

        msg.setIsSuccess(false);
        msg.setMessage(MessageCode.DEFAULT_FAILED.getMessage());
        msg.setCode(MessageCode.DEFAULT_FAILED.getCode());
        return msg;
    }


    /**
     * 提示操作失败
     *
     * @return ResponseMessage
     */
    protected ResponseMessage getErrorResponseMessage(MessageCode messageCode) {

        msg.setIsSuccess(false);
        msg.setMessage(messageCode.getMessage());
        msg.setCode(messageCode.getCode());
        return msg;
    }

    /**
     * 提示操作成功
     *
     * @return ResponseMessage
     */
    protected ResponseMessage getSuccessResponseMessage() {
        msg.setIsSuccess(true);
        msg.setMessage(MessageCode.DEFAULT_SUCCESS.getMessage());
        msg.setCode(MessageCode.DEFAULT_SUCCESS.getCode());
        return msg;
    }


    /**
     * 提示系统异常
     *
     * @return ResponseMessage
     */
    protected ResponseMessage getExceptionResponseMessage() {
        msg.setMessage(MessageCode.SYSTEM_ERROR_EXCEPTION.getMessage());
        msg.setCode(MessageCode.SYSTEM_ERROR_EXCEPTION.getCode());
        return msg;
    }


}
