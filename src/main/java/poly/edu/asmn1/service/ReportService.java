package poly.edu.asmn1.service;

import poly.edu.asmn1.dto.ReportDTO;
import java.util.List;

public interface ReportService {
    List<ReportDTO> getInventoryByCategory();
    List<ReportDTO> getRevenueByCategory();
}