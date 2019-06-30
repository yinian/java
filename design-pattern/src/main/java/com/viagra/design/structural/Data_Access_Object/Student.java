package com.viagra.design.structural.Data_Access_Object;

/**
 * @Auther: viagra
 * @Date: 2019/6/30 20:17
 * @Description: 数值对象
 */
public class Student {

    private String name;
    private int rollNo;

    Student(String name, int rollNo){
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }


}
