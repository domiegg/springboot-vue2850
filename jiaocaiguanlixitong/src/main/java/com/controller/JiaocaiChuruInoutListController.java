
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 出入库详情
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiaocaiChuruInoutList")
public class JiaocaiChuruInoutListController {
    private static final Logger logger = LoggerFactory.getLogger(JiaocaiChuruInoutListController.class);

    @Autowired
    private JiaocaiChuruInoutListService jiaocaiChuruInoutListService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private JiaocaiService jiaocaiService;
    @Autowired
    private JiaocaiChuruInoutService jiaocaiChuruInoutService;

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private JiaoshiService jiaoshiService;
    @Autowired
    private JiaocaiguanliService jiaocaiguanliService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("教师".equals(role))
            params.put("jiaoshiId",request.getSession().getAttribute("userId"));
        else if("教材管理员".equals(role))
            params.put("jiaocaiguanliId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jiaocaiChuruInoutListService.queryPage(params);

        //字典表数据转换
        List<JiaocaiChuruInoutListView> list =(List<JiaocaiChuruInoutListView>)page.getList();
        for(JiaocaiChuruInoutListView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaocaiChuruInoutListEntity jiaocaiChuruInoutList = jiaocaiChuruInoutListService.selectById(id);
        if(jiaocaiChuruInoutList !=null){
            //entity转view
            JiaocaiChuruInoutListView view = new JiaocaiChuruInoutListView();
            BeanUtils.copyProperties( jiaocaiChuruInoutList , view );//把实体数据重构到view中

                //级联表
                JiaocaiEntity jiaocai = jiaocaiService.selectById(jiaocaiChuruInoutList.getJiaocaiId());
                if(jiaocai != null){
                    BeanUtils.copyProperties( jiaocai , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiaocaiId(jiaocai.getId());
                }
                //级联表
                JiaocaiChuruInoutEntity jiaocaiChuruInout = jiaocaiChuruInoutService.selectById(jiaocaiChuruInoutList.getJiaocaiChuruInoutId());
                if(jiaocaiChuruInout != null){
                    BeanUtils.copyProperties( jiaocaiChuruInout , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiaocaiChuruInoutId(jiaocaiChuruInout.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiaocaiChuruInoutListEntity jiaocaiChuruInoutList, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiaocaiChuruInoutList:{}",this.getClass().getName(),jiaocaiChuruInoutList.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("教材信息".equals(role))
            jiaocaiChuruInoutList.setJiaocaiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiaocaiChuruInoutListEntity> queryWrapper = new EntityWrapper<JiaocaiChuruInoutListEntity>()
            .eq("jiaocai_churu_inout_id", jiaocaiChuruInoutList.getJiaocaiChuruInoutId())
            .eq("jiaocai_id", jiaocaiChuruInoutList.getJiaocaiId())
            .eq("jiaocai_churu_inout_list_number", jiaocaiChuruInoutList.getJiaocaiChuruInoutListNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaocaiChuruInoutListEntity jiaocaiChuruInoutListEntity = jiaocaiChuruInoutListService.selectOne(queryWrapper);
        if(jiaocaiChuruInoutListEntity==null){
            jiaocaiChuruInoutList.setInsertTime(new Date());
            jiaocaiChuruInoutList.setCreateTime(new Date());
            jiaocaiChuruInoutListService.insert(jiaocaiChuruInoutList);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaocaiChuruInoutListEntity jiaocaiChuruInoutList, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jiaocaiChuruInoutList:{}",this.getClass().getName(),jiaocaiChuruInoutList.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("教材信息".equals(role))
//            jiaocaiChuruInoutList.setJiaocaiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JiaocaiChuruInoutListEntity> queryWrapper = new EntityWrapper<JiaocaiChuruInoutListEntity>()
            .notIn("id",jiaocaiChuruInoutList.getId())
            .andNew()
            .eq("jiaocai_churu_inout_id", jiaocaiChuruInoutList.getJiaocaiChuruInoutId())
            .eq("jiaocai_id", jiaocaiChuruInoutList.getJiaocaiId())
            .eq("jiaocai_churu_inout_list_number", jiaocaiChuruInoutList.getJiaocaiChuruInoutListNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaocaiChuruInoutListEntity jiaocaiChuruInoutListEntity = jiaocaiChuruInoutListService.selectOne(queryWrapper);
        if(jiaocaiChuruInoutListEntity==null){
            jiaocaiChuruInoutListService.updateById(jiaocaiChuruInoutList);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jiaocaiChuruInoutListService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<JiaocaiChuruInoutListEntity> jiaocaiChuruInoutListList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JiaocaiChuruInoutListEntity jiaocaiChuruInoutListEntity = new JiaocaiChuruInoutListEntity();
//                            jiaocaiChuruInoutListEntity.setJiaocaiChuruInoutId(Integer.valueOf(data.get(0)));   //出入库 要改的
//                            jiaocaiChuruInoutListEntity.setJiaocaiId(Integer.valueOf(data.get(0)));   //教材 要改的
//                            jiaocaiChuruInoutListEntity.setJiaocaiChuruInoutListNumber(Integer.valueOf(data.get(0)));   //操作数量 要改的
//                            jiaocaiChuruInoutListEntity.setInsertTime(date);//时间
//                            jiaocaiChuruInoutListEntity.setCreateTime(date);//时间
                            jiaocaiChuruInoutListList.add(jiaocaiChuruInoutListEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jiaocaiChuruInoutListService.insertBatch(jiaocaiChuruInoutListList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
