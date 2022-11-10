package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @CreateTime: 2022-11-08  16:00
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductDTO对象", description = "提供部分首页需要的信息的对象")
public class ProductDTO {

    private Long id;

    private String name;

    private String pic;

    @ApiModelProperty(value = "促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "副标题")
    private String subTitle;
    @ApiModelProperty(value = "商品价")
    private BigDecimal price;
    @ApiModelProperty(value = "市场价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "控制价格是否要加上 xx元起 ，如果=1就不需要加，如果=0否则需要")
    private Integer sub;
}
