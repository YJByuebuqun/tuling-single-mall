package com.tulingxueyuan.mall.modules.pms.controller;

import com.alibaba.druid.sql.ast.expr.SQLAllExpr;
import com.tulingxueyuan.mall.common.api.CommonResult;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeGoodsSaleDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusBannerDTO;
import com.tulingxueyuan.mall.modules.pms.model.dto.HomeMenusDTO;
import com.tulingxueyuan.mall.modules.pms.service.PmsProductCategoryService;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import com.tulingxueyuan.mall.modules.sms.model.SmsHomeCategory;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeAdvertiseService;
import com.tulingxueyuan.mall.modules.sms.service.SmsHomeCategoryService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CreateTime: 2022-11-08  15:50
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    PmsProductCategoryService pmsProductCategoryService;

    @Autowired
    SmsHomeAdvertiseService smsHomeAdvertiseService;

    @Autowired
    SmsHomeCategoryService smsHomeCategoryService;

    /**
     * /home/menu_banner
     * 描述：提供前台首页数据
     */
    @GetMapping("/menu_banner")
    @ApiOperation("提供前台首页数据")
    public CommonResult getMenusAndBanners() {
        HomeMenusBannerDTO homeMenusBannerDTO = new HomeMenusBannerDTO();
        //设置Menus
        List<HomeMenusDTO> homeMenusList = pmsProductCategoryService.getMenus();
        homeMenusBannerDTO.setHomeMenusList(homeMenusList);

        //设置banner
        List<SmsHomeAdvertise> homeAdvertisesList = smsHomeAdvertiseService.getBanners();
        homeMenusBannerDTO.setHomeAdvertisesList(homeAdvertisesList);
        return CommonResult.success(homeMenusBannerDTO);
    }

    /**
     * /home/goods_sale
     * 分类推荐和分类推荐的商品
     */
    @GetMapping("/goods_sale")
    @ApiOperation("提供前台首页分类推荐和分类推荐的商品数据")
    public CommonResult getGoodsSale() {

        List<HomeGoodsSaleDTO> list = smsHomeCategoryService.getGoodsSale();

        return CommonResult.success(list);
    }
}
