package com.jzo2o.foundations.service.impl;

import com.jzo2o.common.expcetions.CommonException;
import com.jzo2o.common.expcetions.ForbiddenOperationException;
import com.jzo2o.common.model.PageResult;
import com.jzo2o.common.utils.BeanUtils;
import com.jzo2o.common.utils.ObjectUtils;
import com.jzo2o.foundations.enums.FoundationIsHotEnum;
import com.jzo2o.foundations.enums.FoundationStatusEnum;
import com.jzo2o.foundations.mapper.RegionMapper;
import com.jzo2o.foundations.mapper.ServeItemMapper;
import com.jzo2o.foundations.mapper.ServeMapper;
import com.jzo2o.foundations.model.domain.Region;
import com.jzo2o.foundations.model.domain.Serve;
import com.jzo2o.foundations.model.domain.ServeItem;
import com.jzo2o.foundations.model.dto.request.ServePageQueryReqDTO;
import com.jzo2o.foundations.model.dto.request.ServeUpsertReqDTO;
import com.jzo2o.foundations.model.dto.response.ServeResDTO;
import com.jzo2o.foundations.service.IServeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzo2o.mysql.utils.PageHelperUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public class ServeServiceImpl extends ServiceImpl<ServeMapper, Serve> implements IServeService {

    @Resource
    private ServeItemMapper serveItemMapper;

    @Resource
    private RegionMapper regionMapper;

    public PageResult<ServeResDTO> page(ServePageQueryReqDTO reqDTO) {
        PageResult<ServeResDTO> serveResDTOPageResult = PageHelperUtils.selectPage(reqDTO,
                () -> baseMapper.queryServeListByRegionId(reqDTO.getRegionId()));
        return serveResDTOPageResult;
    }

    public void batchAdd(List<ServeUpsertReqDTO> reqDTOList) {
       for (ServeUpsertReqDTO reqDTO : reqDTOList) {
           // 合法校验
           // ServeItem是否启用，否则不能添加
           Long serveItemId = reqDTO.getServeItemId();
           ServeItem serveItem = serveItemMapper.selectById(serveItemId);
           if(ObjectUtils.isNull(serveItem) || serveItem.getActiveStatus() != FoundationStatusEnum.ENABLE.getStatus()) {
                // 抛出异常
               throw new ForbiddenOperationException("服务项不存在或未启用，无法添加服务");
           }

           // 同一个区域中不能添加相同的服务
           Integer count = lambdaQuery()
                   .eq(Serve::getServeItemId, reqDTO.getServeItemId())
                   .eq(Serve::getRegionId, reqDTO.getRegionId())
                   .count();
           if(count > 0){
               throw new ForbiddenOperationException("该区域已存在相同的服务，无法重复添加");
           }
           // 添加
           Serve serve = BeanUtils.toBean(reqDTO, Serve.class);
           Long regionId = serve.getRegionId();
           Region region = regionMapper.selectById(regionId);
           String cityCode = region.getCityCode();
           serve.setCityCode(cityCode);

           baseMapper.insert(serve);


       }

    }

    public Serve update(Long id, BigDecimal price) {
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getPrice, price)
                .update();
        if(!update) {
            throw new CommonException("更新服务价格失败，服务可能不存在");
        }
        return baseMapper.selectById(id);


    }


    public Serve onSale(Long id) {
        // 根据id查询serve服务
        Serve serve = baseMapper.selectById(id);
        if (ObjectUtils.isNull(serve)) {
            throw new CommonException("服务不存在，无法上架");
        }
        // 如果sale_status为0或1（草稿或下架），可以上架，否则报错
        Integer saleStatus = serve.getSaleStatus();
        if(saleStatus != FoundationStatusEnum.INIT.getStatus() && saleStatus != FoundationStatusEnum.DISABLE.getStatus()) {
            throw new ForbiddenOperationException("草稿或下架状态的服务才能上架");
        }
        // 服务项目未启用不能上架
        Long id1 = serve.getServeItemId();
        ServeItem serveItem = serveItemMapper.selectById(id1);
        if(serveItem.getActiveStatus() != FoundationStatusEnum.ENABLE.getStatus()) {
            throw new ForbiddenOperationException("服务项未启用，无法上架服务");
        }
        // 更新服务的sale_status为2（上架)
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getSaleStatus, FoundationStatusEnum.ENABLE.getStatus())
                .update();
        if (!update) {
            throw new CommonException("服务上架失败");
        }

        return baseMapper.selectById(id);
    }

    public Serve offSale(Long id) {
        Serve serve = baseMapper.selectById(id);
        if(ObjectUtils.isNull(serve)) {
            throw new CommonException("该服务不存在，无法下架");
        }
        // 如果sale_status为2（上架），可以下架，否则报错
        Integer saleStatus = serve.getSaleStatus();
        if(saleStatus != FoundationStatusEnum.ENABLE.getStatus()) {
            throw new ForbiddenOperationException("该服务未上架或者处于草稿状态中，无法下架");
        }
        // 更新服务的sale_status为1（下架）
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getSaleStatus, FoundationStatusEnum.DISABLE.getStatus())
                .update();
        if (!update) {
            throw new CommonException("服务下架失败");
        }
        return baseMapper.selectById(id);
    }

    public Serve onHot(Long id) {
        Serve serve = baseMapper.selectById(id);
        if(ObjectUtils.isNull(serve)) {
            throw new CommonException("服务不存在，无法设置热门");
        }
        Integer saleStatus = serve.getSaleStatus();
        if(saleStatus != FoundationStatusEnum.ENABLE.getStatus()) {
            throw new ForbiddenOperationException("服务未上架，无法设置热门");
        }
        Integer isHot = serve.getIsHot();
        if(isHot == FoundationIsHotEnum.HOT.getIsHot()){
            throw new ForbiddenOperationException("服务已设置热门，请勿重复设置");
        }
        // 更新服务的hot_status为1（热门）
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getIsHot, FoundationIsHotEnum.HOT.getIsHot())
                .update();
        if(!update) {
            throw new CommonException("设置服务热门失败");
        }
        return baseMapper.selectById(id);
    }

    public Serve offHot(Long id) {
        Serve serve = baseMapper.selectById(id);
        if(ObjectUtils.isNull(serve)) {
            throw new CommonException("服务不存在，无法取消热门");
        }
        Integer isHot = serve.getIsHot();
        if(isHot == FoundationIsHotEnum.NOT_HOT.getIsHot()){
            throw new ForbiddenOperationException("服务已设置非热门，请勿重复设置");
        }
        // 更新服务的hot_status为0（非热门）
        boolean update = lambdaUpdate()
                .eq(Serve::getId, id)
                .set(Serve::getIsHot, FoundationIsHotEnum.NOT_HOT.getIsHot())
                .update();
        if(!update) {
            throw new CommonException("取消服务热门失败");
        }
        return baseMapper.selectById(id);
    }

    public int queryServeCountByRegionIdAndSaleStatus(Long regionId, int saleStatus) {
        int count = lambdaQuery()
                .eq(Serve::getRegionId, regionId)
                .eq(Serve::getSaleStatus, saleStatus)
                .count();
        return count;
    }

    public int queryServeCountByServeItemIdAndSaleStatus(Long serveItemId, int saleStatus) {
        int count = lambdaQuery()
                .eq(Serve::getServeItemId, serveItemId)
                .eq(Serve::getSaleStatus, saleStatus)
                .count();
        return count;
    }
}
