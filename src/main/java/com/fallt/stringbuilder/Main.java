package com.fallt.stringbuilder;

public class Main {
    public static void main(String[] args) {
        SimpleStringBuilder sb = new SimpleStringBuilder();
        sb.append("Hello");
        System.out.println(sb);
        sb.append(" ").append("World");
        System.out.println(sb);
        sb.undo();
        sb.undo();
        System.out.println(sb);
    }
}
