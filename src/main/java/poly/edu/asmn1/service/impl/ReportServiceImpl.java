package poly.edu.asmn1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import poly.edu.asmn1.dto.ReportDTO;
import poly.edu.asmn1.repository.ProductRepository;
import poly.edu.asmn1.service.ReportService;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired ProductRepository productRepo;

    @Override
    public List<ReportDTO> getInventoryByCategory() {
        return productRepo.getInventoryByCategory();
    }

    @Override
    public List<ReportDTO> getRevenueByCategory() {
        return productRepo.getRevenueByCategory();
    }
}