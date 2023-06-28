package lojaLivre.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe criada para para importar e exportar as informações armazenadas no banco de dados.
 * @author Matheus Eduardo
 * @see lojaLivre.modelo.ClienteNormal
 * @see	lojaLivre.modelo.Conexao
 */
public class Cliente {

	private int codCpf;
	private String nome;
	private int idade;
	private String email;
	private String telefone;
	private String endereco;
	private int cep;
	
	public int getCodCpf() {
		return codCpf;
	}
	public void setCodCpf(int codCpf) {
		this.codCpf = codCpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * Metodo feito para cadastrar o cliente no banco de dados, usando as informações dadas na janelaCliente, isso será feito
	 * caso não seja encontrado dados existentes na consulta.
	 * @param codCpf		Número do CPF do clinete.
	 * @param nome			Nome do cliente.
	 * @param idade			Idade do cliente.
	 * @param email			Email do cliente.
	 * @param telefone		Telefone do cliente.
	 * @param endereco		Endereço do cliente.
	 * @param cep			CEP do cliente.
	 * @return
	 */
	public boolean cadastrarCliente(int codCpf, String nome, int idade , String email, String telefone, String endereco, int cep ) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaCliente();
			String sql = "insert into cliente set cliente_cpf=?, cliente_nome=?, cliente_idade=?, cliente_email=?, cliente_telefone=?, cliente_endereco=?, cliente_cep=?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, codCpf); 
			ps.setString(2, nome); 
			ps.setInt(3, idade);
			ps.setString(4, email);
			ps.setString(5, telefone);
			ps.setString(6, endereco);
			ps.setInt(7, cep);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o cliente: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Metodo criado para realizar uma consulta atravês do banco de dados pelo parametro do CPF do cliente, apartir disso ele
	 * retornará as informações da tabela caso tenha dados, se não tiver, não retornará nada.
	 * @param numCpf		Número do CPF do clinete.
	 * @return
	 */
	public boolean consultarCliente(int numCpf) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaFuncionario();
			String sql = "select * from cliente where cliente_cpf=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numCpf); 
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				System.out.println("Cliente não cadastrado!");
				return false; 
			} else {
				while (rs.next()) {
					this.nome = rs.getString("cliente_nome");
					this.idade = rs.getInt("cliente_idade");
					this.email = rs.getString("cliente_email");
					this.telefone = rs.getString("cliente_telefone");
					this.endereco = rs.getString("cliente_endereco");
					this.cep = rs.getInt("cliente_cep");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o cliente: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Metodo feito para atualizar o banco de dados utilizando informações dados pelo usuário,  primeiro é feito uma checagem
	 * para saber se existe dados no sistema, se sim, é feita a atualização.
	 * @param codCpf		Número do CPF do clinete.
	 * @param nome			Nome do cliente.
	 * @param idade			Idade do cliente.
	 * @param email			Email do cliente.
	 * @param telefone		Telefone do cliente.
	 * @param endereco		Endereço do cliente.
	 * @param cep			CEP do cliente.
	 * @return
	 */
	public boolean atualizarCliente(int codCpf, String nome, int idade, String email , String telefone, String endereco , int cep ) {
		if (!consultarCliente(codCpf))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaFuncionario();
				String sql = "update cliente set cliente_nome=?, cliente_idade=?, cliente_email=?, cliente_telefone=?, cliente_endereco=?, cliente_cep=? where cliente_cpf=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, nome); 
				ps.setInt(2, idade); 
				ps.setString(3, email);
				ps.setString(4, telefone);
				ps.setString(5, endereco);
				ps.setInt(6, cep);
				ps.setInt(7, codCpf);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o cliente: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	/**
	 * Metodo feito para deletar o cliente do banco de dados, é necessario usar o numero do CPF do cliente para localizar o 
	 * cliente a ser retirado do sistema.
	 * @param codCpf		Número do CPF do cliente.
	 * @return
	 */
	public boolean deletaCliente(int codCpf) {
	    if (!consultarCliente(codCpf))
	        return false;
	    else {
	        Connection conexao = null;
	        try {
	            conexao = Conexao.conectaFuncionario();
	            String sql = "delete from cliente where cliente_cpf=?";
	            PreparedStatement ps = conexao.prepareStatement(sql);
	            ps.setInt(1, codCpf);
	            int totalRegistrosAfetados = ps.executeUpdate();
	            if (totalRegistrosAfetados == 0)
	                System.out.println("Não foi possível excluir o cliente!");
	            else
	                System.out.println("Cliente excluído com sucesso!");
	            return true;
	        } catch (SQLException erro) {
	            System.out.println("Erro ao excluir o cliente: " + erro.toString());
	            return false;
	        } finally {
	            Conexao.fechaConexao(conexao);
	        }
	    }
	}
}
