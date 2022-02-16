/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author McCowan
 * Simulation of the operation of airport control
 * Airplanes arriving at the airport need to land in an orderly fashion with as little delay as possible
 * Primary consideration is that arrivals take priority over take offs
 * Flights are simulated to arrive and take off in a controlled order
 */

//libraries to handle program functionality 
import java.util.Scanner; //read files
import java.util.LinkedList; //create queues
import java.util.Queue; // create queues
import java.io.File; //handle file io
import java.io.FileNotFoundException; //handle file not found expcetion
import java.awt.event.ActionEvent; //action events
import java.awt.event.ActionListener; // event listener
import javax.swing.Timer; //timer 

public class AirportGUI extends javax.swing.JFrame {

    //define queues for planes arriving and taking off
    public static Queue<Integer> landing = new LinkedList<Integer>();
    public static Queue<Integer> takeoff = new LinkedList<Integer>();
    // define timer to run 
    static int tDuration = 600;
    Timer t = new Timer(tDuration, new TimerListener());
    //define files to read data from
    static File arrivalsFile = new File("arrivals.txt");
    static File takeoffFile = new File("takeoffs.txt");
    //set counters to 0
    static int timeCount = 0;
    static int arrivalCount = 0;
    public static Queue<Integer> arrival = new LinkedList<Integer>();

