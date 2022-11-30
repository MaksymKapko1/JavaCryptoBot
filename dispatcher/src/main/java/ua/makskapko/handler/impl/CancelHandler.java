package ua.makskapko.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.helper.KeyboardHelper;
import ua.makskapko.model.UserRequest;
import ua.makskapko.service.TelegramService;

import static ua.makskapko.constant.Constants.*;

@Component
public class CancelHandler extends UserRequestHandler {
    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    public CancelHandler(TelegramService telegramService, KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), BTN_CANCEL);
    }

    @Override
    public void handle(UserRequest userRequest, Update update) {
        ReplyKeyboard replyKeyboard = keyboardHelper.buildStartMenu();
        telegramService.sendMessage(userRequest.getChatId(),
                "Choose from the menu below ⤵️", replyKeyboard);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
