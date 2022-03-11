package com.hoang.util_classes;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope("prototype")
public class CommandDefinition {
    private String idOfCommand;
    private String structureOfCommand;
    private String descriptionOfCommand;

    public CommandDefinition(@Value(" ") String id,
                             @Value(" ") String structure,
                             @Value(" ") String description) {
        this.idOfCommand = id;
        this.structureOfCommand = structure;
        this.descriptionOfCommand = description;
    }

    public void printDefinition() {
        System.out.format("%-20s", this.getStructureOfCommand());
        List<String> smallStringsList = getSmallerStrings(this.descriptionOfCommand);
        System.out.format("%-60s\n", smallStringsList.get(0));
        for(int i = 1; i < smallStringsList.size(); i++) {
            System.out.format("%20s%-60s\n", " ", smallStringsList.get(i));
        }
        System.out.println();
    }

    private static List<String> getSmallerStrings(String aLongString) {
        List<String> smallStringsList = new ArrayList<>();
        if(aLongString.length() < 60) {
            smallStringsList.add(aLongString);
        } else {
            String aSmallString = new String();
            String[] words = aLongString.split(" ");
            for(String word : words) {
                if(aSmallString.length() + word.length() < 60) {
                    aSmallString += (word + " ");
                } else {
                    smallStringsList.add(aSmallString);
                    aSmallString = new String(word + " ");
                }
            }
            if(aSmallString.length() > 0) {
                smallStringsList.add(aSmallString);
            }
        }

        return smallStringsList;
    }
}
