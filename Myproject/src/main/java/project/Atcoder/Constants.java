package project.Atcoder;

public class Constants {
    public static final String LOGIN_URL = "https://atcoder.jp/login";
    public static final int LANGUAGE_ID = 4003;  // 4003 is the language id for C++
    public static String USER_NAME = "AKAHAHA";
    public static String PASSWORD = "ZJ20001215";

    public static String CONTEST_NAME;
    public static String TASK_NAME;
    public static String SOURCE_CODE_PATH;

    public static void initialize(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Insufficient arguments provided. Expected: <contest_prefix> <contest_number> <task_suffix> <source_code_path>");
        }

        CONTEST_NAME = String.format("%s%03d", args[0].toLowerCase(), Integer.parseInt(args[1]));
        TASK_NAME = String.format("%s_%s", CONTEST_NAME, args[2].toLowerCase());
        SOURCE_CODE_PATH = args[3];
    }
}
