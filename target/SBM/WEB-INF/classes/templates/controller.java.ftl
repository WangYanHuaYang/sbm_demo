package ${package.Controller};

import com.wyhy.sbm_demo.Util.Msg;
import ${package.Entity}.${entity};
import ${package.ServiceImpl}.${table.serviceImplName};
import io.swagger.annotations.Api;
import com.wyhy.sbm_demo.Util.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Api(tags = {"${entity} 接口"})
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Autowired
    ${table.serviceImplName} baseService;

    /**
    * @Description: 新增 ${entity}
    * @Param: [${table.name}]
    * @return: com.wyhy.sbm_demo.Util.Msg
    * @Author: ${author}
    * @Date: 2018/9/30
    */
    @ApiOperation("新增 ${entity}")
    @RequestMapping(value = "save${entity}",method = RequestMethod.PUT)
    @ResponseBody
    private Msg save${entity}(@ModelAttribute("${table.name}") ${entity} ${table.name}){
        boolean state=baseService.save(${table.name});
        if (state){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    /**
     * @Description: 删除 ${entity} (非逻辑删除)
     * @Param: [id]
     * @return: com.wyhy.sbm_demo.Util.Msg
     * @Author: ${author}
     * @Date: 2018/9/30
     */
    @ApiOperation("删除 ${entity} (非逻辑删除)")
    @RequestMapping(value = "delete${entity}",method = RequestMethod.DELETE)
    @ResponseBody
    private Msg delete${entity}(@RequestParam(value = "id",defaultValue = "no")String id){
        boolean state=baseService.removeById(id);
        if (state){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    /**
     * @Description: 修改 ${entity}
     * @Param: [${table.name}]
     * @return: com.wyhy.sbm_demo.Util.Msg
     * @Author: ${author}
     * @Date: 2018/9/30
     */
    @ApiOperation("修改 ${entity}")
    @RequestMapping(value = "update${entity}",method = RequestMethod.POST)
    @ResponseBody
    private Msg update${entity}(@ModelAttribute("${table.name}")${entity} ${table.name}){
        boolean state=baseService.updateById(${table.name});
        if (state){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    /**
     * @Description: 多条件查询 ${entity}
     * @Param: [${table.name}]
     * @return: com.wyhy.sbm_demo.Util.Msg
     * @Author: ${author}
     * @Date: 2018/9/30
     */
    @ApiOperation("多条件查询 ${entity}")
    @RequestMapping(value = "select${entity}s",method = RequestMethod.POST)
    @ResponseBody
    private Msg select${entity}s(@ModelAttribute("${table.name}")${entity} ${table.name}){
        List<${entity}> state=baseService.list(new QueryWrapper<${entity}>().setEntity(${table.name}));
        if (state.size()>0){
            return Msg.success().add("resultSet",state);
        }else{
            return Msg.fail();
        }
    }

    /**
     * @Description: 根据id查询 ${entity}
     * @Param: [id]
     * @return: com.wyhy.sbm_demo.Util.Msg
     * @Author: ${author}
     * @Date: 2018/9/30
     */
    @ApiOperation("根据id查询 ${entity}")
    @RequestMapping(value = "select${entity}ById",method = RequestMethod.POST)
    @ResponseBody
    private Msg select${entity}ById(@RequestParam(value = "id",defaultValue = "no")String id){
    ${entity} state=baseService.getById(id);
        if (state!=null){
            return Msg.success().add("result",state);
        }else{
            return Msg.fail();
        }
    }

    /**
     * @Description: 导入 ${entity}
     * @Param: [id]
     * @return: com.wyhy.sbm_demo.Util.Msg
     * @Author: ${author}
     * @Date: 2018/9/30
     */
    @ApiOperation("导入 ${entity}")
    @RequestMapping(value = "import${entity}",method = RequestMethod.POST)
    @ResponseBody
    private Msg import${entity}(@RequestParam(value = "file")MultipartFile file){
        boolean state=baseService.imput${entity}s(file);
        if (state){
            return Msg.success().add("result",state);
        }else{
            return Msg.fail();
        }
    }

    /**
     * @Description: 导出 ${entity}
     * @Param: [id]
     * @return: com.wyhy.sbm_demo.Util.Msg
     * @Author: ${author}
     * @Date: 2018/9/30
     */
    @ApiOperation("导出 ${entity}")
    @RequestMapping(value = "export${entity}",method = RequestMethod.GET)
    @ResponseBody
    private void export${entity}(@ModelAttribute("${table.name}")${entity} ${table.name},HttpServletResponse response){
        List<${entity}> resultSet=baseService.list(new QueryWrapper<${entity}>().setEntity(${table.name}));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FileUtil.exportExcel(resultSet,${table.name}.toString(),"1",${entity}.class,LocalDateTime.now().format(df)+".xlsx",response);
    }

}
</#if>
