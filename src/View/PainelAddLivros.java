package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.EditoraGetter;
import Entity.Editora;
import Entity.Livro;

@SuppressWarnings("serial")
public class PainelAddLivros extends JPanel {

	EscolheEditora escolheEditora = new EscolheEditora();
	JButton btnAtivaEscolhaEditora = new JButton("Escolher editora");
	Editora editoraEscolhida = null;

	JButton adicionar = new JButton("Adicionar");

	JLabel lblNome = new JLabel("Nome");
	JTextField fldNome = new JTextField(20);

	JLabel lblIsbn = new JLabel("Isbn");
	JTextField fldIsbn = new JTextField(20);

	JLabel lblPreco = new JLabel("Preço");
	JTextField fldPreco = new JTextField(20);

	JLabel lblEditoraEscolhida = new JLabel("Nenhuma");

	public PainelAddLivros() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel informacoes = new JPanel(new GridLayout(4, 2));

		add(informacoes);
		informacoes.add(lblNome);
		informacoes.add(fldNome);
		informacoes.add(lblIsbn);
		informacoes.add(fldIsbn);
		informacoes.add(lblPreco);
		informacoes.add(fldPreco);

		informacoes.add(new JLabel("Editora: "));
		informacoes.add(lblEditoraEscolhida);

		JPanel controle = new JPanel();
		add(controle);
		controle.add(adicionar);
		adicionar.setEnabled(false);
		controle.add(btnAtivaEscolhaEditora);
		add(Box.createRigidArea(new Dimension(0, 400)));

		btnAtivaEscolhaEditora.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				escolheEditora.setVisible(true);
				escolheEditora.preencheTabela();

			}
		});

	}

	public void addAcaoEnviarEditora(EditoraGetter ge) {
		escolheEditora.getEditoras = ge;
	}

	public Livro getLivroAdd() {
		return new Livro(fldNome.getText(), fldIsbn.getText(), editoraEscolhida.getPublisherId(),
				Double.parseDouble(fldPreco.getText()));
	}

	public void addAcaoAdicionarLivro(ActionListener al) {
		adicionar.addActionListener(al);
	}

	class EscolheEditora extends JFrame {

		EditoraGetter getEditoras;

		List<Editora> editoras;

		DefaultTableModel dtm = new DefaultTableModel(new Object[] { "Editoras" }, 0);
		JTable jt = new JTable(dtm);

		JScrollPane js = new JScrollPane(jt);

		public EscolheEditora() {
			add(js);
			jt.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					if (e.getClickCount() == 2) {
						editoraEscolhida = getEditoraFromTabela();
						setVisible(false);
						lblEditoraEscolhida.setText(editoraEscolhida.getNome());
						adicionar.setEnabled(true);
					}

				}
			});
			setBounds(200, 200, 200, 300);
		}

		public void preencheTabela() {
			dtm.setRowCount(0);
			editoras = getEditoras.getEditoras();
			for (Editora e : getEditoras.getEditoras()) {
				dtm.addRow(new Object[] { e.getNome(), e.getUrl() });
			}
		}

		public Editora getEditoraFromTabela() {
			int row = jt.getSelectedRow();
			return editoras.get(row);
		}
	}

}
