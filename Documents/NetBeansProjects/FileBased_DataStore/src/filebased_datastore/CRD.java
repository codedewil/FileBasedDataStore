/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filebased_datastore;

/**
 *
 * @author ELCOT
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class CRD {
    Scanner scan=new Scanner(System.in);
   static String s="C:\\Users\\ELCOT\\Documents\\sample.json";
    File tmpDir=new File(s);
    JSONArray jarr=new JSONArray();
     JSONObject jsonObj = new JSONObject();
    void create_KeyPair()
    {
        System.out.println("enter 1 to create your own file path\nEnter any number to create a default path");
        int choice=scan.nextInt();
        if(choice==1)
        {
            System.out.println("Enter path eg.C:\\Users\\ELCOT\\Documents\\sample.json");
            s=scan.next();
            tmpDir = new File(s);
            boolean exists = tmpDir.exists();
            if(!exists)
                System.out.println("created successfully");
            else
                System.out.println("File already exist");
        }
       /* else
        {
            tmpDir = new File("C:\\Users\\ELCOT\\Documents\\sample.json");
             System.out.println("File created successfully");
        }*/
        int flag=1;
        String key="";
       while(flag==1)
        {
        System.out.println("Create a new Key-Pair");
        System.out.println("enter key with 32 char not execeeding that");
        key=scan.next();
        //key=scan.nextLine();
        if(key.length()>32)
        {
            System.out.println("key exceeds 32 characters the key value is trimmed to 32 characters"); 
            System.out.println("if you want to re-enter the key enter 1 else any number");
            flag=scan.nextInt();
            if(flag!=1)
            {
                key=key.substring(0,32);
                //System.out.println("Key-value Pair created successfully");
                 break;
            }
        }
        else
        {
            
            break;
        }
        }
        System.out.println("Enter value not exceeding 16kb");
        //System.out.println("")
        String value=scan.next();
        if(value.length()>8000)
            System.out.println("value exceeds limit");
       
        jsonObj.put(key,value);
        if(jsonObj.size()>1000000000)
        {
            System.out.println("file storage exceeds 1 gb");
        }
        if(read_KeyPair(key)!=null)
           System.out.println("key already exist");
        else
        {
        jarr.add(jsonObj);
        try (FileWriter file = new FileWriter(tmpDir,false)) {
            file.flush();
            file.write(jsonObj.toJSONString());
            
            System.out.println("Key-value Pair created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
    static String read_KeyPair(String key)
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(s))
		{
            Object obj = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;  
            
            String value=(String)jsonObject.get(key);
            
            //System.out.println(value);
            return value;
            
            

        } catch (Exception e) {
           System.out.println("Exception");
        } 
       return null;
    }
    void delete_KeyPair() 
    {
        try {
            Path file = Paths.get(s);
            String input = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
            JSONObject obj = (JSONObject) JSONValue.parse(input);
            System.out.println("Enter key to delete");
            String key=scan.next();
            obj.remove(key);
            System.out.println(obj);
            PrintWriter writer = new PrintWriter(new File(s));
            writer.append(obj.toJSONString());
            writer.flush();
            System.out.println("key value pair deleted successfully");
        } catch (IOException ex) {
            System.out.println("key does not exist");
        }
    }
    
}
