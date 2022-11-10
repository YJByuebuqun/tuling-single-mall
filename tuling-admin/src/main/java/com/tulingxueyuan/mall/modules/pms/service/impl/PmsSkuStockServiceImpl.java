package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsSkuStock;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsSkuStockMapper;
import com.tulingxueyuan.mall.modules.pms.service.PmsSkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements PmsSkuStockService {
    /**
     * url:'/sku/'+pid,
     * method:'get',
     * params:params
     * 描述：编辑查询sku库存信息
     */
    @Override
    public List<PmsSkuStock> fetchSkuStockList(Long pid, String keyword) {
        QueryWrapper<PmsSkuStock> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PmsSkuStock::getProductId, pid);
        if (!StrUtil.isBlank(keyword)) {
            queryWrapper.lambda().like(PmsSkuStock::getSkuCode, keyword);
        }
        List<PmsSkuStock> list = this.list(queryWrapper);

        return list;
    }

    /**
     * url:'/sku/update/'+pid,
     * method:'post',
     * data:data
     * 描述：编辑sku库存信息
     */
    @Override
    @Transactional
    public Boolean updateSkuStockList(Long pid, List<PmsSkuStock> skuStockList) {
        boolean update = false;
        for (PmsSkuStock skuStock : skuStockList) {
            UpdateWrapper<PmsSkuStock> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda().set(PmsSkuStock::getStock, skuStock.getStock()).eq(PmsSkuStock::getSkuCode,skuStock.getSkuCode()).eq(PmsSkuStock::getProductId,pid);
            updateWrapper.lambda().set(PmsSkuStock::getLowStock, skuStock.getLowStock()).eq(PmsSkuStock::getSkuCode,skuStock.getSkuCode()).eq(PmsSkuStock::getProductId,pid);
            updateWrapper.lambda().set(PmsSkuStock::getPrice, skuStock.getPrice()).eq(PmsSkuStock::getSkuCode,skuStock.getSkuCode()).eq(PmsSkuStock::getProductId,pid);
            update = this.update(updateWrapper);
        }
        return update;
    }
}
