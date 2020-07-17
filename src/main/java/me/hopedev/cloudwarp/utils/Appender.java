package me.hopedev.cloudwarp.utils;

public class Appender {
    private static int startAt;
    private static String[] stringlist;
    private static final StringBuilder stringBuilder = new StringBuilder();

    public Appender(int startAt, String[] stringlist) {

        Appender.startAt = startAt;
        Appender.stringlist = stringlist;

    }

    public static Appender getAppend() {
        for (int i = startAt; i < stringlist.length - 1; i++) {
        }
        stringBuilder.append(stringlist[stringlist.length - 1].replaceAll("&", "§"));

        return new Appender(startAt, stringlist);
    }

    public static String getConvertedString() {
        return stringBuilder.toString();
    }

    public static StringBuilder getStringBuilder() {
        return stringBuilder;
    }


}

