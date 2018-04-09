package org.jaxws.wsdl2html.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;
import org.jaxws.wsdl2bytecodes.service.WsdlImportException;
import org.jaxws.wsdl2html.service.Wsdl2Html;

import java.awt.Label;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
//import net.miginfocom.swing.MigLayout;

public class Wsdl2HtmlJForm {

	private JFrame frmWsdlhtml;
	private JTextField textWSDLFilePathField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wsdl2HtmlJForm window = new Wsdl2HtmlJForm();
					window.frmWsdlhtml.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Wsdl2HtmlJForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWsdlhtml = new JFrame();
		frmWsdlhtml.setTitle("WSDL2HTML");
		frmWsdlhtml.setBounds(100, 100, 491, 608);
		frmWsdlhtml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JTextPane textPane1 = new JTextPane();
		JLabel lblEnterTheWsdl = new JLabel("Enter the WSDL Local Path ");
		
		textWSDLFilePathField = new JTextField();
		textWSDLFilePathField.setColumns(10);
		
		JButton btnGenrateDocunantion = new JButton("Generate Documentation");
		btnGenrateDocunantion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String tempWSDl="C:\\Users\\mohammed\\git\\wsdl2html\\resources\\wsdl\\BankCustomerService.wsdl";
				String wsdlUrl= textWSDLFilePathField.getText();
				
				File f = new File(wsdlUrl);
				if (! f.isFile()) {
					JOptionPane.showMessageDialog(frmWsdlhtml, "I can't find File");
					return;
				}
			
				
				try {
					String html = org.jaxws.wsdl2html.service.Wsdl2Html.generateHtml(wsdlUrl);
					File htmlDir = new File("output");
					String reportFileName=f.getName()+".html";
					File fileReport = new File(htmlDir, reportFileName);
					FileUtils.writeStringToFile(fileReport, html);
					textPane1.setText("Please find the HTML files at " + fileReport.getAbsolutePath());
					//System.out.println();
				} catch (WsdlImportException e) {
					// TODO Auto-generated catch block
					textPane1.setText(e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					textPane1.setText(e.getMessage());
					e.printStackTrace();
				} 
			}
		});
		
		JButton btnBrowseWSDL = new JButton("Browse for WSDL file");
		btnBrowseWSDL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					textWSDLFilePathField.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frmWsdlhtml.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(161)
					.addComponent(btnBrowseWSDL)
					.addContainerGap(181, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(151)
					.addComponent(btnGenrateDocunantion)
					.addContainerGap(171, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(132)
					.addComponent(lblEnterTheWsdl, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addGap(156))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textPane1, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textWSDLFilePathField, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
							.addGap(24))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblEnterTheWsdl, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(textWSDLFilePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBrowseWSDL, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnGenrateDocunantion, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(textPane1, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		frmWsdlhtml.getContentPane().setLayout(groupLayout);
	}
}
