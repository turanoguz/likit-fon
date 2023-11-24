package com.kogus.repository;


import com.kogus.entitiy.FundLog;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface FundLogRepository extends JpaRepository<FundLog, Integer> {
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<FundLog> findTop6ByFundIdOrderByFundDateAsc(String fundId);
    @QueryHints(value = {@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value="select * from fund_log where id IN (SELECT MAX(id) from fund_log group by fund_id)", nativeQuery = true)
    List<FundLog> findLastUpdateFunds();
}
