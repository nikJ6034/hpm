const ansiRegex = require('ansi-regex')
module.exports = {
  runtimeCompiler: true,
  outputDir: '../../src/main/resources/static/',
  publicPath: '/',
  transpileDependencies: [ansiRegex],
  devServer: {
    disableHostCheck: true,
    port:8081,
    proxy: { 
            '/api': {                                           //spring rest api를 바로보게 한다.
                target:'http://localhost:8080/',
                // ws: true,
                // secure: false,
                changeOrigin: true
      }
    }
  }
}
