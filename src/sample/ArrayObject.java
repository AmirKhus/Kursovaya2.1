package sample;

public class ArrayObject {
    String[] body;

    public ArrayObject(int size) {
        body = new String[size];
    }

    public ArrayObject(String[] body) {
        this.body = body;
    }
    public ArrayObject() {
    }

    public String[] getBody() {
        return body;
    }
}
