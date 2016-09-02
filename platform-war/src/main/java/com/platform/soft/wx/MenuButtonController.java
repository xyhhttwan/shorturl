package com.platform.soft.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import weixin.popular.api.MenuAPI;
import weixin.popular.api.TokenAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.menu.Button;
import weixin.popular.bean.menu.MenuButtons;
import weixin.popular.bean.token.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信菜单的管理
 */
@Controller
@RequestMapping("wx/menuButton")
public class MenuButtonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuButtonController.class);



    @RequestMapping("create")
    public void addMenuButton(){


        MenuButtons menuButtons = new MenuButtons();


        //订单查询
        Button bt1 = new Button();
        bt1.setName("订单查询");
//        List<Button> subbt1 = new ArrayList<Button>();
//        bt1.setSub_button(subbt1);
//        //我
//        Button subbt1_1 = new Button();
//        subbt1_1.setName("易商付");
//        subbt1_1.setType("view");
//        subbt1_1.setUrl("http://www.esyto.com/wei/ysf_phone.html");
//        subbt1.add(subbt1_1);
         Token token = TokenAPI.token("wxcc6562f9601acaf4", "7fb51d2466240e7a50d746ac2f2b712b");
         String accessToken = token.getAccess_token();

        System.out.println("accessToken:"+accessToken);
        menuButtons.setButton(new Button[]{bt1});
        //BaseResult result = MenuAPI.menuCreate("f32g_8liNqVehWRdvJXknMAvZdj9ptjr3epbPGCoNcT_JK4yU1TcvnnbZGgwflyP6NVKjH4E6e5VePPY9PsGdqV0BVLOn53Fi2wJ2zQFnCIAOwwUBmpKG5z-tqLVcIUqPXTbAJADLH", menuButtons);
       // System.out.print(result.getErrmsg());


    }
}
//
//
//{
//        "button":[
//        {
//        "type":"click",
//        "name":"今日歌曲",
//        "key":"V1001_TODAY_MUSIC"
//        },
//        {
//        "name":"菜单",
//        "sub_button":[
//        {
//        "type":"view",
//        "name":"搜索",
//        "url":"http://www.soso.com/"
//        },
//        {
//        "type":"view",
//        "name":"视频",
//        "url":"http://v.qq.com/"
//        },
//        {
//        "type":"click",
//        "name":"赞一下我们",
//        "key":"V1001_GOOD"
//        }]
//        }]
//        }
