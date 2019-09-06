package com.wyhy.sbm_demo.service;

import com.wyhy.sbm_demo.model.ManagerRole;
import com.wyhy.sbm_demo.dao.ManagerRoleMapper;
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
public class ManagerRoleService extends BaseService<ManagerRoleMapper, ManagerRole> {

    /**
    * @Description: 导入 ManagerRole (存在则刷新，不存在则新增)
    * @Param: [file]
    * @return: void
    * @Author: wyhy
    * @Date: 2019-04-15
    */
    public boolean imputManagerRoles(MultipartFile file) {
        ImportParams params = new ImportParams();
        boolean state=false;
        try {
            List<ManagerRole> resultSet= ExcelImportUtil.importExcel(file.getInputStream(),ManagerRole.class,params);
            state=saveOrUpdateBatch(resultSet,resultSet.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

}
