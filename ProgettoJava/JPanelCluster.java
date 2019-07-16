import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;


class JPanelCluster extends JPanel
{
	public JTextField fileText = new JTextField(20);
	public JTextField tableText = new JTextField(20);
	public JTextField parameterText = new JTextField(10);
	public JTextArea clusterOutput = new JTextArea();
	public JButton executeButton = new JButton("Execute");

	JPanelCluster(String buttonName, ActionListener a)
	{
		setLayout(new BorderLayout(0, 0));

		JPanel upPanel = new JPanel();
		add(upPanel, BorderLayout.NORTH);
		upPanel.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblTable = new JLabel("Table Name: ");
		upPanel.add(lblTable);

		upPanel.add(tableText);

		JLabel lblRadius = new JLabel("Radius: ");
		upPanel.add(lblRadius);

		upPanel.add(parameterText);

		JPanel downPanel = new JPanel();
		add(downPanel, BorderLayout.SOUTH);

		downPanel.add(executeButton);

		JPanel centralPanel = new JPanel();
		add(centralPanel, BorderLayout.CENTER);
		centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.X_AXIS));

		centralPanel.add(clusterOutput);

		executeButton.addActionListener(a);



	}
}
