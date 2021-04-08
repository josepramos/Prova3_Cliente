package TrabalhoOTES.TrabalhoEngSoft;

import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		
		
	    Scanner sc = new Scanner(System.in);
	    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("Indique a aderencia à cada um dos indicadores a seguir com as letras: T, L, P, N ou NA");

		String gpr1 = ValidaRespostaPergunta("GPR1 - O escopo do trabalho para o projeto é estabelecido, mantido atualizado e utilizado?");
		String gpr5 = ValidaRespostaPergunta("GPR5- O orçamento e o cronograma do projeto, incluindo a definição de marcos, são estabelecidos e mantidos atualizados?");
		String gpr6 = ValidaRespostaPergunta("GPR6- Os recursos humanos para o projeto são planejados considerando as habilidades e os conhecimentos necessários para executá-lo?");
		String gpr8 = ValidaRespostaPergunta("GPR8- A estratégia de transição para operação e suporte do produto, incluindo as tarefas e o cronograma, é planejada?");
		String gpr9 = ValidaRespostaPergunta("GPR9 - O envolvimento das partes interessadas no projeto é planejado?");
		String req1 = ValidaRespostaPergunta("REQ1 - As necessidades, expectativas e restrições das partes interessadas, tanto em relação ao produto quanto a suas interfaces, são identificadas?");
		String req5 = ValidaRespostaPergunta("REQ5 - O compromisso da equipe técnica com a implementação dos requisitos é obtido?");
		String req6 = ValidaRespostaPergunta("REQ6 - A rastreabilidade bidirecional entre requisitos, atividades e produtos de trabalho do projeto é estabelecida e mantida?");
		String req7 = ValidaRespostaPergunta("REQ7 - Os planos, atividades e produtos de trabalho relacionados são revisados visando identificar e tratar inconsistência em relação aos requisitos?");
		
		//API:
		System.out.println("\nAgora iremos calcular a qualidade dos seus processos:");
		String path = "8080/indicadores/nivelg/"+gpr1+"/"+gpr5+"/"+gpr6+"/"+gpr8+"/"+gpr9+"/"+req1+"/"+req5+"/"+req6+"/"+req7;
		
		//Pega o retorno da API:
		System.out.println("Retorno da API:");
		String retornoJson = ServicoDeAPI.chamaAPIGet(path);

		//Instancia o Parser de Json para tratar o dado:
		JsonObject jsonObject = new JsonParser().parse(retornoJson).getAsJsonObject();
		
		String RetornoResultados = jsonObject.get("resultados").getAsString();
		String RetornoExecucao = jsonObject.get("execucao").getAsString();
		String RetornoPessoas = jsonObject.get("pessoas").getAsString();
		
		String RetornoResultadosLetra = jsonObject.get("resultados_string").getAsString();
		String RetornoExecucaoLetra = jsonObject.get("execucao_string").getAsString();
		String RetornoPessoasLetra = jsonObject.get("pessoas_string").getAsString();
		
		System.out.println("Capacidade I - O processo produz os resultados definidos. Nota: " +RetornoResultados+" - " + RetornoResultadosLetra);
		System.out.println("Capacidade II - A execução do processo é planejada e monitorada. Nota: " +RetornoExecucao+" - " + RetornoExecucaoLetra);
		System.out.println("Capacidade III - As pessoas estão preparadas para executar suas responsabilidades no processo. Nota: " +RetornoPessoas+" - " + RetornoPessoasLetra);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				
		System.out.println("\n ------------ \n");
		System.out.println("Agora vamos calcular o índice de maturidade do software - SMI:");
		System.out.println("SMI - Número de módulos na versão atual:");
		String smi_mt = sc.next();
		System.out.println("SMI - Número de módulos na versão atual que foram alterados:");
		String smi_fc = sc.next();
		System.out.println("SMI - Número de módulos na versão atual que foram acrescentados:");
		String smi_fa = sc.next();
		System.out.println("SMI - Número de módulos da versão anterior que foram excluídos na versão atual:");
		String smi_fd = sc.next();
		
		//API:
		System.out.println("\nAgora iremos calcular o índice de maturidade do software:");
		path = "8081/smi/"+smi_mt+"/"+smi_fc+"/"+smi_fa+"/"+smi_fd;

		//Pega o retorno da API:
		System.out.println("Retorno da API:");
		retornoJson = ServicoDeAPI.chamaAPIGet(path);
		
		jsonObject = new JsonParser().parse(retornoJson).getAsJsonObject();
		String retorno = jsonObject.get("resultado_smi").getAsString();
		
		System.out.println("O índice de maturidade de software SMI é de: "+retorno);
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("\n ------------ \n");
		System.out.println("Agora vamos calcular a integridade do software:");
		System.out.println("Integridade - Porcentagem de ameaca em decimal:");
		String integridade_ameaca = sc.next();
		System.out.println("Integridade - Porcentagem de seguranca em decimal:");
		String integridade_seguranca = sc.next();
		
		//API:
		System.out.println("\nAgora iremos calcular a integridade do seu software:");
		path = "8081/integridade/"+integridade_ameaca+"/"+integridade_seguranca;

		//Pega o retorno da API:
		System.out.println("Retorno da API:");
		retornoJson = ServicoDeAPI.chamaAPIGet(path);

		
		jsonObject = new JsonParser().parse(retornoJson).getAsJsonObject();
		retorno = jsonObject.get("resultado_integridade").getAsString();
		
		System.out.println("A integridade de software é: "+retorno);

		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("\n ------------ \n");
		System.out.println("Agora vamos calcular a eficiência na remoção de defeitos - DRE:");
		System.out.println("DRE - Número de erros encontrados antes que o software seja fornecido ao usuário:");
		String erros = sc.next();
		System.out.println("DRE - Número de defeitos depois que o software foi entregue:");
		String defeitos = sc.next();
		
		//API:
		System.out.println("\nAgora iremos calcular a eficiência na remoção de defeitos:");
		path = "8081/dre/"+erros+"/"+defeitos;
		
		//Pega o retorno da API:
		System.out.println("Retorno da API:");
		retornoJson = ServicoDeAPI.chamaAPIGet(path);
		
		
		jsonObject = new JsonParser().parse(retornoJson).getAsJsonObject();
		retorno = jsonObject.get("resultado_dre").getAsString();
		
		System.out.println("A eficiência na remoção de defeitos é: "+retorno);
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		System.out.println("\n ------------ \n");
		System.out.println("Agora vamos calcular a exposição geral ao risco - RE:");
		System.out.println("RE - Probabilidade de ocorrência de um risco:");
		String probabilidade = sc.next();
		System.out.println("RE - Custo para o projeto caso o risco ocorra:");
		String custo = sc.next();
		
		//API:
		System.out.println("\nAgora iremos calcular a exposição geral ao risco:");
		path = "8081/re/"+probabilidade+"/"+custo;
		
		//Pega o retorno da API:
		System.out.println("Retorno da API:");
		retornoJson = ServicoDeAPI.chamaAPIGet(path);
		
		
		jsonObject = new JsonParser().parse(retornoJson).getAsJsonObject();
		retorno = jsonObject.get("resultado_re").getAsString();
		
		System.out.println("A exposição geral ao risco é: "+retorno);

