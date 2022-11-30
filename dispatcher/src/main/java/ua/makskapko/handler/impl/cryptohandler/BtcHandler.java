package ua.makskapko.handler.impl.cryptohandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.handler.impl.cryptohandler.parse.impl.ParseBtc;
import ua.makskapko.model.UserRequest;
import ua.makskapko.service.TelegramService;

@Component
public class BtcHandler extends UserRequestHandler {
    ParseBtc parseBtc = new ParseBtc();
    private static final String command = "BTC ₿";
    private final TelegramService telegramService;

    public BtcHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest request, Update update) {
        telegramService.sendMessage(request.getChatId(), "<b>BTC rate is = </b>"
                + parseBtc.parse());
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
