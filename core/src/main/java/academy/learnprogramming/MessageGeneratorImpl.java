package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator{


    private final Game game;

    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void confirmation(){
        log.debug("the value of autowired game was: {}", game);
    }

    @Override
    public String getMainMessage() {
        log.info("Main message is being printed");
        return "Number is between " +
                game.getSmallest() +
                " and "+game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        log.info("Result message is being printed");
        if (game.isGameWon()) {
            return "You guessed it. Congrats! The number was: "+ game.getNumber();
        } else if (game.isGameLost()) {
            return "you lost. The number was:" + game.getNumber();
        } else if (!game.isValidNumberRange()){
            return "Invalid number range!";
        } else if (game.getRemainingGuesses() == game.getGuessCount()){
            return  "What is your first guess?";
        } else {
            String direction = "lower";
            if (game.getGuess() < game.getNumber()){
                direction = "Higher";
            }
            return direction +". You have "+ game.getRemainingGuesses() +" guesses left";
        }

    }



}
