package com.viagra.oauth;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*
MultiValueMap：即一个键对应多个值,
    Spring的内部实现是LinkedMultiValueMap。
一键多值的使用场景是比较多的，在使用该数据结构之前，通常会自己定义
    Map<K, List<V>>可以使用该数据结构进行代替使用.

 */
public class MultiValueMapTest {

    private LinkedMultiValueMap<String,String> map;

    @Before
    public void setUp() {
        map = new LinkedMultiValueMap<String, String>();
    }

    @Test
    public void add() {

        map.add("key", "value1");
        map.add("key", "value2");
        assertEquals(1,map.size());
        List<String> expected = new ArrayList<String>(2);
        expected.add("value1");
        expected.add("value2");
        assertEquals(expected,map.get("key"));
    }


    @Test
    public void test(){

        map.set("key","value1");
        map.set("key","value2");
        assertEquals(1,map.size());
        assertEquals(Collections.singletonList("value2"),map.get("key"));
    }

}
