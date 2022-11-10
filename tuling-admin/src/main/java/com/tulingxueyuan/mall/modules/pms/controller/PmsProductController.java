package com.tulingxueyuan.mall.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tulingxueyuan.mall.common.api.CommonPage;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.dto.ProductSaveParamsDTO;
import com.tulingxueyuan.mall.modules.pms.model.PmsProduct;
import com.tulingxueyuan.mall.modules.pms.model.dto.PmsProductConditionDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductUpdateInitDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author XuShu
 * @since 2022-10-27
 */
@RestController
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    PmsProductService pmsProductService;

    /**
     * url:'/product/list',
     * method:'get',
     * params:{
     * keyword: null,
     * pageNum: 1,
     * pageSize: 5,
     * publishStatus: null,
     * verifyStatus: null,
     * productSn: null,
     * productCategoryId: null,
     * brandId: null
     * }
     * 描述：商品列表页面展示搜索后数据列表提供page页
     */
    @ApiOperation("查询所有商品")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductConditionDTO productConditionDTO) {
        Page page = pmsProductService.getListBy(productConditionDTO);
        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * url:'/product/update/deleteStatus',
     * method:'post',
     * params:params
     * 描述：逻辑删除数据列表数据
     */
    @PostMapping("/update/deleteStatus")
    public CommonResult<Boolean> removeByIds(@RequestParam("ids") List<Long> ids) {

        Boolean result = pmsProductService.deleteByIds(ids);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }

    /**
     * url:'/product/update/newStatus',
     * method:'post',
     * params:params
     * 描述：设置为新品
     */
    @PostMapping("/update/newStatus")
    public CommonResult<Boolean> updateNewStatus(@RequestParam("ids") List<Long> ids,
                                                 @RequestParam("newStatus") Integer newStatus) {

        Boolean result = pmsProductService.updateStatus(ids, newStatus, PmsProduct::getNewStatus);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }

    /**
     * url:'/product/update/recommendStatus',
     * method:'post',
     * params:params
     * 描述：设置为推荐
     */
    @PostMapping("/update/recommendStatus")
    public CommonResult<Boolean> UpdateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                                       @RequestParam("recommendStatus") Integer recommendStatus) {

        Boolean result = pmsProductService.updateStatus(ids, recommendStatus, PmsProduct::getRecommandStatus);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }

    /**
     * url:'/product/update/publishStatus',
     * method:'post',
     * params:params
     * 描述：设置为上架
     */
    @PostMapping("/update/publishStatus")
    public CommonResult<Boolean> updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                                     @RequestParam("publishStatus") Integer publishStatus) {

        Boolean result = pmsProductService.updateStatus(ids, publishStatus, PmsProduct::getPublishStatus);
        if (result) {
            return CommonResult.success(result);
        }
        return CommonResult.failed();

    }

    /**
     * 商品添加
     * url:'/product/create',
     * method:'post',
     * data:data    json
     */
    @PostMapping("/create")
    public CommonResult create(@RequestBody ProductSaveParamsDTO productSaveParamsDTO) {
        boolean result = pmsProductService.create(productSaveParamsDTO);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }


    /**
     * 点击编辑获得对应id的商品的信息返回
     * url:'/product/updateInfo/'+id,
     * method:'get',
     */
    @GetMapping("/updateInfo/{id}")
    public CommonResult getProduct(@PathVariable("id") Long id) {
        ProductUpdateInitDTO productUpdateInitDTO = pmsProductService.getProductById(id);

        return CommonResult.success(productUpdateInitDTO);

    }

    /**
     * 编辑商品
     * url:'/product/update/'+id,
     * method:'post',
     * data:data
     */
    @PostMapping("/update/{id}")
    public CommonResult updateProduct(@RequestBody @Valid ProductSaveParamsDTO productSaveParamsDTO) {
        boolean result = pmsProductService.updateProduct(productSaveParamsDTO);
        if (result) {
            return CommonResult.success(result);
        } else {
            return CommonResult.failed();
        }
    }

}

