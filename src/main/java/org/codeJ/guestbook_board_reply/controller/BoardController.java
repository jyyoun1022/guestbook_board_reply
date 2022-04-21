package org.codeJ.guestbook_board_reply.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codeJ.guestbook_board_reply.dto.BoardDTO;
import org.codeJ.guestbook_board_reply.dto.PageRequestDTO;
import org.codeJ.guestbook_board_reply.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board/")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(Model model,
                     @ModelAttribute("requestDTO")PageRequestDTO requestDTO){

        log.info("Get List");

         model.addAttribute("result",boardService.getList(requestDTO));

    }

    @PostMapping("/register")
    public String register(BoardDTO boardDTO, RedirectAttributes re){

        Long bno = boardService.register(boardDTO);
        log.info(bno+" 번이 신규 등록 되었습니다.");
        re.addFlashAttribute("msg",bno);

        return "redirect:/board/list";
    }

    @GetMapping("/register")
    public void register(){
        log.info("Get Register");
    }

    @GetMapping("/read")
    public void read(Model model,
                     @RequestParam(value = "bno" ,defaultValue = "1",required = true)Long bno,
                     @ModelAttribute("requestDTO")PageRequestDTO requestDTO
                     ){

        BoardDTO boardDTO = boardService.get(bno);

        model.addAttribute("dto",boardDTO);
    }

}
