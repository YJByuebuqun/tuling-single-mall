package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@Service
public class PmsProductAttributeCategoryServiceImpl extends ServiceImpl<PmsProductAttributeCategoryMapper, PmsProductAttributeCategory> implements PmsProductAttributeCategoryService {
    @Autowired
    PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    /**
     * url:'/productAttribute/category/list',
     * method:'get',
     * params:params
     * pageNum: 1,
     * pageSize: 5
     * 描述：设置每页最大显示数据，并返回当前页
     */
    @Override
    public Page listPage(Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        Page page1 = this.page(page);
        return page1;
    }

    /**
     * url:'/productAttribute/category/create',
     * method:'post',
     * data:data
     * 描述：添加商品类型
     */
    @Override
    public boolean creatProductCate(PmsProductAttributeCategory pmsProductAttributeCategory) {
        boolean save = this.save(pmsProductAttributeCategory);
        pmsProductAttributeCategory.setAttributeCount(0);
        pmsProductAttributeCategory.setParamCount(0);

        return save;
    }

    /**
     * url:'/productAttribute/category/delete/'+id,
     * method:'get'
     * 描述：根据Id进行删除
     */
    @Override
    public boolean deleteProductAttrCateById(Long id) {
        boolean result = this.removeById(id);
        return result;
    }

    /**
     * url:'/productAttribute/category/update/'+id,
     * method:'post',
     * data:data
     * 描述：根据id修改商品类型
     */
    @Override
    public boolean updateProductAttrCate(PmsProductAttributeCategory pmsProductAttributeCategory) {
        boolean result = this.updateById(pmsProductAttributeCategory);
        return result;
    }

    /**
     * url:'/productAttribute/category/list/withAttr',
     * method:'get'
     * 描述：查询添加中筛选列表的list
     */
    @Override
    public List<ProductAttributeCateDTO> getWithAttrList() {
        List<ProductAttributeCateDTO> list = pmsProductAttributeCategoryMapper.getWithAttrList();
        return list;
    }

}
