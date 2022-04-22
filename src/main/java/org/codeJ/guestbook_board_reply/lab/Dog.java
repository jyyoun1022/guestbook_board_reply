package org.codeJ.guestbook_board_reply.lab;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dog {

    private String name;
    private String species;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", species='" + species + '\'' +
                '}';
    }

    public Dog(String name) {
        this.name = name;
    }
}
