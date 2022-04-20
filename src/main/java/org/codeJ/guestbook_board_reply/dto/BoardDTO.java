package org.codeJ.guestbook_board_reply.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Long bno;

    private String title;

    private String content;

    private String memberEmail;

    private String memberName;

    private LocalDateTime regDate,modDate;

    private int replyCount;
}
