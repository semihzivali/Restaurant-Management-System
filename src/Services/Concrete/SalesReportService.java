package Services.Concrete;

import Models.SalesReport;
import Repository.Concrete.SalesReportRepository;

import java.util.List;

public class SalesReportService {

    private final SalesReportRepository salesReportRepository = new SalesReportRepository();

    public List<SalesReport> getSalesReportData() {
        return salesReportRepository.getSalesReportData();
    }

   
    public double getTotalSales() {
        return salesReportRepository.getTotalSales();
    }
}
