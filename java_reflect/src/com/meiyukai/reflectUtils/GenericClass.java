package com.meiyukai.reflectUtils;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.List;
import java.util.Map;
public class GenericClass<T> implements Serializable {

    //成员变量
    public Map<T , String > genericField;
    //无参构造函数
    public GenericClass(){}
    //有参构造函数
    public <E>GenericClass(E e){}
    //普通方法
    public <B> Map<Integer , String>[] genericMethod(List<? extends Integer> list , List<String> list2 , String  str , B[] tArr) throws Exception{
        return  null;
    }


    static  void instanceActualTypeArguments(Type type) throws Exception{
        System.out.println("【传入的参数类型是 ： 】" +type );

        if (type instanceof ParameterizedType){
            System.out.println("type :   "+ type+"   是 ----- 参数化类型");
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            for (int i = 0 ; i<actualTypeArguments.length; i++){
                Type tempType = actualTypeArguments[i];

                if (tempType instanceof TypeVariable){
                    System.out.println("第"+i+"个参数："+tempType+"    "+ "是类型变量，无法实例化");
                }

                if (tempType instanceof WildcardType){
                    System.out.println("第"+i+"个参数："+tempType+"    "+ "是通配符，无法实例化");
                }

                if (tempType instanceof  Class){
                    System.out.println("第"+i+"个参数："+tempType+"    "+ "是类，可以直接实例化！！！");
                }

            }
        }

        else if (type instanceof GenericArrayType){
            System.out.println("type   "+type+  " 是参数化类型数组 或者 类型变量数组  ");
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            System.out.println("将 参数化／类型变量  数组 迭代分析如下 ： ");
            instanceActualTypeArguments(genericComponentType);
        }


        else if (type instanceof TypeVariable){
            System.out.println(" type   :  "+type+"是 ----- 类型变量  ");
        }

        else if(type instanceof  WildcardType){
            System.out.println("type :   "+ type +"是 ----- 通配符");
        }
        else if (type instanceof Class){
            System.out.println("type :  "+type+"是 ----- 类");
        }

        else{
            System.out.println("type : "+ type +"不是Type 类型");
            throw new RuntimeException();
        }



    }


    public static void main(String[] args) throws Exception{
        Class<?> clazz = GenericClass.class;
        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.println("一 、 【成员变量类型】的范型参数");
        Field field = clazz.getField("genericField");
        Type genericType = field.getGenericType();
        instanceActualTypeArguments(genericType);
        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.println("二、 【成员方法返回值】的范型参数");
        //    List<? extends Integer> list , List<String> list2 , String  str , B[] tArr)
        Method genericMethod = clazz.getMethod("genericMethod", new Class<?>[]{List.class, List.class, String.class, Object[].class});
        Type genericReturnType = genericMethod.getGenericReturnType();
        instanceActualTypeArguments(genericReturnType);
        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.println("三、 【成员方法参数】的范型参数");
        Type[] genericParameterTypes = genericMethod.getGenericParameterTypes();
        for(Type type : genericParameterTypes){
            instanceActualTypeArguments(type);
        }

        System.out.println("----------------------------------------------------------------------------------------------");


        System.out.println("四 、 构造方法的参数类型的范型参数");
        Constructor<?> constructor = clazz.getConstructor(Object.class);
        Type[] parameterTypes = constructor.getGenericParameterTypes();
        for(Type type : parameterTypes ){
            instanceActualTypeArguments(type);
        }

        System.out.println("----------------------------------------------////------------------------------------------------");

    }

}
