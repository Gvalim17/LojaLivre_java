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

import lojaLivre.modelo.FuncionarioNormal;
/**
 * 
 * @author Guilherme Valim
 *
 */
public class JanelaFuncionario {
	public static JFrame criarJanelaFuncionario() {

		JFrame janela = new JFrame("Atualização de Funcionario"); 
		janela.setResizable(false); 
		janela.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		janela.setSize(500, 400); 
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);

		JLabel labelCodFuncionario = new JLabel("Código do Funcionario: ");
		JLabel labelCpf = new JLabel("CPF do Funcionario: ");
		JLabel labelNome = new JLabel("Nome do Funcionario: ");
		JLabel labelSalario = new JLabel("Salário do Funcionario: ");
		JLabel labelCargo = new JLabel("Cargo do Funcionario: ");

		labelCodFuncionario.setBounds(50, 40, 150, 20);
		labelCpf.setBounds(50, 80, 150, 20); 
		labelNome.setBounds(50, 120, 150, 20);
		labelSalario.setBounds(50, 160, 150, 20);
		labelCargo.setBounds(50, 200, 150, 20);

		JTextField jTextcodFuncionario = new JTextField();
		JTextField jTextCpf = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextSalario = new JTextField();
		JTextField jTextCargo = new JTextField();

		jTextcodFuncionario.setEnabled(true);
		jTextCpf.setEnabled(false);
		jTextNome.setEnabled(false);
		jTextSalario.setEnabled(false);
		jTextCargo.setEnabled(false);

		jTextcodFuncionario.setBounds(220, 40, 50, 20);
		jTextCpf.setBounds(220, 80, 100, 20);
		jTextNome.setBounds(220, 120, 100, 20);
		jTextSalario.setBounds(220, 160, 100, 20);
		jTextCargo.setBounds(220, 200, 100, 20);

		janela.add(labelCodFuncionario);
		janela.add(labelNome);
		janela.add(labelCpf);
		janela.add(labelSalario);
		janela.add(labelCargo);
		janela.add(jTextcodFuncionario);
		janela.add(jTextCpf);
		janela.add(jTextNome);
		janela.add(jTextSalario);
		janela.add(jTextCargo);

		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(350, 40, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 240, 100, 20);
		botaoGravar.setEnabled(false);
		janela.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 240, 100, 20);
		janela.add(botaoLimpar);
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.setBounds(50, 280, 100, 20);
		janela.add(botaoExcluir);
		botaoExcluir.setEnabled(false);

		FuncionarioNormal funcionario = new FuncionarioNormal();

		botaoConsultar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int codFuncionario = Integer.parseInt(jTextcodFuncionario.getText());
		            botaoGravar.setEnabled(true);
		            String nome;
		            int cpf;
		            double salario;
		            String cargo;
		            
		            if (!funcionario.consultarFuncionario(codFuncionario)) {
		                nome = "";
		                cpf = 0;
		                salario = 0.0;
		                cargo = "";
		            } else {
		                nome = funcionario.getNome();
		                cpf = funcionario.getCpf();
		                salario = funcionario.getSalario();
		                cargo = funcionario.getCargo();
		            }
		            
		            jTextCpf.setText(Integer.toString(cpf));
		            jTextNome.setText(nome);
		            jTextSalario.setText(Double.toString(salario));
		            jTextCargo.setText(cargo);
		            jTextcodFuncionario.setEnabled(false);
		            jTextCpf.setEnabled(true);
		            jTextSalario.setEnabled(true);
		            jTextCargo.setEnabled(true);
		            botaoConsultar.setEnabled(false);
		            botaoExcluir.setEnabled(true);
		            jTextNome.setEnabled(true);
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
					int codFuncionario = Integer.parseInt(jTextcodFuncionario.getText());
					String nome = jTextNome.getText().trim(); 
					int cpf = Integer.parseInt(jTextCpf.getText());
					double salario = Double.parseDouble(jTextSalario.getText());
					String cargo = jTextCargo.getText().trim();
					if (nome.length() == 0) {
						JOptionPane.showMessageDialog(janela, "Preencha o campo titular");
						jTextCpf.requestFocus();
					} else {
						if (!funcionario.consultarFuncionario(codFuncionario)) {
							if (!funcionario.cadastrarFuncionario(codFuncionario, cpf, nome, salario, cargo))
								JOptionPane.showMessageDialog(janela, "Erro na inclus�o do titular!");
							else
								JOptionPane.showMessageDialog(janela, "Inclus�o realizada!");
						} else {
							if (!funcionario.atualizarFuncionario(codFuncionario, cpf, nome, salario, cargo))
								JOptionPane.showMessageDialog(janela, "Erro na atualização do funcionario!");
							else
								JOptionPane.showMessageDialog(janela, "Alteração realizada!");
						}

					}

				}
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextcodFuncionario.setText("");
				jTextCpf.setText(""); 
				jTextNome.setText(""); 
				jTextSalario.setText("");
				jTextCargo.setText("");
				jTextcodFuncionario.setEnabled(true);
				jTextCpf.setEnabled(false);
				jTextNome.setEnabled(false);
				jTextSalario.setEnabled(false);
				jTextCargo.setEnabled(false);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(false);
				jTextcodFuncionario.requestFocus(); 
			}
		});
		
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int resposta = JOptionPane.showConfirmDialog(janela, "Deseja atualizar?", "Confirmação",
						JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					int codFuncionario = Integer.parseInt(jTextcodFuncionario.getText());
					funcionario.deletaFuncionario(codFuncionario);
					jTextcodFuncionario.setText("");
					jTextCpf.setText("");
					jTextNome.setText("");
					jTextSalario.setText("");
					jTextCargo.setText("");
					jTextcodFuncionario.setEnabled(true);
					jTextCpf.setEnabled(false);
					jTextNome.setEnabled(false);
					jTextSalario.setEnabled(false);
					jTextCargo.setEnabled(false);
					botaoConsultar.setEnabled(true);
					botaoGravar.setEnabled(false);
					jTextcodFuncionario.requestFocus();
				}
			}
		});
		return janela;
	}
}