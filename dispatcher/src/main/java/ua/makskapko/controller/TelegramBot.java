package ua.makskapko.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.makskapko.Dispatcher;
import ua.makskapko.model.UserRequest;

@Component
@Log4j
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;

    private final Dispatcher dispatcher;

    public TelegramBot(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    //private static final Logger log = Logger.getLogger(TelegramBot.class); lombok

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();

            String userId = update.getMessage().getFrom().getId().toString();
            String userFirstName = update.getMessage().getFrom().getFirstName();

            log.debug("User ID - " + userId + " Username - " + userFirstName + " text - " + textFromUser);

            Long chatId = update.getMessage().getChatId();

            UserRequest userRequest = UserRequest
                    .builder()
                    .update(update)
                    .chatId(chatId)
                    .build();

            boolean dispatched = dispatcher.dispatch(userRequest, update);

            if (!dispatched) {
                log.warn("Unexpected update from user");
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }
}
