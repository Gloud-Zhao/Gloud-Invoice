package cn.gloud.invoiceManager.Entity;

public class InvoiceRespon {

    /**
     * ret : -10
     * msg : 缺少必要参数
     * data : {"invoiceid":0,"leftCode":"","invoiceCode":"","price":"","date":"","checkCode":"","orignStr":"","master":"","updater":"","update_date":0,"claim":0,"update_by":""}
     */

    private int ret;
    private String msg;
    private InvoiceBean data;

    public InvoiceRespon() {
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public InvoiceBean getData() {
        return data;
    }

    public void setData(InvoiceBean data) {
        this.data = data;
    }

    public static class InvoiceBean {
        /**
         * invoiceid : 0
         * leftCode :
         * invoiceCode :
         * price :
         * date :
         * checkCode :
         * orignStr :
         * master :
         * updater :
         * update_date : 0
         * claim : 0
         * update_by :
         */

        private int invoiceid;
        private String leftCode;
        private String invoiceCode;
        private String price;
        private String date;
        private String checkCode;
        private String orignStr;
        private String master;
        private String updater;
        private int update_date;
        private int claim;
        private int isDelete;
        private String update_by;

        public InvoiceBean() {
        }

        public int getInvoiceid() {
            return invoiceid;
        }

        public void setInvoiceid(int invoiceid) {
            this.invoiceid = invoiceid;
        }

        public String getLeftCode() {
            return leftCode;
        }

        public void setLeftCode(String leftCode) {
            this.leftCode = leftCode;
        }

        public String getInvoiceCode() {
            return invoiceCode;
        }

        public void setInvoiceCode(String invoiceCode) {
            this.invoiceCode = invoiceCode;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
        }

        public String getOrignStr() {
            return orignStr;
        }

        public void setOrignStr(String orignStr) {
            this.orignStr = orignStr;
        }

        public String getMaster() {
            return master;
        }

        public void setMaster(String master) {
            this.master = master;
        }

        public String getUpdater() {
            return updater;
        }

        public void setUpdater(String updater) {
            this.updater = updater;
        }

        public int getUpdate_date() {
            return update_date;
        }

        public void setUpdate_date(int update_date) {
            this.update_date = update_date;
        }

        public int getClaim() {
            return claim;
        }

        public void setClaim(int claim) {
            this.claim = claim;
        }

        public String getUpdate_by() {
            return update_by;
        }

        public void setUpdate_by(String update_by) {
            this.update_by = update_by;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        @Override
        public String toString() {
            return "InvoiceBean{" +
                    "invoiceid=" + invoiceid +
                    ", leftCode='" + leftCode + '\'' +
                    ", invoiceCode='" + invoiceCode + '\'' +
                    ", price='" + price + '\'' +
                    ", date='" + date + '\'' +
                    ", checkCode='" + checkCode + '\'' +
                    ", orignStr='" + orignStr + '\'' +
                    ", master='" + master + '\'' +
                    ", updater='" + updater + '\'' +
                    ", update_date=" + update_date +
                    ", claim=" + claim +
                    ", isDelete=" + isDelete +
                    ", update_by='" + update_by + '\'' +
                    '}';
        }
    }
}
