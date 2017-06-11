using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Permissions;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

public class FileWatcher
{
    public delegate void WatcherExecute(FileWatcher sender, string filePath);



    public FileSystemWatcher Watcher { get; private set; }
    public string WatchedFolder { get { return Watcher.Path; } set { Watcher.Path = value; } }
    public string WatchedFilter { get { return Watcher.Filter; } set { Watcher.Filter = value; } }
    public WatcherExecute ExecuteFunction { get; set; }



    public FileWatcher(FileSystemWatcher watcher)
    {
        //init
        Watcher = watcher;
        Watcher.Changed += (a, b) =>
        {
            try
            {
                //
                Watcher.EnableRaisingEvents = false;
                //
                ExecuteFunction(this, b.FullPath);
            }
            finally
            {
                Watcher.EnableRaisingEvents = true;
            }
        };
    }
}

