import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menubar;

    JMenu file, edit;

    // menuitems of file
     JMenuItem newfile, openfile, savefile;
    // menuitems of edit
     JMenuItem cut, copy, paste, selectAll, close;

     JTextArea textarea;
    TextEditor(){
        // step1: created window by initiating JFrame
      frame = new JFrame();
      // step 2 : create menuBar using JMenuBar
        menubar = new JMenuBar();

      // step 3 : create menus using JMenu
        file = new JMenu("file");
        edit = new JMenu("edit");

      // step 4 : create menuitems of file and edit
      newfile = new JMenuItem("new File");;
      openfile = new JMenuItem("open File");
      savefile = new JMenuItem("save file");


      // adding actionListener to file menu
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

      // adding actionlistener to file menus
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);


      cut = new JMenuItem("cut");
      copy = new JMenuItem("copy");
      paste = new JMenuItem("paste");
      selectAll = new JMenuItem("select All");
      close = new JMenuItem("close");


      // adding actionlistener to edit menus
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

      // initiating the textarea
        textarea = new JTextArea();
      // addind menuitems to menus
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // adding menus to menubar
        menubar.add(file);
        menubar.add(edit);

      // setting menubar to frame
        frame.setJMenuBar(menubar);


        // adding textarea to frame
        frame.add(textarea);

      // setting bounds of JFrame
      frame.setBounds(0, 0, 400, 400);
      frame.setVisible(true);
      frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()== cut){
            // cut operation using textArea predefined class
            textarea.cut();
        }
        if(actionEvent.getSource()== copy){
            textarea.copy();
        }
        if(actionEvent.getSource() == paste){
            textarea.paste();
        }
        if(actionEvent.getSource()== selectAll){
            textarea.selectAll();
        }
        if(actionEvent.getSource()== close){
            System.exit(0);
        }
        if(actionEvent.getSource()== openfile){
            // open a file chooser
            JFileChooser fileChooser = new JFileChooser("c:");
            int chooseOption = fileChooser.showOpenDialog(null);

            // if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // getting selected file
                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filePath = file.getPath();
                try{
                    // initialise file reader
                    FileReader fileReader = new FileReader(filePath);
                    // initialize buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // read contents of file line by line
                    while((intermediate = bufferedReader.readLine())!= null){
                        output += intermediate+"\n";
                    }
                    // set the output string to textArea
                    textarea.setText(output);
                }catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        // save file
        if(actionEvent.getSource() == savefile){
            // initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+" .txt");

                try{
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    // initialize Buffered Writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write contents of text area to file
                    textarea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        // new File
        if(actionEvent.getSource() == newfile){
           TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args){
        TextEditor obj = new TextEditor();
    }

}