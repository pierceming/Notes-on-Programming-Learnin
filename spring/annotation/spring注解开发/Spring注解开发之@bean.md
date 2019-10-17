# Spring注解开发之@bean

## 1, spring ioc注入之单实例

### 1.1 单实例开发 Singleton

#### 1.1.1 创建一个配置类

```java
@Configuration
public class MainConfig2 {
@Bean("person")
	public Person person(){
		System.out.println("给容器中添加Person....");
		return new Person("张三", 25);
	}
}
```

#### 1.1.2 启动类测试

```java
@Test
	public void test02(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		Object bean = applicationContext.getBean("person");
		Object bean2 = applicationContext.getBean("person");
		System.out.println(bean == bean2);
	}
```

#### 1.1.3 打印结果：

```java
true
```

### 1.2 多实例开发 prototype

#### 1.2.1 创建配置类

```
@Configuration
public class MainConfig2 {
@Scope("prototype")
@Bean("person")
	public Person person(){
		System.out.println("给容器中添加Person....");
		return new Person("张三", 25);
	}
}
```

### 1.2.2 打印结果：

```java
false
```

**特别注意说明：**

在多实例注解开发时，只用在使用到对象的时候，容器才会创建一个对象，某则不会创建。而在单例模式，则是在容器加载的时候就已经实例化完毕。如果单例情况也像和多实例一样，创建的时候才会实例化，那么可以使用**@Lazy**注解，如下：

```java
@Lazy
	@Bean("person")
	public Person person(){
		System.out.println("给容器中添加Person....");
		return new Person("张三", 25);
	}
```



### 