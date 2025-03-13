package HDT6;

/*
 * Universidad del Valle de Guatemala 
 * Autora: Tiffany Salazar Suarez 
 * Código: 24761
 * Fecha de creación: 7/03/2025
 * Fecha de finalización: 12/03/2025
 * Descripción: ésta hoja de trabajo tiene como objetivo poner en práctica conocimientos de la JCF y de los diccionarios para java (maps).
 * 
 * Cálculo de complejidad: considero que para la operación 4 la complejidad en tiempo sería O(n) para la lectura del csv e imprimir los resultados, 
 * para que sean ordenadas por tipo 1 tendría complejidad máxima de O(logn). De forma que si tomamos el peor caso posible, la complejidad para la
 * operación 4 es de O(log n).
*/

import java.io.File;
import java.util.*;

public class MainPokemon{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        GestorCSV csvGes = new GestorCSV();

        //Provicional
        csvGes.mostrarCSV();

        System.out.println("Ruta actual: " + new File(".").getAbsolutePath());

        
        System.out.println("Bienvenido a tu base de datos de Pokemon, indica el número de implementación que desea: \n1. HashMap. \n2. TreeMap. \n3. LinkedHashMap.");
        String op = sc.nextLine();
        InterfaceMap impMap = Factory.obtenerInterfaceMap(op);

        int op2 = 0;
        while (op2 != 5){
            System.out.println("Ingrese el número de opción que desea realizar:\n1. Agregar un pokemon a la colección. \n2. Mostrar datos de un pokemon. \n3. Mostrar el nombre y tipo1 de todos los pokemon existentes. \n4. Mostrar el nombre del pokemon que tiene la habilidad indicada por el usuario. \n5. Salir.");
            op2 = sc.nextInt();

            switch (op2){
                case 1:
                    System.out.println("Ha seleccionado agregar un pokemon.\nIngrese en nombre del pokemon que desee agregar:");
                    sc.nextLine();
                    String nomPok = sc.nextLine();

                    if (csvGes.boolPok(nomPok)){
                        int pokNum = csvGes.indElem("Name", nomPok);
                        String type1 = csvGes.itemFilCol("Type1", pokNum);
                        if (type1 == null){
                            type1 = "None";
                        }
                        String type2 = csvGes.itemFilCol("Type2", pokNum);
                        if (type2 == null){
                            type2 = "None";
                        }
                        String classification = csvGes.itemFilCol("Classification", pokNum);
                        String heightStr = csvGes.itemFilCol("Height(m)", pokNum);
                        if (heightStr == null || heightStr.trim().isEmpty()){
                            heightStr = "0";
                        }
                        double height = Double.parseDouble(heightStr);
                        String weightStr = csvGes.itemFilCol("Weight(kg)", pokNum);
                        if (weightStr == null || weightStr.trim().isEmpty()){
                            weightStr = "0";
                        }
                        double weight = Double.parseDouble(weightStr);
                        String abilities = csvGes.itemFilCol("Abilities", pokNum);
                        String generationStr = csvGes.itemFilCol("Generation", pokNum);
                        if (generationStr == null || generationStr.trim().isEmpty()){
                            generationStr = "1";
                        }
                        int generation = Integer.parseInt(generationStr);
                        String legendaryStr = csvGes.itemFilCol("Legendary Status", pokNum);
                        boolean isLegendary = legendaryStr != null && legendaryStr.trim().equalsIgnoreCase("true");

                        Pokemon nuevPok = new Pokemon(nomPok, pokNum, type1, type2, classification, height, weight, abilities, generation, isLegendary);
                        System.out.println(impMap.addPokemon(nuevPok));

                    }else{
                        System.out.println("El Pokémon ingresado no existe en la base de datos.");
                    }

                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Ha seleccionado mostrar datos de un pokemon.\nIngrese el nombre del Pokémon: ");
                    String nomPoke = sc.nextLine();

                    if (impMap.existePokemon(nomPoke)){
                        System.out.println(impMap.mostrarPokemon());
                    }else{
                        System.out.println("El Pokémon no está en la colección.");
                    }
                
                    break;
                case 3:
                    System.out.println("Ha seleccionado mostrar el nombre y tipo1 de todos los Pokémon existentes:");
                    String[] datosPoke = impMap.mostrarPokemon().split("\n----------------------------\n");

                    List<String[]> listaPokemon = new ArrayList<>();

                    for (String pokemonData : datosPoke) {
                        String[] lines = pokemonData.split("\n");
                        String name = "";
                        String type1 = "";

                        for (String line : lines) {
                            if (line.startsWith("Name: ")) {
                                name = line.replace("Name: ", "").trim();
                            }
                            if (line.startsWith("Type1: ")) {
                                type1 = line.replace("Type1: ", "").trim();
                            }
                        }

                        if (!name.isEmpty() && !type1.isEmpty()) {
                            listaPokemon.add(new String[]{name, type1});
                        }
                    }

                    Collections.sort(listaPokemon, Comparator.comparing(p -> p[1]));

                    for (String[] p : listaPokemon) {
                        System.out.println("Tipo: " + p[1] + " → " + p[0]);
                    }

                    break;
                case 4:
                    sc.nextLine();
                    System.out.println("Ha seleccionado mostrar el nombre del pokemon que tiene la habilidad indicada por el usuario.\nIngrese la habilidad a buscar: ");
                    String hab = sc.nextLine();
                    boolean enc = false;

                    System.out.println("Pokémon con la habilidad '" + hab + "':");
                    for (String pokemonData : impMap.mostrarPokemon().split("\n----------------------------\n")){
                        String[] lines = pokemonData.split("\n");
                        String name = "";
                        String abilities = "";

                        for (String line : lines){
                            if (line.startsWith("Name: ")){
                                name = line.replace("Name: ", "").trim();
                            }
                            if (line.startsWith("Abilities: ")){
                                abilities = line.replace("Abilities: ", "").trim();
                            }
                        }

                        if (!name.isEmpty() && abilities.toLowerCase().contains(hab.toLowerCase())){
                            System.out.println("- " + name);
                            enc = true;
                        }
                    }

                    if (!enc){
                        System.out.println("No se encontró ningún Pokémon con esa habilidad.");
                    }

                    break;
                case 5: 
                    System.out.println("Ha seleccionado salir...");
                    break;
                default:
                    System.out.println("La opción ingresada no es válida...");
                    break;
            }
        }
    sc.close();
    }
}
