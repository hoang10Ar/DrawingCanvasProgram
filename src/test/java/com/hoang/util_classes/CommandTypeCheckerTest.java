package com.hoang.util_classes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandTypeCheckerTest {
    @Test
    public void testIsNullCommand() {
        assertTrue(CommandTypeChecker.isNullCommand(null));
        assertTrue(CommandTypeChecker.isNullCommand(""));
        assertTrue(CommandTypeChecker.isNullCommand(" "));
        assertTrue(CommandTypeChecker.isNullCommand("   "));
    }

    @Test
    public void testIsCanvasCommand() {
        assertTrue(CommandTypeChecker.isCanvasCommand("C 2 3"));
        assertTrue(CommandTypeChecker.isCanvasCommand(" C 1234 4567  "));
        assertFalse(CommandTypeChecker.isCanvasCommand(""));
        assertFalse(CommandTypeChecker.isCanvasCommand("C"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C h"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C 2"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C 2 h"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C -2 2"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C 0 2"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C 2 0"));
        assertFalse(CommandTypeChecker.isCanvasCommand("c 2 3"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C 2  3"));
        assertFalse(CommandTypeChecker.isCanvasCommand("C 2 3 h"));
    }

    @Test
    public void testIsLineCommand() {
        assertTrue(CommandTypeChecker.isLineCommand("L 2 3 12 3"));
        assertTrue(CommandTypeChecker.isLineCommand(" L 234 3 234 12  "));
        assertTrue(CommandTypeChecker.isLineCommand("L 0 0 6 0"));
        assertFalse(CommandTypeChecker.isLineCommand(""));
        assertFalse(CommandTypeChecker.isLineCommand("L"));
        assertFalse(CommandTypeChecker.isLineCommand("L 2"));
        assertFalse(CommandTypeChecker.isLineCommand("L 2 3"));
        assertFalse(CommandTypeChecker.isLineCommand("L 2 3 12"));
        assertFalse(CommandTypeChecker.isLineCommand("L 2 3 12 h"));
        assertFalse(CommandTypeChecker.isLineCommand("L 1 2 2 3"));
        assertFalse(CommandTypeChecker.isLineCommand("L -1 2 -1 3"));
        assertFalse(CommandTypeChecker.isLineCommand("L 2 -1 3 -1"));
        assertFalse(CommandTypeChecker.isLineCommand("l 2 3 12 3"));
        assertFalse(CommandTypeChecker.isLineCommand("L 2  3 12 3"));
        assertFalse(CommandTypeChecker.isLineCommand("L 2 3 12 3 h"));
    }

    @Test
    public void testIsRectangleCommand() {
        assertTrue(CommandTypeChecker.isRectangleCommand("R 2 3 6 7"));
        assertTrue(CommandTypeChecker.isRectangleCommand(" R 0 123 124 678  "));
        assertFalse(CommandTypeChecker.isRectangleCommand(""));
        assertFalse(CommandTypeChecker.isRectangleCommand("R"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3 6"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3 h 7"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3 2 7"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3 6 3"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3 1 7"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3 6 2"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 1 -1 2 3"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R -2 1 -1 3"));
        assertFalse(CommandTypeChecker.isRectangleCommand("r 2 3 6 7"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2  3 6 7"));
        assertFalse(CommandTypeChecker.isRectangleCommand("R 2 3 6 7 h"));
    }

    @Test
    public void testIsBucketFillCommand() {
        assertTrue(CommandTypeChecker.isBucketFillCommand("B 2 3 h"));
        assertTrue(CommandTypeChecker.isBucketFillCommand(" B 123 456 @  "));
        assertTrue(CommandTypeChecker.isBucketFillCommand("B 2 3 1"));
        assertTrue(CommandTypeChecker.isBucketFillCommand("B 0 3 B"));
        assertFalse(CommandTypeChecker.isBucketFillCommand(""));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B 2"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B 2 3"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B 2 h 3"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B 2 3 hh"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B -2 3 h"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B 2 -3 h"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("b 2 3 h"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B 2  3 h"));
        assertFalse(CommandTypeChecker.isBucketFillCommand("B 2 3 h k"));
    }

    @Test
    public void testIsQuitCommand() {
        assertTrue(CommandTypeChecker.isQuitCommand("Q"));
        assertTrue(CommandTypeChecker.isQuitCommand(" Q    "));
        assertFalse(CommandTypeChecker.isQuitCommand(""));
        assertFalse(CommandTypeChecker.isQuitCommand("Q 1"));
        assertFalse(CommandTypeChecker.isQuitCommand("Q h"));
        assertFalse(CommandTypeChecker.isQuitCommand("q"));
    }

    @Test
    public void testIsListCommand() {
        assertTrue(CommandTypeChecker.isListCommand("L"));
        assertTrue(CommandTypeChecker.isListCommand(" L    "));
        assertFalse(CommandTypeChecker.isListCommand(""));
        assertFalse(CommandTypeChecker.isListCommand("L 1"));
        assertFalse(CommandTypeChecker.isListCommand("L h"));
        assertFalse(CommandTypeChecker.isListCommand("l"));
    }

    @Test
    public void testIsHistoryCommand() {
        assertTrue(CommandTypeChecker.isHistoryCommand("H"));
        assertTrue(CommandTypeChecker.isHistoryCommand(" H    "));
        assertFalse(CommandTypeChecker.isHistoryCommand(""));
        assertFalse(CommandTypeChecker.isHistoryCommand("H 1"));
        assertFalse(CommandTypeChecker.isHistoryCommand("H h"));
        assertFalse(CommandTypeChecker.isHistoryCommand("h"));
    }

    @Test
    public void testIsUndoCommand() {
        assertTrue(CommandTypeChecker.isUndoCommand("U"));
        assertTrue(CommandTypeChecker.isUndoCommand(" U    "));
        assertFalse(CommandTypeChecker.isUndoCommand(""));
        assertFalse(CommandTypeChecker.isUndoCommand("U 1"));
        assertFalse(CommandTypeChecker.isUndoCommand("U h"));
        assertFalse(CommandTypeChecker.isUndoCommand("u"));
    }

    @Test
    public void testIsViewCanvasCommand() {
        assertTrue(CommandTypeChecker.isViewCanvasCommand("V"));
        assertTrue(CommandTypeChecker.isViewCanvasCommand(" V    "));
        assertFalse(CommandTypeChecker.isViewCanvasCommand(""));
        assertFalse(CommandTypeChecker.isViewCanvasCommand("V 1"));
        assertFalse(CommandTypeChecker.isViewCanvasCommand("V h"));
        assertFalse(CommandTypeChecker.isViewCanvasCommand("v"));
    }

    @Test
    public void testIsJumpCommand() {
        assertTrue(CommandTypeChecker.isJumpCommand("J 1DSA3-2xa#1"));
        assertTrue(CommandTypeChecker.isJumpCommand(" J 123   "));
        assertTrue(CommandTypeChecker.isJumpCommand("J h"));
        assertTrue(CommandTypeChecker.isJumpCommand("J J"));
        assertFalse(CommandTypeChecker.isJumpCommand(""));
        assertFalse(CommandTypeChecker.isJumpCommand("J"));
        assertFalse(CommandTypeChecker.isJumpCommand("j 1DSA3-2xa#1"));
        assertFalse(CommandTypeChecker.isJumpCommand("J  1DSA3-2xa#1"));
        assertFalse(CommandTypeChecker.isJumpCommand("J 1dsf1 dg2sm"));
    }
}
