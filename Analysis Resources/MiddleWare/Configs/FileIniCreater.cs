using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class FileIniCreater
{
    #region InstanceOf
    public Dictionary<string, string> Attributes { get; private set; }
    public string FilePath { get; private set; }
    public bool IsEdited { get; private set; }
    private FileIniCreater(string filepath, Dictionary<string, string> attributes = null)
    {
        if (attributes == null)
            Attributes = new Dictionary<string, string>();
        else
            Attributes = attributes;
        FilePath = filepath;
        IsEdited = false;
    }
    #endregion

    public static FileIniCreater Load(string filePath)
    {
        try
        {
            if (File.Exists(filePath))
            {
                var attributes = new Dictionary<string, string>();
                using (var reader = new StreamReader(filePath))
                {
                    string line;
                    while (!string.IsNullOrEmpty(line = reader.ReadLine()))
                    {
                        var att = line.Split(new char[] { '=' }, 2);
                        try { attributes.Add(att[0], att[1]); }
                        catch { attributes.Add(att[0], string.Empty); }
                    }
                    reader.Close();
                }
                return new FileIniCreater(filePath, attributes);
            }
        }
        catch { }
        return Create(filePath);
    }

    public static FileIniCreater Create(string filePath)
    {
        try
        {
            return new FileIniCreater(filePath);
        }
        catch { }
        return null;
    }

    public bool Save()
    {
        try
        {
            if (IsEdited)
            {
                using (var writer = new StreamWriter(FilePath))
                {
                    foreach (var att in Attributes)
                    {
                        writer.WriteLine(string.Format("{0}={1}", att.Key, att.Value));
                    }
                    writer.Close();
                }
            }
            return true;
        }
        catch { }
        return false;
    }

    public void Set(string key, string value)
    {
        if (Attributes.ContainsKey(key))
            Attributes[key] = value;
        else
            Attributes.Add(key, value);
        IsEdited = true;
    }

    public string Get(string key, string defaultValue = "")
    {
        if (Attributes.ContainsKey(key))
            return Attributes[key];
        Set(key, defaultValue);
        return defaultValue;
    }

    public void Clear()
    {
        Attributes.Clear();
        IsEdited = true;
    }
}