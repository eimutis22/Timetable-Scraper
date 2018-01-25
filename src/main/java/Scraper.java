import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.*;

public class Scraper {

    public static void main(String[] args) throws Exception {
        //ScrapeText();
        ScrapeHTML();
        //SplitIntoBlocks();
    }


    public static void SplitIntoBlocks() throws Exception {

        final Document document = Jsoup.connect("http://bit.ly/2BtqnF7").get();
        String ttOut = "";

        for(Element row : document.select("table tr")) {
            final String rows = row.select("td").html();

            ttOut += rows;
        }
        ttOut = ttOut.substring(ttOut.indexOf("<!-- START OBJECT-CELL -->"), ttOut.lastIndexOf("<!-- END OBJECT-CELL -->"));

        String[] parts = ttOut.split("<!-- START OBJECT-CELL -->");

        try {
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].substring(0, parts[i].indexOf("<!-- END OBJECT-CELL -->"));
            }
        } catch(Exception err){
            System.out.print("***** ERROR --> " + err.getMessage());
        }

        Integer x = 5;

        String sampleOutput = parts[x] = parts[x].substring(0,parts[x].indexOf("<!-- END OBJECT-CELL -->"));
        System.out.print(sampleOutput);



        String pattern = "\\w\\d{4}";
        if (sampleOutput.matches(pattern))
        {
            System.out.print("***** Matches *****");
        }
        //System.out.print("Number of cells: " + parts.length); // 46 cells ???
        //System.out.print(parts[2]);
    }




    // Scraped HTML -   <!-- START OBJECT-CELL -->     <!-- END OBJECT-CELL -->
    public static void ScrapeHTML() throws Exception {
        final Document document = Jsoup.connect("http://bit.ly/2zexx0L").get();
        String ttOut = "";

        for(Element row : document.select("table tr")) {
            final String rows = row.select("td").html();

            ttOut += rows;
        }
        ttOut = ttOut.substring(ttOut.indexOf("<!-- START OBJECT-CELL -->"), ttOut.lastIndexOf("<!-- END OBJECT-CELL -->"));
        System.out.print(ttOut);
    }




    // Method for scraping timetable text - Between "Mon" and first "Print Timetable"
    public static void ScrapeText() throws Exception {
        final Document document = Jsoup.connect("http://bit.ly/2zexx0L").get();
        String ttOut = "";

        for(Element row : document.select("table tr")) {
            final String rows = row.select("td").text();
            final String rows2 = row.select("td table tbody td").text();
            final String rows3 = row.select("font").text();
            final String test = row.select("tr+ tr td").text();

            //  can work with the colspan to sort slots

            ttOut += rows;
            //System.out.println(rows);
            //System.out.println(rows);
        }

        // Between "Mon" and first "Print Timetable"
        ttOut = ttOut.substring(ttOut.indexOf("Mon"), ttOut.indexOf("Print Timetable"));
        System.out.print(ttOut);
        //System.out.print("Mon ----> " + ttOut.indexOf("Mon"));
        //System.out.print("Print Timetable ----> " + ttOut.indexOf("Print Timetable"));
    }
}

