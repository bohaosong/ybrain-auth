# ybrain-auth

通过密码模式授权流程分析可以知道以下两部分内容：
第一部分：关于授权类型 grant_type 的解析

 1. 每种 grant_type 都会有一个对应的 TokenGranter 实现类。
 2. 所有 TokenGranter 实现类都通过 CompositeTokenGranter 中的 tokenGranters 集合存起来。
 3. 然后通过判断 grantType 参数来定位具体使用那个 TokenGranter 实现类来处理授权。

第二部分：关于授权登录逻辑

 1. 每种 授权方式 都会有一个对应的 AuthenticationProvider 实现类来实现。
 2. 所有 AuthenticationProvider 实现类都通过 ProviderManager 中的 providers 集合存起来。
 3. TokenGranter 类会 new 一个 AuthenticationToken 实现类，如 UsernamePasswordAuthenticationToken 传给 ProviderManager 类。
 4. 而 ProviderManager 则通过 AuthenticationToken 来判断具体使用那个 AuthenticationProvider 实现类来处理授权。
 5. 具体的登录逻辑由 AuthenticationProvider 实现类来实现，如 DaoAuthenticationProvider。


通过以上分析，实现自定义授权方式获取token需要完成以下三个主要内容：

 1. 定义一个新的 grantType 类型，并新增对应的 TokenGranter 实现类添加到 CompositeTokenGranter 中的 tokenGranters 集合里
 2. 新增一个 AuthenticationToken 实现类，用于存放该授权所需的信息。
 3. 新增一个 AuthenticationProvider 实现类 实现授权的逻辑，并重写 supports 方法绑定步骤二的 AuthenticationToken 实现类
创建

https://blog.csdn.net/Devil_Song/article/details/111409631
