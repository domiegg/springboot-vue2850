package com.dao;

import com.entity.XueshengshenqingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueshengshenqingView;

/**
 * 学生申请 Dao 接口
 *
 * @author 
 */
public interface XueshengshenqingDao extends BaseMapper<XueshengshenqingEntity> {

   List<XueshengshenqingView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
