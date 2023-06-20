package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.ControleMarca;
import Controller.ControleRemedio;
import Model.Marca;
import Model.Remedio;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class ListaRemedio extends JFrame {

	private JPanel contentPane;
	private JTable tbRemedios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaRemedio frame = new ListaRemedio();
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
	public ListaRemedio() {
		Map<Integer, Integer> rowId_remedioId = new HashMap<>();
		
		setTitle("Remédios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastraRemedio = new JButton("Cadastrar remédio");
		btnCadastraRemedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastraRemedio cadastra_remedio = new CadastraRemedio();
				cadastra_remedio.setVisible(true);
				setVisible(false);
			}
		});
		btnCadastraRemedio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCadastraRemedio.setBounds(10, 419, 196, 42);
		contentPane.add(btnCadastraRemedio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 772, 400);
		contentPane.add(scrollPane);
		
		tbRemedios = new JTable();
		tbRemedios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Marca", "C\u00F3digo de barras", "Data de produ\u00E7\u00E3o", "Validade", "Valor de custo", "Valor de venda", "Quantidade"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tbRemedios);
		atualizaLista(tbRemedios, rowId_remedioId);
	}
	private void atualizaLista(JTable tbRemedios, Map<Integer, Integer> rowId_remedioId) {
		ControleRemedio controle_remedio = new ControleRemedio();
		List<Remedio> remedios = controle_remedio.listaRemedios();
		
		int i = -1; //contador do ID linha
		for (Remedio remedio : remedios) {
			i++;
			DefaultTableModel model = (DefaultTableModel) tbRemedios.getModel();
			Object[] newRow = {remedio.getNome(), remedio.getMarca().getNome(), remedio.getCodigoBarra(), 
					remedio.getDataProducao().toString(), remedio.getDataValidade().toString(), 
					remedio.getValorCusto(), remedio.getValorVenda(), remedio.getQuantidade()};
			model.addRow(newRow);
			rowId_remedioId.put(i, remedio.getId());
		}
	}
}