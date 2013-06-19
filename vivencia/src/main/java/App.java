import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import vivencia.core.produto.TipoProduto;
import vivencia.persistencia.Persistence;
import vivencia.persistencia.produto.AdicionaProduto;


public class App {
	static Prevayler<Persistence> prevayler;

	public static void main(String[] args) throws Exception {
		System.out.println("Iniciando Prevayler...");

		prevayler = PrevaylerFactory
				.createPrevayler(new Persistence(), "Percistencia");
		;

		System.out.print("Digite nome da pessoa ou FIM para sair: ");
		String nome = lerTeclado();
		while (!nome.equals("FIM")) {
			try {
				prevayler.execute(new AdicionaProduto(nome, 5, new BigDecimal(1.69), TipoProduto.OUTROS));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("Pessoa armazenada.");
			System.out.print("Digite o nome da pessoa ou FIM para sair: ");
			nome = lerTeclado();
		}

		System.out.println("Imprimindo pessoas persistidas.");
		Persistence lista = prevayler.prevalentSystem();

		System.out.println(lista.toString());

	}

	public static String lerTeclado() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			return reader.readLine();
		} catch (IOException e1) {
			return "FIM";
		}
	}
}
