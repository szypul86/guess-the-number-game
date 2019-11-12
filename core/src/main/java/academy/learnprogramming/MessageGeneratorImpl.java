package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator {

    // constatnts
    private static final String MAIN_MESSAGE = "game.main.message";
    private static final String WIN = "game.win";
    private static final String LOSE = "game.lose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIRST_GUESS = "game.first.guess";
    private static final String HIGHER = "game.higher";
    private static final String LOWER = "game.lower";
    private static final String REMAINING = "game.remaining";

    private final MessageSource messageSource;

    private final Game game;

    @Autowired
    public MessageGeneratorImpl(Game game, MessageSource messageSource) {

        this.game = game;
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void confirmation() {
        log.debug("the value of autowired game was: {}", game);
    }

    @Override
    public String getMainMessage() {
        log.info("Main message is being printed");
        return getMessage(MAIN_MESSAGE, game.getSmallest(), game.getBiggest());
        /*"Number is between " +
                game.getSmallest() +
                " and "+game.getBiggest() +
                ". Can you guess it?";*/
    }

    @Override
    public String getResultMessage() {
        log.info("Result message is being printed");
        if (game.isGameWon()) {
            return getMessage(WIN, game.getNumber());
        } else if (game.isGameLost()) {
            return getMessage(LOSE, game.getNumber());
        } else if (!game.isValidNumberRange()) {
            return getMessage(INVALID_RANGE);
        } else if (game.getRemainingGuesses() == game.getGuessCount()) {
            return getMessage(FIRST_GUESS);
        } else {
            String direction = getMessage(LOWER);
            if (game.getGuess() < game.getNumber()) {
                direction = getMessage(HIGHER);
            }
            return getMessage(REMAINING, direction ,game.getRemainingGuesses());
        }

    }

    // private methods
    private String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }


}
