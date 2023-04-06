package com.example.newtgbot1.repo;

import com.example.newtgbot1.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Transactional
    @Modifying
    @Query("update tg_data u set u.count = u.count + 1 where u.id is not null and u.name=:name")
    void updateMsgCounterByUserName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update tg_data u set u.timeLastMessage=current_time where u.name=:name")
    void updateTimeStampByUserName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("update tg_data u set u.lastMessage=:message where u.name=:name")
    void updateLastMessageOld(@Param("message") String message, @Param("name") String name);

    @Transactional
    @Query(value = "SELECT * FROM tg_data ORDER BY tg_data.time_last_message DESC,time_last_message DESC  LIMIT 1", nativeQuery = true)
    User updateLastMessage();


    Optional<User> findUserByName(String name);
}