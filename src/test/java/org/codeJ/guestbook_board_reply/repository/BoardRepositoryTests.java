package org.codeJ.guestbook_board_reply.repository;

import org.codeJ.guestbook_board_reply.entity.Board;
import org.codeJ.guestbook_board_reply.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository repository;

    @Test
    public void insertBoard(){

        IntStream.rangeClosed(1,100).forEach(i->{

            Member member = Member.builder().email("User" + i + "@gmail.com").build();

            Board board = Board.builder()
                    .title("Title_" + i)
                    .content("Content_" + i)
                    .member(member)
                    .build();

            repository.save(board);

        });
    }

    @Test
    @Transactional
    public void testRead1(){

        Optional<Board> result = repository.findById(100L);

        if(result.isPresent()){
            Board board = result.get();

            System.out.println(board);
            System.out.println("============");
            System.out.println(board.getMember());
        }
    }

    @Test
    public void testReadWithMember(){

        Object result = repository.getBoardWithWriter(100L);

        Object[] result1 = (Object[]) result;
        System.out.println("============");
        System.out.println(Arrays.toString(result1));
        //자바에서 배열 내용을 출력해보려고 배열 자체에서 toString()를 사용하면 배열의 내용이 아니라 배열의 주소값이 출력됩니다.
        //배열의 내용을 출력할때는 Arrays.toString을 사용합니다.
    }
}