///////////TESTE AUTOMATIZADOS/////////////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\n ------------ \n");
		System.out.println("Aperte enter para continuar o programa e conferir o TDD");
		sc.next();
		
		int tdd_sucessos = 0;
		int tdd_erros = 0;
		
		System.out.println("TDD do DRE, calculo para conferencia da API:");
		
		System.out.println("Probabilidade inserida: 0.8");
		System.out.println("Custo inserido: 25200");
		System.out.println("Retorno esperado: 20160.0");
		
		//API:
		path = "8081/re/0.8/25200";		
		retornoJson = ServicoDeAPI.chamaAPIGet(path);
		
		
		jsonObject = new JsonParser().parse(retornoJson).getAsJsonObject();
		retorno = jsonObject.get("resultado_re").getAsString();
		
		if (retorno.equals("20160.0")) {
			tdd_sucessos = tdd_sucessos + 1;
		}else {
			tdd_erros = tdd_erros + 1;
		}
		
		System.out.println("O teste automático da API da métrica RE finalizou:");
		System.out.println("Numero de sucessos: "+tdd_sucessos);
		System.out.println("Numero de erros: "+tdd_erros);
		
	}
	
	public static String ValidaRespostaPergunta(String pergunta) {
	    
		Scanner sc = new Scanner(System.in);
		String resposta = null;
		
		do {
			System.out.println(pergunta);
			resposta = sc.next();
		}while(
			!resposta.equalsIgnoreCase("T") && 
			!resposta.equalsIgnoreCase("L") && 
			!resposta.equalsIgnoreCase("P") && 
			!resposta.equalsIgnoreCase("N") && 
			!resposta.equalsIgnoreCase("NA")
			);
		
		return resposta;
	}
}