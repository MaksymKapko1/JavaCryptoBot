package ua.makskapko.handler.impl.currencyhandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.model.UserRequest;
import ua.makskapko.handler.impl.cryptohandler.parse.impl.ParseEur;
import ua.makskapko.service.TelegramService;

import static ua.makskapko.constant.Constants.EUR;

@Component
public class EurHandler extends UserRequestHandler {
    private final TelegramService telegramService;

    ParseEur parseEur = new ParseEur();

    public EurHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest request) {
        return isTextMessage(request.getUpdate(), EUR);
    }

    @Override
    public void handle(UserRequest dispatchRequest, Update update) {
        telegramService.sendMessage(dispatchRequest.getChatId(), "<b>Official EUR rate to HRN is</b> " + parseEur.parse());
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
