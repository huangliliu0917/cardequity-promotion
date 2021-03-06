package com.youyu.cardequity.promotion.biz.controller;


import com.youyu.cardequity.promotion.api.ProductCouponApi;
import com.youyu.cardequity.promotion.biz.service.ProductCouponService;
import com.youyu.cardequity.promotion.dto.other.CommonBoolDto;
import com.youyu.cardequity.promotion.dto.other.CouponDetailDto;
import com.youyu.cardequity.promotion.dto.other.ObtainCouponViewDto;
import com.youyu.cardequity.promotion.dto.req.AddCouponReq2;
import com.youyu.cardequity.promotion.dto.req.EditCouponReq2;
import com.youyu.cardequity.promotion.dto.req.MemberProductMaxCouponReq;
import com.youyu.cardequity.promotion.dto.rsp.MemberProductMaxCouponRsp;
import com.youyu.cardequity.promotion.vo.req.*;
import com.youyu.cardequity.promotion.vo.rsp.CouponPageQryRsp;
import com.youyu.cardequity.promotion.vo.rsp.GatherInfoRsp;
import com.youyu.common.api.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 代码生成器
 *
 * @author 技术平台
 * @date 2018-12-07
 */
@RestController
@RequestMapping(path = "/productCoupon")
public class ProductCouponController implements ProductCouponApi {

    @Autowired
    private ProductCouponService productCouponService;

    /**
     * *********************************【App接口】************************
     * 【App】获取可以领取的优惠券
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【App-有效期内-上架-有额度】获取可以领取的优惠券")
    @PostMapping(path = "/findEnableGetCoupon")
    public Result<List<CouponDetailDto>> findEnableGetCoupon(@RequestBody QryProfitCommonReq req) {
        List<CouponDetailDto> rspList = productCouponService.findEnableGetCoupon(req);
        return Result.ok(rspList);
    }

    /**
     * 【H5】查询H5首页权益优惠券
     *
     * @param req 查询请求体
     * @return
     */
    @Override
    @ApiOperation(value = "【H5】查询H5首页会员专享权益优惠券")
    @PostMapping(path = "/findFirstPageVipCoupon")
    public Result<List<ObtainCouponViewDto>> findFirstPageVipCoupon(@RequestBody PageQryProfitCommonReq req) {
        return Result.ok(productCouponService.findFirstPageVipCoupon(req));
    }

    /**
     * 查询H5指定月可领优惠券
     *
     * @param req 查询请求体
     * @return
     */
    @Override
    @ApiOperation(value = "【H5】查询H5首页会员专享权益优惠券")
    @PostMapping(path = "/findEnableObtainCouponByMonth")
    public Result<List<ObtainCouponViewDto>> findEnableObtainCouponByMonth(@RequestBody FindEnableObtainCouponByMonthReq req) {
        return Result.ok(productCouponService.findEnableObtainCouponByMonth(req));

    }

