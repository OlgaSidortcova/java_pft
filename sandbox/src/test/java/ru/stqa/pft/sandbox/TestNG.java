package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG {

  private Point p1 = new Point(2,-7);
  private Point p2 = new Point(2,-7);
  private Point p3 = new Point(-1, -3);

  @Test
  public void testDistance() {

    Assert.assertEquals(p1.distance(p2), 0.0);
    Assert.assertEquals(p1.distance(p2), p2.distance(p1));

    Point p1 = new Point(-9, 4);
    Point p2 = new Point(-9, 8);

    Assert.assertEquals(p2.distance(p1), 4.0);

  }

  @Test
  public void testDistance2() {

    Point p4 = new Point(-5, 0);

    Assert.assertEquals(p4.distance(p3), 5.0);

    double s = MyPointProgram.distance(p4,p3);
    Assert.assertEquals(p3.distance(p4), s);

  }

  @Test
  public void testDistance3() {

    Point p5 = new Point(3, 1);
    Point p6 = new Point(6, 5);

    Assert.assertEquals(p5.distance(p6), 5.0);

    double s = MyPointProgram.distance(p6,p5);
    Assert.assertEquals(p5.distance(p6), s);

  }
}
