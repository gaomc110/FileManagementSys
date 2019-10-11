package cn.com.sparknet.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户行为监控（自定义注解）
 * @author chenxy
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserBehaviorMonitor {

	String module()  default "未配置模块名称";
	String describe()  default "未配置行为描述";
	
}
