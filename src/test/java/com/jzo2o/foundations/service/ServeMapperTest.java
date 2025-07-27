package com.jzo2o.foundations.service;

import com.jzo2o.foundations.mapper.ServeMapper;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Description ServeMapper单元测试类
 * @author donot-know
 */
@SpringBootTest
@Slf4j
public class ServeMapperTest {

    @Resource
    private ServeMapper serveMapper;
    @Test
    public void test_queryServeListByRegionId() {
        List<ServeResDTO> serveResDTOS = serveMapper.queryServeListByRegionId(1686303222843662337L);
        Assert.notEmpty(serveResDTOS, "查询结果不能为空");
    }
}
