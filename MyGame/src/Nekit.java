import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by npolevoy on 19.01.2015.
 */
public class Nekit extends JFrame{

    JTextArea countLabel1 = new JTextArea(); //окно для ввода

    JLabel okno1 = new JLabel();

    LinkedList<WordModel> list = new LinkedList<WordModel>(); // основной масив

    byte regim = 0; // режим состояния окна 1 - сравниваем; 2 - переходим в

// первый режим; 0 - только зашли

    int kursor;
    int blok;

    Nekit(int blok) {

        this.blok = blok;
        list = zapolnitmas();

        System.out.println(blok);
// System.out.print("3");

        okno1.setText("Слов в работу: "+list.size());

        JPanel Panel1 = new JPanel(new GridLayout(3, 12, 80, 80));

        setBounds(10, 10, 500, 500);

        add(Panel1, BorderLayout.CENTER);

        JButton knopka1 = new JButton("Ok");

        Panel1.add(countLabel1);

        Panel1.add(okno1);

        Panel1.add(knopka1);

        setVisible(true);

// pack();

        countLabel1.addKeyListener(new KeyListener() {

            @Override

            public void keyPressed(KeyEvent e){

                if(e.getKeyCode() == KeyEvent.VK_ENTER){

                    e.consume();

                    buttoncklick();

                }

            }

            @Override

            public void keyTyped(KeyEvent e){}

            @Override

            public void keyReleased(KeyEvent e){}

        });

        knopka1.addActionListener(new ActionListener() {

// @Override

            public void actionPerformed(ActionEvent e) {
                buttoncklick();
            }
        });

    }

    LinkedList<WordModel> zapolnitmas() // метод создающий список

    {

        String sCurrentLine = null;

        LinkedList<WordModel> list = new LinkedList<WordModel>(); // сюда херачим слова

        String[] tokens;

        try {

            BufferedReader br = new BufferedReader(new FileReader(

                    "src/text.txt"));

            while ((sCurrentLine = br.readLine()) != null) {

                tokens = sCurrentLine.split(":");

                if (Integer.parseInt(tokens[3]) == blok)
                list.add(new WordModel(tokens[0], tokens[1]));

            }

        } catch (Exception e) {

            System.out.println("Ошибка");

        }

        return list;

    }

    int variant(LinkedList list) {

        new Random().nextInt(5);

        return new Random().nextInt(list.size());

    }

    void buttoncklick(){	// System.exit(0);

        switch (regim) {

            case 0:

//	System.out.println("Режим 0");

                countLabel1.setText("");

                okno1.setText("");

                kursor = variant(list);

                this.setTitle(""+list.size());

//if (list.get(kursor) == null) {System.exit(0);}

                okno1.setText(list.get(kursor).getrussianWord());

                regim = 1;

                break;

            case 1:

//	System.out.println("Режим 1");

                String first = list.get(kursor).getEnglishWord();

                String second = countLabel1.getText();

                if (first.equalsIgnoreCase(second)) {

                    okno1.setText("Молодэц!");

                    switch (list.get(kursor).getid()){

                        case 0:

                            list.remove(kursor);
                            this.setTitle(""+list.size());

                            break;

                        case 3:

                            list.get(kursor).setid((byte)2);

                            break;

                        case 2:

                            list.get(kursor).setid((byte)1);
                            this.setTitle(""+list.size());

                            break;

                        case 1:

                            list.get(kursor).setid((byte)0);
                            this.setTitle(""+list.size());

                            break;

                    }

                } else {

                    okno1.setText("Правильно будет не "

                            + countLabel1.getText() + ", а "

                            + list.get(kursor).getEnglishWord());

                    list.get(kursor).setid((byte)3);

//okno1.setText("Ошибка");

                }

                regim = 2;
                this.setTitle(""+list.size());

                break;

            case 2:

//	System.out.println("Режим 2");

                countLabel1.setText("");

                if (list.size() == 0) {System.exit(0);}

                kursor = variant(list);

                okno1.setText(list.get(kursor).getrussianWord());

                regim = 1;
                this.setTitle(""+list.size());

                break;

            default:

                System.exit(1);

                break;

        }

/*

* for (WordModel word : list) {

* System.out.println(word.getEnglishWord()); }

*/

// System.out.println( list.get(variant(list)).getrussianWord()

// );
        // okno1.setText("");

// okno1.setText(list.get(variant(list)).getrussianWord());

// for (int j = 0; j<3; j++) //проверка

// { System.out.println(list.get(j).getEnglishWord());}

// okno1.setText(str);

// button7.setVisible(false);}

    }
}
