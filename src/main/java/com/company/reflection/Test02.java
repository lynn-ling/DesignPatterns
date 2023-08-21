package com.company.reflection;

//测试Class类的创建方式有哪些
public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("这个人是："+person.name);
        //结果：  这个人是：学生

        //方式一：通过对象获得
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());
        //结果：931919113

        //方式二：通过forname获得
        Class c2 = Class.forName("com.company.reflection.Student");
        System.out.println(c2.hashCode());
        //结果：931919113

        //方式三：通过类名.class获得
        Class c3 = Student.class;
        System.out.println(c3.hashCode());
        //结果：931919113

        //获得父类类型
        Class c5 = c1.getSuperclass();
        System.out.println(c5);
        //结果：class com.company.reflection.Person
    }
}

class Person {
    public String name;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {
    public Student() {
        this.name = "学生";
    }
}

class Teacher extends Person {
    public Teacher() {
        this.name = "老师";
    }
}

