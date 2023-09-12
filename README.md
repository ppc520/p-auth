# p-auth
an auth frame likes SpringSecurity

# 快速开始

```xml
<dependency>
   <groupId>ppc</groupId>
   <artifactId>security_framework</artifactId>
   <version>0.0.1-SNAPSHOT</version>
</dependency>
```

```yml
security-frame:
  jwt-config: #配置jwt相关设置
      access-token-expire: 600000 #accessToken的存活时间
      refresh-token-expire: 3000000 #refreshToken的存活时间
      secret: fhsuhfuhfuf #jwt加密的密钥
      access-token-name: access-token1 #设置accessToken的名字
      refresh-token-name: refresh-token1 #设置jwtToken的名字
  result-entity: #返回的实体的配置
    path: com.ppc.common.result.R #指定返回通用实体类的地址
    code-field-name: code #这三个配置是返回实体类的字段名字
    message-field-name: message
    data-field-name: data
  authentication-config:
    login-path: /login #认证时的url 
    username-field-name: username #前端传入的json中username的字段名
    path-strict-matching: false #严格匹配url，设置为true需要路径完全匹配才会通过认证
```
