package org.codeJ.guestbook_board_reply.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.codeJ.guestbook_board_reply.dto.ReplyDTO;
import org.codeJ.guestbook_board_reply.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/replies/")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    //produces : 출력하고자 하는 데이터 포멧을 정의합니다.(반환하는 데이터 타입을 강제), 서버 -> 클라이언트
    //consumes : 수신 하고자 하는 데이터 포멧을 정의합니다.(들어오는 데이터 타입을 강제),클라이언트 -> 서버

    @GetMapping(value="board/{bno}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno")Long bno){

        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }


}
