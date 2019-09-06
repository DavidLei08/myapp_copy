module.exports = {

    productionSourceMap: true,

    devServer: {
        //前端项目地址和端口
        host: "0.0.0.0",
        port: 8080,
        //自动打开浏览器
        open: false,
        hotOnly: true,
        https: false,
    }
}