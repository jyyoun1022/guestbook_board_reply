package org.codeJ.guestbook_board_reply.service;

import org.codeJ.guestbook_board_reply.dto.ReplyDTO;
import org.codeJ.guestbook_board_reply.entity.Board;
import org.codeJ.guestbook_board_reply.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO replyDTO){

        Board board = Board.builder().bno(replyDTO.getBno()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replier(replyDTO.getReplier())
                .board(board)
                .build();

        return reply;
    }

    default ReplyDTO entityToDto(Reply reply){

        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replier(reply.getReplier())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return dto;


    }
}
