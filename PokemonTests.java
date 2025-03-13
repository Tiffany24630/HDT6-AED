import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonTests{
    private GestorCSV gestor;
    private HashMapImp pokedex;

    @BeforeEach
    void setUp(){
        gestor = new GestorCSV();
        pokedex = new HashMapImp();
        pokedex.addPokemon(new Pokemon("Pikachu", 25, "Electric", "", "Mouse Pokémon", 0.4, 6.0, "Static", 1, false));
    }

    //Método BoolPok de GestorCSV
    @Test
    void testBoolPok_Existe(){
        assertTrue(gestor.boolPok("Pikachu"), "Pikachu debería existir en el CSV");
    }

    @Test
    void testBoolPok_NoExiste(){
        assertFalse(gestor.boolPok("FakePokemon"), "FakePokemon no debería existir en el CSV");
    }

    //Método ExistePokemon de HashMapImp
    @Test
    void testExistePokemon_PorNumero(){
        assertTrue(pokedex.existePokemon("25"), "Pikachu debería existir por número");
    }

    @Test
    void testExistePokemon_PorNombre(){
        assertTrue(pokedex.existePokemon("Pikachu"), "Pikachu debería existir por nombre");
    }

    @Test
    void testExistePokemon_NoExiste(){
        assertFalse(pokedex.existePokemon("9999"), "Número 9999 no debería existir");
    }
}
