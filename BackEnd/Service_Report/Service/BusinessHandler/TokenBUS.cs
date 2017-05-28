using DomainData;
using Newtonsoft.Json;
using Service.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Web;

namespace Service.BusinessHandler
{
    public class TokenBUS
    {
        public static bool tokenCheck(BodyRequest request, BodyResponse response, int role)
        {
            if (!Configs.DEBUG_MODE)
            {
                //var thread = new Thread((object t) =>
                //{
                    int times = 3;
                    bool fail = true;
                    var client = new HttpClient();

                    CheckTokenRequest ctRequest = new CheckTokenRequest();
                    ctRequest.Token = request.Token;
                    ctRequest.TokenPassword = Configs.TOKEN_PASSWORD;
                    ctRequest.Role = role;

                    do
                    {
                        try
                        {
                            var result = client.PostAsync(Configs.CHECK_TOKEN, new StringContent(JsonConvert.SerializeObject(ctRequest), Encoding.UTF8, "application/json")).Result.Content.ReadAsAsync<CheckTokenResponse>().Result;
                            fail = result == null;
                            if (!fail)
                            {
                                response.IsTokenTimeout = result.IsTokenTimeout;
                                if (result.IsError)
                                {
                                    response.Errors.Add("Không thể truy cập đến máy chủ.");
                                    response.IsError = true;
                                }
                                break;
                            }
                        }
                        catch { }
                    } while (fail && --times > 0);
                    //(t as Thread).Abort();
                //});
                //thread.Start(thread);
            }
            return !response.IsError && !response.IsTokenTimeout;
        }

        public static TokenData tokenData(BodyRequest request, BodyResponse response, int role)
        {
            TokenData tokenData = null;
            if (!Configs.DEBUG_MODE)
            {
                //var thread = new Thread((object t) =>
                //{
                    int times = 3;
                    bool fail = true;
                    var client = new HttpClient();

                    CheckTokenRequest ctRequest = new CheckTokenRequest();
                    ctRequest.Token = request.Token;
                    ctRequest.TokenPassword = Configs.TOKEN_PASSWORD;
                    ctRequest.Role = role;

                    do
                    {
                        try
                        {
                            var result = client.PostAsync(Configs.CHECK_TOKEN, new StringContent(JsonConvert.SerializeObject(ctRequest), Encoding.UTF8, "application/json")).Result.Content.ReadAsAsync<CheckTokenResponse>().Result;
                            fail = result == null;
                            if (!fail)
                            {
                                response.IsTokenTimeout = result.IsTokenTimeout;
                                if (result.IsError)
                                {
                                    response.Errors.Add("Không thể truy cập đến máy chủ.");
                                    response.IsError = true;
                                }
                                else
                                {
                                    tokenData = result.Data;
                                }
                                break;
                            }
                        }
                        catch { }
                    } while (fail && --times > 0);
                    //(t as Thread).Abort();
                //});
                //thread.Start(thread);
            }
            return tokenData;
        }
    }
}