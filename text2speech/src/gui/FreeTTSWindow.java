package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import commands.CommandFactory;


public class FreeTTSWindow extends JFrame{
	
	private JTextArea textArea = new JTextArea(10, 10);
	private JFileChooser fileChooser = new JFileChooser();
	private JMenuItem mntmNewMenuItem;		// New File
	private JMenuItem mntmNewMenuItem_1;	// Open file...
	private JMenuItem mntmNewMenuItem_2;	// Save file
	private JMenuItem mntmNewMenuItem_3;	// Save As file...
	private JMenuItem mntmNewMenuItem_4;	// Exit
		
	public FreeTTSWindow() {
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
        
        FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
      	fileChooser.setFileFilter(txtFilter);
                
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new LineBorder(SystemColor.controlHighlight, 20));		
        
        setTitle("FreeTTS Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 435);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/*
		 * FILE MENU
		 */
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("New file");
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Open file...");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("Save");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("Save As...");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		mnNewMenu.addSeparator();
		
		mntmNewMenuItem_4 = new JMenuItem("Exit");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		createListeners();	
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JFileChooser getFileChooser() {
		return fileChooser;
	}
	
	public void setFileChooser(JFileChooser fc) {
		fileChooser = fc;
	}
		
	public void createListeners() {
		CommandFactory factory = new CommandFactory();
		mntmNewMenuItem.addActionListener(factory.makeCommand("NewFileCommand", this));
		mntmNewMenuItem_1.addActionListener(factory.makeCommand("OpenFileCommand", this));
		mntmNewMenuItem_2.addActionListener(factory.makeCommand("SaveFileCommand", this));
		mntmNewMenuItem_3.addActionListener(factory.makeCommand("SaveAsFileCommand", this));
		mntmNewMenuItem_4.addActionListener(factory.makeCommand("ExitFileCommand", this));
	}
	
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FreeTTSWindow frame = new FreeTTSWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
