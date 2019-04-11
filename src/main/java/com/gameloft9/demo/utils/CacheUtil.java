package com.gameloft9.demo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 啊发包
 * @Date: 2019/04/10 2019-04-10
 * 缓存工具类
 */
public class CacheUtil {

    private static CacheUtil cacheUtil;

    /**map缓存*/
    private static Map<String, List<String>> cacheMap;

    private CacheUtil(){
        cacheMap = new HashMap<String, List<String>>();
    }

    /**单例*/
    public static CacheUtil getInstance(){
        if(cacheUtil == null){
            cacheUtil = new CacheUtil();
        }
        return cacheUtil;
    }

    /**
     * 添加缓存
     * @param key key
     * @param value value
     */
    public void addCacheData(String key,List<String> value){
        cacheMap.put(key,value);
    }

    /**
     * 取出缓存
     * @param key key
     * @return 缓存
     */
    public List<String> getCacheData(String key){
        return cacheMap.get(key);
    }

    /**
     * 起初缓存
     * @param key
     */
    public void removeCacheData(String key){
        cacheMap.remove(key);
    }


}
