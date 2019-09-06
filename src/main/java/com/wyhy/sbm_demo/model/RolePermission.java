package com.wyhy.sbm_demo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wyhy
 * @since 2019-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ExcelTarget("role_permission")
@ApiModel(value="RolePermission对象", description="")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色id")
    private Integer roleid;

    @ApiModelProperty(value = "权限id")
    @Excel(name = "权限id")
    private Integer permissionid;


    @Override
    protected Serializable pkVal() {
        return this.roleid;
    }

}
