package com.wyhy.sbm_demo.controller;

import com.wyhy.sbm_demo.Util.Msg;
import com.wyhy.sbm_demo.model.Manager;
import com.wyhy.sbm_demo.service.ManagerService;
import io.swagger.annotations.Api;
import com.wyhy.sbm_demo.Util.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wyhy
 * @since 2019-04-15
 */
@Api(tags = {"Manager 接口"})
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService baseService;

    /**
    * @Description: 新增 Manager
    * @Param: [manager]
    * @Author: wyhy
    * @Date: 2018/9/30
    */
    @ApiOperation("新增 Manager")
    @RequestMapping(value = "saveManager",method = RequestMethod.PUT)
    @ResponseBody
    private Msg saveManager(@ModelAttribute("manager") Manager manager){
        boolean state=baseService.save(manager);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 删除 Manager (非逻辑删除)
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("删除 Manager (非逻辑删除)")
    @RequestMapping(value = "deleteManager",method = RequestMethod.DELETE)
    @ResponseBody
    private Msg deleteManager(@RequestParam(value = "id",defaultValue = "no")String id){
        boolean state=baseService.removeById(id);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 修改 Manager
     * @Param: [manager]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("修改 Manager")
    @RequestMapping(value = "updateManager",method = RequestMethod.POST)
    @ResponseBody
    private Msg updateManager(@ModelAttribute("manager")Manager manager){
        boolean state=baseService.updateById(manager);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 多条件查询 Manager
     * @Param: [manager]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("多条件查询 Manager")
    @RequestMapping(value = "selectManagers",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectManagers(@ModelAttribute("manager")Manager manager,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "1")Integer pageSize){
        Page<Manager> page=new Page<Manager>(pageNum,pageSize);
        IPage<Manager> state=baseService.page(page,new QueryWrapper<Manager>().setEntity(manager));
        if (state.getSize()>0){
            return Msg.SUCCESS().add("resultSet",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 根据id查询 Manager
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("根据id查询 Manager")
    @RequestMapping(value = "selectManagerById",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectManagerById(@RequestParam(value = "id",defaultValue = "no")String id){
    Manager state=baseService.getById(id);
        if (state!=null){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导入 Manager
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导入 Manager")
    @RequestMapping(value = "importManager",method = RequestMethod.POST)
    @ResponseBody
    private Msg importManager(@RequestParam(value = "file")MultipartFile file){
        boolean state=baseService.imputManagers(file);
        if (state){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导出 Manager
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导出 Manager")
    @RequestMapping(value = "exportManager",method = RequestMethod.GET)
    @ResponseBody
    private void exportManager(@ModelAttribute("manager")Manager manager,HttpServletResponse response){
        List<Manager> resultSet=baseService.list(new QueryWrapper<Manager>().setEntity(manager));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FileUtil.exportExcel(resultSet,manager.toString(),"1",Manager.class,LocalDateTime.now().format(df)+".xlsx",response);
    }

}
