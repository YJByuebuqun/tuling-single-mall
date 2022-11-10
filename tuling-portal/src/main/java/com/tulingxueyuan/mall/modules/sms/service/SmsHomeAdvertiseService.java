package com.tulingxueyuan.mall.modules.sms.service;

import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-11-08
 */
public interface SmsHomeAdvertiseService extends IService<SmsHomeAdvertise> {
    /**
     * /home/menu_banner
     * 描述：提供前台首页数据
     * 设置banners
     */
    List<SmsHomeAdvertise> getBanners();
}
