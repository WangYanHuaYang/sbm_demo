package com.wyhy.sbm_demo.controller;

import com.wyhy.sbm_demo.Util.Msg;
import com.wyhy.sbm_demo.model.Permission;
import com.wyhy.sbm_demo.service.PermissionService;
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
@Api(tags = {"Permission 接口"})
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService baseService;

    /**
    * @Description: 新增 Permission
    * @Param: [permission]
    * @Author: wyhy
    * @Date: 2018/9/30
    */
    @ApiOperation("新增 Permission")
    @RequestMapping(value = "savePermission",method = RequestMethod.PUT)
    @ResponseBody
    private Msg savePermission(@ModelAttribute("permission") Permission permission){
        boolean state=baseService.save(permission);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 删除 Permission (非逻辑删除)
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("删除 Permission (非逻辑删除)")
    @RequestMapping(value = "deletePermission",method = RequestMethod.DELETE)
    @ResponseBody
    private Msg deletePermission(@RequestParam(value = "id",defaultValue = "no")String id){
        boolean state=baseService.removeById(id);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 修改 Permission
     * @Param: [permission]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("修改 Permission")
    @RequestMapping(value = "updatePermission",method = RequestMethod.POST)
    @ResponseBody
    private Msg updatePermission(@ModelAttribute("permission")Permission permission){
        boolean state=baseService.updateById(permission);
        if (state){
            return Msg.SUCCESS();
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 多条件查询 Permission
     * @Param: [permission]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("多条件查询 Permission")
    @RequestMapping(value = "selectPermissions",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectPermissions(@ModelAttribute("permission")Permission permission,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "1")Integer pageSize){
        Page<Permission> page=new Page<Permission>(pageNum,pageSize);
        IPage<Permission> state=baseService.page(page,new QueryWrapper<Permission>().setEntity(permission));
        if (state.getSize()>0){
            return Msg.SUCCESS().add("resultSet",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 根据id查询 Permission
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("根据id查询 Permission")
    @RequestMapping(value = "selectPermissionById",method = RequestMethod.POST)
    @ResponseBody
    private Msg selectPermissionById(@RequestParam(value = "id",defaultValue = "no")String id){
    Permission state=baseService.getById(id);
        if (state!=null){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导入 Permission
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导入 Permission")
    @RequestMapping(value = "importPermission",method = RequestMethod.POST)
    @ResponseBody
    private Msg importPermission(@RequestParam(value = "file")MultipartFile file){
        boolean state=baseService.imputPermissions(file);
        if (state){
            return Msg.SUCCESS().add("result",state);
        }else{
            return Msg.FAIL();
        }
    }

    /**
     * @Description: 导出 Permission
     * @Param: [id]
     * @Author: wyhy
     * @Date: 2018/9/30
     */
    @ApiOperation("导出 Permission")
    @RequestMapping(value = "exportPermission",method = RequestMethod.GET)
    @ResponseBody
    private void exportPermission(@ModelAttribute("permission")Permission permission,HttpServletResponse response){
        List<Permission> resultSet=baseService.list(new QueryWrapper<Permission>().setEntity(permission));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FileUtil.exportExcel(resultSet,permission.toString(),"1",Permission.class,LocalDateTime.now().format(df)+".xlsx",response);
    }

}
