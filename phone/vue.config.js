module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: "http://127.0.0.1:8089",
        // target: "http://192.168.1.147:8080",
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
}
