package br.com.autenticacaoMVC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.autenticacaoMVC.model.Usuario;
import br.com.autenticacaoMVC.util.ConnectionFactory;

public class UsuarioDAOImpl implements GenericDAO {

	private Connection conn;

	public UsuarioDAOImpl() throws Exception {
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<Object> listarTodos() {

		List<Object> lista = new ArrayList<Object>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, nome, email, ativo FROM usuario";

		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));
				lista.add(usuario);
			}
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar Usuario " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception ex) {
				System.out.println("Problemas na DAO ao fechar Conexão!" + ex.getMessage());
			}
		}

		return lista;
	}

	@Override
	public Object listarPorId(int id) {
		Usuario usuario = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id, nome, email, ativo" + " FROM usuario" + " WHERE id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setAtivo(rs.getBoolean("ativo"));
			}
		} catch (SQLException ex) {
			System.out.println("Problema na DAO ao listar Usuario por id! " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception e) {
				System.out.println("Problema na DAO ao fechar Conexão! " + e.getMessage());
				e.printStackTrace();
			}
		}
		return usuario;
	}

	@Override
	public boolean cadastrar(Object object) {

		Usuario usuario = (Usuario) object;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO usuario (nome, email, senha, ativo)" 
		+ " VALUES (?,?,MD5(?),?)";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setBoolean(4, true);
			stmt.execute();
			return true;
		} catch (Exception ex) {
			System.out.println("Problemas na DAO ao cadastrar Usuario " + ex.getMessage());
			ex.printStackTrace();
			return false;
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problema na DAO ao fechar Conexão! " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean alterar(Object object) {
		Usuario usuario = (Usuario) object;
		PreparedStatement stmt = null;
		String sql = "UPDATE usuario SET" + " nome = ?," + " email = ?," + " senha = ?," + " ativo = ? "
				+ "WHERE id = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setBoolean(3, usuario.getAtivo());
			stmt.setInt(4, usuario.getId());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			System.out.println("Erros na DAO ao alterar usuario! " + ex.getMessage());
			ex.printStackTrace();
			return false;

		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problema na DAO ao fechar Conexão! " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	@Override
	public void excluir(int id) {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM usuario WHERE id = ?";

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();

		} catch (Exception ex) {
			System.out.println("Problema na DAO ao excluir usuario!" + ex.getMessage());
		} finally {
			try {
				ConnectionFactory.closeConnection(conn, stmt, null);
			} catch (Exception e) {
				System.out.println("Problema na DAO ao fechar Conexão! " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
