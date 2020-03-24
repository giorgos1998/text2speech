package commands;

import gui.FreeTTSWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class NewFile implements ActionListener{
	
	private FreeTTSWindow frame;
	private String fileAuthor;
	private String fileTitle;
	private LocalDateTime creationDate;
	
	public NewFile(FreeTTSWindow frame) {
		this.frame = frame;
		fileAuthor = "";
		fileTitle = "";
		creationDate = null;
	}
		
	@Override
	public void actionPerformed(ActionEvent ev) {
		// Pop's up a window that requests the user to give file's title and author
		try {
			JFrame newFileFrame = new JFrame();	
			newFileFrame.setTitle("New File");
			newFileFrame.setBounds(400, 200, 482, 245);
			newFileFrame.setResizable(false);
			newFileFrame.setVisible(true);
			
			JLabel titleLabel = new JLabel("Title");
			JLabel authorLabel = new JLabel("Author");
			
			JTextField titleTextField = new JTextField();
			titleTextField.setColumns(10);
			
			JTextField authorTextField = new JTextField();
			authorTextField.setColumns(10);
					
			JButton createButton = new JButton("Create");
			createButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					String message;
					fileAuthor = authorTextField.getText().trim();
					fileTitle = titleTextField.getText().trim();
					
					// Make sure user gave title and author
					if (fileAuthor.length() == 0 && fileTitle.length() == 0) {
						message = "You must give the file a Title and an Author!";
					}else if (fileTitle.length() == 0) {
						message = "You must give the file a Title!";
					}else if (fileAuthor.length() == 0) {
						message = "You must give the file an Author!";
					// If user gave both title and author close newFileWindow
					}else {
						creationDate = LocalDateTime.now();
						newFileFrame.dispose();
						FileFilter txtFilter = new FileNameExtensionFilter("Plain text", "txt");
						frame.setFileChooser(new JFileChooser());
						frame.getFileChooser().setFileFilter(txtFilter);
						frame.setTitle("NewFile*   -   FreeTTS Editor");
						frame.getTextArea().setText(null);
						return;
					}
					JOptionPane.showMessageDialog(null, message, "", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
					newFileFrame.dispose();
				}
			});
			
			GroupLayout groupLayout = new GroupLayout(newFileFrame.getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap().addComponent(createButton).addGap(31).addComponent(cancelButton)
						.addGap(28)).addGroup(groupLayout.createSequentialGroup().addGap(75)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(authorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(titleLabel, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
						.addGap(27).addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorTextField, 217, 217, 217)))).addContainerGap(114, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup()
						.addGap(41).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(titleTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(26).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(authorTextField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(26).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(createButton).addComponent(cancelButton)).addContainerGap())
			);
			newFileFrame.getContentPane().setLayout(groupLayout);			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getFileTitle() {
		return fileTitle;
	}
	
	public String getFileAuthor() {
		return fileAuthor;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
}
