package com.platform.soft.wx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.message.message.NewsMessage;
import weixin.popular.bean.xmlmessage.XMLMessage;
import weixin.popular.bean.xmlmessage.XMLTextMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baixiaobin on 16/8/9.
 */
@Controller
@RequestMapping("wx")
public class MessageCallBackController {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //从官方获取
    private String token = "10c88d5deaac140f5d7d36c0adb41a1e";

    //重复通知过滤
    private static ExpireKey expireKey = new DefaultExpireKey();


    @RequestMapping("callback")
    public  void receiveFirst(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        //首次请求申请验证,返回echostr
        if(echostr!=null){
            outputStreamWrite(outputStream,echostr);
            return;
        }

        //验证请求签名
        if(!signature.equals(SignatureUtil.generateEventMessageSignature(token,timestamp,nonce))){
            System.out.println("The request signature is invalid");
            return;
        }

        if(inputStream!=null){
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,inputStream);

            //消息类型
            String messagType =  eventMessage.getMsgType();

            String event = eventMessage.getEvent();

            XMLMessage xmlTextMessage=null;

            //关注恢复
            if(event.equals("subscribe")){
                //创建回复
                xmlTextMessage = new XMLTextMessage(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        "您好,欢迎关注,请输入订单号查询订单");
                return;
            }

            if(messagType.equals("text")){

                String key = eventMessage.getFromUserName() + "__"
                        + eventMessage.getToUserName() + "__"
                        + eventMessage.getMsgId() + "__"
                        + eventMessage.getCreateTime();

                if(expireKey.exists(key)){
                    //重复通知不作处理
                    return;
                }else{
                    expireKey.add(key);
                }
                List<NewsMessage.Article> articles = new ArrayList<NewsMessage.Article>();
                NewsMessage.Article article = new NewsMessage.Article("订单查询","您的订单到底苏州保税区</br>点击查看详情","http://www.baidu.com","");
                articles.add(article);
                NewsMessage newsMessage = new NewsMessage("订单",articles);
                //创建回复
                xmlTextMessage = new XMLTextMessage(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        "你好,骚年");
                //回复
            }else{


                if(messagType.equals("subscribe")){

                }
                //创建回复
                 xmlTextMessage = new XMLTextMessage(
                        eventMessage.getFromUserName(),
                        eventMessage.getToUserName(),
                        "你好,骚年暂时没法处理你的请求");
            }

            xmlTextMessage.outputStreamWrite(outputStream);

            return;
        }
        outputStreamWrite(outputStream,"");
    }

    /**
     * 数据流输出
     * @param outputStream
     * @param text
     * @return
     */
    private boolean outputStreamWrite(OutputStream outputStream, String text){
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
