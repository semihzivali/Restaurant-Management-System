package Repository.Abstract;

import java.util.List;
import Models.SalesReport;

public interface ISalesReportRepository {
	
	 public List<SalesReport> getSalesReportData();
	 
	 public double getTotalSales();

}
