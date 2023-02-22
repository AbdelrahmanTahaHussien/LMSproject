import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilesManipulations {

    public static void replace_Dollar(String filePath) throws FileNotFoundException, IOException {
    File inFile =new File(filePath);
    List<String> output = new ArrayList<>();
    BufferedReader reader = null;
    BufferedWriter writer = null;
    try {
        reader = new BufferedReader(new FileReader(inFile));
        String line;

        while((line = reader.readLine()) != null){
            line=line.replaceAll("[$]", "\n");
            output.add(line);

        }

        reader.close();

        writer = new BufferedWriter(new FileWriter(new File(".\\Data\\Students_Data.csv")));
        for (String s : output) {

            writer.write(s);
            writer.newLine();

        }

        writer.flush();
        writer.close();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    public   static void replace_Hash_Add_Id(String filePath) throws FileNotFoundException, IOException{
        File inFile =new File(filePath);
        List<String> output = new ArrayList<>();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inFile));
            String line;
            int id=0;
            while((line = reader.readLine()) != null){
                if (id==0) {
                    id++;
                    line=line.replaceAll("# ", " ");
                    line =line.replaceAll("#", ",");
                    output.add("id,"+line);
                    continue;
                }

                line=line.replaceAll("# ", " ");
                line =line.replaceAll("#", ",");
                output.add(id++ +","+line);

            }

            reader.close();

            writer = new BufferedWriter(new FileWriter(new File(".\\Data\\Students_Data.csv")));
            for (String s : output) {

                writer.write(s);
                writer.newLine();

            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void convert_Xml_To_Csv(String path) throws ParserConfigurationException, SAXException, IOException, TransformerException {


        File stylesheet = new File(".\\Data\\style.xsl");
        File xmlSource = new File(path);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder=factory.newDocumentBuilder();
        Document document = builder.parse(xmlSource);

        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File(".\\Data\\Courses_Data.csv"));
        transformer.transform(source, outputTarget);

    }



}
