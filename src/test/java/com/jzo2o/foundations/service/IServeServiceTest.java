package com.jzo2o.foundations.service;

import com.jzo2o.foundations.service.IServersService;
import com.jzo2o.common.model.PageResult;
import com.jzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.jzo2o.foundations.model.dto.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Slf4j
class IServeServiceTest {
    @Resource
    private IServersService serveService;

    @Test
    void testServePage() {
        ServePageQueryReqDTO reqDTO = new ServePageQueryReqDTO();
        reqDTO.setRegionId(1686303222843662337L);
        reqDTO.setPageNo(1L);
        reqDTO.setPageSize(2L);
        PageResult<ServeResDTO> page = serveService.page(reqDTO);
        Assert.notNull(page, "分页结果不能为空");
    }
//    @Test
//    void listServeItemByCityCode() {
//        List<Long> longs = serveService.queryServeItemIdListByCityCode("010");
//        System.out.println(longs);
//    }

//    @Test
//    void findHotServeListByCityCode() {
//        List<ServeAggregationSimpleResDTO> list = serveService.findHotServeListByCityCode("010");
//        System.out.println(list);
//    }
//
//    @Test
//    void findServeTypeListByCityCode() {
//        List<ServeAggregationTypeSimpleResDTO> list = serveService.findServeTypeListByCityCode("010");
//        System.out.println(list);
//    }
//
//    @Test
//    void findServeIconCategoryByCityCode() {
//        List<ServeCategoryResDTO> list = serveService.findServeIconCategoryByCityCode("010");
//        System.out.println(list.toString());
//    }

//    @Test
//    void findDetailById() {
//        ServeAggregationSimpleResDTO detail = serveService.findDetailById(1693815624114970626L);
//        System.out.println(detail);
//    }
//
//
//    @Test
//    void update() {
//        serveService.update(1693815624114970626L, BigDecimal.valueOf(38.3));
//    }
//
//    @Test
//    void changeHotStatus() {
//        serveService.changeHotStatus(1693815624114970626L, 1);
//    }


    //分页测试
//    @Test
//    public void test_page(){
//        ServePageQueryReqDTO servePageQueryReqDTO = new ServePageQueryReqDTO();
//        servePageQueryReqDTO.setRegionId(1677152267410149378L);
//        servePageQueryReqDTO.setPageNo(1L);
//        servePageQueryReqDTO.setPageSize(3L);
//        PageResult<ServeResDTO> page = serveService.page(servePageQueryReqDTO);
//        log.info("page : {}", page);
//        Assert.notEmpty(page.getList(),"列表为空");
//    }
}