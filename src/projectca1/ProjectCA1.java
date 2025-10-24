package projectca1;

import java.util.Optional;

class Food {
    public String name;   
    private int weight;
    private int bbd;          // days until best-before (1..14)
    private int timeplace;    

    Food(String name, int weight, int bbd, int timeplace) {
        this.name = name;
        this.weight = weight;
        this.bbd = bbd;
        this.timeplace = timeplace;
    }

    // Static factory: returns Optional.empty() if invalid
    static Optional<Food> safeCreate(String name, int weight, int bbd, int timeplace) {
        if (name == null || name.trim().isEmpty()) return Optional.empty();  
        if (weight <= 0) return Optional.empty();
        if (bbd < 1 || bbd > 14) return Optional.empty();
        return Optional.of(new Food(name.trim(), weight, bbd, timeplace));
    }

    
    public String getname()   { return name; }
    public int getweight()    { return weight; }
    public int getbbd()       { return bbd; }
    public int gettimeplace() { return timeplace; }

 
    @Override
    public String toString() {
        return name + " (" + weight + "g, BB " + bbd + "d, t=" + timeplace + ")";
    }
}
