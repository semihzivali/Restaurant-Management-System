package Services.Abstract;

import java.util.List;
import Models.SalesReport;

public interface ISalesReportService {
	
	public List<SalesReport> getSalesReportData();
   
    public double getTotalSales();

}
