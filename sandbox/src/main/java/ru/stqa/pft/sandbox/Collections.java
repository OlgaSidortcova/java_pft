package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {


  public static void main(String[] args) {

    /*String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "C#";
    langs[2] = "Python";
    langs[3] = "PHP";
*/
    String[] langs = {"Java", "C#", "Python", "PHP"};
  /*  for (int i = 0; i<=langs.length; i++){
      System.out.println("Я хочу выучить "+ langs[i]);
    }
*/
    List languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");

    List<String> languages2 = Arrays.asList("Java", "C#", "Python", "PHP");


    for (String l : langs) {

      System.out.println("Я хочу выучить " + l);
    }

    System.out.println();

    for (Object l : languages) {

      System.out.println("Я хочу выучить " + l);
    }
    System.out.println();

    for (int i = 0; i< languages2.size(); i++) {

      System.out.println("Я хочу выучить " + languages2.get(i));
    }


  }


}
