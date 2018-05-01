package org.jaxws.wsdl2html.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import java.io.FileFilter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window.Type;
//import net.miginfocom.swing.MigLayout;

public class Wsdl2HtmlJForm {

	private JFrame frmWsdlhtml;
	private JTextField textWSDLFilePathField;
	private JTextField textFieldForOutput;
	String Wrong_output_folder = "This Is not valid output folder ";

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
		frmWsdlhtml.setType(Type.UTILITY);
		frmWsdlhtml.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				textFieldForOutput.setText( new File("output").getAbsolutePath());
			}
		});
		frmWsdlhtml.setTitle("WSDL2HTML");
		frmWsdlhtml.setBounds(100, 100, 520, 608);
		frmWsdlhtml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JTextPane textPane1 = new JTextPane();
		JLabel lblEnterTheWsdl = new JLabel("Enter the WSDL Local Path ");
		
		textWSDLFilePathField = new JTextField();
		textWSDLFilePathField.setColumns(10);
		
		JButton btnGenrateDocunantion = new JButton("Generate Documentation");
		btnGenrateDocunantion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String wsdlUrl= textWSDLFilePathField.getText();
				
				File f = new File(wsdlUrl);
				if (! f.isFile()) {
					JOptionPane.showMessageDialog(frmWsdlhtml, "I can't find File");
					return;
				}
			
				
				try {
					String html = org.jaxws.wsdl2html.service.Wsdl2Html.generateHtml(wsdlUrl);
					
					File htmlDir = new File(textFieldForOutput.getText());
					File file = new File(textFieldForOutput.getText());
					if(!file.isDirectory())
					{
						
						JOptionPane.showMessageDialog(frmWsdlhtml, Wrong_output_folder);
						return;
					}
					
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
				jfc.addChoosableFileFilter(new FileNameExtensionFilter("wsdl","wsdl"));

				int returnValue = jfc.showOpenDialog(null);
			
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					textWSDLFilePathField.setText(selectedFile.getAbsolutePath());
				}
			}
		});
		
		textFieldForOutput = new JTextField();
		textFieldForOutput.setColumns(10);
		
		JButton btnOpenOutput = new JButton("Open Output");
		btnOpenOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					File file = new File(textFieldForOutput.getText());
					if(!file.isDirectory())
					{
						JOptionPane.showMessageDialog(frmWsdlhtml,Wrong_output_folder);
						return;
					}
					Desktop.getDesktop().open(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblOutputPath = new JLabel("Output path ");
		
		
		GroupLayout groupLayout = new GroupLayout(frmWsdlhtml.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(btnBrowseWSDL)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnOpenOutput)
					.addGap(15)
					.addComponent(btnGenrateDocunantion)
					.addContainerGap(84, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textWSDLFilePathField, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
						.addComponent(textPane1, GroupLayout.PREFERRED_SIZE, 454, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(textFieldForOutput, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
					.addGap(24))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblOutputPath, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(363, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEnterTheWsdl)
					.addContainerGap(363, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(lblEnterTheWsdl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textWSDLFilePathField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(lblOutputPath, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textFieldForOutput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGenrateDocunantion)
						.addComponent(btnBrowseWSDL)
						.addComponent(btnOpenOutput))
					.addGap(30)
					.addComponent(textPane1, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		frmWsdlhtml.getContentPane().setLayout(groupLayout);
	}
}
