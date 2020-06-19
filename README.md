# rpc-service-starter
手写简易版RPC框架starter，可直接接入springboot项目提供服务间通信，仅供学习使用  

1、模块api和模块provider都install到本地仓库  
2、需要使用的服务maven依赖这两个jar  
3、服务提供者把Service接口写到api接口，对应的实现类impl使用RpcReference即可发布服务   
4、服务使用者使用RpcService注入api中需要的服务即可使用  

application.properties需要填写以下属性  

rpc.service.invokeHost=localhost //需要调用服务的host  
rpc.service.invokePort=8888 // 需要调用服务的port  
rpc.service.registerPort=8889 // 自己对外开放服务的port  
未接入注册中心，目前只支持两个服务间通信  
感兴趣的同学可以尝试接入注册中心  
