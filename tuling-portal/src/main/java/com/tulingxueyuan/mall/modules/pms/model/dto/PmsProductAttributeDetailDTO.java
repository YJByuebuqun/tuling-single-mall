package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.tulingxueyuan.mall.modules.pms.model.PmsProductAttributeValue;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @CreateTime: 2022-11-10  09:46
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PmsProductAttributeDetailDTO对象", description = "存储产品参数信息的表")
public class PmsProductAttributeDetailDTO extends PmsProductAttributeValue {
    private String attrName;
}
