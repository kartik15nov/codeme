package com.codeme.pro.interview.ge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GePatients {

  public static void main(String[] args) {
    System.out.println("Hello");

//    Patient p1 = new Patient();
//    p1.setName("Alice");
//    p1.setPriority(2);
//    p1.setArrivalTime(1);
//
//    Patient p2 = new Patient();
//    p2.setName("Bob");
//    p2.setPriority(1);
//    p2.setArrivalTime(2);
//
//    Patient p3 = new Patient();
//    p3.setName("Charlie");
//    p3.setPriority(2);
//    p3.setArrivalTime(3);

    Patient p1 = new Patient();
    p1.setName("John");
    p1.setPriority(3);
    p1.setArrivalTime(5);

    Patient p2 = new Patient();
    p2.setName("Jane");
    p2.setPriority(1);
    p2.setArrivalTime(10);

    Patient p3 = new Patient();
    p3.setName("Jim");
    p3.setPriority(3);
    p3.setArrivalTime(1);

    // [["John",3,5], ["Jane",1,10], ["Jim",3,1]]Output: ["Jane", "Jim", "John"]
    List<Patient> patients = new ArrayList<>();
    patients.add(p1);
    patients.add(p2);
    patients.add(p3);
    System.out.println(getPatients(patients));
  }

  public static List<String> getPatients(List<Patient> patients) {
    return patients.stream()
        .sorted(Comparator
            .comparingInt(Patient::getPriority)
            .thenComparingInt(Patient::getArrivalTime))
        .map(Patient::getName)
        .collect(Collectors.toList());
  }

  public static class Patient {

    String name;
    int priority;
    int arrivalTime;

    public String getName() {
      return this.name;
    }

    public int getPriority() {
      return this.priority;
    }

    public int getArrivalTime() {
      return this.arrivalTime;
    }


    public void setName(String name) {
      this.name = name;
    }

    public void setPriority(int priority) {
      this.priority = priority;
    }

    public void setArrivalTime(int arrivalTime) {
      this.arrivalTime = arrivalTime;
    }
  }
}
