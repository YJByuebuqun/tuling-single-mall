package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.sms.model.SmsHomeAdvertise;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @CreateTime: 2022-11-08  22:00
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HomeMenusBannerDTO对象", description = "提供首页menus和banner的对象")
public class HomeMenusBannerDTO {
    private List<HomeMenusDTO> homeMenusList;
    private List<SmsHomeAdvertise> HomeAdvertisesList;
}
