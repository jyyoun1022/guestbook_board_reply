package org.codeJ.guestbook_board_reply.service;

import org.codeJ.guestbook_board_reply.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReplyServiceTests {

    @Autowired
    private ReplyService replyService;

    @Test
    public void testGetList(){

        List<ReplyDTO> result = replyService.getList(22L);

        result.forEach(System.out::println);
    }
}
