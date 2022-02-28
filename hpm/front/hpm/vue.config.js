const webpack = require('webpack');
const isProd = process.env.NODE_ENV === "production";

module.exports = {
  runtimeCompiler: true,
  outputDir: '../../src/main/resources/static/',
  publicPath: '/',
  configureWebpack: {
    // Set up all the aliases we use in our app.
    plugins: [
      new webpack.optimize.LimitChunkCountPlugin({
        maxChunks: 6
      })
    ]
  },
  pwa: {
    name: 'hpm',
    themeColor: '#172b4d',
    msTileColor: '#172b4d',
    appleMobileWebAppCapable: 'yes',
    appleMobileWebAppStatusBarStyle: '#172b4d'
  },
  css: {
    // Enable CSS source maps.
    sourceMap: process.env.NODE_ENV !== 'production'
  },
  devServer: {
    disableHostCheck: true,
    port: process.env.VUE_APP_PORT || 8081,
    // proxy: { 
    //         '/api': {                                           //spring rest api를 바로보게 한다.
    //             target:'http://localhost:8080/',
    //             // ws: true,
    //             // secure: false,
    //             changeOrigin: true
    //   }
    // }
  }
};
