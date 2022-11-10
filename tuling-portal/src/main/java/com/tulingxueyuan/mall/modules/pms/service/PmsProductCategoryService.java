package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-11-08
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {
    /**
     * /home/menus
     * 描述：提供前台首页数据
     */
    List<HomeMenusDTO> getMenus();
}
