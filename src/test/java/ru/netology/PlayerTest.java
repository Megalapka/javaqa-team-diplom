package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    GameStore store = new GameStore();
    Game game1 = store.publishGame("Game1", "Genre1");
    Game game2 = store.publishGame("Game2", "Genre2");
    Game game3 = store.publishGame("Game3", "Genre1");
    Game game4 = store.publishGame("Game4", "Genre3");
    Game game5 = store.publishGame("Game5", "Genre2");
    Game game6 = store.publishGame("Game6", "Genre1");

    Player player1 = new Player("Player1");

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    // другие ваши тесты
    @Test
    public void shouldSumGenre() {

        player1.installGame(game1);
        player1.play(game1, 1);

        player1.installGame(game2);
        player1.play(game2, 1);

        player1.installGame(game3);
        player1.play(game3, 1);

        int expected = 2;
        int actual = player1.sumGenre(game1.getGenre());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfGenreZero() {  //суммируем по жанру, в который не играли

        player1.installGame(game1);
        player1.play(game1, 1);

        player1.installGame(game2);
        player1.play(game2, 1);

        player1.installGame(game3);
        player1.play(game3, 1);

        player1.installGame(game4);

        int expected = 0;
        int actual = player1.sumGenre(game4.getGenre());

        assertEquals(expected, actual);
    }


    @Test
    public void shouldInstallGameTwiceWithoutReset() {   //повторная установка игры, игра не должна обнулиться
        player1.installGame(game1);
        player1.play(game1, 3);
        player1.installGame(game1);

        int expected = 3;
        int actual = player1.sumGenre(game1.getGenre());
        assertEquals(expected, actual);

    }

    @Test
    public void shouldReturnMostPlayedByGenre() {  //возвращает жанр, в который играли больше всего
        player1.installGame(game1);
        player1.installGame(game2);
        player1.installGame(game3);
        player1.installGame(game4);
        player1.installGame(game5);
        player1.installGame(game6);

        player1.play(game1, 1);
        player1.play(game2, 1);
        player1.play(game3, 1);
        player1.play(game4, 1);
        player1.play(game5, 1);
        player1.play(game6, 1);
        player1.play(game2, 2);
        player1.play(game5, 5);

        // game5 genre2 - 6 часов, для проверки возьмём название его жанра из другой игры

        Game expected = game5;
        Game actual = player1.mostPlayerByGenre(game2.getGenre());

        assertEquals(expected, actual);
    }
}
