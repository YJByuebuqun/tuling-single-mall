package com.tulingxueyuan.mall.modules.pms.controller;

import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDetailDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @CreateTime: 2022-11-09  22:53
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    PmsProductService pmsProductService;

    /**
     * /product/detail/${this.id}
     * 描述：展示sku和spu信息
     */
    @GetMapping("/detail/{id}")
    public CommonResult getProductDetail(@PathVariable("id") Long id) {
        ProductDetailDTO productDetail = pmsProductService.getProductDetail(id);
        return CommonResult.success(productDetail);
    }
}
