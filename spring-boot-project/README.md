## LocalDateTime 序列化
```java
public class UserVo {
  @LocalDateTimeFormat
  private LocalDateTime lastLoginAt;
}
```
Controller层返回 UserVo 时，lastLoginAt 以时间戳格式返回。

## LocaleResolver
默认语言解析，取 Header 的 key locale 所对应的 value。

## logback.groovy 日志配置

## hibernate validator 
[hibernate validator](https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#_validating_constraints)

@Validated 加在 controller 上，可以使用 @Size 限制 list 的长度 (list 前必须加 @RequestParam)

## global exception handler
@ControllerAdvice
@ExceptionHandler

## querydsl 

## mockito
unit test

## mapstruct
Entity to DTO (vice versa)

## failsafe
integration test plugin

## flyway
with @RunWith(H2EachResetExtension.class) can keep db clean before start each test method

## spring data jpa audit & hibernate envers

## response without using Result.success()
直接在controller中返回数据对象，使用@ResponseResult去封装返回值。
https://mp.weixin.qq.com/s/nx-HSNbasb7SDiSISwuF4A