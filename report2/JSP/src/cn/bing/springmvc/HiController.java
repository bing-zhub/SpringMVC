package cn.bing.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class HiController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String userRegister(ModelMap modelMap) {
        modelMap.put("msg", "�û�ע��");
        modelMap.put("button","���ע��");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerResult(String name,
                                 String tel,
                                 String gender,
                                 ModelMap modelMap){

        modelMap.put("tel",tel);
        modelMap.put("name", name);
        modelMap.put("male", gender.equals("male")?"checked":"");
        modelMap.put("female", gender.equals("female")?"checked":"");
        modelMap.put("msg", "ע��ɹ�");
        modelMap.put("button", "�����ע��");
        modelMap.put("btn_attr","disabled");
        return "register";
    }


}