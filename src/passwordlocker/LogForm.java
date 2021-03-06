/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordlocker;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.ImageIcon;
/**
 *
 * @author Rajesh
 */
public class LogForm extends javax.swing.JFrame implements KeyListener {
    ArrayList<Log> userlogs;
    /**
     * Creates new form LogForm
     */
    public LogForm(String username) {
        initComponents();
        initLogTable(username);
        searchTF.addKeyListener(this);
    }

    final void initLogTable(String username){
        userlogs = Control.getLogs(username);
        TableModel tm = getCustomModel(logTable.getModel(), userlogs.size());
        for(int i=0;i<userlogs.size();i++){
            tm.setValueAt(userlogs.get(i).getDate(), i, 0);
            tm.setValueAt(userlogs.get(i).getTime(), i, 1);
            tm.setValueAt(userlogs.get(i).getDescription(), i, 2);
        }
        logTable.setModel(tm);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        searchTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Log");
        setIconImage(new ImageIcon(getClass().getResource("/img/passwordlocker.png")).getImage());
        setResizable(false);

        logTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Date", "Time", "Description"
            }
        ));
        jScrollPane1.setViewportView(logTable);

        jLabel1.setText("Search:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable logTable;
    private javax.swing.JTextField searchTF;
    // End of variables declaration//GEN-END:variables

public TableModel getCustomModel(TableModel tm, int row) {
        String columns[] = new String[tm.getColumnCount()];
        for (int i = 0; i < tm.getColumnCount(); i++) {
            columns[i] = tm.getColumnName(i);
        }
        DefaultTableModel dtm = new DefaultTableModel(columns, row);
        return dtm;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String searchvalue = searchTF.getText();
        ArrayList<Log> logs = new ArrayList();
        if(!searchvalue.equals("")){
            for(int i=0;i<userlogs.size();i++){
                if(userlogs.get(i).getDate().contains(searchvalue) || userlogs.get(i).getTime().contains(searchvalue) || userlogs.get(i).getDescription().contains(searchvalue)){
                    logs.add(userlogs.get(i));
                }
            }
        }else{
            logs = userlogs;
        }
        TableModel tm = getCustomModel(logTable.getModel(), logs.size());
        for(int i=0;i<logs.size();i++){
            tm.setValueAt(logs.get(i).getDate(), i, 0);
            tm.setValueAt(logs.get(i).getTime(), i, 1);
            tm.setValueAt(logs.get(i).getDescription(), i, 2);
        }
        logTable.setModel(tm);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
