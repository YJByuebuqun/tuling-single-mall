package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @CreateTime: 2022-11-01  21:25
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RelationAttrInfoDTO对象", description = "商品分类中编辑商品获取筛选属性的对象")
public class RelationAttrInfoDTO {
    //商品类型ID
    private Long attributeCategoryId;
    //商品属性ID
    private Long attributeId;
}
