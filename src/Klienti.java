public class Klienti {
    String name;
    int timesBought, moneySpent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimesBought() {
        return timesBought;
    }

    public void setTimesBought(int timesBought) {
        this.timesBought = timesBought;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(int moneySpent) {
        this.moneySpent = moneySpent;
    }

    @Override
    public String toString() {
        return "Emri: " + name +
                " Ka blere " + timesBought + " here" +
                ", Ka shpenzuar " + moneySpent + " para";
    }
}
