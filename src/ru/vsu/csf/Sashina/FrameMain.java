package ru.vsu.csf.Sashina;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;

public class FrameMain extends JFrame {

    private JPanel panelMain;
    private JButton getText1;
    private JTextField getListField1;
    private JButton getFileNameInput;
    private JTextField getNameInputField;
    private JLabel mistakeLabelList1;
    private JLabel mistakeLabelInput;
    private JButton getText2;
    private JTextField getListField2;
    private JLabel mistakeLabelList2;
    private JList<String> showLists;
    private JButton solution;
    private JButton export;
    private JTextField getFileOutput;
    private JLabel mistakeExport;
    private JButton clear;

    DefaultListModel<String> model = new DefaultListModel<>();
    LinkedListNum<Double> list1 = new LinkedListNum<>();
    LinkedListNum<Double> list2 = new LinkedListNum<>();

    public FrameMain () {
        this.setTitle("Связной список");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        getText1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = getListField1.getText();
                    list1 = Logic.fillLinkedList(s);
                    model.addElement(s);
                    showLists.setModel(model);
                } catch (Exception exp) {
                    mistakeLabelList1.setText("Ошибка в обработке листа");
                    model.addElement(null);
                    showLists.setModel(model);
                }
            }
        });

        getText2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = getListField2.getText();
                    list2 = Logic.fillLinkedList(s);
                    model.addElement(s);
                    showLists.setModel(model);
                } catch (Exception exp) {
                    mistakeLabelList2.setText("Ошибка в обработке листа");
                    model.addElement(null);
                    showLists.setModel(model);
                }
            }
        });

        getFileNameInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = getNameInputField.getText();
                    if (!name.contains(".txt")) {
                        name += ".txt";
                    }
                    String [] str = Files.getNumbers(name);
                    list1 = Logic.fillLinkedList(str[0]);
                    model.addElement(str[0]);
                    list2 = Logic.fillLinkedList(str[1]);
                    model.addElement(str[1]);
                    showLists.setModel(model);
                } catch (Exception exp) {
                    mistakeLabelInput.setText("Ошибка в чтении файла");
                    model.addElement(null);
                    model.addElement(null);
                    showLists.setModel(model);
                }
            }
        });

        solution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = "Результат: ";
                try {
                    s += Logic.listToString(Logic.solution(list1, list2));
                    model.addElement(s);
                    showLists.setModel(model);
                } catch (Exception exp) {
                    model.addElement(s + "Ошибка в обработке листа");
                    showLists.setModel(model);
                }
            }
        });

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = getFileOutput.getText();
                    if (!name.contains(".txt")) {
                        name += ".txt";
                    }
                    Files.fillFile(name, showLists.getModel());
                } catch (Exception exp) {
                    mistakeExport.setText("Ошибка в сохранении ответа");
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getListField1.setText("");
                getListField2.setText("");
                mistakeLabelList1.setText("");
                mistakeLabelList2.setText("");
                getNameInputField.setText("");
                mistakeLabelInput.setText("");
                getFileOutput.setText("");
                mistakeExport.setText("");
                model.clear();
                list1 = new LinkedListNum<>();
                list2 = new LinkedListNum<>();
            }
        });
    }
}
