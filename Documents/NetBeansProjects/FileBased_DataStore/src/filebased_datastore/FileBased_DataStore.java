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
import java.io.*;
import java.util.*;
public class FileBased_DataStore extends Thread{

    /**
     * @param args the command line arguments
     */
    public void run(){  
        CRDOperations();
    }
   synchronized static void CRDOperations()
    {
       int flag=1;
       Scanner scan=new Scanner(System.in);
       CRD crd=new CRD();
       while(flag==1)
       {
        System.out.println("Enter your choice :");
        System.out.println("1.Create 2.Read 3.Delete 4.Exit");
        int choice=scan.nextInt();
        switch(choice)
                {
                    case 1:
                        crd.create_KeyPair();
                        break;
                    case 2:
                        System.out.println("Enter key");
                        String key=scan.next();
                        String value=crd.read_KeyPair(key);
                        if(value==null)
                            System.out.println("key does not exist");
                        else
                        System.out.println(key+" : "+value);
                        break;
                    case 3:
                        crd.delete_KeyPair();
                        break;
                    case 4:
                        flag=0;
                       
                }
       }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        FileBased_DataStore t1=new FileBased_DataStore();
        t1.start();
        FileBased_DataStore t2=new FileBased_DataStore();
        t2.start();
    }
    
}
