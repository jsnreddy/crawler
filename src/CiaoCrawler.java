
/*******************************************************
 * 			@author sudhanshu.kumar@abzooba.com
 * 			26-Nov-2015 10:42:00 am 
 * 			ciao.es crawling
 *******************************************************/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CiaoCrawler {

	/*************************************************************************
	   IMP -- use timer before crawling data, otherwise they may block the IP
	   remember u have to make changes at three places namely, output file name, 
	   URL and the iteration of for loop for the no. of pages of review.
	 * @throws InterruptedException 
	**************************************************************************/

	public static void main(String[] args) throws Exception {

		int pageNo = 1;
		File file = new File("ciao_Vodafone__335731.tsv");

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);

		for (pageNo = 0; pageNo <= 735; pageNo += 15) {

			Document doc = Jsoup.connect("http://www.ciao.es/Opiniones/Vodafone__335731/Start/" + pageNo).timeout(0).userAgent("chrome").get();
			Elements full_review = doc.select("p[class = e-reem-seemore]");

			//			System.out.println(doc);
			//			Elements author = doc.select("p[class = m-reem-uname]");
			//			Elements content = doc.select("div[class = m-reem-content]");
			//			Elements title = doc.select("p[class = m-reem-rrating]");
			//			Elements heading = doc.select("div[class = m-reem-rname]");
			//			Elements proscons = doc.select("div[class = m-reem-proscons]");
			//			Elements review = doc.select("div[class = m-reem-rtext]");

			System.out.println(full_review.size());
			for (int i = 0; i < full_review.size(); i++) {
				try {

					String review_link = full_review.get(i).getElementsByTag("a").attr("href");
					//System.out.println(review_link);
					Document doc1 = Jsoup.connect(review_link).userAgent("chrome").timeout(0).get();
					Elements review_text = doc1.select("div[class = reviewText]");

					bw.write(review_text.get(0).text() + "\n");

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Done page: " + pageNo);
			Thread.sleep(3 * 1000);
		}
		bw.close();
		System.out.println("Done");
	}

}