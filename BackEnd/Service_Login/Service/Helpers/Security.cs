using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

public static class Security
{
    #region Encode One-way
	public static string EncodeMD5(string hash)
	{
		using (MD5 md5Hash = MD5.Create())
		{
			byte[] data = md5Hash.ComputeHash(Encoding.UTF8.GetBytes(hash));
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < data.Length; i++)
			{
				builder.Append(data[i].ToString("x2"));
			}

			return builder.ToString();
		}
	}

	public static string EncodeSHA1(string hash)
	{
		using (SHA1 sha1 = SHA1.Create())
		{
			byte[] data = sha1.ComputeHash(Encoding.UTF8.GetBytes(hash));

			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < data.Length; i++)
			{
                builder.Append(data[i].ToString("x2"));
			}

			return builder.ToString();
		}
	}

    public static string Encode(string hash, string encode_type = "MD5")
    {
        if (encode_type.Equals("MD5"))
            return EncodeMD5(hash);
        return EncodeSHA1(hash);
    }
    #endregion

    #region Custom Encrypt-Decrypt
    private static string KEY_CODE = "465339662332334498465131354686356656";

    public static string Encrypt(string str)
    {
        byte[] a = Encoding.UTF8.GetBytes(KEY_CODE);
        byte[] b = Encoding.UTF8.GetBytes(str);
        for (int i = 0; i < a.Length; i++)
        {
            if (i < b.Length)
                a[i] += b[i];
        }
        return Encoding.UTF8.GetString(a);
    }

    public static string Decrypt(string hash)
    {
        byte[] a = Encoding.UTF8.GetBytes(hash);
        byte[] b = Encoding.UTF8.GetBytes(KEY_CODE);
        for (int i = 0; i < a.Length; i++)
        {
            if (i < b.Length)
                a[i] -= b[i];
        }
        return Encoding.UTF8.GetString(a);
    }
    #endregion
}
