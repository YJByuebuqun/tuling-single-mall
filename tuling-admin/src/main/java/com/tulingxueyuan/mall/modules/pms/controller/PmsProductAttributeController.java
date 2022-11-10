package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttribute;
import com.tulingxueyuan.mall.modules.pms.model.PmsProductCategory;
import com.tulingxueyuan.mall.modules.pms.model.dto.RelationAttrInfoDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@RestController
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    PmsProductAttributeService pmsProductAttributeService;

    /**
     * url:'/productAttribute/list/'+cid,
     * method:'get',
     * params:params
     * pageNum: 1,
     * pageSize: 5,
     * type: this.$route.query.type
     * 描述：点击属性列表，跳转到属性列表页面，获取属性列表
     */
    @GetMapping("list/{cid}")
    public CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable("cid") Long cid,
                                                                 @RequestParam("pageNum") Integer pageNum,
                                                                 @RequestParam("pageSize") Integer pageSize,
                                                                 @RequestParam("type") Integer type) {
        Page page = pmsProductAttributeService.getList(cid, pageNum, pageSize, type);

        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * url:'/productAttribute/create',
     * method:'post',
     * data:data
     * 描述:添加商品属性
     */
    @PostMapping("/create")
    public CommonResult<Boolean> createProductAttr(@RequestBody PmsProductAttribute pmsProductAttribute) {
        boolean result = pmsProductAttributeService.createProductAttr(pmsProductAttribute);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/'+id,
     * method:'get'
     * 描述：点击编辑商品属性列表，回传当前id对应的数据
     */
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttribute> getProductAttrById(@PathVariable("id") Long id) {
        PmsProductAttribute pmsProductAttribute = pmsProductAttributeService.getProductAttrById(id);

        return CommonResult.success(pmsProductAttribute);
    }

    /**
     * url:'/productAttribute/update/'+id,
     * method:'post',
     * data:data
     * 描述：修改商品属性列表
     */
    @PostMapping("/update/{id}")
    public CommonResult<Boolean> updateProductAttrById(@RequestBody PmsProductAttribute pmsProductAttribute) {
        boolean result = pmsProductAttributeService.updateProductAttrById(pmsProductAttribute);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/delete',
     * method:'post',
     * data:data
     * 描述：点击删除，传入int[]ids数组，根据ID删除商品属性
     */
    @PostMapping("/delete")
    public CommonResult<Boolean> deleteProductAttrCateById(@RequestParam("ids") int[] ids) {
        boolean result = pmsProductAttributeService.deleteProductAttrCateById(ids);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();
    }

    /**
     * url:'/productAttribute/attrInfo/'+productCategoryId,
     * method:'get'
     * 描述：获取商品分类编辑中商品属性
     */
    @GetMapping("/attrInfo/{cid}")
    public CommonResult<List<RelationAttrInfoDTO>> getRelationAttrInfoByCid(@PathVariable("cid") Long cid) {

        List<RelationAttrInfoDTO> list = pmsProductAttributeService.getRelationAttrInfoByCid(cid);
        return CommonResult.success(list);
    }

}

