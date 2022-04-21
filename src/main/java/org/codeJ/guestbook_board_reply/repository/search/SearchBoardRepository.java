package org.codeJ.guestbook_board_reply.repository.search;

import org.codeJ.guestbook_board_reply.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
