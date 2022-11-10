package com.tulingxueyuan.mall;

import com.tulingxueyuan.mall.modules.pms.mapper.PmsProductAttributeCategoryMapper;
import com.tulingxueyuan.mall.modules.pms.model.dto.ProductAttributeCateDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @CreateTime: 2022-10-30  23:20
 * @Author: YanJiabo
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
public class GetWithAttrListDemo {
    @Autowired
    PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    @Test
    public void getWithAttrList(){
        List<ProductAttributeCateDTO> withAttrList = pmsProductAttributeCategoryMapper.getWithAttrList();
        System.out.println(withAttrList);
    }
}
