package br.com.autenticacaoMVC.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.autenticacaoMVC.DAO.GenericDAO;
import br.com.autenticacaoMVC.DAO.UsuarioDAOImpl;
import br.com.autenticacaoMVC.model.Usuario;

public class UsuarioController {

	public List<Usuario> ListarTodos() {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			List<Usuario> lista = new ArrayList<Usuario>();

			for (Object object : dao.listarTodos()) {
				lista.add((Usuario) object);
			}

			return lista;

		} catch (Exception ex) {
			System.out.println("Problemas na Controller para a listar Usuario " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	public Usuario ListarPorId(int id) {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			Usuario usuario = (Usuario) dao.listarPorId(id);
			return usuario;
		} catch (Exception e) {
			System.out.println("Problema na Controller para listar Usuario" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	public boolean cadastrar(Usuario usuario) {

		try {
			GenericDAO dao = new UsuarioDAOImpl();
			dao.cadastrar(usuario);
			return true;
		} catch (Exception ex) {
			System.out.println("Problema na Controller para cadastrar Usuario" + ex.getMessage());
			ex.printStackTrace();
			return false;
		}
	}

	public boolean alterar(Usuario usuario) {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			dao.alterar(usuario);
			return true;

		} catch (Exception e) {
			System.out.println("Problema na Controller para alterar Usuario" + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public void excluir(int id) {
		try {
			GenericDAO dao = new UsuarioDAOImpl();
			dao.excluir(id);
		} catch (Exception e) {
			System.out.println("Problema na Controller para excluir Usuario" + e.getMessage());
			e.printStackTrace();
		}
	}
}
