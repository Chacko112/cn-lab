import java.io.*;
import java.net.*;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket("localhost",5000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
        );

        System.out.print("Enter the name of the file to request: ");
        String fileName = sc.nextLine();

        out.println(fileName);

        String pidLine = in.readLine();
        String status = in.readLine();

        System.out.println();
        System.out.println("Response from server (" + pidLine + ")");

        if (status.equals("FOUND")) {
            System.out.println("----- File Contents -----");

            String line;

            while (!(line = in.readLine()).equals("<<END>>")) {
                System.out.println(line);
            }

        } else {
            String message = in.readLine();

            in.readLine();

            System.out.println(message);
        }

        socket.close();
    }
}