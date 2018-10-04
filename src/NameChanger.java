import javax.swing.*;
import java.io.File;

//class that will look in a specified folder and change the name of all files in it
//IT WILL NOT RENAME A FILE IF THEY ALL HAVE SAME NAME
public class NameChanger {
	
	public int fileLocation (String changeToName, String fileFormat, String fileDirectory) {
		int totalFilesRenamed =0;
		
		String folderPath = fileDirectory;
		File myFolder = new File(folderPath);
		
		File[] fileArray = myFolder.listFiles();
		StringBuilder sb = new StringBuilder();
		sb.append(changeToName).append(" E");
		
		try {
			if (fileArray !=null) {
				for (File x : fileArray) {
					char episodeNum;
					boolean doubleDigit = false;
					//gets the files, and edits the name to remove all letters except sS and eE also does not remove numbers
					if (x.isFile()) {
						File myFile = new File(folderPath + "\\" + x.getName());
						
						String editedName = x.getName();
						
						editedName = editedName.replaceAll("[^sSeE0-9]", "");
						
						
						char[] episodeFinder = editedName.toCharArray();
						
						for (int i = 0 ; i < episodeFinder.length ; i++) {
							if ((episodeFinder[i] == 'e' || episodeFinder[i] == 'E') && episodeFinder[i + 1] == '0') {
								episodeNum = episodeFinder[i + 2];
								sb.append(episodeNum);
								//break;
							}
							
							//handles double digits
							if ((episodeFinder[i] == 'e' || episodeFinder[i] == 'E') && (episodeFinder[i + 1] == '1' || episodeFinder[i + 1] == '2')) {
								doubleDigit = true;
								String doubleDigitEpisode = "";
								Character tmp = episodeFinder[i + 1];
								Character tmp2 = episodeFinder[i + 2];
								doubleDigitEpisode = doubleDigitEpisode.concat(tmp.toString()).concat(tmp2.toString());
								sb.append(doubleDigitEpisode);
								//break;
								
							}
							
							
						}//end episode finder for
						
						//this is what actually renames the file
						if (myFile.renameTo(new File(folderPath + "\\" + sb.toString() + fileFormat))){
							totalFilesRenamed++;
						}
						
						if (doubleDigit) {
							sb.deleteCharAt(sb.length() - 1);
							sb.deleteCharAt(sb.length() - 1);
							doubleDigit = false;
						}
						else
							sb.deleteCharAt(sb.length() - 1);
						
					}//end if
				}//end for
			}//end if making sure file array is not null
		}//end try
		//will display an error if the format for finding the episode number is incorrect
		catch (java.lang.ArrayIndexOutOfBoundsException i) {
			JOptionPane.showMessageDialog(null, "Make sure the episode format is Exx, 2 digit episode numbers", "Alert",
					JOptionPane.ERROR_MESSAGE);
		}
		return totalFilesRenamed;
	}//end fileLocation method

	
}//end class NameChanger
