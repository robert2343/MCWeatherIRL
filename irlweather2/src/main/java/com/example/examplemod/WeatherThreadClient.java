package com.example.examplemod;
import com.fazecast.jSerialComm.SerialPort;

public class WeatherThreadClient extends Thread {
    public void run()
    {
        SerialPort comPort = SerialPort.getCommPorts()[0];
        comPort.openPort();
        try {
            String currentString = "";
            while (true)
            {
                while (comPort.bytesAvailable() == 0)
                    Thread.sleep(20);

                byte[] readBuffer = new byte[comPort.bytesAvailable()];
                comPort.readBytes(readBuffer, readBuffer.length);
                String str = new String(readBuffer);
                String leftPart = "";
                String rightPart = "";
                if(str.indexOf("\n") == -1)
                {
                    currentString += str;
                }
                else {
                    leftPart = str.substring(0, str.indexOf("\n"));
                    rightPart = str.substring(str.indexOf("\n"));
                    //System.out.print(currentString + leftPart);
                    currentString = rightPart;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        comPort.closePort();
    }
}
