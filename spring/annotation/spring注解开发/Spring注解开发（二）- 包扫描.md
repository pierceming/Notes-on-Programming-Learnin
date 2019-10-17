# Spring注解开发（二）- 包扫描

## 前言

前一篇文章我们讲到了spring IOC的注解式注入，有关于bean对象的注入，我们对比了xml注入和通过配置**@Configuration** 注解标注的配置类进行**@bean** 的注入。其中注解的方式为我们节省了大量的xml配置工作。但是有一个问题就是，我们如何实现构造器呢，如何进行包扫描呢，这是我们接下来需要探讨的问题。我们在这里先讨论包扫描的问题。包扫描是通过**@ComponentScan** 注解实现的。如何在扫描的同时，添加我们的过滤条件，这种过滤条件又有哪些实现方式呢？我们一起来看下

## 1，@ComponentScan 无过滤条件

### 1.1.1 新建一个配置类：

```java
@ComponentScan("pierce.sustar.controller")
@Configuration
public class MainConfig {
	
}
```

### 1.1.2 新建一个组件对象：

```java
@Controller
public class BookController {
}
```

### 1.1.3 先建一个主类，查看容器中已经装配的bean对象

```java
public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	
	}
```

### 1.1.4 输出结果：

```java
org.springframework.context.annotation.internalConfigurationAnnotationProcessor
org.springframework.context.annotation.internalAutowiredAnnotationProcessor
org.springframework.context.annotation.internalRequiredAnnotationProcessor
org.springframework.context.annotation.internalCommonAnnotationProcessor
org.springframework.context.event.internalEventListenerProcessor
org.springframework.context.event.internalEventListenerFactory
mainConfigTest
bookService   ##我们在容器中注入了这一个service
```

### 1.1.5 总结

**@ComponentScan** 注解类似我们在spring的配置文件中这样一个配置：

```xml
<context:component-scan base-package="pierce.sustar.controller"></context:component-scan>
```

## 2，@ComponentScan 有过滤条件

查看源码(部分)：

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Repeatable(ComponentScans.class)   ## 说明这个注解同一个地方可以多次使用
public @interface ComponentScan {
    @AliasFor("basePackages")   ## 使用basePackages作为别名进行包路径的装载
    String[] value() default {};

    @AliasFor("value") ## 使用value进行多个包路径的装载
    String[] basePackages() default {};

    String resourcePattern() default "**/*.class";

    boolean useDefaultFilters() default true;   ## 使用默认的过滤器

    ComponentScan.Filter[] includeFilters() default {};  ## 包含的过滤器

    ComponentScan.Filter[] excludeFilters() default {};  ## 排除的过滤器

    boolean lazyInit() default false;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({})
    public @interface Filter {   ## 包扫描可能使用的过滤器
        FilterType type() default FilterType.ANNOTATION;

        @AliasFor("classes")
        Class<?>[] value() default {};

        @AliasFor("value")
        Class<?>[] classes() default {};

        String[] pattern() default {};
    }
}
```

## 2.1 排除某些包不扫描

### 2.1.1 新建一个配置类

```java
@ComponentScan(value = "pierce.sustar.demo.bean",excludeFilters = {
		@Filter(type=FilterType.,classes = {Controller.class, Service.class})
})
public class MainConfig {
	
	//给容器中注册一个Bean;类型为返回值的类型，id默认是用方法名作为id
	@Bean("person")
	public Person person01(){
		return new Person("lisi", 20);
	}

}
```

### 2.1.2 创建测试类

```java
public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

		String[] names = context.getBeanDefinitionNames();
		for (String name: names
			 ) {
			System.out.println(name);
		}
	}
```

### 2.1.3 测试结果

```java
mainConfig
bookDao
person
```

#### 工程结构如图：

![](D:\github笔记\Notes\spring\annotation\spring注解开发\imgs\spring注解开发二-工程结构.png)

## 2.2 扫描时只要某些组件

### 2.2.1 配置类：

```java
@ComponentScan(value = "com.atguigu.demo.bean",includeFilters = {
		@Filter(type=FilterType.ANNOTATION,classes = {Controller.class, Service.class})
},useDefaultFilters = false)   ## 注意这里的useDefaultFilters = false 表示禁用默认的过滤器，否则不生效
public class MainConfig {	
	//给容器中注册一个Bean;类型为返回值的类型，id默认是用方法名作为id
	@Bean("person")
	public Person person01(){
		return new Person("lisi", 20);
	}
}
```

## 2.3 @ComponentScans 多扫描包方式

### 2.3.1 配置类

```java
//配置类==配置文件
@Configuration  //告诉Spring这是一个配置类
@ComponentScans(
		value = {
				@ComponentScan(value="com.atguigu.demo.bean",includeFilters = {
						@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
						)
				},useDefaultFilters = false)
		}
		)
public class MainConfig {
	@Bean("person")
	public Person person01(){
		return new Person("lisi", 20);
}
```

## 2.4 包扫描过滤方式之自定义过滤器

### 2.4.1 配置类

```java
@Configuration  //告诉Spring这是一个配置类
@ComponentScans(
		value = {
				@ComponentScan(value="com.atguigu.demo.bean",includeFilters = {
					@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
					@Filter(type=FilterType.CUSTOM,classes={MyTypeFilter.class})  #注意过滤类型
				},useDefaultFilters = false)
		}
		)
public class MainConfig {
	@Bean("person")
	public Person person01(){
		return new Person("lisi", 20);
}
```

### 2.4.2 自定义过滤器

```java
public class MyTypeFilter implements TypeFilter {

	/**
	 * metadataReader：读取到的当前正在扫描的类的信息
	 * metadataReaderFactory:可以获取到其他任何类信息的
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		// TODO Auto-generated method stub
		//获取当前类注解的信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		//获取当前正在扫描的类的类信息
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		//获取当前类资源（类的路径）
		Resource resource = metadataReader.getResource();
		String className = classMetadata.getClassName();
		System.out.println("--->"+className);
		if(className.contains("er")){   ## 过滤器中表示类名包含er的即可作为有效过滤器
			return true;
		}
		return false;
	}

}
```

### 2.4.3 其他类型的过滤方式

```java
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE：按照给定的类型；
//FilterType.ASPECTJ：使用ASPECTJ表达式
//FilterType.REGEX：使用正则指定
//FilterType.CUSTOM：使用自定义规则
```

