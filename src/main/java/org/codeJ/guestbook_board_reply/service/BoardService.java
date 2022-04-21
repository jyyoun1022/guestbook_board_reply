package org.codeJ.guestbook_board_reply.service;

import org.codeJ.guestbook_board_reply.dto.BoardDTO;
import org.codeJ.guestbook_board_reply.dto.PageRequestDTO;
import org.codeJ.guestbook_board_reply.dto.PageResultDTO;
import org.codeJ.guestbook_board_reply.entity.Board;
import org.codeJ.guestbook_board_reply.entity.Member;

import java.util.Optional;

public interface BoardService {

    Long register(BoardDTO dto);

    PageResultDTO<BoardDTO,Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void removeWithReplies(Long bno);

    void modify(BoardDTO dto);

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
                .replyCount(Long.valueOf(Optional.ofNullable(replyCount).orElse(0L)).intValue())  //Long 타입은 바로 intValue()로 형변환이 가능하지만 long 타입은 Long.valueOf().intValue()해야함.
                .build();
                                            //Long 타입의 경우 null이 들어갈 수 있습니다. null을 intValue()를 이용하여 cast할 경우 NPE를 발생시키기 때문에 예외처리가 필요!
                                            //of(x)는 x가 null이 아님이 확실할 때만 사용해야 하며,x가 null이면 NPE발생, ofNullable(x0는 x가 null일 수도 있을 때만 사용해야 한다.
                                            //결론, x가 null이 아님이 확실하면 of()를 사용하고 확실하지 않다면 ofNullable()을 사용한다!
        return dto;
    }


}
