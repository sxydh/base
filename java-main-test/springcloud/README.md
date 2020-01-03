# Profile
Simple samples of springcloud.
# QuickStart
  * 启动注册中心-[*server*](./springcloud-server/src/main/java/fun/ehe/App.java)
  * 启动服务端-[*provider*](./springcloud-provider/src/main/java/fun/ehe/App.java)
  * 启动客户端-[*comsumerWithRibbon*](./springcloud-cousumer-ribbon/src/main/java/fun/ehe/App.java)
  * 访问 `http://localhost:9000/hello?name=Jack`
# Specification
  * 负载均衡
    * Ribbon
      * 启动[*server*](./springcloud-server/src/main/java/fun/ehe/App.java)
      * 启动多个 (改端口方式) [*provider*](./springcloud-provider/src/main/java/fun/ehe/App.java)
      * 启动[*comsumerWithRibbon*](./springcloud-cousumer-ribbon/src/main/java/fun/ehe/App.java)
      * 多次访问 `http://localhost:9000/hello?name=Jack`
    * Feign
      * 启动[*server*](./springcloud-server/src/main/java/fun/ehe/App.java)
      * 启动多个 (改端口方式) [*provider*](./springcloud-provider/src/main/java/fun/ehe/App.java)
      * 启动[*comsumerWithFeign*](./springcloud-consumer-feign/src/main/java/fun/ehe/App.java)
      * 多次访问 `http://localhost:9000/hello?name=Jack`
  * 路由网关
    * Zuul
      * 启动[*server*](./springcloud-server/src/main/java/fun/ehe/App.java)
      * 启动[*provider*](./springcloud-provider/src/main/java/fun/ehe/App.java)
      * 启动[*comsumerWithFeign*](./springcloud-consumer-feign/src/main/java/fun/ehe/App.java)
      * 启动[*comsumerWithRibbon*](./springcloud-cousumer-ribbon/src/main/java/fun/ehe/App.java)
      * 启动[*zuul*](./springcloud-zuul/src/main/java/fun/ehe/App.java)
      * 访问 `http://localhost:10000/feign/hello?name=Jack` 或 `http://localhost:10000/ribbon/hello?name=Jack`