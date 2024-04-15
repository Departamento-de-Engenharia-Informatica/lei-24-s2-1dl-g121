package pt.ipp.isep.dei.esoft.project.DTOs;

public class VFM {
     private String name;
        private String email;
        private String ID;
        private String phoneNumber;

        public VFM (String name, String email, String ID, String phoneNumber){
            this.name = name;
            this.email = email;
            this.ID = ID;
            this.phoneNumber = phoneNumber;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public String getID() {
            return ID;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
}
