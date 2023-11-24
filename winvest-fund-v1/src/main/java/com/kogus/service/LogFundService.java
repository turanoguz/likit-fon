package com.kogus.service;

import com.kogus.entitiy.Fund;
import com.kogus.entitiy.FundLog;
import com.kogus.repository.FundLogRepository;
import com.kogus.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicReference;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogFundService {

    private final FundLogRepository fundLogRepository;
    private final FundRepository fundRepository;

    public List<FundLog> findMostValuedFund() {
        AtomicReference<Double> maxIncrement = new AtomicReference<>((double) 0);
        AtomicReference<Fund> maxIncrementFund = new AtomicReference<>(new Fund());
        fundLogRepository.findLastUpdateFunds().forEach((value) -> {
            Fund fund = fundRepository.getById(Integer.parseInt(value.getFundId()));
            double difference = fund.getFundPrice() - value.getFundPrice();
            if(difference > maxIncrement.get()) {
                maxIncrementFund.set(fund);
                maxIncrement.set(difference);
            }
        });
        Fund fund = maxIncrementFund.get();
        //dbde yapılmalıydı aslında ama vakit yok diye hızlı çözüm :)
        //anydesk ile çağataya bağlanmalı kod yazmalı çok saygılı bir gece (22.08.2023 04.23)
        return getWeekFunds(String.valueOf(fund.getId()));
    }

    public List<FundLog> getWeekFunds(String fundId) {
        List<FundLog> fundLogList = fundLogRepository.findTop6ByFundIdOrderByFundDateAsc(fundId);
        Fund fund = fundRepository.getById(Integer.parseInt(fundId));
        //hibernate interceptor null(bu hatayı 1 hafta sonra çözeceğim :D) FundLog fundLog = ReflectionUtils.cast(fund, FundLog.class);
        FundLog fundLog = FundLog.builder()
                .fundDate(fund.getFundDate())
                .fundCode(fund.getFundCode())
                .fundId(String.valueOf(fund.getId()))
                .fundFounder(fund.getFundFounder())
                .fundName(fund.getFundName())
                .fundPrice(fund.getFundPrice())
                .fundType(fund.getFundType())
                .build();
        fundLogList.add(fundLog);
        return fundLogList;
    }
}
