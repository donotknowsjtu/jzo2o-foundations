package com.jzo2o.foundations.service;

import com.jzo2o.foundations.mapper.ServeMapper;
import com.jzo2o.foundations.model.dto.response.ServeCategoryResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 门户查询接口Service
 */

public interface HomeService {



    List<ServeCategoryResDTO> queryServeIconCategoryByRegionIdCache(Long regionId);
}
