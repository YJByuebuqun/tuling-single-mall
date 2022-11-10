package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.dto.ProductSaveParamsDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductConditionDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductUpdateInitDTO;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
public interface PmsProductService extends IService<PmsProduct> {

    /**
     * url:'/product/list',
     * method:'get',
     * params:{
     * }
     * 描述：商品列表页面展示搜索后数据列表提供page页
     */
    Page getListBy(PmsProductConditionDTO productConditionDTO);


    /**
     * url:'/product/update/deleteStatus',
     * method:'post',
     * params:params
     * 描述：逻辑删除数据列表数据
     */
    Boolean deleteByIds(List<Long> ids);

    /**
     * 改变商品标签状态（上架，新品，推荐）
     *
     * @param ids
     * @param status
     * @param getStatus
     * @return
     */
    Boolean updateStatus(List<Long> ids, Integer status, SFunction<PmsProduct, ?> getStatus);

    /**
     * 商品添加
     * url:'/product/create',
     * method:'post',
     * data:data    json
     */
    boolean create(ProductSaveParamsDTO productSaveParamsDTO);

    /**
     * 点击编辑获得对应id的商品的信息返回
     * url:'/product/updateInfo/'+id,
     * method:'get',
     */
    ProductUpdateInitDTO getProductById(Long id);

    /**
     * 编辑商品
     * url:'/product/update/'+id,
     * method:'post',
     * data:data
     */
    boolean updateProduct(ProductSaveParamsDTO productSaveParamsDTO);
}
