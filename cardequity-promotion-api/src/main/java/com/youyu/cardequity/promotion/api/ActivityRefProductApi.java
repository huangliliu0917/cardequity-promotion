package com.youyu.cardequity.promotion.api;

import com.youyu.cardequity.promotion.dto.ActivityRefProductDto;
import com.youyu.cardequity.promotion.dto.other.CommonBoolDto;
import com.youyu.cardequity.promotion.vo.req.*;
import com.youyu.cardequity.promotion.vo.rsp.GatherInfoRsp;
import com.youyu.common.api.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 配置活动的商品信息
 *
 * @author 技术平台
 * @date 2018-12-07
 * 开发日志
 */
@Api(tags = "配置活动的商品信息")
@FeignClient(name = "cardequity-promotion"/*,url = "http://127.0.0.1:8888"*/)
@RequestMapping(path = "/activityRefProduct")
public interface ActivityRefProductApi {

    /**
     * *********************************【通用接口】************************
     * 【通用】查询活动配置的商品
     *
     * @param req 活动基本信息
     * @return 商品基本信息
     */
    @ApiOperation(value = "【通用】查询活动配置的商品")
    @PostMapping(path = "/findActivityProducts")
    Result<List<BaseProductReq>> findActivityProducts(@RequestBody BaseActivityReq req);


    /**
     * *********************************【后台接口】************************
     * 查询已经配置了活动的商品
     *
     * @return
     */
    @ApiOperation(value = "【后台】查询已经配置了活动的商品")
    @PostMapping(path = "/findAllProductInValidActivity")
    Result<List<ActivityRefProductDto>> findAllProductInValidActivity(@RequestBody BaseActivityReq req);

    /**
     * 配置优惠的适用商品范围
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "【后台】配置优惠的适用商品范围")
    @PostMapping(path = "/batchAddActivityRefProduct")
    Result<CommonBoolDto<Integer>> batchAddActivityRefProduct(@RequestBody BatchRefProductReq req);

    /**
     * 查询商品的活动数量
     *
     * @param req 商品基本信息
     * @return 活动数量列表
     */
    @ApiOperation(value = "【后台】查询商品的活动数量")
    @PostMapping(path = "/findProductAboutActivityNum")
    Result<List<GatherInfoRsp>> findProductAboutActivityNum(@RequestBody BatchBaseProductReq req);


    /**
     * 【后台-有效期内-上架的】查询正在参与活动的商品
     *
     * @param req 类型和状态
     * @return 商品列表
     */
    @ApiOperation(value = "【后台-有效期内-上架的】查询正在参与活动的商品")
    @PostMapping(path = "/findProductInValidActivity")
    Result<List<BaseProductReq>> findProductInValidActivity(@RequestBody FindProductInValidActivityReq req);

    /**
     * 根据初始产品列表过滤出可以配置的产品
     *
     * @param req 商品列表及活动
     * @return 可配置商品列表
     */
    @ApiOperation(value = "【后台】根据初始产品列表过滤出可以配置的产品")
    @PostMapping(path = "/findEnableCifgInProducts")
    Result<List<BaseProductReq>> findEnableCifgInProducts(@RequestBody BatchRefProductDetailReq req);
}
