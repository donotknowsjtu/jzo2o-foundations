package com.jzo2o.foundations.service;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.foundations.model.domain.Serve;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;

import java.util.List;

/**
 * <p>
 * MySQL Foreign Servers table 服务类
 * </p>
 *
 * @author donot-know
 * @since 2025-07-25
 */
public interface IServersService extends IService<Serve> {
    /**
     * 区域服务分页查询
     *
     * @param reqDTO 请求参数
     * @return 区域服务列表
     */
    PageResult<ServeResDTO> page(ServePageQueryReqDTO reqDTO);

}
