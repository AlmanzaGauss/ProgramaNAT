/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author falag
 */
public class ServidorUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            DatagramSocket miSocket= new DatagramSocket(5005);
            byte[] buffer = new byte[1824];
            while(true)
            {
                DatagramPacket peticion= new DatagramPacket(buffer, buffer.length);
              miSocket.receive(peticion);
         System.out.println("ip:"+peticion.getAddress());
         
         System.out.println("Canonical"+peticion.getAddress().getCanonicalHostName());
         System.out.println("SocketAddress"+peticion.getSocketAddress());
         System.out.println("Algo"+peticion.getAddress().getHostAddress());
         System.out.println("Mas"+peticion.getAddress().getHostName());
         
         System.out.println("Puerto:"+peticion.getPort());
         System.out.println("Mensaje:"+new String(peticion.getData(),0,peticion.getLength()));
              //InetAddress ip=peticion.getAddress();
              
              
              /*Aqui manadamos al Cliente*/
              
              String resp= new Date().toString();
            InetAddress addr= peticion.getAddress();
            int port=peticion.getPort();
            DatagramPacket paqueteEnvio= new DatagramPacket(resp.getBytes(),resp.length(),addr,port);
            System.out.println("Enviando"+new String(paqueteEnvio.getData()));
            miSocket.send(paqueteEnvio);
            //miSocket.close();
              
                
            }
        } catch (SocketException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
