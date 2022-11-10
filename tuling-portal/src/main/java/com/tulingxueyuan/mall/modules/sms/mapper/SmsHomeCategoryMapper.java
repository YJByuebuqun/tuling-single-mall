package com.tulingxueyuan.mall.modules.sms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeGoodsSaleDTO;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2022-11-09
 */
public interface SmsHomeCategoryMapper extends BaseMapper<SmsHomeCategory> {

    List<HomeGoodsSaleDTO> getGoodsSale();
}
