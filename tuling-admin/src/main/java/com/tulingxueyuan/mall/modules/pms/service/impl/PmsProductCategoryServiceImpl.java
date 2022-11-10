package com.tulingxueyuan.mall.modules.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.BeanProperty;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategoryAttributeRelation;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductCateWithChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryAttributeRelationService;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author YanJiabo
 * @since 2022-10-27
 */
@Service
public class PmsProductCategoryServiceImpl extends ServiceImpl<PmsProductCategoryMapper, PmsProductCategory> implements PmsProductCategoryService {
    @Autowired
    PmsProductCategoryAttributeRelationService attributeRelationService;

    @Autowired
    PmsProductCategoryMapper pmsProductCategoryMapper;


    /**
     * 描述：根据前端传来的pageNum，和pageSize，和页面包含的内容查询条件parentId来返回一个page页面
     *
     * @param parentId 一级页面
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     * @return
     */
    @Override
    public Page listPage(Long parentId, Integer pageNum, Integer pageSize) {
        Page page = new Page(pageNum, pageSize);
        Page page1 = this.page(page, new QueryWrapper<PmsProductCategory>().lambda()
                .eq(PmsProductCategory::getParentId, parentId)
                .orderByAsc(PmsProductCategory::getSort));
        return page1;
    }

    /**
     * url:/productCategory/update/navStatus,
     * method:'post',
     * data:data
     * data.append('ids',ids);
     * data.append('navStatus',row.navStatus);
     * 描述：根据id查找某条内容，并修改navStatus
     *
     * @param ids       根据id
     * @param navStatus navStatus
     * @return
     */

    @Override
    public boolean updateNavStatus(Integer ids, Integer navStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda()
                .in(PmsProductCategory::getId, ids)
                .set(PmsProductCategory::getNavStatus, navStatus);


        return this.update(pmsProductCategoryUpdateWrapper);
    }

    /**
     * url:'/productCategory/update/showStatus',
     * method:'post',
     * data:data
     * data.append('ids',ids);
     * data.append('showStatus',row.showStatus);
     * 描述：根据当前ID修改showStatus的值
     */
    @Override
    public boolean updateShowStatus(Integer ids, Integer showStatus) {
        UpdateWrapper<PmsProductCategory> pmsProductCategoryUpdateWrapper = new UpdateWrapper<>();
        pmsProductCategoryUpdateWrapper.lambda()
                .in(PmsProductCategory::getId, ids)
                .set(PmsProductCategory::getShowStatus, showStatus);


        return this.update(pmsProductCategoryUpdateWrapper);
    }

    /**
     * url:'/productCategory/delete/'+id,
     * method:'post'
     * 描述：通过id删除某条记录
     */
    @Override
    public boolean deleteById(Long id) {
        boolean b = this.removeById(id);
        return b;
    }

    /**
     * url:'/productCategory/create',
     * method:'post',
     * data:data
     * 描述；表单回传数据，进行数据库数据增加
     */
    @Override
    public boolean creatProductCate(PmsProductCategoryDTO pmsProductCategoryDTO) {
        boolean save = false;

        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        BeanUtils.copyProperties(pmsProductCategoryDTO, pmsProductCategory);

        pmsProductCategory.setProductCount(0);
        //如果商品分类的getParentId为0，我就设置为一级
        if (pmsProductCategory.getParentId() == 0) {
            pmsProductCategory.setLevel(0);
        } else {
            pmsProductCategory.setLevel(1);
        }
        //先将商品分类的信息储存到数据库
        boolean save1 = this.save(pmsProductCategory);
        if (save1) {
            save = save1;
        } else {
            return false;
        }

        //之后将商品分类id和商品属性id对应关系放到数据库
        List<Long> productAttributeIdList = pmsProductCategoryDTO.getProductAttributeIdList();
        for (Long productAttributeId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation attributeRelation = new PmsProductCategoryAttributeRelation();
            attributeRelation.setProductCategoryId(pmsProductCategory.getId());
            attributeRelation.setProductAttributeId(productAttributeId);
            boolean save2 = attributeRelationService.save(attributeRelation);
            save = save2;
        }

        return save;
    }

    /**
     * url:'/productCategory/'+id,
     * method:'get',
     * 描述：点击编辑按钮，根据id返回数据填充
     */
    @Override
    public PmsProductCategory getProductCateById(Long id) {
        PmsProductCategory pmsProductCategory = this.getById(id);
        return pmsProductCategory;
    }

    @Override
    public boolean updateProductCate(Long id, PmsProductCategoryDTO pmsProductCategoryDTO) {
        boolean save = false;
        //修改pmsProductCategory中的数据，不包括删选
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        BeanUtils.copyProperties(pmsProductCategoryDTO, pmsProductCategory);
        boolean save1 = this.updateById(pmsProductCategory);
        if (save1) {
            save = save1;
        } else {
            return false;
        }

        List<Long> productAttributeIdList = pmsProductCategoryDTO.getProductAttributeIdList();
        //先删除已保存的商品分类和商品属性的关系，后面在重新添加修改的商品分类和商品属性的关系
        attributeRelationService.remove(new QueryWrapper<PmsProductCategoryAttributeRelation>().lambda().eq(PmsProductCategoryAttributeRelation::getProductCategoryId, pmsProductCategory.getId()));
        for (Long productAttributeId : productAttributeIdList) {
            PmsProductCategoryAttributeRelation AttributeRelation = new PmsProductCategoryAttributeRelation();
            AttributeRelation.setProductAttributeId(productAttributeId);
            AttributeRelation.setProductCategoryId(pmsProductCategory.getId());
            boolean save2 = attributeRelationService.save(AttributeRelation);
            save = save2;
        }
        return save;
    }

    @Override
    public List<ProductCateWithChildrenDTO> getWithChildren() {
        List<ProductCateWithChildrenDTO> list = pmsProductCategoryMapper.getWithChildren();
        return list;
    }


}
