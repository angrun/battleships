package test;

public class Report {

    private int count;
    private int averageOrderAmount;
    private int turnoverWithoutVAT;
    private int turnoverVAT;
    private int turnoverWithVAT;

    public Report(int count, int turnoverWithoutVAT) {
        this.count = count;
        this.turnoverWithoutVAT = turnoverWithoutVAT;
        this.averageOrderAmount = turnoverWithoutVAT / count;
        this.turnoverVAT = (int) (turnoverWithoutVAT * 0.2);
        this.turnoverWithVAT = turnoverVAT + turnoverWithoutVAT;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAverageOrderAmount() {
        return averageOrderAmount;
    }

    public void setAverageOrderAmount(int averageOrderAmount) {
        this.averageOrderAmount = averageOrderAmount;
    }

    public int getTurnoverWithoutVAT() {
        return turnoverWithoutVAT;
    }

    public void setTurnoverWithoutVAT(int turnoverWithoutVAT) {
        this.turnoverWithoutVAT = turnoverWithoutVAT;
    }

    public int getTurnoverVAT() {
        return turnoverVAT;
    }

    public void setTurnoverVAT(int turnoverVAT) {
        this.turnoverVAT = turnoverVAT;
    }

    public int getTurnoverWithVAT() {
        return turnoverWithVAT;
    }

    public void setTurnoverWithVAT(int turnoverWithVAT) {
        this.turnoverWithVAT = turnoverWithVAT;
    }
}
