package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @CreateTime: 2022-10-30  22:57
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ProductCateWithChildrenDTO对象", description = "商品列表中添加商品分类筛选属性对象")
public class ProductCateWithChildrenDTO {
    //商品分类Id
    private Long id;
    //商品分类名称
    private String name;
    //商品分类二级级联
    private List<PmsProductCategory> children;


}
