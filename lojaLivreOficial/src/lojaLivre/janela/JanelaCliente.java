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

import lojaLivre.modelo.ClienteNormal;
/**
 * 
 * @author Guilherme Valim
 *
 */
public class JanelaCliente {
	public static JFrame criarJanelaCliente() {

		JFrame janela = new JFrame("Atualização de Cliente"); 
		janela.setResizable(false); 
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janela.setSize(500, 500); 

		Container caixa = janela.getContentPane();
		caixa.setLayout(null);

		JLabel labelCodCpf = new JLabel("CPF do Cliente: ");
		JLabel labelNome = new JLabel("Nome do Cliente: ");
		JLabel labelIdade = new JLabel("Idade do Cliente: ");
		JLabel labelEmail = new JLabel("Email do Cliente: ");
		JLabel labelTelefone = new JLabel("Telefone do Cliente: ");
		JLabel labelEndereco = new JLabel("Endereço do Cliente: ");
		JLabel labelCep = new JLabel("CEP do Cliente: ");

		labelCodCpf.setBounds(50, 40, 150, 20); 
		labelNome.setBounds(50, 80, 150, 20); 
		labelIdade.setBounds(50, 120, 150, 20);
		labelEmail.setBounds(50, 160, 150, 20);
		labelTelefone.setBounds(50, 200, 150, 20);
		labelEndereco.setBounds(50, 240, 150, 20);
		labelCep.setBounds(50, 280, 150, 20);

		JTextField jTextCodCpf = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextIdade = new JTextField();
		JTextField jTextEmail = new JTextField();
		JTextField jTextTelefone = new JTextField();
		JTextField jTextEndereco = new JTextField();
		JTextField jTextCep = new JTextField();

		jTextCodCpf.setEnabled(true);
		jTextNome.setEnabled(false);
		jTextIdade.setEnabled(false);
		jTextEmail.setEnabled(false);
		jTextTelefone.setEnabled(false);
		jTextEndereco.setEnabled(false);
		jTextCep.setEnabled(false);

		jTextCodCpf.setBounds(220, 40, 200, 20);
		jTextNome.setBounds(220, 80, 200, 20);
		jTextIdade.setBounds(220, 120, 200, 20);
		jTextEmail.setBounds(220, 160, 200, 20);
		jTextTelefone.setBounds(220, 200, 200, 20);
		jTextEndereco.setBounds(220, 240, 200, 20);
		jTextCep.setBounds(220, 280, 200, 20);

		janela.add(labelCodCpf);
		janela.add(labelIdade);
		janela.add(labelNome);
		janela.add(labelTelefone);
		janela.add(labelEmail);
		janela.add(labelEndereco);
		janela.add(labelCep);
		janela.add(jTextCodCpf);
		janela.add(jTextNome);
		janela.add(jTextIdade);
		janela.add(jTextEmail);
		janela.add(jTextTelefone);
		janela.add(jTextEndereco);
		janela.add(jTextCep);

		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(80, 340, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(80, 380, 100, 20);
		botaoGravar.setEnabled(false);
		janela.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(280, 340, 100, 20);
		janela.add(botaoLimpar);
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(280, 380, 100, 20);
		janela.add(botaoExcluir);
		botaoExcluir.setEnabled(false);

		ClienteNormal Cliente = new ClienteNormal();

		botaoConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int codCpf = Integer.parseInt(jTextCodCpf.getText());
		            botaoGravar.setEnabled(true);
		            String nome;
		            int idade;
		            String email;
		            String telefone;
		            String endereco;
		            int cep;
		            
		            if (!Cliente.consultarCliente(codCpf)) {
		                nome = "";
		                idade = 0;
		                email = "";
		                telefone = "";
		                endereco = "";
		                cep = 0;
		            } else {
		                nome = Cliente.getNome();
		                idade = Cliente.getIdade();
		                email = Cliente.getEmail();
		                telefone = Cliente.getTelefone();
		                endereco = Cliente.getEndereco();
		                cep = Cliente.getCep();
		            }
		            
		            jTextNome.setText(nome);
		            jTextIdade.setText(Integer.toString(idade));
		            jTextEmail.setText(email);
		            jTextTelefone.setText(telefone);
		            jTextEndereco.setText(endereco);
		            jTextCep.setText(Integer.toString(cep));
		            
		            jTextCodCpf.setEnabled(false);
		            jTextNome.setEnabled(true);
		            jTextEmail.setEnabled(true);
		            jTextTelefone.setEnabled(true);
		            jTextEndereco.setEnabled(true);
		            jTextCep.setEnabled(true);
		            
		            botaoConsultar.setEnabled(false);
		            botaoExcluir.setEnabled(true);
		            jTextIdade.setEnabled(true);
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
					int codCpf = Integer.parseInt(jTextCodCpf.getText());
					String nome = jTextNome.getText().trim();;
					int idade = Integer.parseInt(jTextIdade.getText());
					String email = jTextEmail.getText().trim();;
					String telefone = jTextTelefone.getText().trim();;
					String endereco = jTextEndereco.getText().trim();;
					int cep = Integer.parseInt(jTextCep.getText());
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janela, "Preencha o campo titular");
						jTextNome.requestFocus();
					} else {
						if (!Cliente.consultarCliente(codCpf)) {
							if (!Cliente.cadastrarCliente(codCpf, nome, idade, email, telefone, endereco, cep))
								JOptionPane.showMessageDialog(janela, "Erro na inclus�o do titular!");
							else
								JOptionPane.showMessageDialog(janela, "Inclus�o realizada!");
						} else {
							if (!Cliente.atualizarCliente(codCpf, nome, idade, email, telefone, endereco, cep))
								JOptionPane.showMessageDialog(janela, "Erro na atualização do Cliente!");
							else
								JOptionPane.showMessageDialog(janela, "Alteração realizada!");
						}

					}

				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextCodCpf.setText(""); 
				jTextNome.setText(""); 
				jTextIdade.setText(""); 
				jTextEmail.setText("");
				jTextTelefone.setText("");
				jTextEndereco.setText("");
				jTextCep.setText("");
				
				jTextCodCpf.setEnabled(true);
				jTextNome.setEnabled(false);
				jTextIdade.setEnabled(false);
				jTextEmail.setEnabled(false);
				jTextTelefone.setEnabled(false);
				jTextEndereco.setEnabled(false);
				jTextCep.setEnabled(false);
				
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextCodCpf.requestFocus(); 
			}
		});
		
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(janela, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int codCliente = Integer.parseInt(jTextCodCpf.getText());
					Cliente.deletaCliente(codCliente);
					jTextCodCpf.setText("");
					jTextNome.setText("");
					jTextIdade.setText("");
					jTextEmail.setText("");
					jTextTelefone.setText("");
					jTextEndereco.setText("");
					jTextCep.setText("");
					
					jTextCodCpf.setEnabled(true);
					jTextNome.setEnabled(false);
					jTextIdade.setEnabled(false);
					jTextEmail.setEnabled(false);
					jTextTelefone.setEnabled(false);
					jTextEndereco.setEnabled(false);
					jTextCep.setEnabled(false);
					botaoConsultar.setEnabled(true);
					botaoGravar.setEnabled(false);
		
					jTextCodCpf.requestFocus();
				}
			}
		});
		return janela;
	}
}
