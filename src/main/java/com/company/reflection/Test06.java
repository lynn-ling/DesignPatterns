package com.company.reflection;

//类加载器
public class Test06 {
    public static void main(String[] args) throws ClassNotFoundException {
        //获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        //jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b

        //获取系统类加载器的父类加载器 → 扩展类加载器
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        //jdk.internal.loader.ClassLoaders$PlatformClassLoader@568db2f2
        
        //获取扩展类加载器的父类加载器 → 根加载器
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);
        //null

        //测试当前类是哪个加载器加载的
        ClassLoader classLoader = Class.forName("com.company.reflection.Test06").getClassLoader();
        System.out.println(classLoader);
        //jdk.internal.loader.ClassLoaders$AppClassLoader@63947c6b

        //测试JDK内置的类是谁加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);
        //null

        //如何获得系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
        /*
        F:\project\src\github\DesignPatterns\target\classes;
        C:\Users\包子铺\.m2\repository\org\springframework\spring-context\5.3.18\spring-context-5.3.18.jar;
        C:\Users\包子铺\.m2\repository\org\springframework\spring-aop\5.3.18\spring-aop-5.3.18.jar;
        C:\Users\包子铺\.m2\repository\org\springframework\spring-beans\5.3.18\spring-beans-5.3.18.jar;
        C:\Users\包子铺\.m2\repository\org\springframework\spring-core\5.3.18\spring-core-5.3.18.jar;
        C:\Users\包子铺\.m2\repository\org\springframework\spring-jcl\5.3.18\spring-jcl-5.3.18.jar;
        C:\Users\包子铺\.m2\repository\org\springframework\spring-expression\5.3.18\spring-expression-5.3.18.jar;
        C:\Users\包子铺\.m2\repository\cglib\cglib\3.3.0\cglib-3.3.0.jar;
        C:\Users\包子铺\.m2\repository\org\ow2\asm\asm\7.1\asm-7.1.jar
         */
        //类都在上面这些路径里。如果没在的话就加载不出来

        /*
        双亲委派机制：
        我自己定义了一个类，比如叫java.lang.String。会先去用户类加载器看有没有这个包，再去内置类里找，再去系统类加载器里找。
        如果有java.lang.String类，那么自己写的这个类运行不起来
         */

    }
}
