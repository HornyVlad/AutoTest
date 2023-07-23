package logger;

import common.Config;

public class ConsoleLog {

    public static void print(String s) {
        if (Config.ALLOW_LOG) {
            System.out.println(s);
        }
    }
}
