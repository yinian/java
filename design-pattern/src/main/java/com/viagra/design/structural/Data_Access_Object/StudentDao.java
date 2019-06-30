package com.viagra.design.structural.Data_Access_Object;

import java.util.List;

/**
 * @Auther: viagra
 * @Date: 2019/6/30 20:18
 * @Description: 创建数据访问对象接口
 */
public interface StudentDao {

    public List<Student> getAllStudents();
    public Student getStudent(int rollNo);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
}
