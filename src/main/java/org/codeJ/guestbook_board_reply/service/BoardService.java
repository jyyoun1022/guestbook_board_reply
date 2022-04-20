package org.codeJ.guestbook_board_reply.service;

import org.codeJ.guestbook_board_reply.dto.BoardDTO;
import org.codeJ.guestbook_board_reply.entity.Board;
import org.codeJ.guestbook_board_reply.entity.Member;

public interface BoardService {

    Long register(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto){

        Member member = Member.builder().email(dto.getMemberEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(member)
                .build();

        return board;
    }
    default BoardDTO entityToDto(Board board,Member member,Long replyCount){

        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .memberEmail(member.getEmail())
                .memberName(member.getName())
                .regDate(board.getRegDate())
                .modDate(board.getRegDate())
                .replyCount(replyCount.intValue())
                .build();

        return dto;
    }


}
