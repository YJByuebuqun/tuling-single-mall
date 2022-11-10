package com.tulingxueyuan.mall.modules.pms.controller;


import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.List;

/**
 * <p>
 * sku的库存 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@RestController
@RequestMapping("/sku")
public class PmsSkuStockController {

    @Autowired
    PmsSkuStockService skuStockService;

    /**
     * url:'/sku/'+pid,
     * method:'get',
     * params:params
     * 描述：查询sku库存信息
     */
    @GetMapping("/{pid}")
    public CommonResult<List<PmsSkuStock>> fetchSkuStock(@PathVariable("pid") Long pid,
                                                         @RequestParam(value = "keyword", required = false) String keyword) {
        List<PmsSkuStock> skuStockList = skuStockService.fetchSkuStockList(pid, keyword);
        return CommonResult.success(skuStockList);

    }

    /**
     * url:'/sku/update/'+pid,
     * method:'post',
     * data:data
     * 描述：编辑sku库存信息
     */
    @PostMapping("/update/{pid}")
    public CommonResult<Boolean> updateSkuStock(@PathVariable("pid") Long pid,
                                                @RequestBody List<PmsSkuStock> skuStockList) {
        Boolean result = skuStockService.updateSkuStockList(pid, skuStockList);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }
}

