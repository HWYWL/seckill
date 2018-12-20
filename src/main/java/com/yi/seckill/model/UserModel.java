package com.yi.seckill.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息
 * @author YI
 * @date 2018-12-18 10:29:26
 */
public class UserModel implements Serializable {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String name;

    /**
     * 性别
     */
    @NotNull(message = "性别不能留空")
    private Byte gender;

    /**
     * 年龄
     */
    @NotNull(message = "年龄不能留空")
    @Min(value = 0, message = "年龄必须大于0岁")
    @Max(value = 150, message = "年龄必须小于150岁")
    private Integer age;

    /**
     * 电话号码
     */
    private String telphone;

    /**
     * 注册方式
     */
    private String registerMode;

    /**
     * 第三方登录id
     */
    private String thirdPartyId;

    /**
     * 加密密码
     */
    private String encrptPassword;

    private static final long serialVersionUID = 1L;

    public UserModel() {
        super();
    }

    public UserModel(Integer id, String name, Byte gender, Integer age, String telphone, String registerMode, String thirdPartyId, String encrptPassword) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.telphone = telphone;
        this.registerMode = registerMode;
        this.thirdPartyId = thirdPartyId;
        this.encrptPassword = encrptPassword;
    }

    public UserModel(String name, Byte gender, Integer age, String telphone, String registerMode, String encrptPassword) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.telphone = telphone;
        this.registerMode = registerMode;
        this.encrptPassword = encrptPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }
}