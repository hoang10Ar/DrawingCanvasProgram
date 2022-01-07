package version1;

public class BucketFillComponent implements MyComponent {   // Class thể hiện 1 điểm và 1 màu sắc sẽ lan ra từ điểm đó
    int x, y;     // Tọa độ của điểm bắt đầu tô màu
    String color;   // Màu sẽ tô

    public BucketFillComponent(int m, int n, String col) {
        x = m;
        y = n;
        color = col;
    }

    public String toString() {
        return "Bucket fill: ( " + x + " ; " + y + " ) with color " + color ;
    }

    // Tô màu lên 1 canvas
    @Override
    public void drawOn(CanvasComponent can) {
        // Nếu phương thức drawOn() được gọi tại điểm (x ; y) có ký tự tại đó là ký tự "x" -> cạnh của 1 component nào đó
        // -> không tô màu lên cạnh này -> chỉ tiến hành tô màu bắt đầu tại 1 điểm mà ký tự tại đó không là ký tự "x"
        // Nếu màu được tô là ký tự "x" thì cũng không tô vì sẽ tạo ra "các cạnh ảo" nhưng thực sự không phải là 1 cạnh
        if(!can.getPoint(x, y).equals("x") && !color.equals("x")) {
            // Tô màu color bắt đầu tại điểm (x ; y) và lan ra các điểm có màu trùng với màu của điểm (x ; y)
            fill(can, x, y, can.getPoint(x, y));
        }
    }

    // Tô màu bắt đầu tại điểm (m ; n) và lan ra các điểm có màu trùng với màu startCol
    private void fill(CanvasComponent can, int m, int n, String startCol) {
        // Nếu điểm được tô có tọa độ hợp lệ và màu tại điểm này trùng với màu startCol
        if(m > 0 && m <= can.width && n > 0 && n <= can.height && can.getPoint(m, n).equals(startCol)) {
            can.drawAPoint(m, n, color);    // Tô điểm (m ; n) đang có màu startCol thành màu color

            // Tô màu cho 4 điểm xung quanh với điều kiện điểm đó có màu startCol
            // -> sẽ tô được tất cả các điểm có màu startCol thành màu color
            fill(can, m - 1, n, startCol);
            fill(can, m + 1, n, startCol);
            fill(can, m, n - 1, startCol);
            fill(can, m, n + 1, startCol);
        }
    }
}
