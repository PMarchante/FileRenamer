import javafx.stage.FileChooser;


import javax.swing.*;
import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

//class that will look in a specified folder and change the name of all files in it
public class NameChanger {
	
	public void fileLocation(String changeToName, String fileFormat, String fileDirectory){
		
		
		
		String folderPath = fileDirectory;
		File myFolder = new File(folderPath);
		
		File[] fileArray = myFolder.listFiles();
		StringBuilder sb = new StringBuilder();
		sb.append(changeToName).append(" E");
		
		try {
			for (int x = 0 ; x < fileArray.length ; x++) {
				char episodeNum;
				boolean doubleDigit = false;
				
				if (fileArray[x].isFile()) {
					File myFile = new File(folderPath + "\\" + fileArray[x].getName());
					
					String editedName = fileArray[x].getName();
					
					editedName = editedName.replaceAll("[^sSeE0-9]", "");
					
					//System.out.println("the edited name " + editedName);
					char[] episodeFinder = editedName.toCharArray();
					
					for (int i = 0 ; i < episodeFinder.length ; i++) {
						//episode labeler 1-9
						if ((episodeFinder[i] == 'e' || episodeFinder[i] == 'E') && episodeFinder[i + 1] == '0') {
							episodeNum = episodeFinder[i + 2];
							sb.append(episodeNum);
							break;
						}
						
						//handles double digits
						if ((episodeFinder[i] == 'e' || episodeFinder[i] == 'E') && episodeFinder[i + 1] == '1') {
							doubleDigit = true;
							String doubleDigitEpisode = "";
							Character tmp = episodeFinder[i + 1];
							Character tmp2 = episodeFinder[i + 2];
							doubleDigitEpisode = doubleDigitEpisode.concat(tmp.toString()).concat(tmp2.toString());
							sb.append(doubleDigitEpisode);
							break;
							
						}
					}//end episode finder for
					
				
					
					
					//this is what actually renames the file
					myFile.renameTo(new File(folderPath + "\\" + sb.toString() + fileFormat));
					if (doubleDigit) {
						sb.deleteCharAt(sb.length() - 1);
						sb.deleteCharAt(sb.length() - 1);
						doubleDigit = false;
					}
					else
						sb.deleteCharAt(sb.length() - 1);
					
				}//end if
			}//end for
			
		}//end try
		catch (java.lang.ArrayIndexOutOfBoundsException i){
			JOptionPane.showMessageDialog(null, "Make sure the episode format is Exx, 2 digit episode numbers", "Alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}//end fileLocation
	
	
	
}//end class NameChanger
