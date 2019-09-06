package com.wyhy.sbm_demo.controller;

import com.wyhy.sbm_demo.Util.Msg;
import com.wyhy.sbm_demo.model.Role;
import com.wyhy.sbm_demo.service.RoleService;
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
@Api(tags = {"Role 接口"})
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService baseService;

    /**
    * @Description: 新增 Role
    * @Param: [role]
    * @Author: wyhy
    * @Date: 2018/9/30
    */
    @ApiOperation("新增 Role")
    @RequestMapping(value = "saveRole",method = RequestMethod.PUT)
    @ResponseBody
    private Msg saveRole(@ModelAttribute("role") Role role){
        boolean state=baseService.save(role);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 删除 Role (非逻辑删除)
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("删除 Role (非逻辑删除)")
    @RequestMapping(value = "deleteRole",method = RequestMethod.DELETE)
    @ResponseBody
    private Msg deleteRole(@RequestParam(value = "id",defaultValue = "no")String id){
        boolean state=baseService.removeById(id);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 修改 Role
     * @Param: [role]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("修改 Role")
    @RequestMapping(value = "updateRole",method = RequestMethod.POST)
    @ResponseBody
    private Msg updateRole(@ModelAttribute("role")Role role){
        boolean state=baseService.updateById(role);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 多条件查询 Role
     * @Param: [role]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("多条件查询 Role")
    @RequestMapping(value = "selectRoles",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectRoles(@ModelAttribute("role")Role role,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "1")Integer pageSize){
        Page<Role> page=new Page<Role>(pageNum,pageSize);
        IPage<Role> state=baseService.page(page,new QueryWrapper<Role>().setEntity(role));
        if (state.getSize()>0){
            return Msg.SUCCESS().add("resultSet",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 根据id查询 Role
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("根据id查询 Role")
    @RequestMapping(value = "selectRoleById",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectRoleById(@RequestParam(value = "id",defaultValue = "no")String id){
    Role state=baseService.getById(id);
        if (state!=null){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导入 Role
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导入 Role")
    @RequestMapping(value = "importRole",method = RequestMethod.POST)
    @ResponseBody
    private Msg importRole(@RequestParam(value = "file")MultipartFile file){
        boolean state=baseService.imputRoles(file);
        if (state){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导出 Role
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导出 Role")
    @RequestMapping(value = "exportRole",method = RequestMethod.GET)
    @ResponseBody
    private void exportRole(@ModelAttribute("role")Role role,HttpServletResponse response){
        List<Role> resultSet=baseService.list(new QueryWrapper<Role>().setEntity(role));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FileUtil.exportExcel(resultSet,role.toString(),"1",Role.class,LocalDateTime.now().format(df)+".xlsx",response);
    }

}
