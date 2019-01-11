package com.youyu.cardequity.promotion.biz.service.impl;

import com.youyu.cardequity.common.base.converter.BeanPropertiesConverter;
import com.youyu.cardequity.common.base.util.BeanPropertiesUtils;
import com.youyu.cardequity.common.spring.service.BatchService;
import com.youyu.cardequity.promotion.biz.dal.dao.ActivityProfitMapper;
import com.youyu.cardequity.promotion.biz.dal.entity.ActivityProfitEntity;
import com.youyu.cardequity.promotion.biz.service.ActivityRefProductService;
import com.youyu.cardequity.promotion.biz.utils.CommonUtils;
import com.youyu.cardequity.promotion.dto.ActivityProfitDto;
import com.youyu.cardequity.promotion.dto.other.CommonBoolDto;
import com.youyu.cardequity.promotion.enums.CommonDict;
import com.youyu.cardequity.promotion.vo.req.BaseActivityReq;
import com.youyu.cardequity.promotion.vo.req.BaseProductReq;
import com.youyu.cardequity.promotion.vo.req.BatchBaseProductReq;
import com.youyu.cardequity.promotion.vo.req.BatchRefProductReq;
import com.youyu.cardequity.promotion.vo.rsp.GatherInfoRsp;
import com.youyu.common.exception.BizException;
import com.youyu.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.youyu.cardequity.promotion.biz.dal.entity.ActivityRefProductEntity;
import com.youyu.cardequity.promotion.dto.ActivityRefProductDto;
import com.youyu.cardequity.promotion.biz.dal.dao.ActivityRefProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.youyu.cardequity.promotion.enums.ResultCode.PARAM_ERROR;


/**
 * 代码生成器
 *
 * @author 技术平台
 * @date 2018-12-20
 */
@Service
public class ActivityRefProductServiceImpl extends AbstractService<String, ActivityRefProductDto, ActivityRefProductEntity, ActivityRefProductMapper> implements ActivityRefProductService {
    @Autowired
    private ActivityRefProductMapper activityRefProductMapper;

    @Autowired
    private ActivityProfitMapper activityProfitMapper;

    @Autowired
    private BatchService batchService;


    /**
     * 指定活动的商品配置范围和其他活动是否冲突,考虑分页的问题
     *
     * @param req
     * @param activity
     * @return
     */
    @Override
    public CommonBoolDto<List<ActivityRefProductEntity>> checkProductReUse(List<BaseProductReq> req, ActivityProfitDto activity) {
        CommonBoolDto<List<ActivityRefProductEntity>> result = new CommonBoolDto(true);
        int perCount = 100, index = 0;
        List<BaseProductReq> listTemp = null;
        do {
            if (req.size() > (index + perCount)) {
                listTemp = req.subList(index, index + perCount);// 分段处理
            } else {
                listTemp = req.subList(index, req.size());//获得[index, req.size())
            }

            List<ActivityRefProductEntity> entities = activityRefProductMapper.findReProductBylist(listTemp, activity);

            if (!entities.isEmpty()) {
                ActivityRefProductEntity entity = entities.get(0);
                result.setSuccess(false);
                result.setDesc("存在商品已经参与了其他活动,如冲突编号=" + entity.getId() + ",商品id=" + entity.getProductId() + "子商品id=" + entity.getSkuId());
                result.setData(entities);
                return result;
            }
            index = index + perCount;
        } while (index <= req.size());

        return result;
    }

    /**
     * 查询已经配置了活动的商品
     *
     * @return
     */
    @Override
    public List<ActivityRefProductDto> findAllProductInValidActivity(BaseActivityReq req) {
        List<ActivityRefProductEntity> entities = activityRefProductMapper.findByExcludeActivityId(req.getActivityId());
        return BeanPropertiesConverter.copyPropertiesOfList(entities, ActivityRefProductDto.class);
    }

    /**
     * 查询活动配置的商品
     *
     * @param req 活动基本信息
     * @return 商品基本信息
     */
    @Override
    public List<BaseProductReq> findActivityProducts(BaseActivityReq req) {
        if (req == null || CommonUtils.isEmptyorNull(req.getActivityId()))
            throw new BizException(PARAM_ERROR.getCode(), PARAM_ERROR.getFormatDesc("没有指定活动"));

        List<ActivityRefProductEntity> refProductEntities = activityRefProductMapper.findByActivityId(req.getActivityId());
        List<BaseProductReq> result = BeanPropertiesConverter.copyPropertiesOfList(refProductEntities, BaseProductReq.class);
        return result;
    }

    /**
     * 配置活动的商品信息
     *
     * @param req
     * @return
     */
    @Override
    public CommonBoolDto<Integer> batchAddActivityRefProduct(BatchRefProductReq req) {
        if (req == null || CommonUtils.isEmptyorNull(req.getId()))
            throw new BizException(PARAM_ERROR.getCode(), PARAM_ERROR.getFormatDesc("没有指定活动"));

        CommonBoolDto<Integer> result = new CommonBoolDto<>(true);
        ActivityProfitEntity profitEntity = activityProfitMapper.findById(req.getId());
        if (profitEntity == null) {
            throw new BizException(PARAM_ERROR.getCode(), PARAM_ERROR.getFormatDesc("指定活动不存在，活动编号" + req.getId()));
        }

        List<String> activities = new ArrayList<>();
        activities.add(req.getId());
        batchService.batchDispose(activities, ActivityRefProductMapper.class, "deleteByActivityId");
        if (req.getProductList() != null && !req.getProductList().isEmpty()) {
            List<ActivityRefProductEntity> entities = new ArrayList<>();
            for (BaseProductReq item : req.getProductList()) {
                ActivityRefProductEntity entity = new ActivityRefProductEntity();
                entity.setCreateAuthor(item.getOperator());
                entity.setUpdateAuthor(item.getOperator());
                entity.setProductId(item.getProductId());
                entity.setActivityId(profitEntity.getId());
                entity.setId(CommonUtils.getUUID());
                entity.setIsEnable(CommonDict.IF_YES.getCode());
                entities.add(entity);
            }
            batchService.batchDispose(entities, ActivityRefProductMapper.class, "insert");
            result.setData(req.getProductList().size());
        }
        return result;

    }


    /**
     * 查询商品对应的活动数量
     * @param req 商品列表
     * @return 商品对应活动数量
     */
    @Override
    public List<GatherInfoRsp> findProductAboutActivityNum(BatchBaseProductReq req){
        if (req == null || req.getProductList()==null || req.getProductList().isEmpty())
            throw new BizException(PARAM_ERROR.getCode(), PARAM_ERROR.getFormatDesc("没有指定商品"));

        List<GatherInfoRsp> result = activityProfitMapper.findActivityNumByProducts(req);
        return result;
    }
}




