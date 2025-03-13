package HDT6;

public class Pokemon{
    private String name, type1, type2, classification, abilities;
    private int pokedexNumber, generation;
    private double height, weight;
    private boolean isLegendary;
    
    public Pokemon(String name, int pokedexNumber, String type1, String type2, String classification, double height, double weight, String abilities, int generation, boolean isLegendary) {
        this.name = name;
        this.pokedexNumber = pokedexNumber;
        this.type1 = type1;
        this.type2 = type2;
        this.classification = classification;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.generation = generation;
        this.isLegendary = isLegendary;
    }
    
    public int getPokemonNumber(){
        return pokedexNumber;
    }
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return "Pokedex Number: " + pokedexNumber + "\nName: " + name + "\nType1: " + type1 + "\nType2: " + (type2 == null || type2.isEmpty() ? "None" : type2) + "\nClassification: " + classification + "\nHeight: " + height + " m" + "\nWeight: " + weight + " kg" + "\nAbilities: " + abilities + "\nGeneration: " + generation + "\nLegendary: " + (isLegendary ? "Yes" : "No") + "\n----------------------------";
    }
}
