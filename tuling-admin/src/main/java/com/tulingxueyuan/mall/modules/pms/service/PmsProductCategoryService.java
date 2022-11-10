package com.tulingxueyuan.mall.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductCateWithChildrenDTO;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author YanJiabo
 * @since 2022-10-27
 */
public interface PmsProductCategoryService extends IService<PmsProductCategory> {
    /**
     * url:'/productCategory/list/'+parentId
     * parentId,pageNum,pageSize
     * 描述：根据前端传来的pageNum，和pageSize，和页面包含的内容查询条件parentId来返回一个page页面
     */
    Page listPage(Long parentId, Integer pageNum, Integer pageSize);

    /**
     * url:/productCategory/update/navStatus,
     * method:'post',
     * data:data
     * data.append('ids',ids);
     * data.append('navStatus',row.navStatus);
     * 描述：根据id查找某条内容，并修改navStatus
     *
     * @return
     */
    boolean updateNavStatus(Integer ids, Integer navStatus);

    /**
     * url:'/productCategory/update/showStatus',
     * method:'post',
     * data:data
     * data.append('ids',ids);
     * data.append('showStatus',row.showStatus);
     * 描述：根据当前ID修改showStatus的值
     */
    boolean updateShowStatus(Integer ids, Integer showStatus);

    /**
     * url:'/productCategory/delete/'+id,
     * method:'post'
     * 描述：通过id删除某条记录
     */
    boolean deleteById(Long id);

    /**
     * url:'/productCategory/create',
     * method:'post',
     * data:data
     * 描述；表单回传数据，进行数据库数据增加
     */
    boolean creatProductCate(PmsProductCategoryDTO pmsProductCategoryDTO);

    /**
     * url:'/productCategory/'+id,
     * method:'get',
     * 描述：点击编辑按钮，根据id返回数据填充
     */
    PmsProductCategory getProductCateById(Long id);

    boolean updateProductCate(Long id, PmsProductCategoryDTO pmsProductCategoryDTO);

    List<ProductCateWithChildrenDTO> getWithChildren();
}
