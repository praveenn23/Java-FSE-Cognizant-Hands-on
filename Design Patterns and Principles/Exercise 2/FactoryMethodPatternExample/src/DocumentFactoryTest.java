public class DocumentFactoryTest {

    public static void main(String[] args) {

        
        System.out.println("  Factory Method Pattern - Document Test");
        

        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };

        for (DocumentFactory factory : factories) {
            factory.openDocument();
        }

        System.out.println(" Direct Document Access Test ");
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document doc = wordFactory.createDocument();
        System.out.println("Document type: " + doc.getType());
        doc.open();
        doc.save();
        doc.close();

        
        System.out.println("  All Tests Passed.");
        
    }
}
