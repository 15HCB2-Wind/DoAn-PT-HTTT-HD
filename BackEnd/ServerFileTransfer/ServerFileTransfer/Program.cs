using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace ServerFileTransfer
{
    class Program
    {
        //==================================================
        //==================================================
        //==================================================
        //  Configs

        static int PeriodSecond
        {
            get
            {
                return 60;
            }
        }

        #region Send
        static string SBackup
        {
            get
            {
                return @"SBackup\";
            }
        }

        static string SentFile
        {
            get
            {
                return "ServiceLogin_SQLlog";
            }
        }

        static dynamic[] ListDests
        {
            get
            {
                return new dynamic[] 
                {
                    new { Address = "localhost", Port = 44444 },
                };
            }
        }
        #endregion

        #region Receive
        static string RBackup
        {
            get
            {
                return "RBackup";
            }
        }

        static string ReceivedFolder
        {
            get
            {
                return @"RFolder\";
            }
        }

        static string ReceivedFileName
        {
            get
            {
                return "ServiceLogin_SQLlog";
            }
        }

        static int ReceivePort
        {
            get
            {
                return 44444;
            }
        }
        #endregion

        //==================================================
        //==================================================
        //==================================================
        //  Code

        static void Main(string[] args)
        {
            //auto send file
            new Thread(() =>
            {
                while (true)
                {
                    Thread.Sleep(PeriodSecond * 1000);
                    //backup
                    File.Copy(SentFile, SBackup + DateTime.Now);
                    //send file
                    foreach (var item in ListDests)
                    {
                        SRtcp.SendTCP(SentFile, item.Address, item.Port);
                    }
                }
            }).Start();

            //listen
            new Thread(() =>
            {
                SRtcp.ReceiveTCP(ReceivedFileName, ReceivePort);
            }).Start();

            //watch file
            new FileWatcher(new FileSystemWatcher
            {
                IncludeSubdirectories = false,
                NotifyFilter = NotifyFilters.LastWrite,
                Path = ReceivedFolder,
                EnableRaisingEvents = true,
            }).ExecuteFunction = (s, e) =>
            {
                using (var reader = new StreamReader(e))
                {
                    Console.WriteLine(reader.ReadToEnd());
                }
            };
        }
    }
}
