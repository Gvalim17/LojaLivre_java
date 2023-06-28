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
public class JanelaEstoque {
	public static JFrame criarJanelaEstoque() {

		JFrame janela = new JFrame("Atualização de estoque"); 
		janela.setResizable(false); 
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janela.setSize(500, 400); 

		Container caixa = janela.getContentPane();
		caixa.setLayout(null);

		JLabel labelCodLote = new JLabel("Código Lote: ");
		JLabel labelCodProduto = new JLabel("Código do Produto: ");
		JLabel labelNome = new JLabel("Nome Produto: ");
		JLabel labelValor = new JLabel("Valor: ");
		JLabel labelEmEstoque = new JLabel("Em Estoque: ");

		labelCodLote.setBounds(50, 40, 150, 20); 
		labelCodProduto.setBounds(50, 80, 150, 20); 
		labelNome.setBounds(50, 120, 150, 20); 
		labelValor.setBounds(50, 160, 150, 20);
		labelEmEstoque.setBounds(50, 200, 150, 20); 

		JTextField jTextCodLote = new JTextField();
		JTextField jTextCodProduto = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextValor = new JTextField();
		JTextField jTextEmEstoque = new JTextField();

		jTextCodLote.setEnabled(true);
		jTextCodProduto.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextValor.setEnabled(false);
		jTextEmEstoque.setEnabled(false);

		jTextCodLote.setBounds(260, 40, 150, 20);
		jTextCodProduto.setBounds(260, 80, 150, 20);
		jTextNome.setBounds(260, 120, 150, 20);
		jTextValor.setBounds(260, 160, 150, 20);
		jTextEmEstoque.setBounds(260, 200, 150, 20);

		janela.add(labelCodLote);
		janela.add(labelCodProduto);
		janela.add(labelNome);
		janela.add(labelValor);
		janela.add(labelEmEstoque);
		janela.add(jTextCodLote);
		janela.add(jTextCodProduto);
		janela.add(jTextNome);
		janela.add(jTextValor);
		janela.add(jTextEmEstoque);

		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(80, 240, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(80, 280, 100, 20);
		botaoGravar.setEnabled(false);
		janela.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(280, 240, 100, 20);
		janela.add(botaoLimpar);
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(280, 280, 100, 20);
		janela.add(botaoExcluir);
		botaoExcluir.setEnabled(false);
		EstoqueNormal estoque = new EstoqueNormal();
		botaoConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int codLote = Integer.parseInt(jTextCodLote.getText());
		            int codProduto = Integer.parseInt(jTextCodProduto.getText());
		            botaoGravar.setEnabled(true);
		            String nome;
		            double valor;
		            int emEstoque;
		            
		            
		            if (!estoque.consultarEstoque(codLote, codProduto)) {
		            	nome = "";
		                valor = 0;
		                emEstoque = 0;
		            } else {
		            	nome = estoque.getNome();
		                valor = estoque.getValor();
		                emEstoque = estoque.getEmEstoque();
		            }
		            
		            jTextCodLote.setText(Integer.toString(codLote));
		            jTextCodProduto.setText(Integer.toString(codProduto));
		            jTextNome.setText(nome);
		            jTextValor.setText(Double.toString(valor));
		            jTextEmEstoque.setText(Integer.toString(emEstoque));
		            jTextCodLote.setEnabled(true);
		            jTextCodProduto.setEnabled(true);
		            jTextNome.setEnabled(true);
		            jTextValor.setEnabled(true);
		            jTextEmEstoque.setEnabled(false);
		            botaoConsultar.setEnabled(false);
		            botaoExcluir.setEnabled(true);
		            jTextNome.requestFocus();
		        } catch (Exception erro) {
		            JOptionPane.showMessageDialog(janela, "Preencha o campo código corretamente!!");
		        }
		    }
		});

		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resposta = JOptionPane.showConfirmDialog(janela, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int codLote = Integer.parseInt(jTextCodLote.getText());
					int codProduto = Integer.parseInt(jTextCodProduto.getText());
					String nome = jTextNome.getText();
					double valor = Double.parseDouble(jTextValor.getText());
					int emEstoque = Integer.parseInt(jTextEmEstoque.getText());
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janela, "Preencha o campo titular");
						jTextNome.requestFocus();
					} else {
						if (!estoque.consultarEstoque(codLote, codProduto)) {
							if (!estoque.cadastrarEstoque(codLote, codProduto, nome, valor, emEstoque))
								JOptionPane.showMessageDialog(janela, "Erro na inclus�o do titular!");
							else
								JOptionPane.showMessageDialog(janela, "Inclus�o realizada!");
						} else {
							if (!estoque.atualizarEstoque(codLote, codProduto, nome, valor, emEstoque))
								JOptionPane.showMessageDialog(janela, "Erro na atualização do estoque!");
							else
								JOptionPane.showMessageDialog(janela, "Alteração realizada!");
						}

					}

				}
			}
		});
		
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextCodLote.setText(""); 
				jTextCodProduto.setText(""); 
				jTextNome.setText("");
				jTextValor.setText(""); 
				jTextEmEstoque.setText("");
				jTextCodLote.setEnabled(true);
				jTextCodProduto.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextValor.setEnabled(false);
				jTextEmEstoque.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextCodLote.requestFocus();
			}
		});
		

		
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(janela, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int codLote = Integer.parseInt(jTextCodLote.getText());
					int codProduto = Integer.parseInt(jTextCodProduto.getText());
					estoque.deletaEstoque(codLote, codProduto);
					jTextCodLote.setText("");
					jTextCodProduto.setText("");
					jTextNome.setText("");
					jTextValor.setText("");
					jTextEmEstoque.setText("");
					jTextCodLote.setEnabled(true);
					jTextCodProduto.setEnabled(true);
					jTextNome.setEnabled(false);
					jTextValor.setEnabled(false);
					jTextEmEstoque.setEnabled(false);
					botaoConsultar.setEnabled(true);
					botaoGravar.setEnabled(false);
					jTextCodProduto.requestFocus();
				}
			}
		});
		return janela;
	}
}