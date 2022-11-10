package com.tulingxueyuan.mall.modules.pms.service;

import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
public interface PmsSkuStockService extends IService<PmsSkuStock> {

    /**
     * url:'/sku/'+pid,
     * method:'get',
     * params:params
     * 描述：编辑查询sku库存信息
     */
    List<PmsSkuStock> fetchSkuStockList(Long pid,String keyword);
    /**
     * url:'/sku/update/'+pid,
     * method:'post',
     * data:data
     * 描述：编辑sku库存信息
     */
    Boolean updateSkuStockList(Long pid, List<PmsSkuStock> skuStockList);
}
