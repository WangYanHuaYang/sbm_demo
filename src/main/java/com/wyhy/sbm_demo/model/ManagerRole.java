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
@ExcelTarget("manager_role")
@ApiModel(value="ManagerRole对象", description="")
public class ManagerRole extends Model<ManagerRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理员id")
    private Integer managerid;

    @ApiModelProperty(value = "角色id")
    @Excel(name = "角色id")
    private Integer roelid;


    @Override
    protected Serializable pkVal() {
        return this.managerid;
    }

}
