import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChineseCheckersServer implements ActionListener {

    static ChineseCheckersServer server;
    static Game game;
    int i;
    static ServerSocket listener;
    private JFrame frame = new JFrame("Chinese Checkers");
    JPanel Panel = new JPanel();
    private Object source;
    private JLabel messageLabel = new JLabel("Wybierz ilu graczy ma zagrac");
    private JButton threePlayers = new JButton("3");
    private JButton fourPlayers = new JButton("4");
    private JButton sixPlayers = new JButton("6");
    private JButton twoPlayers = new JButton("2");
    private Game.Player players[];

    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static void main(String[] args) throws Exception {

        listener = new ServerSocket(8901);
        System.out.println("Chinese Checker Server is Running");


                server = new ChineseCheckersServer();
                game = new Game();
                server.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                server.frame.setVisible(true);
                server.frame.setSize(600, 100);
                server.Panel.add(server.messageLabel, "North");
                server.Panel.add(server.twoPlayers);
                server.Panel.add(server.threePlayers);
                server.Panel.add(server.fourPlayers);
                server.Panel.add(server.sixPlayers);

                server.twoPlayers.addActionListener(server);
                server.threePlayers.addActionListener(server);
                server.fourPlayers.addActionListener(server);
                server.sixPlayers.addActionListener(server);

                server.frame.add(server.Panel, BorderLayout.SOUTH);

                /*
                player2.setOpponent(player5);
                player3.setOpponent(player6);
                //player5.setOpponent(player2);
                player6.setOpponent(player3);
                player1.setOpponent(player4);
                player4.setOpponent(player1);
                */
            }




    public void actionPerformed(ActionEvent e) {

        source = e.getSource();


          if (source == server.twoPlayers) {
            try {
                server.runTwoPlayers();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
          }
          else if(source == server.threePlayers){
              try {
                  server.runThreePlayers();
              } catch (Exception e1) {
                  e1.printStackTrace();
              }
          }
          else if(source == server.fourPlayers){
              try {
                  server.runFourPlayers();
              } catch (Exception e1) {
                  e1.printStackTrace();
              }
          }
          else if(source == server.sixPlayers) {
              try {
                  server.runSixPlayers();
              } catch (Exception e1) {
                  e1.printStackTrace();
              }
          }
    }

    public synchronized void runTwoPlayers()throws Exception {
        while (true) {
            try {
                server.players = new Game.Player[2];

                System.out.println("Wybrłałeś dwóch graczy");

                Game.Player player1 = game.new Player(listener.accept(), '1');
                server.players[0] = player1;

                Game.Player player2 = game.new Player(listener.accept(), '2');
                server.players[1] = player2;

                player1.start();
                player2.start();


            } finally {
                listener.close();
            }
        }
    }

    public synchronized void runThreePlayers()throws Exception {
        while (true) {
            try {
                server.players = new Game.Player[3];

                System.out.println("Wybrłałeś trzech graczy");

                Game.Player player1 = game.new Player(listener.accept(), '1');
                server.players[0] = player1;

                Game.Player player2 = game.new Player(listener.accept(), '2');
                server.players[1] = player2;

                Game.Player player3 = game.new Player(listener.accept(), '3');
                server.players[2] = player3;

                player1.start();
                player2.start();
                player3.start();


            } finally {
                listener.close();
            }
        }
    }

    public synchronized void runFourPlayers()throws Exception {
        while (true) {
            try {
                server.players = new Game.Player[4];

                System.out.println("Wybrłałeś czterech graczy");

                Game.Player player1 = game.new Player(listener.accept(), '1');
                server.players[0] = player1;

                Game.Player player2 = game.new Player(listener.accept(), '2');
                server.players[1] = player2;

                Game.Player player3 = game.new Player(listener.accept(), '3');
                server.players[2] = player3;

                Game.Player player4 = game.new Player(listener.accept(), '4');
                server.players[3] = player4;

                player1.start();
                player2.start();
                player3.start();
                player4.start();

            } finally {
                listener.close();
            }
        }
    }

    public synchronized void runSixPlayers()throws Exception {
        while (true) {
            try {
                server.players = new Game.Player[6];

                System.out.println("Wybrłałeś szeciu graczy");

                Game.Player player1 = game.new Player(listener.accept(), '1');
                server.players[0] = player1;

                Game.Player player2 = game.new Player(listener.accept(), '2');
                server.players[1] = player2;

                Game.Player player3 = game.new Player(listener.accept(), '3');
                server.players[2] = player3;

                Game.Player player4 = game.new Player(listener.accept(), '4');
                server.players[3] = player4;

                Game.Player player5 = game.new Player(listener.accept(), '5');
                server.players[4] = player5;

                Game.Player player6 = game.new Player(listener.accept(), '6');
                server.players[5] = player6;

                player1.start();
                player2.start();
                player3.start();
                player4.start();
                player5.start();
                player6.start();

            } finally {
                listener.close();
            }
        }
    }
    static class Game {

        /**
         * A board has nine squares.  Each square is either unowned or
         * it is owned by a player.  So we use a simple array of player
         * references.  If null, the corresponding square is unowned,
         * otherwise the array cell stores a reference to the player that
         * owns it.
         */
        private Player[] board = {
                null, null, null,
                null, null, null,
                null, null, null};

        /**
         * The current player.
         */
        Player currentPlayer;


        /**
         * The class for the helper threads in this multithreaded server
         * application.  A Player is identified by a character mark
         * which is either 'X' or 'O'.  For communication with the
         * client the player has a socket with its input and output
         * streams.  Since only text is being communicated we use a
         * reader and a writer.
         */
        class Player extends Thread {
            char mark;
            Player opponent;
            Socket socket;
            BufferedReader input;
            PrintWriter output;

            /**
             * Constructs a handler thread for a given socket and mark
             * initializes the stream fields, displays the first two
             * welcoming messages.
             */
            public Player(Socket socket, char mark) {
                this.socket = socket;
                this.mark = mark;
                try {
                    input = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
                    output = new PrintWriter(socket.getOutputStream(), true);
                    output.println("WELCOME " + mark);
                    output.println("MESSAGE Waiting for opponent to connect");
                } catch (IOException e) {
                    System.out.println("Player died: " + e);
                }
            }

            /**
             * Accepts notification of who the opponent is.
             */
            public void setOpponent(Player opponent) {
                this.opponent = opponent;
            }

            /**
             * The run method of this thread.
             */
            public void run() {

                // The thread is only started after everyone connects.
                output.println("MESSAGE All players connected");


            }
        }
    }
}
