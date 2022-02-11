package com.hoang.util_classes;

import com.hoang.component_classes.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    @Test
    public void testGetComponentByParsingCommand() {
        assertTrue(CommandParser.getComponentByParsingCommand(" C 2 3 ")
                instanceof CanvasComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" L 1 2 1 3 ")
                instanceof LineComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" R 1 2 2 3 ")
                instanceof RectangleComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" B 1 2 h ")
                instanceof BucketFillComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" Q ")
                instanceof QuitComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" L ")
                instanceof ListComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" H ")
                instanceof HistoryComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" U ")
                instanceof UndoComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" V ")
                instanceof ViewCanvasComponent);
        assertTrue(CommandParser.getComponentByParsingCommand(" J 1A ")
                instanceof JumpComponent);

        assertNull(CommandParser.getComponentByParsingCommand(""));
        assertNull(CommandParser.getComponentByParsingCommand("C 1"));
        assertNull(CommandParser.getComponentByParsingCommand("L 1 2 3"));
        assertNull(CommandParser.getComponentByParsingCommand("L 1 2 2 3"));
        assertNull(CommandParser.getComponentByParsingCommand("R 1 2 3"));
        assertNull(CommandParser.getComponentByParsingCommand("R 1 2 1 3"));
        assertNull(CommandParser.getComponentByParsingCommand("B 1 2"));
        assertNull(CommandParser.getComponentByParsingCommand("B h 1 2"));
        assertNull(CommandParser.getComponentByParsingCommand("Q 1"));
        assertNull(CommandParser.getComponentByParsingCommand("L a"));
        assertNull(CommandParser.getComponentByParsingCommand("H 2"));
        assertNull(CommandParser.getComponentByParsingCommand("U h"));
        assertNull(CommandParser.getComponentByParsingCommand("V 3"));
        assertNull(CommandParser.getComponentByParsingCommand("J "));
    }

    @Test
    public void testGetArgumentsInCommand() {
        assertArrayEquals(CommandParser.getArgumentsInCommand(" H"),
                new String[] { "H"});
        assertArrayEquals(CommandParser.getArgumentsInCommand("A 1 2 "),
                            new String[] { "A", "1", "2"});
        assertArrayEquals(CommandParser.getArgumentsInCommand("  bc -1.2 @ # "),
                new String[] { "bc", "-1.2", "@", "#"});
    }
}
