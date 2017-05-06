﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading;

namespace Service.Helpers
{
    public class Token
    {
        #region Settings
        private const double DEFAULT_TIME_LIFE = 30; //minutes
        private const int DEFAULT_PERIOD_DECREASE_TIME_LIFE = 60000; //miliseconds
        #endregion

        #region Instance Of
        public string Data { get; set; }
        public double TimeLife { get; set; }

        public Token(string str, string encode_type = "MD5")
        {
            Data = Security.Encode(str, encode_type);
            TimeLife = DEFAULT_TIME_LIFE;
            new Timer((s) =>
            {
                TimeLife -= 1;
                if (TimeLife <= 0 && TokenDictionary.ContainsKey(this))
                    TokenDictionary.Remove(this);
            }, null, 0, DEFAULT_PERIOD_DECREASE_TIME_LIFE);
        }
        #endregion

        public static Dictionary<Token, dynamic> TokenDictionary { get; set; }

        public static string Create(dynamic value, string str, double tlife = DEFAULT_TIME_LIFE)
        {
            if (TokenDictionary == null)
                TokenDictionary = new Dictionary<Token, dynamic>();
            var token = new Token(string.Format("{0}:{1}", TokenDictionary.Count, str), DateTime.Now.Second % 2 == 0 ? "MD5" : "SHA1");
            token.TimeLife = tlife;
            TokenDictionary.Add(token, value);
            return token.Data;
        }

        public static dynamic Get(string hash)
        {
            var entry = TokenDictionary.Where(x => x.Key.Data == hash)
                .Select(x => (KeyValuePair<Token, dynamic>?)x)
                .FirstOrDefault();
            if (entry != null && entry.HasValue)
            {
                return entry.Value.Value;
            }
            return null;
        }
    }
}