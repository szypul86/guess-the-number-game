package academy.learnprogramming.service;

import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    //fields
    private final Game game;

    private final MessageGenerator messageGenerator;

    //Lombok use
    /*@Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }*/

    @Override
    public boolean isGameOver() {
        return (game.isGameWon() || game.isGameLost());
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();

    }

    @Override
    public void reset() {
        game.reset();
    }

    @PostConstruct
    public void init() {
        log.info(getMainMessage());
        log.info("the number to guess is: {}", game.getNumber());
    }


}
