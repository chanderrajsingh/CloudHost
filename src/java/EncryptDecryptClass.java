import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptDecryptClass {
    
    SecretKey key;
    public void generateKey()
    {
        try {
            KeyGenerator  gen=KeyGenerator.getInstance("AES");
            gen.init(128);
            key=gen.generateKey();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
    String encdata;
    public void encryptData(String s){
        try{
        Cipher cp=Cipher.getInstance("AES");
        cp.init(Cipher.ENCRYPT_MODE,key);
        byte  b1[]=s.getBytes();
        byte b2[]=cp.doFinal(b1);
        encdata=new BASE64Encoder().encode(b2);
            System.out.println(encdata);
        }catch(Exception ex)
        {
            System.out.println(ex); 
        }
    }
    public void decryptData(String s){
        try{
        Cipher cp=Cipher.getInstance("AES");
        cp.init(Cipher.DECRYPT_MODE,key);
        
        byte  b1[]=new BASE64Decoder().decodeBuffer(s);  
        byte b2[]=cp.doFinal(b1);
            String s2=new String(b2);
            System.out.println(s2);
        }catch(Exception ex)
        {
            System.out.println(ex); 
        }
    }
    public static void main(String a[]){
        EncryptDecryptClass en =new EncryptDecryptClass();
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        en.generateKey();
        en.encryptData(s);
        en.decryptData(en.encdata);
        
    }
}
