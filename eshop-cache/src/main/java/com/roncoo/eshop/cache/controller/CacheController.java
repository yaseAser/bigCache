package com.roncoo.eshop.cache.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.eshop.cache.model.ProductInfo;
import com.roncoo.eshop.cache.model.ShopInfo;
import com.roncoo.eshop.cache.service.CacheService;

/**
 * 缓存Controller
 * @author Administrator
 *
 */
@Controller
public class CacheController {

	@Resource
	private CacheService cacheService;
	
	@RequestMapping("/testPutCache")
	@ResponseBody
	public String testPutCache(ProductInfo productInfo) {
		cacheService.saveLocalCache(productInfo);
		return "success";
	}
	
	@RequestMapping("/testGetCache")
	@ResponseBody
	public ProductInfo testGetCache(Long id) {
		return cacheService.getLocalCache(id);
	}
	
	@RequestMapping("/getProductInfo")
	@ResponseBody
	public ProductInfo getProductInfo(Long productId) {
		ProductInfo productInfo = null;
		
		productInfo = cacheService.getProductInfoFromReidsCache(productId);
		System.out.println("=================从redis中获取缓存，商品信息=" + productInfo);   
		
		if(productInfo == null) {
			productInfo = cacheService.getProductInfoFromLocalCache(productId);
			System.out.println("=================从ehcache中获取缓存，商品信息=" + productInfo);   
		}
		
		if(productInfo == null) {
			// 就需要从数据源重新拉去数据，重建缓存，但是这里先不讲
		}
		
		return productInfo;
	}
	
	@RequestMapping("/getShopInfo")
	@ResponseBody
	public ShopInfo getShopInfo(Long shopId) {
		ShopInfo shopInfo = null;
		
		shopInfo = cacheService.getShopInfoFromReidsCache(shopId);
		System.out.println("=================从redis中获取缓存，店铺信息=" + shopInfo);   
		
		if(shopInfo == null) {
			shopInfo = cacheService.getShopInfoFromLocalCache(shopId);
			System.out.println("=================从ehcache中获取缓存，店铺信息=" + shopInfo);   
		}
		
		if(shopInfo == null) {
			// 就需要从数据源重新拉去数据，重建缓存，但是这里先不讲
		}
		
		return shopInfo;
	}
	
}
