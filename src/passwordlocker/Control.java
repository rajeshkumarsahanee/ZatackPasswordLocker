/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordlocker;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Rajesh
 */
public final class Control {

    static ArrayList<User> users = new ArrayList();
    static ArrayList<MyPassword> mypasswords = new ArrayList();
    static ArrayList<Log> logs = new ArrayList();
    static DocumentBuilder db;
    static Document doc;
    static String path = System.getProperty("user.home") + "\\PasswordLocker";

    static {
        fetchUsers();
        fetchLogs();
    }

    /**
     * Add user to array list and than to xml
     */
    public static void addUser(User user) {
        users.add(user);
        storeUsers();
    }
//   

    public static void updateUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            User usr = users.get(i);
            if (usr.equals(user)) {
                usr.setName(user.getName());
                usr.setLastLoginDate(user.getLastLoginDate());
                usr.setLastLoginTime(user.getLastLoginTime());
                usr.setPassword(user.getPassword());
                usr.setFilePath(user.getFilePath());
            }
        }
        storeUsers();
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
//    /** Add log to array list and than to xml */

    public static void addLog(Log log) {
        logs.add(log);
        storeLogs();
    }
//    

    public static ArrayList<Log> getLogs(String username) {
        ArrayList<Log> userLogs = new ArrayList();
        for (int i = 0; i < logs.size(); i++) {
            if (logs.get(i).getUsername().equals(username)) {
                userLogs.add(logs.get(i));
            }
        }
        return userLogs;
    }
//    /** Add mypassword to array list and than to xml */

    public static void addMyPassword(MyPassword mypassword, String path) {
        if(!mypasswords.contains(mypassword)){
        mypasswords.add(mypassword);
        storeMyPasswords(path);
        }else{
            JOptionPane.showMessageDialog(null, "Already exist");
        }
    }
//    
//    /** delete a mypassword from array list and store it to xml*/

    public static void deleteMyPassword(MyPassword mypassword, String path) {
        for (int i = 0; i < mypasswords.size(); i++) {
            MyPassword tr = mypasswords.get(i);
            if (tr.getUsername().equals(mypassword.getUsername()) && tr.getPassword().equals(mypassword.getPassword()) && tr.getDescription().equals(mypassword.getDescription())) {
                mypasswords.remove(i);
                break;
            }
        }
        storeMyPasswords(path);
    }

//    /** update a mypassword from array list and store it to xml*/
    public static void updateMyPassword(MyPassword oldmypassword, MyPassword newmypassword, String path) {
        for (int i = 0; i < mypasswords.size(); i++) {
            MyPassword mp = mypasswords.get(i);
            if (mp.getUsername().equals(oldmypassword.getUsername()) && mp.getPassword().equals(oldmypassword.getPassword()) && mp.getDescription().equals(oldmypassword.getDescription())) {
                mypasswords.get(i).setDescription(newmypassword.getDescription());
                mypasswords.get(i).setLastChangeDate(newmypassword.getLastChangeDate());
                mypasswords.get(i).setLastChangeTime(newmypassword.getLastChangeTime());
                mypasswords.get(i).setPassword(newmypassword.getPassword());
                mypasswords.get(i).setUsername(newmypassword.getUsername());
                break;
            }
        }
        storeMyPasswords(path);
    }
//    

    public static ArrayList<MyPassword> getMyPasswords(String path) {
        fetchMyPasswords(path);
        return mypasswords;
    }   

    private static void storeUsers() {

        File file = new File(path + "\\users.xml");
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.newDocument();
            // create the root element
            Element rootEle = doc.createElement("users");

            for (int i = 0; i < users.size(); i++) {
                // create data elements and place them under root
                Element subroot = doc.createElement("user");
                //create node
                Element name = doc.createElement("name");
                Element username = doc.createElement("username");
                Element password = doc.createElement("password");
                Element lastLoginDate = doc.createElement("lastlogindate");
                Element lastLoginTime = doc.createElement("lastlogintime");
                Element filePath = doc.createElement("filepath");
                //set value
                name.appendChild(doc.createTextNode(encrypt(users.get(i).getName())));
                username.appendChild(doc.createTextNode(encrypt(users.get(i).getUsername())));
                password.appendChild(doc.createTextNode(encrypt(users.get(i).getPassword())));
                lastLoginDate.appendChild(doc.createTextNode(encrypt(users.get(i).getLastLoginDate())));
                lastLoginTime.appendChild(doc.createTextNode(encrypt(users.get(i).getLastLoginTime())));
                filePath.appendChild(doc.createTextNode(encrypt(users.get(i).getFilePath())));
                //store node to subroot
                subroot.appendChild(name);
                subroot.appendChild(username);
                subroot.appendChild(password);
                subroot.appendChild(lastLoginDate);
                subroot.appendChild(lastLoginTime);
                subroot.appendChild(filePath);
                //store subroot to root
                rootEle.appendChild(subroot);//meeting node is added to meetings/root node
            }
            doc.appendChild(rootEle);//root node is added to document

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                // tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                // send DOM to file
                tr.transform(new DOMSource(doc),
                        new StreamResult(new FileOutputStream(file)));
            } catch (TransformerException | IOException te) {
                JOptionPane.showMessageDialog(null, te);
            }
        } catch (ParserConfigurationException pce) {
            JOptionPane.showMessageDialog(null, pce);
        }
    }
