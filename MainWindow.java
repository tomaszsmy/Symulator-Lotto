package Main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {
	private JFrame frmSymulatorLotto;
	private JSpinner[] ValueSpinner = new JSpinner[6];
	private JLabel[] labelScore = new JLabel[3];
	private int[] bet = new int[6];
	private JComboBox<String> comboBoxAllResults;
	private JSpinner spinnerNumbersRand;
	private JLabel lblIloLosowa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmSymulatorLotto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public MainWindow() {
		initialize();
	}

	private boolean check() {
		boolean check = true;

		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				if (bet[i] == bet[j]) {
					check = false;
				}
			}
		}
		return check;
	}

	private ArrayList<Integer> rand() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 1; i < 50; i++) {
			temp.add(Integer.valueOf(i));
		}
		Collections.shuffle(temp);
		ArrayList<Integer> RandomSix = new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
			RandomSix.add(temp.get(i));
		}
		return RandomSix;
	}

	private void initialize() {
		frmSymulatorLotto = new JFrame();
		frmSymulatorLotto.setTitle("Symulator Lotto");
		frmSymulatorLotto.setBounds(100, 100, 408, 310);
		frmSymulatorLotto.setDefaultCloseOperation(3);
		frmSymulatorLotto.getContentPane().setLayout(null);

		JButton btnPlay = new JButton("Graj");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				for (int i = 0; i < 6; i++)
					bet[i] = (Integer) ValueSpinner[i].getValue();

				if (!check())
					JOptionPane.showMessageDialog(frmSymulatorLotto, "Musisz podać 6 różnych liczb");
				else {
					ArrayList<Integer> RandomBets;
					int hits[] = { 0, 0, 0 };
					ArrayList<Integer> numberRand[] = new ArrayList[3];
					for (int i = 0; i < 3; i++)
						numberRand[i] = new ArrayList<Integer>();
					comboBoxAllResults.removeAllItems();
					comboBoxAllResults.setVisible(true);
					for (int i = 0; i < (Integer) spinnerNumbersRand.getValue(); i++) {
						int check = 0;
						RandomBets = rand();
						for (int j = 0; j < 6; j++) {
							if (bet[j] == RandomBets.get(j))
								check++;
							else
								break;

						}

						if (check == 6) {
							hits[2]++;
							numberRand[2].add(i + 1);
						} else if (check == 5) {
							hits[1]++;
							numberRand[1].add(i + 1);
						} else if (check == 4) {
							hits[0]++;
							numberRand[0].add(i + 1);
						}

						comboBoxAllResults.addItem("Losowanie: " + (i + 1) + " " + RandomBets.toString());

					}
					for (int i = 0; i < 3; i++)
						labelScore[i].setText("Trafione " + (i + 4) + ":" + hits[i] + " Numer losowania: "
								+ numberRand[i].toString());

				}
			}
		});

		btnPlay.setBounds(231, 90, 89, 23);
		frmSymulatorLotto.getContentPane().add(btnPlay);

		comboBoxAllResults = new JComboBox<String>();
		comboBoxAllResults.setVisible(false);
		comboBoxAllResults.setBounds(10, 214, 273, 20);
		frmSymulatorLotto.getContentPane().add(comboBoxAllResults);

		spinnerNumbersRand = new JSpinner();
		spinnerNumbersRand.setModel(new SpinnerNumberModel(1, 1, 10000000, 1));
		spinnerNumbersRand.setBounds(107, 91, 114, 20);
		frmSymulatorLotto.getContentPane().add(spinnerNumbersRand);

		lblIloLosowa = new JLabel("Ilość losowań:");
		lblIloLosowa.setBounds(10, 94, 83, 14);
		frmSymulatorLotto.getContentPane().add(lblIloLosowa);

		JLabel lblTwjZakad = new JLabel("Twój zakład:");
		lblTwjZakad.setBounds(120, 24, 101, 14);
		frmSymulatorLotto.getContentPane().add(lblTwjZakad);

		int PosX = 125;

		for (int i = 0; i < 3; i++) {
			labelScore[i] = new JLabel("");
			labelScore[i].setBounds(20, PosX, 285, 14);
			frmSymulatorLotto.getContentPane().add(labelScore[i]);
			PosX += 30;
		}

		int PosY = 50;

		for (int i = 0; i < 6; i++) {
			ValueSpinner[i] = new JSpinner();
			ValueSpinner[i].setModel(new SpinnerNumberModel(i + 1, 1, 49, 1));
			ValueSpinner[i].setBounds(PosY, 50, 42, 20);
			frmSymulatorLotto.getContentPane().add(ValueSpinner[i]);
			PosY += 40;
		}
	}
}
