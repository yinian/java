基于授权码模式+Spring Security OAuth2的最简授权服务器
======

# 操作方式

### 1. 获取授权码

浏览器请求：

http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read_userinfo

登录成功后的页面：

<img src="images/1.png" alt="drawing" style="width: 480;"/>


**注意：state参数暂忽略**

响应案例：
http://localhost:9001/callback?code=34UhX9
### 2. 获取访问令牌
通过postman发送Post请求：

curl -X POST --user clientapp:112233 http://localhost:8080/oauth/token -H
"content-type: application/x-www-form-urlencoded" -d
"code=8uYpdo&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalh
ost%3A9001%2Fcallback&scope=read_userinfo"

<img src="images/2.gif" alt="drawing" style="width: 480;"/>

案例响应：
<img src="images/3.gif" alt="drawing" style="width: 480;"/>

```json
{
    "access_token": "36cded80-b6f5-43b7-bdfc-594788a24530",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "read_userinfo"
}
```


### 3. 调用API

curl -X GET http://localhost:8080/api/userinfo -H "authorization: Bearer 36cded80-b6f5-43b7-bdfc-594788a24530"

案例响应：

```json
{
    "name": "bobo",
    "email": "bobo@spring2go.com"
}
```