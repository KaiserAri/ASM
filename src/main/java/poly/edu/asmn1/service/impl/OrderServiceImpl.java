package poly.edu.asmn1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poly.edu.asmn1.dto.CartItemDTO;
import poly.edu.asmn1.dto.OrderDTO;
import poly.edu.asmn1.dto.OrderDetailDTO;
import poly.edu.asmn1.entity.*;
import poly.edu.asmn1.mapper.OrderDetailMapper;
import poly.edu.asmn1.mapper.OrderMapper;
import poly.edu.asmn1.repository.*;
import poly.edu.asmn1.service.CartService;
import poly.edu.asmn1.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired OrderRepository orderRepo;
    @Autowired OrderDetailRepository detailRepo;
    @Autowired ProductRepository productRepo;
    @Autowired CartService cart;
    @Autowired OrderMapper orderMapper;
    @Autowired OrderDetailMapper detailMapper;

    @Transactional
    @Override
    public OrderDTO create(Account account, String address, String phone, String note) {
        Order order = new Order();
        order.setAccount(account);
        order.setAddress(address);
        order.setPhone(phone);
        order.setNote(note);
        order.setStatus(2);
        orderRepo.save(order);

        for (CartItemDTO item : cart.getItems()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setPrice(item.getPrice());
            detail.setQuantity(item.getQuantity());
            Product p = productRepo.findById(item.getId()).get();
            detail.setProduct(p);
            detailRepo.save(detail);
        }
        cart.clear();
        return orderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> findByUsername(String username) {
        return orderRepo.findByUsername(username).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDTO> findDetailsByOrderId(Long orderId) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order != null) {
            return order.getOrderDetails().stream()
                    .map(detailMapper::toDTO)
                    .collect(Collectors.toList());
        }
        return null;
    }

    // --- LOGIC CHO ADMIN (BỔ SUNG) ---

    @Override
    public List<OrderDTO> findAll() {
        // Slide 6 & 7: Lấy toàn bộ đơn hàng và chuyển sang DTO sạch sẽ
        return orderRepo.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        // Tìm đơn hàng, cập nhật status và lưu lại
        Order order = orderRepo.findById(id).orElse(null);
        if(order != null) {
            order.setStatus(status);
            orderRepo.save(order);
        }
    }
}