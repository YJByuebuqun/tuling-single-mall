package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.RelationAttrInfoDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@Service
public class PmsProductAttributeServiceImpl extends ServiceImpl<PmsProductAttributeMapper, PmsProductAttribute> implements PmsProductAttributeService {

    @Autowired
    PmsProductAttributeCategoryService pmsProductAttributeCategoryService;

    @Autowired
    PmsProductAttributeMapper pmsProductAttributeMapper;

    @Override
    public Page getList(Long cid, Integer pageNum, Integer pageSize, Integer type) {
        Page page = new Page(pageNum, pageSize);
        QueryWrapper<PmsProductAttribute> queryWrapper = new QueryWrapper<PmsProductAttribute>();
        queryWrapper.lambda().eq(PmsProductAttribute::getProductAttributeCategoryId, cid)
                .eq(PmsProductAttribute::getType, type)
                .orderByAsc(PmsProductAttribute::getSort);
        Page page1 = this.page(page, queryWrapper);
        return page1;
    }

    /**
     * url:'/productAttribute/create',
     * method:'post',
     * data:data
     * 描述:添加商品属性
     */
    @Override
    @Transactional
    public boolean createProductAttr(PmsProductAttribute pmsProductAttribute) {
        boolean result = this.save(pmsProductAttribute);
        //如果是type等于0，表明添加的是商品属性，那么就属性数量+1
        UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
        if (pmsProductAttribute.getType() == 0) {

            updateWrapper.lambda().setSql("attribute_count=attribute_count+1");

        } else if (pmsProductAttribute.getType() == 1) {
            //如果是type等于1，表明添加的是商品参数，那么就参数数量+1
            updateWrapper.lambda().setSql("param_count=param_count+1");
        } else {
            return false;
        }
        updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, pmsProductAttribute.getProductAttributeCategoryId());
        pmsProductAttributeCategoryService.update(updateWrapper);
        return result;
    }

    /**
     * url:'/productAttribute/'+id,
     * method:'get'
     * 描述：点击编辑商品属性列表，回传当前id对应的数据
     */
    @Override
    public PmsProductAttribute getProductAttrById(Long id) {
        PmsProductAttribute pmsProductAttribute = this.getById(id);
        return pmsProductAttribute;
    }

    /**
     * url:'/productAttribute/update/'+id,
     * method:'post',
     * data:data
     * 描述：修改商品属性列表
     */
    @Override
    public boolean updateProductAttrById(PmsProductAttribute pmsProductAttribute) {
        boolean result = this.updateById(pmsProductAttribute);
        return result;
    }

    @Override
    @Transactional
    public boolean deleteProductAttrCateById(int[] ids) {
        //通过id删除数据
        boolean result = false;
        int num = 0;
        //通过查询得到pmsProductAttribute对象，一旦得到就退出
        PmsProductAttribute pmsProductAttribute = null;
        for (int id : ids) {
            pmsProductAttribute = this.getById(id);
            if (pmsProductAttribute != null) {
                break;
            }
        }
        for (int i = 0; i < ids.length; i++) {
            result = this.removeById(ids[i]);
            if (result) {
                num++;
            }
        }
        //先判断长度不为0，并且不为空
        if (num != 0 && pmsProductAttribute != null) {
            UpdateWrapper<PmsProductAttributeCategory> updateWrapper = new UpdateWrapper<>();
            //如果是type等于0，表明删除的是商品属性，那么就属性数量-1
            if (pmsProductAttribute.getType() == 0) {
                updateWrapper.lambda().setSql("attribute_count=attribute_count-" + num);

            } else if (pmsProductAttribute.getType() == 1) {
                //如果是type等于1，表明添加的是商品参数，那么就参数数量-1
                updateWrapper.lambda().setSql("param_count=param_count-" + num);
            } else {
                return false;
            }
            updateWrapper.lambda().eq(PmsProductAttributeCategory::getId, pmsProductAttribute.getProductAttributeCategoryId());
            pmsProductAttributeCategoryService.update(updateWrapper);
        }
        return result;
    }

    /**
     * url:'/productAttribute/attrInfo/'+productCategoryId,
     * method:'get'
     * 描述：获取商品分类编辑中商品属性
     */
    @Override
    public List<RelationAttrInfoDTO> getRelationAttrInfoByCid(Long cid) {
        List<RelationAttrInfoDTO> list = pmsProductAttributeMapper.getRelationAttrInfoByCid(cid);
        return list;
    }
}
