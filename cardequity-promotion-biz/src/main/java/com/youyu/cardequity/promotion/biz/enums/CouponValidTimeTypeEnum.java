package com.youyu.cardequity.promotion.biz.enums;

import com.youyu.cardequity.common.base.enums.CardequityEnum;
import com.youyu.cardequity.promotion.biz.dal.entity.ProductCouponEntity;
import com.youyu.cardequity.promotion.dto.req.AddCouponReq2;
import com.youyu.cardequity.promotion.dto.req.EditCouponReq2;
import com.youyu.common.exception.BizException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.youyu.cardequity.promotion.enums.ResultCode.COLLECTION_TIME_SETTING_NOT_REASONABLE;

/**
 * @author panqingqing
 * @version v1.0
 * @date 2019年5月13日 10:00:00
 * @work 优惠券有效时间类型枚举
 */
@Getter
public enum CouponValidTimeTypeEnum implements CardequityEnum {

    BY_DATE("0", "按日期") {
        @Override
        public void doCalcValidDate(ProductCouponEntity productCouponEntity, AddCouponReq2 addCouponReq2) {
            productCouponEntity.setAllowUseBeginDate(addCouponReq2.getAllowUseBeginDate());
            productCouponEntity.setAllowUseEndDate(addCouponReq2.getAllowUseEndDate());

            boolean validFlag = !addCouponReq2.getAllowGetBeginDate().isAfter(addCouponReq2.getAllowUseBeginDate())
                    && !addCouponReq2.getAllowGetEndDate().isAfter(addCouponReq2.getAllowUseEndDate());
            if (!validFlag) {
                throw new BizException(COLLECTION_TIME_SETTING_NOT_REASONABLE);
            }

            productCouponEntity.setAllowGetBeginDate(addCouponReq2.getAllowGetBeginDate());
            productCouponEntity.setAllowGetEndDate(addCouponReq2.getAllowGetEndDate());
        }

        @Override
        public void calcValidDate(EditCouponReq2 editCouponReq2, ProductCouponEntity existProductCouponEntity, ProductCouponEntity productCouponEntity) {
            LocalDateTime allowUseBeginDate = existProductCouponEntity.getAllowUseBeginDate();
            LocalDateTime allowUseEndDate = existProductCouponEntity.getAllowUseEndDate();

            boolean validFlag = !editCouponReq2.getAllowGetBeginDate().isAfter(allowUseBeginDate)
                    && !allowUseEndDate.isAfter(allowUseEndDate);
            if (!validFlag) {
                throw new BizException(COLLECTION_TIME_SETTING_NOT_REASONABLE);
            }

            productCouponEntity.setAllowGetBeginDate(editCouponReq2.getAllowGetBeginDate());
            productCouponEntity.setAllowGetEndDate(editCouponReq2.getAllowGetEndDate());
        }
    },
    BY_DAY("1", "按天数") {
        @Override
        public void doCalcValidDate(ProductCouponEntity productCouponEntity, AddCouponReq2 addCouponReq2) {
            productCouponEntity.setValIdTerm(addCouponReq2.getValidTerm());
            super.doCalcValidDate(productCouponEntity, addCouponReq2);
        }
    },
    CURRENT_MONTH_VALID("2", "当月有效");

    private String code;

    private String msg;

    CouponValidTimeTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据优惠券有效日期类型计算日期
     *
     * @param productCouponEntity
     * @param addCouponReq2
     */
    public void calcValidDate(ProductCouponEntity productCouponEntity, AddCouponReq2 addCouponReq2) {
        productCouponEntity.setValidTimeType(addCouponReq2.getValidTimeType());
        doCalcValidDate(productCouponEntity, addCouponReq2);
    }

    /**
     * 添加:计算优惠券领取有效天数
     *
     * @param productCouponEntity
     * @param addCouponReq2
     */
    protected void doCalcValidDate(ProductCouponEntity productCouponEntity, AddCouponReq2 addCouponReq2) {
        checkValidDate(addCouponReq2.getAllowGetBeginDate().toLocalDate());

        productCouponEntity.setAllowGetBeginDate(addCouponReq2.getAllowGetBeginDate());
        productCouponEntity.setAllowGetEndDate(addCouponReq2.getAllowGetEndDate());
    }

    /**
     * 编辑:计算优惠券领取有效天数
     *
     * @param editCouponReq2
     * @param existProductCouponEntity
     * @param productCouponEntity
     */
    public void calcValidDate(EditCouponReq2 editCouponReq2, ProductCouponEntity existProductCouponEntity, ProductCouponEntity productCouponEntity) {
        checkValidDate(editCouponReq2.getAllowGetBeginDate().toLocalDate());

        productCouponEntity.setAllowGetBeginDate(editCouponReq2.getAllowGetBeginDate());
        productCouponEntity.setAllowGetEndDate(editCouponReq2.getAllowGetEndDate());
    }

    /**
     * 检测开始领取时间是否大于等于当前时间
     *
     * @param allowGetBeginDate
     */
    private void checkValidDate(LocalDate allowGetBeginDate) {
        LocalDate nowLocalDate = LocalDate.now();
        if (allowGetBeginDate.isBefore(nowLocalDate)) {
            // TODO: 2019/5/13
            throw new BizException("");
        }
    }
}