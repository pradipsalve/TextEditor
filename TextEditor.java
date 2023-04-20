import javax.annotation.processing.FilerException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaring  proprties of texteditor
    JFrame frame;
    JMenuBar menuBar;

    JMenu file, edit;
    JMenuItem newfile, openfile,savefile;
    //edit menu item
    JMenuItem cut, copy, paste, selectAll,close;

    JTextArea textArea;
    TextEditor() {
        //initialize the frame;
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

            //set menubar  to frame
        file = new JMenu("File");
       edit = new JMenu("Edit");
            //initialize file menu item

        newfile =new JMenuItem("New Window");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");

        // add action listener to file menu item

         newfile.addActionListener(this);
         openfile.addActionListener(this);
         savefile.addActionListener(this);

         // Add menu item to file menu

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        //initialize edit menu item

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

         //adding actionlestner  to edit menu item

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

           //add menu to menubar

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

          //add menu to member

        menuBar.add(file);
        menuBar.add(edit);

        //set menubar to frame

        frame.setJMenuBar(menuBar);

          // create content panel

        JPanel panel= new JPanel();
        panel.setBorder(new EmptyBorder( 5, 5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add text area

        panel.add(textArea, BorderLayout.CENTER);

        //create scroll page

        JScrollPane scrollPane= new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll panel to panel
      panel.add(scrollPane);

        //add panel to frame
        frame.add(panel);

        //set dimension to frame;//
        frame.setBounds(100,100,400,400);
        frame.setTitle("TextEditor");
        frame.setVisible(true);
        frame.setLayout(null);


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent ){
      if(actionEvent.getSource()==cut){

          //perform cut opertion
          textArea.cut();
      }
      if(actionEvent.getSource()==copy){

          //perform copy operation
          textArea.copy();
      }
      if(actionEvent.getSource()==paste){

          //perform paste operation
          textArea.paste();
      }
      if(actionEvent.getSource()==selectAll){

          //perform selectall operation
          textArea.selectAll();
      }
      if(actionEvent.getSource()==close){
         System.exit(0 );
      }
      if(actionEvent.getSource()==openfile){
          JFileChooser fileChooser= new JFileChooser("c:");
          int chooseOption=fileChooser.showOpenDialog(null);
          if(chooseOption==JFileChooser.APPROVE_OPTION){

              // getting the selected file

            File file = fileChooser.getSelectedFile();
            //get the path of selected file
              String filePath= file.getPath();
              try{
                  //initialize file reader
                  FileReader fileReader = new FileReader(filePath);

                  //inintialize bufferreader
                  BufferedReader bufferedReader=  new BufferedReader(fileReader);
                  String intermediate ="",output ="";
                          //read containg of file line by line
                  while((intermediate = bufferedReader.readLine())!=null){
                      output+=intermediate+"\n";
                  }
                  // set the putpy=ut String
                  textArea.setText(output);
              }
              catch (IOException ioException){
                  ioException.printStackTrace();

              }
          }
      }
      if(actionEvent.getSource()==savefile){

          //initialize file picker
          JFileChooser fileChooser = new JFileChooser("c:");

          //get choose option fron file chooser
          int chooseOption = fileChooser.showSaveDialog(null);
          //check if we click on save button
          if(chooseOption ==JFileChooser.APPROVE_OPTION){

              //create a new file with chosen  directry path and name
              File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
              try{
                  //inintialize file reader
               FileWriter fileWriter= new FileWriter(file);
               //inintialize buffer writer
                  BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                  ////write contain od==f text area to file
                  textArea.write(bufferedWriter);
                  bufferedWriter.close();


              }
              catch(IOException ioException){
                  ioException.printStackTrace();

              }
          }
      }
      if(actionEvent.getSource()==newfile){
          TextEditor newtextEditor = new TextEditor();
      }
    }
    public static void main(String[] args) {
      TextEditor newtextEditor = new TextEditor();


        }
    }
