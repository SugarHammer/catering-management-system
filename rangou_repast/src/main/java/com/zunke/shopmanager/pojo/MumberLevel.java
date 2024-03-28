package com.zunke.shopmanager.pojo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 会员等级表(MumberLevel)实体类
 *
 * @author hy
 * @since 2021-09-15 08:44:35
 */

@ApiModel(value = "MumberLevel", description = "会员等级表")
public class MumberLevel implements Serializable {
    private static final long serialVersionUID = -98924145236209295L;
    /**
     * 会员等级id
     */
    @ApiModelProperty(name = "id", notes = "会员等级id", dataType = "Integer", required = true)
    private Integer id;
    /**
     * 会员等级名称
     */
    @ApiModelProperty(name = "numberLevelName", notes = "会员等级名称", dataType = "String", required = true)
    private String numberLevelName;
    /**
     * 累计消费金额
     */
    @ApiModelProperty(name = "mumberAddUp", notes = "累计消费金额", dataType = "Double", required = true)
    private Double mumberAddUp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberLevelName() {
        return numberLevelName;
    }

    public void setNumberLevelName(String numberLevelName) {
        this.numberLevelName = numberLevelName;
    }

    public Double getMumberAddUp() {
        return mumberAddUp;
    }

    public void setMumberAddUp(Double mumberAddUp) {
        this.mumberAddUp = mumberAddUp;
    }

}
