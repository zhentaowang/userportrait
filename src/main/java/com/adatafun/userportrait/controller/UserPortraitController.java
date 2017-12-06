package com.adatafun.userportrait.controller;

import com.adatafun.userportrait.service.DimGenerationService;
import com.adatafun.userportrait.service.UserPortraitService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhiweicloud.guest.APIUtil.LXResult;
import com.zhiweicloud.guest.APIUtil.LZStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserPortraitController.java
 * Copyright(C) 2017 杭州风数科技有限公司
 * Created by wzt on 2017/11/29.
 */
@Component
public class UserPortraitController {

    private final UserPortraitService userPortraitService;
    private final DimGenerationService dimGenerationService;

    @Autowired
    public UserPortraitController(UserPortraitService userPortraitService, DimGenerationService dimGenerationService) {
        this.userPortraitService = userPortraitService;
        this.dimGenerationService = dimGenerationService;
    }

    public String getUserPortraitResult(final JSONObject request) {

        try {
            JSONArray data = JSONArray.parseArray(request.get("data").toString());
            String portraitType = request.get("portrait_type").toString();
            JSONObject jsonObject = JSONObject.parseObject(data.get(0).toString());
            JSONObject result = new JSONObject();
            JSONObject user = new JSONObject();

//            List<Long> level = dimGenerationService.getGenerationList(age);

            Map<String, Object> param = new HashMap<>();
            param.put("indexName", "lj-user");
            param.put("typeName", "lj-user");

            if (portraitType != null) {
                param.put("labelName", portraitType);
                param.put("aggName", portraitType+"RangeAgg");
                List<Map> userPortrait = userPortraitService.getUserPortraitResult(param, jsonObject);
                List<Map> userPortraitResult = transferDataForm(userPortrait);
                user.put("userPortrait", userPortraitResult);
            }


            param.put("labelName", "accumulationUsageTotal");
            param.put("aggName", "accumulationUsageTotalRangeAgg");
            List<Map> accumulationUsageTotal = userPortraitService.getUserPortraitResult(param, jsonObject);
            Object total = accumulationUsageTotal.get(accumulationUsageTotal.size() - 1).get("total");
            user.put("total", total);
            List<Map> accumulationUsageTotalResult = transferDataForm(accumulationUsageTotal);

            param.put("labelName", "province");
            param.put("aggName", "provinceTermsAgg");
            List<Map> province = userPortraitService.getUserPortraitResult(param, jsonObject);
            List<Map> provinceResult = transferDataForm(province);

            param.put("labelName", "sex");
            param.put("aggName", "sexTermsAgg");
            List<Map> sex = userPortraitService.getUserPortraitResult(param, jsonObject);
            List<Map> sexResult = transferDataForm(sex);

            user.put("accumulationUsageTotal", accumulationUsageTotalResult);
            user.put("province", provinceResult);
            user.put("sex", sexResult);
            result.put("user", user);
            result.put("status", LZStatus.SUCCESS.value());
            result.put("msg", LZStatus.SUCCESS.display());

            return JSON.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(LXResult.build(LZStatus.ERROR.value(), LZStatus.ERROR.display()));
        }

    }

    public List<Map> transferDataForm (List<Map> result) {
        List<Map> newResult = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Map<String, Object> mapResult = new HashMap<>();
            mapResult.put("name", result.get(i).get("key"));
            mapResult.put("id", i);
            mapResult.put("value", result.get(i).get("doc_count"));
            newResult.add(mapResult);
        }
        return newResult;
    }

}
