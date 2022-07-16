package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GameStoreTest {

    Player player1 = new Player("Olya");
    Player player2 = new Player("kolya");
    Player player3 = new Player("sveta");
    Player player4 = new Player("katya");

    //должен добавлять игру и проверять наличие в каталоге, когда игра уже есть в каталоге
    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    //должен добавлять игру и проверять наличие в каталоге, когда игры нет в каталоге
    @Test
    public void shouldAddGameAndCheckAvailability() {
        GameStore store = new GameStore();

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = new Game("Тетрис", "шутер", store);

        assertFalse(store.containsGame(game1));
    }

    //должен регистрировать время, когда игрок не играл в игру
    @Test
    public void shouldRegisterTimeWhenNotPlayer() {

        Map<String, Integer> playedTime = new HashMap<>();
        GameStore store = new GameStore(playedTime);

        store.addPlayTime("olya", 3);

        assertEquals(3, playedTime.get("olya"));
    }

    //должен регистрировать время, когда игрок уже играл в игру
    @Test
    public void shouldRegisterTimeWhenPlayerPlayGame() {

        Map<String, Integer> playedTime = new HashMap<>();
        GameStore store = new GameStore(playedTime);

        store.addPlayTime("olya", 5);
        store.addPlayTime("olya", 3);

        assertEquals(8, playedTime.get("olya"));
    }

    //должен искать имя игрока, который играл в игры этого каталога дольше всего
    @Test
    public void shouldFindPlayer() {

        GameStore store = new GameStore();
        store.addPlayTime("olya", 5);
        store.addPlayTime("kolya", 5);
        store.addPlayTime("sveta", 1);
        store.addPlayTime("katya", 10);

        assertEquals("katya", store.getMostPlayer());
    }

    //должен выводить null, когда пустой каталог
    @Test
    public void shouldFindPlayerWhenMapNull() {

        GameStore store = new GameStore();

        assertEquals(null, store.getMostPlayer());
    }

    //должен искать имя игрока, который играл в игры этого каталога больше всего,
    // когда имеется отрицательное значение
    @Test
    public void shouldFindPlayerWhenNegativeTime() {

        GameStore store = new GameStore();
        store.addPlayTime("olya", 5);
        store.addPlayTime("kolya", 5);
        store.addPlayTime("sveta", -1);
        store.addPlayTime("katya", 10);

        assertEquals("katya", store.getMostPlayer());
    }

    //должен искать имя игрока, когда наибольшее время 1
    @Test
    public void shouldFindPlayerWhenEqualTime() {

        GameStore store = new GameStore();
        store.addPlayTime("olya", 0);
        store.addPlayTime("kolya", 0);
        store.addPlayTime("sveta", 0);
        store.addPlayTime("katya", 1);

        assertEquals("katya", store.getMostPlayer());
    }

    //должен суммировать общее количество времени всех игроков
    @Test
    public void shouldSumTime() {

        GameStore store = new GameStore();
        store.addPlayTime("olya", 5);
        store.addPlayTime("kolya", 5);
        store.addPlayTime("sveta", 1);
        store.addPlayTime("katya", 3);

        assertEquals(14, store.getSumPlayedTime());
    }
}
