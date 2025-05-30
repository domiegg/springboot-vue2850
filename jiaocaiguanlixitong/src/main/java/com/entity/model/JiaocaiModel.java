package com.entity.model;

import com.entity.JiaocaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 教材信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiaocaiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 教材编号
     */
    private String jiaocaiOrderUuidNumber;


    /**
     * 教材类型
     */
    private Integer jiaocaiTypes;


    /**
     * 教材库存
     */
    private Integer jiaocaiKucunNumber;


    /**
     * 教材价格
     */
    private Double jiaocaiNewMoney;


    /**
     * 发布时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：教材编号
	 */
    public String getJiaocaiOrderUuidNumber() {
        return jiaocaiOrderUuidNumber;
    }


    /**
	 * 设置：教材编号
	 */
    public void setJiaocaiOrderUuidNumber(String jiaocaiOrderUuidNumber) {
        this.jiaocaiOrderUuidNumber = jiaocaiOrderUuidNumber;
    }
    /**
	 * 获取：教材类型
	 */
    public Integer getJiaocaiTypes() {
        return jiaocaiTypes;
    }


    /**
	 * 设置：教材类型
	 */
    public void setJiaocaiTypes(Integer jiaocaiTypes) {
        this.jiaocaiTypes = jiaocaiTypes;
    }
    /**
	 * 获取：教材库存
	 */
    public Integer getJiaocaiKucunNumber() {
        return jiaocaiKucunNumber;
    }


    /**
	 * 设置：教材库存
	 */
    public void setJiaocaiKucunNumber(Integer jiaocaiKucunNumber) {
        this.jiaocaiKucunNumber = jiaocaiKucunNumber;
    }
    /**
	 * 获取：教材价格
	 */
    public Double getJiaocaiNewMoney() {
        return jiaocaiNewMoney;
    }


    /**
	 * 设置：教材价格
	 */
    public void setJiaocaiNewMoney(Double jiaocaiNewMoney) {
        this.jiaocaiNewMoney = jiaocaiNewMoney;
    }
    /**
	 * 获取：发布时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：发布时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
