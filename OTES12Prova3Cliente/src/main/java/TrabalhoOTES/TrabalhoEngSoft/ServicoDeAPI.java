package TrabalhoOTES.TrabalhoEngSoft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ServicoDeAPI {
	//URL da API:
    //static String webService = "https://api.mocki.io/v1/70b97785";
    static String webService = "http://localhost:";
    
    static int codigoSucesso = 200;

   //Fun��o que faz a chamada da API, retorna um objeto da classe Endereco: 
    public static String chamaAPIGet(String path) throws Exception {
    	
    	//Completa a url que ser� chamada:
        String urlParaChamada = webService + path;

        try {
        	
        	//Cria uma URL e faz a requisi��o, a qual � recebida na vari�vel "conex�o":
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            //Confere se o status code foi igual a 200, se n�o, o programa encerra.            
            if (conexao.getResponseCode() != codigoSucesso)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());

            //Recebe o body JSON da resposta na vari�vel "resposta", o qual depois � transformado em uma string.
            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = Util.converteJsonEmString(resposta);

            //Retorna o JSON:
            return jsonEmString;
            
        } catch (Exception e) {
            throw new Exception("ERRO: " + e);
        }
    }

}