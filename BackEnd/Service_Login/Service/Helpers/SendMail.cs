using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Threading;
using System.Threading.Tasks;
using System.Web;

namespace Service.Helpers
{
    public class EmailFormModel
    {
        public string MailTitle { get; set; }
        public string FromName { get; set; }
        public string FromEmail { get; set; }
        public string FromEmailPassword { get; set; }
        public string MailBody { get; set; }
        public string ToEmail { get; set; }
    }

    public class SendMail
    {
        #region Settings
        private const string HOST_MAIL = "smtp.gmail.com";
        private const int PORT = 25;
        #endregion

        public static void SendTo(EmailFormModel model)
        {
            var thread = new Thread((object t) =>
            {
                try
                {
                    var body = "<p>Email From: {0} ({1})</p><p>Message:</p><p>{2}</p>";
                    var message = new MailMessage();
                    message.To.Add(new MailAddress(model.ToEmail));
                    message.From = new MailAddress(model.FromEmail);
                    message.Subject = model.MailTitle;
                    message.Body = string.Format(body, model.FromName, model.FromEmail, model.MailBody);
                    message.IsBodyHtml = true;

                    using (var smtp = new SmtpClient())
                    {
                        var credential = new NetworkCredential
                        {
                            UserName = model.FromEmail,
                            Password = model.FromEmailPassword
                        };
                        smtp.Credentials = credential;
                        smtp.Host = HOST_MAIL;
                        smtp.Port = PORT;
                        smtp.EnableSsl = true;
                        smtp.SendMailAsync(message);
                    }
                }
                catch { }
                (t as Thread).Abort();
            });
            thread.Start(thread);
        }
    }
}