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
public class CryptoCommandHandler extends UserRequestHandler {
    public static List<String> crypto = List.of(BTC, ETH);
    private final TelegramService telegramService;
    private final KeyboardHelper keyboardHelper;
    private final static String command = CRYPTO;


    public CryptoCommandHandler(TelegramService telegramService, KeyboardHelper keyboardHelper) {
        this.telegramService = telegramService;
        this.keyboardHelper = keyboardHelper;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request, Update update) {
        ReplyKeyboard replyKeyboardMarkup = keyboardHelper.buildCryptoMenu(crypto);

        telegramService.sendMessage(request.getChatId(),
                "Choose a coin ©⤵",
                replyKeyboardMarkup);
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
