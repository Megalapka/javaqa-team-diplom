package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {


    Player player1 = new Player("Olya");
    Player player2 = new Player("kolya");
    Player player3 = new Player("sveta");
    Player player4 = new Player("katya");

    GameStore store = new GameStore();

    //должен добавлять игру и проверять наличие в каталоге
    @Test
    public void shouldAddGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    //должен проверять наличие игры, которой нет в каталоге
    @Test
    public void shouldAddGameAndCheckAvailability() {

        Game game1 = new Game("Тетрис", "шутер", store);

        assertFalse(store.containsGame(game1));
    }

    //должен добавлять несколько игр и проверять наличие одной из них в каталоге
    @Test
    public void shouldAddSeveralGame() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game1 = store.publishGame("Нетология Баттл", "Шутер");
        Game game2 = store.publishGame("Нетология Онлайн", "Аркады");
        Game game3 = store.publishGame("Баттл", "Стратегия");

        assertTrue(store.containsGame(game1));
    }

    //должен регистрировать время, когда игрок не играл в игру
    @Test
    public void shouldRegisterTimeWhenNotPlayer() {

        store.addPlayTime("olya", 3);

        assertEquals(3, store.getPlayedTime("olya"));
    }

    //должен регистрировать время, когда игрок уже играл в игру один раз
    @Test
    public void shouldRegisterTimeWhenPlayerPlayGame() {

        store.addPlayTime("olya", 5);
        store.addPlayTime("olya", 3);

        assertEquals(8, store.getPlayedTime("olya"));
    }

    //должен регистрировать время, когда игрок уже играл в игру несколько раз
    @Test
    public void shouldRegisterTimeWhenPlayerPlayGameSeveralOnce() {

        store.addPlayTime("olya", 5);
        store.addPlayTime("olya", 3);
        store.addPlayTime("olya", 5);
        store.addPlayTime("olya", 1);
        store.addPlayTime("olya", 5);

        assertEquals(19, store.getPlayedTime("olya"));
    }

    //должен искать имя игрока, который играл в игры этого каталога дольше всего
    @Test
    public void shouldFindPlayer() {

        store.addPlayTime("olya", 5);
        store.addPlayTime("kolya", 5);
        store.addPlayTime("sveta", 1);
        store.addPlayTime("katya", 10);

        assertEquals("katya", store.getMostPlayer());
    }

    //должен выводить null, когда пустой каталог
    @Test
    public void shouldFindPlayerWhenMapNull() {

        assertEquals(null, store.getMostPlayer());
    }

    //должен искать имя игрока, который играл в игры этого каталога больше всего,
    // когда имеется отрицательное значение
    @Test
    public void shouldFindPlayerWhenNegativeTime() {

        store.addPlayTime("olya", 5);
        store.addPlayTime("kolya", 5);
        store.addPlayTime("sveta", -1);
        store.addPlayTime("katya", 10);

        assertEquals("katya", store.getMostPlayer());
    }

    //если игроков с лучшим временем несколько, то выводится один из них
    @Test
    public void shouldFindPlayerWhenEqualsTime() {

        store.addPlayTime("olya", 5);
        store.addPlayTime("kolya", 5);
        store.addPlayTime("sveta", 0);
        store.addPlayTime("katya", 1);

        String actual = store.getMostPlayer();

        assertTrue(actual.equals("olya") || actual.equals("kolya"));
    }

    //должен искать имя игрока, когда наибольшее время 1
    @Test
    public void shouldFindPlayerWhenTime1() {

        store.addPlayTime("olya", 0);
        store.addPlayTime("kolya", 0);
        store.addPlayTime("sveta", 0);
        store.addPlayTime("katya", 1);

        assertEquals("katya", store.getMostPlayer());
    }

    //    должен выводить null, когда игроки только установили игру, но не поиграли
    @Test
    public void shouldsFindPlayerWhenTime1() {

        store.addPlayTime("olya", 0);
        store.addPlayTime("kolya", 0);
        store.addPlayTime("sveta", 0);


        assertEquals(null, store.getMostPlayer());
    }

    //должен суммировать общее количество времени всех игроков
    @Test
    public void shouldSumTime() {

        store.addPlayTime("olya", 5);
        store.addPlayTime("kolya", 5);
        store.addPlayTime("sveta", 1);
        store.addPlayTime("katya", 3);

        assertEquals(14, store.getSumPlayedTime());
    }

    //    должен показывать общее время, когда один игрок
    @Test
    public void shouldSumTimeWhen1Player() {

        store.addPlayTime("olya", 5);

        assertEquals(5, store.getSumPlayedTime());
    }

    //    должен показывать сумму равную 0, когда нет игроков
    @Test
    public void shouldSumTimeWhenNotPlayer() {

        assertEquals(0, store.getSumPlayedTime());
    }
}

