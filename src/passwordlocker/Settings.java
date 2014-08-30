/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordlocker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
/**
 *
 * @author Rajesh
 */
public class Settings extends javax.swing.JFrame implements ActionListener{
    User user;
    /**
     * Creates new form Settings
     */
    public Settings(User user) {
        initComponents();
        this.user = user;
        initValues();
        addListeners();
    }

    final void addListeners(){
        showPasswordB.addActionListener(this);
        changePathB.addActionListener(this);
        updateB.addActionListener(this);
    }
    final void initValues(){
        nameTF.setText(user.getName());
        usernameTF.setText(user.getUsername());
        passwordF.setText(user.getPassword());
        String path = user.getFilePath().substring(0, user.getFilePath().lastIndexOf("\\"));
        String file = user.getFilePath().substring(user.getFilePath().lastIndexOf("\\")+1);
        filePathTF.setText(path);
        fileNameTF.setText(file);
        showPasswordL.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameTF = new javax.swing.JTextField();
        usernameTF = new javax.swing.JTextField();
        passwordF = new javax.swing.JPasswordField();
        filePathTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fileNameTF = new javax.swing.JTextField();
        changePathB = new javax.swing.JButton();
        updateB = new javax.swing.JButton();
        showPasswordL = new javax.swing.JLabel();
        showPasswordB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");
        setIconImage(new ImageIcon(getClass().getResource("/img/passwordlocker.png")).getImage());
        setResizable(false);

        jLabel1.setText("Name:");

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        jLabel4.setText("File Path:");

        usernameTF.setEditable(false);

        passwordF.setToolTipText("Type Password to change");

        filePathTF.setEditable(false);

        jLabel5.setText("File Name:");

        changePathB.setText("Change Path");

        updateB.setText("Update");

        showPasswordL.setText("123456789");

        showPasswordB.setText("Show Password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(updateB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPasswordB))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nameTF)
                                .addComponent(usernameTF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(passwordF))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(showPasswordL))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(fileNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(changePathB))
                        .addComponent(filePathTF)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPasswordL))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(filePathTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fileNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changePathB))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateB)
                    .addComponent(showPasswordB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePathB;
    private javax.swing.JTextField fileNameTF;
    private javax.swing.JTextField filePathTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField nameTF;
    private javax.swing.JPasswordField passwordF;
    private javax.swing.JButton showPasswordB;
    private javax.swing.JLabel showPasswordL;
    private javax.swing.JButton updateB;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changePathB){
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            int response = chooser.showDialog(this, "Select");
            if(response == JFileChooser.APPROVE_OPTION){
                filePathTF.setText(chooser.getSelectedFile().getPath());
            }
        }
        
        if(e.getSource() == showPasswordB){
            if(showPasswordB.getText().equals("Show Password")){
                showPasswordL.setText(passwordF.getText());
                showPasswordB.setText("Hide Password");
            }else{
                showPasswordL.setText("");
                showPasswordB.setText("Show Password");
            }
        }
        
        if(e.getSource() == updateB){
            String name = nameTF.getText();
            String username = usernameTF.getText();
            String password = passwordF.getText();
            String filePath = filePathTF.getText();
            String fileName = fileNameTF.getText();
            if(!name.equals("") && !password.equals("") && !filePath.equals("") && !fileName.equals("")){
                Control.updateUser(new User(name, username, password, user.getLastLoginDate(), user.getLastLoginTime(), filePath+"\\"+fileName));
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(System.currentTimeMillis());
                String date = cal.get(Calendar.DAY_OF_MONTH)+" "+cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)+", "+cal.get(Calendar.YEAR);
                String time = cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND);
                Control.addLog(new Log(user.getUsername(), date, time, "User Detail Updated"));
                JOptionPane.showMessageDialog(this, "Details Updated!");
            }
        }
    }
}
