package net.firiz.atelierconstruction.constants;

public enum Message implements CharSequence {
    REDSTONE_REGULATION_MESSAGE("近くに%d個以上のレッドストーン関係のブロックが配置されています。");

    private final String string;

    Message(String string) {
        this.string = string;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int index) {
        return string.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return string.subSequence(start, end);
    }

    @Override
    public String toString() {
        return string;
    }
}
