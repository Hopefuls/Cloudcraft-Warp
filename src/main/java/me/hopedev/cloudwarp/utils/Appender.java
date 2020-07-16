package me.hopedev.cloudwarp.utils;

public class Appender {
    private static int startAt;
    private static String[] stringlist;
    private static StringBuilder stringBuilder = new StringBuilder();

    public Appender(int startAt, String[] stringlist) {

        this.startAt = startAt;
        this.stringlist = stringlist;

    }

    public static Appender getAppend() {
        for (int i = startAt; i < stringlist.length - 1; i++) {
        }
        stringBuilder.append(stringlist[stringlist.length].replaceAll("&", "§"));

        return new Appender(startAt, stringlist);
    }

    public static String getConvertedString() {
        return stringBuilder.toString();
    }

    public static StringBuilder getStringBuilder() {
        return stringBuilder;
    }


}

