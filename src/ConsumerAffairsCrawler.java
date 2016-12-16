
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ConsumerAffairsCrawler {

	private static final String homeLink = "https://www.consumeraffairs.com";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*Change the link and the file name while running with some other page in the same website */
		String link = "http://www.consumeraffairs.com/finance/community-tax.html?page=";
		Integer pageNo = 1;
		File file = new File("output/community-tax_consumeraffairs.tsv");

		// if file doesn't exist, then create it
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			while (pageNo < 7) {
				Document doc = Jsoup.connect(link + pageNo).timeout(0).userAgent("chrome").get();
				//				Elements full_review = doc.select("div[class = review]");

				//				Elements full_review = doc.select("div[class = non-brand-campaign clearfix]");
				Elements full_review = doc.select("div[class = entry review-text]");

				System.out.println(full_review.size());

				for (int i = 0; i < full_review.size(); i++) {
					try {

						//					String review_link = full_review.get(0).getElementsByTag("a").attr("href");

						//					Elements review_text = full_review.get(i).getElementsByClass(textClassName);
						//System.out.println(review_link);
						//				Document doc1 = Jsoup.connect(review_link).userAgent("chrome").timeout(0).get();
						//					Elements review_text = doc.select("div[class = non-brand-campaign clearfix]");

						bw.write(full_review.get(i).text() + "\n");

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
