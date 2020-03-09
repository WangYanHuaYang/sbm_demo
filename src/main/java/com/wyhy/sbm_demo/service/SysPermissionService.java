package com.wyhy.sbm_demo.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.wyhy.sbm_demo.dao.SysPermissionMapper;
import com.wyhy.sbm_demo.model.SysPermission;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 权限表 服务类
 * @author wyhy
 * @since 2020-01-03
 */
@Service
public class SysPermissionService extends BaseService<SysPermissionMapper, SysPermission> {

    /**
    * @Description: 导入 SysPermission (存在则刷新，不存在则新增)
    * @Param: [file]
    * @return: void
    * @Author: wyhy
    * @Date: 2020-01-03
    */
    public boolean imputSysPermissions(MultipartFile file) {
        ImportParams params = new ImportParams();
        boolean state=false;
        try {
            List<SysPermission> resultSet= ExcelImportUtil.importExcel(file.getInputStream(),SysPermission.class,params);
            state=saveOrUpdateBatch(resultSet,resultSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

}
