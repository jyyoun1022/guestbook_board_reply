package org.codeJ.guestbook_board_reply;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@SpringBootApplication
@EnableJpaAuditing
public class GuestbookBoardReplyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestbookBoardReplyApplication.class, args);
	}

//	@Bean
//	public JPAQueryFactory jpaQueryFactory(EntityManager em){
//		return new JPAQueryFactory(em);
//	}

}
