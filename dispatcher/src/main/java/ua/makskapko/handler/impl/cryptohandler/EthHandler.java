package ua.makskapko.handler.impl.cryptohandler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ua.makskapko.handler.UserRequestHandler;
import ua.makskapko.handler.impl.cryptohandler.parse.impl.ParseEth;
import ua.makskapko.model.UserRequest;
import ua.makskapko.service.TelegramService;

@Component
public class EthHandler extends UserRequestHandler {
    ParseEth parseEth = new ParseEth();
    private final static String command = "ETH ⧫";
    private final TelegramService telegramService;

    public EthHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest dispatchRequest, Update update) {
        telegramService.sendMessage(dispatchRequest.getChatId(), "<b>ETH rate is = </b>" + parseEth.parse());
    }

    @Override
    public boolean isGlobal() {
        return false;
    }

}
