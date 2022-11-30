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
public class InfoCommandHandler extends UserRequestHandler {
    private static final String command = "/info";

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;

    public InfoCommandHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, MainService mainService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isCommand(request.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest dispatchRequest, Update update) {
        ReplyKeyboard replyKeyboard = keyboardHelper.buildStartMenu();
        telegramService.sendMessage(dispatchRequest.getChatId(), "Hello! It's my test project. \nWhat can you do here?" +
                "\nYou can register if you want, just type <b>/registration</b>" +
                "\nOr you can check rates and crypto rates", replyKeyboard);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
