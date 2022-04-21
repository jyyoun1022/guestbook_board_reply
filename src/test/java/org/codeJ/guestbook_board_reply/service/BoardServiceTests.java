package org.codeJ.guestbook_board_reply.service;


import org.codeJ.guestbook_board_reply.dto.BoardDTO;
import org.codeJ.guestbook_board_reply.dto.PageRequestDTO;
import org.codeJ.guestbook_board_reply.dto.PageResultDTO;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for (BoardDTO dto : result.getDtoList()){
            System.out.println(dto);
        }
    }

    @Test
    public void testGet(){
        Long bno =100L;

        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO);


    }

    @Test
    public void testDelete(){
        boardService.removeWithReplies(101L);
    }

    @Test
    @DisplayName("A")
    public void testModify(){
        BoardDTO dto = BoardDTO.builder()
                .bno(102L)
                .title("제목을 변경합니다.")
                .content("내용을 변경합니다.")
                .build();
        boardService.modify(dto);
    }


}
