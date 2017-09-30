# 练手：高仿掘金App —— 基于 databinding kotlin+Java
 1. 项目结构  
    app-->(net,imageloader,adapter,databinding,base-ui,util)  
    net  
    imageloader-->net  
    adapter  
    databinding-->(imageloader,adapter)  
    base-ui-->adapter  
    util
 2. 自定义属性：bind:curIndex="@{tab.curIndex}" 使用，需要先声明：value = {"fragments", "curIndex"}，<attr name="fragments" format="reference" />
 3. BR.item 怎么找不到呢？我发现写个item_default.xml去binding出来就行了。
 4. 难道是我编辑器问题：kotlin的部分代码错误没有详细信息，需要：./gradlew build clean 才能找到详细错误。坑
 
 # kotlin语法部分
 1. var: 定义变量。val：不可变变量定义，只能赋值一次的变量(类似java中final修饰)
 1. 类型后面加？标示可以为空
 1. 字段后加!!像java一样抛出空异常
 1. ?: 做空判断处理
 1. is 运算符检测一个表达式是否某类型的一个实例(类似java中的instanceof关键字)
 1. .. 区间表达式, step 指定步长, until 排出元素
 1. kotlin 没有基础数据类型，只有封装的数字类型。全对象。
 1. === 三个等号比较对象地址。== 两个等号比较两个值的大小
 1. 类型转换：to类型();
 1. 数组：arrayOf(1, 2, 3) Array(3, { i -> (i * 2) })
 1. 字符：单引号
 1. 字符串：和java一样，String是可不变的。[] 可以获取某个字符，也可for遍历
 1. Any 类似于java.lang.Object。kotlin中所有类都有一个共同的父类Any
 1. apply函数：调用某对象的apply函数，在函数范围内可以任意调用该对象的任意方法，并返回该对象
 1. object 使用object关键字替代class关键字来声明一个单例对象，可以继承其它类或实现其它接口，不能声明构造函数。object 是 lazy-init
    在第一次使用的时候被创造出来。
 1. 注解：向代码中加元数据，在类名前加annotation，声明注解。
    @Target 可以注解的元素类型(类、函数、属性、表达式等)
    @Retention 注解是否可存储在编译后的类文件中，是否在运行时可见(默认true)
 1. invoke 调用方法。方法 a(i) 调用 a.invoke(i)
 
 1. lazy{} 懒属性(延迟加载) 只能用在val类型。  
    lateinit 只能用在var类型。
 6. data?.let{//如果不为空执行该语句}
 7. 在Kotlin中所以的类都有一个默认的父类Any，这个类似于Java中的Object。
 8. open:显示的定义一个父类,关键字跟Java中的final是恰恰相反的。open: 对于可以重写的函数，都需要显示的指明，使用open关键字。open修饰类，说明类可以被子类重写。open修饰方法，说明方法可以被子类重写。  
    子类中被标记为override的成员函数本身也可以被重新写，也就是open的，如果希望禁止被重新写，需要用final关键字
 9. apply函数：在函数范围内，任意用该对象的任意方法，并返回改对象
 10. it:当前这个对象作为闭包的it参数
 11. if (context is Activity) {// 判断activity是否已经销毁了
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && context.isDestroyed)
                 return false
         }
 12. Google官方推出了@IntDef作为对Enum的替代。元注解@RetentionPolicy SOURCE:在原文件中有效，被编辑器丢弃。CLASS:在class文件有效，
     可能被虚拟机忽略。 RUNTIME:在运行时有效。
 13.  @LayoutRes xml布局类型标示
 1. @BindingAdapter 自定义xml中的属性
 
 [参考](https://github.com/fashare2015/MVVM-JueJin)