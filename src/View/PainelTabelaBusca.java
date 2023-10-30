package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PainelTabelaBusca extends JPanel {

	JTable jt;

	JButton btn = new JButton("Buscar");

	JButton btnExcluir = new JButton("Excluir registro");

	JTextField textField = new JTextField(20);

	String nomePainel;

	DefaultTableModel dtm;

	List<String> nomeColunas;
	
	List<List<Object>> dados;

	public PainelTabelaBusca(String aNomePainel, List<String> nomeColunas) {
		super();

		setLayout(new BorderLayout());

		setName(aNomePainel);

		this.nomeColunas = nomeColunas;

		Object[] arrayNomeColunas = new Object[nomeColunas.size()];

		for (int i = 0; i < nomeColunas.size(); i++) {
			arrayNomeColunas[i] = this.nomeColunas.get(i);
		}

		dtm = new MyDefaultTableModel(arrayNomeColunas, 0);

		jt = new JTable(dtm);

		jt.getTableHeader().setReorderingAllowed(false);
		;

		JScrollPane jsp = new JScrollPane(jt);

		add(jsp, BorderLayout.CENTER);

		JPanel controles = new JPanel();

		add(controles, BorderLayout.PAGE_END);

		controles.add(textField);
		controles.add(btn);
		controles.add(btnExcluir);

	}

	public String getBusca() {
		return textField.getText();
	}

	public void addAcaoBuscar(ActionListener al) {
		btn.addActionListener(al);
		textField.addActionListener(al);
	}

	public void mostrarBusca(List<List<Object>> list) {
		dados = list;
		
		dtm.setRowCount(0);

		for (List<Object> row : list) {
			dtm.addRow(row.toArray());
		}

		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "Não há nenhum registro com esse nome.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void mostrarConfirmacao(boolean result) {
		if(result == true) {
			JOptionPane.showMessageDialog(null, "Registro Apagado.", "Excluir registro",
					JOptionPane.PLAIN_MESSAGE);
			
			btn.doClick();
		}else {
			JOptionPane.showMessageDialog(null, "Não foi possivel apagar.", "Alerta",
					JOptionPane.WARNING_MESSAGE);
		}

	}
	
	public String apagarItem(int col) {
		int row = jt.getSelectedRow();

		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Selecione um registro para apaga-lo", "Alerta",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

		return "" + dados.get(row).get(col);

	}

	public void addAcaoExcluir(ActionListener al) {
		btnExcluir.addActionListener(al);
	}

	
	class MyDefaultTableModel extends DefaultTableModel {

		MyDefaultTableModel(Object[] arrayNomeColunas, int rowCount) {
			super(arrayNomeColunas, rowCount);
		}

		@Override
		public boolean isCellEditable(int row, int collumn) {
			return false;
		}
	}

}
