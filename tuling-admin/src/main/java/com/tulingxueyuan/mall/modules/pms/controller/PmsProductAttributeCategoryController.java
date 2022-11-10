package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品属性分类表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@RestController
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    PmsProductAttributeCategoryService pmsProductAttributeCategoryService;

    /**
     * url:'/productAttribute/category/list',
     * method:'get',
     * params:params
     * pageNum: 1,
     * pageSize: 5
     * 描述：设置每页最大显示数据，并返回当前页
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam("pageNum") Integer pageNum,
                                                                         @RequestParam("pageSize") Integer pageSize) {

        Page page = pmsProductAttributeCategoryService.listPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(page));

    }

    /**
     * url:'/productAttribute/category/create',
     * method:'post',
     * data:data
     * 描述：添加商品类型
     */
    @PostMapping("/create")
    public CommonResult<Boolean> creatProductCate(PmsProductAttributeCategory pmsProductAttributeCategory) {

        boolean result = pmsProductAttributeCategoryService.creatProductCate(pmsProductAttributeCategory);
        if (result) {
            return CommonResult.success(result);
        }

        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/category/delete/'+id,
     * method:'get'
     * 描述：根据Id进行删除
     */
    @GetMapping("/delete/{id}")
    public CommonResult<Boolean> deleteProductAttrCateById(@PathVariable("id") Long id) {
        boolean result = pmsProductAttributeCategoryService.deleteProductAttrCateById(id);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }

    /**
     * url:'/productAttribute/category/update/'+id,
     * method:'post',
     * data:data
     * 描述：根据id修改商品类型
     */
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateProductAttrCate(PmsProductAttributeCategory pmsProductAttributeCategory) {

        boolean result = pmsProductAttributeCategoryService.updateProductAttrCate(pmsProductAttributeCategory);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/category/list/withAttr',
     * method:'get'
     * 描述：查询添加中筛选列表的list
     */
    @GetMapping("/list/withAttr")
    public CommonResult<List<ProductAttributeCateDTO>> getWithAttrList() {

        List<ProductAttributeCateDTO> list = pmsProductAttributeCategoryService.getWithAttrList();

        System.out.println(list);
        return CommonResult.success(list);
    }

}

