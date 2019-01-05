package com.youyu.cardequity.promotion.biz.controller;

import com.youyu.cardequity.promotion.api.CouponRefProductApi;
import com.youyu.cardequity.promotion.biz.service.CouponRefProductService;
import com.youyu.cardequity.promotion.dto.CommonBoolDto;
import com.youyu.cardequity.promotion.dto.CouponRefProductDto;
import com.youyu.cardequity.promotion.vo.req.BaseCouponReq;
import com.youyu.cardequity.promotion.vo.req.BatchRefProductReq;
import com.youyu.common.api.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/couponRefProduct")
public class CouponRefProductController implements CouponRefProductApi {

    @Autowired
    private CouponRefProductService couponRefProductService;
    /**
     * 添加优惠券关联商品
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "添加优惠券关联商品")
    @PostMapping(path = "/addProductRefCoupon")
    @Override
    public Result<CommonBoolDto<Integer>> addProductRefCoupon(@RequestBody BatchRefProductReq req){
        return Result.ok(couponRefProductService.batchAddCouponRefProduct(req));
    }

    /**
     * 查询优惠券关联的商品列表
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "查询优惠券关联的商品列表")
    @PostMapping(path = "/findJoinProductByCoupon")
    @Override
    public Result<List<CouponRefProductDto>> findJoinProductByCoupon(@RequestBody BaseCouponReq req){
        return Result.ok(couponRefProductService.findJoinProductByCoupon(req));
    }


}
