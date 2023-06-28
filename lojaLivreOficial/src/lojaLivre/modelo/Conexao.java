package lojaLivre.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Essa classe é encarregada de fazer as conexões entre todas as classes e janelas.
 * @author Isabela Menezes
 *
 */
public class Conexao {
	/**
	 * Essa função é responsável de fazer a conexão da classe Funcionario com o banco de dados.
	 * @return
	 */
	public static Connection conectaFuncionario() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/livre_bd"; 
			String user = "root"; 
			String password = ""; 
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver n�o encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conex�o ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro n�o identificado: " + erro.toString());
		} 
		return conexao;
		
	}
	/**
	 * Essa função é responsável de fazer a conexão da classe Cliente com o banco de dados.
	 * @return
	 */
	public static Connection conectaCliente() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/livre_bd"; 
			String user = "root"; 
			String password = ""; 
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver n�o encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conex�o ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro n�o identificado: " + erro.toString());
		} 
		return conexao;
	}
	/**
	 * Essa função é responsável de fazer a conexão da classe Estoque com o banco de dados.
	 * @return
	 */
	public static Connection conectaEstoque() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/livre_bd"; 
			String user = "root"; 
			String password = ""; 
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver n�o encontrado: " + erro);
		} catch (SQLException erro) {
			System.out.println("Erro de conex�o ao banco de dados: " + erro.toString());
		} catch (Exception erro) {
			System.out.println("Erro n�o identificado: " + erro.toString());
		} 
		return conexao;
	}
	/**
	 * Essa função é responsável pelo desligamento com o banco de dados.
	 * @param conexao
	 */
	public static void fechaConexao(Connection conexao) {
		try {
			conexao.close();
		} catch (Exception erro) {
			System.out.println("Erro ao fechar a conex�o: " + erro.toString());
		}
	}
}