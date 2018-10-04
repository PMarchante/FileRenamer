import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI{
	
	private JButton clickMeButton;
	private JPanel fileRenamer;
	private JLabel Label;
	private JTextField renameTo;
	private JComboBox<String> format;
	private JButton directory;
	private JLabel newNameLabel;
	
	public String getFolderDirectory () {
		
		return folderDirectory;
	}
	
	public void setFolderDirectory (String folderDirectory) {
		
		this.folderDirectory = folderDirectory;
	}
	
	private String folderDirectory;
	public GUI () {
		
		format.setSelectedIndex(0);
		
		
			clickMeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed (ActionEvent e) {
					
					
					if (!renameTo.getText().isEmpty()) {
						NameChanger test = new NameChanger();
						test.fileLocation(renameTo.getText(), (String) format.getSelectedItem(), getFolderDirectory());
					}
					else {
						JOptionPane.showMessageDialog(null, "Enter what you want to rename the file to", "Error",JOptionPane.ERROR_MESSAGE);
					}//end else
				}
			});
		
		
		directory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed (ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					System.out.println("directory = "+ chooser.getSelectedFile().getAbsolutePath());
					setFolderDirectory(chooser.getSelectedFile().getAbsolutePath());
				}
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
