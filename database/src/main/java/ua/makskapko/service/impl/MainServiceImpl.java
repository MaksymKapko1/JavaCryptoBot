package ua.makskapko.service.impl;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ua.makskapko.dao.AppUserDAO;
import ua.makskapko.dao.RawDataDAO;
import ua.makskapko.entity.AppUser;
import ua.makskapko.service.MainService;


import static ua.makskapko.entity.enums.UserState.BASIC_STATE;

@Service
public class MainServiceImpl implements MainService {
    private final RawDataDAO rawDataDAO;
    private final AppUserDAO appUserDAO;

    public MainServiceImpl(RawDataDAO rawDataDAO, AppUserDAO appUserDAO) {
        this.rawDataDAO = rawDataDAO;
        this.appUserDAO = appUserDAO;
    }

    @Override
    public void processTextMessage(Update update) {
        var textMessage = update.getMessage();
        var telegramUser = textMessage.getFrom();
        var appUser = saveOrFindAppUser(update);
    }

    @Override
    public void processDocMessage(Update update) {

    }

//    private void saveRawData(Update update) {
//        RawData rawData = RawData.builder()
//                .event(update)
//                .build();
//        rawDataDAO.save(rawData);
//    }

//    @Override
//    public void processDocMessage(Update update) {
//        var appUser = saveOrFindAppUser(update);
//        var chatId = update.getMessage().getChatId();
//        if (isNotAllowToSendContent )
//    }

//    private boolean isNotAllowToSendContent(Long chatId, AppUser appUser) {
//        var userState = appUser.getState();
//        if (!appUser.getIsActive()) {
//            var error = "Зарегистрируйтесь или активируйте "
//                    + "свою учетную запись для загрузки контента.";
//            //sendAnswer(error, chatId);
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(chatId);
//            sendMessage.setText(error);
//            try {
//
//            }
//            return true;
//        } else if (!BASIC_STATE.equals(userState)) {
//            var error = "Отмените текущую команду с помощью /cancel для отправки файлов.";
//            sendAnswer(error, chatId);
//            return true;
//        }
//        return false;
//    }


    private AppUser saveOrFindAppUser(Update update) {
        User telegramUser = update.getMessage().getFrom();
        AppUser persistentAppUser = appUserDAO.findAppUserByTelegramUserId(telegramUser.getId());
        if (persistentAppUser == null) {
            AppUser transientAppUser = AppUser.builder()
                    .telegramUserId(telegramUser.getId())
                    .username(telegramUser.getUserName())
                    .firstName(telegramUser.getFirstName())
                    .lastName(telegramUser.getLastName())
                    //TODO изменить значение по умолчанию после добавления регистрации
                    .isActive(true)
                    .state(BASIC_STATE)
                    .build();
            return appUserDAO.save(transientAppUser);
        }
        return persistentAppUser;
    }

}
