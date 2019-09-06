package com.wyhy.sbm_demo.dao;

import com.wyhy.sbm_demo.model.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wyhy
 * @since 2019-04-15
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByAdminUserId(int userId);

}
