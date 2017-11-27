package com.adatafun.userportrait.model;

import io.searchbox.annotations.JestId;

/**
 * User.java
 * Copyright(C) 2017 杭州风数科技有限公司
 * Created by wzt on 05/09/2017.
 */
public class User {

    @JestId
    private String  userId;
    private String  nameValidation;
    private String  sex;
    private String  age;
    private String  city;
    private String  cityLevel;
    private String  cityRegion;
    private String  institutionType;
    private String  protocolType;
    private String  productType;
    private String  loungeType;
    private String  accumulationUsage;
    private String  recencyUsage;
    private String  notRecencyUsage;
    private String  serviceCount;
    private String  guestRoomType;


    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String userId, String nameValidation, String sex, String age, String city,
                String cityLevel, String cityRegion, String institutionType, String protocolType,
                String  productType, String  loungeType, String  accumulationUsage,
                String recencyUsage, String notRecencyUsage, String serviceCount, String guestRoomType) {
        super();
        this.userId = userId;
        this.nameValidation = nameValidation;
        this.sex = sex;
        this.age = age;
        this.city = city;
        this.cityLevel = cityLevel;
        this.cityRegion = cityRegion;
        this.institutionType = institutionType;
        this.protocolType = protocolType;
        this.productType = productType;
        this.loungeType = loungeType;
        this.accumulationUsage = accumulationUsage;
        this.recencyUsage = recencyUsage;
        this.notRecencyUsage = notRecencyUsage;
        this.serviceCount = serviceCount;
        this.guestRoomType = guestRoomType;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", nameValidation=" + nameValidation + ", sex=" + sex +
                ", age=" + age + ", city=" + city + ", cityLevel=" + cityLevel +
                ", cityRegion=" + cityRegion + ", institutionType=" + institutionType + ", protocolType=" + protocolType +
                ", productType=" + productType + ", loungeType=" + loungeType +
                ", accumulationUsage=" + accumulationUsage + ", recencyUsage=" + recencyUsage +
                ", notRecencyUsage=" + notRecencyUsage + ", serviceCount=" + serviceCount + ", guestRoomType=" + guestRoomType +"]";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNameValidation() {
        return nameValidation;
    }

    public void setNameValidation(String nameValidation) {
        this.nameValidation = nameValidation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityLevel() {
        return cityLevel;
    }

    public void setCityLevel(String cityLevel) {
        this.cityLevel = cityLevel;
    }

    public String getCityRegion() {
        return cityRegion;
    }

    public void setCityRegion(String cityRegion) {
        this.cityRegion = cityRegion;
    }

    public String getInstitutionType() {
        return institutionType;
    }

    public void setInstitutionType(String institutionType) {
        this.institutionType = institutionType;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getLoungeType() {
        return loungeType;
    }

    public void setLoungeType(String loungeType) {
        this.loungeType = loungeType;
    }

    public String getAccumulationUsage() {
        return accumulationUsage;
    }

    public void setAccumulationUsage(String accumulationUsage) {
        this.accumulationUsage = accumulationUsage;
    }

    public String getRecencyUsage() {
        return recencyUsage;
    }

    public void setRecencyUsage(String recencyUsage) {
        this.recencyUsage = recencyUsage;
    }

    public String getNotRecencyUsage() {
        return notRecencyUsage;
    }

    public void setNotRecencyUsage(String notRecencyUsage) {
        this.notRecencyUsage = notRecencyUsage;
    }

    public String getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(String serviceCount) {
        this.serviceCount = serviceCount;
    }

    public String getGuestRoomType() {
        return guestRoomType;
    }

    public void setGuestRoomType(String guestRoomType) {
        this.guestRoomType = guestRoomType;
    }
}
