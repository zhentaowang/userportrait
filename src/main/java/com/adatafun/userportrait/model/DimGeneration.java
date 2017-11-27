/**
 * DimGeneration.java
 * Copyright(C) 2016 杭州量子金融信息服务有限公司
 * https://www.zhiweicloud.com
 * 2017-11-27 17:07:22 Created By wzt
*/
package com.adatafun.userportrait.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * DimGeneration.java
 * Copyright(C) 2016 杭州量子金融信息服务有限公司
 * https://www.zhiweicloud.com
 * 2017-11-27 17:07:22 Created By wzt
*/
@ApiModel(value="DimGeneration",description="dim_generation")
public class DimGeneration {
    @ApiModelProperty(value="",name="id", required=true)
    @NotEmpty
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @ApiModelProperty(value="",name="name")
    private String name;

    @ApiModelProperty(value="",name="age")
    private Date age;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return name 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return age 
     */
    public Date getAge() {
        return age;
    }

    /**
     * 
     * @param age 
     */
    public void setAge(Date age) {
        this.age = age;
    }
}