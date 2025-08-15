package com.jzo2o.foundations.controller.operation;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.jzo2o.foundations.model.dto.request.ServeUpsertReqDTO;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;
import com.jzo2o.foundations.service.IServeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
@RestController("operationServeController")
@RequestMapping("/operation/serve")
@Api(tags = "运营端-区域管理相关接口")
public class ServeController {

    @Resource
    IServeService serversService;

    @GetMapping("/page")
    @ApiOperation("区域服务分页查询")
    public PageResult<ServeResDTO> Page(ServePageQueryReqDTO reqDTO) {
        return serversService.page(reqDTO);
    }

    @PostMapping("/batch")
    @ApiOperation("添加区域服务")
    public void add(@RequestBody List<ServeUpsertReqDTO> reqDTOList) {
        serversService.batchAdd(reqDTOList);

    }

    @PutMapping("/{id}")
    @ApiOperation("修改区域服务价格")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务iD", required = true, dataTypeClass = Long.class),
            @ApiImplicitParam(name = "price", value = "价格", required = true, dataTypeClass = BigDecimal.class)
    })
    public void update(@PathVariable("id") Long id, BigDecimal price){
        serversService.update(id, price);
    }

    @PutMapping("/onSale/{id}")
    @ApiOperation("上架区域服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务iD", required = true, dataTypeClass = Long.class),
    }
    )
    public void onSale(@PathVariable("id") Long id) {
        serversService.onSale(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除区域服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务iD", required = true, dataTypeClass = Long.class),
    })
    public void delete(@PathVariable("id") Long id) {
        serversService.removeById(id);
    }

    @PutMapping("/offSale/{id}")
    @ApiOperation("下架区域服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务iD", required = true, dataTypeClass = Long.class),
    })
    public void offSale(@PathVariable("id") Long id) {
        serversService.offSale(id);
    }

    @PutMapping("/onHot/{id}")
    @ApiOperation("区域服务设置热门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务iD", required = true, dataTypeClass = Long.class),
    })
    public void onHot(@PathVariable("id") Long id) {
        serversService.onHot(id);
    }

    @PutMapping("/offHot/{id}")
    @ApiOperation("区域服务取消热门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务iD", required = true, dataTypeClass = Long.class),
    })
    public void offHot(@PathVariable("id") Long id) {
        serversService.offHot(id);
    }

}
