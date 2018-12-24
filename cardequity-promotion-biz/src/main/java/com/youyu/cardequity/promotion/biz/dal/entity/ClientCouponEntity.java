package com.youyu.cardequity.promotion.biz.dal.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "TB_CLIENT_COUPON")
public class ClientCouponEntity extends com.youyu.common.entity.BaseEntity<String> {
    /**
     * 编号:
     */
    @Id
    @Column(name = "UUID")
    private String uuid;

    /**
     * 优惠券编号:
     */
    @Column(name = "COUPON_ID")
    private String couponId;

    /**
     * 阶梯编号:
     */
    @Column(name = "STAGE_ID")
    private String stageId;

    /**
     * 优惠策略类型:0-折扣券(该表不会出现) 1-阶梯优惠券(满多少减多少)  2-定额优惠券（该券无阶梯优惠固定金额）
     */
    @Column(name = "COUPON_STRATEGY_TYPE")
    private String couponStrategyType;

    /**
     * 类型:0-红包 1-优惠券 2-运费券
     */
    @Column(name = "COUPON_TYPE")
    private String couponType;


    /**
     * 优惠标签:标签：满返券、促销等
     */
    @Column(name = "COUPON_LABLE")
    private String couponLable;

    /**
     * 优惠短描:如满3件减20
     */
    @Column(name = "COUPON_SHORT_DESC")
    private String couponShortDesc;

    /**
     * 门槛触发类型:0-按买入金额 1-按买入数量（应设置其中之一，如果第二件5折可在此设置）
     */
    @Column(name = "TRIGGER_BY_TYPE")
    private String triggerByType;

    /**
     * 值起始（不含）:没有阶梯填(0,999999999]
     */
    @Column(name = "BEGIN_VALUE")
    private BigDecimal beginValue;

    /**
     * 结束值（含）:最大值为999999999；
     */
    @Column(name = "END_VALUE")
    private BigDecimal endValue;

    /**
     * 客户号:
     */
    @Column(name = "CLIENT_ID")
    private String clientId;

    /**
     * 委托方式:通过什么方式获取
     */
    @Column(name = "ENTRUST_WAY")
    private String entrustWay;

    /**
     * 优惠金额:
     */
    @Column(name = "COUPON_AMOUT")
    private BigDecimal couponAmout;

    /**
     * 有效起始日:到时分秒
     */
    @Column(name = "VALID_START_DATE")
    private LocalDate validStartDate;

    /**
     * 有效结束日:到时分秒
     */
    @Column(name = "VALID_END_DATE")
    private LocalDate validEndDate;

    /**
     * 状态:0-正常 1-使用中 2-已使用
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 关联订单号:多次恢复可能涉及多个订单号时填最近一次订单号
     */
    @Column(name = "JOIN_ORDER_ID")
    private String joinOrderId;

    /**
     * 备注:
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 使用日期
     */
    @Column(name = "BUSIN_DATE")
    private LocalDate BusinDate;

    /**
     * 级别：0-自定义 1-全局
     */
    @Column(name = "COUPON_LEVEL")
    private String couponLevel;


    /**
     * 是否有效:
     */
    @Column(name = "IS_ENABLE")
    private String isEnable;

    @Override
    public String getId() {
        return uuid;
    }

    @Override
    public void setId(String uuid) {
        this.uuid = uuid;
    }
}