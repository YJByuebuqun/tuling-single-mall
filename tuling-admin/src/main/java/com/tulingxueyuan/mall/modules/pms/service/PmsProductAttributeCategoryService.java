package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
public interface PmsProductAttributeCategoryService extends IService<PmsProductAttributeCategory> {
    /**
     * url:'/productAttribute/category/list',
     * method:'get',
     * params:params
     * pageNum: 1,
     * pageSize: 5
     * 描述：设置每页最大显示数据，并返回当前页
     */
    Page listPage(Integer pageNum, Integer pageSize);

    /**
     * url:'/productAttribute/category/create',
     * method:'post',
     * data:data
     * 描述：添加商品类型
     */
    boolean creatProductCate(PmsProductAttributeCategory pmsProductAttributeCategory);

    /**
     * url:'/productAttribute/category/delete/'+id,
     * method:'get'
     * 描述：根据Id进行删除
     */
    boolean deleteProductAttrCateById(Long id);

    /**
     * url:'/productAttribute/category/update/'+id,
     * method:'post',
     * data:data
     * 描述：根据id修改商品类型
     */
    boolean updateProductAttrCate(PmsProductAttributeCategory pmsProductAttributeCategory);
    /**
     * url:'/productAttribute/category/list/withAttr',
     * method:'get'
     * 描述：查询添加中筛选列表的list
     */
    List<ProductAttributeCateDTO> getWithAttrList();
}
