package lojaLivre.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Classe criada para para importar e exportar as informações armazenadas no banco de dados.
 * @author Guilherme Valim
 * @see lojaLivre.modelo.EstoqueNormal
 * @see	lojaLivre.modelo.Conexao
 */
public class Estoque {

	private int codLote;
	private int codProduto;
	private String nome;
	private double valor;
	private int emEstoque;
	private double estoque_valor_total;

	public double getEstoque_valor_total() {
		return estoque_valor_total;
	}

	public void setEstoque_valor_total(double estoque_valor_total) {
		this.estoque_valor_total = estoque_valor_total;
	}

	public int getCodLote() {
		return codLote;
	}

	public void setCodLote(int codLote) {
		this.codLote = codLote;
	}

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getEmEstoque() {
		return emEstoque;
	}

	public void setEmEstoque(int emEstoque) {
		this.emEstoque = emEstoque;
	}

	/**
	 * Esse metodo faz a consulta do estoque, pelo lote e código do produto no banco de dados.
	 * @param codLote		Número do lote do produto.
	 * @param codProduto	Número do código do produto.
	 * @return
	 */

	public boolean consultarEstoque(int codLote, int codProduto) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaEstoque();
			String sql = "SELECT * FROM estoque WHERE estoque_lote=? AND estoque_codigo=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, codLote);
			ps.setInt(2, codProduto);
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("Estoque não cadastrado!");
				return false;
			} else {
				while (rs.next()) {
					this.codLote = rs.getInt("estoque_lote");
					this.codProduto = rs.getInt("estoque_codigo");
					this.nome = rs.getString("estoque_nome");
					this.valor = rs.getDouble("estoque_valor");
					this.emEstoque = rs.getInt("estoque_ativo");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar o estoque: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	
	/**
	 * Esse metodo cadastra todas as informações do produto no estoque, salvando no banco de dados.
	 * @param codLote		Número do lote do produto.
	 * @param codProduto	Número do código do produto.
	 * @param nome			Descrição do produto, nome e afins.
	 * @param valor			Valor em real (R$) do produto, unitário .
	 * @param emEstoque		A quantidade que aquele produto com aquele lote tem no estoque.
	 * @return
	 */
	
	public boolean cadastrarEstoque(int codLote, int codProduto, String nome, double valor, int emEstoque) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaEstoque();
			String sql = "INSERT INTO estoque SET estoque_lote=?, estoque_codigo=?, estoque_nome=?, estoque_valor=?, estoque_ativo=?";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, codLote);
			ps.setInt(2, codProduto);
			ps.setString(3, nome);
			ps.setDouble(4, valor);
			ps.setInt(5, emEstoque);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar o estoque: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	/**
	 * Esse metodo atualiza o banco de dados, porém ela realiza uma verificação se ele já existe no sistema.
	 * Caso ele exista, é feito o procedimento de atualização, o usuário preenche os campos e a informação é armazenada.
	 * Caso ele não exista, se abre um caso de cadastrarEstoque().
	 * @param codLote		Número do lote do produto.
	 * @param codProduto	Número do código do produto.
	 * @param nome			Descrição do produto, nome e afins.
	 * @param valor			Valor em real (R$) do produto, unitário .
	 * @param emEstoque		A quantidade que aquele produto com aquele lote tem no estoque.
	 * @return
	 */
	
	public boolean atualizarEstoque(int codLote, int codProduto, String nome, double valor, int emEstoque) {
		if (!consultarEstoque(codLote, codProduto))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaEstoque();
				String sql = "UPDATE estoque SET estoque_nome=?, estoque_valor=?, estoque_ativo=? WHERE estoque_lote=? AND estoque_codigo=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, nome);
				ps.setDouble(2, valor);
				ps.setInt(3, emEstoque);
				ps.setInt(4, codLote);
				ps.setInt(5, codProduto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar o estoque: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Esse metodo utiliza o lote e o codigo para achar o produto correto no estoque, feito isso ele fará a exclusão do mesmo
	 * no banco de dados.
	 * @param codLote		Número do lote do produto.
	 * @param codProduto	Número do código do produto.
	 * @return
	 */
	
	public boolean deletaEstoque(int codLote, int codProduto) {
		if (!consultarEstoque(codLote, codProduto))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaEstoque();
				String sql = "DELETE FROM estoque WHERE estoque_lote=? AND estoque_codigo=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, codLote);
				ps.setInt(2, codProduto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi possível excluir o estoque!");
				else
					System.out.println("Estoque excluído com sucesso!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao excluir o estoque: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Esse metodo pega o produto pelo lote e codigo, junto com o banco de dados, e adiciona uma quantidade x a ele.
	 * @param codLote		Número do lote do produto.
	 * @param codProduto	Número do código do produto.
	 * @param quantidade	Valor que irá ser acrescentado ao estoque.
	 * @return
	 */
	
	public boolean adicionarQuantidade(int codLote, int codProduto, int quantidade) {
		if (!consultarEstoque(codLote, codProduto))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaEstoque();
				String sql = "UPDATE estoque SET estoque_ativo = estoque_ativo + ? WHERE estoque_lote = ? AND estoque_codigo = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, quantidade);
				ps.setInt(2, codLote);
				ps.setInt(3, codProduto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi possível adicionar a quantidade ao estoque!");
				else
					System.out.println("Quantidade adicionada ao estoque!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao adicionar quantidade ao estoque: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}

	/**
	 * Essa função pega o produto pelo lote e codigo, junto com o banco de dados, e retira uma quantidade x dele.
	 * @param codLote		Número do lote do produto.
	 * @param codProduto	Número do código do produto.
	 * @param quantidade	Valor que irá ser acrescentado ao estoque.
	 * @return
	 */
	
	public boolean retirarQuantidade(int codLote, int codProduto, int quantidade) {
		if (!consultarEstoque(codLote, codProduto))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaEstoque();
				String sql = "UPDATE estoque SET estoque_ativo = estoque_ativo - ? WHERE estoque_lote = ? AND estoque_codigo = ?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setInt(1, quantidade);
				ps.setInt(2, codLote);
				ps.setInt(3, codProduto);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi possível retirar a quantidade do estoque!");
				else
					System.out.println("Quantidade retirada do estoque!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao retirar quantidade do estoque: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
	
	/**
	 * Esse metodo realiza o calculo do valor total que o produto tem no estoque, levando como parametro o lote e o codigo do produto.
	 * @param estoque_lote		Número do lote do produto.
	 * @param estoque_codigo	Número do código do produto.
	 * @return
	 */
	
	public double calcularTotalEstoque(int estoque_lote, int estoque_codigo) {
	    Connection conexao = null;
	    try {
	        conexao = Conexao.conectaEstoque();
	        String sql = "SELECT estoque_valor_total FROM estoque WHERE estoque_lote = ? AND estoque_codigo = ?";
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, estoque_lote);
	        ps.setInt(2, estoque_codigo);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            this.estoque_valor_total = rs.getDouble("estoque_valor_total");
	            return rs.getDouble("estoque_valor_total");
	        } else {
	            throw new RuntimeException("Não foi possível calcular o valor total do estoque!");
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao calcular o valor total do estoque: " + erro.toString());
	    } finally {
	        Conexao.fechaConexao(conexao);
	    }
	    return 0.0;
	}

}

