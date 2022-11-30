package ua.makskapko.handler.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.helper.KeyboardHelper;
import ua.makskapko.model.UserRequest;
import ua.makskapko.service.MainService;
import ua.makskapko.service.TelegramService;

@Component
public class StartCommandHandler extends UserRequestHandler {
    private static final String command = "/start";

    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final MainService mainService;


    public StartCommandHandler(TelegramService telegramService, KeyboardHelper keyboardHelper, MainService mainService) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
        this.mainService = mainService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command);
    }


    @Override
    public void handle(UserRequest request, Update update) {
        telegramService.sendMessage(request.getChatId(), "For more info type <b>/info</b>" +
                "\nFor registration type <b>/registration</b>\nOr choose an option, good luck!");
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
