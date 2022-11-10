package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDetailDTO;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-11-08
 */
public interface PmsProductService extends IService<PmsProduct> {
    /**
     * /product/detail/${this.id}
     * 描述：展示sku和spu信息
     * 嘎嘎香
     */
    ProductDetailDTO getProductDetail(Long id);
}
