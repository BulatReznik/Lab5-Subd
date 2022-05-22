
import logic.*;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    Session session;

    public ConsoleUI(Session session) {
        this.session = session;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            String[] params = input.split(" ", 2);
            switch (params[0]) {
                case ("мануал") -> printManual();
                case ("выход") -> session.close();
                case ("customer") -> doCustomerAction(params[1]);
                case ("receipt") -> doReceiptAction(params[1]);
                case ("device_receipt") -> doDeviceReceiptAction(params[1]);
                case ("device") -> doDeviceAction(params[1]);
                case ("type") -> doTypeAction(params[1]);
                case ("price") -> doPriceAction(params[1]);

                case ("запрос") -> executeFreeSqlQuery();
                default -> throw new IllegalArgumentException();
            }
        } catch (PersistenceException e) {
            System.out.println("Эту сущность нельзя удалить, сначала удалите связанную строку");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода данных");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вы ввели недостаточное количество аргументов");
        } catch (NullPointerException e) {
            System.out.println("Запрашивамых данных не существует");
        } catch (Exception e) {
            System.out.println("Неизвестная ошибка");
        }
    }

    public void printManual() {
        System.out.println("--Руководство пользователя--");
        System.out.println("Если вы хотите работать с сущностями введите 'название_сущности create/read/update/delete'");
        System.out.println("Вы можете выполнить запрос в базу введя команду 'запрос'(Потребуется пароль)");
        System.out.println("Если хотите выйти, введите 'выйти'");
    }

    public void executeFreeSqlQuery(){
        System.out.print("Введите пароль:");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if(!answer.equals("453916453916")){
            System.out.println("Неверный пароль");
            return;
        }
        String sql = scanner.nextLine();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
    }

    public void doReceiptAction(String action) {
        ReceiptLogic logic = new ReceiptLogic(session);
        if ("create".equals(action)) {
            System.out.println("Введите 'дату время имякассира'");//////
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 3);
            logic.createReceipt(Date.valueOf(attributes[0]), Time.valueOf(attributes[1]), attributes[2]);
        }
        else if ("read".equals(action)) {
            var list = logic.readReceipt();
            list.forEach(System.out::println);
            System.out.println("Введите что-нибудь для продолжения"); ////
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        else if ("update".equals(action)) {
            System.out.println("Введите 'id дату время имякассира'"); /////
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 4);
            logic.updateReceipt(Integer.parseInt(attributes[0]), Date.valueOf(attributes[1]), Time.valueOf(attributes[2]), attributes[3]);
        }
        else if ("delete".equals(action)) {
            System.out.println("Введите 'id'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            logic.deleteReceipt(Integer.parseInt(input));
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void doCustomerAction(String action) {
        CustomerLogic logic = new CustomerLogic(session);
        if ("create".equals(action)) {
            System.out.println("Введите 'ФИО номеркарточки телефонныйномер'"); ///////
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 3);
            logic.createCustomer(attributes[0], attributes[1], attributes[2]);
        }
        else if ("read".equals(action)) {
            var list = logic.readCustomers();
            list.forEach(System.out::println);
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        else if ("update".equals(action)) {
            System.out.println("Введите 'id ФИО номеркарточки телефонныйномер'"); //////
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 4);
            logic.updateCustomer(Integer.parseInt(attributes[0]), attributes[1], attributes[2], attributes[3]);
        }
        else if ("delete".equals(action)) {
            System.out.println("Введите 'id'");
            Scanner scanner = new Scanner(System.in);
            String input1 = scanner.nextLine();
            logic.deleteCustomer(Integer.parseInt(input1));
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    public void doDeviceReceiptAction(String action) {
        DeviceReceiptLogic logic = new DeviceReceiptLogic(session);
        if ("create".equals(action)) {
            System.out.println("Введите 'девайсID чекID количество'"); /////
            System.out.println("Паттерн даты 'yyyy-mm-dd'");

            Scanner scanner1 = new Scanner(System.in);
            String input1 = scanner1.nextLine();
            String[] attributes = input1.split(" ", 3);
            logic.createDeviceReceipt(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]));
        } else if ("read".equals(action)) {
            var list = logic.readDeviceReceipt();
            list.forEach(System.out::println);
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        } else if ("update".equals(action)) {
            System.out.println("Введите 'id девайсID чекID количество'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 4);
            logic.updateDeviceReceipt(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]), Integer.parseInt(attributes[3]));
        } else if ("delete".equals(action)) {
            System.out.println("Введите 'id'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            logic.deleteDeviceReceipt(Integer.parseInt(input));

        } else {
            throw new IllegalArgumentException();
        }
    }

    public void doDeviceAction(String action) {
        DeviceLogic logic = new DeviceLogic(session);
        if ("create".equals(action)) {
            System.out.println("Введите 'название типПродажи idType'");
            Scanner scanner1 = new Scanner(System.in);
            String input1 = scanner1.nextLine();
            String[] attributes = input1.split(" ", 3);
            logic.createDevice(attributes[0], attributes[1],Integer.parseInt(attributes[2]) );
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        else if ("read".equals(action)) {
            var list = logic.readDevices();
            list.forEach(System.out::println);
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        else if ("update".equals(action)) {
            System.out.println("Введите 'id название типПродажи'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 4);
            logic.updateDevice(Integer.parseInt(attributes[0]), attributes[1], attributes[2], Integer.parseInt(attributes[3]));
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner2 = new Scanner(System.in);
            scanner2.nextLine();
        }
        else if ("delete".equals(action)) {
            System.out.println("Введите 'id'");
            Scanner scanner1 = new Scanner(System.in);
            String input1 = scanner1.nextLine();
            logic.deleteDevice(Integer.parseInt(input1));
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }else {
            throw new IllegalArgumentException();
        }
    }
    public void doTypeAction(String action){
        TypeLogic logic = new TypeLogic(session);
        if ("create".equals(action)) {
            System.out.println("Введите 'Категория");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 1);
            logic.createType(attributes[0]);
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner2 = new Scanner(System.in);
            scanner2.nextLine();
        }
        else if ("read".equals(action)) {
            var list = logic.readType();
            list.forEach(System.out::println);
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        else if ("update".equals(action)) {
            System.out.println("Введите 'id категория'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 3);
            logic.updateType(Integer.parseInt(attributes[0]), attributes[1]);
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner2 = new Scanner(System.in);
            scanner2.nextLine();
        }
        else if ("delete".equals(action)) {
            System.out.println("Введите 'id'");
            Scanner scanner1 = new Scanner(System.in);
            String input1 = scanner1.nextLine();
            logic.deleteType(Integer.parseInt(input1));
        }else {
            throw new IllegalArgumentException();
        }
    }

    public void doPriceAction(String action) {
        PriceLogic logic = new PriceLogic(session);
        if ("create".equals(action)) {
            System.out.println("Введите 'цена датаИзмения'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 2);
            logic.createPrice(Integer.parseInt(attributes[0]), Date.valueOf(attributes[1]));
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner2 = new Scanner(System.in);
            scanner2.nextLine();
        }
        else if ("read".equals(action)) {
            var list = logic.readPrice();
            list.forEach(System.out::println);
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        else if ("update".equals(action)) {
            System.out.println("Введите 'id цена датаИзмения'");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] attributes = input.split(" ", 3);
            logic.updatePrice(Integer.parseInt(attributes[0]), Integer.parseInt(attributes[1]), Date.valueOf(attributes[3]));
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner2 = new Scanner(System.in);
            scanner2.nextLine();
        }
        else if ("delete".equals(action)) {
            System.out.println("Введите 'id'");
            Scanner scanner1 = new Scanner(System.in);
            String input1 = scanner1.nextLine();
            logic.deletePrice(Integer.parseInt(input1));
            System.out.println("Введите что-нибудь для продолжения");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }else {
            throw new IllegalArgumentException();
        }
    }
}
