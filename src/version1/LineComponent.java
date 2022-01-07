package version1;

public class LineComponent implements MyComponent {     // Class thể hiện 1 line
    int x1, y1, x2, y2;     // Tọa độ 2 điểm ở 2 đầu line

    public LineComponent(int x, int y, int m, int n) {
        x1 = x;
        y1 = y;
        x2 = m;
        y2 = n;
    }

    public String toString() {
        return "Line: ( " + x1 + " ; " + y1 + " ) --> ( " + x2 + " ; " + y2 + " )";
    }

    // Vẽ line này lên 1 canvas
    @Override
    public void drawOn(CanvasComponent can) {
        // Nếu line này nằm hoàn toàn bên trong canvas thì mới vẽ vào canvas
        if(x1 <= can.width && x2 <= can.width && y1 <= can.height && y2 <= can.height) {
            if(x1 == x2) {      // Nếu 2 điểm này nằm trên 1 cột -> line này song song Oy
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);
                for(int i = minY; i <= maxY; i++) {     // Vẽ các điểm dựa vào tung độ y
                    can.drawAPoint(x1, i, "x");
                }
            } else if(y1 == y2) {   // Nếu 2 điểm này nằm trên 1 hàng -> line này song song Ox
                int minX = Math.min(x1, x2);
                int maxX = Math.max(x1, x2);
                for(int i = minX; i <= maxX; i++) {     // Vẽ các điểm dựa vào hoành độ x
                    can.drawAPoint(i, y1, "x");
                }
            }
        }
    }
}
