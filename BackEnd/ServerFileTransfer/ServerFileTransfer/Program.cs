using System;
using System.Collections.Generic;
using System.Diagnostics;
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
                return 10;
            }
        }

        #region Send
        static string SendFolder
        {
            get
            {
                return @"C:\Users\19101994\Documents\GitHub\DoAn-PT-HTTT-HD\BackEnd\ServerFileTransfer\ServerFileTransfer\bin\Debug\send\";
            }
        }

        static string SBackup
        {
            get
            {
                return @"C:\Users\19101994\Documents\GitHub\DoAn-PT-HTTT-HD\BackEnd\ServerFileTransfer\ServerFileTransfer\bin\Debug\send\backup\";
            }
        }

        static dynamic[] ListDests
        {
            get
            {
                return new dynamic[] 
                {
                    new { Address = "192.168.22.108", Port = 44444 },
                };
            }
        }
        #endregion

        #region Receive
        static string RBackup
        {
            get
            {
                return @"C:\Users\19101994\Desktop\receive\backup\";
            }
        }

        static string ReceivedFolder
        {
            get
            {
                return @"C:\Users\19101994\Desktop\receive\";
            }
        }

        static int ReceivePort
        {
            get
            {
                return 44444;
            }
        }

        static string SqlInstanceName
        {
            get
            {
                return @"CHIPHONG\SQLEXPRESS_2012";
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
                    foreach (var file in Directory.GetFiles(SendFolder))
                    {
                        File.Copy(file, SBackup + DateTime.Now.Ticks);
                        //send file
                        foreach (var item in ListDests)
                        {
                            SRtcp.SendTCP(file, item.Address, item.Port);
                        }
                        File.Delete(file);
                    }
                }
            }).Start();

            //listen
            new Thread(() =>
            {
                SRtcp.ReceiveTCP(ReceivedFolder + DateTime.Now.Ticks, ReceivePort);
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
                //backup
                var backupFileName = RBackup + DateTime.Now.Ticks;
                File.Copy(e, backupFileName);
                //read sql
                Process.Start("sqlcmd", string.Format("-S {0} -i {1} -o {2}_out", SqlInstanceName, e, backupFileName));
                File.Delete(e);
            };

            Console.WriteLine("Press any key to exit...");
            Console.Read();
        }
    }
}
