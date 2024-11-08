package br.edu.fatecpg.lista.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edu.fatecpg.lista.model.Aluno;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;

public class JFrame2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_novo_nome;
	private JTextField txt_novo_telefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame2 frame = new JFrame2(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrame2(ArrayList<Aluno> ListaAlunos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(245, 245, 245));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lbl_nome = new JLabel("");
		lbl_nome.setBounds(185, 67, 70, 15);
		contentPane.add(lbl_nome);

		JLabel lbl_telefone = new JLabel("");
		lbl_telefone.setBounds(185, 91, 70, 15);
		contentPane.add(lbl_telefone);

		JComboBox comboBox = new JComboBox<>(ListaAlunos.toArray(new Aluno[0]));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno alunoselecionado = (Aluno) comboBox.getSelectedItem();
				if (alunoselecionado != null) {
					lbl_nome.setText(alunoselecionado.getNome());
					lbl_telefone.setText(alunoselecionado.getTelefone());

				}
			}
		});
		comboBox.setBounds(336, 12, 102, 24);
		contentPane.add(comboBox);

		JButton btn_atualizar = new JButton("Atualizar Aluno");
		btn_atualizar.setBackground(new Color(34, 139, 34));
		btn_atualizar.setForeground(Color.WHITE);

		btn_atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String novo_nome = txt_novo_nome.getText();
				String novo_telefone = txt_novo_telefone.getText();
				Aluno alunoselecionado = (Aluno) comboBox.getSelectedItem();

				if (alunoselecionado != null) {
					if (!novo_nome.isEmpty() && !novo_telefone.isEmpty()) {

						alunoselecionado.setNome(novo_nome);
						alunoselecionado.setTelefone(novo_telefone);
						JOptionPane.showMessageDialog(btn_atualizar, "Alterado Com Sucesso!");
						lbl_nome.setText(novo_nome);
						lbl_telefone.setText(novo_telefone);

						txt_novo_nome.setText("");
						txt_novo_telefone.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(btn_atualizar, "Selecione um aluno para atualizar!");
				}
			}
		});

		JButton btn_excluir = new JButton("Excluir Aluno");
		btn_excluir.setBackground(new Color(220, 20, 60));
		btn_excluir.setForeground(Color.WHITE);

		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aluno alunoselecionado = (Aluno) comboBox.getSelectedItem();
				if (alunoselecionado != null) {
					String mensagem_excluir = JOptionPane.showInputDialog("Tem certeza que deseja excluir o aluno: "
							+ alunoselecionado + " ? \n\n Digite 'Excluir Aluno' para excluir: ");
					if (mensagem_excluir.equals("Excluir Aluno")) {

						ListaAlunos.remove(alunoselecionado);
						JOptionPane.showMessageDialog(btn_excluir, "Aluno excluído com sucesso!");
						lbl_nome.setText(" ");
						lbl_telefone.setText(" ");
						comboBox.removeItem(alunoselecionado);
					}

					else {
						JOptionPane.showMessageDialog(btn_excluir,
								"Por favor digite a mensagem correta: 'Excluir Aluno'");
					}

				} else {
					JOptionPane.showMessageDialog(btn_excluir, "Selecione um Aluno para Excluir!");
				}
			}
		});

		btn_atualizar.setBounds(33, 130, 117, 25);
		contentPane.add(btn_atualizar);

		txt_novo_nome = new JTextField();
		txt_novo_nome.setText("");
		txt_novo_nome.setBounds(33, 68, 114, 19);
		contentPane.add(txt_novo_nome);
		txt_novo_nome.setColumns(10);

		txt_novo_telefone = new JTextField();
		txt_novo_telefone.setText(" ");
		txt_novo_telefone.setBounds(33, 99, 114, 19);
		contentPane.add(txt_novo_telefone);
		txt_novo_telefone.setColumns(10);

		btn_excluir.setBounds(33, 176, 117, 25);
		contentPane.add(btn_excluir);
	}
}
