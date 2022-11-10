package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @CreateTime: 2022-11-05  15:29
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductConditionDTO对象", description = "商品列表筛选搜索列表对象")
public class PmsProductConditionDTO {
    //商品名称模糊查找对象
    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
    //商家状态
    private Integer publishStatus;
    //审核状态
    private Integer verifyStatus;
    //商品货号
    private String productSn;
    //商品分类id
    private Long productCategoryId;
    //商品品牌id
    private Long brandId;

}
