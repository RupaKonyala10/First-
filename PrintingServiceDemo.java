import java.io.FileInputStream;
import java.io.IOException;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;
import javax.print.event.*;

public class PrintingServiceDemo {
   public static void main(String[] args) throws PrintException,
         IOException{

      PrintService ps=PrintServiceLookup.lookupDefaultPrintService();
      DocPrintJob job=ps.createPrintJob();
      job.addPrintJobListener(new PrintJobAdapter() {
      public void printDataTransferCompleted(PrintJobEvent event){
         System.out.println("data transfer complete");
      }
      public void printJobNoMoreEvents(PrintJobEvent event){
            System.out.println("received no more events");
         }
      });
      FileInputStream fis=new FileInputStream("D:/JAVA CODE/PrintingServiceDemo.java");
      Doc doc=new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
      // Doc doc=new SimpleDoc(fis, DocFlavor.INPUT_STREAM.JPEG, null);
      PrintRequestAttributeSet attrib=new HashPrintRequestAttributeSet();
      attrib.add(new Copies(1));
      job.print(doc, attrib);
   }
}