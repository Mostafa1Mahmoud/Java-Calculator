package com.GUI_Project;

import com.sun.net.httpserver.Headers;
import com.sun.source.tree.Tree;

import javax.naming.spi.DirectoryManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Layer extends JPanel {
    private final int  panelraw=780;
    private final int  buttonraw=100;
    private final int  butonhight=100;
    private Operate Input = new Operate();
        public Layer(){

            setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

            JPanel Headbar=new JPanel();
            Headbar.setBackground(Color.gray);
            Headbar.setPreferredSize(new Dimension(1000,20));
            Headbar.setLayout(new BoxLayout(Headbar,BoxLayout.X_AXIS));

            JLabel label1 =new JLabel("File");
            JLabel label2=new JLabel("Help");
            Headbar.add(label1);
            Headbar.add(Box.createRigidArea(new Dimension(10,0)));
            Headbar.add(label2);
            Headbar.add(Box.createRigidArea(new Dimension(1000,0)));

            JPanel Bluepanel0=new JPanel();
            Bluepanel0.setPreferredSize(new Dimension(1000,30));
            Bluepanel0.setBackground(Color.gray);

            JPanel Bluepanel1=new JPanel();
            Bluepanel1.setPreferredSize(new Dimension(1000,30));
            Bluepanel1.setBackground(Color.gray);

            JPanel Bluepanel2=new JPanel();
            Bluepanel2.setPreferredSize(new Dimension(1000,30));
            Bluepanel2.setBackground(Color.gray);
            Bluepanel2.setLayout(new BoxLayout(Bluepanel2,BoxLayout.X_AXIS));



            JPanel TextField=new JPanel();
            TextField.setPreferredSize(new Dimension(800,30));

            JTextField TextArea =new JTextField();
            TextArea.setPreferredSize(new Dimension(800,30));
            TextField.add(TextArea);
            TextArea.addKeyListener(new KeyListener() {
                int pointFlag=0;
                @Override
                public void keyTyped(KeyEvent e) {
                }
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        try{
                            Input.Result(TextArea.getText());
                            TextArea.setText(Input + "=" + Input.Result());
                        }
                        catch (IllegalArgumentException exception){
                            TextArea.setText("Math Error");
                        }
                        catch (OverflowException exception){
                            TextArea.setText(TextArea.getText()+" is Too Big Value to be handled");
                        }
                    }
                    if ((e.getKeyChar() >= '!' && e.getKeyChar() <= '9')||e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_SHIFT||e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
                        TextArea.setEditable(true);
                    } else {
                        TextArea.setEditable(false);
                    }
                    if(e.getKeyCode()==KeyEvent.VK_SHIFT){
                        if(Input.Result() == 0.0){
                            TextArea.setText("");
                        }else {
                            TextArea.setText(Double.toString(Input.Result()));
                        }
                    }
                    if(e.getKeyChar()=='.'){
                        if(pointFlag==0){
                            TextArea.setEditable(true);
                            pointFlag = 1;
                        }else {
                            TextArea.setEditable(false);
                        }
                    }
                     /*if(e.getKeyChar()=='+'||e.getKeyChar()=='-'||e.getKeyChar()=='*'||e.getKeyChar()=='/'||e.getKeyChar()=='!'||e.getKeyChar()=='^'||e.getKeyChar()=='('||e.getKeyChar()==')'){
                        pointFlag=0;
                    }*/
                }
                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            JPanel InstructionBar=new JPanel(new GridLayout(1,9,0,0));
            InstructionBar.setBackground(Color.gray);
            InstructionBar.setPreferredSize(new Dimension(panelraw,50));
            InstructionBar.add(Box.createRigidArea(new Dimension(50,0)));

            JButton DEL = new JButton("DEL");
            DEL.setPreferredSize(new Dimension(buttonraw,butonhight));
            InstructionBar.add(DEL);
            InstructionBar.add(Box.createRigidArea(new Dimension(150,30)));
            DEL.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String Text=TextArea.getText();
                    TextArea.setText((Text.length()>0)?Text.substring(0,Text.length()-1):"");
                }
            });

            JButton CLR = new JButton("CLR");
            CLR.setPreferredSize(new Dimension(buttonraw,butonhight));
            InstructionBar.add(CLR);
            InstructionBar.add(Box.createRigidArea(new Dimension(150,30)));
            CLR.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText("");
                }
            });

            JButton ANS = new JButton("ANS");
            ANS.setPreferredSize(new Dimension(buttonraw,butonhight));
            InstructionBar.add(ANS);
            InstructionBar.add(Box.createRigidArea(new Dimension(150,30)));
            ANS.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                 try{
                      Input.Result(TextArea.getText());
                      TextArea.setText(Input + "=" + Input.Result());
                  }
                  catch (IllegalArgumentException exception){
                      TextArea.setText("Math Error");
                  }
                 catch (OverflowException exception){
                     TextArea.setText(TextArea.getText()+" is Too Big Value to be handled");
                 }
                }
            });

            JButton EXIT = new JButton("EXIT");
            EXIT.setPreferredSize(new Dimension(buttonraw,butonhight));
            InstructionBar.add(EXIT);
            InstructionBar.add(Box.createRigidArea(new Dimension(150,30)));
            EXIT.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            JPanel SimpleOperation=new JPanel(new GridLayout(4,4,5,5));
            SimpleOperation.setBackground(Color.gray);
            SimpleOperation.setPreferredSize(new Dimension(panelraw/2,250));

            JPanel ScientificOperation=new JPanel(new GridLayout(5,5,5,5));
            ScientificOperation.setBackground(Color.gray);
            ScientificOperation.setVisible(false);

            final JCheckBox checkBox =new JCheckBox("Scientific");
            Bluepanel2.add(checkBox);
            Bluepanel2.add(Box.createRigidArea(new Dimension(1000,0)));
            checkBox.setBackground(Color.gray);
            checkBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (checkBox.isSelected())
                        ScientificOperation.setVisible(true);
                    else
                        ScientificOperation.setVisible(false);
                }
            });

            JPanel Container =new JPanel();
            Container.setLayout(new BoxLayout(Container,BoxLayout.X_AXIS));
            Container.setBackground(Color.gray);
            Container.add(Box.createRigidArea(new Dimension(20,300)));
            Container.add(SimpleOperation);
            Container.add(Box.createRigidArea(new Dimension(20,300)));
            Container.add(ScientificOperation);
            Container.add(Box.createRigidArea(new Dimension(20,300)));

            JButton Zero = new JButton(" 0 ");
            Zero.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Zero);
            Zero.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"0");
                }
            });

            JButton One = new JButton(" 1 ");
            One.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(One);
            One.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"1");
                }
            });

            JButton Two = new JButton(" 2 ");
            Two.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Two);
            Two.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"2");
                }
            });

            JButton Three = new JButton(" 3 ");
            Three.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Three);
            Three.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"3");
                }
            });

            JButton Four = new JButton(" 4 ");
            Four.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Four);
            Four.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"4");
                }
            });

            JButton Five = new JButton(" 5 ");
            Five.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Five);
            Five.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"5");
                }
            });

            JButton Six = new JButton(" 6 ");
            Six.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Six);
            Six.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"6");
                }
            });

            JButton Seven = new JButton(" 7 ");
            Seven.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Seven);
            Seven.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"7");
                }
            });

            JButton Eight = new JButton(" 8 ");
            Eight.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Eight);
            Eight.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"8");
                }
            });

            JButton Nine = new JButton(" 9 ");
            Nine.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Nine);
            Nine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"9");
                }
            });

            JButton Plus = new JButton(" + ");
            Plus.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Plus);
            Plus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"+");
                }
            });

            JButton Minus = new JButton(" - ");
            Minus.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Minus);
            Minus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"-");
                }
            });

            JButton Mul = new JButton(" * ");
            Mul.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Mul);
            Mul.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"*");
                }
            });

            JButton Divide = new JButton(" / ");
            Divide.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Divide);
            Divide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"/");
                }
            });

            JButton Dot = new JButton(" . ");
            Dot.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(Dot);
            Dot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+".");
                }
            });

            JButton PosNeg = new JButton(" ± ");
            PosNeg.setPreferredSize(new Dimension(buttonraw,butonhight));
            SimpleOperation.add(PosNeg);
            PosNeg.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String in = TextArea.getText();
                    if(in.charAt(0)=='-'){
                        TextArea.setText(in.substring(1,in.length()));
                    }
                    else if(in.charAt(0)=='+'){
                        TextArea.setText("-"+in.substring(1,in.length()));
                    }
                    else {
                        TextArea.setText("-"+in.substring(0,in.length()));
                    }
                }
            });

            JButton Square = new JButton(" χ2 ");
            Square.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Square);
            Square.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"^2");
                }
            });

            JButton Cubic = new JButton(" χ3 ");
            Cubic.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Cubic);
            Cubic.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"^3");
                }
            });

            JButton Power = new JButton(" χy ");
            Power.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Power);
            Power.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"^");
                }
            });

            JButton Invert = new JButton(" 1/x ");
            Invert.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Invert);
            Invert.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText("1/("+TextArea.getText()+")");
                }
            });

            JButton Ffr1stArc = new JButton(" ( ");
            Ffr1stArc.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Ffr1stArc);
            Ffr1stArc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"(");
                }
            });

            JButton Squareroot = new JButton("√");
            Squareroot.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Squareroot);
            Squareroot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"√(");
                }
            });

            JButton Cubicroot = new JButton("∛");
            Cubicroot.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Cubicroot);
            Cubicroot.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"∛(");
                }
            });

            JButton RootOf = new JButton(" ANS ");
            RootOf.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(RootOf);
            RootOf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(Input.Result() == 0.0){
                        TextArea.setText("");
                    }else {
                        TextArea.setText(Double.toString(Input.Result()));
                    }
                }
            });

            JButton Vactorial = new JButton(" n! ");
            Vactorial.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Vactorial);
            Vactorial.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"!");
                }
            });

            JButton SecondArc = new JButton(" ) ");
            SecondArc.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(SecondArc);
            SecondArc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+")");
                }
            });

            JButton Sine = new JButton(" sin ");
            Sine.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Sine);
            Sine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"sin(");
                }
            });

            JButton Cosine = new JButton(" cos ");
            Cosine.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Cosine);
            Cosine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"cos(");
                }
            });

            JButton Tan = new JButton(" tan ");
            Tan.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Tan);
            Tan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"tan(");
                }
            });

            JButton Exponentional = new JButton(" exp ");
            Exponentional.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Exponentional);
            Exponentional.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"e");
                }
            });

            JButton permutation = new JButton(" nPr ");
            permutation.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(permutation);
            permutation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"P");
                }
            });

            JButton ArcSine = new JButton(" asin ");
            ArcSine.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(ArcSine);
            ArcSine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"asin(");
                }
            });

            JButton ArcCosine = new JButton(" acos ");
            ArcCosine.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(ArcCosine);
            ArcCosine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"acos(");
                }
            });

            JButton Arctan = new JButton(" atan ");
            Arctan.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Arctan);
            Arctan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"atan(");
                }
            });

            JButton Log = new JButton(" log ");
            Log.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Log);
            Log.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"log(");
                }
            });

            JButton combinatoric = new JButton(" nCr ");
            combinatoric.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(combinatoric);
            combinatoric.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"C");
                }
            });

            JButton Sineh= new JButton(" sinh ");
            Sineh.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Sineh);
            Sineh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"sinh(");
                }
            });

            JButton Cosineh = new JButton(" cosh ");
            Cosineh.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Cosineh);
            Cosineh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"cosh(");
                }
            });

            JButton Tanh= new JButton(" tanh ");
            Tanh.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Tanh);
            Tanh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"tanh(");
                }
            });

            JButton Log10 = new JButton(" log10 ");
            Log10.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Log10);
            Log10.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"log10(");
                }
            });

            JButton Pi = new JButton(" ∏ ");
            Pi.setPreferredSize(new Dimension(buttonraw,butonhight));
            ScientificOperation.add(Pi);
            Pi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TextArea.setText(TextArea.getText()+"pi");
                }
            });

            add(Headbar);
            add(TextArea);
            add(Bluepanel0);
            add(InstructionBar);
            add(Bluepanel1);
            add(Container);
            add(Bluepanel2);
        }
}