package poly.edu.asmn1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.asmn1.dto.OrderDetailDTO;
import poly.edu.asmn1.mapper.OrderDetailMapper;
import poly.edu.asmn1.repository.OrderDetailRepository;
import poly.edu.asmn1.service.OrderDetailService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository detailRepo;

    @Autowired
    private OrderDetailMapper detailMapper;

    @Override
    public List<OrderDetailDTO> findByOrderId(Long orderId) {
        // Slide 7: Truy vấn tất cả chi tiết có OrderId tương ứng
        // Sau đó dùng Stream để map thủ công sang DTO
        return detailRepo.findAll().stream()
                .filter(d -> d.getOrder().getId().equals(orderId))
                .map(detailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO findById(Long id) {
        return detailRepo.findById(id)
                .map(detailMapper::toDTO)
                .orElse(null);
    }
}