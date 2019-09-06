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
@ExcelTarget("role")
@ApiModel(value="Role对象", description="")
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "角色名")
    @Excel(name = "角色名")
    private String rolename;

    @ApiModelProperty(value = "角色代码")
    @Excel(name = "角色代码")
    private String rolecode;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
