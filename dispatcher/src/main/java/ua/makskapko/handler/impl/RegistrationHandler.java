package ua.makskapko.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.helper.KeyboardHelper;
import ua.makskapko.model.UserRequest;
import ua.makskapko.service.MainService;
import ua.makskapko.service.TelegramService;

@Component
public class RegistrationHandler extends UserRequestHandler {
    private static final String command = "/registration";

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final MainService mainService;

    public RegistrationHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, MainService mainService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.mainService = mainService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest dispatchRequest, Update update) {
        ReplyKeyboard replyKeyboard = keyboardHelper.buildYesNoRegister();
        telegramService.sendMessage(dispatchRequest.getChatId(), "Would you like to register?", replyKeyboard);
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
