package com.jzo2o.foundations.service.impl;

import com.jzo2o.common.utils.CollUtils;
import com.jzo2o.common.utils.ObjectUtils;
import com.jzo2o.foundations.enums.FoundationStatusEnum;
import com.jzo2o.foundations.mapper.ServeMapper;
import com.jzo2o.foundations.model.domain.Region;
import com.jzo2o.foundations.model.dto.response.ServeCategoryResDTO;
import com.jzo2o.foundations.model.dto.response.ServeSimpleResDTO;
import com.jzo2o.foundations.service.HomeService;
import com.jzo2o.foundations.service.IRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    * 门户查询接口Service实现类
 */
@Service
@Slf4j

public class HomeServiceImpl implements HomeService {

    @Resource
    private ServeMapper serveMapper;
    @Resource
    private IRegionService regionService;

    public List<ServeCategoryResDTO> queryServeIconCategoryByRegionIdCache(Long regionId){
        // 查询区域信息
        Region region = regionService.getById(regionId);
        if(ObjectUtils.isNull(region) || region.getActiveStatus() != FoundationStatusEnum.ENABLE.getStatus()){
            return Collections.emptyList();
        }

        List<ServeCategoryResDTO> rawList = serveMapper.findServeIconCategoryByRegionId(regionId);
        if(CollUtils.isEmpty(rawList)){
            return Collections.emptyList();
        }

        // 切割服务类型列表
        int endIndex = Math.min(rawList.size(), 2);
        List<ServeCategoryResDTO> serveCategoryResDTOS = new ArrayList<>(rawList.subList(0, endIndex));

        // 切割服务项列表
        serveCategoryResDTOS.forEach(item -> {
            // 取出原服务项列表
            List<ServeSimpleResDTO> serveSimpleResDTOS1 = item.getServeResDTOList();
            int endIndex2 = Math.min(serveSimpleResDTOS1.size(), 4);
            ArrayList<ServeSimpleResDTO> serveSimpleResDTOS2 = new ArrayList<>(serveSimpleResDTOS1.subList(0, endIndex2));
            // 将切割后的服务项列表设置回去
            item.setServeResDTOList(serveSimpleResDTOS2);
        });

        return serveCategoryResDTOS;
    };


}
