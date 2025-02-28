package Services.Concrete;

import Models.SalesReport;
import Repository.Abstract.ISalesReportRepository;
import Services.Abstract.ISalesReportService;

import java.util.List;

public class SalesReportService implements ISalesReportService{

	private final ISalesReportRepository salesReportRepository;
	
    public SalesReportService(ISalesReportRepository _salesReportRepository) {
        this.salesReportRepository = _salesReportRepository;
    }

    public List<SalesReport> getSalesReportData() {
        return salesReportRepository.getSalesReportData();
    }
   
    public double getTotalSales() {
        return salesReportRepository.getTotalSales();
    }
}
