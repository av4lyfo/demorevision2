package sg.edu.rp.c346.id22013834.demorevision2;

public class pokemon {
    private int id;
    private String type;
    private double power;
    public pokemon(int id, String type, double power){
        this.id = id;
        this.type = type;
        this.power = power;

    }

    public int getId() {
        return id;
    }

    public double getPower() {
        return power;
    }

    public String getType() {
        return type;
    }
}
