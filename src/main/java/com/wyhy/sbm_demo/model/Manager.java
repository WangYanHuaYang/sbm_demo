package com.wyhy.sbm_demo.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

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
@ExcelTarget("manager")
@ApiModel(value="Manager对象", description="")
public class Manager extends Model<Manager> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "角色名")
    @TableField("managerName")
    @Excel(name = "角色名")
    private String managerName;

    @ApiModelProperty(value = "密码")
    @Excel(name = "密码")
    private String password;

    @ApiModelProperty(value = "状态 0 - 关闭 1 - 开启")
    @Excel(name = "状态 0 - 关闭 1 - 开启")
    private Integer state;

    private List<Role> roles;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
