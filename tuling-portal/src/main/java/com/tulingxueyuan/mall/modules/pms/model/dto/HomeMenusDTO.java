package com.tulingxueyuan.mall.modules.pms.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @CreateTime: 2022-11-08  15:58
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "HomeMenusDTO对象", description = "提供首页menus的对象")
public class HomeMenusDTO {
    private Long id;
    private String name;
    private List<ProductDTO> productList;
}
