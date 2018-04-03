package ru.stqa.pft.sandbox;

public class MyPointProgram {

    public static void main(String[] args) {

        hello("user");

        Point p1 = new Point(-1,-3);
        Point p2 = new Point(-5, 0);

        System.out.println("Точка р1 имеет координаты " + p1.x + " и " + p1.y);
        System.out.println("Точка р2 имеет координаты " + p2.x + " и " + p2.y);

        System.out.println("Расстояние (метод класса поинт)= " + p1.distance(p2));
        System.out.println("Расстояние (как функция в запускаемом классе)= " + distance(p1, p2));
    }


    public static double distance(Point p1, Point p2){

        return Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x))+((p1.y - p2.y)*(p1.y - p2.y)));

    }

    public static void hello ( String somebody){

        System.out.println("Hello, " + somebody + "!");

    }

}
