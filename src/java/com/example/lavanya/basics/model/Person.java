package com.example.lavanya.basics.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lavanya on 07/11/17.
 */

@Entity
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int slNo;
    private String name;
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Person{" +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
