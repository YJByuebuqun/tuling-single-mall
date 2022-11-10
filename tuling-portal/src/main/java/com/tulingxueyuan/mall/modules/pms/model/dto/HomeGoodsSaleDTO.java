package com.tulingxueyuan.mall.modules.pms.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @CreateTime: 2022-11-09  21:14
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HomeGoodsSaleDTO对象", description = "提供首页分类推荐和分类推荐的商品的对象")
public class HomeGoodsSaleDTO {
    private String pic;
    private String url;
    private String categoryName;

    private List<ProductDTO> productList;
}
