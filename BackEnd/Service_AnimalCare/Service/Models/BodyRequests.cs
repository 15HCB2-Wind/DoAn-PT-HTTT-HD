using DomainData;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Service.Models
{
    public abstract class BodyRequest
    {
        public string Token { get; set; }
    }

    public class PhanCongRequest : BodyRequest
    {
        public PhanCong Data { get; set; }
    }

    public class ListNhanVienChiNhanh : BodyRequest
    {
        public class NhanVien
        {
            public string manhanvien { get; set; }
            public string hoten { get; set; }
        }
        public List<NhanVien> Data { get; set; }
    }

    public class CheckTokenRequest : BodyRequest
    {
        public string TokenPassword;
        public int Role;
    }

    public class ChamSocRequest : BodyRequest
    {
        public ChamSoc Data { get; set; }
    }

    public class TinhTrangBoRequest : BodyRequest
    {
        public TinhTrangBo Data { get; set; }
        public string TinhTrang { get; set; }
    }

    public class UpdateMilkRequest : BodyRequest
    {
        public float Value { get; set; }
    }

    public class UpdateCow : BodyRequest
    {
        public string Id { get; set; }
        public string NewState { get; set; }
    }

    public class ReportTinhTrangBoRequest : BodyRequest
    {
        public DateTime NgayBatDau { get; set; }
    }
}