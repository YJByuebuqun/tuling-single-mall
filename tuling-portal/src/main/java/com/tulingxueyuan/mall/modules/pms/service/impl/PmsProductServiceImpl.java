package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.*;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductAttributeDetailDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductDetailDTO;
import com.tulingxueyuan.mall.modules.pms.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-11-08
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
    @Autowired
    PmsSkuStockService skuStockService;
    @Autowired
    PmsProductAttributeValueService productAttributeValueService;
    @Autowired
    PmsProductAttributeService productAttributeService;

    @Autowired
    PmsProductAttributeMapper pmsProductAttributeMapper;

    @Override
    public ProductDetailDTO getProductDetail(Long id) {

        //创建返回对象
        ProductDetailDTO productDetailDTO = new ProductDetailDTO();
        //获取对应商品基本信息
        PmsProduct byId = this.getById(id);
        //将商品基本信息字段复制到我们的productUpdateInitDTO
        BeanUtils.copyProperties(byId, productDetailDTO);
        //获取对应produceId的SKU价格数据
        List<PmsSkuStock> pmsSkuStockList = skuStockService.list(new QueryWrapper<PmsSkuStock>().lambda().eq(PmsSkuStock::getProductId, id));
        //获取对应produceId的SPU价格数据
        List<PmsProductAttributeDetailDTO> pmsProductAttributeDetailDTOList = pmsProductAttributeMapper.getList(id);

        //设置productUpdateInitDTO
        productDetailDTO.setProductAttributeValueList(pmsProductAttributeDetailDTOList);
        productDetailDTO.setSkuStockList(pmsSkuStockList);

        return productDetailDTO;
    }
}
