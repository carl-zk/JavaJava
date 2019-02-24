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