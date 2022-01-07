package version1;

public interface MyComponent {      // Những class nào là component thì class đó phải implements interface này
    // Phương thức sẽ vẽ component hiện tại lên 1 canvas cho trước
    // Nếu component hiện tại là 1 canvas thì canvas hiện tại cập nhật lại canvas đã cho
    public void drawOn(CanvasComponent can);
}
