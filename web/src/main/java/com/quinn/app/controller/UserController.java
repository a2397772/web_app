package com.quinn.app.controller;

import com.quinn.app.Test;
import com.quinn.app.model.entity.User;
import com.quinn.app.service.UserService;
import com.quinn.redis.IRedisService;
import com.quinn.redis.RedisConfig;
import com.quinn.redis.RedisUtil;
import com.quinn.payment.service.BankService;
import com.quinn.yfq.service.UserInfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Quinn
 * @date 2018/1/18
 * @package com.quinn.app.controller
 */
@Controller
@RequestMapping("/user/")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private BankService bankService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfService userInfService;

    @Autowired
    private com.quinn.yfq.service.UserService yfqUserService;

    @Autowired
    private Test test;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private IRedisService systemConfigRedisService;

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test(String id){
        redisUtil.set("sss",id);
        systemConfigRedisService.setNX("dddd","sd水电费水电费");
        systemConfigRedisService.put("hehehehe","ttttt",20000);
        Map<String,Object> resultMap = new HashMap();
        System.out.println(this.test.getSs());
        User user = userService.getById("123");
        this.userService.updateTest(user);
        resultMap.put("user",user);
        resultMap.put("userInf", userInfService.getById("8771a3a222924292b3d1a525c19d14af"));
        resultMap.put("bank",bankService.getById(id));
        resultMap.put("yfqUser",yfqUserService.getById(id));
        logger.info("web_app test.....");
        return resultMap;
    }
    @RequestMapping("/test2")
    @ResponseBody
    public Object test2(String id){
        Object o = bankService.getList();
        return o;
    }

    @RequestMapping("/user")
    public String user(){
        return "user/user";
    }

    @RequestMapping("page")
    public String page(HttpServletRequest request, ModelMap model){
        model.put("page", "page");
        request.setAttribute("page", "page");
        return "page";
    }
}
