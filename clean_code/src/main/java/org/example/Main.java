package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }


    public void calc(double a, double b) {
        double res = a * b * 3.14;
        System.out.println(res);
    }

    public void calculateCircleArea(double radius) {
        double area = radius * radius * Math.PI;
        System.out.println("Circle Area: " + area);
    }
}