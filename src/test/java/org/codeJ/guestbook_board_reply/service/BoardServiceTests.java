package org.codeJ.guestbook_board_reply.service;


import org.codeJ.guestbook_board_reply.dto.BoardDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){
        BoardDTO dto = BoardDTO.builder()
                .title("Test Register")
                .content("Test Register")
                .memberEmail("User45@gmail.com")
                .build();
        Long bno = boardService.register(dto);  //101번 삭제할것.
    }
}
