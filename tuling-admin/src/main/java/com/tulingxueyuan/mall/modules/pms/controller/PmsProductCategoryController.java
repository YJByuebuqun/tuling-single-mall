package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductCategoryDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductCateWithChildrenDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author YanJiabo
 * @since 2022-10-27
 */
@RestController
@RequestMapping("/productCategory")
public class PmsProductCategoryController {
    @Autowired
    PmsProductCategoryService pmsProductCategoryService;

    /**
     * url:'/productCategory/list/'+parentId
     * parentId,pageNum,pageSize
     * 描述：根据前端传来的pageNum，和pageSize，和页面包含的内容查询条件parentId来返回一个page页面
     */
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable("parentId") Long parentId,
                                                                @RequestParam("pageNum") Integer pageNum,
                                                                @RequestParam("pageSize") Integer pageSize) {

        Page page = pmsProductCategoryService.listPage(parentId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));

    }


    /**
     * url:/productCategory/update/navStatus,
     * method:'post',
     * data:data
     * data.append('ids',ids);
     * data.append('navStatus',row.navStatus);
     *
     * @return
     */

    @PostMapping("/update/navStatus")
    public CommonResult<Boolean> updateNavStatus(@RequestParam("ids") Integer ids,
                                                 @RequestParam("navStatus") Integer navStatus) {

        boolean result = pmsProductCategoryService.updateNavStatus(ids, navStatus);
        if (result) {
            return CommonResult.success(result);
        }

        return CommonResult.failed();
    }

    /**
     * url:'/productCategory/update/showStatus',
     * method:'post',
     * data:data
     * data.append('ids',ids);
     * data.append('showStatus',row.showStatus);
     * 描述：根据当前ID修改showStatus的值
     */
    @PostMapping("/update/showStatus")
    public CommonResult<Boolean> updateShowStatus(@RequestParam("ids") Integer ids,
                                                  @RequestParam("showStatus") Integer showStatus) {

        boolean result = pmsProductCategoryService.updateShowStatus(ids, showStatus);
        if (result) {
            return CommonResult.success(result);
        }

        return CommonResult.failed();
    }

    /**
     * url:'/productCategory/delete/'+id,
     * method:'post'
     * 描述：通过id删除某条记录
     */
    @PostMapping("/delete/{id}")
    public CommonResult<Boolean> deleteProductCate(@PathVariable("id") Long id) {

        boolean result = pmsProductCategoryService.deleteById(id);
        if (result) {
            return CommonResult.success(result);
        }

        return CommonResult.failed();
    }

    /**
     * url:'/productCategory/create',
     * method:'post',
     * data:data
     * 描述；表单回传数据，进行数据库数据增加
     */
    @PostMapping("/create")
    public CommonResult<Boolean> creatProductCate(@RequestBody PmsProductCategoryDTO pmsProductCategoryDTO) {

        boolean result = pmsProductCategoryService.creatProductCate(pmsProductCategoryDTO);
        if (result) {
            return CommonResult.success(result);
        }

        return CommonResult.failed();
    }


    /**
     * url:'/productCategory/'+id,
     * method:'get',
     * 描述：点击编辑按钮，根据id返回数据填充
     */
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getProductCateById(@PathVariable("id") Long id) {

        PmsProductCategory pmsProductCategory = pmsProductCategoryService.getProductCateById(id);
        return CommonResult.success(pmsProductCategory);
    }

    /**
     * url:'/productCategory/update/'+id,
     * method:'post',
     * data:data
     * 描述：点击编辑按钮后修改数据，而后点击提交后，修改数据库内容
     */

    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateProductCate(@PathVariable("id") Long id,
                                                   @RequestBody PmsProductCategoryDTO pmsProductCategoryDTO) {

        boolean result = pmsProductCategoryService.updateProductCate(id, pmsProductCategoryDTO);
        if (result) {
            return CommonResult.success(result);
        }

        return CommonResult.failed();
    }


    /**
     * url:'/productCategory/list/withChildren',
     * method:'get'
     * 描述：商品列表中商品分类下拉框
     */
    @GetMapping("/list/withChildren")
    public CommonResult<List<ProductCateWithChildrenDTO>> getWithChildren() {
        List<ProductCateWithChildrenDTO> children = pmsProductCategoryService.getWithChildren();
        return CommonResult.success(children);
    }
}
