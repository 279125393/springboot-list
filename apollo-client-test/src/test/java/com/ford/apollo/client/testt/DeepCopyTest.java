/*
 * The MIT License (MIT)
 * Copyright © 2020 <reach>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the “Software”), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ford.apollo.client.testt;

import com.google.common.collect.Lists;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

/**
 * @ClassName DeepCopyTest
 * @Description: TODO
 * @Author hutao
 * @Date 2020/8/6
 * @Version V1.0
 **/
public class DeepCopyTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {
        /**
         * 深度复制：ObjectOutputStream->ByteArrayOutputStream->ByteArrayInputStream->ObjectInputStream
         */
        List<Student> list = Lists.newArrayList(new Student("张三",25));
        Teacher teacher = new Teacher("李老师",list);
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(teacher);
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteOutputStream.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);
        Teacher teacher1 = (Teacher) objectInputStream.readObject();
        objectInputStream.close();
        byteInputStream.close();
        objectOutputStream.close();
        byteOutputStream.close();
        System.out.println(teacher1.toString());

    }
}
class Student implements Serializable {
    private String name;
    private int age;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Teacher implements Serializable{
    private String name;
    private List<Student> list;

    public Teacher() {
    }

    public Teacher(String name, List<Student> list) {
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}