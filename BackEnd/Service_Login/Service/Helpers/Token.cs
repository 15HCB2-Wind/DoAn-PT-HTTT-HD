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
        public const double DEFAULT_TIME_LIFE = 30; //minutes
        public const int DEFAULT_PERIOD_DECREASE_TIME_LIFE = 60000; //miliseconds
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

        public static Dictionary<Token, object> TokenDictionary { get; set; }

        public static string Create(object value, string str)
        {
            if (TokenDictionary == null)
                TokenDictionary = new Dictionary<Token, object>();
            var token = new Token(str, DateTime.Now.Second % 2 == 0 ? "MD5" : "SHA1");
            TokenDictionary.Add(token, value);
            return token.Data;
        }

        public static object Get(string hash)
        {
            var entry = TokenDictionary.Where(x => x.Key.Data == hash)
                .Select(x => (KeyValuePair<Token, object>?)x)
                .FirstOrDefault();
            if (entry != null && entry.HasValue)
            {
                return entry.Value.Value;
            }
            return null;
        }

        public static bool Validate(string hash, Func<object, object[], bool> func, params object[] token_predicates)
        {
            var entry = TokenDictionary.Where(x => x.Key.Data == hash)
                .Select(x => (KeyValuePair<Token, object>?)x)
                .FirstOrDefault();
            if (entry != null && entry.HasValue)
            {
                entry.Value.Key.TimeLife = DEFAULT_TIME_LIFE;
                return func(entry.Value.Value, token_predicates);
            }
            return false;
        }
    }
}