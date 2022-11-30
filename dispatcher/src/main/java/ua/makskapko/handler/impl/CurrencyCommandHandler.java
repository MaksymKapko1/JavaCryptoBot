package ua.makskapko.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.helper.KeyboardHelper;
import ua.makskapko.model.UserRequest;
import ua.makskapko.service.TelegramService;

import java.util.List;

import static ua.makskapko.constant.Constants.*;

@Component
public class CurrencyCommandHandler extends UserRequestHandler {
    public static List<String> currency = List.of(USD, EUR);
    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;


    public CurrencyCommandHandler(TelegramService telegramService, KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), CURRENCY);
    }

    @Override
    public void handle(UserRequest request, Update update) {
        ReplyKeyboard replyKeyboardMarkup = keyboardHelper.buildCurrencyMenu(currency);

        telegramService.sendMessage(request.getChatId(),
                "Choose an option",
                replyKeyboardMarkup);
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
