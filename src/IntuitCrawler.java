import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class IntuitCrawler {
	private static final String homeLink = "https://turbotax.intuit.com/reviews/";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("output/turbotax-intuit_consumeraffairs.tsv");
		// if file doesn't exist, then create it

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			Document doc = Jsoup.connect(homeLink).timeout(0).userAgent("chrome").get();
			Elements pros = doc.select("span[class = BVRRValue BVRRReviewPros]");
			Elements cons = doc.select("span[class = BVRRValue BVRRReviewCons]");
			Elements review = doc.select("span[class = BVRRReviewText]");

			System.out.println("Review size : " + review.size());
			for (int i = 0; i < review.size(); ++i) {
				bw.write(pros.get(i) + "\t");
				bw.write(cons.get(i) + "\t");
				bw.write(review.get(i) + "\n");
			}

			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
