package circuitelements;

public class Signal {

    private String name;
    private int currentValue;

    public Signal(String name, int currentValue) {
        this.currentValue = currentValue;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

}
