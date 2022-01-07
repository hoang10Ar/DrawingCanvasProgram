package version1;

public class ParseCommand {     // Class ParseCommand cho phép đọc 1 command và return 1 component tương ứng (nếu có)
    public static MyComponent parse(String command) {
        if(command == null || command.length() == 0) {
            return null;
        }

        String[] words = command.split(" ");        // Bỏ hết các khoảng trắng và thu về 1 mảng các từ trong command

        // Tạo 1 mảng có kích thước bằng mảng words cho biết word[i] có phải 1 số nguyên dương không
        // Nếu isPosIntWord[i] == true -> words[i] là 1 số nguyên dương
        boolean[] isPosIntWord = new boolean[words.length];

        for(int i = 0; i < words.length; i++) {
            try {
                // Phân giải words thứ i + 1, nếu không là số nguyên dương thì throw NumberFormatException
                int num = Integer.parseInt(words[i]);
                if(num <= 0) {      // Nếu từ này là 1 số <= 0
                    isPosIntWord[i] = false;    // -> words[i] không là 1 số nguyên dương
                } else {
                    isPosIntWord[i] = true;     // -> words[i] là 1 số nguyên dương
                }
            } catch (NumberFormatException e) {
                isPosIntWord[i] = false;    // Nếu không thể parseInt(words[i]) -> words[i] không là 1 số nguyên dương
            }
        }

        // Đặt rule cho các command = chữ cái đầu tiên + các tham số
        switch (words[0]) {
            case "C":   // Command này dùng đề tạo 1 canvas
                // Nếu command có 3 tham số: C + 1 số w + 1 số h
                if(words.length == 3 && isPosIntWord[1] == true && isPosIntWord[2] == true) {
                    // -> return về đối tượng canvas có width và height đã cho
                    return new CanvasComponent(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
                } else {        // Nếu command không có đúng 3 tham số
                    break;      // -> command này không hợp lệ
                }

            case "L":   // Command này dùng đề tạo 1 line
                // Nếu command có 5 tham số: L + 1 số x1 + 1 số y1 + 1 số x2 + 1 số y2
                if(words.length == 5 && isPosIntWord[1] == true && isPosIntWord[2] == true
                                    && isPosIntWord[3] == true && isPosIntWord[4] == true) {
                    return new LineComponent(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
                            Integer.parseInt(words[3]), Integer.parseInt(words[4]));
                } else {        // Nếu command không có đúng 5 tham số
                    break;
                }

            case "R":   // Command này dùng đề tạo 1 rectangle
                // Nếu command có 5 tham số: R + 1 số x1 + 1 số y1 + 1 số x2 + 1 số y2
                if(words.length == 5 && isPosIntWord[1] == true && isPosIntWord[2] == true
                                    && isPosIntWord[3] == true && isPosIntWord[4] == true) {
                    return new RectangleComponent(Integer.parseInt(words[1]), Integer.parseInt(words[2]),
                            Integer.parseInt(words[3]), Integer.parseInt(words[4]));
                } else {        // Nếu command không có đúng 5 tham số
                    break;
                }

            case "B":   // Command này dùng đề tạo 1 bucket fill
                // Nếu command có 4 tham số: C + 1 số x + 1 số y + 1 String color
                if(words.length == 4 && isPosIntWord[1] == true && isPosIntWord[2] == true
                                    && isPosIntWord[3] == false && words[3].length() == 1) {
                    return new BucketFillComponent(Integer.parseInt(words[1]), Integer.parseInt(words[2]), words[3]);
                } else {        // Nếu command không có đúng 4 tham số
                    break;      // -> command này không hợp lệ
                }
        }

        return null;
    }
}
