package com.wyhy.sbm_demo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ExcelTarget("permission")
@ApiModel(value="Permission对象", description="")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "权限名")
    @TableField("permissionName")
    @Excel(name = "权限名")
    private String permissionName;

    @ApiModelProperty(value = "权限代码")
    @TableField("permissionCode")
    @Excel(name = "权限代码")
    private String permissionCode;

    @ApiModelProperty(value = "权限类型 0-菜单 1- 按钮")
    @TableField("permissionType")
    @Excel(name = "权限类型 0-菜单 1- 按钮")
    private String permissionType;

    @ApiModelProperty(value = "上级权限ID")
    @TableField("permissionId")
    @Excel(name = "上级权限ID")
    private String permissionId;

    @ApiModelProperty(value = "模块链接")
    @Excel(name = "模块链接")
    private String href;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
