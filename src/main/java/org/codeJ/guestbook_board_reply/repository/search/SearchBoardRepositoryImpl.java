package org.codeJ.guestbook_board_reply.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.codeJ.guestbook_board_reply.entity.Board;
import org.codeJ.guestbook_board_reply.entity.QBoard;
import org.codeJ.guestbook_board_reply.entity.QMember;
import org.codeJ.guestbook_board_reply.entity.QReply;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static org.codeJ.guestbook_board_reply.entity.QBoard.board;
import static org.codeJ.guestbook_board_reply.entity.QMember.member;
import static org.codeJ.guestbook_board_reply.entity.QReply.reply;


@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

    public SearchBoardRepositoryImpl(){
        super(Board.class);
    }

    @Override
    public Board search1() {

        log.info("search1.......");
        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));
        jpqlQuery.leftJoin(member).on(board.member.eq(member));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count()).groupBy(board);

        log.info("========");
        log.info(tuple);
        log.info("========");

        //fecth() : 리스트로 결과를 반환하는 방법이다. 만약에 데이터가 없으면 빈 리스트를 반환해준다.
        List<Tuple> result = tuple.fetch();
        for (Tuple tuple1 : result) {
            log.info(tuple1);
        }

        log.info(result);
        return null;
    }
}
