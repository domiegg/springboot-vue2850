package com.entity.view;

import com.entity.JiaocaiguanliEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 教材管理员
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("jiaocaiguanli")
public class JiaocaiguanliView extends JiaocaiguanliEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 性别的值
		*/
		private String sexValue;



	public JiaocaiguanliView() {

	}

	public JiaocaiguanliView(JiaocaiguanliEntity jiaocaiguanliEntity) {
		try {
			BeanUtils.copyProperties(this, jiaocaiguanliEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 性别的值
			*/
			public String getSexValue() {
				return sexValue;
			}
			/**
			* 设置： 性别的值
			*/
			public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
			}













}
