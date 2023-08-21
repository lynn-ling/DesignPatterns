package com.company.reflection;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

class NoBug {

    @Jiecha
    public void suanShu() {
        System.out.println("1234567890");
    }

    @Jiecha
    public void jiafa() {
        System.out.println("1+1=" + 1 + 1);
    }

    @Jiecha
    public void jiefa() {
        System.out.println("1-1=" + (1 - 1));
    }

    @Jiecha
    public void chengfa() {
        System.out.println("3 x 5=" + 3 * 5);
    }

    @Jiecha
    public void chufa() {
        System.out.println("6 / 0=" + 6 / 0);
    }

    public void ziwojieshao() {
        System.out.println("我写的程序没有 bug!");
    }

}

@Retention(RetentionPolicy.RUNTIME)
@interface Jiecha {
}


public class TestTool {

    public static void main(String[] args) {

        NoBug testobj = new NoBug();

        Class clazz = testobj.getClass();

        Method[] method = clazz.getDeclaredMethods();
        //用来记录测试产生的 log 信息
        StringBuilder log = new StringBuilder();
        // 记录异常的次数
        int errornum = 0;

        for ( Method m: method ) {
            // 只有被 @Jiecha 标注过的方法才进行测试
            if ( m.isAnnotationPresent( Jiecha.class )) {
                try {
                    m.setAccessible(true);
                    m.invoke(testobj, null);

                } catch (Exception e) {
                    errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }


        log.append(clazz.getSimpleName());
        log.append(" has  ");
        log.append(errornum);
        log.append(" error.");

        // 生成测试报告
        System.out.println(log.toString());

    }

    /*
    1234567890
    1+1=11
    1-1=0
    3 x 5=15
    chufa has error:
      caused by ArithmeticException
    / by zero
    NoBug has  1 error.
     */

}
