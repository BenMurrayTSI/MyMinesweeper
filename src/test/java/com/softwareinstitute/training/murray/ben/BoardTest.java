package com.softwareinstitute.training.murray.ben;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    Board gameBoard = new Board(2, 2, 0);

    @Test
    public void testBoard() {
        assertEquals("The player should not start dead.", false, gameBoard.isDead());
    }

}
