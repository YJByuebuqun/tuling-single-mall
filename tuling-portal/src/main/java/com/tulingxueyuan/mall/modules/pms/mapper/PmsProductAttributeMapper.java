package com.tulingxueyuan.mall.modules.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductAttributeDetailDTO;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author XuShu
 * @since 2022-11-08
 */
public interface PmsProductAttributeMapper extends BaseMapper<PmsProductAttribute> {

    List<PmsProductAttributeDetailDTO> getList(Long id);
}
