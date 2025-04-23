const base = {
    get() {
        return {
            url : "http://localhost:8080/jiaocaiguanlixitong/",
            name: "jiaocaiguanlixitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/jiaocaiguanlixitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "教材管理系统"
        } 
    }
}
export default base
