package com.zinu.inventory.bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BilledItemRepository extends JpaRepository<BilledItem, Long> {

       // @Query("SELECT SUM(bi.quantity * p.sellingPrice) AS totalSales, " +
       //               "SUM(bi.quantity * (p.sellingPrice - p.price)) AS totalProfit " +
       //               "FROM BilledItem bi " +
       //               "JOIN bi.product p " +
       //               "WHERE bi.createdDate BETWEEN :startDate AND :endDate")
       // Object[] calculateTotalSalesAndProfit(@Param("startDate") LocalDateTime startDate,
       //               @Param("endDate") LocalDateTime endDate);

       // @Query("SELECT COALESCE(SUM(bi.quantity * p.sellingPrice), 0) AS totalSales, " +
       //               "COALESCE(SUM(bi.quantity * (p.sellingPrice - p.price)), 0) AS totalProfit " +
       //               "FROM BilledItem bi " +
       //               "JOIN bi.product p")
       // Object[] calculateTotalSalesAndProfit();

       @Query("SELECT COALESCE(SUM(bi.quantity * p.sellingPrice), 0) AS totalSales, " +
                     "COALESCE(SUM(bi.quantity * (p.sellingPrice - p.price)), 0) AS totalProfit " +
                     "FROM BilledItem bi " +
                     "JOIN bi.product p ")
List<Object[]> calculateTotalSalesAndProfit();


}
