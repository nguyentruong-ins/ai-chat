package com.leon.gptclone.repository;

import com.leon.gptclone.model.ChatLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatLineRepository extends JpaRepository<ChatLine, Long> {
    public List<ChatLine> findChatLineByUser_Username(String username);

    @Transactional
    public void deleteChatLinesByUser_Username(String username);
}
