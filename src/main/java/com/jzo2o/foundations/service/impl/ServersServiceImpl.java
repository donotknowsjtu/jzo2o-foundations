package com.jzo2o.foundations.service.impl;

import com.jzo2o.common.model.PageResult;
import com.jzo2o.foundations.mapper.ServeMapper;
import com.jzo2o.foundations.model.domain.Serve;
import com.jzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;
import com.jzo2o.foundations.service.IServersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzo2o.mysql.utils.PageHelperUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * MySQL Foreign Servers table 服务实现类
 * </p>
 *
 * @author donot-know
 * @since 2025-07-25
 */
@Service
public class ServersServiceImpl extends ServiceImpl<ServeMapper, Serve> implements IServersService {
    public PageResult<ServeResDTO> page(ServePageQueryReqDTO reqDTO) {
        PageResult<ServeResDTO> serveResDTOPageResult = PageHelperUtils.selectPage(reqDTO,
                () -> baseMapper.queryServeListByRegionId(reqDTO.getRegionId()));
        return serveResDTOPageResult;
    }
}
