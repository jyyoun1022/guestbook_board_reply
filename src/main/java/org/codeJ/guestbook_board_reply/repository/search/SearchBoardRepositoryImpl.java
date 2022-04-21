package org.codeJ.guestbook_board_reply.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.codeJ.guestbook_board_reply.entity.Board;
import org.codeJ.guestbook_board_reply.entity.QBoard;
import org.codeJ.guestbook_board_reply.entity.QMember;
import org.codeJ.guestbook_board_reply.entity.QReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.codeJ.guestbook_board_reply.entity.QBoard.board;
import static org.codeJ.guestbook_board_reply.entity.QMember.member;
import static org.codeJ.guestbook_board_reply.entity.QReply.reply;


@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{
    //또한 구현 클래스는 QuerydslRepositorySupport를 상속 받아야 하며, 부모의 생성자에 도메인 클래스를 인자로 넘겨주어야 한다.
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

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        log.info("searchPage........");

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.member.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        BooleanExpression expression = board.bno.gt(0L);
        booleanBuilder.and(expression);

        if(type != null ){
            String[] typeArr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();
            for(String s:typeArr){
                switch (s){
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                    case "c" :
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                }
            }
            booleanBuilder.and(conditionBuilder);
        }
        tuple.where(booleanBuilder);

        Sort sort = pageable.getSort();

        sort.stream().forEach(order ->{
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            String prop = order.getProperty();
            PathBuilder orderByExpression = new PathBuilder(Board.class, "board");

            tuple.orderBy(new OrderSpecifier(direction,orderByExpression.get(prop)));
        });

        tuple.groupBy(board);

        //페이징처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> result = tuple.fetch();

        for (Tuple tuple1 : result) {
            log.info(tuple1);
        }

        long count = tuple.fetchCount();
        log.info("COUNT : "+count);


        return new PageImpl<>(result.stream().map(t ->t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}
