
    package CalcPack;

    import java.io.File;
    import java.util.List;
    import javax.swing.*;
    import javax.xml.parsers.DocumentBuilder;
    import javax.xml.parsers.DocumentBuilderFactory;
    import javax.xml.transform.OutputKeys;
    import javax.xml.transform.Transformer;
    import javax.xml.transform.TransformerFactory;
    import javax.xml.transform.dom.DOMSource;
    import javax.xml.transform.stream.StreamResult;

    import org.w3c.dom.*;

    /**
     * @author Allex
     *
     * Класс для создания xml документа
     *
     * MakeXML(String path, List<String> lst) - создание документа xml и добавление в него результатов расчетов
     */
    public class xmlClass {

        public static void MakeXML(String path, List<String> lst){
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                dbFactory.setValidating(true);
                Document docc = dbFactory.newDocumentBuilder().newDocument();
                Element root = docc.createElement("MathRoot");
                docc.appendChild(root);

                for(String cur : lst){
                    Element new_node = docc.createElement("Math");
                    new_node.setTextContent(cur);
                    root.appendChild(new_node);
                }


                File file = new File(path);
                if(file.exists())file.delete();


                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(docc), new StreamResult(file));


            } catch (Exception ex) {};
            }
        }





