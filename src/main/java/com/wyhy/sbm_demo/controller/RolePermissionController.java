package com.wyhy.sbm_demo.controller;

import com.wyhy.sbm_demo.Util.Msg;
import com.wyhy.sbm_demo.model.RolePermission;
import com.wyhy.sbm_demo.service.RolePermissionService;
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
@Api(tags = {"RolePermission 接口"})
@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {

    @Autowired
    RolePermissionService baseService;

    /**
    * @Description: 新增 RolePermission
    * @Param: [role_permission]
    * @Author: wyhy
    * @Date: 2018/9/30
    */
    @ApiOperation("新增 RolePermission")
    @RequestMapping(value = "saveRolePermission",method = RequestMethod.PUT)
    @ResponseBody
    private Msg saveRolePermission(@ModelAttribute("role_permission") RolePermission role_permission){
        boolean state=baseService.save(role_permission);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 删除 RolePermission (非逻辑删除)
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("删除 RolePermission (非逻辑删除)")
    @RequestMapping(value = "deleteRolePermission",method = RequestMethod.DELETE)
    @ResponseBody
    private Msg deleteRolePermission(@RequestParam(value = "id",defaultValue = "no")String id){
        boolean state=baseService.removeById(id);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 修改 RolePermission
     * @Param: [role_permission]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("修改 RolePermission")
    @RequestMapping(value = "updateRolePermission",method = RequestMethod.POST)
    @ResponseBody
    private Msg updateRolePermission(@ModelAttribute("role_permission")RolePermission role_permission){
        boolean state=baseService.updateById(role_permission);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 多条件查询 RolePermission
     * @Param: [role_permission]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("多条件查询 RolePermission")
    @RequestMapping(value = "selectRolePermissions",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectRolePermissions(@ModelAttribute("role_permission")RolePermission role_permission,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "1")Integer pageSize){
        Page<RolePermission> page=new Page<RolePermission>(pageNum,pageSize);
        IPage<RolePermission> state=baseService.page(page,new QueryWrapper<RolePermission>().setEntity(role_permission));
        if (state.getSize()>0){
            return Msg.SUCCESS().add("resultSet",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 根据id查询 RolePermission
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("根据id查询 RolePermission")
    @RequestMapping(value = "selectRolePermissionById",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectRolePermissionById(@RequestParam(value = "id",defaultValue = "no")String id){
    RolePermission state=baseService.getById(id);
        if (state!=null){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导入 RolePermission
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导入 RolePermission")
    @RequestMapping(value = "importRolePermission",method = RequestMethod.POST)
    @ResponseBody
    private Msg importRolePermission(@RequestParam(value = "file")MultipartFile file){
        boolean state=baseService.imputRolePermissions(file);
        if (state){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导出 RolePermission
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导出 RolePermission")
    @RequestMapping(value = "exportRolePermission",method = RequestMethod.GET)
    @ResponseBody
    private void exportRolePermission(@ModelAttribute("role_permission")RolePermission role_permission,HttpServletResponse response){
        List<RolePermission> resultSet=baseService.list(new QueryWrapper<RolePermission>().setEntity(role_permission));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FileUtil.exportExcel(resultSet,role_permission.toString(),"1",RolePermission.class,LocalDateTime.now().format(df)+".xlsx",response);
    }

}
