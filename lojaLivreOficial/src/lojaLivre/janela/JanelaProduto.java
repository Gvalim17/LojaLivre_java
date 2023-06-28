package lojaLivre.janela;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import lojaLivre.modelo.EstoqueNormal;
/**
 * 
 * @author Guilherme Valim
 *
 */

public class JanelaProduto {
	public static JFrame criarJanelaProduto() {
		// Define a janela
		JFrame janela = new JFrame("Atualização de Produto");
		janela.setResizable(false);
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janela.setSize(500, 400); 

		Container caixa = janela.getContentPane();
		caixa.setLayout(null);

		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelCodProdutos = new JLabel("Código Produto: ");
		JLabel labelLote = new JLabel("Lote do produto: ");
		JLabel labelAdicionar = new JLabel("Adicionar: ");
		JLabel labelRemover = new JLabel("Remover: ");
		JLabel labelValorDoEstoque = new JLabel("Valor do estoque: ");
		JLabel labelQuantidade = new JLabel("Quantidade: ");

		labelNome.setBounds(50, 40, 150, 20); 
		labelLote.setBounds(50, 80, 150, 20); 
		labelCodProdutos.setBounds(50, 120, 100, 20); 
		labelAdicionar.setBounds(50, 160, 150, 20);
		labelRemover.setBounds(50, 200, 150, 20); 
		labelValorDoEstoque.setBounds(50, 240, 150, 20);
		labelQuantidade.setBounds(50, 280, 150, 20);

		JTextField jTextNome = new JTextField();
		JTextField jTextcodProdutos = new JTextField();
		JTextField jTextLote = new JTextField();
		JTextField jTextAdicionar = new JTextField();
		JTextField jTextRemover = new JTextField();
		JTextField jTextValorDoEstoque = new JTextField();
		JTextField jTextQuantidade = new JTextField();

		jTextNome.setEnabled(false);
		jTextcodProdutos.setEnabled(true);
		jTextLote.setEnabled(true);
		jTextAdicionar.setEnabled(false);
		jTextRemover.setEnabled(false);
		jTextValorDoEstoque.setEnabled(false);
		jTextQuantidade.setEnabled(false);

		jTextNome.setBounds(200, 40, 100, 20);
		jTextLote.setBounds(200, 80, 100, 20);
		jTextcodProdutos.setBounds(200, 120, 100, 20);
		jTextAdicionar.setBounds(200, 160, 100, 20);
		jTextRemover.setBounds(200, 200, 100, 20);
		jTextValorDoEstoque.setBounds(200, 240, 100, 20);
		jTextQuantidade.setBounds(200, 280, 100, 20);

		janela.add(labelNome);
		janela.add(labelCodProdutos);
		janela.add(labelLote);
		janela.add(labelAdicionar);
		janela.add(labelRemover);
		janela.add(labelValorDoEstoque);
		janela.add(labelQuantidade);
		janela.add(jTextNome);
		janela.add(jTextcodProdutos);
		janela.add(jTextLote);
		janela.add(jTextAdicionar);
		janela.add(jTextRemover);
		janela.add(jTextValorDoEstoque);
		janela.add(jTextQuantidade);

		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(350, 80, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(350, 280, 100, 20);
		janela.add(botaoLimpar);
		JButton botaoAdicionar = new JButton("Adicionar");
		botaoAdicionar.setBounds(350, 160, 100, 20);
		janela.add(botaoAdicionar);
		botaoAdicionar.setEnabled(false);
		JButton botaoRemover = new JButton("Remover");
		botaoRemover.setBounds(350, 200, 100, 20);
		janela.add(botaoRemover);
		botaoRemover.setEnabled(false);

		EstoqueNormal estoque = new EstoqueNormal();

		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int lote = Integer.parseInt(jTextLote.getText());
					int codProdutos = Integer.parseInt(jTextcodProdutos.getText());
					
					if (!estoque.consultarEstoque(lote, codProdutos)) {
	                
					} else {
						
					}
					
					jTextLote.setText(Integer.toString(lote));
					jTextcodProdutos.setText(Integer.toString(codProdutos));
					jTextNome.setText(estoque.getNome());
					jTextQuantidade.setText(Integer.toString(estoque.getEmEstoque()));
					jTextValorDoEstoque.setText(Double.toString(estoque.calcularTotalEstoque(lote, codProdutos)));
					jTextcodProdutos.setEnabled(false);
					jTextLote.setEnabled(false);
					botaoConsultar.setEnabled(false);
					botaoAdicionar.setEnabled(true);
					botaoRemover.setEnabled(true);
					jTextAdicionar.setEnabled(true);
					jTextRemover.setEnabled(true);
					jTextAdicionar.requestFocus();
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,
							"Preencha o campo codigo corretamente!!");
				}
			}
		});
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextcodProdutos.setText(""); 
				jTextNome.setText("");
				jTextLote.setText(""); 
				jTextAdicionar.setText(""); 
				jTextRemover.setText("");
				jTextValorDoEstoque.setText("");
				jTextQuantidade.setText("");
				jTextcodProdutos.setEnabled(true);
				jTextLote.setEnabled(true);
				jTextAdicionar.setEnabled(false);
				jTextRemover.setEnabled(false);
				botaoConsultar.setEnabled(true);
				jTextLote.requestFocus(); 
			}
		});
		
		botaoAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lote = Integer.parseInt(jTextLote.getText());
				int produto = Integer.parseInt(jTextcodProdutos.getText());
				int adicionar = Integer.parseInt(jTextAdicionar.getText());
				estoque.adicionarQuantidade(lote, produto, adicionar);
			}
		});
		
		botaoRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int lote = Integer.parseInt(jTextLote.getText());
				int produto = Integer.parseInt(jTextcodProdutos.getText());
				int remover = Integer.parseInt(jTextRemover.getText());
				estoque.retirarQuantidade(lote, produto, remover);
			}
		});
		
		return janela;
	}
}