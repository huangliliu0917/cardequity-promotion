package com.youyu.cardequity.promotion.biz.service;

import com.youyu.cardequity.promotion.biz.dal.entity.ClientCouponEntity;
import com.youyu.cardequity.promotion.dto.CommonBoolDto;
import com.youyu.cardequity.promotion.dto.ClientCouponDto;
import com.youyu.cardequity.promotion.vo.req.ClientObtainCouponReq;
import com.youyu.cardequity.promotion.vo.req.GetUseEnableCouponReq;
import com.youyu.cardequity.promotion.vo.rsp.UseCouponRsp;
import com.youyu.common.service.IService;

import java.util.List;

/**
 *  代码生成器
 *
 * @author 技术平台
 * @date 2018-12-07
 */
public interface ClientCouponService extends IService<ClientCouponDto, ClientCouponEntity> {

    /**
     * 获取客户已领取的券含：
     * 已使用(status=1和2);
     * 未使用（status=0且有效期内）;
     * 已过期（status=0且未在有效期内）
     *
     * @return 返回已领取的券
     * @Param clientId:指定客户号，必填
     */
    List<ClientCouponDto>  findClientCoupon(String clientId);

    /**
     * 领取优惠券
     *
     * @return 是否领取成功
     * @Param req:有参数clientId-客户号（必填），couponId-领取的券Id（必填）
     */
    CommonBoolDto obtainCoupon(ClientObtainCouponReq req);

    /**
     * 获取可用的优惠券:
     * 1.获取满足基本条件、使用频率、等条件
     * 2.没有校验使用门槛，使用门槛是需要和购物车选择商品列表进行计算
     *
     * @param req 本次操作商品详情，其中productList填充商品的详情
     * @return 开发日志
     * 1004246-徐长焕-20181213 新增
     */
    List<ClientCouponDto> findEnableUseCoupon(GetUseEnableCouponReq req);

    /**
     * 按策略获取可用券的组合:不含运费券
     * 1.根据订单或待下单商品列表校验了使用门槛
     * 2.根据冲突关系按策略计算能使用的券
     * 3.计算出每张券的适配使用的商品列表
     *
     * @param req 本次订单详情
     * @return 推荐使用券组合及应用对应商品详情
     */
    List<UseCouponRsp> combCouponRefProductDeal(GetUseEnableCouponReq req);
}




