package org.kmsandeep.stream.pojo;

import java.util.StringJoiner;

public class Employee {
  private String name;
  private int age;
  private double salary;
  private String department;

  public Employee() {
  }

  public Employee(String name, int age, double salary, String department) {
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.department = department;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public double getSalary() {
    return salary;
  }

  public String getDepartment() {
    return department;
  }
}
