using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

public class SRtcp
{
    public static int BUFFER_SIZE { get { return 8192; } }

    public static void SendTCP(string filePath, string ipAddress, Int32 port)
    {
        byte[] SendingBuffer = null;
        TcpClient client = null;
        NetworkStream netstream = null;
        try
        {
            client = new TcpClient(ipAddress, port);
            netstream = client.GetStream();

            FileStream Fs = new FileStream(filePath, FileMode.Open, FileAccess.Read);
            BinaryReader writer = new BinaryReader(Fs, Encoding.UTF8);

            int NoOfPackets = Convert.ToInt32(Math.Ceiling(Convert.ToDouble(Fs.Length) / Convert.ToDouble(BUFFER_SIZE)));
            int TotalLength = (int)Fs.Length, CurrentPacketLength;
            for (int i = 0; i < NoOfPackets; i++)
            {
                if (TotalLength > BUFFER_SIZE)
                {
                    CurrentPacketLength = BUFFER_SIZE;
                    TotalLength = TotalLength - CurrentPacketLength;
                }
                else
                    CurrentPacketLength = TotalLength;
                SendingBuffer = new byte[CurrentPacketLength];
                writer.Read(SendingBuffer, 0, CurrentPacketLength);

                netstream.Write(SendingBuffer, 0, (int)SendingBuffer.Length);
            }

            Fs.Close();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
        }
        finally
        {
            netstream.Close();
            client.Close();
        }
    }

    public static void ReceiveTCP(string fileSave, int portN)
    {
        TcpListener Listener = null;
        try
        {
            Listener = new TcpListener(IPAddress.Any, portN);
            Listener.Start();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
        }

        byte[] RecData = new byte[BUFFER_SIZE];
        int RecBytes;

        while(true)
        {
            TcpClient client = null;
            NetworkStream netstream = null;
            try
            {
                if (Listener.Pending())
                {
                    client = Listener.AcceptTcpClient();
                    netstream = client.GetStream();

                    FileStream Fs = new FileStream(fileSave, FileMode.OpenOrCreate, FileAccess.Write);
                    BinaryWriter writer = new BinaryWriter(Fs, Encoding.UTF8);

                    while ((RecBytes = netstream.Read(RecData, 0, RecData.Length)) > 0)
                    {
                        writer.Write(RecData, 0, RecBytes);
                    }
                    Fs.Close();

                    netstream.Close();
                    client.Close();
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                //netstream.Close();
            }
        }
    }
}
