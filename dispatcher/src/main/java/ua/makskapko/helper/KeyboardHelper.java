package ua.makskapko.helper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

import static ua.makskapko.constant.Constants.*;

@Component
public class KeyboardHelper {
    public ReplyKeyboard buildYesNoRegister() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("Yes");
        keyboardRow.add("No");

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

    public ReplyKeyboard buildOfficialMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(NOT_OFFICIAL);
        keyboardRow.add(OFFICIAL);

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

    public ReplyKeyboardMarkup buildCitiesMenu(List<String> exchangeCities) {
        List<KeyboardButton> buttons = List.of(
                new KeyboardButton("Kyiv"),
                new KeyboardButton("Lviv"),
                new KeyboardButton("Dnipro"),
                new KeyboardButton("Kharkiv"));
        KeyboardRow row1 = new KeyboardRow(buttons);

        KeyboardRow row2 = new KeyboardRow(List.of(new KeyboardButton(BTN_CANCEL)));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
    public ReplyKeyboardMarkup buildCryptoMenu(List<String> crypto) {
        List<KeyboardButton> buttons = List.of(
                new KeyboardButton(BTC),
                new KeyboardButton(ETH));
        KeyboardRow row1 = new KeyboardRow(buttons);

        KeyboardRow row2 = new KeyboardRow(List.of(new KeyboardButton(BTN_CANCEL)));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
    public ReplyKeyboardMarkup buildCurrencyMenu(List<String> currency) {
        List<KeyboardButton> buttons = List.of(
                new KeyboardButton(USD),
                new KeyboardButton(EUR));
        KeyboardRow row1 = new KeyboardRow(buttons);

        KeyboardRow row2 = new KeyboardRow(List.of(new KeyboardButton(BTN_CANCEL)));

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row1, row2))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildMainMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("❗️Потрібна допомога");

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }

    public ReplyKeyboardMarkup buildStartMenu() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(CRYPTO);
        keyboardRow.add(CURRENCY);
        keyboardRow.add(EXCHANGE);

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(true)
                .build();
    }

    public ReplyKeyboardMarkup buildMenuWithCancel() {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(BTN_CANCEL);

        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(keyboardRow))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}
