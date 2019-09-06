package com.wyhy.sbm_demo.service;

import com.wyhy.sbm_demo.model.RolePermission;
import com.wyhy.sbm_demo.dao.RolePermissionMapper;
import org.springframework.stereotype.Service;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.springframework.web.multipart.MultipartFile;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  服务类
 * @author wyhy
 * @since 2019-04-15
 */
@Service
public class RolePermissionService extends BaseService<RolePermissionMapper, RolePermission> {

    /**
    * @Description: 导入 RolePermission (存在则刷新，不存在则新增)
    * @Param: [file]
    * @return: void
    * @Author: wyhy
    * @Date: 2019-04-15
    */
    public boolean imputRolePermissions(MultipartFile file) {
        ImportParams params = new ImportParams();
        boolean state=false;
        try {
            List<RolePermission> resultSet= ExcelImportUtil.importExcel(file.getInputStream(),RolePermission.class,params);
            state=saveOrUpdateBatch(resultSet,resultSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

}