//    

    private static void storeLogs() {
        File file = new File(path + "\\logs.xml");
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.newDocument();
            // create the root element
            Element rootEle = doc.createElement("logs");

            for (int i = 0; i < logs.size(); i++) {
                // create data elements and place them under root
                Element subroot = doc.createElement("log");
                //create node
                Element username = doc.createElement("username");
                Element date = doc.createElement("date");
                Element time = doc.createElement("time");
                Element description = doc.createElement("description");
                //set value
                username.appendChild(doc.createTextNode(encrypt(logs.get(i).getUsername())));
                date.appendChild(doc.createTextNode(encrypt(logs.get(i).getDate())));
                time.appendChild(doc.createTextNode(encrypt(logs.get(i).getTime())));
                description.appendChild(doc.createTextNode(encrypt(logs.get(i).getDescription())));

                //store node to subroot
                subroot.appendChild(username);
                subroot.appendChild(date);
                subroot.appendChild(time);
                subroot.appendChild(description);

                //store subroot to root
                rootEle.appendChild(subroot);//meeting node is added to meetings/root node
            }
            doc.appendChild(rootEle);//root node is added to document

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                // tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                // send DOM to file
                tr.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(file)));
            } catch (TransformerException | IOException te) {
                JOptionPane.showMessageDialog(null, te);
            }
        } catch (ParserConfigurationException pce) {
            JOptionPane.showMessageDialog(null, pce);
        }
    }
//    

    private static void storeMyPasswords(String path) {
        File file = new File(path + ".xml");
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.newDocument();
            // create the root element
            Element rootEle = doc.createElement("mypasswords");

            for (int i = 0; i < mypasswords.size(); i++) {
                // create data elements and place them under root
                Element subroot = doc.createElement("mypassword");
                //create node
                Element username = doc.createElement("username");
                Element password = doc.createElement("password");
                Element description = doc.createElement("description");
                Element createDate = doc.createElement("createdate");
                Element createTime = doc.createElement("createtime");
                Element lastChangeDate = doc.createElement("lastchangedate");
                Element lastChangeTime = doc.createElement("lastchangetime");
                //set value
                username.appendChild(doc.createTextNode(encrypt(mypasswords.get(i).getUsername())));
                password.appendChild(doc.createTextNode(encrypt(mypasswords.get(i).getPassword())));
                description.appendChild(doc.createTextNode(encrypt(mypasswords.get(i).getDescription())));
                createDate.appendChild(doc.createTextNode(encrypt(mypasswords.get(i).getCreateDate())));
                createTime.appendChild(doc.createTextNode(encrypt(mypasswords.get(i).getCreateTime())));
                lastChangeDate.appendChild(doc.createTextNode(encrypt(mypasswords.get(i).getLastChangeDate())));
                lastChangeTime.appendChild(doc.createTextNode(encrypt(mypasswords.get(i).getLastChangeTime())));
                //store node to subroot
                subroot.appendChild(username);
                subroot.appendChild(password);
                subroot.appendChild(description);
                subroot.appendChild(createDate);
                subroot.appendChild(createTime);
                subroot.appendChild(lastChangeDate);
                subroot.appendChild(lastChangeTime);
                //store subroot to root
                rootEle.appendChild(subroot);//meeting node is added to meetings/root node
            }
            doc.appendChild(rootEle);//root node is added to document

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                // tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                // send DOM to file
                tr.transform(new DOMSource(doc),
                        new StreamResult(new FileOutputStream(file)));
            } catch (TransformerException | IOException te) {
                JOptionPane.showMessageDialog(null, te);
            }
        } catch (ParserConfigurationException pce) {
            JOptionPane.showMessageDialog(null, pce);
        }
    }
