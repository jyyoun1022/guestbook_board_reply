package org.codeJ.guestbook_board_reply.repository;

import org.codeJ.guestbook_board_reply.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board,Long> {

    //한개의 로우(Object) 내에 Object[]로 나옴.
    @Query("select b,m from Board b left join b.member m where b.bno = :bno")
    Object getBoardWithWriter(@Param("bno")Long bno);


}
