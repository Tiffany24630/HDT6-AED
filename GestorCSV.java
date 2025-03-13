package HDT6;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestorCSV{
    private String csvFile = "pokemon_data_pokeapi.csv";

    //Provicional
    public void mostrarCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String linea;
            System.out.println("Contenido del archivo CSV:");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean boolPok(String nomPok){
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            String linea = br.readLine();
            if (linea == null) return false; 
    
            while ((linea = br.readLine()) != null){
                String[] valores = linea.split(",");
    
                if (valores.length > 0){
                    String nombreCSV = valores[0].replace("\"", "").trim();
        
                    if (nombreCSV.equalsIgnoreCase(nomPok.trim())){
                        return true;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public int indElem(String columnaBuscada, String palabraBuscada){
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            String linea = br.readLine();
            if (linea == null) return -1;

            String[] cabecera = linea.split(",");
            int indiceColumna = -1;

            // Buscar índice de la columna
            for (int i = 0; i < cabecera.length; i++){
                if (cabecera[i].trim().equalsIgnoreCase(columnaBuscada.trim())){
                    indiceColumna = i;
                    break;
                }
            }

            if (indiceColumna == -1) return -1;

            int indiceFila = 1;
            while ((linea = br.readLine()) != null){
                String[] valores = linea.split(",");

                if (valores.length > indiceColumna && valores[indiceColumna].trim().equalsIgnoreCase(palabraBuscada.trim())){
                    return indiceFila;
                }
                indiceFila++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return -1; 
    }

    public String itemFilCol(String columnaBuscada, int indiceFilaBuscado) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            String linea = br.readLine();
            if (linea == null) return null;

            String[] cabecera = linea.split(",");
            int indiceColumna = -1;

            for (int i = 0; i < cabecera.length; i++) {
                if (cabecera[i].trim().equalsIgnoreCase(columnaBuscada.trim())){
                    indiceColumna = i;
                    break;
                }
            }

            if (indiceColumna == -1) return null; 

            int indiceFila = 1;
            while ((linea = br.readLine()) != null){
                if (indiceFila == indiceFilaBuscado){
                    String[] valores = linea.split(",");

                    if (valores.length > indiceColumna){
                        return valores[indiceColumna].trim(); 
                    } else {
                        return null; 
                    }
                }
                indiceFila++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
