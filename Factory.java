package HDT6;

public class Factory{
    public static InterfaceMap obtenerInterfaceMap(String type){
        if ("1".equalsIgnoreCase(type)){
            return new HashMapImp();
        }else if ("2".equalsIgnoreCase(type)){
            return new TreeMapImp();
        }
        else if ("3".equalsIgnoreCase(type)){
            return new LinkedHashMapImp();
        }
        throw new IllegalArgumentException("Su selección " + type + " no es válida.");
    }
}