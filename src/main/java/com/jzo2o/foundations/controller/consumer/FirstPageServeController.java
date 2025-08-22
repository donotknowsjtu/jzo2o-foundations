package com.jzo2o.foundations.controller.consumer;


import com.jzo2o.foundations.model.dto.response.ServeCategoryResDTO;
import com.jzo2o.foundations.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController(value = "consumerServeController")
@RequestMapping("/customer/serve")
@Api(tags = "用户端 - 首页服务相关接口")
@Controller
public class FirstPageServeController {

    @Resource
    private HomeService homeService;

    //foundations/customer/serve/firstPageServeList
    @GetMapping("/firstPageServeList")
    @ApiOperation("首页服务列表")
    @ApiImplicitParam(name = "regionId", value = "区域id", required = true, dataTypeClass = Long.class)
    public List<ServeCategoryResDTO> firstPageServeList(@RequestParam Long regionId ) {
        return homeService.queryServeIconCategoryByRegionIdCache(regionId);
    }
}
