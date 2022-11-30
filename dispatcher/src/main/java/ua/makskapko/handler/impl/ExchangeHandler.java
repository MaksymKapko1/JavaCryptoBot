package ua.makskapko.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.helper.KeyboardHelper;
import ua.makskapko.model.UserRequest;
import ua.makskapko.service.TelegramService;

import java.util.List;

import static ua.makskapko.constant.Constants.*;

@Component
public class ExchangeHandler extends UserRequestHandler {
    public static List<String> exchangeCities = List.of("Kyiv", "Lviv", "Dnipro", "Kharkiv");
    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    private final static String command = EXCHANGE;

    public ExchangeHandler(TelegramService telegramService, KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request, Update update) {
        ReplyKeyboardMarkup replyKeyboardMarkup = keyboardHelper.buildCitiesMenu(exchangeCities);

        telegramService.sendMessage(request.getChatId(),
                "Choose a city ⤵",
                replyKeyboardMarkup);
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

}
