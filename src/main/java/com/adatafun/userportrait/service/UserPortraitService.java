package com.adatafun.userportrait.service;

import com.adatafun.userportrait.model.User;
import com.adatafun.userportrait.utils.ElasticSearch;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * UserPortraitService.java
 * Copyright(C) 2017 杭州风数科技有限公司
 * Created by wzt on 2017/11/25.
 */
@Service
public class UserPortraitService {

    private static ElasticSearch elasticSearch = new ElasticSearch();

    public List<Map> getUserPortraitResult(Map<String, Object> param, JSONObject jsonObject) throws Exception {

        JSONObject bool_json = createQuery(jsonObject);
//        JSONObject termsAgg = createTermsAgg("cityLevel", "cityLevel" + "TermsAgg");
        JSONObject rangeAgg = createRangeAgg(param.get("labelName").toString(), param.get("aggName").toString());

        elasticSearch.setUp();
        List<Map> result = elasticSearch.getUserPortrait(param, bool_json, rangeAgg);
        elasticSearch.tearDown();
        return result;

    }

    public JSONObject createQuery(JSONObject jsonObject) throws Exception {

        List<JSONArray> termsList = new ArrayList<>();
        List<JSONArray> rangesList = new ArrayList<>();
        List<String> termsLabelList = new ArrayList<>();
        List<String> rangesLabelList = new ArrayList<>();
        Set<String> set = jsonObject.keySet();
        User user = new User();

        for (String key : set) {
            if (key.matches(user.getTermsLabelName())) {
                JSONArray cityLevel = jsonObject.getJSONArray(key);
                termsList.add(cityLevel);
                termsLabelList.add(key);
            }

            if (key.matches(user.getRangesLabelName())) {
                JSONArray tdc_accumulationUsage = jsonObject.getJSONArray(key);
                rangesList.add(tdc_accumulationUsage);
                rangesLabelList.add(key);
            }
        }

        JSONObject bool_json = new JSONObject();
        JSONObject must_json = new JSONObject();
        JSONArray must_json_array = new JSONArray();

        for (int i = 0; i < termsLabelList.size(); i++) {
            JSONObject terms0 = createTerms(termsLabelList.get(i), termsList.get(i));
            must_json_array.add(terms0);
        }

        for (int j = 0; j < rangesLabelList.size(); j++) {
            JSONObject bool = createRanges(rangesLabelList.get(j), rangesList.get(j));
            must_json_array.add(bool);
        }

        must_json.put("must", must_json_array);
        bool_json.put("bool", must_json);
        return bool_json;

    }

    public JSONObject createTerms(String labelName, JSONArray labelValue) throws Exception {

        JSONObject terms = new JSONObject();
        JSONObject terms_json = new JSONObject();
        if (!labelName.equals("nameValidation") && !labelName.equals("sex")) {
            labelName = labelName + ".keyword";
        }
        terms_json.put(labelName, labelValue);
        terms.put("terms", terms_json);
        return terms;

    }

    public JSONObject createRanges(String labelName, JSONArray labelValue) throws Exception {

        JSONObject bool = new JSONObject();
        JSONObject should_json = new JSONObject();
        JSONArray should_json_array = new JSONArray();
        for (Object o : labelValue) {
            JSONObject range_tdc = new JSONObject();
            JSONObject range_tdc_json = new JSONObject();
            JSONObject range0_tdc_json = new JSONObject();
            if (o.equals("0次") | o.equals("1次") | o.equals("2次") | o.equals("3次") | o.equals("4次") | o.equals("5次")) {
                range0_tdc_json.put("from", Double.valueOf(o.toString().substring(0,1)) + -0.5);
                range0_tdc_json.put("to",  Double.valueOf(o.toString().substring(0,1)) + 0.5);
                range_tdc_json.put(labelName, range0_tdc_json);
                range_tdc.put("range", range_tdc_json);
                should_json_array.add(range_tdc);
            }

            if (o.equals("6-9次")) {
                range0_tdc_json.put("from", 5.5);
                range0_tdc_json.put("to", 9.5);
                range_tdc_json.put(labelName, range0_tdc_json);
                range_tdc.put("range", range_tdc_json);
                should_json_array.add(range_tdc);
            }

            if (o.equals("10-19次")) {
                range0_tdc_json.put("from", 9.5);
                range0_tdc_json.put("to", 19.5);
                range_tdc_json.put(labelName, range0_tdc_json);
                range_tdc.put("range", range_tdc_json);
                should_json_array.add(range_tdc);
            }

            if (o.equals("20-29次")) {
                range0_tdc_json.put("from", 19.5);
                range0_tdc_json.put("to", 29.5);
                range_tdc_json.put(labelName, range0_tdc_json);
                range_tdc.put("range", range_tdc_json);
                should_json_array.add(range_tdc);
            }

            if (o.equals("30次及以上")) {
                range0_tdc_json.put("from", 29.5);
                range_tdc_json.put(labelName, range0_tdc_json);
                range_tdc.put("range", range_tdc_json);
                should_json_array.add(range_tdc);
            }

        }
        should_json.put("should", should_json_array);
        bool.put("bool", should_json);
        return bool;

    }

    public JSONObject createTermsAgg(String labelName, String aggName) throws Exception {

        JSONObject term = new JSONObject();
        JSONObject term_json = new JSONObject();
        JSONObject agg_json = new JSONObject();
        if (!labelName.equals("nameValidation") && !labelName.equals("sex")) {
            labelName = labelName + ".keyword";
        }
        term_json.put("field", labelName);
        term_json.put("size", 34);
        term.put("terms", term_json);
        agg_json.put(aggName, term);
        return agg_json;

    }

    public JSONObject createRangeAgg(String labelName, String aggName) throws Exception {

        String[] keyArray = {"未知","0次","1次","2次","3次","4次","5次"};
        List<JSONObject> rangeList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            JSONObject range = new JSONObject();
            range.put("key", keyArray[i]);
            range.put("from", -1.5+i);
            range.put("to", -0.5+i);
            rangeList.add(range);
        }
        JSONObject range7 = new JSONObject();
        JSONObject range8 = new JSONObject();
        JSONObject range9 = new JSONObject();
        JSONObject range10 = new JSONObject();
        range7.put("key", "6-9次");
        range7.put("from", 5.5);
        range7.put("to", 9.5);
        rangeList.add(range7);
        range8.put("key", "10-19次");
        range8.put("from", 9.5);
        range8.put("to", 19.5);
        rangeList.add(range8);
        range9.put("key", "20-29次");
        range9.put("from", 19.5);
        range9.put("to", 29.5);
        rangeList.add(range9);
        range10.put("key", "30次及以上");
        range10.put("from", 29.5);
        rangeList.add(range10);

        JSONObject ranges = new JSONObject();
        JSONObject range_json = new JSONObject();
        JSONObject agg0_json = new JSONObject();
        range_json.put("field", labelName);
        range_json.put("ranges", rangeList);
        ranges.put("range", range_json);
        agg0_json.put(aggName, ranges);
        return agg0_json;

    }

}
