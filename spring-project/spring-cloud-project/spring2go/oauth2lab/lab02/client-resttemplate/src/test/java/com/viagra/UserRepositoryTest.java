package com.viagra;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/*
Optional类的用法：
这是一个可以为null的容器对象。
如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 */
public class UserRepositoryTest {


    @Test
    public void testMain(){

        /* of */
        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("YanWei");
        //传入参数为null，抛出NullPointerException.
        //Optional<String> someNull = Optional.of(null);

        /*ofNullable*/
        Optional empty = Optional.ofNullable(null);
        // isPresent
        if (name.isPresent()){
            System.out.println(name.get());
        }

        //get
        try {
            System.out.println(empty.get());
        }catch (NoSuchElementException ex){
            System.err.println(ex.getMessage());
        }

        //isPresent
        name.ifPresent((value)->{
            System.out.println("The length of the value is: "+value.length());
        });
        //orElse
        System.out.println(empty.orElse("There is no value present!"));
        System.out.println(name.orElse("There is some value!"));

        //orElseGet
        System.out.println(empty.orElseGet(()->"Default Value"));
        System.out.println(name.orElseGet(String::new));

        // orElseThrow
        try{
            empty.orElseThrow(IllegalArgumentException::new);
        }catch (Throwable ex){
            System.out.println("error:" + ex.getMessage());
        }

        // map
        Optional<String> upperName = name.map((value)->value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));

        //flatMap
        upperName = name.flatMap((value)->Optional.of(value.toUpperCase()));
        System.out.println(upperName.get());
        System.out.println("----------------------------");
        //filter
        List<String> names = Arrays.asList("YanWei","YanTian");
        for (String s : names){
            Optional<String> nameLenLessThan7 =
                    Optional.of(s).filter((value)->value.length()<7);
            System.out.println(nameLenLessThan7.orElse("The name is more than 6 characters"));
        }

    }
}
