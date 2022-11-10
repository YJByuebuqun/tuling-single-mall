package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
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
@ApiModel(value = "ProductAttrCateDto对象", description = "商品分类中添加商品筛选属性对象")
public class ProductAttributeCateDTO {
    private Long id;

    private String name;

    private List<PmsProductAttribute> productAttributeList;


}