//    
//    public void fetchDataFromXml(){
//        fetchAccounts();
//        fetchBeneficiaries();
//        fetchMyPasswords();
//         
//    }
//    

    private static void fetchUsers() {
        User user;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(path + "\\users.xml");//parse xml file and make Document object
            NodeList userList = doc.getElementsByTagName("user");//it gives total entries in meetings
            for (int i = 0; i < userList.getLength(); i++) {
                user = new User();
                Node userNode = userList.item(i);//getting one node at position i
                if (userNode.getNodeType() == Node.ELEMENT_NODE) {//check for node is subnode or data-node
                    Element userele = (Element) userNode;
                    user.setName(decrypt(userele.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue()));
                    user.setUsername(decrypt(userele.getElementsByTagName("username").item(0).getChildNodes().item(0).getNodeValue()));
                    user.setPassword(decrypt(userele.getElementsByTagName("password").item(0).getChildNodes().item(0).getNodeValue()));
                    user.setLastLoginDate(decrypt(userele.getElementsByTagName("lastlogindate").item(0).getChildNodes().item(0).getNodeValue()));
                    user.setLastLoginTime(decrypt(userele.getElementsByTagName("lastlogintime").item(0).getChildNodes().item(0).getNodeValue()));
                    user.setFilePath(decrypt(userele.getElementsByTagName("filepath").item(0).getChildNodes().item(0).getNodeValue()));
                    users.add(user);//adding meeting object to meetings list(ArrayList)
                }

            }

        } catch (IllegalArgumentException | ParserConfigurationException | SAXException e) {
            //JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            try {
                new File(path).mkdir();
                new FileOutputStream(path + "\\users.xml").close();

            } catch (IOException | HeadlessException e2) {
                JOptionPane.showMessageDialog(null, e2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//    

    private static void fetchLogs() {
        Log log;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(path + "\\logs.xml");
            NodeList logList = doc.getElementsByTagName("log");
            for (int i = 0; i < logList.getLength(); i++) {
                log = new Log();
                Node logNode = logList.item(i);
                if (logNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element logele = (Element) logNode;
                    log.setUsername(decrypt(logele.getElementsByTagName("username").item(0).getChildNodes().item(0).getNodeValue()));
                    log.setDate(decrypt(logele.getElementsByTagName("date").item(0).getChildNodes().item(0).getNodeValue()));
                    log.setTime(decrypt(logele.getElementsByTagName("time").item(0).getChildNodes().item(0).getNodeValue()));
                    log.setDescription(decrypt(logele.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue()));
                    logs.add(log);
                }
            }
        } catch (IllegalArgumentException | ParserConfigurationException | SAXException e) {
            //JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            try {
                new File(path).mkdir();
                new FileOutputStream(path + "\\logs.xml").close();
            } catch (IOException | HeadlessException e2) {
                JOptionPane.showMessageDialog(null, e2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//    

    private static void fetchMyPasswords(String path) {
        mypasswords.clear();
        MyPassword mypassword;
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(path + ".xml");
            NodeList mypasswordList = doc.getElementsByTagName("mypassword");
            for (int i = 0; i < mypasswordList.getLength(); i++) {
                mypassword = new MyPassword();
                Node mypasswordNode = mypasswordList.item(i);
                if (mypasswordNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element mypasswordele = (Element) mypasswordNode;
                    mypassword.setUsername(decrypt(mypasswordele.getElementsByTagName("username").item(0).getChildNodes().item(0).getNodeValue()));
                    mypassword.setPassword(decrypt(mypasswordele.getElementsByTagName("password").item(0).getChildNodes().item(0).getNodeValue()));
                    mypassword.setDescription(decrypt(mypasswordele.getElementsByTagName("description").item(0).getChildNodes().item(0).getNodeValue()));
                    mypassword.setCreateDate(decrypt(mypasswordele.getElementsByTagName("createdate").item(0).getChildNodes().item(0).getNodeValue()));
                    mypassword.setCreateTime(decrypt(mypasswordele.getElementsByTagName("createtime").item(0).getChildNodes().item(0).getNodeValue()));
                    mypassword.setLastChangeDate(decrypt(mypasswordele.getElementsByTagName("lastchangedate").item(0).getChildNodes().item(0).getNodeValue()));
                    mypassword.setLastChangeTime(decrypt(mypasswordele.getElementsByTagName("lastchangetime").item(0).getChildNodes().item(0).getNodeValue()));
                    mypasswords.add(mypassword);
                }
            }
        } catch (IllegalArgumentException | ParserConfigurationException | SAXException e) {
            //JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            try {
                new FileOutputStream(path + ".xml").close();
            } catch (IOException | HeadlessException e2) {
                JOptionPane.showMessageDialog(null, e2);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//    
//    public void syncToDatabase(){
//        Connection con=null;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://sql09.freemysql.net/database8","rajesh01","093689636");
//            Statement stmt=con.createStatement();
//           // stmt.execute("create table users(username varchar(30),usernumber varchar(30),bank varchar(30),branch varchar(30),aodate varchar(15))");
//            stmt.execute("delete from users");
//            for(int i=0;i<users.size();i++){
//            stmt.execute("insert into users values('"+users.get(i).getAccountName()+"','"+users.get(i).getAccountNumber()+"','"+users.get(i).getBank()+"','"+users.get(i).getBranch()+"','"+users.get(i).getAodate()+"')");
//            }
//           //stmt.execute("create table beneficiaries(addin varchar(60),username varchar(30),usernumber varchar(30),bank varchar(30),branch varchar(30),date varchar(15))"); 
//            stmt.execute("delete from beneficiaries");
//            for(int i=0;i<beneficiaries.size();i++){
//                stmt.execute("insert into beneficiaries values('"+beneficiaries.get(i).getAddIn()+"','"+beneficiaries.get(i).getAccountName()+"','"+beneficiaries.get(i).getAccountNumber()+"','"+beneficiaries.get(i).getBank()+"','"+beneficiaries.get(i).getBranch()+"','"+beneficiaries.get(i).getDate()+"')");
//            }
//            
//            //stmt.execute("create table mypasswords(debitbank varchar(30),creditbank varchar(30),debituser varchar(60),credituser varchar(60),amount varchar(10),charge varchar(10),date varchar(15))"); 
//            stmt.execute("delete from mypasswords");
//            for(int i=0;i<mypasswords.size();i++){
//                stmt.execute("insert into mypasswords values('"+mypasswords.get(i).getDebitBank()+"','"+mypasswords.get(i).getCreditBank()+"','"+mypasswords.get(i).getDebitAccount()+"','"+mypasswords.get(i).getCreditAccount()+"','"+mypasswords.get(i).getAmount()+"','"+mypasswords.get(i).getCharge()+"','"+mypasswords.get(i).getDate()+"')");
//            }
//        }catch(ClassNotFoundException | SQLException e){
//            System.out.println(e);
//        }finally{
//            try{ con.close(); }catch(Exception e){}
//        }
//        JOptionPane.showMessageDialog(null, "Successfully synched to database");
//    }
//    
//    
//    public void syncFromDatabase(){
//        Connection con=null;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://sql09.freemysql.net/database8","rajesh01","093689636");
//            Statement stmt=con.createStatement();
//            //stmt.execute("create table mypasswords(debitbank varchar(30),creditbank varchar(30),debituser varchar(60),credituser varchar(60),amount varchar(10),charge varchar(10),date varchar(15))"); 
//            ResultSet rs;
//            users.clear();
//            beneficiaries.clear();
//            mypasswords.clear();
//            Account user;
//            Log log;
//            MyPassword mypassword;
//            
//            rs=stmt.executeQuery("select * from users");
//            while(rs.next()){
//                      user=new Account();
//                      user.setAccountName(rs.getString("username"));
//                      user.setAccountNumber(rs.getString("usernumber"));
//                      user.setBank(rs.getString("bank"));
//                      user.setBranch(rs.getString("branch"));
//                      user.setAodate(rs.getString("aodate"));
//                      users.add(user);
//            }
//            rs=stmt.executeQuery("select * from beneficiaries");
//            while(rs.next()){
//                      log=new Log(rs.getString("addin"),rs.getString("username"),rs.getString("usernumber"),rs.getString("bank"),rs.getString("branch"),rs.getString("date"));
//                      beneficiaries.add(log);
//            }
//            rs=stmt.executeQuery("select * from mypasswords");
//            while(rs.next()){
//                mypassword=new MyPassword(rs.getString("debitbank"), rs.getString("creditbank"), rs.getString("debituser"), rs.getString("credituser"), rs.getInt("amount"), rs.getInt("charge"), rs.getString("date"));
//                mypasswords.add(mypassword);
//            }
//            storeDataToXml();
//            
//        }catch(ClassNotFoundException | SQLException e){
//            JOptionPane.showMessageDialog(null, e);
//            return;
//        }finally{
//            try{ con.close(); }catch(Exception e){}
//        }
//        JOptionPane.showMessageDialog(null, "Successfully fetched from database");
//    }
//    
//    public TableModel getCustomModel(TableModel tm, int row) {
//        String columns[] = new String[tm.getColumnCount()];
//        for (int i = 0; i < tm.getColumnCount(); i++) {
//            columns[i] = tm.getColumnName(i);
//        }
//        DefaultTableModel dtm = new DefaultTableModel(columns, row);
//        return dtm;
//    }
//    

    public static String encrypt(String str) {
        String encrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("AES");
            Key key = new SecretKeySpec("MySecretKeyIsKey".getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(str.getBytes());
            encrypted = new BASE64Encoder().encode(encVal);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println(ex);
        }
        return encrypted;
    }

    public static String decrypt(String str) {
        String decrypted = null;
        try {
            //code of encryption goes here
            Cipher cipher = Cipher.getInstance("AES");
            Key key = new SecretKeySpec("MySecretKeyIsKey".getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = new BASE64Decoder().decodeBuffer(str);
            byte[] decVal = cipher.doFinal(decodedValue);
            decrypted = new String(decVal);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IOException ex) {
            System.out.println(ex);
        }
        return decrypted;
    }
}
