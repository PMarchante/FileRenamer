import javax.swing.*;


public class GUI {
	
	private JButton clickMeButton;
	private JPanel fileRenamer;
	private JLabel Label;
	private JTextField renameTo;
	private JComboBox<String> format;
	private JButton directory;
	private JLabel newNameLabel;
	private JCheckBox inOrder;
	
	private String getFolderDirectory () {
		
		return folderDirectory;
	}
	
	private void setFolderDirectory (String folderDirectory) {
		
		this.folderDirectory = folderDirectory;
	}
	
	private String folderDirectory;
	
	private GUI () {
		
		format.setSelectedIndex(0);
		
		clickMeButton.addActionListener(e ->{
			int x=0;
			boolean complete = false;
			//if the inorder button is not selected
			if (!inOrder.isSelected()) {
				x = 0;
				complete = false;
				if (! renameTo.getText().isEmpty()) {
					NameChanger test = new NameChanger();
					x = test.fileLocation(renameTo.getText(), (String) format.getSelectedItem(), getFolderDirectory());
					
					if (x > 0)
						complete = true;
				}
			}
			
			if (inOrder.isSelected()){
				x = 0;
				complete = false;
				
				if (! renameTo.getText().isEmpty()) {
					NameChanger test = new NameChanger();
					x = test.inOrderRename(renameTo.getText(), (String) format.getSelectedItem(), getFolderDirectory());
					
					if (x > 0)
						complete = true;
				}
				
			}//end inorder button selected
			else {
				JOptionPane.showMessageDialog(null, "Enter what you want to rename the file to", "Error", JOptionPane.ERROR_MESSAGE);
			}//end else
			
			//this will only display if all the files were renamed
			if (complete) {
				JOptionPane.showMessageDialog(null, "renamed " + x + " files", "Complete", JOptionPane.INFORMATION_MESSAGE);
				
			}
		} );
		
		//this action listener will get the file directory
		directory.addActionListener( e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("directory = " + chooser.getSelectedFile().getAbsolutePath());
				setFolderDirectory(chooser.getSelectedFile().getAbsolutePath());
			}
		});
		
		
	}
	
	public static void main (String[] args) {
		
		JFrame frame = new JFrame("Rename Files");
		frame.setContentPane(new GUI().fileRenamer);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	
}
