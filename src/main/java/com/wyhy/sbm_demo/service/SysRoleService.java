package com.wyhy.sbm_demo.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.wyhy.sbm_demo.dao.SysRoleMapper;
import com.wyhy.sbm_demo.model.SysRole;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 角色表 服务类
 * @author wyhy
 * @since 2020-01-03
 */
@Service
public class SysRoleService extends BaseService<SysRoleMapper, SysRole> {

    /**
    * @Description: 导入 SysRole (存在则刷新，不存在则新增)
    * @Param: [file]
    * @return: void
    * @Author: wyhy
    * @Date: 2020-01-03
    */
    public boolean imputSysRoles(MultipartFile file) {
        ImportParams params = new ImportParams();
        boolean state=false;
        try {
            List<SysRole> resultSet= ExcelImportUtil.importExcel(file.getInputStream(),SysRole.class,params);
            state=saveOrUpdateBatch(resultSet,resultSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

}
