package version1;

public class CanvasComponent implements MyComponent {   // Class thể hiện 1 canvas và cho phép vẽ vào các ô trên canvas
    int width, height;      // Width và height của canvas
    String[][] canvas;      // Mảng 2 chiều thể hiện 1 canvas hình chữ nhật

    public CanvasComponent(int w, int h) {
        width = w;
        height = h;
        canvas = new String[h + 2][w + 2];      // Canvas có h+2 hàng (h hàng bên trong và 2 hàng bìa) và w+2 cột

        // Cập nhật 1 canvas rỗng
        for(int i = 0; i < h + 2; i++) {
            for(int j = 0; j < w + 2; j++) {
                if (i == 0 || i == h + 1) {     // Nếu hàng đang xét là hàng đầu hoặc cuối
                    canvas[i][j] = "-";
                } else if(j == 0 || j == w + 1) {   // Nếu cột đang xét là cột đầu hoặc cuối
                    canvas[i][j] = "|";
                } else {
                    canvas[i][j] = " ";
                }
            }
        }
    }

    // Vẽ vào điểm có tọa độ (x ; y) trong canvas bằng ký tự kyHieu
    public void drawAPoint(int x, int y, String kyHieu) {
        canvas[y][x] = kyHieu;
    }

    // Lấy điểm (x ; y) trong canvas
    public String getPoint(int x, int y) {
        return canvas[y][x];
    }

    // In canvas
    public void printCanvas() {
        for(int i = 0; i < height + 2; i++) {
            for(int j = 0; j < width + 2; j++) {
                System.out.print(canvas[i][j]);
            }
            System.out.println();
        }
    }

    public String toString() {
        return "Canvas: width = " + width + ", height = " + height;
    }

    // Khi canvas này vẽ lên 1 canvas khác
    @Override
    public void drawOn(CanvasComponent can) {   // Canvas của tham chiếu can sẽ được cập nhật lại giống hệt với canvas này
        can.width = width;
        can.height = height;
        can.canvas = new String[height + 2][width + 2];

        for(int i = 0; i < height + 2; i++) {
            for(int j = 0; j < width + 2; j++) {
                can.canvas[i][j] = canvas[i][j];
            }
        }
    }
}
