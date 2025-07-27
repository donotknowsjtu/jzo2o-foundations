package com.jzo2o.foundations.service.impl;

import com.jzo2o.foundations.model.domain.Servers;
import com.jzo2o.foundations.mapper.ServersMapper;
import com.jzo2o.foundations.service.IServersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * MySQL Foreign Servers table 服务实现类
 * </p>
 *
 * @author donot-know
 * @since 2025-07-25
 */
@Service
public class ServersServiceImpl extends ServiceImpl<ServersMapper, Servers> implements IServersService {

}
