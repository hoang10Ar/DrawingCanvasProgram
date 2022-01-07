package version1;

public class RectangleComponent implements MyComponent {   // Class thể hiện 1 rectangle
    private int x1, y1, x2, y2;     // Tọa độ 2 điểm ở góc trên bên trái và góc dưới bên phải của rectangle

    public RectangleComponent(int x, int y, int m, int n) {
        x1 = x;
        y1 = y;
        x2 = m;
        y2 = n;

        // Điểm (x1 ; y1) nằm góc trên bên trái, điểm (x1 ; y1) nằm góc dưới bên phải
        // -> x1 phải <= x2, y1 phải <= y2, nếu không <= thì cập nhật lại giá trị cho x1, y1 là 1
        if(x1 > x2) {
            x1 = 1;
        }
        if(y1 > y2) {
            y1 = 1;
        }
    }

    public String toString() {
        return "Rectangle: ( " + x1 + " ; " + y1 + " ) --> ( " + x2 + " ; " + y2 + " )";
    }

    // Vẽ rectangle này lên 1 canvas
    @Override
    public void drawOn(CanvasComponent can) {
        // Nếu rectangle này nằm hoàn toàn bên trong canvas thì mới vẽ vào canvas
        if(x1 <= can.width && x2 <= can.width && y1 <= can.height && y2 <= can.height) {
            // Vẽ 4 cạnh của rectangle
            for(int i = y1; i <= y2; i++) {
                can.drawAPoint(x1, i, "x");
            }

            for(int i = y1; i <= y2; i++) {
                can.drawAPoint(x2, i, "x");
            }

            for(int i = x1; i <= x2; i++) {
                can.drawAPoint(i, y1, "x");
            }

            for(int i = x1; i <= x2; i++) {
                can.drawAPoint(i, y2, "x");
            }
        }
    }
}
