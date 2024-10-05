package com.zinu.inventory.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BilledItemRepository extends JpaRepository<BilledItem, Long> {

       @Query("SELECT COALESCE(SUM(bi.quantity * p.sellingPrice), 0) AS totalSales, " +
                     "COALESCE(SUM(bi.quantity * (p.sellingPrice - p.price)), 0) AS totalProfit " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p")
       List<Object[]> calculateTotalSalesAndProfit();


       @Query("SELECT COALESCE(SUM(bi.quantity * p.sellingPrice), 0) AS totalSales, " +
                     "COALESCE(SUM(bi.quantity * (p.sellingPrice - p.price)), 0) AS totalProfit " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p " +
                     "WHERE bi.createdDate BETWEEN :startDate AND :endDate")
       List<Object[]> calculateTotalSalesAndProfit(@Param("startDate") LocalDateTime startDate,
                     @Param("endDate") LocalDateTime endDate);

       @Query("SELECT p.id, p.name, " +
                     "COALESCE(SUM(bi.quantity * p.sellingPrice), 0) AS totalSales, " +
                     "COALESCE(SUM(bi.quantity * (p.sellingPrice - p.price)), 0) AS totalProfit " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p " +
                     "GROUP BY p.id, p.name")
       List<Object[]> calculateSalesAndProfitPerProduct();

       @Query("SELECT c.id, c.name, " +
                     "COALESCE(SUM(bi.quantity * p.sellingPrice), 0) AS totalSales, " +
                     "COALESCE(SUM(bi.quantity * (p.sellingPrice - p.price)), 0) AS totalProfit " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p " +
                     "JOIN p.category c " +
                     "GROUP BY c.id, c.name")
       List<Object[]> calculateSalesAndProfitPerCategory();

       @Query("SELECT p.id, p.name, COALESCE(SUM(bi.quantity * p.sellingPrice), 0) AS totalSales " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p " +
                     "GROUP BY p.id, p.name " +
                     "ORDER BY totalSales DESC")
       List<Object[]> findTopProducts(int limit);

       @Query("SELECT c.id, c.name, COALESCE(SUM(bi.quantity * (p.sellingPrice - p.price)), 0) AS totalProfit " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p " +
                     "JOIN p.category c " +
                     "GROUP BY c.id, c.name")
       List<Object[]> calculateProfitByCategory();

       @Query("SELECT c.id, c.name, COALESCE(SUM(bi.quantity * p.price), 0) AS totalExpenses " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p " +
                     "JOIN p.category c " +
                     "GROUP BY c.id, c.name")
       List<Object[]> calculateExpenseSummaryByCategory();

       
}
