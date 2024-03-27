package br.com.autenticacaoMVC;

import br.com.autenticacaoMVC.controller.UsuarioController;
import br.com.autenticacaoMVC.model.Usuario;

public class Main {

	public static void main(String[] args) {

		Usuario usuario = new Usuario();
		usuario.setNome("Arthur");
		usuario.setEmail("arthur@proway.com");
		usuario.setSenha("12345");

		UsuarioController controller = new UsuarioController();
		controller.cadastrar(usuario);
	}

}
