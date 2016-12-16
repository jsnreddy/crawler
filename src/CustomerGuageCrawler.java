
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CustomerGuageCrawler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*Change the link and the file name while running with some other page in the same website */
		String link = "https://reviews.customergauge.com/h-r-block-india/?page=";
		Integer pageNo = 1;
		File file = new File("output/hrblock_customerguage.tsv");

		// if file doesn't exist, then create it
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			while (pageNo <= 910) {
				Document doc = Jsoup.connect(link + pageNo).timeout(0).userAgent("chrome").get();
				//				Elements full_review = doc.select("div[class = review]");
				Elements full_review = doc.select("div[class = review-content]");
				//				Elements full_review = doc.select("p");
				System.out.println(full_review.size());

				for (int i = 0; i < full_review.size(); i++) {
					try {
						String review = full_review.get(i).text();
						review = review.replace("Report for abuse", " ");
						if (!(review.equals(" ") || review.equals("."))) {
							bw.write(review + "\n");
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				System.out.println("Done PageNo : " + pageNo);
				pageNo++;
				Thread.sleep(4 * 1000);
			}
			bw.close();
			//			Elements nextPage = doc.select("a[class = next]");
			//			String nextPageLink = nextPage.get(0).getElementsByTag("a").attr("href");
			//			System.out.println("nextPageLink : " + nextPageLink);
			System.out.println("Done");
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
