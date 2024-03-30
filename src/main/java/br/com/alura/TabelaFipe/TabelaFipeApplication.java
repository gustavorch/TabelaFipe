package br.com.alura.TabelaFipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.TabelaFipe.principal.Principal;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner 	{

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
		// Quando chamados nesse método(main), as duas linhas abaixo também funcionam.
		// Principal principal = new Principal();
		// principal.exibeMenu();

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}

}
