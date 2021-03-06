package com.youyu.cardequity.promotion.biz.controller;

import com.youyu.cardequity.promotion.api.ActivityRefProductApi;
import com.youyu.cardequity.promotion.biz.service.ActivityRefProductService;
import com.youyu.cardequity.promotion.dto.ActivityRefProductDto;
import com.youyu.cardequity.promotion.dto.other.CommonBoolDto;
import com.youyu.cardequity.promotion.vo.req.*;
import com.youyu.cardequity.promotion.vo.rsp.GatherInfoRsp;
import com.youyu.common.api.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/activityRefProduct")
public class ActivityRefProductController implements ActivityRefProductApi {
    @Autowired
    public ActivityRefProductService activityRefProductService;

    /**
     * 【后台】查询指定活动外已经配置了活动的商品
     *
     * @return
     */
    @Override
    @ApiOperation(value = "【后台】查询指定活动外已经配置了活动的商品")
    @PostMapping(path = "/findAllProductInValidActivity")
    public Result<List<ActivityRefProductDto>> findAllProductInValidActivity(@RequestBody BaseActivityReq req) {
        return Result.ok(activityRefProductService.findAllProductInValidActivity(req));
    }

    /**
     * 【后台】配置优惠的适用商品范围
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "【后台】配置优惠的适用商品范围")
    @PostMapping(path = "/batchAddActivityRefProduct")
    public Result<CommonBoolDto<Integer>> batchAddActivityRefProduct(@RequestBody BatchRefProductReq req){
        return Result.ok(activityRefProductService.batchAddActivityRefProduct(req));

    }


    /**
     * 【后台】查询商品的活动数量
     * @param req 商品基本信息
     * @return 活动数量列表
     */
    @Override
    @ApiOperation(value = "【后台】查询正在参与活动的商品")
    @PostMapping(path = "/findProductAboutActivityNum")
    public Result<List<GatherInfoRsp>> findProductAboutActivityNum(@RequestBody BatchBaseProductReq req){
        return Result.ok(activityRefProductService.findProductAboutActivityNum(req));

    }


    /**
     * 【后台-有效期内-上架的】查询商品对应的活动数量
     * @param req 类型和状态
     * @return 商品列表
     */
    @Override
    @ApiOperation(value = "【后台-有效期内-上架的】查询商品的活动数量")
    @PostMapping(path = "/findProductInValidActivity")
    public Result<List<BaseProductReq>> findProductInValidActivity(@RequestBody FindProductInValidActivityReq req){
        return Result.ok(activityRefProductService.findProductInValidActivity(req));

    }

    /**
     * *********************************【通用接口】************************
     * 【通用】查询活动配置的商品
     * @param req 活动基本信息
     * @return 商品基本信息
     */
    @Override
    @ApiOperation(value = "【通用】查询活动配置的商品")
    @PostMapping(path = "/findActivityProducts")
    public Result<List<BaseProductReq>> findActivityProducts(@RequestBody BaseActivityReq req){
        return Result.ok(activityRefProductService.findActivityProducts(req));

    }

    /**
     * 根据初始产品列表过滤出可以配置的产品
     *
     * @param req 商品列表及活动
     * @return 可配置商品列表
     */
    @Override
    @ApiOperation(value = "【后台】根据初始产品列表过滤出可以配置的产品")
    @PostMapping(path = "/findEnableCifgInProducts")
    public Result<List<BaseProductReq>> findEnableCifgInProducts(@RequestBody BatchRefProductDetailReq req){
        return Result.ok(activityRefProductService.findEnableCifgInProducts(req));

    }
}
