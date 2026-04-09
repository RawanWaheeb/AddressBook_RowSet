public class Main {
    public static void main(String[] args) {
        ContactDAO dao = new ContactDAO();
        dao.getAllContacts();
        dao.saveToXML();
        dao.readFromXML();
    }
}
