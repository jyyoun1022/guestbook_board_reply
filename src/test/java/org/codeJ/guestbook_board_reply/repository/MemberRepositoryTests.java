package org.codeJ.guestbook_board_reply.repository;

import org.codeJ.guestbook_board_reply.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository repository;

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i ->{
            Member member = Member.builder()
                    .email("USER" + i + "@gmail.com")
                    .password("1111")
                    .name("USER" + i)
                    .build();

            repository.save(member);
        });
    }
}
