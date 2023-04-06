package com.example.newtgbot1;

import com.example.newtgbot1.data.User;
import com.example.newtgbot1.repo.UserRepo;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;


@Slf4j
@Service
@Data
public class Bot implements UpdatesListener {


    public static String token = MyBean.arg1Value;
    public static long delay = MyBean.arg2Value;
    private final TelegramBot bot;

    private final UserRepo repo;

    @Autowired
    public Bot(UserRepo repo, ApplicationArguments args) {
        this.repo = repo;
        bot = new TelegramBot(args.getNonOptionArgs().get(0));
        bot.setUpdatesListener(this);
    }


    @Override
    public int process(List<Update> list) {
        for (Update update : list) {
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();

                switch (messageText) {
                    case "/start":
                        updateDb(update.message());
                        break;

                    default:
                        updateDb(update.message());
                        sendMessage(update.message().chat().id(), update.message().text() +
                                " " +
                                repo.findUserByName(update.message().chat().username()).get().getCount(), delay);

                }
            }
        }
        return CONFIRMED_UPDATES_ALL;
    }

    private void updateDb(Message message) {
        String userName = message.chat().username();
        if (repo.findUserByName(userName).isEmpty()) {
            User user = new User();
            user.setId(message.chat().id());
            user.setName(message.chat().username());
            user.setLastMessage("");
            user.setCount(0);
            user.setTimeLastMessage(Time.valueOf(LocalTime.now()));
            repo.save(user);
            log.info("Added to db: " + user);
        } else {
            repo.updateTimeStampByUserName(message.chat().username());
            repo.updateLastMessageOld(message.text(), message.chat().username());
            repo.updateMsgCounterByUserName(message.chat().username());
        }
    }


    private void sendMessage(long chatId, String text, long delay) {
        SendMessage sendMessage = new SendMessage(chatId, text);
        try {
            Thread.sleep(delay);
            bot.execute(sendMessage);
        } catch (InterruptedException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

}
