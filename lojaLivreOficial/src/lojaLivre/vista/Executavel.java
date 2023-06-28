package lojaLivre.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import lojaLivre.janela.JanelaProduto;
import lojaLivre.janela.JanelaCliente;
import lojaLivre.janela.JanelaEstoque;
import lojaLivre.janela.JanelaFuncionario;
/**
 * 
 * @author Guilherme Valim
 *
 */
public class Executavel {

	public static void apresentarMenu() {
		JFrame janelaPrincipal = new JFrame("Cadastro de conta"); 
		janelaPrincipal.setTitle("Empresa Livre");
		janelaPrincipal.setResizable(false);
		janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janelaPrincipal.setSize(500, 400); 
		UIManager.put("OptionPane.yesButtonText", "Sim"); 
		UIManager.put("OptionPane.noButtonText", "Não");

		JMenuBar menuBar = new JMenuBar();
		janelaPrincipal.setJMenuBar(menuBar);
		JMenu menuAtualizar = new JMenu("Livre");
		menuBar.add(menuAtualizar);
		JMenuItem menuProduto = new JMenuItem("Produtos");
		menuAtualizar.add(menuProduto);
		JMenuItem menuFuncionario = new JMenuItem("Funcionarios");
		menuAtualizar.add(menuFuncionario);
		JMenuItem menuCliente = new JMenuItem("Cliente");
		menuAtualizar.add(menuCliente);
		JMenuItem menuEstoque = new JMenuItem("Estoque");
		menuAtualizar.add(menuEstoque);
		JFrame janela = JanelaProduto.criarJanelaProduto();
		JFrame janela2 = JanelaFuncionario.criarJanelaFuncionario();
		JFrame janela3 = JanelaCliente.criarJanelaCliente();
		JFrame janela4 = JanelaEstoque.criarJanelaEstoque();
		menuProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janela.setVisible(true);
			}
		});
		menuFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janela2.setVisible(true);
			}
		});
		menuCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janela3.setVisible(true);
			}
		});
		
		menuEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janela4.setVisible(true);
			}
		});
		janelaPrincipal.setVisible(true);
	}

	public static void main(String[] args) {
		apresentarMenu();
	}
}