import java.net.*;
import java.io.*;

public class MulticastChatClient {
    public static void main(String[] args) throws Exception {
        //Default port number
        int portnumber = 50000;
        if (args.length >= 1) {
            portnumber = Integer.parseInt(args[0]);
        }

        //Create MulticastSocket
        MulticastSocket chatMulticastSocket = new MulticastSocket(portnumber);

        //Determine IP address of host, given name
        InetAddress group = InetAddress.getByName("225.4.5.6");

        //Joins multicast group
        chatMulticastSocket.joinGroup(group);

        //Prompt user to enter message
        String msg = "";
        System.out.println("Type message for server: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        msg = br.readLine();

        //Send message to Multicast address
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, portnumber);
        chatMulticastSocket.send(data);

        //Close socket
        chatMulticastSocket.close();
    }
}