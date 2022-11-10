package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @CreateTime: 2022-10-31  21:55
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PmsProductCategoryDTO对象", description = "商品分类中添加商品筛选属性对象")
public class PmsProductCategoryDTO extends PmsProductCategory {

    private List<Long> productAttributeIdList;
}
