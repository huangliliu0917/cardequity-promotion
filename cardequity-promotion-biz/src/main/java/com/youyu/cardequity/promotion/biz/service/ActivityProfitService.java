package com.youyu.cardequity.promotion.biz.service;

import com.youyu.cardequity.promotion.biz.dal.entity.ActivityProfitEntity;
import com.youyu.cardequity.promotion.dto.ActivityDetailDto;
import com.youyu.cardequity.promotion.dto.ActivityProfitDto;
import com.youyu.cardequity.promotion.dto.ActivityViewDto;
import com.youyu.cardequity.promotion.dto.CommonBoolDto;
import com.youyu.cardequity.promotion.vo.req.*;
import com.youyu.cardequity.promotion.vo.rsp.ActivityDefineRsp;
import com.youyu.cardequity.promotion.vo.rsp.UseActivityRsp;
import com.youyu.common.api.Result;
import com.youyu.common.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *  代码生成器
 *
 * @author 技术平台
 * @date 2018-12-07
 */
public interface ActivityProfitService extends IService<ActivityProfitDto, ActivityProfitEntity> {

    List<ActivityDefineRsp> findEnableGetActivity(QryProfitCommonReq req);

    List<UseActivityRsp> combActivityRefProductDeal(GetUseEnableCouponReq req);

    /**
     * 获取商品活动优惠价
     * @param req
     * @return
     * 1004258-徐长焕-20181226 新建
     */
    ActivityViewDto findActivityPrice(BaseProductReq req);

    /**
     * 批量添加活动
     * @param req
     * @return
     */
    CommonBoolDto<BatchActivityDetailDto> batchAddActivity(BatchActivityDetailDto req);

    /**
     * 批量编辑活动
     * @param req
     * @return
     */
    CommonBoolDto<BatchActivityDetailDto> batchEditActivity(BatchActivityDetailDto req);

    /**
     * 批量删除活动
     * @param req
     * @return
     */
    CommonBoolDto<Integer> batchDelActivity(BatchBaseActivityReq req);

    /**
     * 查找活动
     * @param req
     * @return
     */
    List<ActivityDetailDto> findActivityByCommon(BaseQryActivityReq req);
}




