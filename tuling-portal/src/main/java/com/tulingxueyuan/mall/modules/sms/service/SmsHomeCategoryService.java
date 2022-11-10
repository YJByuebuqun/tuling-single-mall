package com.tulingxueyuan.mall.modules.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeGoodsSaleDTO;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-11-09
 */
public interface SmsHomeCategoryService extends IService<SmsHomeCategory> {
    /**
     * /home/goods_sale
     * 分类推荐和分类推荐的商品
     */
    List<HomeGoodsSaleDTO> getGoodsSale();
}
