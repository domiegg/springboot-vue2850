import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import jiaocai from '@/views/modules/jiaocai/list'
    import jiaocaiChuruInout from '@/views/modules/jiaocaiChuruInout/list'
    import jiaocaiChuruInoutList from '@/views/modules/jiaocaiChuruInoutList/list'
    import jiaocaiguanli from '@/views/modules/jiaocaiguanli/list'
    import jiaoshi from '@/views/modules/jiaoshi/list'
    import jiaoshishenqing from '@/views/modules/jiaoshishenqing/list'
    import news from '@/views/modules/news/list'
    import xueshengshenqing from '@/views/modules/xueshengshenqing/list'
    import addXueshengshenqing from '@/views/modules/xueshengshenqing/add-or-update'
    import yonghu from '@/views/modules/yonghu/list'
    import dictionaryBanji from '@/views/modules/dictionaryBanji/list'
    import dictionaryJiaocai from '@/views/modules/dictionaryJiaocai/list'
    import dictionaryJiaocaiChuruInout from '@/views/modules/dictionaryJiaocaiChuruInout/list'
    import dictionaryJiaoshishenqingYesno from '@/views/modules/dictionaryJiaoshishenqingYesno/list'
    import dictionaryKecheng from '@/views/modules/dictionaryKecheng/list'
    import dictionaryNews from '@/views/modules/dictionaryNews/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryXueshengshenqingYesno from '@/views/modules/dictionaryXueshengshenqingYesno/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    } ,{
        path: '/users',
        name: '管理信息',
        component: users
      }
    ,{
        path: '/dictionaryBanji',
        name: '班级',
        component: dictionaryBanji
    }
    ,{
        path: '/dictionaryJiaocai',
        name: '教材类型',
        component: dictionaryJiaocai
    }
    ,{
        path: '/dictionaryJiaocaiChuruInout',
        name: '出入库类型',
        component: dictionaryJiaocaiChuruInout
    }
    ,{
        path: '/dictionaryJiaoshishenqingYesno',
        name: '申请状态',
        component: dictionaryJiaoshishenqingYesno
    }
    ,{
        path: '/dictionaryKecheng',
        name: '课程',
        component: dictionaryKecheng
    }
    ,{
        path: '/dictionaryNews',
        name: '公告类型',
        component: dictionaryNews
    }
    ,{
        path: '/dictionarySex',
        name: '性别',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryXueshengshenqingYesno',
        name: '申请状态',
        component: dictionaryXueshengshenqingYesno
    }


    ,{
        path: '/dictionary',
        name: '字典表',
        component: dictionary
      }
    ,{
        path: '/jiaocai',
        name: '教材信息',
        component: jiaocai
      }
    ,{
        path: '/jiaocaiChuruInout',
        name: '出入库',
        component: jiaocaiChuruInout
      }
    ,{
        path: '/jiaocaiChuruInoutList',
        name: '出入库详情',
        component: jiaocaiChuruInoutList
      }
    ,{
        path: '/jiaocaiguanli',
        name: '教材管理员',
        component: jiaocaiguanli
      }
    ,{
        path: '/jiaoshi',
        name: '教师',
        component: jiaoshi
      }
    ,{
        path: '/jiaoshishenqing',
        name: '教师申请',
        component: jiaoshishenqing
      }
    ,{
        path: '/news',
        name: '公告信息',
        component: news
      }
    ,{
        path: '/xueshengshenqing',
        name: '学生申请',
        component: xueshengshenqing
      },
	  ,{
	      path: '/addXueshengshenqing',
	      name: '申请教材',
	      component: addXueshengshenqing
	    }
	  
    ,{
        path: '/yonghu',
        name: '学生',
        component: yonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
