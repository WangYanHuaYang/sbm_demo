package com.wyhy.sbm_demo.service;

import com.wyhy.sbm_demo.model.Permission;
import com.wyhy.sbm_demo.dao.PermissionMapper;
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
public class PermissionService extends BaseService<PermissionMapper, Permission> {

    /**
    * @Description: 导入 Permission (存在则刷新，不存在则新增)
    * @Param: [file]
    * @return: void
    * @Author: wyhy
    * @Date: 2019-04-15
    */
    public boolean imputPermissions(MultipartFile file) {
        ImportParams params = new ImportParams();
        boolean state=false;
        try {
            List<Permission> resultSet= ExcelImportUtil.importExcel(file.getInputStream(),Permission.class,params);
            state=saveOrUpdateBatch(resultSet,resultSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

}
