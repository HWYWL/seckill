# seckill
秒杀，商品秒杀

### 技术栈
- Spring Boot 2.1.1.RELEASE
- mybatis
- mysql 5.7

### 项目架构
![](https://i.imgur.com/cVPTHQi.jpg)

### 页面
**登录**
![](https://i.imgur.com/ESXphCy.gif)

**主页**
![](https://i.imgur.com/iHGE2h1.gif)

**秒杀**
![](https://i.imgur.com/zVXkPVr.gif)
![](https://i.imgur.com/JXnXxYC.gif)

### 缺点
- 系统水平扩展较低
- 查询效率较低
- 使用了库存行锁(极端)
- 登录并不是分布式而是使用session

### 总结
虽说是秒杀，但还存在很多问题，此系统并不能支持大并发的秒杀活动，需要继续优化，例如我们的**页面缓存**，例如使用分布式锁代替**库存行锁**，等等。。。

### 问题建议

- 联系我的邮箱：ilovey_hwy@163.com
- 我的博客：https://www.hwy.ac.cn
- GitHub：https://github.com/HWYWL

