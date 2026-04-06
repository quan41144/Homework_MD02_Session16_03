package hwss1603;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Message> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Nhập tên người gửi (hoặc 'exit' để thoát:");
                String name = sc.nextLine();
                if (name.equals("exit")) {
                    break;
                }
                System.out.println("Nhập nội dung tin nhắn:");
                String content = sc.nextLine();
                Message message = new Message(name, content);
                list.add(message);
                System.out.println("Nhập 'history' để xem lịch sử, hoặc 'filter' để lọc tin nhắn theo người gửi, hoặc 'date' để lọc theo ngày:");
                String choice = sc.nextLine();
                switch (choice) {
                    case "history":
                        for (Message m : list) {
                            System.out.println(m.toString());
                        }
                        break;
                    case "filter":
                        System.out.println("Nhập tên người gửi để lọc:");
                        String filterName = sc.nextLine();
                        for (Message m : list) {
                            if (m.getSender().equalsIgnoreCase(filterName)) {
                                System.out.println(m.toString());
                            }
                        }
                        break;
                    case "date":
                        System.out.println("Nhập ngày (dd-MM-yyyy):");
                        String date = sc.nextLine();
                        LocalDate searchDate = LocalDate.parse(date, dtf);
                        for (Message m : list) {
                            if (m.getTimestamp().toLocalDate().isEqual(searchDate)) {
                                System.out.println(m.toString());
                            }
                        }
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }
            catch (DateTimeParseException e) {
                System.out.println("Lỗi: Nhập sai định dạng ngày tháng (dd-MM-yyyy)");
            }
        }
    }
}
