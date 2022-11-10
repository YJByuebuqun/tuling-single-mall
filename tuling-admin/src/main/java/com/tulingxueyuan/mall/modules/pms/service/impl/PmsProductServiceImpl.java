package com.tulingxueyuan.mall.modules.pms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.dto.ProductSaveParamsDTO;
import com.tulingxueyuan.mall.modules.pms.model.*;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductConditionDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductUpdateInitDTO;
import com.tulingxueyuan.mall.modules.pms.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
    @Autowired
    PmsProductMapper productMapper;

    @Autowired
    PmsProductCategoryService pmsProductCategoryService;
    @Autowired
    PmsMemberPriceService memberPriceService;
    @Autowired
    PmsProductLadderService productLadderService;
    @Autowired
    PmsProductFullReductionService productFullReductionService;
    @Autowired
    PmsSkuStockService skuStockService;
    @Autowired
    PmsProductAttributeValueService productAttributeValueService;


    @Override
    public Page getListBy(PmsProductConditionDTO productConditionDTO) {
        Page page = new Page(productConditionDTO.getPageNum(), productConditionDTO.getPageSize());

        QueryWrapper<PmsProduct> queryWrapper = new QueryWrapper<>();

        //当输入搜索不为空且不计空格
        if (!StrUtil.isBlank(productConditionDTO.getKeyword())) {
            queryWrapper.lambda().like(PmsProduct::getName, productConditionDTO.getKeyword());
        }
        //当商品货号不为空时
        if (!StrUtil.isBlank(productConditionDTO.getProductSn())) {
            queryWrapper.lambda().eq(PmsProduct::getProductSn, productConditionDTO.getProductSn());
        }

        if (productConditionDTO.getProductCategoryId() != null && productConditionDTO.getProductCategoryId() > 0) {
            queryWrapper.lambda().eq(PmsProduct::getProductCategoryId, productConditionDTO.getProductCategoryId());
        }

        if (productConditionDTO.getBrandId() != null && productConditionDTO.getBrandId() > 0) {
            queryWrapper.lambda().eq(PmsProduct::getBrandId, productConditionDTO.getBrandId());
        }

        if (productConditionDTO.getPublishStatus() != null) {
            queryWrapper.lambda().eq(PmsProduct::getPublishStatus, productConditionDTO.getPublishStatus());
        }

        if (productConditionDTO.getVerifyStatus() != null && productConditionDTO.getVerifyStatus() > 0) {
            queryWrapper.lambda().eq(PmsProduct::getVerifyStatus, productConditionDTO.getVerifyStatus());
        }
        queryWrapper.lambda().orderByAsc(PmsProduct::getSort);

        return this.page(page, queryWrapper);
    }

    @Override
    public Boolean deleteByIds(List<Long> ids) {
        boolean result = this.removeByIds(ids);
        return result;
    }

    /**
     * 改变商品标签状态（上架，新品，推荐）
     *
     * @param ids
     * @param status
     * @param getStatus
     * @return
     */
    @Override
    public Boolean updateStatus(List<Long> ids, Integer status, SFunction<PmsProduct, ?> getStatus) {
        UpdateWrapper<PmsProduct> pmsProductUpdateWrapper = new UpdateWrapper<>();
        pmsProductUpdateWrapper.lambda().set(getStatus, status).in(PmsProduct::getId, ids);
        boolean update = this.update(pmsProductUpdateWrapper);

        return update;
    }

    /**
     * 添加
     *
     * @param productSaveParamsDTO
     * @return
     */
    @Override
    @Transactional
    public boolean create(ProductSaveParamsDTO productSaveParamsDTO) {
        // 1. 保存商品基本信息 --商品主表
        PmsProduct product = productSaveParamsDTO;
        product.setId(null);
        boolean result = this.save(product);
        if (result) {

            // 为了解决 前端会传入其他促销方式的空数据进来
            switch (product.getPromotionType()) {
                case 2:
                    // 2. 会员价格
                    SaveManyList(productSaveParamsDTO.getMemberPriceList(), product.getId(), memberPriceService);
                    break;
                case 3:
                    // 3. 阶梯价格
                    SaveManyList(productSaveParamsDTO.getProductLadderList(), product.getId(), productLadderService);
                    break;
                case 4:
                    // 4. 减满价格
                    SaveManyList(productSaveParamsDTO.getProductFullReductionList(), product.getId(), productFullReductionService);
                    break;
            }
            // 5. sku
            SaveManyList(productSaveParamsDTO.getSkuStockList(), product.getId(), skuStockService);

            // 6 spu
            SaveManyList(productSaveParamsDTO.getProductAttributeValueList(), product.getId(), productAttributeValueService);

        }
        return result;
    }

    /**
     * 点击编辑获得对应id的商品的信息返回
     * url:'/product/updateInfo/'+id,
     * method:'get',
     */
    @Override
    @Transactional
    public ProductUpdateInitDTO getProductById(Long id) {
        //创建返回对象
        ProductUpdateInitDTO productUpdateInitDTO = new ProductUpdateInitDTO();
        //获取对应商品基本信息
        PmsProduct byId = this.getById(id);
        //将商品基本信息字段复制到我们的productUpdateInitDTO
        BeanUtils.copyProperties(byId, productUpdateInitDTO);
        //获取一级分类id
        PmsProductCategory one = pmsProductCategoryService.getOne(new QueryWrapper<PmsProductCategory>().lambda().eq(PmsProductCategory::getId, byId.getProductCategoryId()));
        productUpdateInitDTO.setCateParentId(one.getParentId());
        //获取对应produceId的会员价格数据
        List<PmsMemberPrice> pmsMemberPriceList = memberPriceService.list(new QueryWrapper<PmsMemberPrice>().lambda().eq(PmsMemberPrice::getProductId, id));
        //获取对应produceId的阶梯价格数据
        List<PmsProductLadder> pmsProductLadderList = productLadderService.list(new QueryWrapper<PmsProductLadder>().lambda().eq(PmsProductLadder::getProductId, id));
        //获取对应produceId的满减价格数据
        List<PmsProductFullReduction> pmsProductFullReductionList = productFullReductionService.list(new QueryWrapper<PmsProductFullReduction>().lambda().eq(PmsProductFullReduction::getProductId, id));
        //获取对应produceId的SKU价格数据
        List<PmsSkuStock> pmsSkuStockList = skuStockService.list(new QueryWrapper<PmsSkuStock>().lambda().eq(PmsSkuStock::getProductId, id));
        //获取对应produceId的SPU价格数据
        List<PmsProductAttributeValue> pmsProductAttributeValueList = productAttributeValueService.list(new QueryWrapper<PmsProductAttributeValue>().lambda().eq(PmsProductAttributeValue::getProductId, id));

        //设置productUpdateInitDTO
        productUpdateInitDTO.setMemberPriceList(pmsMemberPriceList);
        productUpdateInitDTO.setProductAttributeValueList(pmsProductAttributeValueList);
        productUpdateInitDTO.setProductFullReductionList(pmsProductFullReductionList);
        productUpdateInitDTO.setProductLadderList(pmsProductLadderList);
        productUpdateInitDTO.setSkuStockList(pmsSkuStockList);

        return productUpdateInitDTO;
    }

    /**
     * 编辑商品
     * url:'/product/update/'+id,
     * method:'post',
     * data:data
     */
    @Override
    @Transactional
    public boolean updateProduct(ProductSaveParamsDTO productSaveParamsDTO) {
        // 1. 保存商品基本信息 --商品主表
        PmsProduct product = productSaveParamsDTO;
        boolean result = this.updateById(product);
        if (result) {

            // 为了解决 前端会传入其他促销方式的空数据进来
            switch (product.getPromotionType()) {
                case 2:
                    // 2. 会员价格
                    //编辑的话先删除在保存会比较好
                    memberPriceService.remove(new QueryWrapper<PmsMemberPrice>().lambda().eq(PmsMemberPrice::getProductId, product.getId()));
                    SaveManyList(productSaveParamsDTO.getMemberPriceList(), product.getId(), memberPriceService);
                    break;
                case 3:
                    // 3. 阶梯价格
                    //编辑的话先删除在保存会比较好
                    productLadderService.remove(new QueryWrapper<PmsProductLadder>().lambda().eq(PmsProductLadder::getProductId, product.getId()));
                    SaveManyList(productSaveParamsDTO.getProductLadderList(), product.getId(), productLadderService);
                    break;
                case 4:
                    // 4. 减满价格
                    //编辑的话先删除在保存会比较好
                    productFullReductionService.remove(new QueryWrapper<PmsProductFullReduction>().lambda().eq(PmsProductFullReduction::getProductId, product.getId()));
                    SaveManyList(productSaveParamsDTO.getProductFullReductionList(), product.getId(), productFullReductionService);
                    break;
            }
            // 5. sku
            //编辑的话先删除在保存会比较好
            skuStockService.remove(new QueryWrapper<PmsSkuStock>().lambda().eq(PmsSkuStock::getProductId, product.getId()));
            SaveManyList(productSaveParamsDTO.getSkuStockList(), product.getId(), skuStockService);

            // 6 spu
            //编辑的话先删除在保存会比较好
            productAttributeValueService.remove(new QueryWrapper<PmsProductAttributeValue>().lambda().eq(PmsProductAttributeValue::getProductId, product.getId()));
            SaveManyList(productSaveParamsDTO.getProductAttributeValueList(), product.getId(), productAttributeValueService);

        }
        return result;
    }


    /**
     * 公共方法： 保存会员价格、阶梯价格、减满价格、 sku 、 spu 商品的关联数据
     * <p>
     * 统一： 都需要设置商品id,  都需要批量保存
     */
    public void SaveManyList(List list, Long productId, IService service) {
        // 如果数据为空 或者长度为0  不做任何操作
        if (CollectionUtil.isEmpty(list)) return;

        try {
            // 循环 反射 赋值商品id
            for (Object obj : list) {
                Method setProductIdMethod = obj.getClass().getMethod("setProductId", Long.class);

                // 在修改状态清除主键id
                Method setId = obj.getClass().getMethod("setId", Long.class);
                setId.invoke(obj, (Long) null);

                // 调用setProductId
                setProductIdMethod.invoke(obj, productId);
            }

            service.saveBatch(list);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
