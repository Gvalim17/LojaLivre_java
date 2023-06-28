package lojaLivre.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe criada para para importar e exportar as informações armazenadas no banco de dados.
 * @author Patricia Massena
 * @see lojaLivre.modelo.FuncionarioNormal
 * @see	lojaLivre.modelo.Conexao
 */
public class Funcionario {
	
	private int codFuncionario;
	private int cpf;
	private String nome;
	private double salario;
	private String cargo;


	public int getCodFuncionario() {
		return codFuncionario;
	}
	public void setCodFuncionario(int codFuncionario) {
		this.codFuncionario = codFuncionario;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Metodo criado para cadastrar as informações base para o banco de dados, que o usuário fornecerá através da janelaFuncionario
	 * @param numFuncionario		Número no sistema do funcionario.
	 * @param numCpf				Número do CPF do funcionario.
	 * @param nome					Nome do funcionario.
	 * @param salario				Valor do salario que o funcionario recebe.
	 * @param cargo					Descrição da função que o funcionario realizará na empresa.
	 * @return
	 */
	
	public boolean cadastrarFuncionario(int numFuncionario, int numCpf, String nome, double salario, String cargo) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaFuncionario();
			String sql = "insert into funcionario set funcionario_codigo=?, funcionario_cpf=?, funcionario_nome=?, funcionario_salario=?, funcionario_cargo=?;";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numFuncionario); 
			ps.setInt(2, numCpf); 
			ps.setString(3, nome); 
			ps.setDouble(4, salario);
			ps.setString(5,  cargo);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o funcionario: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Metodo criado para realizar uma consulta atravês do banco de dados pelo parametro do codigo do funcionario, apartir disso ele
	 * retornará as informações da tabela caso tenha dados, se não tiver, não retornará nada.
	 * @param numFuncionario		Número no sistema do funcionario.
	 * @return
	 */
	public boolean consultarFuncionario(int numFuncionario) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaFuncionario();
			String sql = "select * from funcionario where funcionario_codigo=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numFuncionario); 
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				System.out.println("Funcionario não cadastrado!");
				return false; 
			} else {
				
				while (rs.next()) {
					this.codFuncionario = rs.getInt("funcionario_codigo");
					this.cpf = rs.getInt("funcionario_cpf");
					this.nome = rs.getString("funcionario_nome");
					this.salario = rs.getDouble("funcionario_salario");
					this.cargo = rs.getString("funcionario_cargo");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o funcionario: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	/**
	 * Metodo feito para atualizar o banco de dados utilizando informações dados pelo usuário,  primeiro é feito uma checagem
	 * para saber se existe dados no sistema, se sim, é feita a atualização.
	 * @param numFuncionario		Número no sistema do funcionario.
	 * @param numCpf				Número do CPF do funcionario.
	 * @param nome					Nome do funcionario.
	 * @param salario				Valor do salario que o funcionario recebe.
	 * @param cargo					Descrição da função que o funcionario realizará na empresa.
	 * @return
	 */
	public boolean atualizarFuncionario(int numFuncionario, int numCpf, String nome, double salario, String cargo) {
		if (!consultarFuncionario(numFuncionario))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaFuncionario();
				String sql = "update funcionario set funcionario_nome=?, funcionario_salario=?, funcionario_cargo=? where funcionario_codigo=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, nome);
				ps.setDouble(2, salario);
				ps.setString(3, cargo);
				ps.setInt(4, numFuncionario);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o funcionario: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	/**
	 * Metodo feito para deletar o funcionario do banco de dados, é necessario usar o numero do funcionario para localizar o 
	 * funcionario a ser retirado do sistema.
	 * @param numFuncionario		Número no sistema do funcionario.
	 * @return
	 */
	public boolean deletaFuncionario(int numFuncionario) {
	    if (!consultarFuncionario(numFuncionario))
	        return false;
	    else {
	        Connection conexao = null;
	        try {
	            conexao = Conexao.conectaFuncionario();
	            String sql = "delete from funcionario where funcionario_codigo=?";
	            PreparedStatement ps = conexao.prepareStatement(sql);
	            ps.setInt(1, numFuncionario);
	            int totalRegistrosAfetados = ps.executeUpdate();
	            if (totalRegistrosAfetados == 0)
	                System.out.println("Não foi possível excluir o funcionario!");
	            else
	                System.out.println("Funcionario excluído com sucesso!");
	            return true;
	        } catch (SQLException erro) {
	            System.out.println("Erro ao excluir o funcionario: " + erro.toString());
	            return false;
	        } finally {
	            Conexao.fechaConexao(conexao);
	        }
	    }
	}
	
}