    /**
     * Creates new form AirportGUI
     */
    public AirportGUI() {
        initComponents();
        //read and write all corresponding information from files on startip
        readFile(arrivalsFile, landing);
        readFile(takeoffFile, takeoff);
        updateQueue(arrival);
        
    }
    //method to read fiels from database into the queues
    public void readFile(File database, Queue<Integer> queue) {
        try {
            //define scanner to scan the files
            Scanner scanData = new Scanner(database);
            while(scanData.hasNextLine()){
                //take the next line and add the data to the queue
                String nextLine = scanData.nextLine();
                queue.add(Integer.parseInt(nextLine));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
    }

    public void updateQueue(Queue<Integer> inputQueue) {
        //if there is at least one of the queues arent empty
        if(!landing.isEmpty() || !takeoff.isEmpty()){
            //if landing is empty, set variables to allow for take off
            if (landing.isEmpty()) {
                arrivalCount = 2;
            }
            //if take off is empty, set variables to allow for arrival
            if (takeoff.isEmpty()) {
                arrivalCount = 1;
            }
            //if less than two arrivals have occured in a row, set up for an arrival
            if (arrivalCount < 2) {
                //if the time for arrival has not completed
                if (timeCount < 4) {
                    //display how much longer arrival will take
                    infoLabel.setText("Flight " + String.valueOf(landing.peek()) + " is next to land in " + String.valueOf(4 - timeCount));
                } 
                //if arrival has completed
                else if (timeCount == 4) {
                    //reset variables, remove the flight from queue, and display result
                    infoLabel.setText("Plane landed");
                    landing.remove();
                    timeCount = -1;
                    arrivalCount = arrivalCount + 1;
                }

            } 
            //if two arrivals have occured in a row, set up for takeoff
            else if (arrivalCount == 2) {
                //if time for takeout has not completed
                if (timeCount < 2) {
                    //display how much longer takeoff will take
                    infoLabel.setText("Flight " + String.valueOf(takeoff.peek()) + " is next to takeoff in " + String.valueOf(2 - timeCount));
                } 
                //if take off is complete
                else if (timeCount == 2) {
                    //reset variables, remove flight from queue, and display result
                    infoLabel.setText("Plane departed");
                    takeoff.remove();
                    timeCount = -1;
                    arrivalCount = 0;
                }

            }
            //create backup queues
            Queue<Integer> landingBackup = new LinkedList<Integer>();
            Queue<Integer> takeoffBackup = new LinkedList<Integer>();
            //define strings to print onto the test areas
            String landingFlights = "";
            String takeoffFlights = "";
            //empty landing queue into a backup queue and the text area string
            while (!landing.isEmpty()) {
                int flight = landing.remove();
                landingFlights = landingFlights + String.valueOf(flight) + "\n";
                landingBackup.add(flight);
            }
            //empty backup queue into landing queue
            while (!landingBackup.isEmpty()) {
                landing.add(landingBackup.remove());
            }
            //empty takeoff queue into backup queue and text area string
            while (!takeoff.isEmpty()) {
                int flight = takeoff.remove();
                takeoffFlights = takeoffFlights + String.valueOf(flight) + "\n";
                takeoffBackup.add(flight);
            }
            //empty backup queue into take off queue
            while (!takeoffBackup.isEmpty()) {
                takeoff.add(takeoffBackup.remove());
            }
            //print to text areas
            arrivalsTextArea.setText(landingFlights);
            takeoffTextArea.setText(takeoffFlights);

        }
        //if queues are empty, prevent counter from counting
        if(landing.isEmpty() && takeoff.isEmpty()){
            timeCount = -1;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        arrivalsTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        takeoffTextArea = new javax.swing.JTextArea();
        arrivalsLabel = new javax.swing.JLabel();
        takeoffLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        arrivingFlightsLabel = new javax.swing.JLabel();
        arrivalsTextField = new javax.swing.JTextField();
        takeoffFlightLabel = new javax.swing.JLabel();
        takeoffTextField = new javax.swing.JTextField();
        infoLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Airport Simulator (COW)");

        arrivalsTextArea.setEditable(false);
        arrivalsTextArea.setColumns(20);
        arrivalsTextArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        arrivalsTextArea.setRows(5);
        arrivalsTextArea.setFocusable(false);
        jScrollPane1.setViewportView(arrivalsTextArea);

        takeoffTextArea.setEditable(false);
        takeoffTextArea.setColumns(20);
        takeoffTextArea.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        takeoffTextArea.setRows(5);
        takeoffTextArea.setFocusable(false);
        jScrollPane2.setViewportView(takeoffTextArea);

        arrivalsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        arrivalsLabel.setText("Arrivals");

        takeoffLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        takeoffLabel.setText("Takeoffs");

        startButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        startButton.setText("START");
        startButton.setFocusable(false);
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        ExitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ExitButton.setText("Exit");
        ExitButton.setFocusable(false);
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        arrivingFlightsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        arrivingFlightsLabel.setText("Arriving Flight:");

        arrivalsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                arrivalsTextFieldActionPerformed(evt);
            }
        });

        takeoffFlightLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        takeoffFlightLabel.setText("Takeoff Flight:");

        takeoffTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                takeoffTextFieldActionPerformed(evt);
            }
        });

        infoLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        infoLabel.setText("Press 'START' to begin simulation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ExitButton)
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(infoLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(arrivingFlightsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arrivalsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(arrivalsLabel)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(startButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(takeoffLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(takeoffFlightLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(takeoffTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                .addGap(58, 58, 58))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrivalsLabel)
                    .addComponent(takeoffLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(startButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrivingFlightsLabel)
                    .addComponent(arrivalsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(takeoffTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(takeoffFlightLabel))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ExitButton)
                    .addComponent(errorLabel))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void arrivalsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_arrivalsTextFieldActionPerformed
        //when user input is entered into arrivals
        try {
            //try to add user input into queue
            landing.add(Integer.parseInt(arrivalsTextField.getText()));
            //reset text field
            arrivalsTextField.setText("");
            //update queues
            updateQueue(arrival);
            //empty error label
            errorLabel.setText("");
        } catch (NumberFormatException ex) {
            //if user entry does not match integer setup, tell user to input integer
            errorLabel.setText("Please enter a valid integer flight number");
        }
    }//GEN-LAST:event_arrivalsTextFieldActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        t.start();
    }//GEN-LAST:event_startButtonActionPerformed

    private void takeoffTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_takeoffTextFieldActionPerformed
        //when user input is entered into takeoffs
        try{
            //try to add user input into queue
            takeoff.add(Integer.parseInt(takeoffTextField.getText()));
            //reset error label, reset text field, update queue
            takeoffTextField.setText("");
            updateQueue(arrival);
            errorLabel.setText("");
        }
        catch(NumberFormatException ex){
            //if user entry does not match int requirement, tell user to enter an integer
            errorLabel.setText("Please enter a valid integer flight number");
        }
    }//GEN-LAST:event_takeoffTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AirportGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AirportGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AirportGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AirportGUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AirportGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JLabel arrivalsLabel;
    private javax.swing.JTextArea arrivalsTextArea;
    private javax.swing.JTextField arrivalsTextField;
    private javax.swing.JLabel arrivingFlightsLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel takeoffFlightLabel;
    private javax.swing.JLabel takeoffLabel;
    private javax.swing.JTextArea takeoffTextArea;
    private javax.swing.JTextField takeoffTextField;
    // End of variables declaration//GEN-END:variables

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //update the queue
            updateQueue(arrival);
            //increase the time counter by 1
            timeCount = timeCount + 1;
            
             
        }
    }
}
