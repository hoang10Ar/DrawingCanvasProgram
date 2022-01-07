package version1;

import java.util.Scanner;

public class Main {     // Class Main cho phép thực thi chương trình
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String command;     // Lưu command vừa được nhập
        CanvasComponent canvas;     // Lưu canvas để chứa các hình được vẽ

        // Đầu tiên phải tạo ra 1 canvas
        while(true) {
            System.out.print("Enter a command to create a canvas: ");
            command = input.nextLine();

            if(command == null || command.length() == 0) {
                continue;
            }

            if(command.length() == 1 && command.equals("Q")) {      // Nếu người dùng nhập 1 ký tự "Q"
                System.out.println("Quit!!!");
                return;
            }

            // Thu về 1 component (canvas, line, rectangle,...) tương ứng với command vừa nhập
            MyComponent comp = ParseCommand.parse(command);

            // Nếu comp là 1 thể hiện của lớp CanvasComponent -> command này dùng để tạo 1 canvas -> command hợp lệ
            if(comp instanceof CanvasComponent) {
                canvas = (CanvasComponent) comp;    // Cập nhật comp này vào tham chiếu canvas
                break;
            } else {
                // Nếu comp không phải 1 thể hiện của lớp CanvasComponent -> command này không dùng để tạo canvas
                // -> nhập lại command cho tới khi tạo 1 canvas
                System.out.println("This is not a command to create a canvas!");
                continue;
            }
        }

       canvas.printCanvas();        // In canvas

        // Cho nhập các command để vẽ các component khác trên canvas vừa tạo
        while(true) {
            System.out.print("Enter a command: ");
            command = input.nextLine();

            if(command == null || command.length() == 0) {
                continue;
            }

            MyComponent comp = ParseCommand.parse(command);

            // Nếu comp là null -> có 2 trường hợp có thể xảy ra
            if(comp == null) {
                if(command.length() == 1 && command.equals("Q")) {      // Command vừa nhập dùng để Quit
                    System.out.println("Quit!!!");
                    break;
                } else {
                    // Command vừa nhập không phải 1 command hợp lệ nên phương thức parse() không return 1 component nào
                    System.out.println("This is not a valid command!");
                    continue;
                }
            }

            // Nếu comp là 1 component nào đó thì tiến hành vẽ comp đó lên canvas
            comp.drawOn(canvas);

            canvas.printCanvas();       // In canvas
        }

    }
}
