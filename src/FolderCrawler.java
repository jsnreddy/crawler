import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FolderCrawler {

	public static void listFilesForFolder(File folder, BufferedWriter bw) {
		System.out.println("test1");
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry, bw);
			} else {
				BufferedReader br;

				try {
					System.out.println("test2");
					br = new BufferedReader(new FileReader(fileEntry));
					String s;
					String review = "";
					while ((s = br.readLine()) != null) {
						System.out.println("test3");
						review = review + s;
					}
					bw.write(review + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File folder = new File("/home/abzooba/work/workspace/Crawler/output");
		File file = new File("allReviews.tsv");
		//		File folder = new File("/home/abzooba/test");
		//		File file = new File("testOutput.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			listFilesForFolder(folder, bw);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
