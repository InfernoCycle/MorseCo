import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.*;

public class App implements ActionListener {
    private static String [] values = null;
    private static String [] keys = null;
    final private static int maxMorse = 79;
    private static JButton convertBtn = null;
    private static JRadioButton radioButton1;
    private static JRadioButton radioButton2;
    private static JTextArea output;
    private static ButtonGroup btnGrp;
    private static JTextArea textA;

    private static boolean doesntExist = false;

    /*public void setValues(String [] arr){
        this.values = arr;
    }

    public void setKeys(String [] arr){
        this.keys = arr;
    }*/

    public static void start() throws IOException {
        BufferedReader br = null;
        String txt = "";
        String [] splitStuff = null;
        String [] morseKey = new String [maxMorse];
        String [] morseValue = new String [maxMorse];
        int index = 0;
        try{
            br = new BufferedReader(new FileReader("./src/randoFiles/morse.txt"));
            while((txt = br.readLine()) != null && (index < morseKey.length)){
                splitStuff = txt.split(" ");
                morseKey[index] = splitStuff[0];
                morseValue[index] = splitStuff[1];
                index++;
            }
            br.close();
        }catch(Exception e){
            doesntExist = true;
            StringBuilder build = new StringBuilder();
            for(int k = 0; k < defaultMorse().length(); k++){
                if(defaultMorse().charAt(k) == '\n'){
                    splitStuff = build.toString().split(" ");
                    morseKey[index] = splitStuff[0];
                    morseValue[index] = splitStuff[1];
                    build.delete(0, build.length());
                    index++;
                    continue;
                }
                build.append(defaultMorse().charAt(k));
            }
        }





        //.out.println("Running for test runs");

        //System.out.println(obj.MorseToText(value));
        keys = morseKey;
        values = morseValue;
    }
    public static void main(String [] args) throws IOException {
        start();

        /*Fonts*/
        Font f1 = new Font("Times New Roman", Font.BOLD, 14);

        JFrame frame = new JFrame("Con's Morse Translator");
        frame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        frame.setBounds(120,130, 320,330);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnGrp = new ButtonGroup();

        radioButton1 = new JRadioButton("Text to Morse");
        radioButton1.setSelected(true);
        radioButton1.setFont(f1);
        radioButton1.addActionListener(new App());

        radioButton2 = new JRadioButton("Morse to Text");
        radioButton2.setFont(f1);
        radioButton2.addActionListener(new App());

        btnGrp.add(radioButton1);
        btnGrp.add(radioButton2);

        JLabel labelM = new JLabel("Enter Text Here: ");

        textA = new JTextArea(5, 20);
        textA.setFont(f1);
        textA.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textA);

        output = new JTextArea(5, 20);
        output.setFont(f1);
        output.setForeground(Color.BLACK);
        output.setLineWrap(true);
        output.setEnabled(true);
        JScrollPane scrollPane1 = new JScrollPane(output);
        //textA.setBackground(Color.cyan);

        convertBtn = new JButton("Convert");
        convertBtn.setSize(100, 30);
        convertBtn.addActionListener(new App());

        frame.setResizable(false);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,0,5,0);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(radioButton1);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(radioButton2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(labelM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(convertBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(scrollPane1, gbc);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private StringBuilder MorseToText(String morse){
        StringBuilder builder = new StringBuilder();
        if(!morse.equals("")){
            int index = 0;

            String spliter [] = morse.split(" ");

            for(int i = 0; i < spliter.length; i++){
                try{
                    if(spliter[i].charAt(0) == '/'){
                        builder.append(" ");
                        continue;
                    }
                }catch (StringIndexOutOfBoundsException e){
                    continue;
                }
                index = 0;
                for(int k = 0; k < values.length; k++){
                    try{
                        if(spliter[i].equals(values[k])){
                            builder.append(keys[index]);
                        }
                    }catch (Exception e){
                        e.getStackTrace();
                    }
                    index++;
                }
            }
            return builder;
        }

        return builder;
    }

    private StringBuilder TextToMorse(String text){
        StringBuilder builder = new StringBuilder();
        if(!text.equals("")){
            int index = 0;
            for(int i = 0; i < text.length(); i++){
                if(text.charAt(i) == ' '){
                    builder.append(" / ");
                    continue;
                }
                index = 0;
                for(int k = 0; k < keys.length; k++){
                    if(doesntExist){
                        try{
                            if(text.toUpperCase().charAt(i) == keys[k].charAt(0)){
                                builder.append(values[index].toString() + " ");
                                //System.out.println(this.values[index]);
                            }
                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }
                    else{
                        try{
                            if(text.toString().charAt(i) == keys[k].charAt(0)){
                                builder.append(values[index].toString() + " ");
                                //System.out.println(this.values[index]);
                            }
                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }

                    index++;
                }
            }
            return builder;
        }
        return builder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*Convert button section*/
        if(e.getSource() == convertBtn){
            if(radioButton1.isSelected()){
                output.setText(TextToMorse(textA.getText().trim()).toString());
            }
            if(radioButton2.isSelected()){
                output.setText(MorseToText(textA.getText().trim()).toString());
            }
        }
    }

    public static String defaultMorse(){
        return "A .-\n" +
                "B -...\n" +
                "C -.-.\n" +
                "D -..\n" +
                "E .\n" +
                "F ..-.\n" +
                "G --.\n" +
                "H ....\n" +
                "I ..\n" +
                "J .---\n" +
                "K -.-\n" +
                "L .-..\n" +
                "M --\n" +
                "N -.\n" +
                "O ---\n" +
                "P .--.\n" +
                "Q --.-\n" +
                "R .-.\n" +
                "S ...\n" +
                "T -\n" +
                "U ..-\n" +
                "V ...-\n" +
                "W .--\n" +
                "X -..-\n" +
                "Y -.--\n" +
                "Z --..\n" +
                "  -.-.-.-\n" +
                ", --..--\n" +
                ": ---...\n" +
                "; -.-.-.\n" +
                ". .-.-.-\n" +
                "\" .-..-.\n" +
                "( -.--.\n" +
                ") -.--.-\n" +
                "' .----.\n" +
                "1 .----\n" +
                "2 ..---\n" +
                "3 ...--\n" +
                "4 ....-\n" +
                "5 .....\n" +
                "6 -....\n" +
                "7 --...\n" +
                "8 ---..\n" +
                "9 ----.\n" +
                "0 -----\n" +
                "/ -..-.\n" +
                "? ..--..\n" +
                "- -....-\n" +
                "_ ..--.-\n" +
                "= -...-\n" +
                "& .-...\n" +
                "[ -.--.\n" +
                "] -.--.-";
    }
}

