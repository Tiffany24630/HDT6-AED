package HDT6;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapImp implements InterfaceMap{
    public Map<Integer, Pokemon> diPok = new TreeMap<>(); // Usa TreeMap para ordenar por número de Pokédex

    @Override
    public String addPokemon(Pokemon pokemon){
        diPok.put(pokemon.getPokemonNumber(), pokemon);
        return "Pokémon " + pokemon.getName() + " agregado a la Pokédex.";
    }

    @Override
    public String mostrarPokemon(){
        if (diPok.isEmpty()){
            return "La Pokédex está vacía.";
        }else{
            StringBuilder resultado = new StringBuilder();
            for (Pokemon p : diPok.values()){
                resultado.append(p.toString()).append("\n");
            }
            return resultado.toString();
        }
    }

    @Override
    public boolean existePokemon(String nombreONumero){
        try{
            int numero = Integer.parseInt(nombreONumero);
            return diPok.containsKey(numero);
        }catch (NumberFormatException e){
            for (Pokemon p : diPok.values()){
                if (p.getName().equalsIgnoreCase(nombreONumero)){
                    return true;
                }
            }
        }
        return false;
    }
}
