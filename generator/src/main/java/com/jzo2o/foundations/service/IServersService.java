package com.jzo2o.foundations.service;

import com.jzo2o.foundations.model.domain.Servers;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * MySQL Foreign Servers table 服务类
 * </p>
 *
 * @author donot-know
 * @since 2025-07-25
 */
public interface IServersService extends IService<Servers> {
    List<ServeResDTO> page(ServePageQueryReqDTO reqDTO);

}
