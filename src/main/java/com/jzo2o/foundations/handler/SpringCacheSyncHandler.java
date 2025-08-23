package com.jzo2o.foundations.handler;

import com.jzo2o.api.foundations.dto.response.RegionSimpleResDTO;
import com.jzo2o.foundations.constants.RedisConstants;
import com.jzo2o.foundations.service.HomeService;
import com.jzo2o.foundations.service.IRegionService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Spring Cache缓存同步任务
 */
@Slf4j
@Component
public class SpringCacheSyncHandler {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private IRegionService regionService;
    @Resource
    private HomeService homeService;


    @XxlJob(value = "activeRegionCacheSync")
    public void activeRegionCacheSync(){
        // 清除缓存
        log.info(">>>>>>开启缓存同步，更新已启用区域");
        String key = RedisConstants.CacheName.JZ_CACHE + "::" + "ACTIVE_REGIONS";
        redisTemplate.delete(key);
        // 重新加载缓存
        List<RegionSimpleResDTO> regionSimpleResDTOS = regionService.queryActiveRegionListCache();

        regionSimpleResDTOS.forEach(regionSimpleResDTO -> {
            String key2 = RedisConstants.CacheName.SERVE_ICON + "::" + regionSimpleResDTO.getId();
            redisTemplate.delete(key2);
            // 重新加载首页服务图标缓存
            homeService.queryServeIconCategoryByRegionIdCache(regionSimpleResDTO.getId());
        });


        log.info(">>>>>>更新已启用区域完成");
    }


}
