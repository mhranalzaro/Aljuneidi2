package com.example.aljuneidi.adminClasses;

public class userinformation {
private int ID;
private String ServerIP;
private String PrinterPassword;
private String PrinterID;
private int salesman;

        public userinformation(int ID, String ServerIP, int salesman, String  PrinterID, String PrinterPassword) {
        this.ID=ID;
        this.ServerIP = ServerIP;
        this.salesman = salesman;
        this.PrinterID=PrinterID;
        this.PrinterPassword=PrinterPassword;


        }
        public void setID(int ID) {
                this.ID = ID;
        }

        public void setServerIP(String serverIP) {
                ServerIP = serverIP;
        }

        public void setPrinterPassword(String printerPassword) {
                PrinterPassword = printerPassword;
        }

        public void setPrinterID(String printerID) {
                PrinterID = printerID;
        }

        public void setSalesman(int salesman) {
                this.salesman = salesman;
        }
        public int getID() {
                return ID;
        }

        public String getServerIP() {
                return ServerIP;
        }

        public String getPrinterPassword() {
                return PrinterPassword;
        }

        public String getPrinterID() {
                return PrinterID;
        }

        public int getSalesman() {
                return salesman;
        }


}
