package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.RelationAttrInfoDTO;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {
    /**
     * url:'/productAttribute/list/'+cid,
     * method:'get',
     * params:params
     * pageNum: 1,
     * pageSize: 5,
     * type: this.$route.query.type
     * 描述：点击属性列表，跳转到属性列表页面，获取属性列表
     */
    Page getList(Long cid, Integer pageNum, Integer pageSize, Integer type);

    /**
     * url:'/productAttribute/create',
     * method:'post',
     * data:data
     * 描述:添加商品属性
     */
    boolean createProductAttr(PmsProductAttribute pmsProductAttribute);

    /**
     * url:'/productAttribute/'+id,
     * method:'get'
     * 描述：点击编辑商品属性列表，回传当前id对应的数据
     */
    PmsProductAttribute getProductAttrById(Long id);

    /**
     * url:'/productAttribute/update/'+id,
     * method:'post',
     * data:data
     * 描述：修改商品属性列表
     */
    boolean updateProductAttrById(PmsProductAttribute pmsProductAttribute);

    /**
     * url:'/productAttribute/delete',
     * method:'post',
     * data:data
     * 描述：点击删除，传入int[]ids数组，根据ID删除商品属性
     */
    boolean deleteProductAttrCateById(int[] ids);

    /**
     * url:'/productAttribute/attrInfo/'+productCategoryId,
     * method:'get'
     * 描述：获取商品分类编辑中商品属性
     */
    List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cid);
}
