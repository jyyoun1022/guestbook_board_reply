package org.codeJ.guestbook_board_reply.repository;

import org.codeJ.guestbook_board_reply.entity.Board;
import org.codeJ.guestbook_board_reply.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository repository;

    @Test
    public void insertReply(){

        IntStream.rangeClosed(1,300).forEach(i->{

            long bno = (long)(Math.random()*100)+1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply_" + i)
                    .replier("Replier")
                    .board(board)
                    .build();

            repository.save(reply);
        });
    }

    @Test
    public void testRead1(){

        Optional<Reply> result = repository.findById(1L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }
}
