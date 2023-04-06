package com.example.newtgbot1.controller;

import com.example.newtgbot1.Bot;
import com.example.newtgbot1.data.RequestDelay;
import com.example.newtgbot1.data.User;
import com.example.newtgbot1.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private final UserRepo repo;

    public Controller(UserRepo repo) {
        this.repo = repo;
    }

    @GetMapping("/receiveLastMessage")
    public ResponseEntity<?> getLastMessage() {
        if (repo.updateLastMessage().getLastMessage() != null) {
            return new ResponseEntity<>(repo.updateLastMessage().getLastMessage(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/updateQueueDelay")
    public ResponseEntity<?> updateDelay(@RequestBody RequestDelay delay) {
        try {
            if (delay.getDelay() != null && delay.getDelay() > 0) {
                Bot.delay = delay.getDelay();
                return new ResponseEntity<>(delay, HttpStatus.OK);

            } else
                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
