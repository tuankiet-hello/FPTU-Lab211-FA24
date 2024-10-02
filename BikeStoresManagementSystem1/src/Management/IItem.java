/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.util.List;

public interface IItem<E> {
    //trả ra obj E thông qua id nhận vào
    E getItem(String id);
    //ktra có tồn tại kh bằng id nhận vào
    boolean checkExist(String id);
    //thêm sp
    boolean addItem(E e );
    //cập nhật
    boolean updateItem(E e );
    //xóa
    boolean deleteItem(String id);
    //lưu file
    void save();
    //đọc file
    void load();
    //trả ra vị trí của đối tượng trong danh sách thoog qua id
    int IndexById(String id);
    //trả ra danh sách
    List<E> getAll();
}
