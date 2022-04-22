package org.codeJ.guestbook_board_reply.lab;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DoubleColonTest {

    public String addNim(String s){
        return s +"님";
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("김갑순", "김갑돌");

        DoubleColonTest dct = new DoubleColonTest();

        names.stream().map(x ->dct.addNim(x)).forEach(System.out::println);
        names.stream().map(dct::addNim).forEach(System.out::println);

        List<Dog> dogs1 = names.stream().map(x -> new Dog(x)).collect(Collectors.toList());

        List<Dog> dogs2 = names.stream().map(Dog::new).collect(Collectors.toList());

        dogs2.forEach(x->x.setSpecies("이탈리안 그레이 하운드"));

        System.out.println(dogs1);
        System.out.println(dogs2);
    }
}