    /**
     * *********************************【通用接口】************************
     * 【通用-有效期-上架】查看商品对应优惠券列表
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【通用-有效期-上架】查看商品对应优惠券列表")
    @PostMapping(path = "/findCouponListByProduct")
    public Result<List<CouponDetailDto>> findCouponListByProduct(@RequestBody FindCouponListByProductReq req) {
        return Result.ok(productCouponService.findCouponListByProduct(req));
    }

    /**
     * 查询指定优惠券详情
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【通用】查询指定优惠券详情")
    @PostMapping(path = "/findCouponById")
    public Result<CouponDetailDto> findCouponById(@RequestBody BaseCouponReq req) {
        return Result.ok(productCouponService.findCouponById(req));
    }

    /**
     * 【通用】查看指定优惠券id集合对应优惠券列表
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【通用】查看指定优惠券id集合对应优惠券列表")
    @PostMapping(path = "/findCouponListByIds")
    public Result<List<CouponDetailDto>> findCouponListByIds(@RequestBody List<String> req) {
        return Result.ok(productCouponService.findCouponListByIds(req));
    }

    /**
     * *********************************【后台接口】************************
     * 【后台】添加优惠券
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台】添加优惠券：添加基本信息、领取频率、使用门槛、关联商品等")
    @PostMapping(path = "/addCoupon")
    public Result<CommonBoolDto<CouponDetailDto>> addCoupon(@RequestBody CouponDetailDto req) {
        return Result.ok(productCouponService.addCoupon(req));
    }

    @Override
    @ApiOperation(value = "【后台】添加优惠券：添加基本信息、领取频率、使用门槛、关联商品等")
    @PostMapping(path = "/addCoupon2")
    public Result addCoupon2(@RequestBody AddCouponReq2 addCouponReq2) {
        productCouponService.addCoupon2(addCouponReq2);
        return Result.ok();
    }

    /**
     * 【后台】编辑优惠券
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台】编辑优惠券：编辑基本信息、领取频率、使用门槛、关联商品等")
    @PostMapping(path = "/editCoupon")
    public Result<CommonBoolDto<CouponDetailDto>> editCoupon(@RequestBody CouponDetailDto req) {
        return Result.ok(productCouponService.editCoupon(req));
    }

    @Override
    public Result editCoupon2(@RequestBody EditCouponReq2 editCouponReq2) {
        productCouponService.editCoupon2(editCouponReq2);
        return Result.ok();
    }

    /**
     * 【后台】批量删除优惠券：逻辑删除基本信息、额度值、频率规则、门槛；物理删除商品对应关系
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台】批量删除优惠券：商品对应关系、额度值、频率规则、门槛")
    @PostMapping(path = "/batchDelCoupon")
    public Result<CommonBoolDto<Integer>> batchDelCoupon(@RequestBody BatchBaseCouponReq req) {
        return Result.ok(productCouponService.batchDelCoupon(req));
    }

    /**
     * 【后台-分页】查询所有优惠券列表
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台-分页-汇总】查询所有优惠券列表")
    @PostMapping(path = "/findCouponListByCommon")
    public Result<CouponPageQryRsp> findCouponListByCommon(@RequestBody BaseQryCouponReq req) {
        return Result.ok(productCouponService.findCouponListByCommon(req));

    }

    /**
     * 模糊查询所有优惠券列表
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台-分页-汇总】模糊指定的关键词查询所有优惠券列表")
    @PostMapping(path = "/findCouponList")
    public Result<CouponPageQryRsp> findCouponList(@RequestBody BaseQryCouponReq req) {
        return Result.ok(productCouponService.findCouponList(req));

    }


    /**
     * 查询优惠汇总信息
     *
     * @param req 普通优惠活动请求体
     * @return 优惠汇总列表
     */
    @Override
    @ApiOperation(value = "查询优惠汇总信息")
    @PostMapping(path = "/findGatherCouponByCommon")
    public Result<List<GatherInfoRsp>> findGatherCouponByCommon(@RequestBody BaseQryCouponReq req) {
        return Result.ok(productCouponService.findGatherCouponByCommon(req));
    }

    /**
     * 【后台】上架优惠券
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台】上架优惠券")
    @PostMapping(path = "/upCoupon")
    public Result<CommonBoolDto<Integer>> upCoupon(@RequestBody BatchBaseCouponReq req) {
        return Result.ok(productCouponService.upCoupon(req));

    }

    /**
     * 【后台】下架优惠券
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台】下架优惠券")
    @PostMapping(path = "/downCoupon")
    public Result<CommonBoolDto<Integer>> downCoupon(@RequestBody BatchBaseCouponReq req) {
        return Result.ok(productCouponService.downCoupon(req));
    }

    @Override
    @ApiOperation(value = "获取会员对应的商品最大的优惠券信息")
    @PostMapping(path = "/getMemberProductMaxCoupon")
    public Result<MemberProductMaxCouponRsp> getMemberProductMaxCoupon(@RequestBody MemberProductMaxCouponReq productMaxCouponReq) {
        return Result.ok(productCouponService.getMemberProductMaxCoupon(productMaxCouponReq));
    }

}
