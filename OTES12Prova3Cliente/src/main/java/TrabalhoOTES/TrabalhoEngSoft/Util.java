package TrabalhoOTES.TrabalhoEngSoft;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {
	
	//Faz a conversao de um Json para uma String
    public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }
}
