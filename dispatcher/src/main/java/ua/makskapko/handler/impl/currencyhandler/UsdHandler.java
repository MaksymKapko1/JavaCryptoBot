package ua.makskapko.handler.impl.currencyhandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.model.UserRequest;
import ua.makskapko.handler.impl.cryptohandler.parse.impl.ParseUsd;
import ua.makskapko.service.TelegramService;

import static ua.makskapko.constant.Constants.USD;

@Component
public class UsdHandler  extends UserRequestHandler {
    private final TelegramService telegramService;
    ParseUsd parseUsd = new ParseUsd();

    public UsdHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate(), USD);
    }

    @Override
    public void handle(UserRequest dispatchRequest, Update update) {
        telegramService.sendMessage(dispatchRequest.getChatId(), "<b>Official USD rate to HRN is</b> " + parseUsd.parse());
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
