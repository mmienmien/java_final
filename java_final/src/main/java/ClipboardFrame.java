import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.Scanner;

public class ClipboardFrame  extends JFrame implements ClipboardHandler.EntryListener {
    private final JTextArea text;
    private static Clipboard clip=Toolkit.getDefaultToolkit().getSystemClipboard();
    private final Scanner input=new Scanner(System.in);
    //build frame & register listener
    public ClipboardFrame(){
        text=new JTextArea("aaaa");
        text.setFont(new Font("Consolos", Font.PLAIN, 20));
        add(text);
        this.setSize(500,500);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        ClipboardHandler handler=new ClipboardHandler();
        handler.setEntryListener(this);
        handler.run();
    }

    public void copyToClipboard(String val){
        Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection data=new StringSelection(val);
        clipboard.setContents(data,data);
    }
    @Override
    public void onCopy(String data){
        try {
            data=Translator.translate(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        text.setText(data);
    }


    public static  void main(String[] args) {
        ClipboardFrame test=new ClipboardFrame();
        GlobalListener.preAssignment();

    }


}
