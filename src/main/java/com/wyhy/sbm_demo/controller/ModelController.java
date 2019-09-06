package com.wyhy.sbm_demo.controller;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.wyhy.sbm_demo.Util.Msg;
import com.wyhy.sbm_demo.Util.MybatisAutoGenerator;
import com.wyhy.sbm_demo.model.ValidationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: sbm_demo
 * @description: 模板接口
 * @author: WYHY
 * @create: 2018-09-21 14:30
 **/
@Controller
@RequestMapping(value = "/system")
public class ModelController {

//    @Autowired
//    ModelService modelService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String Test(){
        return "/login";
    }

    @RequestMapping(value = "/modelValidation",method = RequestMethod.GET)
    @ResponseBody
    public String modelValidation(){
        return "test";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        Map map=new HashMap();
        return "/html/index";
    }

    @RequestMapping(value = "/maketable",method = RequestMethod.GET)
    @ResponseBody
    public Msg maketable(@RequestParam("tablename")String tablename){
        try {
            MybatisAutoGenerator.makeTable(tablename);
            return Msg.SUCCESS();
        } catch (MybatisPlusException e) {
            e.printStackTrace();
            return Msg.FAIL();
        }
    }
}
