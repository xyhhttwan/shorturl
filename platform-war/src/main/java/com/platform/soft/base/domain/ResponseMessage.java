package com.platform.soft.base.domain;


import com.platform.soft.base.enums.MessageCode;

public class ResponseMessage extends RspMsg {

    private boolean isSuccess = false;
    private MessageCode msgCode = MessageCode.DEFAULT_FAILED;


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        super.setResult(String.valueOf(isSuccess));
        this.isSuccess = isSuccess;

    }

    public MessageCode getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(MessageCode msgCode) {
        super.setCode(msgCode.getCode());
        super.setMessage(msgCode.getMessage());
        this.msgCode = msgCode;
    }


}
