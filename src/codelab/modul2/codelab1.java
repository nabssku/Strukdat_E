package codelab.modul2;

import java.util.ArrayList;

public class codelab1 {

    public static void main(String[] args) {


        ArrayList<String> jujutsuSorcerers = new ArrayList<>();


        jujutsuSorcerers.add("Itadori");
        jujutsuSorcerers.add("Fushiguro");
        jujutsuSorcerers.add("Kugisaki");

        System.out.println("First Years Assembled: " + jujutsuSorcerers);


        jujutsuSorcerers.add(0, "Okkotsu");

        System.out.println("After Yuta joins: " + jujutsuSorcerers);


        String student = jujutsuSorcerers.get(2);
        System.out.println("Student at index 2 is: " + student);


        jujutsuSorcerers.set(1, "Ryomen Sukuna");

        System.out.println("Oh no, Itadori switched!: " + jujutsuSorcerers);


        jujutsuSorcerers.remove(3);

        System.out.println("After the Shibuya Incident: " + jujutsuSorcerers);


        System.out.println("Remaining students: " + jujutsuSorcerers.size());


        if (jujutsuSorcerers.isEmpty()) {
            System.out.println("No sorcerers left to fight curses...");
        } else {
            System.out.println("The fight continues!");
        }


        jujutsuSorcerers.clear();

        System.out.println("Post-Culling Game Status: " + jujutsuSorcerers);
    }
}
