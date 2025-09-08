package org.example;
import java.util.List;
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

    void p(List<String> l) {
        for (int i=0;i<l.size();i++) {
            var s = l.get(i);
            if (s.length()>42) l.set(i, s.substring(0,42));
        }
    }

    int max(int a, int b) {
        if(a > b) {
            return a;
        } else
            return b;
    }


    int classify(int x) {
        if (x > 0) {
            if (x % 2 == 0)
                return 1;
            else
                return 2;
        } else if (x < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}