package org.codeJ.guestbook_board_reply.repository;

import org.codeJ.guestbook_board_reply.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {

    //한개의 로우(Object) 내에 Object[]로 나옴.
    @Query("select b,m from Board b left join b.member m where b.bno = :bno")
    Object getBoardWithWriter(@Param("bno")Long bno);

    @Query("select b,r from Board b left join Reply r on r.board = b where b.bno =:bno")
    List<Object[]> getBoardWithReply(@Param("bno")Long bno);

    @Query(value = "select b,m,count(r) " +
            "from Board b " +
            "left join b.member m " +
            "left join Reply r on r.board = b " +
            "group by b",
    countQuery = "select count(b) from Board b")//쿼리 전체 개수를 세줌
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
    //게시물의 번호,제목,장성시간,회원의 이름,이메일,해당 게시물의 댓글 수

    @Query("select b,m,count(r) from Board b left join b.member m " +
            "left join Reply r on r.board=b " +
            "where b.bno =:bno")
    Object getBoardByBno(@Param("bno")Long bno);

}
