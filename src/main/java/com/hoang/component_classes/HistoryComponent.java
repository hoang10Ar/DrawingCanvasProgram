package com.hoang.component_classes;

import com.hoang.change_on_canvas.ChangeByCommand;
import com.hoang.util_interfaces.NonDrawableOnCanvas;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class HistoryComponent implements NonDrawableOnCanvas {
    private static List<ChangeByCommand> commandsList = new ArrayList<>();

    public static List<ChangeByCommand> getHistoryList() {
        return commandsList;
    }

    public static void addHistory(ChangeByCommand change) {
        commandsList.add(change);
    }

    public static ChangeByCommand getAndRemoveLastHistory() {
        if(commandsList.size() > 0) {
            return commandsList.remove(commandsList.size() - 1);
        }
        return null;
    }

    public static ChangeByCommand getLastHistory() {
        if(commandsList.size() > 0) {
            return commandsList.get(commandsList.size() - 1);
        }
        return null;
    }

    public static void showHistory() {
        System.out.println("----- History: ----------");
        for(int i = 0; i < commandsList.size(); i++) {
            System.out.println();
            System.out.println("+++++");
            System.out.println("Id: " + commandsList.get(i).getId());
            System.out.println("Command: " + commandsList.get(i).getCommand());
            System.out.println("Date: " + commandsList.get(i).getDate());
            System.out.println("+++++");
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    public static boolean isContainHistoryHaveId(String aFirstPartOfId) {
        for(int i = commandsList.size() - 1; i >= 0; i--) {
            if(Pattern.matches("^" + aFirstPartOfId + ".*", commandsList.get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void performFunction() {
        showHistory();
    }
}
