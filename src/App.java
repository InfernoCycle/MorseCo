import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.*;
import javax.swing.border.Border;

public class App implements ActionListener {
    private static String [] values = null;
    private static String [] keys = null;
    final private static int maxMorse = 105;
    private static JButton convertBtn = null;
    private static JRadioButton radioButton1;
    private static JRadioButton radioButton2;
    private static JTextArea output;
    private static ButtonGroup btnGrp;
    private static ButtonGroup btnGrp2;
    private static JTextArea textA;
    private static JRadioButton g2r1, g2r2;

    private static boolean doesntExist = false;

    /*public void setValues(String [] arr){
        this.values = arr;
    }

    public void setKeys(String [] arr){
        this.keys = arr;
    }*/

    //set and map default characters to their morse
    public static void start() {
        BufferedReader br = null;
        String txt = "";
        String [] splitStuff = null;
        String [] morseKey = new String [maxMorse];
        String [] morseValue = new String [maxMorse];
        int index = 0;
        doesntExist = true;
        StringBuilder build = new StringBuilder();
        String one = defaultMorse();
        for(int k = 0; k < one.length(); k++){
            if(one.charAt(k) == '\n'){
                splitStuff = build.toString().split(" ");
                morseKey[index] = splitStuff[0].toLowerCase();
                morseValue[index] = splitStuff[1];
                build.delete(0, build.length());
                index++;
                continue;
            }
            build.append(one.charAt(k));
        }
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
        GridBagConstraints gbc2 = new GridBagConstraints();

        frame.setBounds(120,130, 320,330);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel btnPanels = new JPanel();
        btnPanels.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
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
        panel1.add(radioButton1, BorderLayout.WEST);
        panel1.add(radioButton2, BorderLayout.EAST);

        btnPanels.add(panel1, BorderLayout.NORTH);

        //-----group 2 ------
        btnGrp2 = new ButtonGroup();

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        g2r1 = new JRadioButton("Regular");
        g2r1.setSelected(true);
        g2r1.setFont(f1);
        g2r1.addActionListener(new App());

        g2r2 = new JRadioButton("Custom");
        g2r2.setFont(f1);
        g2r2.addActionListener(new App());

        btnGrp2.add(g2r1);
        btnGrp2.add(g2r2);
        panel2.add(g2r1, BorderLayout.WEST);
        panel2.add(g2r2, BorderLayout.EAST);

        btnPanels.add(panel2, BorderLayout.SOUTH);

        JLabel labelM = new JLabel("Enter Text Here: ");

        textA = new JTextArea(5, 20);
        textA.setFont(f1);
        textA.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textA);

        JPanel convertPanel = new JPanel();
        convertPanel.setLayout(new BorderLayout());
        convertBtn = new JButton("Convert");
        convertBtn.setSize(100, 30);
        convertBtn.addActionListener(new App());
        convertPanel.add(convertBtn, BorderLayout.CENTER);

        output = new JTextArea(5, 20);
        output.setFont(f1);
        output.setForeground(Color.BLACK);
        output.setLineWrap(true);
        output.setEnabled(true);
        JScrollPane scrollPane1 = new JScrollPane(output);
        //textA.setBackground(Color.cyan);

        frame.setResizable(false);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth=2;
        gbc.gridheight=2;
        panel.add(btnPanels, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        panel.add(scrollPane1, gbc);

        gbc.insets = new Insets(1, 0,1,0);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(convertPanel, gbc);

        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    //convert morse code to text and then display on app
    private StringBuilder MorseToText(String morse, boolean mode){
        StringBuilder builder = new StringBuilder();
        if(!morse.equals("")){
            int index = 0;

            String spliter [] = morse.split(" ");

            //for entire string typed
            for(int i = 0; i < spliter.length; i++){
                try{

                    //checks for spaces and sends out letter when necessary
                    if(mode) {
                        if(spliter[i].charAt(0) == '/' || spliter[i].charAt(0) == ' ' || spliter[i].equals(".......")) {
                            builder.append(" ");
                            continue;
                        }
                    }else{
                        if(spliter[i].charAt(0) == '/' || spliter[i].charAt(0) == ' ') {
                            builder.append(" ");
                            continue;
                        }
                    }
                }catch (StringIndexOutOfBoundsException e){
                    continue;
                }
                index = 0;

                //for none spaced
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

    private StringBuilder TextToMorse(String text, boolean mode){
        StringBuilder builder = new StringBuilder();
        if(!text.equals("")){
            int index = 0;
            for(int i = 0; i < text.length(); i++){

                //check for spaces first
                if(mode){
                    if(text.charAt(i) == ' '){
                        builder.append(" ....... ");
                        continue;
                    }
                }else{
                    if(text.charAt(i) == ' '){
                        builder.append(" / ");
                        continue;
                    }
                }

                index = 0;
                for(int k = 0; k < keys.length; k++){
                    if(doesntExist){
                        try{
                            if(text.charAt(i) == keys[k].charAt(0)){
                                builder.append(values[index] + " ");
                            }
                        }catch (Exception e){
                            e.getStackTrace();
                        }
                    }
                    else{
                        try{
                            if(text.charAt(i) == keys[k].charAt(0)){
                                builder.append(values[index] + " ");
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

    private void handler(boolean mode){
        if(radioButton1.isSelected()){
            output.setText(TextToMorse(textA.getText().trim().toLowerCase(), mode).toString());
        }
        if(radioButton2.isSelected()){
            output.setText(MorseToText(textA.getText().trim().toLowerCase(), mode).toString());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /*Convert button section*/
        if(e.getSource() == convertBtn){
            if(g2r1.isSelected()){
                this.handler(false);
            }else if(g2r2.isSelected()){
                this.handler(true);
            }
        }
    }

    public static String defaultMorse(){
        return "A .-\n" + "B -...\n" + "C -.-.\n" + "D -..\n" + "E .\n" + "F ..-.\n" +
                "G --.\n" + "H ....\n" + "I ..\n" + "J .---\n" + "K -.-\n" + "L .-..\n" +
                "M --\n" + "N -.\n" + "O ---\n" + "P .--.\n" + "Q --.-\n" + "R .-.\n" +
                "S ...\n" + "T -\n" + "U ..-\n" + "V ...-\n" + "W .--\n" + "X -..-\n" +
                "Y -.--\n" + "Z --..\n" +

                "  -.-.-.-\n" + ", --..--\n" + ": ---...\n" + "; -.-.-.\n" + ". .-.-.-\n" +
                "\" .-..-.\n" + "( -.--.\n" + ") -.--.-\n" + "' .----.\n" + "1 .----\n" +
                "2 ..---\n" + "3 ...--\n" + "4 ....-\n" + "5 .....\n" + "6 -....\n" +
                "7 --...\n" + "8 ---..\n" + "9 ----.\n" + "0 -----\n" + "! ..--.\n" +
                "? ..--..\n" + "- -....-\n" + "_ ..--.-\n" + "= -...-\n" +
                "% .--..\n" + "@ .--.-.\n" + "& .-...\n";
    }
}

