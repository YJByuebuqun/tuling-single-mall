package com.tulingxueyuan.mall.modules.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.mapper.SmsHomeAdvertiseMapper;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-11-08
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements SmsHomeAdvertiseService {
    /**
     * /home/menu_banner
     * 描述：提供前台首页数据
     * 设置banners
     */
    @Override
    public List<SmsHomeAdvertise> getBanners() {
        List<SmsHomeAdvertise> list = this.list(new QueryWrapper<SmsHomeAdvertise>().lambda().eq(SmsHomeAdvertise::getType, 1).orderByAsc(SmsHomeAdvertise::getSort));
        return list;
    }
}
