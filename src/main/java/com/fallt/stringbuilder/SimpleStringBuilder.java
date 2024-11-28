package com.fallt.stringbuilder;

import java.util.ArrayDeque;
import java.util.Arrays;

public class SimpleStringBuilder {

    private byte[] value;
    private int count;
    private static final byte[] EMPTY_VALUE = new byte[0];
    private final ArrayDeque<Memento> history = new ArrayDeque<>();

    public SimpleStringBuilder() {
        value = EMPTY_VALUE;
    }

    public SimpleStringBuilder(int capacity) {
        value = new byte[capacity];
    }

    public SimpleStringBuilder(String str) {
        int length = str.length();
        int capacity = (length < Integer.MAX_VALUE - 16)
                ? length + 16 : Integer.MAX_VALUE;
        value = new byte[capacity];
        append(str);
    }

    public SimpleStringBuilder append(String str) {
        saveCurrentState();
        int length = str.length();
        byte[] src = str.getBytes();
        value = Arrays.copyOf(value, count + length);
        System.arraycopy(src, 0, value, count, length);
        count += length;
        return this;
    }

    public String toString() {
        return new String(value);
    }

    private void saveCurrentState(){
        history.push(new Memento(Arrays.copyOf(value, count), count));
    }

    public void undo(){
        if (!history.isEmpty()){
            Memento memento = history.pop();
            value = memento.value;
            count = memento.count;
        }
    }

    private static final class Memento {
        private final byte[] value;
        private final int count;

        public Memento(byte[] value, int count) {
            this.value = value;
            this.count = count;
        }
    }

}
