package com.jzo2o.foundations.service;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.foundations.model.domain.Serve;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.jzo2o.foundations.model.dto.request.ServeUpsertReqDTO;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * MySQL Foreign Servers table 服务类
 * </p>
 *
 * @author donot-know
 * @since 2025-07-25
 */
public interface IServeService extends IService<Serve> {
    /**
     * 区域服务分页查询
     *
     * @param reqDTO 请求参数
     * @return 区域服务列表
     */
    PageResult<ServeResDTO> page(ServePageQueryReqDTO reqDTO);


    void batchAdd(List<ServeUpsertReqDTO> reqDTOList);

    /**
     * 服务价格修改
     * @param id
     * @param price
     * @return
     */
    Serve update(Long id, BigDecimal price) ;

    /**
     * 上架区域服务
     * @param id
     */
    Serve onSale(Long id) ;

    /**
     * 下架区域服务
     * @param id
     */
    Serve offSale(Long id) ;

    /**
     * 服务设置热门
     * @param id 服务项id
     * @return 设置热门后的服务项
     */
    Serve onHot(Long id);

    /**
     * 服务取消热门
     * @param id 服务项id
     * @return 取消热门后的服务项
     */
    Serve offHot(Long id);

    /**
     * 查询指定区域下的指定状态的服务数量
     * @param id
     * @param status
     * @return
     */
    int queryServeCountByRegionIdAndSaleStatus(Long id, int status);


    int queryServeCountByServeItemIdAndSaleStatus(Long id, int status);
}
