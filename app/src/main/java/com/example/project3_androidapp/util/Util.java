package com.example.project3_androidapp.util;

import java.util.List;

/*
 * Class: Util.java
 * Description: Util.java just holds useful
 * data modification in case API results aren't
 * in a useful format and need to be modified.
 * */

public class Util {
    public String StringJoin(List<String> stringList, String delimeter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stringList.size(); i++) {
            sb.append(stringList.get(i));
            if (i != stringList.size() - 1) {
                sb.append(delimeter);
            }
        }

        return sb.toString();
    }
}
