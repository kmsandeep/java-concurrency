package org.kmsandeep.stream.pojo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class StreamProblemSet3 {

  private List<Employee> getEmployees() {
    return Arrays.asList(
        new Employee("Raj", 18, 20000, "account"),
        new Employee("Rohit", 20, 40000, "finance"),
        new Employee("Anita", 25, 35000, "hr"),
        new Employee("Vikram", 30, 45000, "it"),
        new Employee("Suresh", 22, 30000, "marketing"),
        new Employee("Pooja", 28, 50000, "sales"),
        new Employee("Amit", 35, 60000, "it"),
        new Employee("Meena", 26, 32000, "account"),
        new Employee("Vivek", 40, 70000, "management"),
        new Employee("Sunita", 24, 28000, "hr"),
        new Employee("Manoj", 32, 48000, "finance"),
        new Employee("Kavita", 29, 53000, "sales"),
        new Employee("Ravi", 27, 42000, "it"),
        new Employee("Deepak", 31, 41000, "marketing"),
        new Employee("Sneha", 21, 27000, "account"),
        new Employee("Arun", 23, 38000, "finance"),
        new Employee("Geeta", 34, 46000, "hr"),
        new Employee("Rakesh", 33, 62000, "management"),
        new Employee("Neha", 19, 24000, "sales"),
        new Employee("Kiran", 36, 55000, "it"),
        new Employee("Harish", 37, 68000, "management"),
        new Employee("Jyoti", 28, 37000, "hr"),
        new Employee("Ajay", 38, 59000, "finance"),
        new Employee("Lakshmi", 22, 31000, "account"),
        new Employee("Prakash", 39, 65000, "it"),
        new Employee("Rina", 20, 26000, "marketing"),
        new Employee("Gaurav", 25, 43000, "sales"),
        new Employee("Ashok", 27, 47000, "finance"),
        new Employee("Kusum", 32, 49000, "hr"),
        new Employee("Naveen", 29, 52000, "management")
    );
  }

  private Map<String, List<String>> groupByDeptMappingNames() {
    Map<String, Long> countingMap = getEmployees().stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
            Collectors.mapping(Employee::getName, Collectors.counting())));
    System.out.println(countingMap);


    System.out.println(countingMap);

    return getEmployees().stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
            Collectors.mapping(Employee::getName, Collectors.toList())));
  }

  public static void main(String[] args) {
    StreamProblemSet3 app = new StreamProblemSet3();
    System.out.println(app.groupByDeptMappingNames());

  }

}
