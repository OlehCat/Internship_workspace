package com.example.testapp1;

public class Test {

    public static int getInt(Object obj) {
        if (obj instanceof Integer) return (Integer) obj;
        else try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
             e.printStackTrace();
        }
        return 0;
    }
}
