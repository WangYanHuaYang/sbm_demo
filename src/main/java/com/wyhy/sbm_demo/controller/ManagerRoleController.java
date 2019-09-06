package com.wyhy.sbm_demo.controller;

import com.wyhy.sbm_demo.Util.Msg;
import com.wyhy.sbm_demo.model.ManagerRole;
import com.wyhy.sbm_demo.service.ManagerRoleService;
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
@Api(tags = {"ManagerRole 接口"})
@RestController
@RequestMapping("/manager-role")
public class ManagerRoleController {

    @Autowired
    ManagerRoleService baseService;

    /**
    * @Description: 新增 ManagerRole
    * @Param: [manager_role]
    * @Author: wyhy
    * @Date: 2018/9/30
    */
    @ApiOperation("新增 ManagerRole")
    @RequestMapping(value = "saveManagerRole",method = RequestMethod.PUT)
    @ResponseBody
    private Msg saveManagerRole(@ModelAttribute("manager_role") ManagerRole manager_role){
        boolean state=baseService.save(manager_role);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 删除 ManagerRole (非逻辑删除)
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("删除 ManagerRole (非逻辑删除)")
    @RequestMapping(value = "deleteManagerRole",method = RequestMethod.DELETE)
    @ResponseBody
    private Msg deleteManagerRole(@RequestParam(value = "id",defaultValue = "no")String id){
        boolean state=baseService.removeById(id);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 修改 ManagerRole
     * @Param: [manager_role]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("修改 ManagerRole")
    @RequestMapping(value = "updateManagerRole",method = RequestMethod.POST)
    @ResponseBody
    private Msg updateManagerRole(@ModelAttribute("manager_role")ManagerRole manager_role){
        boolean state=baseService.updateById(manager_role);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 多条件查询 ManagerRole
     * @Param: [manager_role]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("多条件查询 ManagerRole")
    @RequestMapping(value = "selectManagerRoles",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectManagerRoles(@ModelAttribute("manager_role")ManagerRole manager_role,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "1")Integer pageSize){
        Page<ManagerRole> page=new Page<ManagerRole>(pageNum,pageSize);
        IPage<ManagerRole> state=baseService.page(page,new QueryWrapper<ManagerRole>().setEntity(manager_role));
        if (state.getSize()>0){
            return Msg.SUCCESS().add("resultSet",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 根据id查询 ManagerRole
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("根据id查询 ManagerRole")
    @RequestMapping(value = "selectManagerRoleById",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectManagerRoleById(@RequestParam(value = "id",defaultValue = "no")String id){
    ManagerRole state=baseService.getById(id);
        if (state!=null){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导入 ManagerRole
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导入 ManagerRole")
    @RequestMapping(value = "importManagerRole",method = RequestMethod.POST)
    @ResponseBody
    private Msg importManagerRole(@RequestParam(value = "file")MultipartFile file){
        boolean state=baseService.imputManagerRoles(file);
        if (state){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导出 ManagerRole
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导出 ManagerRole")
    @RequestMapping(value = "exportManagerRole",method = RequestMethod.GET)
    @ResponseBody
    private void exportManagerRole(@ModelAttribute("manager_role")ManagerRole manager_role,HttpServletResponse response){
        List<ManagerRole> resultSet=baseService.list(new QueryWrapper<ManagerRole>().setEntity(manager_role));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FileUtil.exportExcel(resultSet,manager_role.toString(),"1",ManagerRole.class,LocalDateTime.now().format(df)+".xlsx",response);
    }

}
